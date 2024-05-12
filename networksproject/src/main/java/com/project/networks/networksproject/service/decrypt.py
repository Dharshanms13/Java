import json
import sys

with open('D://NewWorkspace//Start//Java//networksproject//src//main//java//com//project//networks//networksproject//service//jsondata.json') as f:
    jsondict = json.loads(f.read())

list = sys.argv[1::]

for obj in list:
    listofcipherletters = obj.split()
    str = ""
    for letter in listofcipherletters:
        name = [alphabet for alphabet, cipher in jsondict.items() if cipher == letter]
        str += ''.join(name)
    print(str)