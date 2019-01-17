package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class Combinations {
  @EpiTest(testfile = "combinations.tsv")
  // Time: O(n!), Space: O(k) for recursive
  public static List<List<Integer>> combinations(int n, int k) {
    List<List<Integer>> res = new ArrayList<>();
    combinations(n, k, 1, new ArrayList<Integer>(), res);
    return res;
  }

  private static void combinations(int n, int k, int start, ArrayList<Integer> list, List<List<Integer>> res) {
    if (list.size() == k) {
      res.add(new ArrayList<>(list));
    } else {
      int remaining = k- list.size();
      for (int i=start; i<=n && remaining <= n-i+1; i++) {
        list.add(i);
        combinations(n, k, i + 1, list, res);
        list.remove(list.size() - 1);
      }
    }
  }

  @EpiTestComparator
      public static BiPredicate < List<List<Integer>>,
      List < List<Integer>>> comp = (expected, result) -> {
    if (result == null) {
      return false;
    }
    expected.sort(new LexicographicalListComparator<>());
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
