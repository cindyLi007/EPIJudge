package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class NumberOfTraversalsStaircase {
  //@EpiTest(testfile = "number_of_traversals_staircase.tsv")
  // Time: O(N*m), Space: O(N), N is top, m is possible of step
  public static int numberOfWaysToTop(int top, int maximumStep) {
    return numberOfWaysToTop(top, maximumStep, new int[top+1]);
  }

  private static int numberOfWaysToTop(int top, int maximumStep, int[] dp) {
    if (top<=1) {
      return 1;
    }
    if (dp[top]==0) {
      for (int step=1; step<=maximumStep && top-step>=0; step++) {
        dp[top] += numberOfWaysToTop(top-step, maximumStep, dp);
      }
    }
    return dp[top];
  }

  @EpiTest(testfile = "number_of_traversals_staircase.tsv")
  public static int numberOfWaysToTop_bottomUp(int top, int maximumStep) {
    if (top==0) return 0;
    int[] dp = new int[top + 1];
    dp[0]=dp[1]=1;
    // f(n,k) = sum(f(n-i, i)), 1<=i<=k, previous step sit stairs n-k to n-1, this step can be k to 1 to reach n

    for (int i=2; i<=top; i++) {
      for (int j=1; j<=maximumStep&&i>=j; j++) {
        dp[i]+=dp[i-j];
      }
    }
    return dp[top];
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
