package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestNondecreasingSubsequence {
  @EpiTest(testfile = "longest_nondecreasing_subsequence.tsv")

  // Time: O(N*N), Space: O(N)
  public static int longestNondecreasingSubsequenceLength(List<Integer> A) {
    int N = A.size();
    int[] dp = new int[N];
    if (N==0) return 0;
    int max = 1;
    dp[0]=1;
    for (int i=1; i<N; i++) {
      dp[i]=1;
      for (int j=i-1; j>=0; j--) {
        if (A.get(j)<=A.get(i)) dp[i] = Math.max(dp[j]+1, dp[i]);
      }
      max = Math.max(max, dp[i]);
    }
    return max;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
