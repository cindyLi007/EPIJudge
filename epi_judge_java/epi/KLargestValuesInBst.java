package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class KLargestValuesInBst {
  @EpiTest(testfile = "k_largest_values_in_bst.tsv")

  // Time: O(h+k), Space: O(h)
  public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
    List<Integer> res = new ArrayList<>();
    findKLargestInBst(tree, k, res);
    return res;
  }

  private static void findKLargestInBst(BstNode<Integer> tree, int k, List<Integer> res) {
    if (tree != null && res.size() < k) {
      findKLargestInBst(tree.right, k, res);
      if (res.size() < k) {
        res.add(tree.data);
        findKLargestInBst(tree.left, k, res);
      }
    }
  }

  @EpiTestComparator
  public static BiPredicate<List<Integer>, List<Integer>> comp =
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
