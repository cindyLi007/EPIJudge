package epi.excercise.binary.tree;

import epi.BinaryTreeNode;

/**
 * Write a program that returns the size of the largest subtree that is complete.
 */
public class LargestCompleteSubteeSize {
  private static class CompleteStatusWithHeightSize {
    public CompleteStatusWithHeightSize(boolean isComplete, int height, int size) {
      this.isComplete = isComplete;
      this.height = height;
      this.size = size;
    }

    // whether the tree from root is complete
    public boolean isComplete;
    public int height;
    public int size;
  }

  public static int findLargestCompleteSubtree(BinaryTreeNode<Integer> tree) {
    return checkSubTree(tree).size;
  }

  private static CompleteStatusWithHeightSize checkSubTree(BinaryTreeNode<Integer> tree) {
    if (tree==null) {
      return new CompleteStatusWithHeightSize(true, 0, 0);
    }

    CompleteStatusWithHeightSize left = checkSubTree(tree.left);
    CompleteStatusWithHeightSize right = checkSubTree(tree.right);

    // Only when left and right are both complete, we need check whether tree is complete
    if (left.isComplete && right.isComplete) {
      boolean isComplete = left.height - right.height <= 1 && left.height - right.height >=0;
      if (isComplete) {
        int height = left.height + 1;
        int size = left.size + right.size + 1;
        return new CompleteStatusWithHeightSize(true, height, size);
      } else {
      return new CompleteStatusWithHeightSize(false, -1, Math.max(left.size, right.size));
      }
    } else { // any subtree is not complete, tree could not be a complete tree, so only return the largest size of subtree
      return new CompleteStatusWithHeightSize(false, -1, Math.max(left.size, right.size));
    }
  }
}
