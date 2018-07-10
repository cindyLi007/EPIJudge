package epi;

import java.util.Arrays;
import java.util.List;

public class SearchEntryEqualToIndexDup {
  // Time: O(N), Space: O(logN)
  public static int searchEntryEqualToItsIndex(List<Integer> A) {
    return searchEntryEqualToItsIndex(A, 0, A.size() - 1);
  }

  private static int searchEntryEqualToItsIndex(List<Integer> A, int low, int high) {
    if (low > high || low == high && A.get(low) != low) {
      return -1;
    }

    int mid = low + (high - low) / 2;
    int diff = A.get(mid) - mid;
    if (diff == 0) {
      return mid;
    }

    int res;
    if (diff < 0) { // since may have duplicated element, left shift high pointer |diff|
      res = searchEntryEqualToItsIndex(A, low, mid + diff);
      if (res == -1) {
        res = searchEntryEqualToItsIndex(A, mid + 1, high);
      }
    } else {
      // since may have duplicated element, right shift low pointer diff to make index catch up value
      res = searchEntryEqualToItsIndex(A, mid + diff, high);
      if (res == -1) {
        res = searchEntryEqualToItsIndex(A, low, mid - 1);
      }
    }

    return res;
  }

  public static void main(String... args) {
    List<Integer> list = Arrays.asList(-100, -1, 1, 2, 3, 4, 5, 9, 9, 9, 19);

    int res = SearchEntryEqualToIndexDup.searchEntryEqualToItsIndex(list);

    System.out.println("the index is " + res + ", value is " + (res ==-1 ? "not exists" : list.get(res)));
  }
}
