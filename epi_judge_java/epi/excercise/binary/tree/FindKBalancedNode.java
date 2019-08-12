package epi.excercise.binary.tree;

import epi.BinaryTreeNode;

/**
 * Define a node is a binary tree to be k-balanced if the diff in the # of nodes in its left and right subtrees is no
 * more than k (<=k). Design an algorithm that takes as input a binary and positive inter k, and returns a node in the
 * binary tree such that the node is not k-balance but all of its descendants are k-balanced.
 */
public class FindKBalancedNode {
  /**
   * When loop a Binary tree, sometimes we want to record more than 1 information, is that case, we can create a static
   * class to carry as much as possible information.
   */
  private static class BalanceStatusWithHeight {
    public boolean isKBalanced;
    public int size;
    public BinaryTreeNode<Integer> node;

    public BalanceStatusWithHeight(boolean isKBalanced, int size, BinaryTreeNode<Integer> node) {
      this.isKBalanced = isKBalanced;
      this.size = size;
      this.node = node;
    }
  }

  public static BinaryTreeNode<Integer> findKBalancedNode(BinaryTreeNode<Integer> root, int k) {
    return checkKBalance(root, k).node;
  }

  private static BalanceStatusWithHeight checkKBalance(BinaryTreeNode<Integer> tree, int k) {
    if (tree == null) {
      return new BalanceStatusWithHeight(true, 0, tree);
    }

    BalanceStatusWithHeight left = checkKBalance(tree.left, k);
    BalanceStatusWithHeight right = checkKBalance(tree.right, k);

    if (!left.isKBalanced) {
      return left;
    }

    if (!right.isKBalanced) {
      return right;
    }

    // now we know both left and right are K-balanced, which mean all their subtree are K-balanced.
    boolean isKBalanced = Math.abs(left.size - right.size) <= k;
    int size = isKBalanced ? left.size + right.size + 1 : -1;
    BinaryTreeNode<Integer> res = isKBalanced ? tree : left.node;
    return new BalanceStatusWithHeight(isKBalanced, size, res);
  }
}
