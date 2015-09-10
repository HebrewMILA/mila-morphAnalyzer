import os
from milatools import open  # utf-8 version
from tagger import get_tagged
from mila_to_text import MilaText

resources_dir = '../../resources'
base_dirname = resources_dir + '/trainset'
lists_dir = resources_dir + '/lists'

def open_before(func):
    def wrap(infilename, outfilename):
        with open(infilename) as infile, open(outfilename, 'w') as outfile:
            return func(infile, outfile)
    return wrap


@open_before
def tagged_to_txt(infile, outfile):
    print(' ', file=outfile, end='')
    print(' '.join([x.split('\t')[3] for x in infile.readlines()]), file=outfile)


@open_before
def txt_to_xml(infile, outfile):
    outfile.write(get_tagged(infile.read()))


def xml_to_tagged(infilename, outfilename):
    with open(outfilename, 'w') as outfile:
        for token in MilaText(infilename):
            print(*token, file=outfile, sep='\t')


@open_before
def full_to_entities(infile, outfile):
    oldnum = '1'
    for line in infile:
        x = line.split('\t')
        oldnum, x[1] = x[1], oldnum
        if x[1] != oldnum:
            print(file=outfile)
        print(x[3], x[4], file=outfile, sep='\t')


def enumerate_entities(infilename):
    'infilename is a file with "{surface}\t{entity}[\t...]" format'
    with open(infilename) as infile:
        for line in infile:
            line = line.strip()
            # print(line)
            if line:
                yield line.split('\t')


def tagged_to_full(infilename, entities_infilename, outfilename):
    from string import punctuation
    def flat(st):
        d = {
          'ך':'כ',
         'ם':'מ',
         'ן':'נ',
         'ף':'פ',
         'ץ':'צ',
         }
        return ''.join(d.get(c, c) for c in st if c not in punctuation + ' \t\n')

    with open(outfilename, 'w') as outfile:
        tokens = list(open(infilename))
        entities = list(enumerate_entities(entities_infilename))
        i = j = 0
        missed = 0
        while i < len(tokens) and j < len(entities):
            token_list = tokens[i].rstrip().split('\t')
            entity = entities[j]
            token_surface = token_list[3]
            b = True
            old_i = i
            next_phrase = ''.join(e[0] for e in entities[i - 3:i])
            if flat(entity[0]) in flat(token_surface) or any(x in flat(token_surface) for x in flat(entity[0]).split('-')):
                token_list.append(entity[1])
                j += 1
                b = False
            if flat(token_surface) in flat(entity[0]) or '"' in next_phrase and next_phrase == token_surface:
                if b:
                    token_list.insert(4, entities[j - 1][1] if token_surface not in punctuation or token_surface == '-' and entities[j - 1][1] == entities[j + 1][1] else 'O')
                i += 1
                b = False
            if b:
                token_list.append('O')
                print(token_surface, entity[0])
                missed += 1
                if missed > 10:
                    raise Exception(next_phrase)
                j += 1
            if old_i != i:
                print(*token_list, file=outfile, sep='\t')
        if tokens[i:]: print(tokens[i:])
        if entities[j:]: print(entities[j:])


def apply_transformer(src_ext, dst_ext, base_dirname=base_dirname, overwrite=False):
    trans = transformer[(src_ext, dst_ext)]
    fmt = '{0}/{2}/{1}.{2}'
    indir = '{0}/{1}'.format(base_dirname, src_ext)
    for fname in os.listdir(indir):
        basename = fname.split('.')[0]
        in_filename = fmt.format(base_dirname, basename, src_ext)
        out_filename = fmt.format(base_dirname, basename, dst_ext)
        if overwrite or not os.path.isfile(out_filename):
            trans(in_filename, out_filename)
            print(out_filename)
        else:
            print(out_filename, 'already exists')


def apply_tagged_to_full(base_dirname=base_dirname):
    fmt = '{0}/{2}/{1}.{2}'
    indir = '{0}/{1}'.format(base_dirname, 'tagged')
    for fname in os.listdir(indir):
        basename = fname.split('.')[0]
        in_filename = fmt.format(base_dirname, basename, 'tagged')
        out_filename = fmt.format(base_dirname, basename, 'full')
        entity_filename = '{0}/{2}/{1}.{3}'.format(base_dirname, basename, 'entities', 'entities')
        print(in_filename, entity_filename, out_filename)
        tagged_to_full(in_filename, entity_filename, out_filename)


def txt_to_tagged(name, base_dirname=base_dirname):
    def f(st): return st.format(base_dirname, name)
    xml = f('{}/xml/{}.xml')
    txt_to_xml(f('{}/txt/{}.txt'), xml)
    xml_to_tagged(xml, f('{}/tagged/{}.tagged'))


def tagged_to_poslist(infilename, _ignore):
    with open(infilename) as infile:
        for line in infile:
            tagged_to_poslist.set.add(line.split('\t')[10].strip())


def make_poslist():
    tagged_to_poslist.set = set()
    apply_transformer('tagged', 'poslist')
    outfilename = '{}/{}'.format(lists_dir, 'pos_list.txt')
    with open(outfilename, 'w') as outfile:
        for t in sorted(tagged_to_poslist.set):
            print(t, file=outfile)


transformer = {
    ('txt', 'xml'):txt_to_xml,
    ('xml', 'tagged'):xml_to_tagged,
    ('tagged', 'txt'):tagged_to_txt,
    ('tagged', 'full'):tagged_to_full,
    ('full', 'entities'):full_to_entities,
    ('tagged', 'poslist'): tagged_to_poslist,
    }


if __name__ == '__main__':
    #apply_transformer('xml', 'tagged', overwrite=True)
    # apply_tagged_to_full()
    make_poslist()
