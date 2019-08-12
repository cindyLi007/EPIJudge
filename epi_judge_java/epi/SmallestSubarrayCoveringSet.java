package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SmallestSubarrayCoveringSet {

    // Represent subarray by starting and ending indices, inclusive.
    private static class Subarray {
        public Integer start;
        public Integer end;

        public Subarray(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }
    }

    // Time: O(N), Space: O(M)
    public static Subarray findSmallestSubarrayCoveringSet_1(List<String> paragraph,
                                                           Set<String> keywords) {
        Map<String, Long> keyCountMap = keywords.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        int count = keywords.size(), min = Integer.MAX_VALUE;
        int left = -1, right = -1;
        for (int start = 0, end = 0; end < paragraph.size(); end++) {
            String w = paragraph.get(end);
            if (keyCountMap.containsKey(w)) {
                if (keyCountMap.put(w, keyCountMap.get(w) - 1) == 1) count--;
                while (count == 0 && start < paragraph.size()) {
                    if (end - start < min) {
                        min = end - start;
                        left = start;
                        right = end;
                    }
                    String s = paragraph.get(start);
                    if (keywords.contains(s) && keyCountMap.put(s, keyCountMap.get(s) + 1) == 0) {
                        count++;
                    }
                    start++;
                }
            }
        }
        return new Subarray(left, right);
    }

    // Time: O(N), Space: O(K) K is the size of keywords,
    // the LinkedHashMap will do HIDDEN work to remove eldest entry from it,
    public static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph,
                                                             Set<String> keywords) {
        // LinkedHashMap can keep the relative order for key words, the first one in the map is the earliest access word in paragraph
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>(keywords.size(), 1, true);
        int res = Integer.MAX_VALUE;
        Subarray subarray = null;

        for (int i = 0; i < paragraph.size(); i++) {
            String word = paragraph.get(i);
            if (keywords.contains(word)) {
                // NOTICE: if LinkedHashMap already have the key, map will remove the key and insert it to the end of the map with
                // the new value, that is because when create the map, we set the access order to true
                // we only need keep the last idx of each key in map
                map.put(word, i);
                if (map.size() == keywords.size()) {
                    int start = getValueForFirstEntry(map);
                    if (i - start < res) {
                        res = i - start;
                        subarray = new Subarray(start, i);
                    }
                }
            }
        }

        return subarray;
    }

    private static Integer getValueForFirstEntry(LinkedHashMap<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            // We only need get the first (earliest inserted) entry
            return entry.getValue();
        }
        return null;
    }

    @EpiTest(testfile = "smallest_subarray_covering_set.tsv")
    public static int findSmallestSubarrayCoveringSetWrapper(
            TimedExecutor executor, List<String> paragraph, Set<String> keywords)
            throws Exception {
        Set<String> copy = new HashSet<>(keywords);

        Subarray result = executor.run(
                () -> findSmallestSubarrayCoveringSet(paragraph, keywords));

        if (result.start < 0 || result.start >= paragraph.size() ||
                result.end < 0 || result.end >= paragraph.size() ||
                result.start > result.end)
            throw new TestFailure("Index out of range");

        for (int i = result.start; i <= result.end; i++) {
            copy.remove(paragraph.get(i));
        }

        if (!copy.isEmpty()) {
            throw new TestFailure("Not all keywords are in the range");
        }
        return result.end - result.start + 1;
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {
                        }.getClass().getEnclosingClass())
                .ordinal());
    }
}
