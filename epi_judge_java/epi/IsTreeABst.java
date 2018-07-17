package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeABst {
  @EpiTest(testfile = "is_tree_a_bst.tsv")

  // Time: O(N), Space: O(h) h is the height of the tree
  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    return isBinaryTreeBST(tree, Integer.MAX_VALUE, Integer.MIN_VALUE);
  }

  private static boolean isBinaryTreeBST(BinaryTreeNode<Integer> root, int max, int min) {
    if (root == null) return true;
    if (Integer.compare(min, root.data) > 0 || Integer.compare(max, root.data) < 0) {
      return false;
    }
    return isBinaryTreeBST(root.left, root.data, min) && isBinaryTreeBST(root.right, max, root.data);
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
