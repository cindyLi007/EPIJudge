package epi.excercise.search;

import java.util.Arrays;
import java.util.List;

/**
 * Write a program which takes a sorted array A of integers, and an integer K, and returns the interval
 * enclosing k.
 */
public class EnclosingInterval {

  // Time : O(logN), Space: O(1)
  public static int[] findInterval(List<Integer> list, int k) {
    int low = 0, high = list.size() - 1;

    int left = -1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (list.get(mid) < k) {
        low = mid + 1;
      } else if (list.get(mid) > k) {
        high = mid - 1;
      } else {
        left = mid;
        high = mid - 1;
      }
    }

    // reset low and high
    low = 0;
    high = list.size() - 1;
    int right = -1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (list.get(mid) < k) {
        low = mid + 1;
      } else if (list.get(mid) > k) {
        high = mid - 1;
      } else {
        right = mid;
        low = mid + 1;
      }
    }

    return new int[]{left, right};
  }

  public static void main(String... args) {
    List<Integer> list = Arrays.asList(1, 2, 2, 4, 4, 4, 7, 11, 11, 13);
    int[] interval = findInterval(list, 13);

    System.out.println("[" + interval[0] + " , " + interval[1] + "]");
  }
}
