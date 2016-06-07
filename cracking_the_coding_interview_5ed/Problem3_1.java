import demo.MyStackEmptyException;
import demo.MyStackFullException;

class StacksArray {
  private final int STACKS = 3;
  private String arr[];
  private int size;
  private int tops[];
  private int init_tops[];

  private int nextIdx(int idx) {
    if (idx >= size - 1)
      return 0;
    else
      return idx + 1;
  }

  private boolean canExtend(int idx) {
    boolean result = true;

    for(int i = 0; i < STACKS; i++)
      result = result && (nextIdx(idx) != init_tops[i]);

    return result;
  }

  public void push(int stack_no, String value)
    throws IllegalArgumentException, MyStackFullException {
    if (stack_no < 0 || stack_no > STACKS-1)
      throw new IllegalArgumentException("Incorrect stack number");

    int topIdx = tops[stack_no];

    if (arr[topIdx] == null) {   // empty stack
      arr[topIdx] = value;
      return;
    }

    int next = nextIdx(topIdx);

    if (canExtend(topIdx) == true) {
      arr[next] = value;
      tops[stack_no] = next;
    }
    else
      throw new MyStackFullException(tops[stack_no] - init_tops[stack_no] + 1);
  }

  public String pop(int stack_no)
    throws IllegalArgumentException, MyStackEmptyException {
    if (stack_no < 0 || stack_no > STACKS-1)
      throw new IllegalArgumentException("Incorrect stack number");

    int topIdx = tops[stack_no];

    if (arr[topIdx] == null) {   // empty stack
      throw new MyStackEmptyException();
    }

    String value = arr[topIdx];

    arr[topIdx] = null;

    if (topIdx != init_tops[stack_no])
      tops[stack_no] -= 1;

    return value;
  }

  StacksArray(int size) throws IllegalArgumentException {
    if (size < STACKS)
      throw new IllegalArgumentException("Too small size.");

    this.size = size;
    arr = new String[size];

    init_tops = new int[STACKS];
    tops = new int[STACKS];

    for(int i = 0; i < STACKS; i++) {
      init_tops[i] = size / STACKS * i;
      tops[i] = init_tops[i];
    }

    java.util.Arrays.fill(arr, null);
  }

  public String toString() {
    StringBuffer buf = new StringBuffer();

    buf.append("total size " + size + "\n");

    for(int i = 0; i < size; i++) {
      buf.append("element " + (i + 1) + ": ");
      for(int stack = 0; stack < STACKS; stack++) {
        if (i == init_tops[stack])
          buf.append("[init_top:" + (stack + 1) + "] ");
        if (i == tops[stack])
          buf.append("[top:" + (stack + 1) + "] ");
      }
      buf.append(arr[i] + "\n");
    }

    return buf.toString();
  }
}

class Problem3_1 {
  private static StacksArray sa;

  private static void trace() {
    System.out.println(sa);
  }

  private static boolean push(int stack_no, String value) {
    System.out.println("trying to push " + value + " into stack " + stack_no);
    try {
      sa.push(stack_no - 1, value);
      trace();
    }
    catch (Exception e) {
      System.out.println(e);
      return false;
    }
    return true;
  }

  private static boolean pop(int stack_no) {
    System.out.println("trying to pop from stack " + stack_no);
    try {
      System.out.println(sa.pop(stack_no - 1));
      trace();
    }
    catch (Exception e) {
      System.out.println(e);
      return false;
    }
    return true;
  }

  private static void stackTest(int n) {
    int i = 1;

    // push & pop
    push(n, "value");
    pop(n);

    // push untill full
    while (push(n, "test" + i++))
      ;

    // pop untill empty
    while (pop(n))
      ;
  }

  static public void main(String[] args) {
    sa = new StacksArray(10);

    System.out.println(sa);

    stackTest(1);
    stackTest(2);
    stackTest(3);
    stackTest(4);
  }
}
