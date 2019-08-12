package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ListCyclicRightShift {
  @EpiTest(testfile = "list_cyclic_right_shift.tsv")
  // Time: O(N), Space: O(1)
  public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L,
                                                           int k) {
    // compute the len of L
    if (L==null) return L;
    int len = 1;
    ListNode<Integer> run = L;
    while (run.next != null) {
      len++;
      run = run.next;
    }
    // now run is the last node in L
    k %= len;
    if (k==0) {
      return L;
    }
    run.next = L;
    int i = len - k;
    while (i-->0) {
      run = run.next;
    }
    L = run.next;
    run.next = null;
    return L;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
