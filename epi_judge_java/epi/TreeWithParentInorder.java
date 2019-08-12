package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class TreeWithParentInorder {
  @EpiTest(testfile = "tree_with_parent_inorder.tsv")

  // Time: O(N), Space: O(1)
  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
    List<Integer> list = new ArrayList<>();
    if (tree == null) {
      return list;
    }
    BinaryTree<Integer> prev = null, cur = tree;

    /* while (cur.left != null) {
      cur = cur.left;
    }
    while (cur != null) {
      list.add(cur.data);
      cur = SuccessorInTree.findSuccessor(cur);
    }*/
    while (cur != null) {
      BinaryTree<Integer> next;
      if (cur.parent == prev) {
        if (cur.left != null) {
          next = cur.left;
        } else {
          list.add(cur.data);
          if (cur.right != null) {
            next = cur.right;
          } else {
            next = cur.parent;
          }
        }
      } else {
        if (cur.left == prev) {
          list.add(cur.data);
          if (cur.right != null) {
            next = cur.right;
          } else {
            next = cur.parent;
          }
        } else next = cur.parent;
      }

      prev = cur;
      cur = next;
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
