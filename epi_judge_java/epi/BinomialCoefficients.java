package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

// Time: O(N*K), Space: O(N*K)
public class BinomialCoefficients {

  private static int recursive(int n, int k, int[][] dp) {
    if (n==k || k==0) {
      return 1;
    }
    if (dp[n][k]==0) {
      dp[n][k] = recursive(n - 1, k - 1, dp) + recursive(n - 1, k, dp);
    }
    return dp[n][k];
  }

  @EpiTest(testfile = "binomial_coefficients.tsv")
  public static int computeBinomialCoefficient(int n, int k) {
    int[] dp = new int[k+1];
//    int[][] dp = new int[k+1][n+1];
//    for (int i=0; i<=n; i++) dp[0][i] = 1; // from i numbers, choose 0, always 1;
//    for (int i=1; i<=k; i++) dp[i][i] = 1; // from i numbers, choose i, always 1;
    for (int i=0; i<=n; i++) {
      int prev = dp[0];
      dp[0]=1;
      for (int j=1; j<=Math.min(k, i); j++) {
        int temp = dp[j];
        dp[j] += prev;
        prev = temp;
//        dp[j][i] = dp[j][i-1] + dp[j-1][i-1];
      }
    }
//    return dp[k][n];
    return dp[k];
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
