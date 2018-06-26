package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashSet;
import java.util.Set;

public class DoListsOverlap {

  public static ListNode<Integer> overlappingLists(ListNode<Integer> l0,
                                                   ListNode<Integer> l1) {
    // first check whether l0 and l1 has cycle
    ListNode<Integer> loopStart0 = IsListCyclic.hasCycle(l0);
    ListNode<Integer> loopStart1 = IsListCyclic.hasCycle(l1);

    // case 1, neither of them has cycle
    if (loopStart0 == null && loopStart1 == null) {
      return DoTerminatedListsOverlap.overlappingNoCycleLists(l0, l1);
    }

    // case 2, one has cycle and one does not, so they must NO overlapping, THIS IS IMPORTANT CASE
    if ((loopStart0 == null && loopStart1 !=null) || (loopStart0 != null && loopStart1 ==null)) {
      return null;
    }

    // case 3, both have cycle, only they end in same cycle, they have overlapping, otherwise NO overlapping
    ListNode<Integer> run0 = loopStart0;
    // first check whether they are same cycle, run0 should hit the loopStart1 before loopStart0
    do {
      run0 = run0.next;
    } while (run0 != loopStart0 && run0 != loopStart1);

    if (run0 != loopStart1) {
      return null;
    }

    // since they hit to same cycle, they are must overlapping, we need know whether they overlap before cycle or from cycle
    if (loopStart0 == loopStart1) {
      int distance0 = distance(l0, loopStart0);
      int distance1 = distance(l1, loopStart1);
      if (distance0>distance1) {
        l0 = advance(l0, distance0 - distance1);
      } else if (distance0 < distance1) {
        l1 = advance(l1, distance1 - distance0);
      }
      while (l0!=l1) {
        l0=l0.next;
        l1=l1.next;
      }
      return l0;
    } else {
      return loopStart0;
    }

  }

  private static ListNode<Integer> advance(ListNode<Integer> l0, int i) {
    while (i-- > 0) {
      l0=l0.next;
    }
    return l0;
  }

  private static int distance(ListNode<Integer> n0, ListNode<Integer> n1) {
    int dis = 0;
    while (n0!=n1) {
      n0 = n0.next;
      dis++;
    }
    return dis;
  }

  @EpiTest(testfile = "do_lists_overlap.tsv")
  public static void
  overlappingListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                          ListNode<Integer> l1, ListNode<Integer> common,
                          int cycle0, int cycle1) throws Exception {
    if (common != null) {
      if (l0 == null) {
        l0 = common;
      } else {
        ListNode<Integer> it = l0;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }

      if (l1 == null) {
        l1 = common;
      } else {
        ListNode<Integer> it = l1;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }
    }

    if (cycle0 != -1 && l0 != null) {
      ListNode<Integer> last = l0;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l0;
      while (cycle0-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    if (cycle1 != -1 && l1 != null) {
      ListNode<Integer> last = l1;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l1;
      while (cycle1-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    Set<Integer> commonNodes = new HashSet<>();
    ListNode<Integer> it = common;
    while (it != null && !commonNodes.contains(it.data)) {
      commonNodes.add(it.data);
      it = it.next;
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingLists(finalL0, finalL1));

    if (!((commonNodes.isEmpty() && result == null) ||
          (result != null && commonNodes.contains(result.data)))) {
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
