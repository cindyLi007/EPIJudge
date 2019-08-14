package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BstFromPreorder {
  private static int rootIdx = 0;
  @EpiTest(testfile = "bst_from_preorder.tsv")

  public static BstNode<Integer>
  rebuildBSTFromPreorder(List<Integer> preorderSequence) {
    rootIdx = 0;
    return rebuildBSTFromPreorder(preorderSequence, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static BstNode<Integer> rebuildBSTFromPreorder(List<Integer> preorderSequence, int minValue, int maxValue) {
    if (rootIdx >= preorderSequence.size()) return null;
    Integer root = preorderSequence.get(rootIdx);
    /**
     * Everytime define the subtree range, if the next value out of the range, that means it is not in the
     * subtree.
     */
    if (root <= minValue || root >= maxValue) return null;
    rootIdx++;
    // Note that in this method we update global var rootIdx, so the order of following 2 calls are critical
    BstNode<Integer> left = rebuildBSTFromPreorder(preorderSequence, minValue, root);
    BstNode<Integer> right = rebuildBSTFromPreorder(preorderSequence, root, maxValue);
    return new BstNode<>(root, left, right);
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
