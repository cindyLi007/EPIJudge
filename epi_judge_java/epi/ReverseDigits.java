package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseDigits {
  @EpiTest(testfile = "reverse_digits.tsv")
  // Time: O(n), n is the number of digit in x
  public static long reverse(int x) {
    int y = x < 0 ? Math.abs(x) : x;
    long res = 0;
    while (y > 0) {
      res = res * 10 + y % 10;
      y /= 10;
    }
    return x < 0 ? -res : res;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
