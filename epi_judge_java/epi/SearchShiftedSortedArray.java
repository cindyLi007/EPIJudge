package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchShiftedSortedArray {
  @EpiTest(testfile = "search_shifted_sorted_array.tsv")
  // Time: O(logN), Space: O(1)
  public static int searchSmallest(List<Integer> A) {
    int low=0, high=A.size()-1;
    // loop ends when low==high
    while (low<high) {
      int mid=low+(high-low)/2;
      // all element is distinct
      if (A.get(mid)<A.get(high)) {
        high=mid;
      } else {
        // must be low=mid+1, otherwise can be endless
        low=mid+1;
      }
    }
    return low;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
