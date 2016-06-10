import demo.*;

class TwoStacksQueue {
  private MyStack enqueueStack, dequeueStack;
  private int max_size;

  private int getActualSize() {
    return enqueueStack.getSize() + dequeueStack.getSize();
  }

  private void shift() {
    try {
      while (true)
        dequeueStack.push(enqueueStack.pop());
    }
    catch (Exception e) {
      ;
    }
  }

  void enqueue(String value) throws MyQueueFullException {
    if (getActualSize() >= max_size)
      throw new MyQueueFullException(max_size);

    try {
      enqueueStack.push(value);
    }
    catch (MyStackFullException e) {
      throw new MyQueueFullException(max_size);
    }
  }

  String dequeue() throws MyQueueEmptyException {
    if (dequeueStack.getSize() == 0) {
      if (enqueueStack.getSize() == 0)
        throw new MyQueueEmptyException();
      else
        shift();
    }
    try {
      return dequeueStack.pop();
    }
    catch (MyStackEmptyException e) {
      throw new MyQueueEmptyException();
    }
  }

  TwoStacksQueue(int size) {
    max_size = size;
    enqueueStack = new MyStack(size);
    dequeueStack = new MyStack(size);
  }

}

class Problem3_5 {
  private static TwoStacksQueue tsq;
  private static final int SIZE = 10;

  private static void enqueue(String value) {
    System.out.println("Trying to enqueue " + value);
    try {
      tsq.enqueue(value);
    }
    catch (MyQueueFullException e) {
      System.out.println(e);
    }
  }

  private static void dequeue() {
    System.out.println("Trying to dequeue");
    try {
      System.out.println("dequeued: " + tsq.dequeue());
    }
    catch (MyQueueEmptyException e) {
      System.out.println(e);
    }
  }

  public static void main(String args[]) {
    tsq = new TwoStacksQueue(SIZE);

    // dumb tests
    dequeue();         // exception: empty
    enqueue("1");
    enqueue("2");
    enqueue("5");
    dequeue();
    enqueue("22");
    enqueue("55");
    enqueue("55");
    enqueue("56");
    dequeue();
    dequeue();
    enqueue("zzz");
    enqueue("zzzz");
    enqueue("zzzzz");
    enqueue("zzzzzz");
    dequeue();
    enqueue("123");
    enqueue("12");
    enqueue("1");
    enqueue("0");      // exception: full
    dequeue();
    dequeue();
    dequeue();
    dequeue();
    dequeue();
    dequeue();
    dequeue();
    dequeue();
    dequeue();
    dequeue();
    dequeue();        // exception: empty
  }
}
