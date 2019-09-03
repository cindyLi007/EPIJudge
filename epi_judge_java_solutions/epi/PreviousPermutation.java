package epi;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PreviousPermutation {
    public static List<Integer> prevPermutation(List<Integer> perm) {
        int N = perm.size();
        int i=N-2;
        for (; i>=0; i--) {
            if (perm.get(i) > perm.get(i+1)) {
                break;
            }
        }
        if (i<0) return Collections.emptyList();
        // first the 1st elem from [i+1, N) which A[k] > A[i]
        for (int j=N-1; j>i; j--) {
            if (perm.get(j)<perm.get(i)) {
                Collections.swap(perm, j, i);
                break;
            }
        }
        Collections.reverse(perm.subList(i+1, N));
        return perm;
    }

    public static void main(String... a) {
        List<Integer> res = prevPermutation(Arrays.asList(4, 1, 2, 3));
        res.stream().forEach(o-> System.out.print(o + ", "));
    }
}
