package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class PowerSet {
    @EpiTest(testfile = "power_set.tsv")

    public static List<List<Integer>> generatePowerSet(List<Integer> inputSet) {
        List<List<Integer>> res = new ArrayList<>();
        powerSet(inputSet, 0, res, new ArrayList<Integer>());
        return res;
    }

    private static void powerSet(List<Integer> inputSet, int start,
                                 List<List<Integer>> res, ArrayList<Integer> list) {
        res.add(new ArrayList<>(list));

        for (int i=start; i<inputSet.size(); i++) {
            list.add(inputSet.get(i));
            powerSet(inputSet, i+1, res, list);
            list.remove(list.size() - 1);
        }
    }

    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>,
            List<List<Integer>>> comp = (expected, result) -> {
        if (result == null) {
            return false;
        }
        for (List<Integer> l : expected) {
            Collections.sort(l);
        }
        expected.sort(new LexicographicalListComparator<>());
        for (List<Integer> l : result) {
            Collections.sort(l);
        }
        result.sort(new LexicographicalListComparator<>());
        return expected.equals(result);
    };

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {
                        }.getClass().getEnclosingClass())
                .ordinal());
    }
}
