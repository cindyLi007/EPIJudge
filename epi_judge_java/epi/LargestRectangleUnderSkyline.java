package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class LargestRectangleUnderSkyline {
    @EpiTest(testfile = "largest_rectangle_under_skyline.tsv")
    // keep a sequence of active pillar (strictyly increasing height),
    // when a current height is <= top of the stack, remove the top of the stack
    public static int calculateLargestRectangle(List<Integer> heights) {
        int N = heights.size(), res = 0;
        // store the index, so we can keep track of width and height
        Deque<Integer> activePillars = new ArrayDeque<>();

        for (int i = 0; i <= N; i++) {
            // whether need pop top of the stack, 2 conditions:
            // 1. we run out of all pillar (i==N) or 2. current height <= top of stack's height
            // the elements in the stack has following relationship, for ex. top item is 8, next top is 6, and now process idx 9
            // since height[9]==5 and height[8]==6, we need pop top item, the item 6 is the last idx which height is lower than item 8.
            // which means item 7 (although is not in stack, must be >= height[8]. so the width is (9-6-1)
            // Array [1, 4, 2, 5, 6, 3, 2, 6, 6, 5, 2, 1, 3]
            // idx    0  1  2  3  4  5  6  7  8  9  10 11 12
            while (!activePillars.isEmpty() && needPopOfStack(activePillars, i, heights)) {
                Integer height = heights.get(activePillars.pop());
                int width = activePillars.isEmpty() ? i : i - activePillars.peek() - 1;
                res = Math.max(height * width, res);
            }
            activePillars.push(i);
        }

        return res;
    }

    private static boolean needPopOfStack(Deque<Integer> activePillars, int currentPillar, List<Integer> heights) {
        return currentPillar == heights.size() || heights.get(activePillars.peek()) >= heights.get(currentPillar);
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {
                        }.getClass().getEnclosingClass())
                .ordinal());
    }
}
