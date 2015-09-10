#! /usr/bin/python3


def get_POS(base, surface):
    POS = { 'adjective':'G', 'adverb':'A', 'conjunction':'C', 'copula':'V', 'existential':'O', 'foreign':'O',
            'interjection':'O', 'interrogative':'O', 'modal':'O', 'negation':'O', 'noun':'N', 'numberExpression':'R',
            'numeral':'R', 'participle':'X', 'preposition':'P', 'pronoun':'Q', 'properName':'R', 'propername':'R',
            'quantifier':'O', 'title':'O', 'unknown':'O', 'verb':'V', 'wPrefix':'O', 'passiveParticiple':'O', 'MWE':'MWE',
            'punctuation':'PNCT' }
    termPOS = ''
    for c in base.iter():
        if c.tag in POS:
            termPOS = POS[c.tag] if c.tag != 'participle' else {'adjective':'G', 'noun':'N'}.get(c.get('type'), 'V')
            break
    
    if c.tag == 'MWE':
        # not last component of MWE --> no term
        if ' ' not in base.get('lexiconItem', surface):
            return ''
        val = c.get('pos').lower()
        try:
            termPOS = POS[val]
        except KeyError:
            print('KeyError', val)
    return termPOS


def analyze_entity(base):
    numeral = base.find('numeral')
    if numeral is not None:
        return 'number'
    p = base.find('properName')
    if p is None:  # the word does not appear in Lexicon, so no subtype
        p = base.find('MWE')
        if p is None or p.get('pos') != 'propername':
            return None
    tp = p.get('type', 'person')
    d = {'town':'location', 'country':'location', 'dateTime':'date'}
    return d.get(tp, tp)


def make_term(token):
    for analysis in sorted(token, key=lambda a: float(a.get('score')), reverse=True):
        base = analysis.find('base')
        if base is None or base.find('punctuation') is not None:
            continue
        termPOS = get_POS(base, token.get('surface'))
        if termPOS == '':
            continue
        if termPOS == 'R':  # properName
            tp = analyze_entity(base)
            if tp:
                return tp
        return None  # return after finding the 1st analysis with positive score.


def make_KAF(filename): #, out_f=os.path.devnull):
    from milatools import enumerate_tokens
    import xml.etree.ElementTree as ET
    for _, _, token in enumerate_tokens(ET.parse(filename)):
        print(token.get('surface'), make_term(token))


if __name__ == '__main__':
    import sys
    make_KAF(*sys.argv[1:])
