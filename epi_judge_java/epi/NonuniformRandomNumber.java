package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.RandomSequenceChecker;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class NonuniformRandomNumber {

  // Time: O(N) to init intervals array, after that render a Random number is O(lgN), Space: O(N)
  public static int
  nonuniformRandomNumberGeneration(List<Integer> values,
                                   List<Double> probabilities) {
    List<Double> intervals = new ArrayList<>();
    for (Double p : probabilities) {
      intervals.add((intervals.size() == 0 ? 0 : intervals.get(intervals.size() - 1)) + p);
    }
    Random rand = new Random();
    int index = Collections.binarySearch(intervals, rand.nextDouble());
    if (index<0) {
      // index = -1-insertIdx => Math.abs(index+1) = insertInd
      return values.get(Math.abs(index + 1));
    } else {
      return values.get(index);
    }
  }

  private static boolean nonuniformRandomNumberGenerationRunner(
      TimedExecutor executor, List<Integer> values, List<Double> probabilities)
      throws Exception {
    final int N = 1000000;
    List<Integer> results = new ArrayList<>(N);

    executor.run(() -> {
      for (int i = 0; i < N; ++i) {
        results.add(nonuniformRandomNumberGeneration(values, probabilities));
      }
    });

    Map<Integer, Integer> counts = new HashMap<>();
    for (Integer result : results) {
      counts.put(result, counts.getOrDefault(result, 0) + 1);
    }
    for (int i = 0; i < values.size(); ++i) {
      final int v = values.get(i);
      final double p = probabilities.get(i);
      if (N * p < 50 || N * (1.0 - p) < 50) {
        continue;
      }
      final double sigma = Math.sqrt(N * p * (1.0 - p));
      if (Math.abs(counts.get(v) - (p * N)) > 5 * sigma) {
        return false;
      }
    }
    return true;
  }

  @EpiTest(testfile = "nonuniform_random_number.tsv")
  public static void nonuniformRandomNumberGenerationWrapper(
      TimedExecutor executor, List<Integer> values, List<Double> probabilities)
      throws Exception {
    RandomSequenceChecker.runFuncWithRetries(
        ()
            -> nonuniformRandomNumberGenerationRunner(executor, values,
                                                      probabilities));
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
