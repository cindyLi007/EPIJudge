package epi.excercise.linked.list;

/**
 * EPI 7.13 variant take two singly linked lists of digits, and return teh list of their sum. most significant digit comes first
 * Leetcode 445
 */
public class IntAsListAddSigDigitFirst {

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int len1 = length(l1), len2 = length(l2);
    ListNode res=len1 > len2 ? helper(l1, l2, len1-len2) : helper(l2, l1, len2-len1);
    if (res.val>9) {
      ListNode l = new ListNode(1);
      l.next = res;
      res.val %= 10;
      return l;
    }
    return res;
  }

  private ListNode helper(ListNode longer, ListNode shorter, int offset) {
    if (longer==null) return null;
    ListNode next = helper(longer.next, offset==0 ? shorter.next : shorter,
        offset==0 ? 0 : offset-1);
    int sum = longer.val + (offset==0 ? shorter.val : 0);
    if (next !=null && next.val>9) {
      next.val %= 10;
      sum += 1;
    }
    ListNode node = new ListNode(sum);
    node.next = next;
    return node;
  }

  private int length(ListNode l) {
    int count = 0;
    while (l!=null) {
      count++;
      l=l.next;
    }
    return count;
  }


  public static void main(String... args) {
    ListNode l1 = new ListNode(9);
    ListNode l2 = new ListNode(9); l1.next = l2;
    ListNode l3 = new ListNode(8); l2.next = l3;
    ListNode l4 = new ListNode(9); l3.next = l4;
    ListNode l5 = new ListNode(1);
    ListNode l6 = new ListNode(0); l5.next = l6;
    ListNode l7 = new ListNode(9); l6.next = l7;
    IntAsListAddSigDigitFirst listAdd = new IntAsListAddSigDigitFirst();
    ListNode listNode = listAdd.addTwoNumbers(l1, l5);
    while (listNode!=null) {
      System.out.println(listNode.val + " -> ");
      listNode = listNode.next;
    }
  }
}
