package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IntervalsUnion {

  public static class Interval {
    public Endpoint left = new Endpoint();
    public Endpoint right = new Endpoint();

    private static class Endpoint {
      public boolean isClosed;
      public int val;
    }
  }

  // Time: O(NlgN), Space: O(1)
  public static List<Interval> unionOfIntervals(List<Interval> intervals) {
    if (intervals.isEmpty()) {
      return Collections.EMPTY_LIST;
    }

    List<Interval> res = new ArrayList<>();

    intervals.sort((i1, i2)->{
      if (i1.left.val != i2.left.val) {
        return i1.left.val - i2.left.val;
      } else {
        return i1.left.isClosed && !i2.left.isClosed ? -1 : !i1.left.isClosed && i2.left.isClosed ? 1 : 0;
      }
    });

    Interval prev = null;
    for (Interval i : intervals) {
      if (res.isEmpty() || i.left.val > prev.right.val || (i.left.val==prev.right.val && !i.left.isClosed && !prev.right.isClosed)) {
        res.add(i);
        prev = i;
      } else {
        if (i.right.val>prev.right.val || (i.right.val==prev.right.val && i.right.isClosed && !prev.right.isClosed)) {
          prev.right = i.right;
        }
      }
    }

    return res;
  }

  // Time: O(NlgN), Space: O(N) for the 2 arrays, this one is slower than EPI solution
  public static List<Interval> unionOfIntervals_array(List<Interval> intervals) {
    // First sort list by start point
    intervals.sort(Comparator.comparingInt(a -> a.left.val));

    // 2nd created 2 Arrays for start and end points and sort them
    int n = intervals.size();
    Interval.Endpoint[] start = new Interval.Endpoint[n];
    Interval.Endpoint[] end = new Interval.Endpoint[n];
    int k=0;
    for (Interval i : intervals) {
      start[k] = i.left;
      end[k++] = i.right;
    }

    Arrays.sort(start, (a, b) -> a.val!=b.val ? a.val-b.val : a.isClosed&!b.isClosed ? -1 : !a.isClosed&b.isClosed ? 1 : 0);
    Arrays.sort(end, (a, b) -> a.val!=b.val ? a.val-b.val : a.isClosed&!b.isClosed ? 1 : !a.isClosed&b.isClosed ? -1 : 0);

    // finally loop through the 2 arrays, always try to find the next start point which is no overlap with its previous end point
    // when find it, create a new interval
    List<Interval> res = new ArrayList<>();
    for (int i=1, j=0; i<=n; i++) {
      if (i==n || start[i].val > end[i-1].val
          // this is for start and close are both open, we could not treat them union
          || (start[i].val==end[i-1].val && !start[i].isClosed && !end[i-1].isClosed)) {
        Interval interval = new Interval();
        interval.left = start[j];
        interval.right = end[i - 1];
        res.add(interval);
        j=i;
      }
    }
    return res;
  }

  @EpiUserType(
      ctorParams = {int.class, boolean.class, int.class, boolean.class})
  public static class FlatInterval {
    int leftVal;
    boolean leftIsClosed;
    int rightVal;
    boolean rightIsClosed;

    public FlatInterval(int leftVal, boolean leftIsClosed, int rightVal,
                        boolean rightIsClosed) {
      this.leftVal = leftVal;
      this.leftIsClosed = leftIsClosed;
      this.rightVal = rightVal;
      this.rightIsClosed = rightIsClosed;
    }

    public FlatInterval(Interval i) {
      if (i != null) {
        leftVal = i.left.val;
        leftIsClosed = i.left.isClosed;
        rightVal = i.right.val;
        rightIsClosed = i.right.isClosed;
      }
    }

    public Interval toInterval() {
      Interval i = new Interval();
      i.left.val = leftVal;
      i.left.isClosed = leftIsClosed;
      i.right.val = rightVal;
      i.right.isClosed = rightIsClosed;
      return i;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      FlatInterval that = (FlatInterval)o;

      if (leftVal != that.leftVal) {
        return false;
      }
      if (leftIsClosed != that.leftIsClosed) {
        return false;
      }
      if (rightVal != that.rightVal) {
        return false;
      }
      return rightIsClosed == that.rightIsClosed;
    }

    @Override
    public int hashCode() {
      int result = leftVal;
      result = 31 * result + (leftIsClosed ? 1 : 0);
      result = 31 * result + rightVal;
      result = 31 * result + (rightIsClosed ? 1 : 0);
      return result;
    }

    @Override
    public String toString() {
      return "" + (leftIsClosed ? "<" : "(") + leftVal + ", " + rightVal +
          (rightIsClosed ? ">" : ")");
    }
  }

  @EpiTest(testfile = "intervals_union.tsv")
  public static List<FlatInterval>
  unionIntervalWrapper(TimedExecutor executor, List<FlatInterval> intervals)
      throws Exception {
    List<Interval> casted = new ArrayList<>(intervals.size());
    for (FlatInterval in : intervals) {
      casted.add(in.toInterval());
    }

    List<Interval> result = executor.run(() -> unionOfIntervals(casted));

    intervals = new ArrayList<>(result.size());
    for (Interval i : result) {
      intervals.add(new FlatInterval(i));
    }
    return intervals;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
