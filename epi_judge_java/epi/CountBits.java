package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class CountBits {

  @EpiTest(testfile = "count_bits.tsv")
  public static short countBits(int x) {
    short result =0;
    while (x>0) {
      result++;
      x &= (x-1);
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
