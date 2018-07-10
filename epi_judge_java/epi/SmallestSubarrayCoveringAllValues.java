package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

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
    int[][] dp = new int[len][len];
    // dp[i][j] store from paragraph [i, j], cover till index x-th word in keywords
    // first initialize all entries to -1
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }

    for (int end=0; end<len; end++) { // each time move forward one pos in paragraph
      String word = paragraph.get(end);
      for (int start=0; start<=end; start++) {
        if (end==0 || dp[end-1][start]==-1) { // means we need compare first keyword, so we start from this position in paragraph
          if (word.equals(keywords.get(0))) {
            dp[end][start]=0;
          }
        } else { // means from start till end-1, there already cover some keywords, so need compare to the next word
          String expectedWord = keywords.get(dp[end - 1][start] + 1);
          if (expectedWord.equals(word)) {
            dp[end][start] = dp[end - 1][start] + 1;
            if (dp[end][start] == keywords.size() - 1) { // we covers all keywords sequentially
              if (subarray.start == -1 || subarray.end - subarray.start > end - start) {
                subarray.start = start;
                subarray.end = end;
              }
              dp[end][start] = -1; // reset, so next word can start over
            }
          } else {
            dp[end][start] = dp[end - 1][start];
          }
        }
      }
    }
    return subarray;
  }

  public static Subarray findSmallestSequentiallyCoveringSubset(List<String> paragraph, List<String> keywords) {
    Map<String, Integer> keywordToIdx = new HashMap<>();

    // the i-th entry means the i-th keyword appears in paragraph's pos so far
    ArrayList<Integer> latestOccurrence = new ArrayList<>(keywords.size());

    // the i-th entry means the subarray [0, i-th] shortest length
    List<Integer> shortestSubarrayLength = new ArrayList<>(keywords.size());

    // Initialize
    for (int i = 0; i < keywords.size(); i++) {
      latestOccurrence.add(-1);
      shortestSubarrayLength.add(Integer.MAX_VALUE);
      keywordToIdx.put(keywords.get(i), i);
    }

    Subarray subarray = new Subarray(-1, -1);
    for (int i=0; i<paragraph.size(); i++) {
      String word = paragraph.get(i);
      if (keywordToIdx.containsKey(word)) {
        Integer index = keywordToIdx.get(word);
        if (index==0 || latestOccurrence.get(index-1)!=-1) { // from the first keyword
          int distance = index==0 ? 1 : shortestSubarrayLength.get(index-1) + i-latestOccurrence.get(index-1);
          shortestSubarrayLength.set(index, distance);
          latestOccurrence.set(index, i);
        }

        if (index==keywords.size()-1) {
          if (subarray.start == -1 || shortestSubarrayLength.get(index) < subarray.end - subarray.start+1) {
            subarray.start = i-shortestSubarrayLength.get(index)+1;
            subarray.end = i;
          }
        }
      }
    }

    return subarray;
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
