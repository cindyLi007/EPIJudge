package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberOfTraversalsMatrix {
  public static int numberOfWays(int n, int m) {
    return computeNumberOfWaysToXY(n - 1, m - 1, new int[n][m]);
  }

  /**
   * the count of different way from [0, 0] to [x, y]
   */
  private static int computeNumberOfWaysToXY(int x, int y,
                                             int[][] numberOfWays) {
    // base, from [0,0] to [0,0] there is only one path, or from [0, i] 0<=i<=cols or from [i, 0] 0<=i<=rows, there is only one path
    if (x == 0 || y == 0) {
      return 1;
    }

    // this check is necessary, we use cache to avoid TLE
    if (numberOfWays[x][y] == 0) {
      int waysTop = computeNumberOfWaysToXY(x - 1, y, numberOfWays);
      int waysLeft = computeNumberOfWaysToXY(x, y - 1, numberOfWays);
      numberOfWays[x][y] = waysTop + waysLeft;
    }
    return numberOfWays[x][y];
  }

  @EpiTest(testfile = "number_of_traversals_matrix.tsv")
  public static int computeNumberOfWaysSpaceEfficient(int n, int m) {
    if (n < m) {
      int temp = n;
      n = m;
      m = temp;
    }
    List<Integer> A = new ArrayList<>(Collections.nCopies(m, 1));
    for (int i = 1; i < n; ++i) {
      int prevRes = 1;
      for (int j = 1; j < m; ++j) {
        A.set(j, A.get(j) + prevRes);
        prevRes = A.get(j);
      }
    }
    return A.get(m - 1);
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
