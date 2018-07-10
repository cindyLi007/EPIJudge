package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// Time: O(n), Space: O(h)
public class TreeInorder {
  @EpiTest(testfile = "tree_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
    List<Integer> res = new ArrayList<>();
    Deque<BinaryTreeNode<Integer>> stack = new ArrayDeque<>();

    while (tree!=null || !stack.isEmpty()) {
      if (tree == null) {
        BinaryTreeNode<Integer> popNode = stack.pop();
        res.add(popNode.data);
        tree = popNode.right;
      } else {
        stack.push(tree);
        tree = tree.left;
      }
    }
    return res;
  }

  private static void inorder(BinaryTreeNode<Integer> tree, List<Integer> res) {
    if (tree==null) {
      return;
    }
    inorder(tree.left, res);
    res.add(tree.data);
    inorder(tree.right, res);
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
