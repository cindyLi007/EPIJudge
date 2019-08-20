package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

// Time: O(lgK), because from 1 to K, we have K numbers, each time we remove half, Space: O(1)
public class IntSquareRoot {
  @EpiTest(testfile = "int_square_root.tsv")

  public static int squareRoot(int k) {
    int low=1, high=k;
    while (low<=high) {
      int mid = low + (high-low)/2;
      // MUST use mid == k/mid, could not use mid * mid == k, that is because mid * mid can be overflow Integer
      if (mid==k/mid) {
        return mid;
      }
      if (mid<k/mid) low=mid+1;
      else high=mid-1;
    }
    return high;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
