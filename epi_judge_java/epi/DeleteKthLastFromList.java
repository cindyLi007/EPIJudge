package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class DeleteKthLastFromList {
  @EpiTest(testfile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  // Time: O(N), Space: O(1)
  // this is like a sliding window, first build a window which len == k, 2nd shift this window to the end of the list, then
  // the left of the window is K away the end of the list
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
    ListNode<Integer> dummy = new ListNode<>(0, L);
    ListNode<Integer> run = L;
    while (k-->0) {
      run=run.next;
    }
    L = dummy;
    while (run!=null) {
      run=run.next;
      L=L.next;
    }
    L.next=L.next.next;
    return dummy.next;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
