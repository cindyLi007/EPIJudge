package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;

// Time: O(N), Space: O(h)
public class TreeFromPreorderWithNull {
  static int subtreeIdx;

  public static BinaryTreeNode<Integer> reconstructPreorder(List<Integer> preorder) {
      subtreeIdx=0;
      return helper(preorder);
  }

  private static BinaryTreeNode<Integer> helper(List<Integer> preorder) {
    Integer val = preorder.get(subtreeIdx++);
    if (val==null) {
      return null;
    }
    BinaryTreeNode<Integer> left = helper(preorder);
    BinaryTreeNode<Integer> right = helper((preorder));
    return new BinaryTreeNode<>(val, left, right);
  }


  @EpiTest(testfile = "tree_from_preorder_with_null.tsv")
  public static BinaryTreeNode<Integer>
  reconstructPreorderWrapper(TimedExecutor executor, List<String> strings)
      throws Exception {
    List<Integer> ints = new ArrayList<>();
    for (String s : strings) {
      if (s.equals("null")) {
        ints.add(null);
      } else {
        ints.add(Integer.parseInt(s));
      }
    }

    return executor.run(() -> reconstructPreorder(ints));
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
