package epi.excercise.dynamic.programming;

/**
 * EPI 16.3 variant 3
 * A fisherman is in a rectangular sea. The value of the fish at point (i, j) in the sea is specified by an n x m 2D array A.
 * Write a program that computes the maximum value of fish a fisherman can catch on a path from the upper leftmost point to the
 * lower rightmost point. The fisherman can only move down or right.
 */
public class Fishman {

  public static int fish(int[][] matrix) {
    int M = matrix.length, N = matrix[0].length;
    int[][] dp = new int[M][N];
    dp[0][0] = matrix[0][0];
    for (int i=1; i<M; i++) dp[i][0] = dp[i-1][0] + matrix[i][0];
    for (int i = 1; i < N; i++) dp[0][i] = dp[0][i - 1] + matrix[0][i];
    for (int i = 1; i < M; i++) {
      for (int j = 1; j < N; j++) {
        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
      }
    }
    return dp[M - 1][N - 1];
  }

  public static void main(String... args) {
    int[][] fish = new int[][]{{0, 5, 150}, {10, 30, 5}, {20, 40, 0}};
    System.out.println(fish(fish));
  }
}
