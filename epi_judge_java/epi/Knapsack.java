package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.List;

public class Knapsack {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class Item {
    public Integer weight;
    public Integer value;

    public Item(Integer weight, Integer value) {
      this.weight = weight;
      this.value = value;
    }
  }

  /* this is a 2D array, list it here for easy understand below code
  int N=items.size();
  int[][] dp = new int[N][capacity+1];
  for (int i = 0; i < N; i++) {
    int curItemV = items.get(i).value, curItemW = items.get(i).weight;
    for (int c = 0; c <= capacity; c++) {
      if (i == 0) {
        dp[i][c] = c >= curItemW ? curItemV : 0;
      } else {
        // from item [0, i], for capacity j, what is the max value
        // from item [0, i-1] for capacity j, or [0, i-1] for (capacity - items.get(i).weight) if capacity - items.get(i).weight > 0
        // NOTICE: can only grab one item once, so here is dp[i-1][j-w], not dp[i][j-w]
        int woItem = dp[i-1][c];
        int wiItem = c>=curItemW ? dp[i-1][c-curItemW] + curItemV : 0;
        dp[i][c] = Math.max(woItem, wiItem);
      }
    }
  }
  return dp[N - 1][capacity];
  */

  @EpiTest(testfile = "knapsack.tsv")
  // Time: O(n*w) basically it is the fill in dp time, Space: O(w)
  public static int optimumSubjectToCapacity(List<Item> items, int capacity) {
    int N = items.size();
    int[] dp = new int[capacity+1];
    for (int i = 0; i < N; i++) {
      int curItemV = items.get(i).value, curItemW = items.get(i).weight;
      // must descreasing, that is because we need previous line' result
      for (int c = capacity; c >=0; c--) {
        if (i == 0) {
          dp[c] = c >= curItemW ? curItemV : 0;
        } else {
          int wiItem = c>=curItemW ? dp[c-curItemW] + curItemV : 0;
          dp[c] = Math.max(dp[c], wiItem);
        }
      }
    }
    return dp[capacity];
    /*for (int[] row : dp) Arrays.fill(row, -1);
    return helper(items, capacity, N - 1, dp);*/
  }

  // recursive DP is slower than the above bottom-up
  private static int helper(List<Item> items, int capacity, int idx, int[][] dp) {
    if (dp[idx][capacity]!=-1)
      return dp[idx][capacity];

    if (idx == 0) {
      for (int i = 0; i <= capacity; i++) {
        dp[0][i] = i>=items.get(0).weight ? items.get(0).value : 0;
      }
    } else {
      int woCurItem = helper(items, capacity, idx-1, dp);
      Integer curItemW = items.get(idx).weight;
      Integer curItemV = items.get(idx).value;
      int wiCurItem = capacity>=curItemW ? helper(items, capacity-curItemW, idx-1, dp) + curItemV : 0;
      dp[idx][capacity] = Math.max(wiCurItem, woCurItem);
    }
    return dp[idx][capacity];
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
