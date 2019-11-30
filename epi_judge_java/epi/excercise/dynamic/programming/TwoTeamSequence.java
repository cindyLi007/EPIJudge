package epi.excercise.dynamic.programming;

/* EPI 16.2 variant 3
Suppose the final score is given in the fore (s, s'). Team1 scored s points and Team2 scored s' points. how would
you compute the number of distinct scoring sequences which result in this score?
 */
public class TwoTeamSequence {

  public static int twoTeamSequence(int s1, int s2, int[] nums) {
    int[][] dp = new int[s1 + 1][s2 + 1];

    int s = Math.max(s1, s2);
    int[] dp_1 = new int[s + 1];
    dp_1[0] = 1;
    for (int i = 1; i <= s; i++) {
      for (int num : nums) {
        if (num > i) continue;
        dp_1[i] += dp_1[i - num];
      }
    }

    for (int i = 1; i <= s1; i++) dp[i][0] = dp_1[i];
    for (int i = 1; i <= s2; i++) dp[0][i] = dp_1[i];
    dp[0][0] = 1;

    for (int i = 1; i <= s1; i++) {
      for (int j = 1; j <= s2; j++) {
        int t1 = 0, t2 = 0;
        for (int num : nums) {
          if (i >= num) t1 += dp[i - num][j];
          if (j >= num) t2 += dp[i][j - num];
        }
        dp[i][j] = t1 + t2;
      }
    }

    return dp[s1][s2];
  }

  public static void main(String... args) {
    int[] plays = new int[]{2, 3, 7};
    int res = twoTeamSequence(6, 3, plays);
    System.out.println(res);
    System.out.println("leading change is " + leadChange(5, 4, plays));
  }

  // EPI 16.1 variant 4
  public static int leadChange(int s1, int s2, int[] nums) {
    int s = Math.max(s1, s2);
    int[] dp_1 = new int[s + 1];
    dp_1[0] = 1;
    for (int i = 1; i <= s; i++) {
      for (int num : nums) {
        if (num > i) continue;
        dp_1[i] += dp_1[i - num];
      }
    }

    int[][] dp = new int[s1 + 1][s2 + 1];
    for (int i = 1; i <= s1; i++) {
      for (int j = 1; j <= s2; j++) {
        int t1 = 0, t2 = 0;
        for (int num : nums) {
          if (dp_1[j] != 0 && dp_1[i] != 0) { // means this is a valid combination score
            if (i >= num && dp_1[i - num] != 0) {
              int d1 = i - j, d2 = i - num - j;
              int temp = 0;
              if (d1!=0 && d2!=0 && ((d1 ^ d2) >> 31) != 0) temp = 1;
              t1 = Math.max(t1, temp + dp[i-num][j]);
            }
            if (j >= num && dp_1[j - num] != 0) {
              int d1 = i - j, d2 = i - (j-num);
              int temp = 0;
              if (d1!=0 && d2!=0 && ((d1 ^ d2) >> 31) != 0) temp = 1;
              t2 = Math.max(t2, temp + dp[i][j-num]);
            }
          }
        }
        dp[i][j] = Math.max(t1, t2);
      }
    }
    return dp[s1][s2];
  }
}
