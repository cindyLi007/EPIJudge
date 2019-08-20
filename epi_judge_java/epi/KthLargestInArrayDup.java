package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

// Design an algorithm for finding the Kth largest element of A in the presence of duplicates. The kth largest element
// is defined to be A[k -1] after A has been sorted in a stable manner, i.e., if A[i] = A[j] and i < j then A[i] must
// appear before A[j] after stable sorting

public class KthLargestInArrayDup {
  @EpiTest(testfile = "kth_largest_in_array.tsv")
  // Time: O(N), Space: O(1)
  public static int findKthLargest(int k, List<Integer> A) {
    // the 3rd param is a reverseOrder comparator, this is a straightforward way to define Comparator
    return findKthLargest(A, k, (a, b) -> Integer.compare(b, a));
  }

  // this a stable quick sort which need create 2 list to hold the smaller ones and the larger ones
  // http://www.cs.yale.edu/homes/aspnes/pinewiki/QuickSelect.html
  private static int findKthLargest(List<Integer> A, int k, Comparator<Integer> comparator) {
    int left = 0, right = A.size()-1;
    Random random = new Random();
    // random.nextInt endpoint is exclusive
    int pivot = random.nextInt(right - left + 1) + left;

    List<Integer> smaller = new ArrayList<>(), larger = new ArrayList<>();
    int v = A.get(pivot);

    for (int i=left; i<=right; i++) {
      int n = A.get(i);
      int compare = comparator.compare(n, v);
      if (compare < 0) smaller.add(n);
      else if (compare > 0) larger.add(n);
    }

    int N = right - left + 1;
    int size1 = smaller.size(), size2 = larger.size();
    if (k <= size1)
      return findKthLargest(smaller, k, comparator);
    if (k > N - size2)
      return findKthLargest(larger, k - (N- size2), comparator);

    return v;
  }

  private static int[] partition(List<Integer> A, int left, int right, int pivot, Comparator<Integer> comparator) {
    int val = A.get(pivot);
    Collections.swap(A, pivot, left);
    int i=left;
    while (i<=right) {
      int n = comparator.compare(A.get(i), val);
      if (n==0) i++;
      else if (n<0) Collections.swap(A, i++, left++);
      else Collections.swap(A, i, right--);
    }
    return new int[]{left, right};
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
