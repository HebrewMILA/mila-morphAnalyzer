#! /usr/bin/python3.2
''' input: xml file of tokenized text (to get the headers) and the analyses as a 'word' file
    output: The file in KAF format
    usage: txt2KAF.py mila.xml mila.txt output.xml
    This is a standalone version (incorporates recurse.py and printTree.py)
'''
import sys
import re
import xml.etree.ElementTree as ET
import time
from enum import Enum
from xml.sax.saxutils import escape

###############################################
class Word:
    def __init__(self, line):
        [self.parID, self.sentID, self.id, self.surface, self.prefix, self.definite,
        self.pos, self.type, self.lemma] = line.strip().split('\t')
    def __repr__(self):
        return 'parID={}\tsentID={}\tid={}\tsurface={}\tprefix={}\tdefinite={}\tpos={}\ttype={}\tlemma={}'.format(
        self.parID, self.sentID, self.id, self.surface, self.prefix, self.definite,
        self.pos, self.type, self.lemma)


class Hebrew(str, Enum): 
    (א, ב, ג, ד, ה, ו, ז, ח, ט, י, ך, כ, ל, ם, מ, ן, נ, ס, ע, ף, פ, ץ, צ, ק, ר, ש, ת) = map(chr, range(1488, 1515))


def make_header(root):
    newroot = ET.Element("KAF", {'xml:lang':'he'})
    KAFheader = ET.SubElement(newroot, "kafHeader")
    ET.SubElement(KAFheader, "fileDesc", author="Alon Itai", filetype="txt")
    ET.SubElement(KAFheader, "public")
    linguisticProcessors = ET.SubElement(KAFheader, "linguisticProcessors", layer='text')
    KAFdate = get_date(root.find('metadata'))

    for corpus in root.iter('corpus'):
        version = corpus.get('version', '0')
        ET.SubElement(linguisticProcessors, "lp", name='MILA-Tokenizer', version=version, timestamp=KAFdate)
        #why break?
        break
    return newroot


def get_date(metadata):
    dates = False
    if metadata:
        date = metadata.find('date').text
        dates = re.search('(\d+:\d+) (\d+)\/(\d+)\/(\d+)', date)
        if dates:
            day, month, year = dates.groups()[1:4]
    if not dates:  # use current date
        T = time.localtime()
        year = T.tm_year
        month = T.tm_mon
        day = T.tm_mday
        hour_min_sec = '{}:{}:{}Z'.format(T.tm_hour, T.tm_min, T.tm_sec)
       
    return '{}-{}-{}T{}'.format(year, month, day, hour_min_sec)


###############################################################################
def add_entity(entities, tp, eID, termID):
    entity = ET.SubElement(entities, 'entity', type=tp, eid='e{}'.format(eID))
    references = ET.SubElement(entity, 'references')
    span = ET.SubElement(references, 'span')
    ET.SubElement(span, "target", id='t{}'.format(termID))


def make_span(term, L, wordID):
    span = ET.SubElement(term, "span")
    nComponents = L.count(' ')
    # for MWE this is > 0
    # so the following is executed at least once and more for MWE
    for i in range(nComponents + 1):
        ET.SubElement(span, "target", id='w{}'.format(wordID - nComponents + i))  

########################################################################################
def make_term(termID, wordID, word, terms, entities):
            
    if word.prefix != '-':
        termID = add_prefix(word, terms, termID, wordID)
    if word.pos == 'PNCT':
        return termID  # punctuation is not a term
    if word.definite == 'D':
        termID = add_definite(terms, termID, wordID)
    term = ET.SubElement(terms, "term", lemma=escape(word.lemma), pos=word.pos, tid='t{}'.format(termID))
    make_span(term, word.lemma, wordID)
    return termID + 1


def add_definite(terms, termID, wordID):
    term = ET.SubElement(terms, "term", lemma=Hebrew.ה, pos='D', tid='t{}'.format(termID))
    span = ET.SubElement(term, "span")
    target = ET.SubElement(span, "target", id='w{}'.format(wordID))
    termID += 1
    return termID


def add_prefix(word, terms, termID, wordID):
    for p in word.prefix.split('-'):
        term = ET.SubElement(terms, "term", lemma=escape(p[:-1]), pos=p[-1], tid='t{}'.format(termID))
        span = ET.SubElement(term, "span")
        target = ET.SubElement(span, "target", id='w{}'.format(wordID))
        termID += 1
    return termID


########################################################################################
def makeKAF(f, words, out_F):
    out_f = sys.stdout if out_F == sys.stdout else open(out_F, mode='w')
    root = ET.parse(f)
    newroot = make_header(root)
    
    text = ET.SubElement(newroot, 'text')
    terms = ET.SubElement(newroot, 'terms')
    entities = ET.SubElement(newroot, 'entities')
    
    wordID = 1
    termID = 1
    eID = 1
    for word in words:
        s = ET.SubElement(text, "wf", wid='w{}'.format(wordID), sent=word.sentID, para=word.parID)
        s.text = escape(word.surface)
        ############### add term #################################################
        if word.pos == 'R':  # properName
            tp = word.type
            if tp != '-':
                termID = make_term(termID, wordID, word, terms, entities)
                add_entity(entities, word.type, eID, termID)
                eID +=1
        wordID += 1
        
    print('<?xml version="1.0" encoding="UTF-8" standalone="yes"?>', file=out_f)
    print_tree(newroot, out_f)
    print(file=out_f)


def print_tree(root, out_F):
    if out_F == sys.stdout:
        ET.dump(root)
    else:
        et = ET.ElementTree(root)
        et.write(out_F, encoding='UTF-8', xml_declaration=True)


def main(kaf_filename, word_filename, out_filename):
    with open(word_filename) as word_file:
        words = [Word(line) for line in word_file]
    # for word in words:
    #    print(word)
    makeKAF(kaf_filename, words, out_filename)


if __name__ == '__main__':
    main(*sys.argv[1:])

