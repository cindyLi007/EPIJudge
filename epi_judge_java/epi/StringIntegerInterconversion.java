package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class StringIntegerInterconversion {

  /**
   * Time: O(N), space: O(N)
   * @param x
   * @return
   */
  public static String intToString(int x) {
    StringBuilder sb = new StringBuilder();
    boolean neg = x < 0 ? true : false;

    // for x==0, we must first do then while, the "while-do" syntax is wrong
    do {
      // we must not set x=Math.abs(x) because if x == MIN_VALUE, will overflow
      sb.append(Math.abs(x % 10));
      x /= 10;
    } while (x != 0);
    // Adding a digit to the beginning of a string is expensive, so first append then reverse
    String res = sb.reverse().toString();
    return neg ? "-" + res : res;
  }

  public static int stringToInt(String s) {
    int res = 0;
    int start = s.charAt(0) == '-' ? 1 : 0;
    for (int i = start; i < s.length(); i++) {
      int digit = s.charAt(i) - '0';
      // we must keep neg during all parsing to avoid MIN_VALUE overflow
      if (start==1) {
        digit*=-1;
      }
      res = res * 10 + digit;
    }
    return res;
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
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
