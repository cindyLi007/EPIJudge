package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

/**
 * EPI 9.5
 * Time: O(N), Space: O(h) for recursive
 */
public class SumRootToLeaf {
  @EpiTest(testfile = "sum_root_to_leaf.tsv")

  public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
    return helper(tree, 0);
  }

  private static int helper(BinaryTreeNode<Integer> node, int upLevelBit) {
    if (node==null) {
      return 0;
    }
      int sum = upLevelBit * 2 + node.data;
    // leaf
    if (node.left == null && node.right == null) {
      return sum;
    }
    // non-leaf
    return helper(node.left, sum) + helper(node.right, sum);
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
