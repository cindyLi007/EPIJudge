package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class KthNodeInTree {
  public static class BinaryTreeNode<T> {
    public T data;
    public BinaryTreeNode<T> left, right;
    public int size;

    public BinaryTreeNode(T data, BinaryTreeNode<T> left,
                          BinaryTreeNode<T> right, int size) {
      this.data = data;
      this.left = left;
      this.right = right;
      this.size = size;
    }
  }

  // Time: O(h), Space: O(h)
  public static BinaryTreeNode<Integer>
  findKthNodeBinaryTree_recursive(BinaryTreeNode<Integer> tree, int k) {
    if (tree.size < k) {
      return null;
    }
    if (tree.left == null || tree.left.size < k) {
      // go to right-side
      int leftSize = tree.left == null ? 0 : tree.left.size;
      if (k == leftSize + 1) return tree;
      return findKthNodeBinaryTree(tree.right, k - leftSize - 1);
    } else {
      return findKthNodeBinaryTree(tree.left, k);
    }
  }

  // Time: O(h), Space: O(1)
  public static BinaryTreeNode<Integer>
  findKthNodeBinaryTree(BinaryTreeNode<Integer> tree, int k) {
    while (tree!=null) {
      if (tree.left==null || tree.left.size<k) {
        int leftSize = tree.left == null ? 0 : tree.left.size;
        if (k==1+leftSize) return tree;
        else {
          tree=tree.right;
          k-=leftSize+1;
        }
      } else {
        tree=tree.left;
      }
    }
    return null;
  }

  public static BinaryTreeNode<Integer>
  convertToTreeWithSize(BinaryTree<Integer> original) {
    if (original == null)
      return null;
    BinaryTreeNode<Integer> left = convertToTreeWithSize(original.left);
    BinaryTreeNode<Integer> right = convertToTreeWithSize(original.right);
    int lSize = left == null ? 0 : left.size;
    int rSize = right == null ? 0 : right.size;
    return new BinaryTreeNode<>(original.data, left, right, 1 + lSize + rSize);
  }

  @EpiTest(testfile = "kth_node_in_tree.tsv")
  public static int findKthNodeBinaryTreeWrapper(TimedExecutor executor,
                                                 BinaryTree<Integer> tree,
                                                 int k) throws Exception {
    BinaryTreeNode<Integer> converted = convertToTreeWithSize(tree);

    BinaryTreeNode<Integer> result =
        executor.run(() -> findKthNodeBinaryTree(converted, k));

    if (result == null) {
      throw new TestFailure("Result can't be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
