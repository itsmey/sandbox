import demo.MyList;

class Problem2_5 {

  private static MyList sum(MyList l1, MyList l2)
    throws IllegalArgumentException, NumberFormatException {
    if (l1 == null || l2 == null)
      throw new IllegalArgumentException("Null list.");

    if (l1.isEmpty() == true || l2.isEmpty() == true)
      throw new IllegalArgumentException("Empty list.");

    int n1 = listToInt(l1);
    int n2 = listToInt(l2);

    return intToList(n1 + n2);

  }

  private static int listToInt(MyList l)
    throws IllegalArgumentException, NumberFormatException {
    int power = 1;
    int len = l.length();
    int result = 0;
    int i;

    while (--len != 0)
      power *= 10;

    while (l.isEmpty() != true) {
      i = Integer.parseInt(l.head());
      if (i < 0 || i > 9)
        throw new IllegalArgumentException(
          "List must contain representation of digits.");
      result += power * i;
      power /= 10;
      l = l.tail();
    }

    return result;
  }

  private static MyList intToList(int n) throws IllegalArgumentException {
    if (n < 0)
      throw new IllegalArgumentException("Negative argument.");
    if (n == 0)
      return new MyList("0");

    MyList l = new MyList();
    int power = 1;
    int digit;

    while (n != 0) {
      digit = n % 10;
      n = (n - digit) / 10;
      l.appendHead(Integer.toString(digit));
    }

    return l;
  }

  public static void main(String args[]) {
    String data[][][] = {
        {{"1", "4", "7"}, {"6", "9", "5"}},
        {{"0"}, {"0"}},
        {{"5", "5"}, {"0"}},
        {{"rrrrrr"}, {"0"}},
        {{"1", "2", "3"}, {"3", "3"}},
        {{"1", "2", "3"}, {"33"}},
        {{"9", "9", "9"}, {"1"}},
        {{"8", "8", "4", "7", "8", "0"}, {"7", "2", "5", "6", "8", "4", "5"}}
    };

    MyList l1, l2;

    for (String s[][] : data) {
      l1 = new MyList(s[0]);
      l2 = new MyList(s[1]);
      System.out.print(l1);
      System.out.println("+");
      System.out.print(l2);
      System.out.println("=");

      try {
        System.out.println(sum(l1, l2));
      }
      catch (NumberFormatException e) {
        System.out.println(e);
      }
      catch (IllegalArgumentException e) {
        System.out.println(e);
      }
    }

  }
}
