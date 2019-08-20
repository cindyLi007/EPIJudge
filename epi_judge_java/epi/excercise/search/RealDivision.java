package epi.excercise.search;

/**
 * Given 2 positive floating point numbers x and y, how would you compute x/y to within a specified tolerance if the
 * division operation cannot be used? You cannot uee any library function, only addition and multiplication are acceptable.
 */
public class RealDivision {
  // Time: O(lg Double.MAX_VALUE/s), s is the tolerance, Space: O(1)
  public static double fineRealDivision(double x, double y) {
    if (Double.compare(x, y) == 0) return 1.0;
    double low, high;
    if (x<y) {
      low = Double.MIN_VALUE;
      high = 1.0;
    } else {
      low = 1.0;
      high = y >= 1.0 ? x : Double.MAX_VALUE;
    }

    while (compare(low, high) != Ordering.EQUAL) {
      double mid = low + (high-low) * 0.5;
      double res = mid * y;
      if (compare(res, x) == Ordering.SMALLER) low = mid;
      else high = mid;
    }

    return low;
  }

  private enum Ordering {
    SMALLER, EQUAL, LARGER
  }

  private static Ordering compare(double x, double y) {
    // tolerance
    double X = 0.000001;
    double Y = (x-y) / Math.max(Math.abs(x), Math.abs(y));
    return Y < -X ? Ordering.SMALLER : Y>X ? Ordering.LARGER : Ordering.EQUAL;
  }

  public static void main(String... args) {
    double x = 0.25;
    double y = 5;
    double divisionResult = RealDivision.fineRealDivision(x, y);

    System.out.println(x + "/" + y + "= " + divisionResult);
  }
}
