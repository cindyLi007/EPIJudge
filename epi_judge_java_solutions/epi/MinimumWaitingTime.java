package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class MinimumWaitingTime {
  @EpiTest(testfile = "minimum_waiting_time.tsv")
  // Time: O(n*lgn) for sort
  public static int minimumTotalWaitingTime(List<Integer> serviceTimes) {
    // Sort the service times in increasing order.
    Collections.sort(serviceTimes);

    int totalWaitingTime = 0;
    for (int i = 0; i < serviceTimes.size(); ++i) {
      int numRemainingQueries = serviceTimes.size() - (i + 1);
      totalWaitingTime += serviceTimes.get(i) * numRemainingQueries;
    }
    return totalWaitingTime;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
