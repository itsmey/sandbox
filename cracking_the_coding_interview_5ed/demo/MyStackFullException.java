package demo;

public class MyStackFullException extends MyStackException {
  public MyStackFullException(int max_size) {
    super("Stack is full (" + Integer.toString(max_size) + " elements)!");
  }
}
