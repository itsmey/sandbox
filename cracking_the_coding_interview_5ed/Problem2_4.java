import demo.MyList;

class Problem2_4 {

  public static void main(String args[]) {
    String data[][][] = {
      {{"a", "s", "s", "z", "k", "v", "o", "p", "b", "b", "o"}, {"o"}},
      {{"a", "s", "s", "z", "k", "v", "p", "b", "b"}, {"o"}},
      {{"a"}, {"a"}},
      {{"a", "a", "a"}, {"a"}},
      {{"agsj", "ahtrhvds", "ahth", "fsdfsd", "323re"}, {"ahth"}}
    };

    MyList l = null;
    String pivot = "";

    for(String s[][] : data) {
      l = new MyList(s[0]);
      pivot = s[1][0];
      System.out.println("unsorted:");
      System.out.println(l);

      try {
        l.pivotSort(pivot);

        System.out.println("sorted arount " + pivot + ":");
        System.out.println(l);
      }
      catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
