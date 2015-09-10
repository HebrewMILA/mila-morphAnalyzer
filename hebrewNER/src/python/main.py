import sys
from collections import Counter

from milatools import chdir_to_base, log, timed
import mila_to_text as xml

META_SEPARATOR = '\0'

XSD_ENTITIES = [
        "person",
        "location",
        "organization",
        "product",
        "dateTime",
        "country",
        "town",
        "language",
        "symbol",
        "art",
        "other",
        "unspecified",
    ]

def nice_name(name):
    return {
         'PERS' : 'person',
         'LOC' : 'location',
         'ORG' : 'organization',
         'MONEY' : 'money',
         'TIME' : 'time',
         'PERCENT' : 'percent',
         'DATE' : 'date',
         'ENT' : 'entity',
         'AFF' : 'nation',
         'EVENT' : 'event',
         'NUM' : 'number',
         'O' : '',
         '' : '',
     }[name]


def analyze(mila_filename, entities_filename):
    content = xml.MilaText(mila_filename)
    with open(entities_filename, encoding='utf8') as f:
        print(f.read())
    with open(entities_filename, encoding='utf8') as f:
        baseline=[]; hmm=[]; maxent=[]
        for line in f:
            bi, hi, mi = [r.split()[1] for r in line.split(META_SEPARATOR)]
            baseline.append(bi); hmm.append(hi); maxent.append(mi)
    bhm = 'SOS'
    for t, b, h, m in zip(content, baseline, hmm, maxent):
        for possible_analysis in content.get_token(t):
            mila_entity = xml.getOriginal(possible_analysis)
            bhm = merge(bhm, b, h, m, mila_entity)
            if mila_entity == nice_name(bhm):
                content.update_score(t, possible_analysis)
    return content


def mila_coerce(hebner, mila_entity):
    table = {
         'PERS' : ['person'],
         'LOC' : ['location', 'town'],
         'ORG' : ['organization'],
         'MONEY' : ['money'],
         'TIME' : ['dateTime', 'location'], #yes it does not make sense
         'DATE' : ['dateTime', 'location'],
         'PERCENT' : [],
         'ENT' : [],
         'AFF' : ['language'],
         'EVENT' : [],
         'NUM' : [],
         'O' : [],
         '' : [],
    }
    if mila_entity in (table.get(hebner, []) + ['person', 'unspecified', 'other']):
        return mila_entity
    return ''


def merge(prev, baseline_, hmm_, maxent_, mila_entity):
    def tag(x):
        if x != 'O':
            res = x.split('_')[-1]
            #if mila_coerce(res, mila_entity) == mila_entity:
            return res
        return ''
    baseline, hmm, maxent = [tag(x) for x in (baseline_, hmm_, maxent_)]
    if prev == 'SOS':
        return maxent

    def majority():
        [[t, n]] = Counter([baseline, hmm, maxent]).most_common(1)
        if n > 1:
            return t

    def consistent():
        for t in (baseline_, hmm_, maxent_,):
            if tag(t) == prev:  # and t.startswith('B_')  
                return tag(t)

    def one_vote():
        res = [x for x in (baseline, hmm, maxent) if x]
        if len(res) == 1:
            return res[0]

    def naama():
        res = hmm
        if baseline == 'TIME' or maxent == 'DATE' and hmm != 'DATE':
            res = baseline

        if res:
            if hmm in ['LOC', 'ORG', 'MONEY', 'PERS']:
                res = hmm
            elif baseline == 'LOC' or hmm == baseline and hmm != 'PERS':
                res = baseline
        return res

    return majority() or consistent() or one_vote() or naama()  # or maxent


@timed
def run(mila_filename, entities_filename, outfile=sys.stdout.buffer, filetype='xml'):
    chdir_to_base()
    log('running')
    analyze(mila_filename, entities_filename).write(outfile)
    log('done')

if __name__ == '__main__':
    run('resources/temp/katz.xml', '', 'resources/temp/out.xml')
