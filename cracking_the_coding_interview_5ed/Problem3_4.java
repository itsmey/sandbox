import java.util.Stack;

class WrongSizeException extends Exception {
  public WrongSizeException() {
    super("You are trying to put bigger disk to smaller one.");
  }
}

class Hanoi {
  private Stack<Integer> initialStack, bufferStack, targetStack;
  private int ndisks;
  private int turn = 0;

  private void solve() throws WrongSizeException {
    System.out.println("Hanoi Towers solver for " + ndisks + " disks");
    moveTower(ndisks, initialStack, bufferStack, targetStack);
    System.out.println("done in " + turn + " moves\n");

    turn = 0;
  }

  private void moveTower(int n, Stack<Integer> init,
                                Stack<Integer> buf,
                                Stack<Integer> target) throws WrongSizeException {
    if (n == 1) {
      moveDisk(init, target);
      return;
    }

    moveTower(n - 1, init, target, buf);
    moveDisk(init, target);
    moveTower(n - 1, buf, init, target);
  }

  private void moveDisk(Stack<Integer> init, Stack<Integer> target)
      throws WrongSizeException {
    int disk = init.pop();

    if (target.empty() == false && disk > target.peek())
      throw new WrongSizeException();

    turn++;
    target.push(disk);
    System.out.println("turn " + turn + ": move disk " +
      disk + " from " + stackStr(init) + " to " + stackStr(target));
  }

  private String stackStr(Stack<Integer> stack) {
    if (stack == initialStack)
      return "initial stack";

    if (stack == bufferStack)
      return "buffer stack";

    return "target stack";
  }

  Hanoi(int ndisks) throws WrongSizeException {
    if (ndisks < 1)
      throw new IllegalArgumentException("Number of disks must be positive");

    this.ndisks = ndisks;

    initialStack = new Stack<Integer>();
    bufferStack = new Stack<Integer>();
    targetStack = new Stack<Integer>();

    for(int i = ndisks; i > 0; i--)
      initialStack.push(i);

    solve();
  }
}

class Problem3_4 {
  public static void main(String args[]) throws WrongSizeException {
    for(int i = 1; i < 7; i++)
      new Hanoi(i);
  }
}
