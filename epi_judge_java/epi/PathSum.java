package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

/**
 * Time: O(N), Space: O(h)
 */
public class PathSum {
  @EpiTest(testfile = "path_sum.tsv")

  public static boolean hasPathSum(BinaryTreeNode<Integer> tree,
                                   int remainingWeight) {
    if (tree == null) {
      return false;
    }
    // must have this check to make sure tree is a leaf
    if (tree.left == null && tree.right == null) {
      return remainingWeight - tree.data == 0;
    }
    // non-leaf
    return hasPathSum(tree.left, remainingWeight - tree.data)
        || hasPathSum(tree.right, remainingWeight - tree.data);
  }

  public static void main(String[] args) {
    System.exit(GenericTest
        .runFromAnnotations(
            args, new Object() {
            }.getClass().getEnclosingClass())
        .ordinal());
  }
}
