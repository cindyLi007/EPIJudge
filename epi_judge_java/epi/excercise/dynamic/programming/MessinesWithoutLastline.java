package epi.excercise.dynamic.programming;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * EPI 16.11  Solve the same problem when the messiness is the sum of the messinesses of all but the last line.
 */
public class MessinesWithoutLastline {

  // Time: O(L*N), Space: O(N)
  public static int minimumMessiness(List<String> words, int lineLength) {
    int N = words.size();
    int[] dp = new int[N];
    dp[0] = (int)Math.pow(lineLength - words.get(0).length(), 2);
    int min = 0;

    for (int i=1; i<N; i++) {
      int len = words.get(i).length();
      dp[i] = (int)Math.pow((lineLength - len), 2) + dp[i-1];
      if (i==N-1) {
        min = dp[i-1];
      }
      for (int j=i-1; j>=0 && (len + words.get(j).length() + 1) <= lineLength; j--) {
        len += words.get(j).length() + 1;
        int temp = (int) Math.pow(lineLength - len, 2) + (j>0 ? dp[j - 1] : 0);
        dp[i] = Math.min(dp[i], temp);
        if (i==N-1)
          min = Math.min(min, dp[j-1]);
      }
    }
    return min;
  }

  public static void main(String... args) {
    String s = "I have inserted a large number of new examples from the papers for the Mathematical Tripos during the last twenty years, which should be useful to Cambridge students.";
    String[] array = s.split("\\s+");
    int messiness = minimumMessiness(Arrays.asList(array), 36);
    System.out.println(messiness);
  }
}
