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

  private Node findPrev(Node n) throws IllegalArgumentException {
    if (n == null)
      throw new IllegalArgumentException("No node given");

    if (head == n)
      return null;

    Node prev = head;
    while (prev.next != n) {
      prev = prev.next;
      if (prev == null)
        throw new IllegalArgumentException("Node is not in the list");
    }

    return prev;
  }

  private void swapNodes(Node n1, Node n2) throws IllegalArgumentException {
    if (n1.next != n2)
      throw new IllegalArgumentException("Not adjacent nodes");

    Node prev;

    try {
      prev = findPrev(n1);
    }
    catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e);
    }

    swapNodes(prev, n1, n2);
  }

  private void swapNodes(Node prev, Node n1, Node n2) {
    if (prev == null)
      head = n2;
    else
      prev.next = n2;

    n1.next = n2.next;
    n2.next = n1;
  }

  public void sort() throws IllegalArgumentException {
    if (isEmpty() == true)
      throw new IllegalArgumentException("Cannot remove from an empty list.");

    Node n;
    for(int i = 1; i < length(); i++) {
      n = head;
      while (n.next != null) {
        if (n.data.compareTo(n.next.data) > 0) {
          swapNodes(n, n.next);
        }
        else {
          n = n.next;
        }
      }
    }

  }

  public boolean equals(MyList l) {
    if (this == l)
      return true;

    Node n = head;
    Node nl = l.head;

    while (n != null || nl != null) {

      if (n != null && nl != null) {
        if (n.data == nl.data) {
          n = n.next;
          nl = nl.next;
        }
        else
          return false;
      }
      else
        return false;
    }

    return true;
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

  //// Solutions ////
  public void removeDuplicates() throws IllegalArgumentException {
    if (isEmpty() == true)
      throw new IllegalArgumentException("Cannot operate with empty list.");

    Node n = head;
    while (n != null) {
      removeDuplicates(n);
      n = n.next;
    }
  }

  private void removeDuplicates(Node n) {
    Node next = n.next;
    Node prev = n;
    while (next != null) {
      if (next.data == n.data) {
        prev.next = next.next; /* remove duplicate node */
      } else
        prev = next;
      next = next.next;
    }
  }

  public MyList reverse() {
    return reverse(new MyList());
  }

  private MyList reverse(MyList acc) {
    if (isEmpty() == true)
      return acc;

    acc.appendHead(head());

    return tail().reverse(acc);
  }

  public boolean isPalindrome() {
    return equals(reverse());
  }
}
