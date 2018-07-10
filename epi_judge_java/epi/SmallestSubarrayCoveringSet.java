package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SmallestSubarrayCoveringSet {

  // Represent subarray by starting and ending indices, inclusive.
  private static class Subarray {
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  // Time: O(N), Space: O(M)
  /*public static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph,
                                                         Set<String> keywords) {
    Subarray subarray = new Subarray(-1, -1);
    // build up a keyword-count map, all count is 1
    Map<String, Long> keywordsMap = keywords.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    int count = keywords.size();

    for (int start = 0, i = 0; i < paragraph.size(); i++) {
      String currentWord = paragraph.get(i);
      // first time seen the keyword from start pos
      if (keywordsMap.containsKey(currentWord) && keywordsMap.put(currentWord, keywordsMap.get(currentWord) - 1) == 1) {
        count--;
      }

      while (count==0) {
        if (subarray.start == -1 || subarray.end - subarray.start > i - start) {
          subarray.end= i;
          subarray.start=start;
        }
        String leftWord = paragraph.get(start++);
        if (keywordsMap.containsKey(leftWord) && keywordsMap.put(leftWord, keywordsMap.get(leftWord) + 1) == 0) {
          count++;
        }
      }
    }
    return subarray;
  }*/

  public static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph,
                                                         Set<String> keywords) {
    Subarray subarray = new Subarray(-1, -1);
    // LinkedHashMap can keep the relative order for key words, the first one in the map is the earliest access word in paragraph
    LinkedHashMap<String, Integer> dict = new LinkedHashMap<>(keywords.size(), 1, true);
    int count=keywords.size();

    for (int i = 0; i < paragraph.size(); i++) {
      String word = paragraph.get(i);
      if (keywords.contains(word)) {
        if (!dict.containsKey(word)) {
          count--;
        }
        dict.put(word, i);
        if (count==0) {
          Integer posForFirstEntry = getValueForFirstEntry(dict);
          if (subarray.start == -1 || i - posForFirstEntry < subarray.end - subarray.start) {
            subarray.start = posForFirstEntry;
            subarray.end = i;
          }
        }
      }
    }

    return subarray;
  }

  private static Integer getValueForFirstEntry(LinkedHashMap<String, Integer> map) {
    Integer res = null;
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      // We only need get the first (earliest inserted) entry
      res = entry.getValue();
      break;
    }
    return res;
  }

  @EpiTest(testfile = "smallest_subarray_covering_set.tsv")
  public static int findSmallestSubarrayCoveringSetWrapper(
      TimedExecutor executor, List<String> paragraph, Set<String> keywords)
      throws Exception {
    Set<String> copy = new HashSet<>(keywords);

    Subarray result = executor.run(
        () -> findSmallestSubarrayCoveringSet(paragraph, keywords));

    if (result.start < 0 || result.start >= paragraph.size() ||
        result.end < 0 || result.end >= paragraph.size() ||
        result.start > result.end)
      throw new TestFailure("Index out of range");

    for (int i = result.start; i <= result.end; i++) {
      copy.remove(paragraph.get(i));
    }

    if (!copy.isEmpty()) {
      throw new TestFailure("Not all keywords are in the range");
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
