package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntersectSortedArrays {
  @EpiTest(testfile = "intersect_sorted_arrays.tsv")

  // Time: O(NLgM), Space: O(1)
  public static List<Integer> intersectTwoSortedArrays_BinarySearch(List<Integer> A,
                                                       List<Integer> B) {
    List<Integer> res = new ArrayList<>();
    for (int i=0; i<A.size(); i++) {
      // IMPORTANT: for Object Integer, must use Integer.equals to compare 2 Integers, even the intVal are equal
      // if use A.get(i)!=A.get(i-1), always return false even value are same
      if ((i==0 || !A.get(i).equals(A.get(i-1))) && Collections.binarySearch(B, A.get(i))>=0) {
        res.add(A.get(i));
      }
    }

    return res;
  }

  @EpiTest(testfile = "intersect_sorted_arrays.tsv")
  // Time: O(M+N)
  public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                       List<Integer> B) {
    List<Integer> res = new ArrayList<>();
    int i=0, j=0;

    while (i < A.size() && j < B.size()) {
      if ((i==0 || !A.get(i-1).equals(A.get(i))) && A.get(i).equals(B.get(j))) {
        res.add(A.get(i));
        i++;
        j++;
      } else if (A.get(i)<B.get(j)) { // for compare "<", we can use Object intVal directly, because "<" is for primitive
        i++;
      } else {
        j++;
      }
    }

    return res;

  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
