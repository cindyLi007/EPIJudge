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
      // since we know [1, maxValue] is consecutive, so [a, maxValue+a] is consecutive, but only when this 2 interval is
      // consecutive, the [1, maxValue+a] is consecutive. We can NOT guarantee that a<=maxValue+1, so we first check a>maxValue
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
