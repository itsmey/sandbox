#include "stdio.h"
#include "string.h"
#include "stdlib.h"

#define REP_SYMBOL ' '

int memoryNeeded(const char *str, int l) {
  int i, count = 0;;
  for(i = 0; i < strlen(str); i++)
    if (str[i] == REP_SYMBOL) count++;

  return (strlen(str) + count * l + 1) * sizeof(char);
}

int replaceBlanks(char *buf, const char *str, const char *rep) {
  int i, j, ls = strlen(str), lr = strlen(rep), bpos = 0;

  for(i = 0; i < ls; i++) {
    if (str[i] == REP_SYMBOL) {
      for(j = 0; j < lr; j++) {
        buf[bpos++] = rep[j];
      }
    } else {
      buf[bpos++] = str[i];
    }
  }
}

void replaceBlanksWrapper(const char *str, const char *rep) {
  char *buf;
  size_t m;

  if (!str || !rep) {
    printf("Null pointer!\n");
    return;
  }

  m = memoryNeeded(str, strlen(rep));

  buf = malloc(m);
  memset(buf, '\0', m);

  replaceBlanks(buf, str, rep);

  printf("initial string: %s\n", str);
  printf("after replace: %s\n\n", buf);

  free(buf);
}

int main() {
  const char *data[] = {
    "This is string.",
    "noblanks",
    "",
    " ",
    "   ",
    0,
    " fdfdsf dsfwf 43     8k43j f   8f   fdfdfdfd     "
  };
  const char *rep = "%20";
  int i;

  for(i = 0; i < sizeof(data) / sizeof(const char *); i++)
    replaceBlanksWrapper(data[i], rep);
}
