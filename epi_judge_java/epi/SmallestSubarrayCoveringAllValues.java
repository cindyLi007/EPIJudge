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
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }

    for (int end=0; end<len; end++) {
      String word = paragraph.get(end);
      for (int start=0; start<=end; start++) {
        if (end==0 || dp[start][end-1]==-1) {
          if (word.equals(keywords.get(0))) {
            dp[start][end]=0;
          }
        } else {
          String expectedWord = keywords.get(dp[start][end - 1] + 1);
          if (expectedWord.equals(word)) {
            dp[start][end]=dp[start][end-1]+1;
            if (dp[start][end]==keywords.size()-1) {
              if (subarray.start==-1 || subarray.end-subarray.start > end-start) {
                subarray.start = start;
                subarray.end = end;
              }
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
    Subarray subarray = new Subarray(-1, -1);

    Map<String, Integer> keyToIdx = new HashMap<>();
    List<Integer> latestOccurrence = new ArrayList<>(keywords.size());
    List<Integer> shortestDistance = new ArrayList<>(keywords.size());

    for (int i=0; i<keywords.size(); i++) {
      keyToIdx.put(keywords.get(i), i);
      latestOccurrence.add(-1);
      shortestDistance.add(Integer.MAX_VALUE);
    }

    for (int i=0; i<paragraph.size(); i++) {
      String word = paragraph.get(i);
      if (keyToIdx.containsKey(word)) {
        Integer index = keyToIdx.get(word);
        if (index ==0 || latestOccurrence.get(index -1)!=-1) {
          latestOccurrence.set(index, i);
          shortestDistance.set(index, index==0 ? 1 : shortestDistance.get(index-1) + (i-latestOccurrence.get(index-1)));
        }
        if (index==keywords.size()-1) {
          if (subarray.start==-1 || shortestDistance.get(index) < subarray.end-subarray.start+1) {
            subarray.end = i;
            subarray.start = i-shortestDistance.get(index)+1;
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
