import demo.MyList;

class Problem2_7 {
  public static void main(String args[]) {
    String data[][] = {
      {
        "a",
        "b",
        "a",
        "c",
        "a",
        "b"
      },
      {
        "a",
        "b",
        "a",
        "c",
        "a",
        "b",
        "a"
      },
      {
        "s",
        "t",
        "t",
        "t"
      },
      {
        "a",
        "b",
        "c",
        "c",
        "b",
        "a"
      },
      {
        "a"
      },
      {
        "a",
        "a",
        "a",
        "a"
      },
      {
        "q",
        "w",
        "q",
        "w",
        "q",
        "w",
        "q",
        "w"
      },
    };

    MyList l;

    for(String s[] : data) {
      l = new MyList();
      for(String ss : s)
        l.appendTail(ss);
      System.out.println("list:");
      System.out.println(l);
      if (l.isPalindrome() == true)
        System.out.println("---> is a palindrome\n");
      else
        System.out.println("---> is not a palindrome\n");
    }
  }
}
