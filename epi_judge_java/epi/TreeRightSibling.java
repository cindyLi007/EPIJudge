package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;

public class TreeRightSibling {
  public static class BinaryTreeNode<T> {
    public T data;
    public BinaryTreeNode<T> left, right;
    public BinaryTreeNode<T> next = null; // Populates this field.

    public BinaryTreeNode(T data) { this.data = data; }
  }

  // 9.16 variant
  public static void constructRightSibling(BinaryTreeNode<Integer> tree) {
    if (tree==null) return;
    construct(tree);
    BinaryTreeNode<Integer> node = tree;
    /*while (node!=null && !isLeaf(node)) {
      BinaryTreeNode<Integer> first = node.left;
      while (node!=null) {
        node.left.right.right = node.right == null ? null : node.right.left;
        node = node.next;
      }
      node = first;
    }*/
    System.out.println();
  }

  private static boolean isLeaf(BinaryTreeNode<Integer> node) {
    return node.left == null;
  }

  private static void construct(BinaryTreeNode<Integer> tree) {
    if (tree.left==null && tree.right==null) return;
    construct(tree.right);
    construct(tree.left);
    tree.left.right = tree.right;
    tree.right = null;
    if (!isLeaf(tree.left)) connect(tree.left, tree.left.right);
  }

  private static void connect(BinaryTreeNode left, BinaryTreeNode right) {
    left.left.right.right = right.left;
  }

  // Time: O(N), Space: O(1)
  public static void constructRightSibling_1(BinaryTreeNode<Integer> tree) {
    while (tree!=null) {
      BinaryTreeNode<Integer> temp = tree;
      while (tree!=null && tree.left!=null) {
        tree.left.next = tree.right;
        tree.right.next = tree.next != null ? tree.next.left : null;
        tree = tree.next;
      }
      tree = temp.left;
    }
  }

  private static BinaryTreeNode<Integer>
  cloneTree(BinaryTree<Integer> original) {
    if (original == null) {
      return null;
    }
    BinaryTreeNode<Integer> cloned = new BinaryTreeNode<>(original.data);
    cloned.left = cloneTree(original.left);
    cloned.right = cloneTree(original.right);
    return cloned;
  }

  @EpiTest(testfile = "tree_right_sibling.tsv")
  public static List<List<Integer>>
  constructRightSiblingWrapper(TimedExecutor executor, BinaryTree<Integer> tree)
      throws Exception {
    BinaryTreeNode<Integer> cloned = cloneTree(tree);

    executor.run(() -> constructRightSibling(cloned));

    List<List<Integer>> result = new ArrayList<>();
    BinaryTreeNode<Integer> levelStart = cloned;
    while (levelStart != null) {
      List<Integer> level = new ArrayList<>();
      BinaryTreeNode<Integer> levelIt = levelStart;
      while (levelIt != null) {
        level.add(levelIt.data);
        levelIt = levelIt.next;
      }
      result.add(level);
      levelStart = levelStart.left;
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
