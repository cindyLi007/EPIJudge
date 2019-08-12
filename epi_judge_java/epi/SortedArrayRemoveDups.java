package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.Arrays;
import java.util.List;

public class SortedArrayRemoveDups {
  // Returns the number of valid entries after deletion.
  // Time: O(N), Space: O(1)
  public static int deleteDuplicates(List<Integer> A) {
    int  j=-1;
    for (int i=0; i<A.size(); i++) {
      // Notice, since element in List is Integer, even value is same, A.get(i)==A.get(j) always return false;
      if (j==-1 || Integer.compare(A.get(i), A.get(j))!=0) {
        A.set(++j, A.get(i));
      }
    }
    return j+1;
  }

  /**
   * Implement a function which takes as input an array and a key, and updates
   * the array so that all occurrences of the input key have been removed and the remaining
   * elements have been shifted left to fill the emptied indices. Return the number of
   * remaining elements. There are no requirements as to the values stored beyond the
   * last valid element.
   */
  public static int deleteItem(List<Integer> A, int key) {
    int j=-1;
    for (int i=0; i<A.size(); i++) {
      if (!A.get(i).equals(key)) {
        A.set(++j, A.get(i));
      }
    }
    return j+1;
  }

 /* public static void main(String... va) {
    int res = deleteItem(Arrays.asList(2, 11, 13, 5, 11, 5, 7, 11, 3), 11);
    System.out.println(res);
  }*/

  @EpiTest(testfile = "sorted_array_remove_dups.tsv")
  public static List<Integer> deleteDuplicatesWrapper(TimedExecutor executor,
                                                      List<Integer> A)
      throws Exception {
    int end = executor.run(() -> deleteDuplicates(A));
    return A.subList(0, end);
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
