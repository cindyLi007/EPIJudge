package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class RefuelingSchedule {

  @EpiTest(testfile = "refueling_schedule.tsv")
  // gallons[i] is the amount of gas in city i, and distances[i] is the distance
  // city i to the next city.
  // Time: O(N), Space: O(1)
  public static int findAmpleCity(List<Integer> gallons,
                                  List<Integer> distances) {
    // track the 0 to N-1 city, find the one which when we enter it, we have minimum gas (this can be a negative number)
    // since we enter this city with min gas number, we should start from this city
    int N = gallons.size();
    int min = 0, remainingGas = 0, res = 0;
    for (int i=1; i<N; i++) {
      remainingGas += gallons.get(i-1) - distances.get(i-1) / 20;
      if (remainingGas < min) {
        min = remainingGas;
        res = i;
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
