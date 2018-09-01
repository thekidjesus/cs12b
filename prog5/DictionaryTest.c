/* 
Janson Chiu Jesus Munoz 
jaachiu jmunoz10 
12B 
DictionaryTest.c 
This file tests the Dictionary.c file 
*/ 

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#inckude"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
        char* k; 
        char* v; 
	Dictionary newD = newDictionary();
        char* A[] =  {"I'm", "Almost", "Done", "Yeet"};
        char* B[] = {"I", "Love", "Jesus", "Amen"};
        
	for(int i = 0; i<5; i++){
		insert(newD, A[i], B[i]);
	}

	size(newD);
        printDictionary(stdout, newD); 
        
	delete(newD, "I");
        int j = 0; 
        while(j<5){
		k = A[i];
		v = lookup(newD, k);
		printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
		j++;
	}
	
	printf("%s\n", (isEmpty(newD)? "yes":"no"));
        makeEmpty(newD);
        printf("%s\n", (isEmpty(newD)?"yes":"no"));
        
        freeDictioanry(&newD);
 
	return(EXIT_SUCCESS);
}
        
        
