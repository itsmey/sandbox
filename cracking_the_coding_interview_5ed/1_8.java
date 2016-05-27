class Shift {
  private static boolean isSubstring(String str, String substr) {
    return (str.indexOf(substr) != -1);
  }

  private static boolean isShift(String str1, String str2) {
    return (str1.length() == str2.length()) && isSubstring(str1 + str1, str2);
  }

  static void show(String str1, String str2) {
    if (isShift(str1, str2) == true)
      System.out.println(str1 + " is a cyclic shift of " + str2);
    else
      System.out.println(str1 + " is not a cyclic shift of " + str2);
  }
}

public class ShiftDemo {
  public static void main(String[] args) {
    String data[][] = {
      {"waterbottle", "erbottlewat"},
      {"erbottlewat", "waterbottle"},
      {"water", "rewat"},
      {"water", "retaw"},
      {"water", "terwa"},
      {"", ""},
      {"", " "},
      {"aaaaaaa", "aaa"},
      {"aaaabaa", "baaaaaa"},
    };
    for(String pair[] : data)
      Shift.show(pair[0], pair[1]);
  }
}
