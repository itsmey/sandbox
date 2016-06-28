package demo;

public class Bitwise {

  public static boolean getBit(int arg, int n) {
    int mask = 1 << n;
    return (arg & mask) != 0;
  }

  public static void printBits(int number) {
    System.out.print("DEC " + number + " | BIN ");
    char c;
    int indent_counter = 0;
    for (int i = 31; i >= 0; i--) {
      indent_counter++;
      c = getBit(number, i) ? '1' : '0';
      System.out.print(c);
      if (indent_counter % 8 == 0)
        System.out.print(' ');
    }
    System.out.print('\n');
  }

}
