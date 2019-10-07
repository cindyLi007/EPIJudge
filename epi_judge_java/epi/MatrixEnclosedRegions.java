package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class MatrixEnclosedRegions {
  static int M, N;

  // Time: O(M*N)
  public static void fillSurroundedRegions(List<List<Character>> board) {
    M = board.size();
    N = board.get(0).size();
    for (int i = 0; i < N; i++) {
      if (board.get(0).get(i) == 'W') {
        dfs(0, i, board);
      }
      if (board.get(M - 1).get(i) == 'W') {
        dfs(M - 1, i, board);
      }
    }
    for (int i = 0; i < M; i++) {
      if (board.get(i).get(0) == 'W') {
        dfs(i, 0, board);
      }
      if (board.get(i).get(N - 1) == 'W') {
        dfs(i, N - 1, board);
      }
    }
    change(board);
  }

  private static void dfs(int i, int j, List<List<Character>> board) {
    if (i < 0 || i == M || j < 0 || j == N || board.get(i).get(j) != 'W') return;
    board.get(i).set(j, 'X');
    dfs(i + 1, j, board);
    dfs(i - 1, j, board);
    dfs(i, j + 1, board);
    dfs(i, j - 1, board);
  }

  private static void change(List<List<Character>> board) {
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        board.get(i).set(j, board.get(i).get(j) == 'X' ? 'W' : 'B');
      }
    }
  }

  private static class Point {
    int x, y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  private static void markEdge(List<List<Character>> board, int x, int y) {
    int M = board.size(), N = board.get(0).size();
    /* DFS
    if (x<0 || y<0 || x>=M || y>=N || board.get(x).get(y)!='W') {
      return;
    }
    board.get(x).set(y, 'X');
    markEdge(board, x-1, y);
    markEdge(board, x+1, y);
    markEdge(board, x, y+1);
    markEdge(board, x, y-1);*/

    // BFS
    Queue<Point> queue = new ArrayDeque<>();
    queue.add(new Point(x, y));
    while (!queue.isEmpty()) {
      Point cur = queue.poll();
      int x1 = cur.x, y1 = cur.y;
      if (x1 >= 0 && x1 < M && y1 >= 0 && y1 < N && board.get(x1).get(y1) == 'W') {
        board.get(x1).set(y1, 'X');
        queue.add(new Point(x1 + 1, y1));
        queue.add(new Point(x1 - 1, y1));
        queue.add(new Point(x1, y1 + 1));
        queue.add(new Point(x1, y1 - 1));
      }
    }
  }

  @EpiTest(testfile = "matrix_enclosed_regions.tsv")
  public static List<List<Character>>
  fillSurroundedRegionsWrapper(List<List<Character>> board) {
    fillSurroundedRegions(board);
    return board;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
        .runFromAnnotations(
            args, new Object() {
            }.getClass().getEnclosingClass())
        .ordinal());
  }
}
