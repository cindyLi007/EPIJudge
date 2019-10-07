package epi.excercise.graph;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComputeMaxBlackRegionMultipleTimes {

  public int M, N;
  int[] id;
  int[] sz;
  List<List<Boolean>> image;

  public void computeMaxBlackRegion(List<List<Boolean>> image) {
    M = image.size();
    N = image.get(0).size();
    id = new int[M * N];
    sz = new int[M * N];
    this.image = image;
    // initialize Union Find
    Arrays.fill(id, -1);
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if (image.get(i).get(j)) {
          id[i * N + j] = i * N + j;
          sz[i * N + j] = 1;
        }
      }
    }

    // Union adjacent black points
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if (image.get(i).get(j)) {
          union(i, j, i - 1, j);
          union(i, j, i, j - 1);
        }
      }
    }

    printMaxRegion();
  }

  private void printMaxRegion() {
    int max = 0;
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        max = Math.max(max, sz[i * N + j]);
        if (sz[i * N + j] >= 1) {
          System.out.println(i + ", " + j + " size: " + sz[i * N + j]);
        }
      }
    }

    System.out.println("Max size is " + max);
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        System.out.print(id[i * N + j] + ",");
      }
      System.out.println();
    }
  }

  // set [x, y] to black and recompute the max region
  public void set(int x, int y) {
    if (image.get(x).get(y)) return;
    image.get(x).set(y, true);
    id[x * N + y] = x * N + y;
    sz[x * N + y] = 1;
    union(x, y, x - 1, y);
    union(x, y, x + 1, y);
    union(x, y, x, y - 1);
    union(x, y, x, y + 1);
    printMaxRegion();
  }

  private void union(int x, int y, int i, int j) {
    if (i < 0 || i == M || j < 0 || j == N || !image.get(i).get(j)) return;
    int p = find(x, y), q = find(i, j);
    if (sz[p] > sz[q]) {
      id[q] = p;
      sz[p] += sz[q];
      sz[q] = 0;
    } else {
      id[p] = q;
      sz[q] += sz[p];
      sz[p] = 0;
    }
  }

  private int find(int x, int y) {
    int p = x * N + y;
    while (p != id[p]) {
      id[p] = id[id[p]];
      p = id[p];
    }
    return p;
  }

  public static void main(String... args) {
    ComputeMaxBlackRegionMultipleTimes computeMaxBlackRegionMultipleTimes = new ComputeMaxBlackRegionMultipleTimes();
    List<List<Boolean>> image = buildList();
    computeMaxBlackRegionMultipleTimes.computeMaxBlackRegion(image);
    computeMaxBlackRegionMultipleTimes.set(3, 1);
  }

  public static List<List<Boolean>> buildList() {
    List<List<Boolean>> image = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      List<Boolean> cur = new ArrayList<>();
      if (i == 0) {
        cur.add(true);
        cur.add(true);
        cur.add(false);
        cur.add(false);
      } else if (i == 1) {
        cur.add(false);
        cur.add(false);
        cur.add(true);
        cur.add(true);
      } else if (i == 2) {
        cur.add(false);
        cur.add(true);
        cur.add(true);
        cur.add(false);
      } else {
        cur.add(true);
        cur.add(false);
        cur.add(false);
        cur.add(true);
      }
      image.add(cur);
    }
    return image;
  }

}

