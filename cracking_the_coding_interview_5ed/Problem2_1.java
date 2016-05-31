import demo.MyList;

class Problem2_1 {
  public static void main(String args[]) {
    String data[][] = {
      {
        "string",
        "string",
        "string",
        "string",
        "string",
        "string",
        "string2",
        "string2",
        "string2",
        "string2",
        "string",
        "string",
        "string"
      },
      {
        "string",
        "string",
        "string2",
        "string2"
      },
      {
        "string",
        "string",
        "string",
        "string",
        "string",
        "string"
      },
      {
        "string"
      },
      {
        "aaa",
        "bbb",
        "ccc",
        "ddd"
      },
      {
        "aaa",
        "ccc",
        "aaa",
        "aaa",
        "bbb",
        "ccc",
        "ccc",
        "ddd"
      },
    };

    MyList l;

    for(String s[] : data) {
      l = new MyList();
      for(String ss : s)
        l.appendTail(ss);
      System.out.println("initial list:");
      System.out.println(l);
      l.removeDuplicates();
      System.out.println("duplicates removed:");
      System.out.println(l);
    }
  }
}
