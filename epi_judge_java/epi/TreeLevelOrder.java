package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.ToIntBiFunction;
import java.util.stream.Collectors;

public class TreeLevelOrder {
  @EpiTest(testfile = "tree_level_order.tsv")

  public static List<List<Integer>>
  binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    List<List<Integer>> res = new ArrayList<>();

    if (tree==null) {
      return res;
    }

    List<BinaryTreeNode<Integer>> currentDepthNode = Arrays.asList(tree);
    while (!currentDepthNode.isEmpty()) {
      // This is a way to get average of this level
      int average = (int)currentDepthNode.stream()
              .mapToInt(node -> node.data).average().getAsDouble();
      res.add(currentDepthNode.stream()
          .map(node -> node.data)
          .collect(Collectors.toList()));
      currentDepthNode = currentDepthNode.stream()
          .map(node -> Arrays.asList(node.left, node.right))
          .flatMap(List::stream)
          .filter(child -> child!=null)
          .collect(Collectors.toList());
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
