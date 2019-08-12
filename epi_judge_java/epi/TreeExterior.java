package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;

public class TreeExterior {

  // Time: O(n)
  public static List<BinaryTreeNode<Integer>> exteriorBinaryTree(BinaryTreeNode<Integer> tree) {
    List<BinaryTreeNode<Integer>> res = new ArrayList<>();
    if (tree != null) {
      left(tree.left, res);
      leaf(tree, res);
      right(tree.right, res);
      //findNodesInLeftEdgeAndLeaveInLeftSubTree(tree.left, true, res);
      //findLeaveInRightSubTreeAndNodesInRightEdge(tree.right, true, res);
    }
    if (tree!=null && !isLeaf(tree)) res.add(0, tree);
    return res;
  }

  private static void leaf(BinaryTreeNode<Integer> tree, List<BinaryTreeNode<Integer>> res) {
    if (tree==null) return;
    if (isLeaf(tree)) res.add(tree);
    else {
      leaf(tree.left, res);
      leaf(tree.right, res);
    }
  }

  private static void left(BinaryTreeNode<Integer> tree, List<BinaryTreeNode<Integer>> res) {
    if (tree!=null && !isLeaf(tree)) {
      res.add(tree);
      if (tree.left!=null) {
        left(tree.left, res);
      } else {
        left(tree.right, res);
      }
    }
  }

  private static void right(BinaryTreeNode<Integer> tree, List<BinaryTreeNode<Integer>> res) {
    if (tree!=null) {
      if (!isLeaf(tree)) {
        if (tree.right!=null) right(tree.right, res);
        else right(tree.left, res);
        res.add(tree);
      }
    }
  }

  /**
   * @see TreeExterior#findNodesInLeftEdgeAndLeaveInLeftSubTree(BinaryTreeNode, boolean, List)
   * Find this method is first add leave nodes, then add nodes from bottom to upper in right edge, the other one is from
   * from upper to bottom in left edge, then add leave nodes
   */
  private static void findLeaveInRightSubTreeAndNodesInRightEdge(BinaryTreeNode<Integer> subtree,
                                                                 boolean boundary, List<BinaryTreeNode<Integer>> res) {
    if (subtree!=null) {
      findLeaveInRightSubTreeAndNodesInRightEdge(subtree.left, boundary && subtree.right==null, res);
      findLeaveInRightSubTreeAndNodesInRightEdge(subtree.right, boundary, res);
      if (boundary || isLeaf(subtree)) {
        res.add(subtree);
      }
    }
  }

  private static void findNodesInLeftEdgeAndLeaveInLeftSubTree(BinaryTreeNode<Integer> subtree,
                                                               boolean boundary, List<BinaryTreeNode<Integer>> res) {
    if (subtree!=null) {
      if (boundary || isLeaf(subtree)) {
        res.add(subtree);
      }
      findNodesInLeftEdgeAndLeaveInLeftSubTree(subtree.left, boundary, res);
      findNodesInLeftEdgeAndLeaveInLeftSubTree(subtree.right, boundary && subtree.left==null, res);
    }
  }

  private static boolean isLeaf(BinaryTreeNode<Integer> subtree) {
    return subtree.left == null && subtree.right == null;
  }

  private static List<Integer> createOutputList(List<BinaryTreeNode<Integer>> L)
      throws TestFailure {
    if (L.contains(null)) {
      throw new TestFailure("Resulting list contains null");
    }
    List<Integer> output = new ArrayList<>();
    for (BinaryTreeNode<Integer> l : L) {
      output.add(l.data);
    }
    return output;
  }

  @EpiTest(testfile = "tree_exterior.tsv")
  public static List<Integer>
  exteriorBinaryTreeWrapper(TimedExecutor executor,
                            BinaryTreeNode<Integer> tree) throws Exception {
    List<BinaryTreeNode<Integer>> l =
        executor.run(() -> exteriorBinaryTree(tree));
    return createOutputList(l);
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
