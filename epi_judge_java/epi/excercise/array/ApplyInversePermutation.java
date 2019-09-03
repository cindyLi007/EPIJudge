package epi.excercise.array;

import java.util.Arrays;
import java.util.List;

// EPI 5.11 Variant 1, An inverse permutation is a permutation which you will get by inserting position of an element
// at the position specified by the element value in the array.
public class ApplyInversePermutation {

    public static void apply(List<Integer> A) {
        int N = A.size();
        for (int i = 0; i < N; i++) {
            int pos = A.get(i); // pos is the pos which should set v
            int v = i;
            while (pos >= 0) { // pos >= 0 means there has not be set
                int temp = A.get(pos);
                if (temp < 0) break;
                // set v to the pos and set it to neg number
                A.set(pos, v);
                A.set(pos, v-N);
                // pos is the next v should be set
                v = pos;
                pos = temp;
            }
        }

        for (int i = 0; i < N; i++) {
            A.set(i, A.get(i) + N);
        }
    }

    public static void main(String... args) {
//        List<Integer> list = Arrays.asList(0, 3, 2, 1); // should be 0 3 2 1
//         List<Integer> list = Arrays.asList(1, 2, 3, 4, 0); // 4, 0, 1, 2, 3
        List<Integer> list = Arrays.asList(3, 2, 0, 1); // 2, 3, 1, 0
        apply(list);
        list.stream().forEach(o-> System.out.print(o + ", "));
    }
}
