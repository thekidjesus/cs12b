#! /usr/bin/env python

# put this file and model-out in the directory containting your lab6 directory then type
# python lab6.py lab6 
# max score is 10 - this does NOT guarantee a 10 - other checks may be made in the final evaluation
# especially with regard to correct output.


import os.path
from subprocess import call
import sys

student = sys.argv[1]
score = 0

if (not os.path.isdir(student)):
    print student,"not a directory"
    sys.exit(1)

os.chdir(student)

if (os.path.isdir("lab6")):
    os.chdir("lab6")

call(["rm","testout.txt","makeout.txt","List.class","ListClient.class"])


if (not os.path.isfile('Makefile')):
    print "No Makefile"
else:
    print "Makefile exists"
    score = score + 1


result = open("Makefile").read()
try:
    result.index("Xlint")
    print "Makefile seems to contain -Xlint"
    score = score + 1
except:
    print "Makefile does not use -Xlint"
    score = score - 2

if (call(["make"],stderr=open("makeout.txt","w+")) == 0):
    print "make did something"
    if (os.path.isfile('ListClient')): score = score + 1
    else: print "no ListClient created"
else:
    print "make failed"

result = open("makeout.txt").read()
try:
    result.index("unchecked")
    print "unchecked warning found"
except:
    score = score + 1
try:
    result.index("overrides")
    print "overrides warning found"
except:
    score = score + 1


if (call(["java","-jar","ListClient"],stdout=open("testout.txt","w+"))==0):
    print "ListClient ran with output:"
    call(["cat","testout.txt"])
    print "*********End of output********\n"
    score = score + 1
else:
    if (not os.path.isfile('ListClient')):
        print "no ListClient created - running java ListClient"
        score = score - 2
        if (call(["java","ListClient"],stdout=open("testout.txt","w+")) == 0):
            print "ListClient(non-jar) ran with output:"
            call(["cat","testout.txt"])
            print "*********End of output********\n"
        else:
            print "java ListClient failed"

if (not os.path.isfile('testout.txt')):
    print "No testout.txt generated"
else:
    if (call(["diff","-b", "-B", "../model-out", "testout.txt"]) == 0):
        print "output is correct\n"
        score = score + 3
    else:
        print "output differed from expected\n"

if (os.path.isfile('log.txt')): score = score + 1
else: print "no log.txt"

print student, "score is ", score
