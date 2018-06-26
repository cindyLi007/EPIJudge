package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeBalanced {

  @EpiTest(testfile = "is_tree_balanced.tsv")

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    if (heightOfTree(tree)>=0) return true;
    return false;
  }

  public static int heightOfTree(BinaryTreeNode<Integer> tree) {
    if (tree==null) {
      return 0;
    }

    int left = heightOfTree(tree.left);
    if (left==-1) {
      return -1;
    }
    int right = heightOfTree(tree.right);
    if (right==-1) {
      return -1;
    }
    if (Math.abs(left-right) > 1) {
      return -1;
    }
    else return Math.max(left, right)+1;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
