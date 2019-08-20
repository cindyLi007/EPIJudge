package epi.excercise.search;

import java.util.Arrays;
import java.util.List;

public class FindMaxInAscendingDescending {
    public static int findMaxInAscendingDescending(List<Integer> A) {
        int low = 0, high = A.size() - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (A.get(mid - 1) < A.get(mid) && A.get(mid) > A.get(mid + 1)) return mid;
            if (A.get(mid - 1) < A.get(mid) && A.get(mid) < A.get(mid + 1)) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }

    public static void main(String... args) {
//    List<Integer> list = Arrays.asList(1, 200, 23, 34, 9, 8, 6, 4, -1);
        List<Integer> list = Arrays.asList(1, 2, 4, 0);
        int index = FindMaxInAscendingDescending.findMaxInAscendingDescending(list);

        System.out.println("The max value : " + list.get(index));
    }
}
