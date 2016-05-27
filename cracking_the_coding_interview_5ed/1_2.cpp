#include <iostream>
#include <cstring>
#include <string>

using namespace std;

void reverse(char *str) {
  char acc;
  int l = strlen(str);

  acc = str[l-1];
  for(int i = l - 2; i >= 0; i--)
    str[i+1] = str[i];

  str[0] = acc;
}

int main()
{
  string *str = new string("abcdefghijklmnopqrstuvwxyz");
  const int count = str->length() + 1;
  char *cstr = (char*)str->c_str();

  for(int i = 0; i < count; i++) {
    cout << *str << endl;
    reverse(cstr);
    delete str;
    str = new string(cstr);
  }

  delete str;

  return 0;
}
