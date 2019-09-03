package epi.excercise.dynamic.programming;

import java.util.Arrays;

/**
 * Define a string t to be an interleaving of string s1 and s2 if there is a way to interleave the char of s1 and s2
 * keeping the left-to-right order of each, to obtain t.
 * Design an algorithm that takes as input strings s1, s2 and t, and determines if t is an interleaving of s1 and s2
 */
public class InterleaveStrings {
  static int[][] dp;

  public static boolean interleave(String s1, String s2, String t) {
    int M = s1.length(), N = s2.length(), L = t.length();
    if (M+N != L) return false;
    if (M==0) return s2.equals(t);
    if (N==0) return s1.equals(t);
    dp = new int[M+1][N+1];
    for (int[] row : dp) Arrays.fill(row, -1);
    helper(s1, M, s2, N, t, L);
    return dp[M][N]==1;
  }

  private static int helper(String s1, int m, String s2, int n, String t, int l) {
    if (dp[m][n]!=-1) return dp[m][n];
    // base case
    if (m==0) dp[m][n] = s2.substring(0, n).equals(t.substring(0, l)) ? 1 : 0;
    else if (n==0) dp[m][n] = s1.substring(0, m).equals(t.substring(0, l)) ? 1 : 0;
    else {
      int temp = 0;
      if (s1.charAt(m - 1) == t.charAt(l - 1)) {
        temp += helper(s1, m - 1, s2, n, t, l - 1);
      }
      if (s2.charAt(n - 1) == t.charAt(l - 1)) {
        temp += helper(s1, m, s2, n - 1, t, l - 1);
      }
      if (temp >= 1) dp[m][n] = 1;
      else dp[m][n] = 0;
    }
    return dp[m][n];
  }

  public static void main(String... args) {
    String s1 = "gtaa", s2 = "atc";
    System.out.println(interleave(s1, s2, "gattaca"));
    System.out.println(interleave(s1, s2, "gtataac"));
    System.out.println(interleave(s1, s2, "gatacta"));

  }
}
