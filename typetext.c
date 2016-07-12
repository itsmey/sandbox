/* generate test text for typing practice */

#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <string.h>

#define NLINES 20
#define NCHARS 80
#define NGROUPS 4
#define CHANCES {50, 30, 18, 2}
#define CHARMAP {"     asdfghklwertyuiopcvbnmASDFGHKLWERTYUIOPCVBNM-=,.",\
                 "jqzxJQZX1234567890!*()_+;:'\"",\
                 "?@#$|\\[]{}?/<>%&",\
                 "|^`~"};

size_t get_buf_size() {
  const char *map[] = CHARMAP;
  const int chances[] = CHANCES;

  int i, j, n, l, size = 0;

  for(i = 0; i < NGROUPS; i++) {
    n = chances[i];
    l = strlen(map[i]);
    for(j = 0; j < chances[i]; j++) {
      size += l;
    }
  }

  return size;
}

void fill_buf(const int *chances, const char **map, char *curr) {
  int i, j, n;

  for(i = 0; i < NGROUPS; i++) {
    n = chances[i];
    for(j = 0; j < n; j++) {
      strcat(curr, map[i]);
    }
  }
}

int main() {
  const char *map[] = CHARMAP;
  const int chances[] = CHANCES;

  FILE *f;
  int i, j;

  size_t bsize = get_buf_size();
  char *buf = malloc(bsize + 1);
  memset(buf, 0, bsize + 1);

  fill_buf(chances, map, buf);
  srand(time(NULL));

  f = fopen("typet", "w");

    for(i = 0; i < NLINES; i++) {
      for(j = 0; j < NCHARS; j++) {
        char c;
        int r = rand() % bsize;
        putc(buf[r], f);
      }
      putc('\n', f);
    }

  fclose(f);
  free(buf);

  return 0;
}
