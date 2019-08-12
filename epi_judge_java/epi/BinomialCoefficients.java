package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

// Time: O(N*K), Space: O(N*K)
public class BinomialCoefficients {
  @EpiTest(testfile = "binomial_coefficients.tsv")

  public static int computeBinomialCoefficient(int n, int k) {
    int[] dp = new int[k+1];
    dp[0]=1;
    for (int i=1; i<=n; i++) {
      // NOTICE: must by from high-idx to low-idx, because we use prev-row value from left to right, need guarantee prev
      // value is not overwritten by this round
      for (int j=Math.min(i, k); j>0; j--) {
        dp[j] += dp[j-1];
      }
    }
    return dp[k];
  }

  private static int recursive(int n, int k, int[][] dp) {
    if (n==k || k==0) {
      return 1;
    }
    if (dp[n][k]==0) {
      dp[n][k] = recursive(n - 1, k - 1, dp) + recursive(n - 1, k, dp);
    }
    return dp[n][k];
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
