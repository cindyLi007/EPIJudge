package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IsValidSudoku {
  @EpiTest(testfile = "is_valid_sudoku.tsv")

  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
    int size = partialAssignment.size();
    // check rows
    for (int r=0; r<size; r++) {
      if (hasDuplicate(partialAssignment, r, r+1, 0, size)) {
        return false;
      }
    }
    // check columns
    for (int c=0; c<size; c++) {
      if (hasDuplicate(partialAssignment, 0, size, c, c+1)) {
        return false;
      }
    }
    // check 9 cube
    int radius = (int) Math.sqrt(size);
    for (int c=0; c<radius; c++) {
      for (int r = 0; r < radius; r++) {
        if (hasDuplicate(partialAssignment, radius*c, radius*(c+1), radius*r, radius*(r+1))) {
          return false;
        }
      }
    }
    return true;
  }

  private static boolean hasDuplicate(List<List<Integer>> partialAssignment, int startRow, int endRow, int startCol, int endCol) {
    BitSet set = new BitSet(10);
    for (int i = startRow; i < endRow; i++) {
      for (int j = startCol; j < endCol; j++) {
        if (partialAssignment.get(i).get(j)!=0 && set.get(partialAssignment.get(i).get(j))) {
          return true;
        }
        set.set(partialAssignment.get(i).get(j));
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
