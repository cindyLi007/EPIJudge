package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SearchFirstGreaterValueInBst {

    // Time: O(h), Space: O(1)
    public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree,
                                                         Integer k) {
        BstNode<Integer> res = null, subtree = tree;
        while (subtree != null) {
            if (subtree.data <= k) {
                subtree = subtree.right;
            } else {
                res = subtree;
                subtree = subtree.left;
            }
        }
        return res;
    }

    public static BstNode<Integer> findFirstEqualK(BstNode<Integer> tree,
                                                   Integer k) {
        BstNode<Integer> res = null, subtree = tree;
        while (subtree != null) {
            if (subtree.data >= k) {
                if (subtree.data == k) res = subtree;
                subtree = subtree.left;
            } else {
                subtree = subtree.right;
            }
        }
        return res;
    }

    @EpiTest(testfile = "search_first_greater_value_in_bst.tsv")
    public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree,
                                                   Integer k) {
        BstNode<Integer> result = findFirstGreaterThanK(tree, k);
        return result != null ? result.data : -1;
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {
                        }.getClass().getEnclosingClass())
                .ordinal());
    }
}
