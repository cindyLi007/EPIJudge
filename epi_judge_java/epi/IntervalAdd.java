package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class IntervalAdd {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Interval {
    public int left, right;

    public Interval(int l, int r) {
      this.left = l;
      this.right = r;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Interval interval = (Interval)o;

      if (left != interval.left) {
        return false;
      }
      return right == interval.right;
    }

    @Override
    public String toString() {
      return "[" + left + ", " + right + "]";
    }
  }

  @EpiTest(testfile = "interval_add.tsv")
  // Time: O(lgN), Space: O(1)
  public static List<Interval> addInterval(List<Interval> disjointIntervals,
                                           Interval newInterval) {
    int low=0, high=disjointIntervals.size()-1;
    while (low<=high) {
      int mid = low + (high-low)/2;
      if (disjointIntervals.get(mid).left>=newInterval.left) {
        high=mid-1;
      } else {
        low=mid+1;
      }
    }
    // begin is the most right entry which left < newInterval.left, if no such element exists, begin will be -1
    int begin=high;

    low=0;
    high=disjointIntervals.size()-1;
    while (low<=high) {
      int mid = low + (high-low)/2;
      if (disjointIntervals.get(mid).right<=newInterval.right) {
        low=mid+1;
      } else {
        high=mid-1;
      }
    }
    // end is the most left entry which right > newInterval.right, if no such element exists end will be size
    int end=low;

    int removeStart=begin+1; // init removeStart to begin+1 in case there is no overlap
    if (begin>0 && disjointIntervals.get(begin).right>=newInterval.left) {
      newInterval.left = Math.min(disjointIntervals.get(begin).left, newInterval.left);
      removeStart=begin;
    }

    int removeEnd=end-1; // init removeEnd to end-1 in case there is no overlap
    if (end<disjointIntervals.size() && disjointIntervals.get(end).left<=newInterval.right) {
      newInterval.right = Math.max(disjointIntervals.get(end).right, newInterval.right);
      removeEnd=end;
    }

    // extreme case to save time, if newInterval cover all intervals in list
    if (removeEnd-removeStart >=disjointIntervals.size()-1) {
      List<Interval> list = new ArrayList<>();
      list.add(newInterval);
      return list;
    }

    begin=removeStart;
    for (int i=0; i<=removeEnd-removeStart; i++) {
      disjointIntervals.remove(removeStart);
    }
    disjointIntervals.add(begin, newInterval);

    return disjointIntervals;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
