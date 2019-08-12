package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MinimumWeightPathInATriangle {
  @EpiTest(testfile = "minimum_weight_path_in_a_triangle.tsv")

  // Time: O(N^2), Space: O(N), we can save space by using triangle to store the partial path sum. but that makes time longer
  public static int minimumPathTotal(List<List<Integer>> triangle) {
    int len = triangle.size();
    if (len==0) return 0;
    int[] dp = new int[len];
    List<Integer> bottom = triangle.get(len - 1);
    for (int i = 0; i < bottom.size(); i++) {
      dp[i] = bottom.get(i);
    }

    for (int i=len-2; i>=0; i--) {
      List<Integer> curLay = triangle.get(i);
      int right = dp[curLay.size()];
      for (int idx=curLay.size()-1; idx>=0; idx--) {
        int min = Math.min(right, dp[idx]);
        right = dp[idx];
        dp[idx]=min + curLay.get(idx);
      }
    }

    return dp[0];
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
