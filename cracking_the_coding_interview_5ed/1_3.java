import java.util.Arrays;

class Permut {
  private static String sort(String str) {
    char chars[] = str.toCharArray();
    Arrays.sort(chars);
    String sorted = new String(chars);
    return sorted;
  }

  private static boolean isPermut(String str1, String str2) {
    return (sort(str1).equals(sort(str2)));
  }

  static void show(String str1, String str2) {
    if (isPermut(str1, str2) == true)
      System.out.println(str1 + " is permutation of " + str2);
    else
      System.out.println(str1 + " is not permutation of " + str2);
  }
}

public class PermutDemo {
  public static void main(String[] args) {
    String data[][] = {
      {"asdf", "fdsa"},
      {"", "gh4c5gc4g5"},
      {"", ""},
      {"a", "f"},
      {"a", "a"},
      {"ab", "ba"},
      {"manana", "ananam"},
      {"q q w e r t y", "q q w e r t t"},
      {"q q w e r t y", "q  w tey q  r"}
    };
    for(String pair[] : data)
      Permut.show(pair[0], pair[1]);
  }
}
