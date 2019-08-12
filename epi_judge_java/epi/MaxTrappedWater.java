package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MaxTrappedWater {
  @EpiTest(testfile = "max_trapped_water.tsv")
  // Time: O(N), Space: O(1)
  public static int getMaxTrappedWater(List<Integer> heights) {
    int max=0;
    for (int i=0, j=heights.size()-1; i<j; ) {
      int currentWater = (j - i) * Math.min(heights.get(i), heights.get(j));
      max = Math.max(max, currentWater);
      if (heights.get(i) < heights.get(j)) {
        i++;
      } else {
        j--;
      }
    }
    return max;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
