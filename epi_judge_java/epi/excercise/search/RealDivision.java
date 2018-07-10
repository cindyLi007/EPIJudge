package epi.excercise.search;

/**
 * Given 2 positive floating point numbers x and y, how would you compute x/y to within a specified tolerance if the
 * division operation cannot be used? You cannot uee any library function, only addition and multiplication are acceptable.
 */
public class RealDivision {
  // Time: O(logs Double.MAX_VALUE), s is the tolerance, Space: O(1)
  public static double fineRealDivision(double x, double y) {
    if (x==y) return 1.0;

    // first decide the initial interval, based on x>y
    double low, high;
    if (x > y) {
      low = 1.0;
      high = y >= 1.0 ? x : Double.MAX_VALUE;
    } else {
      low = Math.abs(Double.MIN_VALUE);
      high = 1.0;
    }

    while (compare(low, high) != Ordering.EQUAL) {
      double mid = low + (high - low) * 0.5;
      double multiple = mid * y;
      if (compare(multiple, x)==Ordering.LARGER) {
        high=mid;
      } else {
        low=mid;
      }
    }

    return low;
  }

  private enum Ordering {
    SMALLER, EQUAL, LARGER
  }

  private static Ordering compare(double x, double y) {
    // tolerance
    final double EPSILON = 0.000001;
    double diff = (x - y) / Math.max(Math.abs(x), Math.abs(y));
    return diff<-EPSILON ? Ordering.SMALLER :
        diff>EPSILON ? Ordering.LARGER : Ordering.EQUAL;
  }

  public static void main(String... args) {
    double x = 0.25;
    double y = 0.5;
    double divisionResult = RealDivision.fineRealDivision(x, y);

    System.out.println(x + "/" + y + "= " + divisionResult);
  }
}
