package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.RandomSequenceChecker;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class OnlineSampling {

  // Assumption: there are at least k elements in the stream.
  // Time: O(N), Space: O(K)
  public static List<Integer> onlineRandomSample(Iterator<Integer> stream,
                                                 int k) {
    List<Integer> res = new ArrayList<>();
    while (res.size()<k && stream.hasNext()) {
      res.add(stream.next());
    }

    int N=k;
    Random random = new Random();
    // the equally presents every new incoming item has the chance to be put in first [0, k-1] list
    // statically treat the stream [0, N), we know we need select K elem, so every item has K/N possibility into the sample
    while (stream.hasNext()) {
      int x = stream.next();
      N++;
      int toBeRemovedIndex = random.nextInt(N);
      // we partition 2 ranges [0, k) and [k, N), an idx has k/N possibility fall in the first one and (N-K)/N fall in the
      // second range. If the toBeRemovedIndex is in first range, that means we will replace
      if (toBeRemovedIndex < k) {
        res.set(toBeRemovedIndex, x);
      }
    }

    return res;
  }

  private static boolean onlineRandomSampleRunner(TimedExecutor executor,
                                                  List<Integer> A, int k)
      throws Exception {
    List<List<Integer>> results = new ArrayList<>();

    executor.run(() -> {
      for (int i = 0; i < 1000000; ++i) {
        results.add(onlineRandomSample(A.iterator(), k));
      }
    });

    int totalPossibleOutcomes =
        RandomSequenceChecker.binomialCoefficient(A.size(), k);
    Collections.sort(A);
    List<List<Integer>> combinations = new ArrayList<>();
    for (int i = 0; i < RandomSequenceChecker.binomialCoefficient(A.size(), k);
         ++i) {
      combinations.add(
          RandomSequenceChecker.computeCombinationIdx(A, A.size(), k, i));
    }
    List<Integer> sequence = new ArrayList<>();
    for (List<Integer> result : results) {
      Collections.sort(result);
      sequence.add(combinations.indexOf(result));
    }
    return RandomSequenceChecker.checkSequenceIsUniformlyRandom(
        sequence, totalPossibleOutcomes, 0.01);
  }

  @EpiTest(testfile = "online_sampling.tsv")
  public static void onlineRandomSampleWrapper(TimedExecutor executor,
                                               List<Integer> stream, int k)
      throws Exception {
    RandomSequenceChecker.runFuncWithRetries(
        () -> onlineRandomSampleRunner(executor, stream, k));
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
