package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;

public class SearchFrequentItems {

  public static List<String> searchFrequentItems(int k,
                                                 Iterable<String> stream) {
    Map<String, Integer> map = new HashMap<>();
    Iterator<String> it = stream.iterator();
    int count = 0;
    while (it.hasNext()) {
      String s = it.next();
      count++;
      map.put(s, map.getOrDefault(s, 0) + 1);
      Set<String> delKey = new HashSet<>();
      if (map.size() == k) {
        for (String key : map.keySet()) {
          if (map.get(key) == 1) {
            delKey.add(key);
          } else {
            map.put(key, map.get(key) - 1);
          }
        }
        for (String key : delKey) {
          map.remove(key);
        }
      }
    }
    for (String key : map.keySet()) {
      map.put(key, 0);
    }
    it = stream.iterator();
    while (it.hasNext()) {
      String s = it.next();
      if (map.containsKey(s)) {
        map.put(s, map.get(s) + 1);
      }
    }

    double threshold = (double) count / k;
    List<String> list = new ArrayList<>();
    for (String key : map.keySet()) {
      if (threshold < map.get(key)) {
        list.add(key);
      }
    }

    return list;
  }

  @EpiTest(testfile = "search_frequent_items.tsv")
  public static List<String> searchFrequentItemsWrapper(int k,
                                                        List<String> stream) {
    return searchFrequentItems(k, stream);
  }

  @EpiTestComparator
  public static BiPredicate<List<String>, List<String>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
