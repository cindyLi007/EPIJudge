package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

// EPI 5.11
public class NextPermutation {
  @EpiTest(testfile = "next_permutation.tsv")
  public static List<Integer> nextPermutation(List<Integer> perm) {
    int i = perm.size() - 2;

    // find i where p[i]<p[i+1]
    while (i>=0 && perm.get(i) >= perm.get(i+1)) i--;

    if (i==-1) return Collections.emptyList();

    // find the right most value in [i+1, len-1] where p[j]>p[i], the right most value must be the smallest val which > perm.get(i)
    // since sublist(i+1, len-1] is descending order
    for (int j=perm.size()-1; j>i; j--) {
      if (perm.get(j) > perm.get(i)) {
        Collections.swap(perm, i, j);
        break;
      }
    }

    Collections.reverse(perm.subList(i+1, perm.size()));
    return perm;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
