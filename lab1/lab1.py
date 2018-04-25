#! /usr/bin/env python
import os.path
from subprocess import call

score = 0

if (not os.path.isfile('Makefile')):
    print "No Makefile"
else:
    print "Makefile exists"
    score = score + 1

call(["rm","HelloUser.class","HelloUser2.class","Hello"])

if (call(["make"]) == 0):
    print "make did something"
    if (os.path.isfile('HelloUser.class')): score = score + 1
    else: print "no HelloUser.class"
    if (os.path.isfile('HelloUser2.class')): score = score + 1
    else: print "no HelloUser2.class"
    if (os.path.isfile('Hello')): score = score + 1
    else: print "no Hello"
else:
    print "make failed"

if (call(["java","HelloUser"],stdout=open("hello.txt","w+")) == 0):
    print "HelloUser ran with output:"
    call(["cat","hello.txt"])
    print "*********End of output********\n"
    score = score + 1
else:
    print "HelloUser failed"

if (call(["java","HelloUser2"],stdout=open("hello2.txt","w+")) == 0):
    print "HelloUser2 ran with output:"
    call(["cat","hello2.txt"]);
    print "*********End of output********\n"
    score = score + 1
else:
    print "HelloUser2 failed"

if (call(["java","-jar","Hello"],stdout=open("hello3.txt","w+")) == 0):
    print "Hello ran with output:"
    call(["cat","hello3.txt"]);
    print "*********End of output********\n"
    score = score + 1
else:
    print "Hello failed"

if (call(["make","clean"]) == 0):
    print "make clean did something"
else:
    print "make clean failed"

if (os.path.isfile('HelloUser.class')):
    print "make clean failed to remove .class file"
else:
    print "make clean removed .class files (or they didn't exist in the first place)"
    score = score + 1

if (os.path.isfile('log.txt')): score = score + 1
else: print "no log.txt"

if (os.path.isfile('README')): score = score + 1
else: print "no README"

print "score is ", score
