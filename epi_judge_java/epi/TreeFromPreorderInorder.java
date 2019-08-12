package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TreeFromPreorderInorder {
    @EpiTest(testfile = "tree_from_preorder_inorder.tsv")
    // Time: O(N), Space: O(N + h)
    public static BinaryTreeNode<Integer>
    binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
        int N = preorder.size();
        return binaryTreeFromPreorderInorder(preorder, 0, N - 1, 0, N - 1,
                // Make up a map which is value-to-"the val idx in inorder list" mapping
                IntStream.range(0, N).boxed().collect(Collectors.toMap(i -> inorder.get(i), i -> i)));
    }

    private static BinaryTreeNode<Integer> binaryTreeFromPreorderInorder(List<Integer> preorder, int ps, int pe, int is, int ie, Map<Integer, Integer> map) {
        if (ps>pe) return null;
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(preorder.get(ps));

        // NOTICE: mid is the idx of inorder, it could NOT be directly used in preorder as an index. We must calculate the left subtree count to render the mid point of preorder
        Integer mid = map.get(preorder.get(ps));
        int leftSubTreeCount = mid - is;
        root.left = binaryTreeFromPreorderInorder(preorder, ps + 1, ps + leftSubTreeCount, is, mid - 1, map);
        root.right = binaryTreeFromPreorderInorder(preorder,  ps + 1 +leftSubTreeCount, pe, mid+1, ie, map);
        return root;
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {
                        }.getClass().getEnclosingClass())
                .ordinal());
    }
}
