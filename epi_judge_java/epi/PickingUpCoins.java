package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;

public class PickingUpCoins {
  // sum[i] is the sum of [0, i]
  static int[] sum;
  static int[][] revenue;
  static int len;

  @EpiTest(testfile = "picking_up_coins.tsv")
  // Time: O(N*N), Space: O(N*N)
  // My algorithm is to count player1 revenue as positive, count player2 revenue as negative, save diff of player1 and player2
  // in dp
  public static int pickUpCoinsWOComputeSum(List<Integer> coins) {
    len = coins.size();
    // dp[i][j] stores from [i, j] (player1 revenue - player2 revenue)
    int[][] dp = new int[len][len];
    for (int i=len-1; i>=0; i--) for (int j=i; j<len; j++) {
      if (i==j) {
        dp[i][j] = coins.get(i);
      }
      else if (i+1==j) {
        dp[i][j] = Math.max(coins.get(i), coins.get(j)) - Math.min(coins.get(i), coins.get(j));
      }
      else {// (distance between i and j) > 1
        dp[i][j] = Math.max(coins.get(i) - dp[i + 1][j], coins.get(j) - dp[i][j - 1]);
      }
    }
    sum=new int[coins.size()];
    sum[0]=coins.get(0);
    for (int i = 1; i < coins.size(); i++) {
        sum[i]=sum[i-1] + coins.get(i);
    }
    /** x-y = dp[0][len-1] x is first players' revenue, y is 2nd player's revenue
    x+y = sum[len-1]
    2x = sum+dp[0][len-1] */
    return (dp[0][len-1] + sum[len-1])/2;
    /*
    return helper(coins, 0, coins.size()-1,
            new int[coins.size()][coins.size()]);*/
  }

  // because the enemy will always minimize mine revenue, we should choose the min revenue of all choice after my this
  // time choice and his next time choice (notice, his next time choice depends on my this time choice
  private static int helper(List<Integer> coins, int start, int end, int[][] r) {
    if (start>end) return 0;
    if (r[start][end] != 0) return r[start][end];

    int pickupStart = coins.get(start) + Math.min(helper(coins, start + 1, end - 1, r), helper(coins, start + 2, end, r));
    int pickupEnd = coins.get(end) + Math.min(helper(coins, start + 1, end - 1, r), helper(coins, start, end-2, r));
    r[start][end] = Math.max(pickupStart, pickupEnd);
    return r[start][end];
  }

  // Time: O(n*n), Space: O(n*n)
  public static int pickUpCoins_withComputeSum(List<Integer> coins) {
    sum=new int[coins.size()];
    sum[0]=coins.get(0);
    for (int i = 1; i < coins.size(); i++) {
        sum[i]=sum[i-1] + coins.get(i);
    }

    revenue = new int[coins.size()][coins.size()];

    return pickUpCoins(coins, 0, coins.size()-1);
  }

  // dp[start][end] = Max(coins[start] + (sum[start+1][end]-dp[start+1][end],
  //                      coins[end] + (sum[start][end-1]-dp[start][end-1]))
  private static int pickUpCoins(List<Integer> coins, int start, int end) {
    if (start>end) {
      return 0;
    }
    if (start==end) {
      return coins.get(start);
    }
    if (start==end-1) {
      return Math.max(coins.get(start), coins.get(end));
    }
    if (revenue[start][end] == 0) {
      int pickUpStart = coins.get(start) + (sum(coins, start + 1, end) - pickUpCoins(coins, start + 1, end));
      int pickUpEnd= coins.get(end) + (sum(coins, start, end-1) - pickUpCoins(coins, start, end-1));
      revenue[start][end] = Math.max(pickUpStart, pickUpEnd);
    }
    return revenue[start][end];
  }

  private static int sum(List<Integer> coins, int start, int end) {
    return sum[end] - sum[start] + coins.get(start);
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
