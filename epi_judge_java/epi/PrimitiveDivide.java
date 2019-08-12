package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PrimitiveDivide {
  @EpiTest(testfile = "primitive_divide.tsv")
  public static int divide(int x, int y) {
    int res = 0;
    int power = 32;
    long yPower = y << power;
    while (x >= y) {
      while (yPower > x) {
        yPower >>>= 1;
        power--;
      }
      res += 1 << power;
      x -= yPower;
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
