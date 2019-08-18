package epi.excercise.search;

import java.util.Arrays;
import java.util.List;

/**
 * Let A be an unsorted array of n integers, which A[0]>=A[1] and A[n-2]<=A[n-1]. Call an index i
 * a local minimum if A[i] is less than or equal to its neighbors.
 */
public class FindLocalMinimum {

  // Time: O(logN), Space: O(logN)
  public static int findLocalMinimum(List<Integer> A) {
    return findLocalMinimum(A, 0, A.size()-1);
  }

  private static int findLocalMinimum(List<Integer> A, int low, int high) {
    if (low>high) return -1;
    int mid = low + (high - low) / 2;
    if (mid>0 && A.get(mid-1)>=A.get(mid)
        && mid<A.size()-1 && A.get(mid+1)>=A.get(mid)) {
      return mid;
    // Give A[0]>=A[i], if mid>0 && A.get(mid-1)<A.get(mid), we guarantee left half must have a local minimum
    } else if (mid>0 && A.get(mid-1)<A.get(mid)){
      return findLocalMinimum(A, low, mid - 1);
    }
    return findLocalMinimum(A, mid + 1, high);
  }

  public static void main(String... args) {
    List<Integer> list = Arrays.asList(9, 6, 3, 14, 5, 7, 14);
    int res = findLocalMinimum(list);
    assert (res == 2);

    list = Arrays.asList(4, 3, 1, 14, 16, 40);
    res = findLocalMinimum(list);
    assert (res == 2);

    list = Arrays.asList(23, 8, 15, 2, 3);
    res = findLocalMinimum(list);
    assert (res == 1);
  }
}
