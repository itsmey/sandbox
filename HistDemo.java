class HistData {
  private double data[];
  private int size;

  private double GenerateRandom(double bound1, double bound2) {
    double min = bound1;
    double max = bound2;

    if (bound2 == bound1)
      return bound1;
    if (bound2 < bound1) {
      min = bound2;
      max = bound1;
    }

    return Math.random() * (max - min) + min;
  }

  private void InitData(double n) {
    for(int i = 0; i < size; i++)
      data[i] = n;
  }

  double GetElement(int n) {
    return data[n];
  }

  double GetSize() {
    return size;
  }

  void FillRandom(double bound1, double bound2) {
    for(int i = 0; i < size; i++)
      data[i] = GenerateRandom(bound1, bound2);
  }

  void PrintLine(int precision) {
    System.out.print("{ ");
    for(int i = 0; i < size; i++) {
      System.out.format("%." + precision + "f", data[i]);
      if (i != size - 1)
        System.out.print(" ");
    }
    System.out.println(" }");
  }

  HistData(int s) {
    size = s;
    data = new double[s];
    InitData(0);
  }
}

class Hist {
  private int size = 30;
  private double scale;
  private int interval, ncolumns;
  private int min, max;
  private int columns[];
  private HistData data;

  private int NElements(double low, double high) {
    int counter = 0;
    boolean condition;

    for (int i = 0; i < data.GetSize(); i++) {
      double elem = data.GetElement(i);
        if (low == min)
          condition = ((elem >= low) && (elem <= high));
        else
          condition = ((elem > low) && (elem <= high));
      if (condition)
        counter++;
    }

    return counter;
  }

  private double Fill() {
    int low = min, high = min + interval;
    int max_nelements = 0;

    for (int i = 0; i < ncolumns; i++) {
      columns[i] = NElements(low, high);

      // System.out.format("interval %d-%d: %d elements\n",
      //   low, high, columns[i]);

      if (columns[i] > max_nelements)
        max_nelements = columns[i];

      low += interval;
      high += interval;
    }

    return (double)max_nelements / size;
  }

  void PrintHorizontal() {
    char symbol = '|';
    int height;
    String col;

    for (int i = 0; i < ncolumns; i++) {
      height = (int)(columns[i] / scale);
      col = "";
      for (int j = 0; j < height; j++)
        col += symbol;
      System.out.format("%5d-%-5d " + col + " %d\n",
        min + i*interval, min + (i+1)*interval, columns[i]);
    }
  }

  void PrintVertical() {
    char symbol = '|';
    int height;

    Screen scr = new Screen(size + 1, ncolumns * 4);

    for (int i = 0; i < ncolumns; i++) {
      height = (int)(columns[i] / scale);
      scr.VerticalLine(i * 3, 0, height, symbol);
      scr.VerticalLine(i * 3 + 1, 0, height, symbol);
      scr.Number(i * 3, height, columns[i]);
    }

    scr.Print();
  }

  Hist(HistData d, int min, int max, int i) {
    double n;

    this.min = min;
    this.max = max;
    interval = i;

    data = d;

    n = (max - min) / (double)interval;
    ncolumns = ((int)n == n) ? (int)n : (int)(n + 1);

    System.out.println("columns: " + ncolumns);

    columns = new int[ncolumns];
    scale = Fill();
  }
}

class Screen {
  private int height, width;
  private char matrix[][];

  void VerticalLine(int x, int y, int h, char ch) {
    while (h > 0) {
      matrix[y][x] = ch;
      y++;
      h--;
    }
  }

  void Number(int x, int y, int n) {
    String str = Integer.toString(n);

    for (int i = 0; i < str.length(); i++) {
      matrix[y][x + i] = str.charAt(i);
    }
  }

  void Print() {
    for (int i = height - 1; i >= 0; i--) {
      for (int j = 0; j < width; j++) {
        System.out.print(matrix[i][j]);
      }
      System.out.println();
    }
  }

  Screen(int height, int width) {
    this.height = height;
    this.width = width;

    matrix = new char[height][width];

    for (int i = 0; i < width; i++)
      for (int j = 0; j < height; j++)
        matrix[j][i] = ' ';
  }
}

public class HistDemo {
  public static void main(String[] args) {
    int size = 1000;
    int min = 0;
    int max = 100;
    int interval = 5;

    HistData data = new HistData(size);

    data.FillRandom(min, max);

    Hist hist = new Hist(data, min, max, interval);

    System.out.println();

    hist.PrintHorizontal();

    System.out.println();

    hist.PrintVertical();

    System.out.println();
  }
}
