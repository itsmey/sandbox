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
    if (j - i + 1 <  Bitwise.bitSize(M)) {
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

  Problem5_1(int N, int M, int i, int j) {
    super("Problem 5.1");

    this.N = N;
    this.M = M;
    this.i = i;
    this.j = j;

    fullCycle();
  }
}

class Problem5_2 extends AbstractSolver {
  private double A;
  private int B = 0;   /*binary representation of A*/

  public void printInput() {
    System.out.println("A = " + A);
  }

  public void solve() {

    if (A <= 0 || A >= 1) {
      System.out.println("A must be between 0 and 1.");
      return;
    }

    int exponent = getExponent(A);
    int mantissa = getMantissa(A);

    System.out.println("Exponent: ");
    Bitwise.printBits(exponent);

    exponent += 127;

    System.out.println("Normalized exponent: ");
    Bitwise.printBits(exponent);

    if (Bitwise.bitSize(exponent) > 8) {
      System.out.println("Too big exponent.");
      return;
    }

    System.out.println("Mantissa: ");
    Bitwise.printBits(mantissa);

    if (Bitwise.bitSize(mantissa) > 24) {
      System.out.println("Too big mantissa.");
      return;
    }

    B = constructFloat(B, exponent, mantissa);
  }

  public void printOutput() {
    System.out.println("Binary representation of A:");
    Bitwise.printBits(B);
  }

  Problem5_2(double A) {
    super("Problem 5.2");

    this.A = A;

    fullCycle();
  }

  private static int getExponent(double number) {
    int exponent = -1;

    while ( (number *= 2) < 1 )
      exponent--;

    return exponent;
  }

  static class MRes {
    int power;
    int mantissa;
    MRes(int p, int m) {power = p; mantissa = m;}
  }

  private static int getMantissa(double number) {

    return getMantissa(23, number).mantissa;
  }

  private static MRes getMantissa(double count, double number) {

    if (number == 0.5)
      return new MRes(1, 0);

    if (count == 0)
      return new MRes(1, 0);

    number *= 2;
    int digit = (number >= 1) ? 1 : 0;
    number =  number - (long)number;
    MRes prev = getMantissa(count - 1, number);

    return new MRes(prev.power * 2, prev.mantissa + prev.power * digit);
  }

  private static int constructFloat(int num, int exponent, int mantissa) {
    /* construct 32-bit representation
        31-st bit is a sign (0)
        23..30 bits is exponent + 127
        0..22 bits is mantissa without highest 1
    */

    for(int i = 0; i < 8; i++)
      num = Bitwise.updateBit(num, i + 23, Bitwise.getBit(exponent, i));

    int bsize = Bitwise.bitSize(mantissa);
    int start_pos = 22 - (bsize - 2);

    for(int i = 0; i < bsize - 1; i++)
      num = Bitwise.updateBit(num, start_pos + i, Bitwise.getBit(mantissa, i));

    return num;
  }
}

class Problem5_3 extends AbstractSolver {
  private int N, greater, lower;
  private boolean is_greater = true, is_lower = true;

  public void printInput() {
    System.out.print("N = ");
    Bitwise.printBits(N);
  }

  public void solve() {
    if (N <= 0) {
      System.out.println("N must be positive");
      return;
    }

    int n = nOnes(N);
    int max_int = ~(1 << 31);
    int min_int = 1;
    greater = N;
    lower = N;

    if (greater == max_int)
      is_greater = false;
    else {
      while (nOnes(++greater) != n) {
        if (greater == max_int) {
          is_greater = false;
          break;
        }
      }
    }

    if (lower == min_int)
      is_lower = false;
    else {
      while (nOnes(--lower) != n) {
        if (lower == min_int) {
          is_lower = false;
          break;
        }
      }
    }

  }

  public void printOutput() {

    if (is_greater == true) {
      System.out.println("nearest greater with same number of 1s = ");
      Bitwise.printBits(greater);
    }
    if (is_lower == true) {
      System.out.println("nearest lower with same number of 1s = ");
      Bitwise.printBits(lower);
    }
  }

  private int nOnes(int number) {
    int n = 0;

    while (number != 0) {
      if (number % 2 == 1)
        n++;
      number /= 2;
    }

    return n;
  }

  Problem5_3(int N) {
    super("Problem 5.3");

    this.N = N;

    fullCycle();
  }
}

class Problem5_6 extends AbstractSolver {
  private int N, swapped;

  public void printInput() {
    System.out.print("N = ");
    Bitwise.printBits(N);
  }

  public void solve() {
    int mask = 0;
    for(int i = 0; i < 16; i++)
      mask = (mask << 2) + 1;

    int evens = N & mask;
    int odds = N & (mask << 1);

    swapped = (evens << 1) | (odds >> 1);
  }

  public void printOutput() {
    System.out.println("N and swapped N");
    Bitwise.printBits(N);
    Bitwise.printBits(swapped);
  }

  Problem5_6(int N) {
    super("Problem 5.6");

    this.N = N;

    fullCycle();
  }
}

class Problem5 {
  public static void main(String args[]) {
    new Problem5_1(54321, 12345, 4, 17);
    new Problem5_2(0.72);
    new Problem5_3(54321);
    new Problem5_6(987654321);
  }
}
