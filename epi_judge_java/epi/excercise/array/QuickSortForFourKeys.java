package epi.excercise.array;

import java.util.*;

/*
EPI 5.1 Variant 2.
 */
public class QuickSortForFourKeys {
    public static void reorderFourKeys(List<Integer> A) {
        int N = A.size();
        // first find the 4 distinct values
        SortedSet<Integer> numbers = new TreeSet<>();
        for (int n : A) {
            numbers.add(n);
            if (numbers.size() == 4) break;
        }
        Iterator<Integer> it = numbers.iterator();
        it.next();

        // use the 2 middle ones as the key
        Integer v1 = it.next(), v2 = it.next();
        int left = 0, i = 0, right = N - 1;
        while (i <= right) {
            int com1  = Integer.compare(A.get(i), v1);
            int com2  = Integer.compare(A.get(i), v2);
            if (com1 < 0) Collections.swap(A, i++, left++);
            else if (com1 == 0 || com2 == 0) i++;
            else Collections.swap(A, i, right--);
        }

        // [left, right] is the subarray for the 2 middle keys, need sort for them
        i=left;
        while (i<=right) {
            int com = Integer.compare(A.get(i), v1);
            if (com==0) i++;
            else Collections.swap(A, i, right--);
        }
    }

    public static void main(String... args) {
        List list = Arrays.asList(4, 2, 3, 1, 4, 2, 4, 1, 3, 2, 4);
        reorderFourKeys(list);
        list.stream().forEach(o-> System.out.print(o + ",  "));
    }
}
