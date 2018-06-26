package epi.excercise.linked.list;

public class DoublySortedListMerge {

  public static DoublyListNode merge(DoublyListNode l1, DoublyListNode l2) {
    DoublyListNode res = new DoublyListNode(0);
    DoublyListNode run = res;

    while (l1!=null && l2!=null) {
      if (l1.val<l2.val) {
        run.next = l1;
        l1.prev=run;
        l1=l1.next;
      } else {
        run.next = l2;
        l2.prev=run;
        l2=l2.next;
      }
      run = run.next;
    }

    run.next = l1 == null ? l2 : l1;
    return res.next;
  }

  public static void main(String... args) {
    DoublyListNode l1 = new DoublyListNode(1);
    DoublyListNode l2 = new DoublyListNode(2);
    DoublyListNode l3 = new DoublyListNode(3);
    DoublyListNode l4 = new DoublyListNode(4);
    DoublyListNode l5 = new DoublyListNode(5);
    DoublyListNode l6 = new DoublyListNode(6);
    l1.next = l3;
    l3.next = l5; l3.prev = l1;
    l5.prev = l3;
    l2.next = l4; l4.prev = l2;
    l4.next = l6; l6.prev = l4;

    DoublyListNode res = merge(l1, l2);

    while (res!=null) {
      System.out.print(res.val + " -> ");
      res = res.next;
    }
  }
}

class DoublyListNode {
  int val;
  DoublyListNode prev, next;

  DoublyListNode(int v) {
    this.val = v;
    prev = null;
    next = null;
  }
}