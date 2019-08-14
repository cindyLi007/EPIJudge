package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

// similar with leetcode 264. Ugly Number II
public class ABSqrt2 {

    public static class Number {
        int a, b;
        double val;

        Number(int a, int b) {
            this.a = a;
            this.b = b;
            val = a + b * Math.sqrt(2);
        }
    }

    @EpiTest(testfile = "a_b_sqrt2.tsv")
    // Time: O(kLgk), Space: O(k)
    public static List<Double> generateFirstKABSqrt2(int k) {
        /*List<Double> res = new ArrayList<>();
        TreeSet<Number> candidates = new TreeSet<>((a, b) -> Double.compare(a.val, b.val));
        candidates.add(new Number(0, 0));

        while (res.size() < k) {
            Number number = candidates.pollFirst();
            res.add(number.val);
            candidates.add(new Number(number.a + 1, number.b));
            candidates.add(new Number(number.a, number.b + 1));
        }
        return res;*/

        // Time: O(K), because we need not sort, Space: O(K)
        List<Number> cand = new ArrayList<>();
        cand.add(new Number(0, 0));
        // i and j are the next pos of the number we should based on to calculate the candidate
        int i=0, j=0;
        for (int n=1; n<k; n++) {
            Number candPlus1 = new Number(cand.get(i).a+1, cand.get(i).b);
            Number candPlusSqrt2 = new Number(cand.get(j).a, cand.get(j).b+1);
            cand.add(candPlus1.val < candPlusSqrt2.val ? candPlus1 : candPlusSqrt2);
            // Notice here must compare the just-inserted-val with cand1 and cand2 both because cand1 can equals to cand2 sometimes
            // check book for the first couple of numbers sequence
            if (Double.compare(cand.get(n).val, candPlus1.val)==0) i++;
            if (Double.compare(cand.get(n).val, candPlusSqrt2.val)==0) j++;
        }
        return cand.stream().map(c -> c.val).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {
                        }.getClass().getEnclosingClass())
                .ordinal());
    }
}
