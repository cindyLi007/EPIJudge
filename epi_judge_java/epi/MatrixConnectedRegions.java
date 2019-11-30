package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class MatrixConnectedRegions {

  // Time: O(M*N)
  public static void flipColor_BFS(int x, int y, List<List<Boolean>> image) {
    boolean color = image.get(x).get(y);
    int M = image.size(), N = image.get(0).size();

    Queue<int[]> queue = new ArrayDeque<>();
    // always first flip before push it to queue, to avoid duplicate process same point
    image.get(x).set(y, !color);
    queue.offer(new int[]{x, y});

    while (!queue.isEmpty()) {
      int[] point = queue.poll();
      int i = point[0], j = point[1];
      for (int[] p : Arrays.asList(new int[]{i + 1, j}, new int[]{i - 1, j}, new int[]{i, j + 1}, new int[]{i, j - 1})) {
        if (p[0] >= 0 && p[0] < M && p[1] >= 0 && p[1] < N && image.get(p[0]).get(p[1]) == color) {
          image.get(p[0]).set(p[1], !color);
          queue.offer(p);
        }
      }
    }
  }

  // Time: O(M*N)
  public static void flipColor(int x, int y, List<List<Boolean>> image) {
    flipColorHelper(x, y, image, image.get(x).get(y));
  }

  private static void flipColorHelper(int x, int y, List<List<Boolean>> image, boolean color) {
    int M = image.size(), N = image.get(0).size();
    if (x == M || x < 0 || y < 0 || y == N || image.get(x).get(y) != color) {
      return;
    }
    image.get(x).set(y, !color);
    flipColorHelper(x + 1, y, image, color);
    flipColorHelper(x - 1, y, image, color);
    flipColorHelper(x, y + 1, image, color);
    flipColorHelper(x, y - 1, image, color);
  }

  @EpiTest(testfile = "painting.tsv")
  public static List<List<Integer>> flipColorWrapper(TimedExecutor executor,
                                                     int x, int y,
                                                     List<List<Integer>> image)
      throws Exception {
    List<List<Boolean>> B = new ArrayList<>();
    for (int i = 0; i < image.size(); i++) {
      B.add(new ArrayList<>());
      for (int j = 0; j < image.get(i).size(); j++) {
        B.get(i).add(image.get(i).get(j) == 1);
      }
    }

    executor.run(() -> flipColor(x, y, B));

    image = new ArrayList<>();
    for (int i = 0; i < B.size(); i++) {
      image.add(new ArrayList<>());
      for (int j = 0; j < B.get(i).size(); j++) {
        image.get(i).add(B.get(i).get(j) ? 1 : 0);
      }
    }

    return image;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
        .runFromAnnotations(
            args, new Object() {
            }.getClass().getEnclosingClass())
        .ordinal());
  }
}
