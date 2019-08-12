package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

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
    intervals.sort((i1, i2) -> Integer.compare(i1.right, i2.right));
    if (intervals.size() <=1)
      return intervals.size();
    int res = 1;
    Interval prev = intervals.get(0);
    for (int i = 1; i < intervals.size(); i++) {
      if (intervals.get(i).left <= prev.right) {
        continue;
      } else {
        res++;
        prev = intervals.get(i);
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
