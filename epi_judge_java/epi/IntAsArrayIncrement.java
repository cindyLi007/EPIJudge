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
    int i=A.size()-1;
    for (; i>=0; i--) {
      int sum = A.get(i)+1;
      if (sum<=9) {
        A.set(i, sum);
        break;
      } else {
        A.set(i, 0);
      }
    }
    /**
     * Notice should be -1, not 0, because when the whole loop is done, i == -1
     */
    if (i==-1) {
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
