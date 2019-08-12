package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestSubarrayWithDistinctValues {
  @EpiTest(testfile = "longest_subarray_with_distinct_values.tsv")
  // Time: O(N), Space: O(N)
  public static int longestSubarrayWithDistinctEntries(List<Integer> A) {
    if (A.size()==0) {
      return 0;
    }
    Map<Integer, Integer> map = new HashMap<>();
    int res = 1;
    int i=0;
    for (int j = 0; j < A.size(); j++) {
      int val = A.get(j);
      // map.put will return the previous val related to key, or null if no this key before
      Integer idx = map.put(val, j);
      // if current val appears in current subarray
      if (idx !=null && idx >= i) {
        res = Math.max(res, j-i);
        i = idx + 1;
      }
    }
    return Math.max(res, A.size() - i);
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
