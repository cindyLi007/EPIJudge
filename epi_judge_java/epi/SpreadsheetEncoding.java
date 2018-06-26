package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

/**
 * implement a function that converts a spreadsheet col id to the corresponding integer, with "A" -> 1
 */
public class SpreadsheetEncoding {
  @EpiTest(testfile = "spreadsheet_encoding.tsv")

  public static int ssDecodeColID(final String col) {
    int res =0;
    for (int i=0; i<col.length(); i++) {
      int i1 = (col.charAt(i) - 'A') + 1;
      res = res*26 + i1;
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
