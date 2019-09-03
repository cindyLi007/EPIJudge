package epi.excercise.dynamic.programming;

/*
Suppose the final score is given in the fore (s, s'). Team1 scored s points and Team2 scored s' points. how would
you compute the number of distinct scoring sequences which result in this score?
 */
public class TwoTeamSequence {

  public static int twoTeamSequence(int s1, int s2, int[] nums) {
    int[][] dp = new int[s1+1][s2+1];

    int s = Math.max(s1, s2);
    int[] dp_1 = new int[s+1];
    dp_1[0] = 1;
    for (int i=1; i<=s; i++) {
      for (int num : nums) {
        if (num>i) continue;
        dp_1[i] += dp_1[i-num];
      }
    }

    for (int i=1; i<=s1; i++) dp[i][0] = dp_1[i];
    for (int i=1; i<=s2; i++) dp[0][i] = dp_1[i];
    dp[0][0]=1;

    for (int i=1; i<=s1; i++) {
      for (int j=1; j<=s2; j++) {
        int t1=0, t2=0;
        for (int num : nums) {
          if (i>=num) t1+=dp[i-num][j];
          if (j>=num) t2+=dp[i][j-num];
        }
        dp[i][j]=t1+t2;
      }
    }

    return dp[s1][s2];
  }

  public static void main(String... args) {
    int[] plays = new int[]{2, 3, 7};
    int res = twoTeamSequence(6, 3, plays);
    System.out.println(res);
  }
}
