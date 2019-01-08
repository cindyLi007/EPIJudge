package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class MinimumDistance3SortedArrays {

  public static class ArrayData implements Comparable<ArrayData> {
    public int val;
    public int idx; // the sortedArrays index

    public ArrayData(int idx, int val) {
      this.val = val;
      this.idx = idx;
    }

    @Override
    public int compareTo(ArrayData o) {
      int result = Integer.compare(val, o.val);
      if (result == 0) {
        result = Integer.compare(idx, o.idx);
      }
      return result;
    }
  }

  @EpiTest(testfile = "minimum_distance_3_sorted_arrays.tsv")

  public static int
  findMinDistanceSortedArrays(List<List<Integer>> sortedArrays) {
    int res = Integer.MAX_VALUE;
    // Store current pointer of every sorted array
    List<Integer> heads = new ArrayList<>();
    // first, init pointer to 0 for every sorted array
    for (List<Integer> sortedArray : sortedArrays) {
      heads.add(0);
    }
    TreeSet<ArrayData> currentInterval = new TreeSet<>();
    for (int i=0; i<heads.size(); i++) {
      currentInterval.add(new ArrayData(i, sortedArrays.get(i).get(heads.get(i))));
    }

    while (true) {
      res = Math.min(res, currentInterval.last().val - currentInterval.first().val);
      int minElementIdxInCorArray = currentInterval.pollFirst().idx;
      int index = heads.get(minElementIdxInCorArray);
      if (index == sortedArrays.get(minElementIdxInCorArray).size()-1) return res;
      heads.set(minElementIdxInCorArray, heads.get(minElementIdxInCorArray)+1);
      ArrayData arrayData = new ArrayData(minElementIdxInCorArray, sortedArrays.get(minElementIdxInCorArray).get(heads.get(minElementIdxInCorArray)));
      currentInterval.add(arrayData);
    }
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
