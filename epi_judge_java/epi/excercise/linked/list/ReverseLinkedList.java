package epi.excercise.linked.list;

public class ReverseLinkedList {

  // this recursive solution has a stackOverflow problem, if the list is too long (more than 1000)
  // Time: O(N), Space: O(N)
  public ListNode reverseList_recurisve(ListNode head) {
    if (head==null) return null;
    return helper(head)[0];
  }

  private ListNode[] helper(ListNode n) {
    if (n.next==null) return new ListNode[]{n, n};
    ListNode res[] = helper(n.next);
    n.next = null;
    res[1].next = n;
    return new ListNode[]{res[0], n};
  }

  // Time: O(N), Space: O(1)
  public ListNode reverseList(ListNode head) {
    if (head==null || head.next==null) return head;
    ListNode dummy = new ListNode(0), prev = dummy;
    dummy.next = head;
    while (head.next!=null) {
      ListNode temp = head.next;
      head.next = temp.next;
      temp.next = prev.next;
      prev.next = temp;
    }
    return dummy.next;
  }
}
