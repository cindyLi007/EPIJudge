package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsListPalindromic {
  @EpiTest(testfile = "is_list_palindromic.tsv")

  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
    if (L==null || L.next==null) {
      return true;
    }
    ListNode<Integer> fast = L, slow = L;
    while (fast!=null && fast.next!=null) {
      fast = fast.next.next;
      slow = slow.next;
    }

    ListNode<Integer> firstHalf = L;
    ListNode<Integer> secondHalf = reverse(slow);
    while (secondHalf != null && firstHalf != null) {
      if (!secondHalf.data.equals(firstHalf.data)) {
        return false;
      }
      secondHalf = secondHalf.next;
      firstHalf = firstHalf.next;
    }
    return true;
  }

  private static ListNode<Integer> reverse(ListNode<Integer> head) {
    if (head == null) return head;
    ListNode<Integer> dummy = new ListNode<Integer>(0, head);
    while (head.next!=null) {
      ListNode<Integer> temp = dummy.next;
      dummy.next = head.next;
      head.next = head.next.next;
      dummy.next.next = temp;
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
