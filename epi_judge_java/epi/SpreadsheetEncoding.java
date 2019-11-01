package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

/**
 * implement a function that converts a spreadsheet col id to the corresponding integer, with "A" -> 1
 */
public class SpreadsheetEncoding {
  @EpiTest(testfile = "spreadsheet_encoding.tsv")

  public static int ssDecodeColID(final String col) {
    return col.chars().reduce(0, (res, c) -> res * 26 + c - 'A' + 1);
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
