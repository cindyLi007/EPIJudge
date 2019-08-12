package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class EvenOddListMerge {
  @EpiTest(testfile = "even_odd_list_merge.tsv")

  public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
    // corner case
    if (L==null || L.next == null) {
      return L;
    }

    ListNode<Integer> run = L, prev = L;
    ListNode<Integer> dummy = new ListNode<>(-1, L.next);
    int count = 0;
    while (run.next!=null) {
      prev = run;
      ListNode<Integer> temp = run.next;
      run.next = run.next.next;
      run = temp;
      count ^= 1;
    }
    if (count==1) {
      prev.next = dummy.next;
    } else {
      run.next = dummy.next;
    }
    return L;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
