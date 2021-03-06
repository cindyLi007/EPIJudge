package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class KthLargestInArray {
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then
  // findKthLargest(A, 1) returns 3, findKthLargest(A, 2) returns 2,
  // findKthLargest(A, 3) returns 1, and findKthLargest(A, 4) returns -1.
  @EpiTest(testfile = "kth_largest_in_array.tsv")
  // Time: O(N), Space: O(1)
  public static int findKthLargest(int k, List<Integer> A) {
    // the 3rd param is a reverseOrder comparator, this is a straightforward way to define Comparator
    return findKthLargest(A, k, (a, b) -> Integer.compare(b, a));
  }

  private static int findKthLargest(List<Integer> A, int k, Comparator<Integer> comparator) {
    int left = 0, right = A.size()-1;
    Random random = new Random();
    while (left <= right) {
      // random.nextInt endpoint is exclusive
      int pivot = random.nextInt(right - left + 1) + left;
      int newPivot = partition(A, left, right, pivot, comparator);
      if (newPivot == k-1) {
        return A.get(newPivot);
      }
      if (newPivot < k-1) {
        left = newPivot+1;
      } else {
        right = newPivot-1;
      }
    }
    return -1;
  }

  // partition list A into 2 part, greater than A[pivot], A[pivot] and less than A[pivot]
  private static int partition(List<Integer> A, int left, int right, int pivot, Comparator<Integer> comparator) {
    int pivotValue = A.get(pivot);
    Collections.swap(A, pivot, right); // first put pivot value to the last one in the  list range [left, right]
    int newPivotIdx = left; // this idx should be the 1st occurrence idx of greater-than pivotValue
    for (int i=left; i<right; i++) {
      if (comparator.compare(A.get(i), pivotValue) < 0) {
        Collections.swap(A, i, newPivotIdx++);
      }
    }
    Collections.swap(A, right, newPivotIdx);
    return newPivotIdx;
  }

  // Time: O(N*lgK), Space: O(K)
  public static int findKthLargest_usingHeap(int k, List<Integer> A) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (Integer num : A) {
      if (pq.size() < k) {
        pq.offer(num);
      } else {
        if (num > pq.peek()) {
          pq.poll();
          pq.offer(num);
        }
      }
    }
    return pq.poll();
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
