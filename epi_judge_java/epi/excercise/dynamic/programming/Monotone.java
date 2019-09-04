package epi.excercise.dynamic.programming;

/**
 * EPI 16.3 Variant 5
 * Decimal number is a sequence of digits, i.e., a sequence over {0,1,2,..., 9}. The sequence has to be of length 1 or
 * more, and the first element in the sequence cannot be 0. Call a decimal number D monotone if D[i] <= D[i + 1], 0 <= i < |D|.
 * Write a program which takes as input a positive integer k and computes the number of decimal numbers of length k that are monotone.
 */
public class Monotone {

  public static int monotone(int k) {
    int[][] dp = new int[k+1][10];
    for (int i=1; i<10; i++) dp[1][i] = i; // dp[1][i] mean for length-1 number, if we can choose from [1, i], count of monotone number
    // for ex. i=3, there is 3 monotone number, 1, 2, 3
    for (int i=2; i<=k; i++) {
      dp[i][1]=1; // for only 1 can choose, there is always 1 monotone number
      for (int j=2; j<=9; j++) {
        // for dp[i][j] which means we could make a length-i number from [1, j], it could append the digit j to all
        // (length-(i-1) including j) monotone numbers (dp[i-1][j]), or replace the last digit of all
        // (length-i not including j) monotone numbers (dp[i][j-1])
        dp[i][j] = dp[i][j-1] + dp[i-1][j];
      }
    }
    return dp[k][9];
  }

  public static int strictMonotone(int k) {
    if (k>9) return 0;

    int[][] dp = new int[k+1][10];
    for (int i=1; i<10; i++) dp[1][i] = i;
    for (int i=2; i<=k; i++) {
      // if number < length, we could not make up a strict monotone number
      for (int j=i; j<=9; j++) {
        // for dp[i][j] which means we could make a length-i number from [1, j], it could append the digit j to all
        // (length-(i-1) not including j) monotone numbers (dp[i-1][j-1], or replace the last digit of all (length-i not including j)
        // monotone numbers (dp[i][j-1])
        dp[i][j]=dp[i-1][j-1] + dp[i][j-1];
      }
    }
    return dp[k][9];
  }

  public static void main(String... args) {
    System.out.println(monotone(3));
    System.out.println(strictMonotone(2));
    System.out.println(strictMonotone(3));
  }
}
