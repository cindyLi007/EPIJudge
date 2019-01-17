package epi;

// Write a program that takes an input a BST and an interval and returns the BST
// keys that lie in the interval.

import java.util.ArrayList;
import java.util.List;

public class RangeLookupInBST {
    public static class Interval {
        int left, right;

        Interval(int l, int r) {
            left = l;
            right = r;
        }
    }

    // Time: O(h+m) where m is the number of keys in interval S
    public static List<Integer> rangeLookupInBst(BstNode<Integer> tree, Interval interval) {
        List<Integer> res = new ArrayList<>();
        rangeLookupInBst(tree, interval, res);
        return res;
    }

    private static void rangeLookupInBst(BstNode<Integer> tree, Interval interval, List<Integer> res) {
        if (tree == null) {
            return;
        }
        if (tree.data >= interval.left && tree.data <= interval.right) {
            rangeLookupInBst(tree.left, interval, res);
            res.add(tree.data);
            rangeLookupInBst(tree.right, interval, res);
        } else if (tree.data < interval.left) {
            rangeLookupInBst(tree.right, interval, res);
        } else {
            rangeLookupInBst(tree.left, interval, res);
        }
    }
}
