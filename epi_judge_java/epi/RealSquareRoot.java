package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class RealSquareRoot {
  @EpiTest(testfile = "real_square_root.tsv")
  // Time: O(logs x), s is the tolerance, Space: O(1)
  public static double squareRoot(double x) {
    double left, right;
    if (x < 1.0) {
      left = x;
      right = 1.0;
    } else {
      left = 1.0;
      right = x;
    }

    while (compare(left, right) != Ordering.EQUAL) {
      double mid = left + (right - left) * 0.5;
      double res = mid * mid;
      if (compare(res, x) == Ordering.LARGER) {
        right = mid;
      } else left = mid;
    }

    return left;
  }

  private enum Ordering { SMALLER, EQUAL, LARGER }

  private static Ordering compare(double x, double y) {
    double n = 0.000001;
    double m = (x - y) / Math.max(x, y);
    return m < -n ?  Ordering.SMALLER : (m > n ? Ordering.LARGER : Ordering.EQUAL);
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
