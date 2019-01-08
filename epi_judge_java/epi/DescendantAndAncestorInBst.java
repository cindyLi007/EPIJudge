package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

/**
 * Given 2 nodes and one middle node, test if one of the 2 nodes is the middle node's Ancestor and the other is the
 * middle node's descendant
 */
public class DescendantAndAncestorInBst {

  public static boolean
  pairIncludesAncestorAndDescendantOfM(BstNode<Integer> possibleAncOrDesc0,
                                       BstNode<Integer> possibleAncOrDesc1,
                                       BstNode<Integer> middle) {
    BstNode<Integer> search0 = possibleAncOrDesc0, search1 = possibleAncOrDesc1;
    // first check whether any of nodes's child is middle
    if (findChild(search0, middle)) {
      return findChild(middle, possibleAncOrDesc1);
    } else {
      if (findChild(search1, middle)) {
        return findChild(middle, possibleAncOrDesc0);
      }
    }
    return false;
  }

  private static boolean findChild(BstNode<Integer> root, BstNode<Integer> possibleChild) {
    if (root == possibleChild) {
      return false;
    }
    while (root !=null) {
      if (root.data == possibleChild.data) return true;
      if (root.data > possibleChild.data) {
        root = root.left;
      } else {
        root = root.right;
      }
    }
    return false;
  }


  @EpiTest(testfile = "descendant_and_ancestor_in_bst.tsv")
  public static boolean pairIncludesAncestorAndDescendantOfMWrapper(
      TimedExecutor executor, BstNode<Integer> tree, int possibleAncOrDesc0,
      int possibleAncOrDesc1, int middle) throws Exception {
    final BstNode<Integer> candidate0 =
        BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc0);
    final BstNode<Integer> candidate1 =
        BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc1);
    final BstNode<Integer> middleNode =
        BinaryTreeUtils.mustFindNode(tree, middle);

    return executor.run(()
                            -> pairIncludesAncestorAndDescendantOfM(
                                candidate0, candidate1, middleNode));
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
