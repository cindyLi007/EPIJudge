package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

//
public class Permutations {
  @EpiTest(testfile = "permutations.tsv")

  // Time: O(n*n!), Space: O(n)
  public static List<List<Integer>> permutations(List<Integer> A) {
    List<List<Integer>> res = new ArrayList<>();
    permutations(A, 0, res);
    return res;
  }

  // Compute for list A, from startIdx to A.size()-1 all permutation
  private static void permutations(List<Integer> A, int startIdx, List<List<Integer>> res) {
    if (startIdx == A.size() - 1) {
      res.add(new ArrayList<>(A));
    } else {
      for (int i=startIdx; i<A.size(); i++) {
        Collections.swap(A, startIdx, i);
        // A is the swapped list, which is unique
        permutations(A, startIdx+1, res);
        Collections.swap(A, startIdx, i);
      }
    }
  }

  @EpiTestComparator
      public static BiPredicate < List<List<Integer>>,
      List < List<Integer>>> comp = (expected, result) -> {
    if (result == null) {
      return false;
    }
    for (List<Integer> l : expected) {
      Collections.sort(l);
    }
    expected.sort(new LexicographicalListComparator<>());
    for (List<Integer> l : result) {
      Collections.sort(l);
    }
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
