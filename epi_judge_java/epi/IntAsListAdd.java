package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntAsListAdd {
  @EpiTest(testfile = "int_as_list_add.tsv")

  public static ListNode<Integer> addTwoNumbers(ListNode<Integer> L1,
                                                ListNode<Integer> L2) {
    int carry = 0;
    ListNode<Integer> res = new ListNode<>(0, null), prev = res;
    while (L1!=null || L2!=null || carry!=0) {
      int v1 = L1==null ? 0 : L1.data.intValue();
      int v2 = L2==null ? 0 : L2.data.intValue();
      int sum = v1+v2+carry;
      prev.next = new ListNode<>(sum%10, null);
      prev = prev.next;
      carry = sum/10;
      L1 = L1==null ? null: L1.next;
      L2 = L2==null ? null: L2.next;
    }
    return res.next;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
