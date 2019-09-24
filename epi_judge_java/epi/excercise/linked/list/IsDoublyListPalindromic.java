package epi.excercise.linked.list;

/**
 * EPI 7.11 variant: Write a program that test whether a singly linked list is palindromic when the list is doubly linked
 * and you have pointers to the head and the tail
 */
public class IsDoublyListPalindromic {

  public static boolean isLinkedListAPalindrome(DoublyListNode<Integer> head, DoublyListNode<Integer> tail) {
    while (head!=tail) {
      if (!head.val.equals(tail.val)) return false;
      if (head.next == tail) return true;
      head = head.next;
      tail = tail.prev;
    }
    return true;
  }

  public static void main(String... args) {
    DoublyListNode<Integer> l0 = new DoublyListNode<>(2);
    DoublyListNode<Integer> l1 = new DoublyListNode<>(3); l0.next = l1; l1.prev = l0;
    DoublyListNode<Integer> l2 = new DoublyListNode<>(5); l1.next = l2; l2.prev = l1;
//    DoublyListNode<Integer> l3 = new DoublyListNode<>(5); l2.next = l3; l3.prev = l2;
    DoublyListNode<Integer> l3 = new DoublyListNode<>(3); l2.next = l3; l3.prev = l2;
    DoublyListNode<Integer> l4 = new DoublyListNode<>(1); l3.next = l4; l4.prev = l3;
    System.out.println(isLinkedListAPalindrome(l0, l4));
  }
}
