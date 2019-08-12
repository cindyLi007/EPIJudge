package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;

public class NumberOfTraversalsMatrix {
  @EpiTest(testfile = "number_of_traversals_matrix.tsv")

  // Time: O(N*M), Space: O(N*M)
  public static int numberOfWays(int n, int m) {
    int[] dp = new int[m];
    Arrays.fill(dp, 1);
    for (int i=1; i<n; i++) {
      int left = 1;
      for (int j=1; j<m; j++) {
        dp[j] += left;
        left = dp[j];
      }
    }
    return dp[m-1];
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
