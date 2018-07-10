package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestorCloseAncestor {

  // Time: O(D): D is the farthest distance to LCA, Space: O(D0+D1)
  public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    Set<BinaryTree> ancestorSet = new HashSet<>();
    // must use "||" instead of "&&", because when one node hit to root(null), maybe the other node still in lower layer,
    // need "wait" the slower one hit till common ancestor
    while (node0 != null || node1 != null) {
      if (node0 == node1) {
        return node0;
      }
      if (node0 != null) {
        // use "add" result to check whether node is in set, so we need not first use "contains" to check then use "add" to add node to set
        if (!ancestorSet.add(node0)) {
          return node0;
        }
        node0 = node0.parent;
      }
      if (node1 != null) {
        if (!ancestorSet.add(node1)) {
          return node1;
        }
        node1 = node1.parent;
      }
    }
    return null;
  }

  @EpiTest(testfile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> LCA(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
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
