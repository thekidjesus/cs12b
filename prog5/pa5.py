#! /usr/bin/env python
import os.path
from subprocess import call
import sys

# put this file (pa5.py),  and DictionaryClient-out from the web site in the SAME directory as your prog5 directory
# put your Makefile, .c files etc. in prog5
# cd into the directory CONTAINING the prog5 directory
# type python pa5.py .    (NOTE don't forget the DOT)
# if it passes all checks the max score is 10
# This is a minimal test and not a guarantee of full credit.


student = sys.argv[1]
score = 0

if (not os.path.isdir(student)):
    print student,"not a directory"
    sys.exit(1)

os.chdir(student)

if (os.path.isdir("prog5")):
    os.chdir("prog5")
else:
    print "no prog5 directory"
    score = score - 1

if (not os.path.isfile('Makefile')):
    print "No Makefile"
else:
    print "Makefile exists"
    score = score + 1

#call(["rm","BlockchainClient","*.o","testout.txt"])

if (call(["make"]) == 0):
    print "make did something"
    if (os.path.isfile('DictionaryClient')): score = score + 1
    else: print "no DictionaryClient created"
else:
    print "make failed"

if (call(["./DictionaryClient"],stdout=open("testout.txt","w+"))==0):
    print "DictionaryClient ran with output:"
    call(["cat","testout.txt"])
    print "*********End of output********\n"
    score = score + 1
else:
    print "DictionaryClient failed"

if (not os.path.isfile('testout.txt')):
    print "No testout.txt generated"
else:
#    if (call(["diff","-b", "-B", "/afs/cats.ucsc.edu/users/s/mcdowell/12b/pa5/DictionaryClient-out", "testout.txt"]) == 0):
    if (call(["diff","-b", "-B", "../DictionaryClient-out", "testout.txt"]) == 0):
        print "correct testout.txt"
        score = score + 4
    else: print "wrong testout.txt"

if (call(["valgrind","-v","--leak-check=full","--error-exitcode=33","DictionaryClient"])==0):
    print "valgrind passed"
    score = score + 1
else:
    print "valgrind failed"

if (os.path.isfile('log.txt')): score = score + 1
else: print "no log.txt"

if (os.path.isfile('README')): score = score + 1
else: print "no README"

print student, "score is ", score
