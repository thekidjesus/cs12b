//Jesus Munoz jmunoz10 12M
//Block.c
//the juicy stuff

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "Block.h"

// private
// BlockObj
typedef struct BlockObj{
	char* data;
	int id;
	long hash;
} BlockObj;

//constructor of Block
Block newBlock(char* data, int id, long hash){
	Block B = malloc(sizeof(BlockObj));
		  assert(B!=NULL);
	B->data = data;
	B->id = id;
	B->hash = hash;
	return(B);
}
// public 
void freeBlock(Block* pB){
	if( pB!=NULL && *pB!=NULL ){
		free(*pB);
		*pB = NULL;
	}
}
char* data(Block B){
	if (B==NULL){ fprintf(stderr, "argument for data() is NULL");
	exit(EXIT_FAILURE);}

	return B->data;
}

long previousHash(Block B){
	if(B==NULL){
	fprintf(stderr,"Argument for previousHash() is null");
	exit(EXIT_FAILURE);
	}

	return B->hash;
}
long hash(Block B){
	if(B==NULL){
        fprintf(stderr,"Argument for hash() is null");
        exit(EXIT_FAILURE);
	}	
	long newHash = 0;

          for(int i = 0; i<strlen(data(B)); i++){
                newHash += data(B)[i];
          }
	return newHash + B->id + previousHash(B);
   
}

void printBlock(FILE* out, Block B){
	fprintf(out, "%d:",B->id);
	fprintf(out, "%s\n",B->data);

	}

