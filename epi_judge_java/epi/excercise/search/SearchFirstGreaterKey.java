package epi.excercise.search;

import java.util.Arrays;
import java.util.List;

/**
 * 11.1 variant I
 * Design an efficient algorithm that takes a sorted array and a key, and finds the index of the first occurrence of
 * an element greater than that key.
 */
// Time: O(logN), Space: O(1)
public class SearchFirstGreaterKey {
  public static int searchFirstKeyGreaterThan(List<Integer> A, int k) {
    int res=-1;
    int low=0, high=A.size()-1;

    while (low<=high) {
      int mid = low + (high - low) / 2;
      if (A.get(mid)<=k) {
        low=mid+1;
      } else { //A[mid] > k, it is a candidate
        res=mid;
        high=mid-1;
      }
    }

    return res;
  }

  public static void main(String... args) {
    List<Integer> A = Arrays.asList(-14, -10, 2, 108, 108, 243, 285, 285, 285, 401);

    int res = searchFirstKeyGreaterThan(A, 285);
    System.out.println(res + " should be 9");

    res = searchFirstKeyGreaterThan(A, -13);
    System.out.println(res + " should be 1");

    res = searchFirstKeyGreaterThan(A, -19);
    System.out.println(res + " should be 0");
  }
}
