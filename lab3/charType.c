/*Jesus Munoz jmunoz10 12M
This programs displays the type of characters in each line
Some of the program is similar to lab2 and alpha_num*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>
#define MAX_STRING_LENGTH 100

//prototype
void extract_chars(char* s, char* a, char* d, char* p,char* w);
void extract_alpha(char* s, char* a);
void extract_digits(char* s, char* a);
void extract_punct(char* s, char* a);
void extract_space(char* s, char* a);
void print_line(char* s, char* a, char* d, char* p,char* w, int counter,FILE* out);

int main(int argc, char* argv[]){

FILE* in;
FILE* out;
char* line;
char* alphabetic;
char* numeric;
char* punctuation;
char* whitespace;

if( argc!= 3){
  printf("Wrong number of command line arguments, try again my guy");
  exit(EXIT_FAILURE);
}
 in= fopen(argv[1],"r");
 if(in==NULL){
   printf("Unable to read from file %s\n",argv[1]);
   exit(EXIT_FAILURE);
 }

 out=fopen(argv[2],"w");
 if(out==NULL){
   printf("Unable to write to file %s\n",argv[2]);
   exit(EXIT_FAILURE);
 }
line=calloc(MAX_STRING_LENGTH+1,sizeof(char));
alphabetic=calloc(MAX_STRING_LENGTH+1,sizeof(char));
numeric=calloc(MAX_STRING_LENGTH+1,sizeof(char));
punctuation=calloc(MAX_STRING_LENGTH+1,sizeof(char));
whitespace=calloc(MAX_STRING_LENGTH+1,sizeof(char));

int counter=1;
while (fgets(line,MAX_STRING_LENGTH,in)!= NULL){
	extract_chars(line,alphabetic,numeric,punctuation,whitespace);
	
	print_line(line,alphabetic,numeric,punctuation,whitespace,counter,out);
	counter++;
}

//end condition
 free(line);
 free(alphabetic);
 free(punctuation); 
 free(whitespace); 
 free(numeric);
 fclose(in);
 fclose(out);
 return EXIT_SUCCESS;
}
  void extract_chars(char* s, char* a, char* d, char* p,char* w){
	extract_alpha(s,a);
	extract_digits(s,d);
	extract_punct(s,p);
	extract_space(s,w);
  }
 void print_line(char* s, char* a, char* d, char* p,char* w, int counter,FILE* out){
	 
	 fprintf(out,"%s%d%s","line ",counter," contains: \n");
	 
	 if (strlen(a)>1){
	 fprintf(out,"%ld%s%s\n",strlen(a)," alphabetic characters: ",a);
	 }else{ fprintf(out,"%ld%s%s\n",strlen(a)," alphabetic character: ",a);}
	 
	 if (strlen(d)>1){
	 fprintf(out,"%ld%s%s\n",strlen(d)," numeric characters: ",d);
	 }else{ fprintf(out,"%ld%s%s\n",strlen(d)," numeric character: ",d);}
	 
	 if (strlen(p)>1){
	 fprintf(out,"%ld%s%s\n",strlen(p)," punctuation characters: ",p);
	 }else{ fprintf(out,"%ld%s%s\n",strlen(p)," punctuation character: ",p);}
	 
	 if (strlen(w)>1){
	 fprintf(out,"%ld%s%s\n",strlen(w)," whitespace characters: ",w);
	 }else{ fprintf(out,"%ld%s%s\n",strlen(w)," whitespace character: ",w);}
 }
 void extract_alpha(char* s, char* a){
	int i=0;
	int j=0;
	while (s[i]!='\0' && i<MAX_STRING_LENGTH){
		if( isalpha(s[i])){
			a[j++]=s[i];
			i++;
		}
		else i++;
	}
	a[j]='\0';
 }
 void extract_digits(char* s, char* a){
	int i=0;
	int j=0;
	while (s[i]!='\0' && i<MAX_STRING_LENGTH){
		if( isdigit(s[i])){
			a[j++]=s[i];
			i++;
		}
		else i++;
	}
	a[j]='\0';
 }
 void extract_punct(char* s, char* a){
	int i=0;
	int j=0;
	while (s[i]!='\0' && i<MAX_STRING_LENGTH){
		if( ispunct(s[i])){
			a[j++]=s[i];
			i++;
		}
		else i++;
	}
	a[j]='\0';
 }
 void extract_space(char* s, char* a){
	int i=0;
	int j=0;
	while (s[i]!='\0' && i<MAX_STRING_LENGTH){
		if( isspace(s[i])){
			a[j++]=s[i];
			i++;
		}
		else i++;
	}
	a[j]='\0';
 }