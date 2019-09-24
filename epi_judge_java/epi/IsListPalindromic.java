package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsListPalindromic {
  @EpiTest(testfile = "is_list_palindromic.tsv")
  // Time: O(N), Space: O(N)
 /* public static boolean isLinkedListAPalindrome_useDeque(ListNode<Integer> L) {
    if (L==null || L.next==null) return true;
    Deque<Integer> deque = new ArrayDeque<>();
    ListNode<Integer> run = L;
    while (run!=null) {
      deque.addLast(run.data);
      run = run.next;
    }
    while (!deque.isEmpty() && deque.size()>1) {
      int first = deque.pollFirst(), last = deque.pollLast();
      if (first!=last) return false;
    }
    return true;
  }*/

  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
    ListNode<Integer> fast = L, slow = L;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    slow = reverse(slow);
    fast = L;
    while (fast!=null && slow!=null){
      // IMPORTANT: must use equals because ListNode store Object of Integer, if we use fast.data != slow.data,
      // even the intVal is same, still return false
      if (!fast.data.equals(slow.data))
        return false;
      fast = fast.next;
      slow = slow.next;
    }
    return true;
  }

  private static ListNode<Integer> reverse(ListNode<Integer> head) {
    ListNode<Integer> prev = null;
    while (head!=null) {
      ListNode<Integer> temp = head.next;
      head.next = prev;
      prev = head;
      head = temp;
    }
    return prev;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
