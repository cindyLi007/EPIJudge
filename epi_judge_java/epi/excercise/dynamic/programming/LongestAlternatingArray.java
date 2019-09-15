package epi.excercise.dynamic.programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Define a sequence of numbers to be alternating if Ai < Ai+1 for even i and Ai > Ai+1 for odd i. Given an array of
 * numbers A of length n, find a longest sub-sequence (i0,..., ik-1) such that (A[i0], A[i1],..., A[ik-1]) is alternating.
 * NOTE: elements are not required to immediately follow each other in the original sequence.
 */
public class LongestAlternatingArray {

  /**
   * It is very hard to do this way, because we need interleave between increasing and decreasing when we buildup the sequence,
   * although it can save space, it make backtrack the sequence very tricky
   */
  public static List<Integer> longestAlternatingArray_saveSpace(int[] nums) {
    int N = nums.length;
    List<Integer> list = new ArrayList<>();
    // dp[i][0] is the longest alternating sub list's length which nums[i] > its previous element
    // dp[i][1] is the longest alternating sub list's length which nums[i] < its previous element
    int[][] dp = new int[N][2];
    // index[i][0] is the prev element's index which < nums[i] and satisfy alternating decreasing
    // index[i][1] is the prev element's index which > nums[i] and satisfy alternating increasing
    int[][] index = new int[N][2];
    for (int[] row : index) Arrays.fill(row, -1);
    dp[0][0] = 1; dp[0][1] = 1;
    // max is the longest alternating sub list's last index
    int max = 0;

    for (int i=1; i<N; i++) {
      dp[i][0] = 1; dp[i][1] = 1;
      for (int j=i-1; j>=0; j--) {
        if (nums[j] < nums[i] && dp[j][1] + 1 > dp[i][0]) {
          dp[i][0] = dp[j][1] + 1;
          index[i][0] = j;
        }
        if (nums[j] > nums[i] && dp[j][0] + 1 > dp[i][1]) {
          dp[i][1] = dp[j][0] + 1;
          index[i][1] = j;
        }
      }
      int temp = Math.max(dp[i][1], dp[i][0]);
      if (temp > Math.max(dp[max][1], dp[max][0])) {
        max = i;
      }
    }

    boolean increase = dp[max][0] > dp[max][1];
    do {
      list.add(nums[max]);
      max = increase ? index[max][0] : index[max][1];
      increase ^= true;
    } while (max>=0);
    Collections.reverse(list);
    // to satisfy even idx < odd idx element
    if (list.size()>1 && list.get(0) > list.get(1)) list.remove(0);
    return list;
  }

  // Time: O(N*N), may need extra time for list copy, which is O(N)
  // Space: O(N*N), for each element in nums, need a 2 lists for it
  public static List<Integer> longestAlternatingArray(int[] nums) {
    int N = nums.length;
    List<Integer> max = new ArrayList<>();
    // inc[i] is the longest alternating sub list which nums[i] > its previous element
    // des[i] is the longest alternating sub list which nums[i] < its previous element
    List<Integer>[] inc = (List<Integer>[])new List[N];
    List<Integer>[] des = (List<Integer>[])new List[N];
    inc[0] = Arrays.asList(nums[0]);
    des[0] = Arrays.asList(nums[0]);

    for (int i=1; i<N; i++) {
      inc[i] = new ArrayList<>(); // find j < nums[i] and des[j]
      des[i] = new ArrayList<>(); // find j > nums[i] and inc[j]
      inc[i].add(nums[i]);
      des[i].add(nums[i]);
      for (int j=i-1; j>=0; j--) {
        if (nums[j] < nums[i]) {
          if (des[j].size() + 1 > inc[i].size()) {
            inc[i].clear();
            inc[i].addAll(des[j]);
            inc[i].add(nums[i]);
          }
        }
        if (nums[j] > nums[i]) {
          if (inc[j].size() + 1 > des[i].size()) {
            des[i].clear();
            des[i].addAll(inc[j]);
            des[i].add(nums[i]);
          }
        }
      }
      if (max.size() < Math.max(inc[i].size(), des[i].size())) {
        max = inc[i].size() >= des[i].size() ? inc[i] : des[i];
      }
    }

    if (max.size()>1 && max.get(0) > max.get(1)) max.remove(0);
    return max;
  }

  public static void main(String... args) {

    int[] nums = new int[] {1, 5, 4};
    List<Integer> longestAlternatingArray = longestAlternatingArray_saveSpace(nums);
    System.out.print(longestAlternatingArray.size() + ": ");
    for (int num : longestAlternatingArray) System.out.print(num + ", ");
    System.out.println();

    nums = new int[] {10, 22, 9, 33, 49, 50, 31, 60};
    longestAlternatingArray = longestAlternatingArray_saveSpace(nums);
    System.out.print(longestAlternatingArray.size() + ": ");
    for (int num : longestAlternatingArray) System.out.print(num + ", ");
    System.out.println();

    nums = new int[] {10, 8, 4, 12, 2, 10, 6, 14, 1, 9};
    longestAlternatingArray = longestAlternatingArray_saveSpace(nums);
    System.out.print(longestAlternatingArray.size() + ": ");
    for (int num : longestAlternatingArray) System.out.print(num + ", ");
    System.out.println();

    nums = new int[] {1, 4, 5};
    longestAlternatingArray = longestAlternatingArray_saveSpace(nums);
    System.out.print(longestAlternatingArray.size() + ": ");
    for (int num : longestAlternatingArray) System.out.print(num + ", ");
    System.out.println();
  }
}
