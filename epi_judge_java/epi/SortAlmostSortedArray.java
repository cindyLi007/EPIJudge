package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

// Time: O(N*lgK), Space: O(K)
public class SortAlmostSortedArray {

  public static List<Integer>
  sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>(k);
    List<Integer> res = new ArrayList<>();

    while (sequence.hasNext()) {
      pq.offer(sequence.next());
      if (pq.size()>k) {
        res.add(pq.poll());
      }
    }

    while (!pq.isEmpty()) {
      res.add(pq.poll());
    }
    return res;
  }

  @EpiTest(testfile = "sort_almost_sorted_array.tsv")
  public static List<Integer>
  sortApproximatelySortedDataWrapper(List<Integer> sequence, int k) {
    return sortApproximatelySortedData(sequence.iterator(), k);
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
