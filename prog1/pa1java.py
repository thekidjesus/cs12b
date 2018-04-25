#! /usr/bin/env python
import os.path
import fileinput
from subprocess import call

score = 0

if (not os.path.isfile('Makefile')):
    score = score - 1
    print "No Makefile"
else:
    print "Makefile exists"

call(["rm","Recursion"])

if (call(["make"]) == 0):
    print "make did something"
    if (not os.path.isfile('Recursion')): 
        print "no Recursion created"
        score = score - 1
else:
    print "make failed"
    score = score - 2

if (call(["java","-jar","Recursion"],stdout=open("out.txt","w+")) == 0):
    print "Recursion ran with output:"
    call(["cat","out.txt"])
    print "*********End of output********\n"
else:
    print "Recursion failed"

result = open("out.txt")
line = result.readline()
if (not line.strip() == "-1 2 6 12 9 2 -5 -2 8 5 7"): print "wrong first line - continuing"
line = result.readline()
if (line.strip() == "minIndex = 6"): score = score + 1
else: print "wrong minIndex"
line = result.readline()
if (line.strip() == "maxIndex = 3"): score = score + 1
else: print "wrong maxIndex"
line = result.readline()
if (line.strip() == "7 5 8 -2 -5 2 9 12 6 2 -1"): score = score + 1
else: print "wrong reverseArray1"
line = result.readline()
if (line.strip() == "7 5 8 -2 -5 2 9 12 6 2 -1"): score = score + 1
else: print "wrong reverseArray2"
line = result.readline()
if (line.strip() == "7 5 8 -2 -5 2 9 12 6 2 -1"): score = score + 1
else: print "wrong reverseArray3"

src = open("Recursion.java").read()

src = src.replace("-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7","-1, 2, -3, 4, -5, 6, -7, 8, -9, 10, -11, 12")
src = src.replace("LEN 11", "LEN 12")
src = src.replace("Array1(A, A.length", "Array1(A, A.length/2")
src = src.replace("Array2(A, A.length", "Array2(A, A.length/2")
src = src.replace("Array3(A, 0, A.length-1", "Array3(A, 0, A.length/2")
src = src.replace("Recursion", "xyz")
f= open("xyz.java","w+")
f.write(src)
f.close()

call(["javac","xyz.java"])

if (call(["java","xyz"],stdout=open("out2.txt","w+")) == 0):
    print "Recursion2 ran with output:"
    call(["cat","out2.txt"])
    print "*********End of output********\n"
else:
    print "Recursion2 failed"

result = open("out2.txt")
line = result.readline()
if (not line.strip() == "-1 2 -3 4 -5 6 -7 8 -9 10 -11 12"):
    print "unexpected first line - continuing"
    print line.strip()
line = result.readline()
if (line.strip() == "minIndex = 10"): score = score + 1
else: print "wrong minIndex"
line = result.readline()
if (line.strip() == "maxIndex = 11"): score = score + 1
else: print "wrong maxIndex"
line = result.readline()
if (line.strip() == "0 0 0 0 0 0 6 -5 4 -3 2 -1"): score = score + 1
else: print "wrong reverseArray1"
line = result.readline()
if (line.strip() == "12 -11 10 -9 8 -7 0 0 0 0 0 0"): score = score + 1
else: print "wrong reverseArray2"
line = result.readline()
if (line.strip() == "-7 6 -5 4 -3 2 -1 8 -9 10 -11 12"): score = score + 1
else: print "wrong reverseArray3"

print "score is ", score

call(["rm","xyz.java","xyz.class","Recursion","Recursion.class"])
