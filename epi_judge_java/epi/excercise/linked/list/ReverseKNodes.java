package epi.excercise.linked.list;

/**
 * Takes as input a singly linked list L, and a non-negative integer K, and reverses the list k nodes at a time. If the
 * number of nodes n in the list is not a multiple of K, lave the last n mode k nodes unchanged.
 * Leetcode 25, different solutions
 */
public class ReverseKNodes {

  // Time: O(N)
  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || k == 1) {
      return head;
    }

    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode prev = dummy;
    ListNode curr = head;

    int i = 0;
    while (curr != null) {
      i++;
      if (i % k == 0) {
        // now curr is the last node in the K Group, will be the first node after reverse
        // prev is always the previous node of reverse, so after this time reverse, we set prev to the last node of this time reverse
        prev = reverse(prev, k);
        curr = prev.next;
      } else {
        curr = curr.next;
      }
    }

    return dummy.next;
  }

  // revser linked list from prev.next, return the last node of the reverse list
  private ListNode reverse(ListNode prev, int k) {
    // run currently is the 1st node of sublist, will be the last node after reverse
    ListNode run = prev.next;
    int count = 1;
    while (count < k) {
      ListNode temp = run.next;
      run.next = temp.next;
      temp.next = prev.next;
      prev.next = temp;
      count++;
    }
    // we must return the last node of the after-reverse, that is because we need from this node to traverse the remaining of the list
    return run;
  }

  public static void main(String... args) {
    ListNode l1 = new ListNode(1);
    ListNode l2 = new ListNode(2);
    l1.next = l2;
    ListNode l3 = new ListNode(3);
    l2.next = l3;
    ListNode l4 = new ListNode(4);
    l3.next = l4;
    ListNode l5 = new ListNode(5);
    l4.next = l5;

    ReverseKNodes reverseKNodes = new ReverseKNodes();
    ListNode res = reverseKNodes.reverseKGroup(l1, 2);

    while (res != null) {
      System.out.print(res.val + " -> ");
      res = res.next;
    }
  }

}

class ListNode {
  int val;
  ListNode next;

  ListNode(int v) {
    this.val = v;
    next = null;
  }
}
