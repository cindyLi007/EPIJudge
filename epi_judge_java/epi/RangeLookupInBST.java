package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

// Time: O(m+h) m is the nodes lie on the range, Space: O(h)
public class RangeLookupInBST {
    @EpiUserType(ctorParams = {int.class, int.class})

    public static class Interval {
        public int left, right;

        public Interval(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    @EpiTest(testfile = "range_lookup_in_bst.tsv")
    // Time: O(h+m) where m is the number of keys in interval S
    public static List<Integer> rangeLookupInBst(BstNode<Integer> tree,
                                                 Interval interval) {
        List<Integer> res = new ArrayList<>();
        rangeLookupInBst(tree, interval, res);
        return res;
    }

    private static void rangeLookupInBst(BstNode<Integer> tree, Interval interval, List<Integer> res) {
        if (tree == null) return;
        if (interval.left <= tree.data && tree.data <= interval.right) {
            rangeLookupInBst(tree.left, interval, res);
            res.add(tree.data);
            rangeLookupInBst(tree.right, interval, res);
        } else if (tree.data < interval.left) rangeLookupInBst(tree.right, interval, res);
        else rangeLookupInBst(tree.left, interval, res);
    }

    public static void rangeLookupInBstHelper(BstNode<Integer> tree,
                                              Interval interval,
                                              List<Integer> result) {
        if (tree == null) {
            return;
        }
        if (interval.left <= tree.data && tree.data <= interval.right) {
            // tree.data lies in the interval.
            rangeLookupInBstHelper(tree.left, interval, result);
            result.add(tree.data);
            rangeLookupInBstHelper(tree.right, interval, result);
        } else if (interval.left > tree.data) {
            rangeLookupInBstHelper(tree.right, interval, result);
        } else { // interval.right >= tree.data
            rangeLookupInBstHelper(tree.left, interval, result);
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args,
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}

