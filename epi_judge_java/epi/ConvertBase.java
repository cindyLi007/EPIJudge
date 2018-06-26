package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ConvertBase {
  @EpiTest(testfile = "convert_base.tsv")

  public static String convertBase(String numAsString, int b1, int b2) {
    boolean neg = numAsString.startsWith("-");
    int numAsInt = numAsString.substring(neg ? 1 : 0)
        .chars()
        .reduce(0, (x, c) -> x * b1 + (Character.isDigit(c) ? c - '0' : c - 'A' + 10));
    StringBuilder sb = new StringBuilder();
    do {
      int num = numAsInt%b2;
      sb.append(num >= 10 ? (char)((num-10)+'A') : (char)(num+'0'));
      numAsInt/=b2;
    } while (numAsInt!=0);
    String res = sb.reverse().toString();
    return neg ? "-"+res : res;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
