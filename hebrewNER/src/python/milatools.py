#! /usr/bin/python3.2
from functools import partial
import tempfile
import sys

open = partial(open, encoding='utf-8')


log = partial(print, file=sys.stderr)


def chdir_to_base():
    import os
    while 'hebrew' not in os.getcwd().lower():
        os.chdir("../")


def temp_file(iterable=()):
    f = tempfile.NamedTemporaryFile('w+', encoding='utf-8') #
    for line in iterable:
        print(line, file=f)
    f.seek(0)
    return f


def as_binary_file(iterable=()):
    f = tempfile.TemporaryFile()
    f.writelines([x.encode('utf-8')+b'\r\n' for x in iterable])
    f.seek(0)
    return f


def timed(f):
    '''decorator for printing the timing of functions
    usage: 
    @timed
    def some_funcion(args...):'''
    from time import clock
    def wrap(*x, **d):
        start = clock()
        res = f(*x, **d)
        log(f.__name__, ':', clock() - start)
        return res
    return wrap


if __name__ == '__main__':
    for line in temp_file(['123', '456']):
        print(line)   
