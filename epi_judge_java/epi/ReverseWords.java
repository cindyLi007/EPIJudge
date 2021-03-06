package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class ReverseWords {

  // Time: O(N), Space: O(1)
  public static void reverseWords(char[] input) {
    reverse(input, 0, input.length - 1);
    int N = input.length, start = 0, end =0;
    while (start < N) {
      // skip all space char, stop in the 1st non-space pos
      while (start < N && input[start]==' ') start++;

      // skip all non-space char, stop in the 1st space pos which after start
      while (end < start || end < N && input[end]!=' ') end++;

      if (start < N) {
        reverse(input, start, end-1);
        // DO NOT forget update start to next char after blank
        start = end+1;
      }
    }
  }

  private static void reverse(char[] input, int start, int end) {
    while (start < end) {
      char temp = input[start];
      input[start++] = input[end];
      input[end--] = temp;
    }
  }

  @EpiTest(testfile = "reverse_words.tsv")
  public static String reverseWordsWrapper(TimedExecutor executor, String s)
      throws Exception {
    char[] sCopy = s.toCharArray();

    executor.run(() -> reverseWords(sCopy));

    return String.valueOf(sCopy);
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
