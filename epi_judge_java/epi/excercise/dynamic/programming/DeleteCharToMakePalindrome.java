package epi.excercise.dynamic.programming;

import java.util.Arrays;

/**
 * EPI 16.2 variant 3 Given a string A, compute the minimum number of characters you need to delete from A ot make the
 * resulting string a palindrome.
 * Time: O(N*N), Space: O(N*N)
 */
public class DeleteCharToMakePalindrome {
  static int[][] dp;

  public static int process(String s) {
    int N = s.length();
    if (N <= 1) return 0;
    dp = new int[N][N];
    for (int[] row : dp) Arrays.fill(row, -1);
    for (int i=0; i<N; i++) dp[i][i]=0;
    /*for (int i=N-2; i>=0; i--) {
      for (int j=i+1; j<N; j++) {
        if (s.charAt(i)==s.charAt(j)) dp[i][j]= i+1<=j-1 ? dp[i+1][j-1] : 0;
        else {
          dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1]) + 1;
        }
      }
    }
    return dp[0][N-1];*/
    return helper(s, 0, N - 1);
  }

  private static int helper(String s, int start, int end) {
    if (start > end) return 0;
    if (dp[start][end]!=-1) return dp[start][end];
    if (start==end) {
      dp[start][end]=0;
      return 0;
    }
    if (s.charAt(start) == s.charAt(end)) {
      dp[start][end] = helper(s, start+1, end-1);
    } else {
      dp[start][end] = Math.min(helper(s, start+1, end), helper(s, start, end-1)) + 1;
    }
    return dp[start][end];
  }

  public static void main(String... args) {
    System.out.println(process("geeksforgeeks")); // should return 8
    System.out.println(process("aebcbda")); // should return 2
  }
}
