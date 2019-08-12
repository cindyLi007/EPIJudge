package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class MatrixEnclosedRegions {

  // Time: O(M*N)
  public static void fillSurroundedRegions(List<List<Character>> board) {
    int M = board.size(), N = board.get(0).size();
    for (int i=0; i<M; i++) {
      markEdge(board, i, 0);
      markEdge(board, i, N-1);
    }
    for (int j=0; j<N; j++) {
      markEdge(board, 0, j);
      markEdge(board, M-1, j);
    }
    for (int i = 0; i < M; i++) {
      for (int j=0; j<N; j++) {
        board.get(i).set(j, board.get(i).get(j)=='X' ? 'W' : 'B');
      }
    }
  }

  private static class Point {
    int x, y;
    Point(int x, int y) {
      this.x=x;
      this.y=y;
    }
  }

  private static void markEdge(List<List<Character>> board, int x, int y) {
    int M=board.size(), N=board.get(0).size();
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
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
