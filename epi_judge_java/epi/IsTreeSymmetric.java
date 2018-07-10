package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

/**
 * Time O(n), Space O(h).
 * n is # of nodes, h is the height of tree
 */
public class IsTreeSymmetric {
  @EpiTest(testfile = "is_tree_symmetric.tsv")

  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    if (tree==null) return true;
    return isSymmetric(tree.left, tree.right);
  }

  private static boolean isSymmetric(BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right) {
    if (left==null) return right==null;
    if (right==null || left.data != right.data) return false;

    return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
