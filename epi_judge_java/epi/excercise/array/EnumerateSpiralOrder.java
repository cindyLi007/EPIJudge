package epi.excercise.array;

// EPI 5.18 Variant 3
// Write a program to enumerate the first n pairs of integers {a, b) in spiral order,
// starting from (0,0) followed by (1,0). For example, if n = 10, your output should be
// (0,0), (1,0), (1, -1), (0, -1), (-1, -1), (-1,0), (-1,1), (0,1), (1,1), (2,1).
public class EnumerateSpiralOrder {

  public static int[][] spiralPosPair(int n) {
    int[][] res = new int[n][2];
    res[0] = new int[]{0, 0};
    int[][] dirs = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    int len = 1, d = 0, count = 1;
    int x = 0, y = 0;
    while (count < n) {
      for (int i=0; i<len; i++) {
        x += dirs[d][0]; y += dirs[d][1];
        res[count++] = new int[]{x, y};
      }
      d = (d+1)%4;
      if (d%2==0) len++;
    }
    return res;
  }

  public static void main(String... args) {
    int[][] res = spiralPosPair(10);
    for (int[] pair : res) {
      System.out.println("(" + pair[0] + ", " + pair[1] + ")");
    }
  }
}
