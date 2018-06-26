package epi.excercise.linked.list;

/**
 * Takes as input a singly linked list L, and a non-negative integer K, and reverses the list k nodes at a time. If the
 * number of nodes n in the list is not a multiple of K, lave the last n mode k nodes unchanged.
 */
public class ReverseKNodes {

  public ListNode reverseKNode(ListNode L, int k) {
    if (k <= 1 || L == null || L.next == null) return L;
    // first round find out number N of ListNode L
    int count = 0;
    ListNode run = L;
    while (run != null) {
      run = run.next;
      count++;
    }
    // 2nd round, reverse every K nodes each time, mark the start node's prev node
    ListNode aux = new ListNode(0);
    aux.next = L;
    ListNode prev = aux;
    while (count >= k) {
      int pos = 1;
      while (pos++ < k) {
        ListNode temp = L.next;
        L.next = temp.next;
        temp.next = prev.next;
        prev.next = temp;
      }
      count -= k;
      // prev is this round's last node, next round's start node's prev node
      prev = L;
      // L is the start node of next round
      L = L.next;
    }
    return aux.next;
  }

  public static void main(String... args) {
    ListNode l1 = new ListNode(1);
    ListNode l2 = new ListNode(2); l1.next = l2;
    ListNode l3 = new ListNode(3); l2.next = l3;
    ListNode l4 = new ListNode(4); l3.next = l4;
    ListNode l5 = new ListNode(5); l4.next = l5;

    ReverseKNodes reverseKNodes = new ReverseKNodes();
    ListNode res = reverseKNodes.reverseKNode(l1, 4);

    while (res!=null) {
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
