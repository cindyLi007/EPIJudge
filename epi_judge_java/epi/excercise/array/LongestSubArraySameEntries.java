package epi.excercise.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LongestSubArraySameEntries {

    // Time: O(N)
    public static int longest(List<Integer> list) {
        // this is for stream
        Iterator<Integer> it = list.iterator();
        int j=it.next(), count=1, max=1;
        while (it.hasNext()) {
            int i = it.next();
            if (i!=j) {
                max = Math.max(max, count);
                count = 1;
                j = i;
            } else {
                count++;
            }
        }
        return Math.max(max, count);
    }

    public static void main(String... args) {
        int longest = longest(Arrays.asList(2, 2, 3, 1, 1, 1, 1, 4, 3, 4, 4, 4));
        System.out.println(longest);
    }
}
