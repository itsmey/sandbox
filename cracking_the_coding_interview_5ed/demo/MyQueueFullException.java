package demo;

public class MyQueueFullException extends MyQueueException {
  public MyQueueFullException(int max_size) {
    super("Queue is full (" + Integer.toString(max_size) + " elements)!");
  }
}
