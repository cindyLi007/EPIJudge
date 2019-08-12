package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class LongestContainedInterval {
  @EpiTest(testfile = "longest_contained_interval.tsv")
  // Time: O(N) we only process each item once, Space: O(N)
  public static int longestContainedRange(List<Integer> A) {
    // remove all duplicated entries
    HashSet<Integer> set = new HashSet<>(A);
    int res = 0;
    while (!set.isEmpty()) {
      // must create a iterator each time, because the set keep changing
      Iterator<Integer> iterator = set.iterator();
      int val = iterator.next();
      int lowerBound = val - 1;
      while (set.contains(lowerBound)) {
        set.remove(lowerBound);
        lowerBound--;
      }
      int upperBound = val+1;
      while (set.contains(upperBound)) {
        set.remove(upperBound);
        upperBound++;
      }
      res = Math.max(res, upperBound - lowerBound - 1);
      // must remove val since we have loop it already, to avoid TLE
      set.remove(val);
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
