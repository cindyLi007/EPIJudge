package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class MinimumWaitingTime {
  @EpiTest(testfile = "minimum_waiting_time.tsv")

  public static int minimumTotalWaitingTime(List<Integer> serviceTimes) {
    // 1, 2, 3, 5
    int res=0, sum=0;

    Collections.sort(serviceTimes);
    for (int i = 1; i < serviceTimes.size(); i++) {
      // sum keeps sum of [0, i-1]
      sum += serviceTimes.get(i - 1);
      res += sum;
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
