package epi.excercise.dynamic.programming;

// EPI 16.5 variant 1
public class IsStringMatrixOnlyOnce {
  int N, row, col;

  // Time: O(M*N*|S|), Space: O(M*N)
  public boolean isPatternContainedInGrid(int[][] grid, int[] pattern) {
    N = pattern.length;
    row = grid.length;
    col = grid[0].length;
    boolean[][] visited = new boolean[row][col];
    for (int i=0; i<row; i++) {
      for (int j=0; j<col; j++) {
        if (dfs(grid, i, j, pattern, 0, visited)) return true;
      }
    }
    return false;
  }

  private boolean dfs(int[][] grid, int i, int j, int[] pattern, int offset, boolean[][] visited) {
    if (offset == N) return true;
    if (i<0 || j<0 || i==row || j==col || pattern[offset]!=grid[i][j] || visited[i][j]) return false;
    visited[i][j]=true;
    if (dfs(grid, i-1, j, pattern, offset+1, visited) ||
        dfs(grid, i+1, j, pattern, offset+1, visited) ||
        dfs(grid, i, j-1, pattern, offset+1, visited) ||
        dfs(grid, i, j+1, pattern, offset+1, visited))
    return true;

    visited[i][j]=false;
    return false;
  }

  public static void main(String... args) {
    int[][] grid = new int[][]{{1, 2, 3}, {3, 4, 5}, {5, 6, 7}};
    int[] pattern = new int[]{2, 3, 5, 7, 6};
    int[] pattern_1 = new int[]{2, 3, 2, 4, 6};
    IsStringMatrixOnlyOnce isStringMatrixOnlyOnce = new IsStringMatrixOnlyOnce();
    System.out.println(isStringMatrixOnlyOnce.isPatternContainedInGrid(grid, pattern)); // return true;
    System.out.println(isStringMatrixOnlyOnce.isPatternContainedInGrid(grid, pattern_1)); // return false;
  }
}
