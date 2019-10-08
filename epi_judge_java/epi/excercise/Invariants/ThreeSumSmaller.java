package epi.excercise.Invariants;

import java.util.Arrays;

/**
 * EPI 17.4 variant 4 Write a program that takes as input an array of integers A and an integer T, and returns the number
 * of 3-tuples (p, q, r) such that A[p] + A[q] + A[r] <= T and A[p]<A[q]<A[r]
 */
public class ThreeSumSmaller {

  // Time: O(N^2), Space: O(1)
  public int threeSumSmaller(int[] nums, int target) {
    Arrays.sort(nums);
    int N = nums.length, count = 0;
    for (int i=0; i<N-2; i++) {
      int v = target - nums[i];
      for (int j=i+1, k=N-1; j<k;) {
        int sum = nums[j] + nums[k];
        if (sum<=v) {
          // now in range [j+1, k], (each number as right) + nums[j] can make sum <= v, so all of them count
          count+=k-j;
          j++;
        } else {
          k--;
        }
      }
    }
    return count;
  }
}
