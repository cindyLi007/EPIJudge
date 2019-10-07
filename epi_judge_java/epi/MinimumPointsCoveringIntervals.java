package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Comparator;
import java.util.List;

public class MinimumPointsCoveringIntervals {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Interval {
    public int left, right;

    public Interval(int l, int r) {
      this.left = l;
      this.right = r;
    }
  }

  @EpiTest(testfile = "minimum_points_covering_intervals.tsv")

  public static Integer findMinimumVisits(List<Interval> intervals) {
    intervals.sort(Comparator.comparingInt(i2 -> i2.right));
    if (intervals.size() <=1)
      return intervals.size();
    int res = 1;
    int prev = intervals.get(0).right;
    for (int i = 1; i < intervals.size(); i++) {
      if (intervals.get(i).left > prev) {
        res++;
        prev = intervals.get(i).right;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
