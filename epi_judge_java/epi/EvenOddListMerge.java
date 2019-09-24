package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class EvenOddListMerge {
  @EpiTest(testfile = "even_odd_list_merge.tsv")

  // Time: O(N), Space: O(1)
  public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
    if (L == null || L.next == null || L.next.next == null) return L;
    ListNode<Integer> dummy = new ListNode<>(0, L), prev = dummy.next, cur = prev.next;
    // each time we process the odd node, cur is always a odd node, prev is the last even node so far
    while (cur != null && cur.next!=null) {
      ListNode<Integer> temp = cur.next;
      cur.next = temp.next;
      temp.next = prev.next;
      prev.next = temp;
      cur = cur.next;
      prev = prev.next;
    }
    return dummy.next;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
