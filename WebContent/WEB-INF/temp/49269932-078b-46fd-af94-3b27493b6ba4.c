#include <stdio.h>
 
int main()
{
    char str[100]="souhir", rev[100];
    int t, i, j;
 
    //printf(" Entrez une chaîne de caractère :  ");
   
    
    j = 0;
    t = strlen(str);
 
    rev[t] = '\0'; //le dernier caractère doit toujours être égale à '\0'.
    for (i = t - 1; i >= 0; i--)
    {
      rev[j++] = str[i];
    }
    rev[i] = '\0';
 
    printf(" Chaîne de caractère après inversion = %s", rev);
    
    return 0;
}