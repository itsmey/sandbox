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

public class MyList {
  private Node head;

  private Node lastNode() throws IllegalArgumentException {
    if (isEmpty() == true)
      throw new IllegalArgumentException("Cannot get last element of empty list.");

    Node n = head;
    while(n.next != null) {
      n = n.next;
    }
    return n;
  }

  public String last() throws IllegalArgumentException {
    return lastNode().data;
  }

  public boolean isEmpty() {
    return (head == null);
  }

  public int length() {
    if (isEmpty() == true)
      return 0;

    int count = 0;
    Node n = head;
    do {
      count++;
      n = n.next;
    } while(n != null);

    return count;
  }

  public String head() throws IllegalArgumentException {
    if (isEmpty() == true)
      throw new IllegalArgumentException("Cannot get a head of empty list.");

    return head.data;
  }

  public MyList tail() throws IllegalArgumentException {
    if (isEmpty() == true)
      throw new IllegalArgumentException("Cannot get a tail of empty list.");

    Node n = head.next;
    if (n == null)
      return new MyList();
    else
      return new MyList(n);
  }

  public String nth(int pos) throws IllegalArgumentException {
    if (pos <= 0 || pos > length())
      throw new IllegalArgumentException("Invalid position.");

    int curr_pos = 1;
    Node n = head;
    do {
      if (pos == curr_pos++)
        return n.data;
      n = n.next;
    } while(n != null);
    return "";  //unreachable
  }

  public void appendHead(String data) {
    if (isEmpty() == true) {
      head = new Node(data);
      return;
    }

    head = new Node(data, head);
  }

  public void appendTail(String data) {
    if (isEmpty() == true) {
      head = new Node(data);
      return;
    }

    Node n = lastNode();
    n.next = new Node(data);
  }

  public void insert(int pos, String data) throws IllegalArgumentException {
    if ((pos <= 0) || (pos > length() + 1))
      throw new IllegalArgumentException("Invalid position.");

    if (pos == 1) {
      appendHead(data);
      return;
    }

    if (pos == length() + 1) {
      appendTail(data);
      return;
    }

    int p = 2;
    Node n = head;
    while(p <= pos) {
      if (p == pos) {
        n.next = new Node(data, n.next);
        return;
      }
      p++;
      n = n.next;
    }
  }

  public void remove(String data) throws IllegalArgumentException {
    if (isEmpty() == true)
      throw new IllegalArgumentException("Cannot remove from an empty list.");

    if (head.data == data) {
      head = head.next;
      return;
    }

    Node prev = head;
    Node n = head.next;
    while(n != null) {
      if (n.data == data) {
        prev.next = n.next;
        return;
      }
      prev = n;
      n = n.next;
    }
  }

  public String toString() {
    if (isEmpty() == true) {
      return "Empty list!\n";
    }

    StringBuffer result = new StringBuffer();
    Node n = head;
    int pos = 1;
    while(n != null) {
      result.append(pos++ + ": \"" + n.data + "\"\n");
      n = n.next;
    }

    return result.toString();
  }

  // empty list
  public MyList() {
    head = null;
  }

  // list with single node
  public MyList(String head_data) {
    head = new Node(head_data);
  }

  // from existing list
  public MyList(Node head) throws IllegalArgumentException  {
    if (head == null)
      throw new IllegalArgumentException("Head must not be null.");

    this.head = head;
  }
}
