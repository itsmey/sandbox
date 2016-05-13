/* ------------------------------------------------------------------ */
/* Trivial time-line example                                          */
/*   compilation tip:                                                 */
/*   gcc -ansi -Wall -Werror timeline.c -o timeline                   */
/* ------------------------------------------------------------------ */
#include "stdio.h"
#include "stdlib.h"
#include "string.h"

/* ------------------------- Definitions ---------------------------- */

#define MAX_MARKS 10
#define MAX_LEN 20

typedef struct timeline_mark {
  short year;
  short month;
  short day;
  int used;  /* 0 or 1 */
  char comment[MAX_LEN + 1];
} timeline_mark_t;

typedef struct timeline {
  short begin_year;
  short end_year;
  timeline_mark_t marks[MAX_MARKS];
} timeline_t;

void tl_init(timeline_t* tl, short begin_y, short end_y);
int tl_add_mark(timeline_t* tl, short year, short month, short day,
                const char* comment);
void tl_print(timeline_t* tl);
void tl_fill_intervals(timeline_t* tl, int *intervals);
int tl_next_mark(timeline_t* tl, int current_mark);

int mark_validate(timeline_mark_t mark);
int mark_init(timeline_mark_t *mark, short year, short month, short day,
              const char* comment);
int mark_is_equal(timeline_mark_t mark1, timeline_mark_t mark2);
int mark_copy(timeline_mark_t src, timeline_mark_t *dest);
void mark_print(timeline_mark_t mark);
int mark_get_days(timeline_mark_t mark);

int valid_date(int day, int month, int year);
int is_leap(int year);

/* ------------------------------------------------------------------ */

int main() {
  timeline_t *tl = malloc(sizeof(timeline_t));

  tl_init(tl, 1985, 2015);

  tl_add_mark(tl, 1988, 3, 27, "Vasya was born.");
  tl_add_mark(tl, 1996, 9, 1, "Vasya goes to school.");
  tl_add_mark(tl, 2005, 9, 1, "Vasya goes to university.");
  tl_add_mark(tl, 2009, 2, 10, "Vasya gets a job.");
  tl_add_mark(tl, 2013, 7, 16, "Vasya marries Masha.");

  tl_print(tl);

  free(tl);

  return 0;
}

void tl_init(timeline_t* tl, short begin_y, short end_y) {
  int i;

  tl->begin_year = begin_y;
  tl->end_year = end_y;

  for (i = 0; i < MAX_MARKS; i++)
    mark_init(&tl->marks[i], 0, 0, 0, "empty");
}

int tl_add_mark(timeline_t* tl, short year, short month, short day,
                const char* comment) {
  int i;
  timeline_mark_t mark;

  mark_init(&mark, year, month, day, comment);

  if (!mark_validate(mark))
    return -1;

  for (i = 0; i < MAX_MARKS; i++) {
    if (!(tl->marks[i].used)) {
      if (!(mark_is_equal(mark, tl->marks[i]))) {
        mark_copy(mark, &tl->marks[i]);
        tl->marks[i].used = 1;
        return 0;
      }
    }
  }

  return -1;
}

void tl_print(timeline_t* tl) {
  int i, j;
  int intervals[MAX_MARKS];

  tl_fill_intervals(tl, intervals);

  printf("%d\n * \n", tl->begin_year);
  for (i = 0; i < MAX_MARKS; i++) {
    if (tl->marks[i].used) {
      mark_print(tl->marks[i]);
      for (j = 1; j < intervals[i]; j++) {
        printf(" |\n");
      }
    }
  }
  printf(" *\n%d\n", tl->end_year);
}

void tl_fill_intervals(timeline_t* tl, int *intervals) {
  int i, next;

  for (i = 0; i < MAX_MARKS - 1; i++)
    intervals[i] = 0;

  for (i = 0; i < MAX_MARKS - 1; i++) {
    if (tl->marks[i].used) {
      next = tl_next_mark(tl, i);
      if (next) {
        intervals[i] =
          (mark_get_days(tl->marks[next]) -
           mark_get_days(tl->marks[i])) / 365;
      }
    }
  }

  intervals[MAX_MARKS - 1] = 0;
}

int tl_next_mark(timeline_t* tl, int current_mark) {
  int i;

  for (i = current_mark + 1; i < MAX_MARKS; i++)
    if (tl->marks[i].used) {
      return i;
    }

  return 0;
}

int mark_init(timeline_mark_t *mark, short year, short month, short day,
              const char* comment) {
  unsigned short len;

  mark->year = year;
  mark->month = month;
  mark->day = day;
  mark->used = 0;

  memset(mark->comment, '\0', MAX_LEN + 1);

  len = strlen(comment);

  if (len > MAX_LEN)
    memcpy(mark->comment, comment, MAX_LEN);
  else
    memcpy(mark->comment, comment, len);

  return 0;
}

int mark_validate(timeline_mark_t mark) {
  return valid_date(mark.day, mark.month, mark.year);
}

int mark_is_equal(timeline_mark_t mark1,timeline_mark_t mark2) {
  return (mark1.year == mark2.year) &&
         (mark1.month == mark2.month) &&
         (mark1.day == mark2.day);
}

int mark_copy(timeline_mark_t src, timeline_mark_t *dest) {
  dest->year = src.year;
  dest->month = src.month;
  dest->day = src.day;
  dest->used = src.used;

  strcpy(dest->comment, src.comment);

  return 0;
}

void mark_print(timeline_mark_t mark) {
  printf(" | %d %02d %02d : %s\n", mark.year, mark.month, mark.day, mark.comment);
}

int mark_get_days(timeline_mark_t mark) {
  return mark.year * 365 + mark.month * 30 + mark.day;
}

int is_leap(int year) {
  return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
}

int valid_date(int day, int month, int year) {
  int days = 31;

  if (month < 1 || month > 12)
    return 0;

  if (day < 1)
    return 0;

  if (month == 2) {
    days = 28;
    if (is_leap(year)) {
        days = 29;
    }
  } else
  if (month == 4 || month == 6 || month == 9 || month == 11) {
    days = 30;
  }

  if (day > days)
    return 0;

  return 1;
}
