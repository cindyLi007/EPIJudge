package epi.excercise.linked.list;

/**
   7.8 Variant Let m be a positive num and L a sorted singly linked list of integer. For each int K, if K appears more than
       m times in L, remove all nodes from L containing K.
 */
public class RemoveMtimeOccurItemFromSortedList {

  // Time: O(N), Space: O(1)
  public ListNode deleteDuplicates(ListNode L, int m) {
    if (L==null || L.next==null) return L;

    ListNode d = new ListNode(0), prev = d;
    d.next = L;

    while (L!=null && L.next!=null) {
      int count=1;
      while (L.next!=null && L.next.val == L.val) {
        L=L.next;
        count++;
      }
      if (count>m) {
        // here we only know L.next is not equal to previous item, but we don't know whether L.next appears how many times
        // so we will NOT move prev to prev.next. for ex. 1->2->3->3->4->4->5, m=2
        // we first skip 3, now prev.next points to 4, but we should not move prev to 4
        prev.next = L.next;
      } else {
        prev.next = L;
        prev = prev.next;
      }
      L = L.next;
    }
    return d.next;
  }
}
