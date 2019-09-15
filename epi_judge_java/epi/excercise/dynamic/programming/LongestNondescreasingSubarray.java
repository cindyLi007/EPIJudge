package epi.excercise.dynamic.programming;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *  Write a program that takes as input an array of numbers and returns a longest non-decreasing sub-sequence in the array.
 */
public class LongestNondescreasingSubarray {

  // Time: O(N*N), Space: O(N)
  public static List<Integer> longestNondescreasing(int[] nums) {
    int N = nums.length;
    int[] dp = new int[N], prev = new int[N];
    dp[0] = 1;
    Arrays.fill(prev, -1);
    int max = 0;
    for (int i=1; i<N; i++) {
      dp[i]=1;
      for (int j=i-1; j>=0; j--) {
        if (nums[j]<=nums[i] && dp[j]+1 > dp[i])  {
          dp[i] = dp[j] + 1;
          prev[i] = j;
        }
      }
      if (dp[i] > dp[max]) max = i;
    }
    List<Integer> res = new LinkedList<>();
    while (max>=0) {
      res.add(nums[max]);
      max = prev[max];
    }
    Collections.reverse(res);
    return res;
  }

  public static void main(String... args) {
    int[] nums = new int[] {0, 8, 4, 12, 2, 10, 6, 14, 1, 9};
    List<Integer> longestNondescreasing = longestNondescreasing(nums);
    longestNondescreasing.stream().forEach(o-> System.out.print(o + ", "));
    System.out.println();
  }
}
