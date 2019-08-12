package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmallestSubarrayCoveringAllValues {

  public static class Subarray {
    // Represent subarray by starting and ending indices, inclusive.
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  // Time: O(N*N), Space: O(N*N)
  public static Subarray findSmallestSequentiallyCoveringSubset_dp(List<String> paragraph, List<String> keywords) {
    Subarray subarray = new Subarray(-1, -1);
    int len = paragraph.size();
    /*
      stores the up-to-now keyword idx in dp, [i][j] denotes the in sub string list paragraph [i, j], when go to next word in paragraph, we know we should look for which word in keywords
     */
    int[][] dp = new int[len][len];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }

    for (int end=0; end<len; end++) {
      String word = paragraph.get(end);
      for (int start=0; start<=end; start++) {
        if (end==0 || dp[start][end-1]==-1) { // we have not find any keyword
          if (word.equals(keywords.get(0))) {
            dp[start][end]=0;
          }
        } else { // we already find some keywords in previous words in paragraph
          String expectedWord = keywords.get(dp[start][end - 1] + 1);
          if (expectedWord.equals(word)) {
            dp[start][end]=dp[start][end-1]+1;
            if (dp[start][end]==keywords.size()-1) {
              if (subarray.start==-1 || subarray.end-subarray.start > end-start) {
                subarray.start = start;
                subarray.end = end;
              }
              // set dp[start][end] to -1 because we already run out of keywords, end is the so-far shortest, we should start over a new one
              dp[start][end]=-1;
            }
          } else {
            dp[start][end]=dp[start][end-1];
          }
        }
      }
    }

    return subarray;
  }

  // Time: O(N) N is the number of paragraph, Space: O(m) m is the number of keywords
  public static Subarray findSmallestSequentiallyCoveringSubset(List<String> paragraph, List<String> keywords) {
    int N = keywords.size();
    int min = Integer.MAX_VALUE, left = -1, right = -1;

    Map<String, Integer> keyIdxMap = new HashMap();
    int[] shortestDistance = new int[N];
    int[] lastAppear = new int[N];
    for (int i = 0; i < N; i++) {
      keyIdxMap.put(keywords.get(i), i);
      shortestDistance[i] = -1;
      lastAppear[i] = -1;
    }

    for (int i = 0; i<paragraph.size(); i++) {
      String word = paragraph.get(i);
      if (keywords.contains(word)) {
        // check no blockage
        int idx = keyIdxMap.get(word);
        if (idx == 0 || shortestDistance[idx-1]!=-1) {
          shortestDistance[idx] = idx==0 ? 1 : shortestDistance[idx-1] + i - lastAppear[idx-1];
        }
        lastAppear[idx] = i;
        if (idx == N-1 && shortestDistance[idx]!=-1) {
          if (shortestDistance[idx]<min) {
            min = shortestDistance[idx];
            right = i;
            left = i - shortestDistance[idx] + 1;
          }
        }
      }
    }

    return new Subarray(left, right);
  }

    @EpiTest(testfile = "smallest_subarray_covering_all_values.tsv")
  public static int findSmallestSequentiallyCoveringSubsetWrapper(
      TimedExecutor executor, List<String> paragraph, List<String> keywords)
      throws Exception {
    Subarray result = executor.run(
        () -> findSmallestSequentiallyCoveringSubset(paragraph, keywords));

    int kwIdx = 0;
    if (result.start < 0) {
      throw new TestFailure("Subarray start index is negative");
    }
    int paraIdx = result.start;

    while (kwIdx < keywords.size()) {
      if (paraIdx >= paragraph.size()) {
        throw new TestFailure("Not all keywords are in the generated subarray");
      }
      if (paraIdx >= paragraph.size()) {
        throw new TestFailure("Subarray end index exceeds array size");
      }
      if (paragraph.get(paraIdx).equals(keywords.get(kwIdx))) {
        kwIdx++;
      }
      paraIdx++;
    }
    return result.end - result.start + 1;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
