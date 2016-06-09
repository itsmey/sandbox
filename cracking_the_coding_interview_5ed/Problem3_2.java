import java.util.Random;

import demo.MyStack;
import demo.MyStackFullException;
import demo.MyStackEmptyException;

class StackWithMin {
  MyStack stack;
  MyStack min_stack;

  String getMin() {
    try {
      return min_stack.peek();
    }
    catch (MyStackEmptyException e) {
      return null;
    }
  }

  void push(String value) throws MyStackFullException {
    stack.push(value);

    String min = getMin();

    if (min == null)
      min_stack.push(value);
    else {
      int cmp = value.compareTo(min);
      if (cmp < 0)
        min_stack.push(value);
    }
  }

  String pop() throws MyStackEmptyException {
    String value = stack.pop();

    if (getMin() == value)
      min_stack.pop();

    return value;
  }

  public String toString() {
    String min_str = getMin();
    if (min_str == null)
      min_str = "not extsts";

    return String.format("%s  minimal element: %s", stack.toString(), min_str);
  }

  StackWithMin(int size) {
    stack = new MyStack(size);
    min_stack = new MyStack(size);
  }
}

class Problem3_2 {
  private final static int SIZE = 9;

  public static void main(String args[]) {
    Random rnd = new Random();

    StackWithMin swm = new StackWithMin(SIZE);

    System.out.println(swm);

    String value;

    for(int i = 0; i < SIZE; i++) {
      value = Integer.toString(rnd.nextInt(SIZE) + 1);
      try {
        System.out.println("\nTrying to push " + value);
        swm.push(value);
        System.out.println(swm);
      }
      catch (MyStackFullException e) {
        System.out.println(e);
      }
    }

    for(int i = 0; i < SIZE; i++) {
      try {
        System.out.println("\nTrying to pop " + swm.pop());
        System.out.println(swm);
      }
      catch (MyStackEmptyException e) {
        System.out.println(e);
      }
    }
  }
}
