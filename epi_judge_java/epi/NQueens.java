package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class NQueens {
  @EpiTest(testfile = "n_queens.tsv")

  public static List<List<Integer>> nQueens(int n) {
    List<List<Integer>> res = new ArrayList<>();
    nQueens(n, 0, new ArrayList<Integer>(), res);
    return res;
  }

  private static void nQueens(int n, int rowIndex, ArrayList<Integer> columns, List<List<Integer>> res) {
    if (rowIndex == n) {
      res.add(new ArrayList<>(columns));
      return;
    }
    for (int i=0; i<n; i++) {
      columns.add(i);
      if (valid(columns)) {
        nQueens(n, rowIndex + 1, columns, res);
      }
      columns.remove(columns.size() - 1);
    }
  }

  private static boolean valid(ArrayList<Integer> columns) {
    int index = columns.size() - 1;
    for (int i = 0; i < index; i++) {
      int diff = Math.abs(columns.get(index) - columns.get(i));
      if (diff == 0 || diff == index - i) {
        return false;
      }
    }
    return true;
  }


  @EpiTestComparator
      public static BiPredicate < List<List<Integer>>,
      List < List<Integer>>> comp = (expected, result) -> {
    if (result == null) {
      return false;
    }
    expected.sort(new LexicographicalListComparator<>());
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
