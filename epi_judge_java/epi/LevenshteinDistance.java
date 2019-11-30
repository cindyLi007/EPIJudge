package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class LevenshteinDistance {
  @EpiTest(testfile = "levenshtein_distance.tsv")

  // Bottom up DP Time: O(M*N)
  public static int levenshteinDistance(String A, String B) {
    int M = A.length(), N = B.length();
    int[][] dp = new int[M + 1][N + 1];
    for (int i=1; i<=M; i++) dp[i][0] = i;
    for (int i=1; i<=N; i++) dp[0][i] = i;
    for (int i=0; i<M; i++) {
      for (int j = 0; j < N; j++) {
        if (A.charAt(i) == B.charAt(j)) dp[i + 1][j + 1] = dp[i][j];
        else {
          dp[i+1][j+1] = 1 + Math.min(dp[i][j + 1], Math.min(dp[i + 1][j], dp[i][j]));
        }
      }
    }
    return dp[M][N];
  }

  // Recursive
  private static int distance(String a, int aIdx, String b, int bIdx, int[][] dp) {
    if (aIdx == -1) {
      return bIdx + 1; // insert [0, bIdx] char
    }
    if (bIdx == -1) {
      return aIdx + 1; // delete from [0, aIdx} char
    }
    if (dp[aIdx][bIdx] == -1) {
      if (a.charAt(aIdx) == b.charAt(bIdx)) {
        return distance(a, aIdx - 1, b, bIdx - 1, dp);
      } else {
        int subsituteDis = distance(a, aIdx - 1, b, bIdx - 1, dp);
        int insertDis = distance(a, aIdx, b, bIdx -1, dp);
        int deleteDis = distance(a, aIdx-1, b, bIdx, dp);
        dp[aIdx][bIdx] = 1 + Math.min(subsituteDis, Math.min(insertDis, deleteDis));
      }
    }
    return dp[aIdx][bIdx];
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
