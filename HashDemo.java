class Entry {
  private String key;
  private String value;
  private int hash;
  private Entry next;

  Entry getNext() {
    return next;
  }

  void setNext(Entry e) {
    next = e;
  }

  String getKey() {
    return key;
  }

  String getValue() {
    return value;
  }

  void setValue(String v) {
    value = v;
  }

  public String toString() {
    return String.format("entry: hash %d key %s, value %s\n", hash, key, value);
  }

  Entry(String k, String v, int h, Entry n) {
    key = k;
    value = v;
    hash = h;
    next = n;
  }
}

class HashTable {
  private int capacity;
  private Entry table[];

  private static int hash(String str) {
    return str.hashCode();
  }

  private void trace(String format, Object... args) {
    System.out.printf(format, args);
  }

  private void trace(String str) {
    System.out.print(str);
  }

  private Entry getEntry(String key) {
    int hash = hash(key);
    int index = hash & (capacity - 1);
    Entry e = table[index];
    while (e != null) {
      if (e.getKey().equals(key) == true)
        return e;
      e = e.getNext();
    }
    return null;
  }

  void put(String key, String value) {
    Entry e = getEntry(key);
    if (e != null) {
      e.setValue(value);
      return;
    }
    else {
      int hash = hash(key);
      int index = hash & (capacity - 1);
      Entry current = table[index];
      table[index] = new Entry(key, value, hash, current);
    }
  }

  String get(String key) {
    Entry e = getEntry(key);
    if (e != null)
      return e.getValue();
    else
      return null;
  }

  void remove(String key) {
    int hash = hash(key);
    int index = hash & (capacity - 1);
    Entry e = table[index];
    Entry previous = null;
    while (e != null) {
      if (e.getKey().equals(key) == true) {
        if (previous == null)
          table[index] = e.getNext();
        else
          previous.setNext(e.getNext());
        return;
      }
      previous = e;
      e = e.getNext();
    }
  }

  void show() {
    for (int i = 0; i < capacity; i++) {
      trace("%4d : ", i);
      if (table[i] == null)
        trace("null\n");
      else {
        Entry e = table[i];
        trace(e.toString());
        while ((e = e.getNext()) != null)
          trace("       " + e.toString());
      }
    }
    trace("\n");
  }

  HashTable(int capacity) {
    this.capacity = capacity;
    table = new Entry[capacity];
    java.util.Arrays.fill(table, null);
  }
}

class HashDemo {
  public static void main(String args[]) {
    HashTable ht = new HashTable(8);  /* choose 4, 8, 16, 32 etc...*/
    System.out.println("Empty hash table:");
    ht.show();
    System.out.println("Fill with key-value pairs:");
    ht.put("alpha", "aaa");
    ht.put("beta", "bbb");
    ht.put("gamma", "ggg");
    ht.put("delta", "ddd");
    ht.put("epsilon", "eee");
    ht.show();
    System.out.println("Overwrite epsilon value:");
    ht.put("epsilon", "new_eee");
    ht.show();
    System.out.println("Get existing values:");
    System.out.println(ht.get("alpha"));
    System.out.println(ht.get("gamma"));
    System.out.println("Get nonexistent value:");
    System.out.println(ht.get("omega"));
    System.out.println("Remove gamma:");
    ht.remove("gamma");
    ht.show();
    System.out.println("More complicated test. Add new entries:");
    for(char i = 'a'; i <= 'z'; i++) {
      String str = new String();
      str += i;
      ht.put(str, str);
    }
    ht.show();
    System.out.println("Delete this entries:");
      for(char i = 'a'; i <= 'z'; i++)  {
      String str = new String();
      str += i;
      ht.remove(str);
    }
    ht.show();
    System.out.println("Delete other entries:");
    ht.remove("alpha");
    ht.remove("beta");
    ht.remove("gamma");
    ht.remove("delta");
    ht.remove("epsilon");
    ht.show();
  }
}
