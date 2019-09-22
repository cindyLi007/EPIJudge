package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseSublist {
  @EpiTest(testfile = "reverse_sublist.tsv")
  // Time: O(N)
  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    ListNode<Integer> aux = new ListNode<>(0, L);
    ListNode<Integer> run = aux;
    int pos = 1;
    while (pos < start) {
      run = run.next;
      pos++;
    }
    // now run is the prev node of the reverse begin node, subIt is the begin node of reverse, after the reverse, it will be
    // the last node of the subList
    ListNode<Integer> subIt = run.next;
    while (pos< finish) {
      ListNode<Integer> temp = subIt.next;
      subIt.next = temp.next;
      temp.next = run.next;
      run.next = temp;
      pos++;
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
