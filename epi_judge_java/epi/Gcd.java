package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class Gcd {
  @EpiTest(testfile = "gcd.tsv")
  // *, / and % is disallowed
  // assume x <= y
  public static long GCD(long x, long y) {
    if (x > y) return GCD(y, x);
    if (x == y || x == 0) return y;
    // x < y
    if ((x & 1) == 0 && (y & 1) == 0) { // 2 even number
      return GCD(x >>> 1, y >>> 1) << 1;
    }
    // when one is even, one is odd, appearently 2 could not divide the odd, so 2 is useless, need divide the even one by 2
    else if ((x & 1) == 0 && (y & 1) == 1) { // small is even, large is odd
      return GCD(x >>> 1, y);
    } else if ((y & 1) == 0 && (x & 1) == 1) { // small is odd, large is even
      return GCD(x, y >>> 1);
    }
    return GCD(x, y - x);
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
