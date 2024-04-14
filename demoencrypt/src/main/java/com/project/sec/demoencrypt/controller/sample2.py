import sys
import os

def sampleMethod():
    print("Hello World!")
    num1=int(sys.argv[1])
    num2=int(sys.argv[2])
    num1 *= num1
    num2 *= num2
    print(num1)
    print(num2)

sampleMethod()