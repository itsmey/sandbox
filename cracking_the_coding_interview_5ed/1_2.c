#include <stdio.h>
#include <string.h>

void reverse(char *str);

int main(int argc, char *argv[])
{
    char alphabet[] = "abcdefthijklmnopqrstuvwxyz";
    size_t alph_len = strlen(alphabet);

    printf("%s\n", alphabet);

    reverse(alphabet);

    printf("%s\n", alphabet);

    return 0;
}


void reverse(char *str)
{
    size_t len = strlen(str);
    char *lpos = str;
    char *rpos = str + len - 1;

    while(lpos < rpos)
    {
        char tmp = *lpos;
        *lpos = *rpos;
        *rpos = tmp;

        lpos++;
        rpos--;
    }
}
