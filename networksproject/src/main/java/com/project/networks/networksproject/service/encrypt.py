import json
import sys

with open('D://NewWorkspace//Start//Java//networksproject//src//main//java//com//project//networks//networksproject//service//jsondata.json') as f:
    jsondict = json.loads(f.read())

list = sys.argv[1::]
# print(list)

for obj in list:
    str = ""
    for letter in obj:
        str += jsondict[letter] + " "
    print(str)