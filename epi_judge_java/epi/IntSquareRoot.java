package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

// Time: O(lgK), Space: O(1)
public class IntSquareRoot {
  @EpiTest(testfile = "int_square_root.tsv")

  public static int squareRoot(int k) {
    int low=1, high=k;
    while (low<=high) {
      int mid = low + (high-low)/2;
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
