package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class ThreeSum {
  @EpiTest(testfile = "three_sum.tsv")

  public static boolean hasThreeSum(List<Integer> A, int t) {
    Collections.sort(A);
    return A.stream().anyMatch(a -> TwoSum.hasTwoSum(A, t - a));
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
