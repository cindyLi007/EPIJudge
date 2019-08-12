package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;

public class LevenshteinDistance {
  @EpiTest(testfile = "levenshtein_distance.tsv")

  public static int levenshteinDistance(String A, String B) {
    int[][] dp = new int[A.length()][B.length()];
    for (int[] row : dp) {
      Arrays.fill(row, -1); // -1 means we did not calculate the value
    }
    return distance(A, A.length()-1, B, B.length()-1, dp);
  }

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
