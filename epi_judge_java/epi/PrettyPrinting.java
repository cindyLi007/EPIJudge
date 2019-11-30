package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class PrettyPrinting {
  @EpiTest(testfile = "pretty_printing.tsv")
  /**
   * fit from last to start words, for each word, find all possible to fit for one line and pick up the optinum
   * Time: O(L*N), Space: O(N)
   */
  public static int minimumMessiness(List<String> words, int lineLength) {
    int N = words.size();
    int[] dp = new int[N + 1];
    for (int i = 0; i < N; i++) {
      int len = words.get(i).length();
      int temp = (int) Math.pow(lineLength - len, 2) + dp[i];
      for (int j = i - 1; j >= 0 && len + words.get(j).length() + 1 <= lineLength; j--) {
        len += words.get(j).length() + 1;
        temp = Math.min(temp, (int) Math.pow(lineLength - len, 2) + dp[j]);
      }
      dp[i + 1] = temp;
    }
    return dp[N];
  }

  private static int minimumMessiness(List<String> words, int idx, int lineLength, int[] dp) {
    // base case
    if (idx < 0) return 0;

    if (dp[idx] == -1) {
      int len = 0;
      int messiness = Integer.MAX_VALUE;
      for (int i = idx; i >= 0; i--) {
        len += idx == i ? words.get(i).length() : words.get(i).length() + 1;

        if (len <= lineLength) {
          int blank = lineLength - len;
          int currentMessiness = blank * blank;
          int previousLinesMessiness = minimumMessiness(words, i - 1, lineLength, dp);
          messiness = Math.min(currentMessiness + previousLinesMessiness, messiness);
        } else {
          break;
        }
      }
      dp[idx] = messiness;
    }

    return dp[idx];
  }

  public static void main(String[] args) {
    System.exit(GenericTest
        .runFromAnnotations(
            args, new Object() {
            }.getClass().getEnclosingClass())
        .ordinal());
  }
}
