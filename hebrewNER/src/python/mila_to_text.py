#! /usr/bin/python3.2
from milatools import log
import xml.etree.ElementTree as ET
from collections import namedtuple

Token = namedtuple('Token', ['paragraph', 'sentence', 'id', 'surface', 'lemma', 'pref', 'definite', 'cons', 'pos', 'type', 'hmm_pos'])


def logElem(e, *pref):
    log(*pref, end=" ")
    log(ET.tostring(e, 'utf-8'))


def getOriginal(a):
    base = a.find('base')
    if base is not None:
        return _get_type(_getPOS(base), base)


class MilaText:
    def __init__(self, xml_filename):
        try:
            self.et = ET.parse(xml_filename)
        except IOError:
            raise  # self.root = ET.fromstring(xml_filename)

    header = '\t'.join(map('{{{}}}'.format,
            ['paragraph', 'sentence', 'id', 'surface', 'lemma', 'pref', 'definite', 'cons', 'pos', 'type', 'hmm_pos']))

    @property
    def root(self):
        return self.et.getroot()

    def __iter__(self):
        for par, sent, token in self._enumerate_tokens():
            yield _get_word(token, int(par), int(sent))

    def iter_strings(self):
        yield from ('\t'.join(str(x) for x in token) for token in self)

    def get_token(self, t):
        article = 1
        return self.root[article - 1][t.paragraph - 1][t.sentence - 1][t.id - 1]

    def update_score(self, t, selected_analysis, p=0.2):
        s = selected_analysis.attrib
        for a in self.get_token(t):
            att = a.attrib
            f = float(att['score'])
            att['score'] = str(f-p*f)
            s['score'] = str(float(s['score']) + p*f)

    def set_type(self, t, entity):
        if not entity:
            return
        tok = self.get_token(t)
        for pN in tok.findall('.//properName'):
            pN.set('type', entity)
        # the following feature changes the DTD!
        # for mwe in tok.findall('.//MWE'):
        #    mwe.set('type', entity)

    def write(self, file):
        return self.et.write(file, encoding="utf-8", xml_declaration=False)

    def _enumerate_tokens(self):
        for par in self.root.iter('paragraph'):
            for sent in par.iter('sentence'):
                for token in sent.iter('token'):
                    yield par.get('id'), sent.get('id'), token


def _get_type(pos, base, all_bases=()):
    if pos == 'numeral':
        return 'number'
    if 'MWE' != pos != 'R':
        return '-' 
    pN = base.find('properName')
    if pN is None: 
        # the word does not appear in Lexicon, so no subtype
        mwe = base.find('MWE')
        pN = mwe if mwe is not None and mwe.get('pos') == 'propername' else {}
    res = pN.get('type', '-')
    if res != '-':
        #log("get type:", res)
        for other_res in {_get_type(_getPOS(b), b, ()) for b in filter(None, all_bases)} - {'-', res}:
            res += '|' + other_res
    return res


def _prefix(a):
    _prefixPOS = {'relativizer':'C', 'relativizer/subordinatingConjunction':'C', 'conjunction':'C', 'temporalSubConj':'C',
                  'definiteArticle':'D', 'preposition':'P', 'adverb':'A'}
    def get(p):
        return p.get('surface') + _prefixPOS.get(p.get('function'), '') 
    return '-'.join([get(p) for p in a.iter('prefix')] or '-')


def get_pos_for_HMM(base):
    if not (base and len(base)):
        return '[]'
    prop = base[0]
    d = dict(prop.attrib)
    # log("get_pos:", d)
    tag = prop.tag if prop.tag != 'participle' else d['type']
    if tag in ['verb', 'copula', 'modal']:
        return '{verb}'
    if tag in ['pronoun', 'quantifier']:
        return '{{{}}}'.format(tag)
    if tag == 'noun':
        d = {k:v for k, v in d.items() if k in {'definiteness', 'gender', 'number', 'status', 'foreign'} }
    dismiss = ['root', 'expansion', 'multiWordUndotted', 'value', 'multiWordTransliterated', 'spelling', 'register', 'type']
    for k in dismiss:
        d.pop(k, None)
    res = '{' + tag + '} [' + ','.join('({}: {})'.format(k, v) for k, v in sorted(d.items())) + ']'
    return res


def _getPOS(b):
    _POS = {'adjective':'G', 'adverb':'A', 'conjunction':'C', 'copula':'V', 'noun':'N', 'numberExpression':'R', 'numeral':'R',
            'participle':'X', 'preposition':'P', 'pronoun':'Q', 'properName':'R', 'propername':'R', 'verb':'V', 'MWE':'MWE',
            'punctuation':'PNCT',
            'interrogative':'O', 'modal':'O', 'negation':'O', 'quantifier':'O', 'title':'O', 'unknown':'O',
            'wPrefix':'O', 'existential':'O', 'foreign':'O', 'interjection':'O', 'passiveParticiple':'O'}
    p = [c for c in b if c.tag in _POS]
    if not p:
        return 'O'
    c = p[0]
    if c.tag == 'participle':
        return _POS.get(c.get('type'), 'V')
    return _POS[c.tag]


def _get_word(token, paragraph, sentence):
    def get_score(x):
        return float(x.get('score'))
    all_analysis = list(filter(get_score, token.iter('analysis')))
    analysis = max(all_analysis, key=get_score)
    surface = token.get('surface')
    base = analysis.find('base')
    tid = int(token.get('id'))
    pref = _prefix(analysis)
    if base is None:
        return Token(**{
           'paragraph' : paragraph,
           'sentence' : sentence,
           'id'   : tid,
           'pref' : pref,
           'lemma': surface,
           'surface' : surface,
           'definite'  : '-',
           'cons' : 'F',
           'type' : '-',
           'pos'  : '-',
           'hmm_pos' : '[]'
          })

    pos = _getPOS(base)
    return Token(**{
       'paragraph' : paragraph,
       'sentence' : sentence,
       'id'   : tid,
       'pref' : pref,
       'lemma': base.get('lexiconItem', surface),
       'surface' : surface,
       'definite'  : 'D' if len(base) and base[0].get('definiteness') == 'true' else '-',
       'cons' : 'T' if len(base) and base[0].get('status') == 'construct' else 'F',
       'type' : _get_type(pos, base, list(a.find('base') for a in all_analysis)),
       'pos'  : pos,
       'hmm_pos' : get_pos_for_HMM(base),
      })


if __name__ == '__main__':
    #print(MilaText.header)
    import sys
    print('\t'.join(['paragraph', 'sentence', 'id', 'surface', 'lemma', 'pref', 'definite', 'cons', 'pos', 'type', 'hmm_pos']))
    #with open(sys.argv[2], 'w', encoding='utf-8') as f:
    for token in MilaText( "resources\\examples\\xml\\test.xml").iter_strings():
        print(token)
