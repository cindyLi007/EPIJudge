package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class IntAsArrayIncrement {
  /**
   * O(N)
   */
  @EpiTest(testfile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    int i = A.size() - 1;
    for (; i >= 0; i--) {
      if (A.get(i)==9) A.set(i, 0);
      else {
        A.set(i, A.get(i)+1);
        break;
      }
    }
    if (i<0) {
      A.add(0, 1);
    }
    return A;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
