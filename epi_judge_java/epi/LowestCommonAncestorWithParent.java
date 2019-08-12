package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class LowestCommonAncestorWithParent {

  // Time O(h), Space: O(1)
  public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    int depth0 = depthOf(node0);
    int depth1 = depthOf(node1);
    while (depth0>depth1) {
      node0 = node0.parent;
      depth0--;
    }
    while (depth0<depth1) {
      node1 = node1.parent;
      depth1--;
    }
    while (node0!=node1) {
      node0=node0.parent;
      node1=node1.parent;
    }
    return node0;
  }

  // Time: O(h), Space: O(1)
  private static int depthOf(BinaryTree<Integer> node) {
    // no need to set a local var for node, we change node here will not impact outside of thie method
    int depth=0;
    while (node != null) {
      depth++;
      node = node.parent;
    }
    return depth;
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
