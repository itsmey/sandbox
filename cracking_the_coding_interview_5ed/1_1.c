#include "stdio.h"
#include "string.h"

int isUnique(const char *str) {
  int i, j, l = strlen(str);
  char s;

  for (i = 0; i < l; i++) {
    s = str[i];
    for(j = i + 1; j < l; j++) {
      if (str[j] == s)
        return 0;
    }
  }

  return 1;
}

void isUniqueWrapper(const char *str) {

  if (!str) {
    printf("Null pointer!\n");
    return;
  }

  if (isUnique(str))
    printf("%s - unique\n", str);
  else
    printf("%s - not unique\n", str);
}

int main() {
   const char *data[] = {
    "String",
    "StringString",
    "fl,vjra;cgragra",
    0,
    0,
    "asdfghjkl;qwertyuiop",
    "abracadabra xaxax",
  };
   int i;

   for(i = 0; i < sizeof(data) / sizeof(const char *); i++)
     isUniqueWrapper(data[i]);
}
