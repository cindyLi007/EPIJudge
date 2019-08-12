package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestNondecreasingSubsequence {
  @EpiTest(testfile = "longest_nondecreasing_subsequence.tsv")

  // Time: O(N*N), Space: O(N)
  public static int longestNondecreasingSubsequenceLength(List<Integer> A) {
    List<Integer> dp = new ArrayList<>(A.size());
    dp.add(0);

    for (int i=1; i<A.size(); i++) {
      dp.add(0);
      for (int j=0; j<i; j++) {
        if (A.get(j)<=A.get(i) && dp.get(j) >= dp.get(i)) {
          dp.set(i, dp.get(j)+1);
        }
      }
    }
    return Collections.max(dp) + 1;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
