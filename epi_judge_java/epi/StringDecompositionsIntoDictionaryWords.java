package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringDecompositionsIntoDictionaryWords {
  @EpiTest(testfile = "string_decompositions_into_dictionary_words.tsv")

  // Time: O(N*M), Space: O(K), N is len of s, M is sum of len of all words in words = K*n, K is number of word in words
  public static List<Integer> findAllSubstrings(String s, List<String> words) {
    List<Integer> res = new ArrayList<>();
    if (words.size() == 0) return res;

    Map<String, Integer> wordFreq = new HashMap<>();
    words.forEach(w -> wordFreq.put(w, wordFreq.getOrDefault(w, 0) + 1));

    int unitLen = words.get(0).length();
    for (int i=0; i<= s.length() - words.size() * unitLen; i++) {
      if (findSubstring(s.substring(i, i+ words.size() * unitLen), unitLen, wordFreq)) {
        res.add(i);
      }
    }
    return res;
  }

  private static boolean findSubstring(String s, int unitLen, Map<String, Integer> wordFreq) {
    Map<String, Integer> curWordFreq = new HashMap<>();
    for (int i=0; i<=s.length() - unitLen; i+=unitLen) {
      String w = s.substring(i, i + unitLen);
      if (!wordFreq.containsKey(w)) {
        return false;
      }
      curWordFreq.put(w, curWordFreq.getOrDefault(w, 0) + 1);
      if (curWordFreq.get(w) > wordFreq.get(w)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
