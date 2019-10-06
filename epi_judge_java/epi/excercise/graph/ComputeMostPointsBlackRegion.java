package epi.excercise.graph;

// EPI 18.2 variant 1
// Design an algorithm for cmputing the black rgegion that contains the most points

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ComputeMostPointsBlackRegion {



  public int M, N;

  public void computeMaxBlackRegion(List<List<Boolean>> image) {
    M = image.size();
    N = image.get(0).size();
    Set<Coordinate> res = new HashSet<>();
    for (int i=0; i<M; i++) {
      for (int j=0; j<N; j++) {
        if (image.get(i).get(j)) {
          Set<Coordinate> set = new HashSet<>();
          dfs(i, j, image, set);
          if (set.size() > res.size()) res = set;
        }
      }
    }
    res.stream().forEach(o -> System.out.print("[" + o.x + ", " + o.y + "],  "));
    System.out.println();
    System.out.println(res.size());
  }

  private void dfs(int i, int j, List<List<Boolean>> image, Set<Coordinate> set) {
    if (i<0 || i>=M || j<0 || j>=N || !image.get(i).get(j)) return;
    image.get(i).set(j, false);
    set.add(new Coordinate(i, j));
    for (Coordinate nextMove : Arrays.asList(new Coordinate(i+1, j), new Coordinate(i-1, j),
        new Coordinate(i, j+1), new Coordinate(i, j-1))) {
      dfs(nextMove.x, nextMove.y, image, set);
    }
  }

  public static void main(String... args) {
    ComputeMostPointsBlackRegion computeMostPointsBlackRegion = new ComputeMostPointsBlackRegion();
    List<List<Boolean>> image = new ArrayList<>();
    for (int i=0; i<4; i++) {
      List<Boolean> cur = new ArrayList<>();
      if (i==0) {
        cur.add(true); cur.add(true); cur.add(false); cur.add(false);
      } else if (i==1) {
        cur.add(false); cur.add(false); cur.add(true); cur.add(true);
      } else if (i==2) {
        cur.add(false); cur.add(true); cur.add(true); cur.add(false);
      } else {
        cur.add(true); cur.add(false); cur.add(false); cur.add(true);
      }
      image.add(cur);
    }
    computeMostPointsBlackRegion.computeMaxBlackRegion(image);

  }

}

class Coordinate {
  public Integer x;
  public Integer y;

  public Coordinate(Integer x, Integer y) {
    this.x = x;
    this.y = y;
  }
}
