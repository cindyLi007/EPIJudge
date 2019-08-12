package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class LowestCommonAncestor {

  public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree,
                                            BinaryTreeNode<Integer> node0,
                                            BinaryTreeNode<Integer> node1) {
    return LcaHelper(tree, node0, node1).ancestor;
  }

  private static Status LcaHelper(BinaryTreeNode<Integer> tree, BinaryTreeNode<Integer> node0, BinaryTreeNode<Integer> node1) {
    if (tree==null) {
      return new Status(0, tree);
    }

    Status leftStatus = LcaHelper(tree.left, node0, node1);
    if (leftStatus.numberOfNodes==2) {
      return leftStatus;
    }

    Status rightStatus = LcaHelper(tree.right, node0, node1);
    if (rightStatus.numberOfNodes==2) {
      return rightStatus;
    }

    // must check tree with node0 and node1 both, because it can be node0 == node1
    int numberOfNodes = leftStatus.numberOfNodes + rightStatus.numberOfNodes +
        (tree == node0 ? 1 : 0) + (tree == node1 ? 1 : 0);
    return new Status(numberOfNodes, numberOfNodes == 2 ? tree : null);
  }

  private static class Status {
    int numberOfNodes;
    BinaryTreeNode<Integer> ancestor;

    public Status(int numberOfNodes, BinaryTreeNode<Integer> ancestor) {
      this.numberOfNodes = numberOfNodes;
      this.ancestor = ancestor;
    }
  }

  @EpiTest(testfile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor,
                               BinaryTreeNode<Integer> tree, Integer key0,
                               Integer key1) throws Exception {
    BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTreeNode<Integer> result =
        executor.run(() -> LCA(tree, node0, node1));

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
