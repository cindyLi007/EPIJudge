package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchFirstKey {
  @EpiTest(testfile = "search_first_key.tsv")

  // Time: O(logN), Space: O(1)
  public static int searchFirstOfK(List<Integer> A, int k) {
    int res = -1;
    int low=0, high=A.size()-1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (A.get(mid) < k) {
        low = mid + 1;
      }
      if (A.get(mid) == k) {
        res = mid;
        high = mid-1;
      }
      if (A.get(mid) > k) {
        high = mid - 1;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
