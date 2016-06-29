import demo.Bitwise;

interface Solver {
  void printInput();
  void solve();
  void printOutput();
}

abstract class AbstractSolver implements Solver {
  String name;

  abstract public void printInput();
  abstract public void solve();
  abstract public void printOutput();

  void fullCycle() {
    System.out.println("* * * * * * * * * * * * * * *");
    System.out.println(name);

    System.out.println("input:");
    printInput();

    System.out.println("solving...");
    solve();

    System.out.println("output:");
    printOutput();
  }

  AbstractSolver(String name) {
    this.name = name;
  }
}

class Problem5_1 extends AbstractSolver {
  private int N;
  private int M;
  private int i;
  private int j;

  public void printInput() {
    System.out.print("N = ");
    Bitwise.printBits(N);
    System.out.print("M = ");
    Bitwise.printBits(M);
    System.out.println("i = " + i + ", j = " + j);
  }

  public void solve() {
    if (j - i + 1 <  bitSize(M)) {
      System.out.println("Can't solve. Not enough space to paste N.");
      return;
    }

    int bit, pos_M = 0;
    for(int k = i; k <= j; k++) {
      bit = Bitwise.getBit(M, pos_M);
      N = Bitwise.updateBit(N, k, bit);
      pos_M++;
    }
  }

  public void printOutput() {
    System.out.print("N = ");
    Bitwise.printBits(N);
  }

  private static int bitSize(int number) {
    if  (number == 0) return 1;

    int power = 1, size = 0;

    while (number >= power) {
      power *= 2;
      size++;
    }

    return size;
  }

  Problem5_1(int N, int M, int i, int j) {
    super("Problem 5.1");

    this.N = N;
    this.M = M;
    this.i = i;
    this.j = j;

    fullCycle();
  }
}

class Problem5 {
  public static void main(String args[]) {
    new Problem5_1(54321, 12345, 4, 17);
  }
}
