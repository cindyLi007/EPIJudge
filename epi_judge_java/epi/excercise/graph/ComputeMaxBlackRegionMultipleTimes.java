package epi.excercise.graph;

import sun.swing.StringUIClientPropertyKey;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ComputeMaxBlackRegionMultipleTimes {

  public class ComputeMostPointsBlackRegion {

    public final int M, N;
    int[] id;
    int[] sz;

    public void computeMaxBlackRegion(List<List<Boolean>> image) {
      M = image.size();
      N = image.get(0).size();
      id = new int[M * N];
      sz = new int[M * N];
      Arrays.fill(sz, 1);
      for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
          id[i * N + j] = i * N + j;
        }
      }

      for (int i=0; i<M; i++) {
        for (int j=0; j<N; j+=) {
          if (image.get(i).get(j)) {
            union(i, j, i-1, j, image);
            union(i, j, i, j-1, image);
          }
        }
      }
    }

    private void union(int x, int y, int i, int j, List<List<Boolean>> image) {

    }

    private int dfs(int i, int j, List<List<Boolean>> image, String id) {
      if (i < 0 || i >= M || j < 0 || j >= N || !image.get(i).get(j) || id[i * N + j] != "") return;
      id[i * N + j] = id;
      int size = 1;
      for (Coordinate nextMove : Arrays.asList(new Coordinate(i + 1, j), new Coordinate(i - 1, j),
          new Coordinate(i, j + 1), new Coordinate(i, j - 1))) {
        size += dfs(nextMove.x, nextMove.y, image, id);
      }
      return size;
    }
  }


}
