package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Time: O(N*LogK), Space: O(N)
public class SortIncreasingDecreasingArray {

  @EpiTest(testfile = "sort_increasing_decreasing_array.tsv")
  public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> A) {
    // Decomposes A into a set of sorted arrays.
    List<List<Integer>> subArrayList = new ArrayList<>();
    SubarrayType subarrayType = SubarrayType.INCREASING;
    int startIndex=0;
    for (int i=1; i<=A.size(); i++) {
      if (i == A.size() // hit the end of list
          || A.get(i) > A.get(i - 1) && subarrayType == SubarrayType.DECREASING
          || A.get(i) <= A.get(i - 1) && subarrayType == SubarrayType.INCREASING) {
        List<Integer> subList = A.subList(startIndex, i);
        if (subarrayType == SubarrayType.DECREASING) {
          Collections.reverse(subList);
        }
        subArrayList.add(subList);
        startIndex = i;
        // must reset sub-array type
        subarrayType = subarrayType==SubarrayType.DECREASING ? SubarrayType.INCREASING
                                                             : SubarrayType.DECREASING;
      }
    }
    return SortedArraysMerge.mergeSortedArrays(subArrayList);
  }

  private enum SubarrayType {
    INCREASING, DECREASING
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
