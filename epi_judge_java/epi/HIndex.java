package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class HIndex {

  /**
   * Average running time:    8 us
   * Median running time:     3 us
   * Time O(N), Space O(N)
   */
  @EpiTest(testfile = "h_index.tsv")
  public static int hIndex_2(List<Integer> citations) {
    int size = citations.size();
    int[] cit = new int[size+1];
    for (int i : citations) {
      if (i>=size) cit[size]++;
      else cit[i]++;
    }
    int res=0;
    for (int i=size; i>=0; i--) {
      res += cit[i];
      if (res>=i) return i;
    }
    return 0;
  }

  /**
   * Average running time:    25 us
   * Median running time:     7 us
   * Time O(NLogN), use time save space
   */
  @EpiTest(testfile = "h_index.tsv")
  public static int hIndex_1(List<Integer> citations) {
    Collections.sort(citations);
    int len = citations.size();
    for (int i = 0; i < len; i++) {
      if (len - i <= citations.get(i))
        return len - i;
    }
    return 0;
  }

  /**
   * Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
   *
   * ANYTIME, see sorted, first consider binary search
   * Average running time:    4 us
   * Median running time:     2 us
   * Time O(NLogN), use time save space
   */
  @EpiTest(testfile = "h_index.tsv")
  public static int hIndex(List<Integer> citations) {
    Collections.sort(citations);
    int len = citations.size();
    int left=0, right=len-1;
    // if use equals, must be right minus 1, left add 1 to avoid unlimited loop
    while (left<=right) {
      int mid = left + (right-left)/2;
      if (len-mid <= citations.get(mid)) right=mid-1;
      else left=mid+1;
    }
    // generally left is included in the final result
    return len-left;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
