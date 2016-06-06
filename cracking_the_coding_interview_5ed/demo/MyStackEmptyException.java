package demo;

class MyStackException extends Exception {
  MyStackException(String msg) {
    super(msg);
  }
}

public class MyStackEmptyException extends MyStackException {
  MyStackEmptyException() {
    super("Stack is empty!");
  }
}
