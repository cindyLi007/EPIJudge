package epi.excercise.dynamic.programming;

public class IsStringMatrixOnlyOnce {
  int N, row, col;

  public boolean isPatternContainedInGrid(int[][] grid, int[] pattern) {
    N = pattern.length;
    row = grid.length;
    col = grid[0].length;
    boolean[][][] dp = new boolean[N][row][col];
    boolean[][] visited = new boolean[row][col];
    for (int i=0; i<row; i++) {
      for (int j=0; j<col; j++) {
        if (dfs(grid, i, j, pattern, 0, dp, visited)) return true;
      }
    }
    return false;
  }

  private boolean dfs(int[][] grid, int i, int j, int[] pattern, int offset, boolean[][][] dp, boolean[][] visited) {
    if (offset == N) return true;
    if (i<0 || j<0 || i==row || j==col || dp[offset][i][j] || pattern[offset]!=grid[i][j] || visited[i][j]) return false;
    visited[i][j]=true;
    if (dfs(grid, i-1, j, pattern, offset+1, dp, visited) ||
        dfs(grid, i+1, j, pattern, offset+1, dp, visited) ||
        dfs(grid, i, j-1, pattern, offset+1, dp, visited) ||
        dfs(grid, i, j+1, pattern, offset+1, dp, visited))
    return true;
    dp[offset][i][j] = true;
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
