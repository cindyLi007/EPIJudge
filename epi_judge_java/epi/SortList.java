package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortList {
  @EpiTest(testfile = "sort_list.tsv")

  // Time: O(nlgn), Space: O(lgN) for recursive stack call
  public static ListNode<Integer> stableSortList(ListNode<Integer> L) {
    if (L==null || L.next==null) return L;

    ListNode<Integer> prev = null;
    ListNode<Integer> slow = L, fast = L;
    while (fast!=null && fast.next!=null) {
      fast = fast.next.next;
      prev = slow;
      slow = slow.next;
    }
    prev.next = null;

    return SortedListsMerge.mergeTwoSortedLists(stableSortList(L), stableSortList(slow));

    /* the following is insertion sorted, O(N^2)

    ListNode<Integer> dummyHead = new ListNode<>(0, L);

    while (L!=null && L.next!=null) {
      if (L.next.data < L.data) {
        ListNode<Integer> prev = dummyHead;
        ListNode<Integer> node = L.next;
        while (prev.next.data <= node.data) {
          prev = prev.next;
        }
        ListNode<Integer> temp = prev.next;
        prev.next = node;
        L.next = node.next;
        node.next = temp;
      } else {
        L = L.next;
      }
    }
    return dummyHead.next;*/
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
