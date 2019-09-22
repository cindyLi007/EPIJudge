package epi.excercise.linked.list;

public class ReverseLinkedList {
  public ListNode reverseList(ListNode head) {
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
}
