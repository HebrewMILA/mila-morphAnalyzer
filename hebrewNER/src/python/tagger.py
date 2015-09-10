'''
An API to the tagger over the web
'''

from http.client import HTTPConnection, HTTPException

headers = { 'Content-Type': 'application/x-www-form-urlencoded' }

host = 'yeda.cs.technion.ac.il'

from urllib.parse import urlencode

class EmptyResult(HTTPException):
    pass


class Tagger:
    def get_tagged(self, data, splitted=True):
        encoded_data = urlencode({'input_text'.encode('utf-8') : data.encode('utf-8')})
        self.conn.request('POST', '/MWE/processAnalyzerXML.jsp', encoded_data, headers=headers)
        res = self.conn.getresponse().read().decode('utf-8')
        if not res.strip():
            raise EmptyResult
        if splitted:
            res = res.split("\n")[1]
        return res
    
    def __enter__(self):
        self.conn = HTTPConnection(host, 8088)
        return self
    
    def __exit__(self, exc_type, exc_value, traceback):
        self.conn.close()


def get_tagged(text):
    with Tagger() as t:
        return t.get_tagged(text)
    

if __name__ == '__main__':
    test_data = ['טקסט פשוט',
                 'שליש מהם נהנים ממנו בבוקר , 14 אחוז אוכלים אותו בערב , ואילו 3 % אוכלים קורנפלקס בצהריים .',
                 '55 % מהישראליות צורכות דגני בוקר באופן סדיר , לעומת 43 % מהגברים .',
                 ]
    for i in test_data:
        print(get_tagged(i), end='')
