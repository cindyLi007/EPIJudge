package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TreePreorder {
  @EpiTest(testfile = "tree_preorder.tsv")

  public static List<Integer> preorderTraversal(BinaryTreeNode<Integer> tree) {
    List<Integer> list = new ArrayList<>();
    Deque<BinaryTreeNode<Integer>> stack = new ArrayDeque<>();

    while (tree!=null || !stack.isEmpty()) {
      if (tree!=null) {
        list.add(tree.data);
        stack.push(tree);
        tree=tree.left;
      } else {
        BinaryTreeNode<Integer> top = stack.pop();
        tree=top.right;
      }
    }
    return list;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
