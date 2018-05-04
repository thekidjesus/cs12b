//Jesus Munoz jmunoz10 12M
//Blockchain.c 
//the juicy stuff
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include <string.h>
#include"Blockchain.h"
#include"Block.h"

#define MAX_BLOCK_NUM 100


typedef struct BlockchainObj{
	Block chain [MAX_BLOCK_NUM];
	int length;
} BlockchainObj;


//constructor
Blockchain newBlockchain(){
	Blockchain B = malloc(sizeof(BlockchainObj));
	assert(B!=NULL);
	B->length=0;
	return(B);
}

//freeBlockchain()
void freeBlockchain(Blockchain* pB){
	if(pB!=NULL && *pB!=NULL){
	while (size(*pB)>0) removeLast(*pB);// leak fix?
		free(*pB);
		*pB=NULL;
	}
}

int append(Blockchain B, char* data){
	//ensure argument isn't null
	if (B==NULL || data==NULL){
		fprintf(stderr,"Blockchain or data argument in append() is NULL\n");
		exit(EXIT_FAILURE);
	}
	//if arguments arent NULL, execute:
	//
	//if chain isn't valid then return 0
	if (valid(B)==0) return 0;

	Block toAppend;	
	long newHash;
	

	if (size(B)==0){
		newHash=0;
		//B->chain[0]=newBlock(data,0,newHash);
		toAppend=newBlock(data,size(B),newHash);
	}

	else if (size(B)>0){
		//newHash=(long)data+(long)id+(long)hash(get(B,id-1));
		newHash=hash(get(B,size(B)-1));
		toAppend=newBlock(data,size(B),newHash);
	}

	B->chain[size(B)]=toAppend;
	B->length++;
	//freeBlock(&toAppend);//doesnt fix leak
	return size(B);
}

int size(Blockchain B){
	//ensure argument isnt NULL
	if (B==NULL){
		fprintf(stderr,"Blockchain argument is NULL in size()\n");
		exit(EXIT_FAILURE);
	}
	//if argument isn't NULL, execute:
	return B->length;
}

Block get(Blockchain B, int idx){
	//ensure argument isnt NULL
	if(B==NULL){
		fprintf(stderr,"Blockchain argument in get() is NULL \n");
		exit(EXIT_FAILURE);
	}
	if(idx<0){
		fprintf(stderr,"idx in get() is negative");
	}
	//if argument isnt NULL 
	return B->chain[idx];
}
int valid(Blockchain B){
	if (B==NULL) fprintf(stderr, "argument in valid() is NULL");
	if (size(B)<=1) return 1;

	for(int i=0;i<size(B)-1;i++){
		if (hash(get(B,i))!=previousHash(get(B,i+1)))
			return 0;	

	}
	return 1;
}
void removeLast(Blockchain B){
	if (B==NULL){
		fprintf(stderr,"Blockchain argument is NULL in removeLast()\n");
		exit(EXIT_FAILURE);
	}
	if (size(B)==0){
		fprintf(stderr, "Blockchain B is empty in removeLast()\n");
		exit(EXIT_FAILURE);
	}
	////B->chain[size(B)]=NULL; //leaks
	Block toRemove=get(B,size(B)-1);
	freeBlock(&toRemove);
	B->length--;

}
void printBlockchain(FILE* out, Blockchain B){
	if (B==NULL){
		fprintf(stderr, "Blockchain Error: calling printBlockchain() on NULL Blockchain reference\n");
		exit(EXIT_FAILURE);
	}

	for (int i=0;i<size(B);i++){
		printBlock(out,get(B,i));
	}

}
