package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class SuccessorInTree {

  // Time: O(h), Space: O(1)
  public static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {
    if (node.right != null) {
      node = node.right;
      while (node.left != null) {
        node = node.left;
      }
      return node;
    }

    while (node.parent != null && node.parent.right == node) {
      node = node.parent;
    }
    return node.parent;
  }

  @EpiTest(testfile = "successor_in_tree.tsv")
  public static int findSuccessorWrapper(TimedExecutor executor,
                                         BinaryTree<Integer> tree, int nodeIdx)
      throws Exception {
    BinaryTree<Integer> n = BinaryTreeUtils.mustFindNode(tree, nodeIdx);

    BinaryTree<Integer> result = executor.run(() -> findSuccessor(n));

    return result == null ? -1 : result.data;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
