package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class IsStringInMatrix {
  static int N, row, col;

  // Time: O(N*M*s), s is the length of pattern, Space: O(N*M*s)
  @EpiTest(testfile = "is_string_in_matrix.tsv")
  public static boolean isPatternContainedInGrid(List<List<Integer>> grid,
                                                 List<Integer> pattern) {
    N=pattern.size();
    row=grid.size();
    col=grid.get(0).size();
    // dp store the combination of [x, y] in grid and index in pattern, if that index is set, means this combo has been visited
    // and could not match the pattern
    int[][] dp = new int[row][col];
    for (int i=0; i<row; i++) {
      for (int j=0; j<col; j++) {
        if (dfs(grid, i, j, pattern, 0, dp)) return true;
      }
    }
    return false;
  }

  private static boolean dfs(List<List<Integer>> grid, int i, int j, List<Integer> pattern, int idx, int[][] dp) {
    if (idx==N)
      return true; // run out the string, get a match
    if (i<0 || j<0 || i==row || j==col || (dp[i][j] & (1<<idx)) != 0 || pattern.get(idx) != grid.get(i).get(j))
      return false;

    if (dfs(grid, i-1, j, pattern, idx+1, dp) || dfs(grid, i, j-1, pattern, idx+1, dp)
                  || dfs(grid, i+1, j, pattern, idx+1, dp) || dfs(grid, i, j+1, pattern, idx+1, dp))
      return true;

    dp[i][j] |= (1 << idx);
    return false;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
