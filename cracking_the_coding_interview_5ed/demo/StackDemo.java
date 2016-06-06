package demo;

class StackDemo {
  public static void main(String args[]) {
    MyStack stack = new MyStack(5);

    System.out.println(stack);

    String str;
    for(int i = 'a'; i < 'g'; i++) {
      str = new String();
      str += (char)i;
      try {
        stack.push(str);
        System.out.println("added: " + str);
      }
      catch (MyStackFullException e) {
        System.out.println(e);
      }
    }

    System.out.println(stack);

    try {
      System.out.println("at the top: " + stack.peek());
    }
    catch (MyStackEmptyException e) {
      System.out.println("This shouldn't be printed.");
    }

    try {
      while(true) {
        System.out.println("extracted: " + stack.pop());
      }
    }
    catch (MyStackEmptyException e) {
      System.out.println(e);
    }

    System.out.println(stack);

  }
}
