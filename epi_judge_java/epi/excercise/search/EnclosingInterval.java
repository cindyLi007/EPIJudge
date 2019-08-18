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

    int mid = 0;
    while (low <= high) {
      mid = low + (high - low) / 2;
      if (list.get(mid) < k) {
        low = mid + 1;
      } else if (list.get(mid) > k) {
        high = mid - 1;
      } else break;
    }

    if (low>high) return new int[]{-1, -1};

    int l = mid;
    int left = low, right = mid-1;
    while (left <= right) {
      int m = left + (right - left) / 2;
      if (list.get(m) < k) left=m+1;
      else { // list.get(m) can only <= k, that is because list.get(mid) == k
        l = m;
        right = m-1;
      }
    }

    int r = mid;
    left = mid+1;
    right = high;
    while (left <= right) {
      int m = left + (right - left) / 2;
      if (list.get(m) > k) right=m-1;
      else {
        r = m;
        left = m+1;
      }
    }

    return new int[]{l, r};
  }

  public static void main(String... args) {
    List<Integer> list = Arrays.asList(1, 2, 2, 4, 4, 4, 7, 11, 11, 13);
    int[] interval = findInterval(list, 11);

    System.out.println("[" + interval[0] + " , " + interval[1] + "]");
  }
}
