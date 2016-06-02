import demo.MyList;

class Problem2_2 {
  public static String nthTail(MyList l, int n) throws IllegalArgumentException {
    if (l == null)
      throw new IllegalArgumentException("Null list.");

    if (l.isEmpty() == true)
      throw new IllegalArgumentException("Empty list.");

    int new_n = l.length() - n + 1;

    try {
      return l.nth(new_n);
    }
    catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid position: " + n);
    }
  }

  public static void main(String args[]) {
    String data[] = {
        "first",
        "second",
        "third",
        "fourth",
        "fifth",
        "sixth",
        "seventh",
        "eighth",
        "nineth",
        "tenth"
    };

    MyList l = new MyList();

    for(String s : data)
        l.appendTail(s);

    System.out.println("list:");
    System.out.println(l);

    for(int i = 0; i <= l.length() + 1; i++) {
      try {
        System.out.println(i + "th tail: " + nthTail(l, i));
      }
      catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
