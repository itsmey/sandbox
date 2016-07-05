class Problem7_7 {

  private static int[] newArray(int size) {
    int a[] = new int[size];

    for(int i = 0; i < size; i++)
      a[i] = 3;

    return a;
  }

  private static int printArray(int[] a) {
    int m = 1;

    for(int i = 0; i < a.length; i++) {
      System.out.print(a[i]);
      m *= a[i];
    }

    System.out.println(" = " + m);

    return m;
  }

  private static int[] incrementArray(int[] a) {

    for(int i = 0; i < a.length; i++) {
      if (a[i] == 3) {
        for(int j = 0; j < a.length; j++) {
          if (a[j] == 7) {
            a[i] = a[j] = 5;
            return a;
          }
        }
      }
    }

    for(int i = 0; i < a.length; i++) {
      if (a[i] == 5) {
        a[i] = 7;
        return a;
      }
    }
    for(int i = 0; i < a.length; i++) {
      if (a[i] == 3) {
        a[i] = 5;
        return a;
      }
    }
    return newArray(a.length + 1);
  }

  private static int findNumber(int k) {
    int a[] = newArray(1);
    int n = 1;

    while (n != k) {
      a = incrementArray(a);
      n++;
    }

    return printArray(a);
  }

  public static void main(String args[]) {
    for (int i = 1; i < 50; i++) {
      System.out.print(i + "th : ");
      findNumber(i);
    }
  }
}
