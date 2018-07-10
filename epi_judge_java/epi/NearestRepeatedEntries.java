package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearestRepeatedEntries {
  @EpiTest(testfile = "nearest_repeated_entries.tsv")

  // Time: O(N), Space: O(d) d is the distinct entries in array
  public static int findNearestRepetition(List<String> paragraph) {
    int res = paragraph.size();
    Map<String, Integer> wordDistanceMap = new HashMap<>();
    for (int i=0; i<paragraph.size(); i++) {
      String word = paragraph.get(i);
      if (wordDistanceMap.containsKey(word)) {
        res = Math.min(res, i - wordDistanceMap.get(word));
      }
      wordDistanceMap.put(word, i);
    }
    return res == paragraph.size() ? -1 : res;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
