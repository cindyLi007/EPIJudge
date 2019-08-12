package epi.excercise.binary.tree;

import epi.BinaryTreeNode;

/**
 * Write a program that returns the size of the largest subtree that is complete.
 */
public class LargestCompleteSubteeSize {
  private static class CompleteStatusWithHeightSize {
    public CompleteStatusWithHeightSize(boolean isComplete, boolean isPerfect, int height, int size) {
      this.isComplete = isComplete;
      this.isPerfect = isPerfect;
      this.height = height;
      this.size = size;
    }

    // whether the tree from root is complete
    public boolean isComplete;
    public boolean isPerfect;
    public int height;
    public int size;
  }

  public static int findLargestCompleteSubtree(BinaryTreeNode<Integer> tree) {
    return checkSubTree(tree).size;
  }

  private static CompleteStatusWithHeightSize checkSubTree(BinaryTreeNode<Integer> tree) {
    if (tree==null) {
      return new CompleteStatusWithHeightSize(true, true, 0, 0);
    }

    CompleteStatusWithHeightSize left = checkSubTree(tree.left);
    CompleteStatusWithHeightSize right = checkSubTree(tree.right);

    // Case 1: if left is perfect and right is complete and their height is same, tree is complete or perfect.
    if (left.isPerfect && right.isComplete && left.height == right.height) { // also include right is perfect
      return new CompleteStatusWithHeightSize(true, right.isPerfect, left.height + 1,
              left.size + right.size + 1);
    }

    // Case 2: if left is not perfect but complete, only right is perfect and 1 less height than left, tree can be complete
    // but not perfect
    if (left.isComplete && right.isPerfect && left.height == right.height + 1) {
      return new CompleteStatusWithHeightSize(true, false, left.height + 1,
              left.size + right.size + 1);
    }

    // Case 3: tree can't be a complete tree
    return new CompleteStatusWithHeightSize(false, false,-1, Math.max(left.size, right.size));
  }
}
