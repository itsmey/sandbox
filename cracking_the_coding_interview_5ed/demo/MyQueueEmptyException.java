package demo;

class MyQueueException extends Exception {
  MyQueueException(String msg) {
    super(msg);
  }
}

public class MyQueueEmptyException extends MyQueueException {
  public MyQueueEmptyException() {
    super("Queue is empty!");
  }
}
