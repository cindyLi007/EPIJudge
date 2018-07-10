package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class RealSquareRoot {
  @EpiTest(testfile = "real_square_root.tsv")
  // Time: O(logs x), s is the tolerance, Space: O(1)
  public static double squareRoot(double x) {
    // First decide the initial interval, based on x>=1.0
    double low=1.0, high=x;
    if (x<1.0) { // for example, 0.25 sqrt is 0.5
      low=x;
      high=1.0;
    }

    // keeps searching as long as low!=high, within tolerance
    while (compare(low, high)!=Ordering.EQUAL) {
      double mid= low + (high-low)*0.5;
      double midSquared = mid*mid;
      if (compare(midSquared, x)==Ordering.LARGER) {
        high=mid;
      } else {
        low=mid;
      }
    }
    return low;
  }

  private enum Ordering { SMALLER, EQUAL, LARGER }

  private static Ordering compare(double x, double y) {
    // tolerance
    final double EPSILON = 0.000001;
    // Uses normalization for precision problem.
    double diff = (x - y) / Math.max(Math.abs(x), Math.abs(y));
    return diff < -EPSILON ? Ordering.SMALLER :
        (diff > EPSILON) ? Ordering.LARGER : Ordering.EQUAL;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
