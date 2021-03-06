package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * the following is DFS, which means we must traverse left subtree, then traverse right tree
 * Read book Page 225 can also use BFS, basically that is tightly define range of each node when offer to queue, and
 * check when poll from queue
 */
public class IsTreeABst {
    // @EpiTest(testfile = "is_tree_a_bst.tsv")

    // Time: O(N), Space: O(h) h is the height of the tree that is for call stack
    public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
        return isBinaryTreeBST(tree, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    static BinaryTreeNode<Integer> prev = null;

    // Time: O(N), Space: O(h) h is the height of the tree
    public static boolean isBinaryTreeBSTAlternative(BinaryTreeNode<Integer> tree) {
        return inorderTraverse(tree);
    }

    // if inorder traverse a BT is sorted, the BT is BST
    // Time: O(N), Space: O(h)
    private static boolean inorderTraverse(BinaryTreeNode<Integer> tree) {
        if (tree == null) {
            return true;
        }
        if (!inorderTraverse(tree.left)) {
            return false;
        }
        if (prev != null && prev.data > tree.data) {
            return false;
        }
        prev = tree;
        return inorderTraverse(tree.right);
    }

    private static boolean isBinaryTreeBST(BinaryTreeNode<Integer> root, int max, int min) {
        if (root == null) return true;
        if (Integer.compare(min, root.data) > 0 || Integer.compare(max, root.data) < 0) {
            return false;
        }
        return isBinaryTreeBST(root.left, root.data, min) && isBinaryTreeBST(root.right, max, root.data);
    }

    @EpiTest(testfile = "is_tree_a_bst.tsv")
    // Time: O(N), Space: O(N)
    public static boolean isBinaryTreeBST_BFS(BinaryTreeNode<Integer> tree) {
        Deque<QueueEntry> queue = new ArrayDeque<>();
        if (tree!=null) queue.add(new QueueEntry(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));
        while (!queue.isEmpty()) {
            QueueEntry cur = queue.poll();
            if (cur.node.data < cur.min || cur.node.data > cur.max) return false;
            if (cur.node.left!=null) queue.add(new QueueEntry(cur.node.left, cur.min, cur.node.data));
            if (cur.node.right!=null) queue.add(new QueueEntry(cur.node.right, cur.node.data, cur.max));
        }
        return true;
    }

    static class QueueEntry {
        BinaryTreeNode<Integer> node;
        int min, max;

        QueueEntry(BinaryTreeNode btn, int min, int max) {
            node = btn;
            this.min = min;
            this.max = max;
        }
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {
                        }.getClass().getEnclosingClass())
                .ordinal());
    }
}
