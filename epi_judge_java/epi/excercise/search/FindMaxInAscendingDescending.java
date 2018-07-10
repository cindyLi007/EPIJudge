package epi.excercise.search;

import java.util.Arrays;
import java.util.List;

public class FindMaxInAscendingDescending {
  public static int findMaxInAscendingDescending(List<Integer> A) {
    int low = 0, high=A.size()-1;
    while (low<high) {
      int mid = low + (high-low)/2;
      if (A.get(mid) > A.get(high)) {
        high=mid;
      } else {
        low=mid+1;
      }
    }
    return low;
  }

  public static void main(String... args) {
    List<Integer> list = Arrays.asList(1, 2, 23, 100, 1000, 99, 98, 56, 4, -1);
    int index = FindMaxInAscendingDescending.findMaxInAscendingDescending(list);

    System.out.println("The max value :" + list.get(index));
  }
}
