package demo;

class Node {
  String data;
  Node next;

  Node(String data) {
    this.data = data;
    next = null;
  }

  Node(String data, Node next) {
    this.data = data;
    this.next = next;
  }
}

public class MyStack {
  private int max_size;
  private int size;
  private Node head;

  private boolean isEmpty() {
    return size == 0;
  }

  public int getSize() {
    return size;
  }

  public void push(String data) throws MyStackFullException {
    if (size >= max_size)
      throw new MyStackFullException(size);

    head = new Node(data, head);
    size++;
  }

  public String pop() throws MyStackEmptyException {
    if (isEmpty() == true)
      throw new MyStackEmptyException();

    String d = head.data;

    head = head.next;
    size--;

    return d;
  }

  public String peek() throws MyStackEmptyException {
    if (isEmpty() == true)
      throw new MyStackEmptyException();

    return head.data;
  }

  public String toString() {
    StringBuffer contents = new StringBuffer();

    if (isEmpty() != true) {
      Node n = head;
      do {
        contents.append(n.data + "  ");
      } while ((n = n.next) != null);
      contents.append("\n");
    }

    return String.format("[Stack: %d elements, max %d elements]\n%s",
                              size,
                              max_size,
                              contents.toString());

  }

  public MyStack(int max_size) {
    this.max_size = max_size;
    this.size = 0;
  }
}
