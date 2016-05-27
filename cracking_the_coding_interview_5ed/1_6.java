class Matrix {
  private int N;
  private int A[][];

  void print() {
    for(int i = 0; i < N; i++) {
      for(int j = 0; j < N; j++) {
        System.out.format("%3d ", A[i][j]);
      }
      System.out.println();
    }
  }

  private void fillRandom() {
    for(int i = 0; i < N; i++)
      for(int j = 0; j < N; j++)
        A[i][j] = (int)(Math.random() * 100);
  }

  void rotate90() {
    int buf[][] = new int[N][N];
    for(int i = 0; i < N; i++)
      for(int j = 0; j < N; j++) {
        int new_i = j;
        int new_j = (N - 1) - i;
        buf[new_i][new_j] = A[i][j];
      }
    A = buf;
  }

  Matrix(int N) {
    this.N = N;
    A = new int[N][N];
    fillRandom();
  }
}

public class RotateDemo {

  static final int SIZE = 9;

  public static void main(String[] args) {
    Matrix m = new Matrix(SIZE);
    System.out.println("Initial matrix:");
    m.print();
    System.out.println("Rotated by 90 degrees:");
    m.rotate90();
    m.print();
  }
}
