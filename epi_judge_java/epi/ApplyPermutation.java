package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApplyPermutation {
  // Time: O(N), Space: O(1)
  public static void applyPermutation(List<Integer> perm, List<Integer> A) {
    int N = perm.size();
    for (int i = 0; i < N; i++) {
      int next = i;
      while (perm.get(next) >= 0) {
        Collections.swap(A, i, perm.get(next));
        int temp = perm.get(next);
        perm.set(next, perm.get(next) - N);
        next = temp;
      }
    }

    for (int i = 0; i < N; i++) {
      perm.set(i, perm.get(i) + N);
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
