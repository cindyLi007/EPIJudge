package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchForMinMaxInArray {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class MinMax {
    public Integer smallest;
    public Integer largest;

    public MinMax(Integer smallest, Integer largest) {
      this.smallest = smallest;
      this.largest = largest;
    }

    private static MinMax minMax(Integer a, Integer b) {
      return Integer.compare(b, a) < 0 ? new MinMax(b, a) : new MinMax(a, b);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      MinMax minMax = (MinMax)o;

      if (!smallest.equals(minMax.smallest)) {
        return false;
      }
      return largest.equals(minMax.largest);
    }

    @Override
    public String toString() {
      return "min: " + smallest + ", max: " + largest;
    }
  }

  @EpiTest(testfile = "search_for_min_max_in_array.tsv")

  // Time: O(N), Space: O(1)
  public static MinMax findMinMax(List<Integer> A) {
    if (A.size()==1) {
      return new MinMax(A.get(0), A.get(0));
    }
    // We can make the aux class constructor complicated, such as make a static method to call the constructor
    MinMax res = MinMax.minMax(A.get(0), A.get(1));
    for (int i=2; i<A.size()-1; i+=2) {
      MinMax local = MinMax.minMax(A.get(i), A.get(i+1));
      res = new MinMax(Math.min(local.smallest, res.smallest), Math.max(local.largest, res.largest));
    }
    if (A.size()%2 != 0) {
      res = new MinMax(Math.min(res.smallest, A.get(A.size()-1)), Math.max(res.largest, A.get(A.size()-1)));
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
