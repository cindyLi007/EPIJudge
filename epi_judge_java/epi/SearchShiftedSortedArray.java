package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchShiftedSortedArray {
  @EpiTest(testfile = "search_shifted_sorted_array.tsv")
  // Time: O(logN), Space: O(1)
  public static int searchSmallest(List<Integer> A) {
    int N = A.size();
    int low = 0, high = N-1;
    while (low < high) {
      int mid = low + (high - low) / 2;
      int num = A.get(mid);
      if (num < A.get(high)) {
        high = mid;
      } else {
        low = mid + 1;
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
