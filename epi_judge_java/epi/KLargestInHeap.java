package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.function.BiPredicate;

public class KLargestInHeap {

  @EpiTest(testfile = "k_largest_in_heap.tsv")

  // Time: O(K*lgK), Space: O(K)
  public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {
    List<Integer> res = new ArrayList<>();
    if (A.size() == 0) return res;
    PriorityQueue<HeapElement> priorityQueue = new PriorityQueue<>((o1, o2) -> o2.val - o1.val);
    priorityQueue.add(new HeapElement(0, A.get(0)));
    // will loop K times, and each time for insert to heap is logK
    while (res.size()< k && !priorityQueue.isEmpty()) {
      HeapElement cur = priorityQueue.poll();
      res.add(cur.val);
      int idx = cur.idx * 2 +1;
      if (idx < A.size()) {
        priorityQueue.add(new HeapElement(idx, A.get(idx)));
      }
      idx++;
      if (idx < A.size()) {
        priorityQueue.add(new HeapElement(idx, A.get(idx)));
      }
    }
    return res;
  }

  private static class HeapElement {
    int idx;
    int val;

    HeapElement(int idx, int val) {
      this.idx = idx;
      this.val = val;
    }
  }

  @EpiTestComparator
  public static BiPredicate<List<Integer>, List<Integer>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
