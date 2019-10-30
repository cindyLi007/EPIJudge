package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class StringIntegerInterconversion {

  /**
   * Time: O(N), space: O(N)
   *
   * @param x
   * @return
   */
  public static String intToString(int x) {
    boolean neg = x < 0 ? true : false;
    StringBuilder sb = new StringBuilder();
    while (x != 0) {
      sb.append(Math.abs(x % 10));
      x /= 10;
    }
    return sb.length() == 0 ? "0" : (neg ? "-" : "") + sb.reverse().toString();
  }

  public static int stringToInt(String s) {
    int res = 0;
    boolean neg = s.charAt(0) == '-';
    int i = neg ? 1 : 0;
    int signal = neg ? -1 : 1;
    while (i < s.length()) {
      res = res * 10 + (s.charAt(i++) - '0');
    }
    return res * signal;
  }

  @EpiTest(testfile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (!intToString(x).equals(s)) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(GenericTest
        .runFromAnnotations(
            args, new Object() {
            }.getClass().getEnclosingClass())
        .ordinal());
  }
}
