package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class EnumerateTrees {

  // Time: O(2^n), Space: O(h)
  public static List<BinaryTreeNode<Integer>> generateAllBinaryTrees(int numNodes) {
    List<BinaryTreeNode<Integer>> res = new ArrayList<>();
    if (numNodes == 0) {
      res.add(null);
      return res;
    }

    for (int i=0; i<numNodes; i++) {
      List<BinaryTreeNode<Integer>> left = generateAllBinaryTrees(i);
      List<BinaryTreeNode<Integer>> right = generateAllBinaryTrees(numNodes-1-i);
      for (BinaryTreeNode l : left) {
        for (BinaryTreeNode r : right) {
          res.add(new BinaryTreeNode<Integer>(0, l, r));
        }
      }
    }
    return res;
  }

  public static List<Integer> serializeStructure(BinaryTreeNode<Integer> tree) {
    List<Integer> result = new ArrayList<>();
    Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
    stack.push(tree);
    while (!stack.empty()) {
      BinaryTreeNode<Integer> p = stack.pop();
      result.add(p == null ? 0 : 1);
      if (p != null) {
        stack.push(p.left);
        stack.push(p.right);
      }
    }
    return result;
  }

  @EpiTest(testfile = "enumerate_trees.tsv")
  public static List<List<Integer>>
  generateAllBinaryTreesWrapper(TimedExecutor executor, int numNodes)
      throws Exception {
    List<BinaryTreeNode<Integer>> result =
        executor.run(() -> generateAllBinaryTrees(numNodes));

    List<List<Integer>> serialized = new ArrayList<>();
    for (BinaryTreeNode<Integer> x : result) {
      serialized.add(serializeStructure(x));
    }
    serialized.sort(new LexicographicalListComparator<>());
    return serialized;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
