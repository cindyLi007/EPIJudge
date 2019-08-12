package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class LookAndSay {
  @EpiTest(testfile = "look_and_say.tsv")

  // Time: O(n), Space: O(1)
  public static String lookAndSay(int n) {
    int count = 1;
    String s = "1";
    while (count<n) {
      StringBuilder sb = new StringBuilder();
      int j=0;
      for (int i = 1; i <= s.length(); i++) {
        if (i==s.length() || s.charAt(i) != s.charAt(i-1)) {
          sb.append(i-j).append(s.charAt(i-1));
          j=i;
        }
      }
      s=sb.toString();
      count++;
    }
    return s;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
