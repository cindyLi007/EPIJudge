package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ConvertBase {
  @EpiTest(testfile = "convert_base.tsv")

  public static String convertBase(String numAsString, int b1, int b2) {
    boolean neg = numAsString.startsWith("-");
    int numAsInt = 0;
    int start = neg ? 1 : 0;
    for (; start<numAsString.length(); start++) {
      char c = numAsString.charAt(start);
      int d = Character.isDigit(c) ? c-'0' : (c-'A')+10;
      numAsInt = numAsInt * b1 + d;
    }
    StringBuilder sb = new StringBuilder();
    do {
      int currenntDigit = numAsInt % b2;
      char c = currenntDigit > 9 ? (char)(currenntDigit-10 + 'A') : (char)(currenntDigit + '0');
      sb.append(c);
      numAsInt /= b2;
     } while (numAsInt!=0);
     return (neg ? "-" : "") + sb.reverse().toString();
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
