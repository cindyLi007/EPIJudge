package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class SmallestNonconstructibleValue {
  @EpiTest(testfile = "smallest_nonconstructible_value.tsv")

  // Time: O(NlgN), Space: O(1)
  public static int smallestNonconstructibleValue(List<Integer> A) {
    Collections.sort(A);
    int maxValue = 0;
    for (Integer a : A) {
      if (a>maxValue+1) {
        break;
      }
      // in this case a <=maxValue + 1, so a must connect maxValue
      // since we know [1, maxValue] is consecutive, so [a, maxValue+a] is consecutive
      // since A is sorted maxValue+a must be the next Max Value
      maxValue += a;
    }
    return maxValue+1;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
