#include <stdio.h>

void main(int argc, char* argv[]){
int *A=calloc(4,sizeof(int));
int B[]={13,52,24,35};
printf("%d\n",*A);
printf("%d\n",A);
printf("%d\n",*B);
printf("%d\n",B);
printf("%d\n",B[1]);

}

