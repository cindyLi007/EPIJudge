package epi.excercise.dynamic.programming;

// EPI 16.2 variant 2
public class LongestSubsequenceSubstring {

  public static String longestSubsequence(String S, String T) {
    int M = S.length(), N = T.length();
    String[][] dp = new String[M+1][N+1];
    for (int i = 0; i < M; i++) dp[i + 1][0] = "";
    for (int i = 0; i < N; i++) dp[0][i+1] = "";
    dp[0][0] = "";
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if (S.charAt(i)==T.charAt(j)) dp[i + 1][j + 1] = dp[i][j] + S.charAt(i);
        else {
          String s1 = dp[i+1][j], s2 = dp[i][j+1], s3 = dp[i][j];
          int l1 = s1.length(), l2 = s2.length(), l3 = s3.length();
          if (l1 >= Math.max(l2, l3)) {
            dp[i+1][j+1] = dp[i+1][j];
          } else if (l2 >= l3) {
            dp[i+1][j+1] = dp[i][j+1];
          } else dp[i+1][j+1] = dp[i][j];
        }
      }
    }
    return dp[M][N];
  }

  public static void main(String... args) {
    System.out.println(longestSubsequence("Carthorsf", "Orchestra"));
  }
}
