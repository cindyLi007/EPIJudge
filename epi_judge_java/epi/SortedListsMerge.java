package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortedListsMerge {
  @EpiTest(testfile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {
    ListNode<Integer> res = new ListNode(0, null);
    ListNode<Integer> prev = res;
    while (L1!=null && L2!=null) {
      if (L1.data < L2.data) {
        prev.next = L1;
        L1 = L1.next;
      } else {
        prev.next = L2;
        L2 = L2.next;
      }
      prev = prev.next;
    }
    prev.next = L1!=null ? L1 : L2;
    return res.next;
  }

  public static ListNode<Integer> mergeTwoSortedLists_recursive(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {
    if (L1 == null) return L2;
    if (L2 == null) return L1;

    if (L1.data < L2.data) {
      L1.next = mergeTwoSortedLists_recursive(L1.next, L2);
      return L1;
    } else {
      L2.next = mergeTwoSortedLists_recursive(L1, L2.next);
      return L2;
    }
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
