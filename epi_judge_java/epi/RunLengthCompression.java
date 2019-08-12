package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class RunLengthCompression {

  // "3e4f2e" => "eeeffffee"
  // aaaabcccaa" => "4a1b3c2a"
  public static String decoding(String s) {
    if (s.length()==0) return s;
    StringBuilder sb = new StringBuilder();
    int count=0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        count = count * 10 + (c - '0');
      } else {
        for (int j=0; j<count; j++) {
          sb.append(c);
        }
        count=0;
      }
    }
    return sb.toString();
  }

  public static String encoding(String s) {
    if (s.length() == 0) return "";
    StringBuilder sb = new StringBuilder();
    char prev = s.charAt(0);
    int count = 1;
    for (int i = 1; i < s.length(); i++) {
      if (s.charAt(i)==prev) {
        count++;
      } else {
        sb.append(count).append(prev);
        count=1;
        prev = s.charAt(i);
      }
    }
    sb.append(count).append(prev);
    return sb.toString();
  }

  @EpiTest(testfile = "run_length_compression.tsv")
  public static void rleTester(String encoded, String decoded)
      throws TestFailure {
    if (!decoding(encoded).equals(decoded)) {
      throw new TestFailure("Decoding failed");
    }
    if (!encoding(decoded).equals(encoded)) {
      throw new TestFailure("Encoding failed");
    }
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
