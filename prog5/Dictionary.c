/* 
Janson Chiu Jesus Munoz 
jaachiu jmunoz10 
12B 
Dictionary.c 

This files uses the Dictionary ADT with Hash tables 
*/
#include <stdio.h>
#include <string.h>
#include <assert.h>
#include <stdlib.h>
#include "Dictionary.h"


//test with other table sizes to guarantee collisions (prime #s)
const int tableSize=101;

typedef struct NodeObj{
	char* key;
	char* value;
	struct NodeObj* next;
}NodeObj;

typedef NodeObj* Node;

//constructor of newNode
Node newNode(char* k, char* v){
	Node N =malloc(sizeof(Node));
	assert(N!= NULL);
	N->key=k;
	N->value=v;
	N->next=NULL;
	return N;
}

// Dictionary
typedef struct DictionaryObj{
	Node* table;
	int numItems;
}DictionaryObj;

// new dictionary
Dictionary newDictionary(void){
	Dictionary newD = malloc(sizeof(DictionaryObj));
	assert(newD!= NULL);
	newD->table = calloc(tableSize, sizeof(Node*));
        newD->numItems = 0;
        return newD;
}

// node destructor
void freeNode(Node* pN){
	if(pN!=NULL && *pN!=NULL){
		free(*pN);
		*pN=NULL;
	}
}

// dictionary destructor
void freeDictionary(Dictionary* pD){
    if(pD != NULL && *pD != NULL){
        makeEmpty(*pD);
        free((*pD)->table);
	free(*pD);
	*pD=NULL;
    }
}


// isEmpty()
int isEmpty(Dictionary D){
	if (D->numItems==0)return 1;
	return 0; 
}

// size()
int size(Dictionary D){
	if (D ==NULL){
	fprintf(stderr,"argument in size() is NULL");
        exit(EXIT_FAILURE);
	}
	return D->numItems;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
	if(D==NULL){
		fprintf(stderr, "can't call lookup() on NULL reference\n");
                exit(EXIT_FAILURE);
        }
	int hashValue=hash(k);
	while (D->table[hashValue]!=NULL){
		if (strcmp(D->table[hashValue]->key,k)==0){
			return D->table[hashValue]->value;
		}
		else D->table[hashValue]=D->table[hashValue]->next;	
	}		
	return NULL;
}
// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
	if(lookup(D,k)!=NULL){
		fprintf(stderr, "key is already in Dictionary");
                exit(EXIT_FAILURE);
	}else{
		int hashValue = hash(k);
        	Node N = newNode(k, v);
		if(D->table[hashValue] == NULL){
			D->table[hashValue] = N;
        	}else{
			Node tempNode = D->table[hashValue];
			D->table[hashValue] = N;
			D->table[hashValue]->next = tempNode;
		}
	D->numItems++;
        }
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
	if (lookup(D,k)==NULL){
		fprintf(stderr,"key wasn't found in Dictionary");
                exit(EXIT_SUCCESS);
	}
	int hashValue=hash(k);
        Node temp; 
        Node toDelete;
	if (strcmp(D->table[hashValue]->key,k)==0){
		temp=D->table[hashValue];
		D->table[hashValue]=D->table[hashValue]->next;
		freeNode(&temp);
	}else {
		temp=D->table[hashValue];
		while(strcmp(temp->next->key,k)==0){
			temp=temp->next;
		}
		toDelete =temp->next;
		temp->next=toDelete->next;
		freeNode(&toDelete);

	}D->numItems--;
}
// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
int i=0;
  while(i<tableSize){
      while(D->table[i] != NULL){
         Node N;
         N = D-> table[i];
         D->table[i]=N->next;
         freeNode(&N);
         N = NULL;
      }i++;
   }
   D->numItems = 0;
}
// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){ 
    int i = 0;  
    Node N;
    while(i<tableSize){
        if(D->table[i] != NULL){
        	N= D->table[i];
		while(N != NULL){
        		fprintf(out,"%s ",N->key);
        		fprintf(out,"%s\n",N->value);
        		N=N->next;
        	}
        }
    i++; 
    }
}
// provided functions:
 
// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
	int sizeInBits = 8*sizeof(unsigned int);
	shift = shift & (sizeInBits - 1);
	if ( shift == 0 )
	return value;
	return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
	unsigned int result = 0xBAE86554;
	while (*input) {
	result ^= *input++;
	result = rotate_left(result, 5);
	}
	return result;
}
// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
	return pre_hash(key)%tableSize;
}

// end of provided functions
