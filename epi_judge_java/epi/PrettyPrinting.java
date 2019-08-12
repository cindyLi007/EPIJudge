package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;

public class PrettyPrinting {
  @EpiTest(testfile = "pretty_printing.tsv")

  /**
   * fit from last to start words, for each word, find all possible to fit for one line and pick up the optinum
   * Time: O(L*N), Space: O(N)
   */
  public static int minimumMessiness(List<String> words, int lineLength) {
    int N = words.size();
    int[] dp = new int[N];

    for (int i = 0; i < N; i++) {
      int remainBlank = lineLength - words.get(i).length();
      int messiness = (i==0 ? 0 : dp[i-1]) + remainBlank * remainBlank;
      for (int j=i-1; j>=0; j--) {
        remainBlank -= words.get(j).length() + 1;
        if (remainBlank<0) break;
        messiness = Math.min(messiness, remainBlank * remainBlank + (j == 0 ? 0 : dp[j - 1]));
      }
      dp[i]=messiness;
    }
    return dp[N-1];
  }

  private static int minimumMessiness(List<String> words, int idx, int lineLength, int[] dp) {
    // base case
    if (idx<0) return 0;

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
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
