#include <stdio.h>
 
int main()
{
    char str[100]="souhir", rev[100];
    int t, i, j;
 
    //printf(" Entrez une cha�ne de caract�re :  ");
   
    
    j = 0;
    t = strlen(str);
 
    rev[t] = '\0'; //le dernier caract�re doit toujours �tre �gale � '\0'.
    for (i = t - 1; i >= 0; i--)
    {
      rev[j++] = str[i];
    }
    rev[i] = '\0';
 
    printf(" Cha�ne de caract�re apr�s inversion = %s", rev);
    
    return 0;
}