package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

/**
 * Time: O(N), Space: O(1)
 */
public class AdvanceByOffsets {
  @EpiTest(testfile = "advance_by_offsets.tsv")
  public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
    int farest=0, lastIndex = maxAdvanceSteps.size() - 1;
    for (int i = 0; i <= farest && i <= lastIndex; i++) {
      farest = Math.max(farest, i + maxAdvanceSteps.get(i));
      if (farest>=lastIndex)
        return true;
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
