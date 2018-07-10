package epi.excercise.hash.tables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Design an O(nm) (n is # of Strings, m is the max string length) algorithm to return groups of anagrams for all words
 */
public class AnagramsSet {

  public static List<List<String>> findAnagrams(List<String> dictionary) {
    Map<String, List<String>> map = new HashMap<>();
    for (String word : dictionary) {
      int keyVal = word.chars().reduce(0, (val, c) -> val + c);
      String key = String.valueOf(keyVal) + "_" + word.length();
      map.putIfAbsent(key, new ArrayList<>());
      map.get(key).add(word);
    }
    return map.values().stream().filter(group -> group.size() >= 2).collect(Collectors.toList());
  }

  public static void main(String... args) {
    List<String> dictionary = Arrays.asList("debitcard", "elvis", "silent", "badcredit", "lives", "freedom", "listen", "levis", "money");
    List<List<String>> anagrams = findAnagrams(dictionary);

    anagrams.stream().forEach(System.out::print);
  }
}
