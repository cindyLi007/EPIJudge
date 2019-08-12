package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.RandomSequenceChecker;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomSubset {

  // Returns a random k-sized subset of {0, 1, ..., n - 1}.
  // Time: O(N) create list, after that O(K) to render subset; Space: O(N)
  public static List<Integer> randomSubset(int n, int k) {
    List<Integer> res = IntStream.range(0, n).boxed().collect(Collectors.toList());
    for (int i=0; i<k; i++) {
        OfflineSampling.randomSampling(k, res);
    }
    return res.subList(0, k);
  }

  // Time: O(K), Space: O(K)
  public static List<Integer> randomSubsetDynamicCreate(int n, int k) {
    // dynamic record which number picked from [0, n-1]
    // map[0, k-1] also record the subset
    Map<Integer, Integer> changedElement = new HashMap<>();
    Random random = new Random();
    for (int i=0; i<k; i++) {
      int randIdx = i + random.nextInt(n - i);
      Integer ptr1 = changedElement.get(randIdx);
      Integer ptr2 = changedElement.get(i);
      if (ptr1==null || ptr2==null) {
        changedElement.put(i, randIdx);
        changedElement.put(randIdx, i);
      } else if (ptr1==null || ptr2!=null) {
        changedElement.put(randIdx, ptr2);
        changedElement.put(i, randIdx);
      } else if (ptr1!=null || ptr2==null) {
        changedElement.put(i, ptr1);
        changedElement.put(randIdx, i);
      } else {
        changedElement.put(i, ptr1);
        changedElement.put(randIdx, ptr2);
      }
    }

    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < k; i++) {
      res.add(changedElement.get(i));
    }
    return res;
  }

  private static boolean randomSubsetRunner(TimedExecutor executor, int n,
                                            int k) throws Exception {
    List<List<Integer>> results = new ArrayList<>();

    executor.run(() -> {
      for (int i = 0; i < 1000000; ++i) {
        results.add(randomSubset(n, k));
      }
    });

    int totalPossibleOutcomes = RandomSequenceChecker.binomialCoefficient(n, k);
    List<Integer> A = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) {
      A.add(i);
    }
    List<List<Integer>> combinations = new ArrayList<>();
    for (int i = 0; i < RandomSequenceChecker.binomialCoefficient(n, k); ++i) {
      combinations.add(RandomSequenceChecker.computeCombinationIdx(A, n, k, i));
    }
    List<Integer> sequence = new ArrayList<>();
    for (List<Integer> result : results) {
      Collections.sort(result);
      sequence.add(combinations.indexOf(result));
    }
    return RandomSequenceChecker.checkSequenceIsUniformlyRandom(
        sequence, totalPossibleOutcomes, 0.01);
  }

  @EpiTest(testfile = "random_subset.tsv")
  public static void randomSubsetWrapper(TimedExecutor executor, int n, int k)
      throws Exception {
    RandomSequenceChecker.runFuncWithRetries(
        () -> randomSubsetRunner(executor, n, k));
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
