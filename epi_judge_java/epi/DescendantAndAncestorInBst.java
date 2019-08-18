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
  // Time: O(d) d is the difference between the depths of the ancestor and descendant if return true
  // O(h) if return false
  public static boolean
  pairIncludesAncestorAndDescendantOfM(BstNode<Integer> possibleAncOrDesc0,
                                       BstNode<Integer> possibleAncOrDesc1,
                                       BstNode<Integer> middle) {
    /*if (middle==possibleAncOrDesc0 || middle==possibleAncOrDesc1){
      return false;
    }*/
    BstNode<Integer> search0 = possibleAncOrDesc0, search1 = possibleAncOrDesc1;
    // interleaved search whether middle is either node's child or one node is the other's child
    // if we hit a point in there one node is the other's descentdant, then must return false
    // if we find middle is one of nodes descendant, break the loop, check whether middel is the other's ancestor
    while ((search0 != null || search1 != null) &&
            (search0 != possibleAncOrDesc1 && search1 != possibleAncOrDesc0) && (search0 != middle && search1 != middle)) {
      if (search0 != null) {
        if (search0.data < middle.data) search0 = search0.right;
        else search0 = search0.left;
      }
      if (search1 != null) {
        if (search1.data < middle.data) search1 = search1.right;
        else search1 = search1.left;
      }
    }

    if (search0 == null && search1 == null || search0 == possibleAncOrDesc1 || search1 == possibleAncOrDesc0) return false;

    // If both searches were unsuccessful, or we got from possibleAncOrDesc0
    // to possibleAncOrDesc1 without seeing middle, or from possibleAncOrDesc1
    // to possibleAncOrDesc0 without seeing middle, middle cannot lie between
    // possibleAncOrDesc0 and possibleAncOrDesc1.
    if ((search0 == null && search1 == null) || search0 == possibleAncOrDesc1 || search1 == possibleAncOrDesc0) {
      return false;
    }

    return search0 == middle ? findChild(middle, possibleAncOrDesc1) : findChild(middle, possibleAncOrDesc0);
  }

  private static boolean findChild(BstNode<Integer> root, BstNode<Integer> possibleChild) {
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
