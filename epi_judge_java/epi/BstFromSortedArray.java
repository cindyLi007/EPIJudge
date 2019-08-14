package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TestUtils;
import epi.test_framework.TimedExecutor;

import java.util.List;

/**
 * Given a sorted array, build a BST of minimum possilbe height
 */
public class BstFromSortedArray {

    // Time: O(N)
    public static BstNode<Integer> buildMinHeightBSTFromSortedArray(List<Integer> A) {
        return helper(A, 0, A.size());
    }

    private static BstNode<Integer> helper(List<Integer> a, int start, int end) {
        if (start>=end) return null;
        int mid = start + (end - start)/2;
        return new BstNode<>(a.get(mid), helper(a, start, mid), helper(a, mid + 1, end));
    }

    @EpiTest(testfile = "bst_from_sorted_array.tsv")
    public static int
    buildMinHeightBSTFromSortedArrayWrapper(TimedExecutor executor,
                                            List<Integer> A) throws Exception {
        BstNode<Integer> result =
                executor.run(() -> buildMinHeightBSTFromSortedArray(A));

        List<Integer> inorder = BinaryTreeUtils.generateInorder(result);

        TestUtils.assertAllValuesPresent(A, inorder);
        BinaryTreeUtils.assertTreeIsBst(result);
        return BinaryTreeUtils.binaryTreeHeight(result);
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {
                        }.getClass().getEnclosingClass())
                .ordinal());
    }
}
