package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class DeleteKthLastFromList {
  @EpiTest(testfile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  // Time: O(N), Space: O(1)
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
    // the reason we use a dummy head is for case we need remove the 1st node
    ListNode<Integer> head = new ListNode<>(0, L);
    ListNode<Integer> slow = head, fast = L;
    while (k-- > 0) {
      fast=fast.next;
    }
    // there are k nodes between slow and fast, slow is the prev-node of the removed one, which is the (k+1)-th last node in L
    while (fast!=null) {
      slow=slow.next;
      fast=fast.next;
    }
    slow.next = slow.next.next;
    return head.next;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
