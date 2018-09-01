#! /usr/bin/env python
import os.path
import fileinput
from subprocess import call
import string
import sys

# put this file (pa4.py),  and model-out from the web site in the SAME directory as your prog4 directory
# put your Makefile, .java files etc. in the prog4 directory 
# cd into the directory CONTAINING the prog4 directory
# type python pa4.py .    (NOTE don't forget the DOT)
# if it passes all checks the max score is 12
# This is a minimal test and not a guarantee of full credit.


score = 0
student = sys.argv[1]

if (not os.path.isdir(student)):
    print student,"not a directory"
    sys.exit(1)

os.chdir(student)

if (os.path.isdir("prog4")):
    os.chdir("prog4")
else:
    print "no prog4 directory"
    score = -1

#call(["rm","ex1","ex1.trc","ex1.rpt","*.class"])

if (not os.path.isfile('Makefile')):
    print "No Makefile"
else:
    print "Makefile exists"
    score = score + 1
    if (call(["make"]) == 0):
        print "make did something"
        if (os.path.isfile('DictionaryClient')): score = score + 1
        else: print "no DictionaryClient created"
    else:
        print "make failed"
        call(["javac", "DictionaryClient.java"]);

if (not os.path.isfile('DictionaryTest.java')):
    print "No DictionaryTest.java"
else:
    if (call(["java","DictionaryTest"],stdout=open("dtestout.txt","w+")) == 0):
        print "DictionaryTest ran with output:"
        call(["cat","dtestout.txt"])
        print "*********End of output********\n"

        src = open("DictionaryTest.java").read()
        score = score + 6
        if (string.find(src,"isEmpty") == -1):
            print "isEmpty not tested in DictionaryTest"
            score = score -1
        if (string.find(src,"size") == -1):
            print "size not tested in DictionaryTest"
            score = score -1
        if (string.find(src,"lookup") == -1):
            print "lookup not tested in DictionaryTest"
            score = score -1
        if (string.find(src,"insert") == -1):
            print "insert not tested in DictionaryTest"
            score = score -1
        if (string.find(src,"delete") == -1):
            print "delete not tested in DictionaryTest"
            score = score -1
        if (string.find(src,"makeEmpty") == -1):
            print "makeEmpty not tested in DictionaryTest"
            score = score -1

if (os.path.isfile('DictionaryClient')):
    if (call(["java","-jar","DictionaryClient"],stdout=open("testout.txt","w+")) == 0):
        print "DictinaryClient ran with output"
        call(["cat","testout.txt"])
        print "*********end of output********"
    else:
        print "DictionaryClient failed"
else:
    if (call(["java","DictionaryClient"],stdout=open("testout.txt","w+")) == 0):
        print "DictinaryClient (non-jar version) ran with output"
        call(["cat","testout.txt"])
        print "*********end of output********"
        score = score - 1
    else:
        print "java DictionaryClient failed"

#if (call(["diff","-b", "-B", "/Users/charlie/grading/12b/s18/pa4/model-out", "testout.txt"]) == 0):
if (call(["diff","-b", "-B", "../model-out", "testout.txt"]) == 0):
    score = score + 3
    print "output was correct"
else:
    print "output DIFFERED"

if (not os.path.isfile('log.txt')):
    print "No log.txt"
else:
    score = score + 1
    print "log.txt exists"

print "score is ", score

