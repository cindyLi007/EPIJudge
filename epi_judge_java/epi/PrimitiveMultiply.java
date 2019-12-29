package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PrimitiveMultiply {
  @EpiTest(testfile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
    long res = 0;
    while (x!=0) {
      if ((x&1)==1) {
        res = bitAdd(res, y);
      }
      y <<=1;
      x >>= 1;
    }
    return res;
  }

  private static long bitAdd(long res, long y) {
    while (y!=0) {
      long carry = res & y;
      res ^= y;
      y = carry<<1;
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
