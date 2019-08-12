package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ClosestIntSameWeight {
  @EpiTest(testfile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long x) {
    for (int i=0; i<62; i++) {
      if (((x >>> i) & 1) != ((x >>> (i + 1)) & 1)) {
        long mask = 1<<i | 1<<i+1;
        return x ^= mask;

      }
    }
    throw new IllegalArgumentException("All bits are 0 or 1");
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
