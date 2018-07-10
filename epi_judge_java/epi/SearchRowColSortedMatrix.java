package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchRowColSortedMatrix {
  @EpiTest(testfile = "search_row_col_sorted_matrix.tsv")

  // Time: O(m+n), because for each row and column, we only compare one elem in it
  public static boolean matrixSearch(List<List<Integer>> A, int x) {
    int r=A.size(), c=A.get(0).size();
    int i=0, j=c-1;
    while (i<r && j>=0) {
      Integer current = A.get(i).get(j);
      if (current==x) return true;
      if (current>x) { // x could not be in this column
        j--;
      }
      else { // x could not be in this row
        i++;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
