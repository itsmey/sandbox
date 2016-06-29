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
    System.out.println(name);

    System.out.println("input:");
    printInput();

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
    System.out.println("N = ");
    Bitwise.printBits(N);
    System.out.println("M = ");
    Bitwise.printBits(M);
    System.out.println("i = " + i + ", j = " + j);
  }

  public void solve() {
    //
  }

  public void printOutput() {
    System.out.println("N = ");
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
    new Problem5_1(42, 14, 15, 20);
  }
}
