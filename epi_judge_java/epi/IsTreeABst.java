package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

/*
 * the follwoing are DFS, which mean we must traverse left subtree, then traverse right tree
 * Read book Page 225 can also use BFS, bacially that is tightly define range of each node when offer to queue, and
 * check when poll from queue
 */
public class IsTreeABst {
    @EpiTest(testfile = "is_tree_a_bst.tsv")

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

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {
                        }.getClass().getEnclosingClass())
                .ordinal());
    }
}
