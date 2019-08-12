package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApplyPermutation {
  // Time: O(N), Space: O(1)
  public static void applyPermutation(List<Integer> perm, List<Integer> A) {
    for (int i = 0; i < A.size(); i++) {
      int cur = i;
      while (perm.get(cur) >= 0) {
        int loc = perm.get(cur); // what is the pos of A[next] should go
        // each time swap value in loc and i, use i as the temp storage for next-processing value
        // the value in index i is always we are procssing value, the value is loc is the next round we should processing value
        Collections.swap(A, loc, i);
        // set index of current procesing item to negative to indiate we have permuted this index
        perm.set(cur, perm.get(cur) - A.size());
        // we will process loc for next round
        cur = loc;
      }
    }

    // restore perm
    for (int i=0; i<A.size(); i++) {
      perm.set(i, perm.get(i) + A.size());
    }
  }

  public static void applyPermutation_withOutChangePerm(List<Integer> perm, List<Integer> A) {
    int N = A.size();
    for (int i = 0; i < N; i++) {
      /* we only permute for this cycle when i is the most left pos of this cycle, this is same purpose as set perm value
      to negative, to avoid duplicated permute same cycle. we only do permute once and do it when find the left most pos of
      a cycle. If a permute target pos is < i, that means the target pos must have been visited in the previous "for" loop,
      we already did permute for this cycle, so should skip
       */
      if (isMostLeftPosInCyclic(i, perm)) {
        permute(perm, A, i);
      }
    }
  }

  private static void permute(List<Integer> perm, List<Integer> A, int start) {
    int loc = perm.get(start);
    int curVal = A.get(start);
    while (loc != start) {
      int temp = A.get(loc);
      A.set(loc, curVal);
      curVal = temp;
      loc = perm.get(loc);
    }
    A.set(loc, curVal);
  }

  private static boolean isMostLeftPosInCyclic(int start, List<Integer> perm) {
    int j = perm.get(start);
    while (j != start) {
      if (j<start) return false;
      j=perm.get(j);
    }
    return true;
  }

  @EpiTest(testfile = "apply_permutation.tsv")
  public static List<Integer> applyPermutationWrapper(List<Integer> perm,
                                                      List<Integer> A) {
    applyPermutation(perm, A);
    return A;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
