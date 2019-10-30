package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class LookAndSay {
  @EpiTest(testfile = "look_and_say.tsv")

  // Time: O(n*2^n), Space: O(1)
  public static String lookAndSay(int n) {
    String s = "1";
    for (int i = 2; i <= n; i++) {
      StringBuilder sb = new StringBuilder();
      int count = 1;
      char c = s.charAt(0);
      for (int j = 1; j <= s.length(); j++) {
        if (j == s.length()) sb.append(count).append(c);
        else {
          if (s.charAt(j) == s.charAt(j - 1)) count++;
          else {
            sb.append(count).append(c);
            c = s.charAt(j);
            count = 1;
          }
        }
      }
      s = sb.toString();
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
