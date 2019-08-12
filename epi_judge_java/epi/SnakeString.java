package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SnakeString {
  @EpiTest(testfile = "snake_string.tsv")
  // Time: O(N), Space: O(N)
  public static String snakeString(String s) {
    StringBuilder up = new StringBuilder();
    StringBuilder middle = new StringBuilder();
    StringBuilder lower = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (i%2==0) {
        middle.append(c);
      } else if (i%4==1){
        up.append(c);
      } else { // i%4==3
        lower.append(c);
      }
    }
    return up.append(middle).append(lower).toString();
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
