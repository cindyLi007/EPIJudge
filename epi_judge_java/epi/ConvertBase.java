package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ConvertBase {
  @EpiTest(testfile = "convert_base.tsv")

  public static String convertBase(String numAsString, int b1, int b2) {
    boolean neg = numAsString.startsWith("-");
    int n = 0;
    int start = neg ? 1 : 0;
    for (; start<numAsString.length(); start++) {
      char c = numAsString.charAt(start);
      int d = Character.isDigit(c) ? c-'0' : (c-'A')+10;
      n = n * b1 + d;
    }
    StringBuilder sb = new StringBuilder();
    while (n>0) {
      int m = n%b2;
      if (m<10) sb.append(m);
      else sb.append((char)(m-10+'A'));
      n /= b2;
    }
    return (neg ? "-" : "") + (sb.length() ==0 ? "0" : sb.reverse().toString());
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
