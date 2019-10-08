package epi.excercise.Invariants;

import epi.TwoSum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * EPI 17.4 Variant
 * Solve 17.4 but the 3 element must be distinct
 */
public class ThreeSum {

  public static boolean hasThreeSum(List<Integer> A, int t) {
    Collections.sort(A);
    for (int i=0; i<A.size(); i++) {
      if (getTwoSum(A, t - A.get(i), i+1).length>0)
        return true;
    }
    return false;
  }

  private static int[] getTwoSum(List<Integer> A, int target, int startIdx) {
    int len = A.size();
    if (startIdx>len-2) return new int[]{};
    int i=startIdx, j=len-1;
    while (i<j) {
      int sum = A.get(i) + A.get(j);
      if (sum==target)
        return new int[]{A.get(i), A.get(j)};
      if (sum > target) j--;
      else i++;
    }
    return new int[]{};
  }

  public static void main(String... args) {
    List<Integer> list = new ArrayList<>();
    list.add(5);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(3);
    System.out.println(hasThreeSum(list, 9));
  }
}
