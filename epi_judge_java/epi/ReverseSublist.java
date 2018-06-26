package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseSublist {
  @EpiTest(testfile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    ListNode<Integer> aux = new ListNode<>(0, L);
    ListNode<Integer> run = aux;
    int pos = 1;
    while (pos++ < start) {
      run = run.next;
    }
    ListNode<Integer> subIt = run.next;
    while (pos++ <= finish) {
      ListNode<Integer> temp = subIt.next;
      subIt.next = temp.next;
      temp.next = run.next;
      run.next = temp;
    }
    return aux.next;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
