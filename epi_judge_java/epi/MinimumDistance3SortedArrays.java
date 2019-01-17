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
    public int pos;

    public ArrayData(int idx, int val, int pos) {
      this.val = val;
      this.idx = idx;
      this.pos = pos;
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

  // Time: O(n*lgK) K is size of sortedArrays
  public static int
  findMinDistanceSortedArrays(List<List<Integer>> sortedArrays) {
    int res = Integer.MAX_VALUE;

    TreeSet<ArrayData> currentInterval = new TreeSet<>();
    for (int i=0; i<sortedArrays.size(); i++) {
      currentInterval.add(new ArrayData(i, sortedArrays.get(i).get(0), 0));
    }

    while (true) {
      res = Math.min(res, currentInterval.last().val - currentInterval.first().val);
      ArrayData minElem = currentInterval.pollFirst();
      int minElementIdxInCorArray = minElem.idx;
      int index = minElem.pos;
      index++;
      if (index == sortedArrays.get(minElementIdxInCorArray).size()) {
        return res;
      }
      ArrayData arrayData = new ArrayData(minElementIdxInCorArray, sortedArrays.get(minElementIdxInCorArray).get(index), index);
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
