package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class RemoveDuplicatesFromSortedList {
  @EpiTest(testfile = "remove_duplicates_from_sorted_list.tsv")
  // Time: O(N), Space: O(1)
  public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
    if (L==null) return L;
    ListNode<Integer> run = L.next, prev = L;
    while (run!=null) {
      if (run.data == prev.data) {
        prev.next = run.next;
      } else {
        prev = run;
      }
      run=run.next;
    }
    return L;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
