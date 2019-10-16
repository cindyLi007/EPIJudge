package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class DoTerminatedListsOverlap {

  // Time: O(N) N is the total nodes in both lists
  public static ListNode<Integer>
  overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
    // compute len1 and len2
    int len0 = length(l0), len1 = length(l1);

    // advance long list
    while (len1 > len0) {
      l1 = l1.next;
      len1--;
    }
    while (len1 < len0) {
      l0 = l0.next;
      len0--;
    }

    // traverse both lists and compare nodes, if find same node, return
    while (l1 != l0) {
      l1 = l1.next;
      l0 = l0.next;
    }
    return l1;
  }

  // 用两个指针走两遍list 将两个list首尾串起来，如果一长一短但有common nodes必然会相交
  public static ListNode<Integer>
  overlappingNoCycleLists_tricky(ListNode<Integer> l0, ListNode<Integer> l1) {
    ListNode<Integer> pa = l0, pb = l1;
    while (pa!=pb) {
      pa = pa == null ? l1 : pa.next;
      pb = pb == null ? l0 : pb.next;
    }
    return pa;
  }

  // Java pass by value, so if we change the reference of the object, will not change it outside this method
  private static int length(ListNode<Integer> listNode) {
    int count = 0;
    while (listNode!=null) {
      count++;
      listNode = listNode.next;
    }
    return count;
  }

  @EpiTest(testfile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                 ListNode<Integer> l1, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l0 != null) {
        ListNode<Integer> i = l0;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l0 = common;
      }

      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
