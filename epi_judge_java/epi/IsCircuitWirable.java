package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class IsCircuitWirable {

  public static class GraphVertex {
    public int d = 0;
    public List<GraphVertex> edges = new ArrayList<>();
  }

  // Time: O(V + E), Space: O(V)
  public static boolean isAnyPlacementFeasible_dfs(List<GraphVertex> graph) {
    for (GraphVertex origin : graph) {
      if (origin.d == 0 && !dfs(origin, 1)) return false;
    }
    return true;
  }

  private static boolean dfs(GraphVertex graphVertex, int color) {
    if (graphVertex.d == color) return true;
    if (graphVertex.d != 0) return false;
    graphVertex.d = color;
    for (GraphVertex neighbor : graphVertex.edges) {
      if (!dfs(neighbor, -color)) return false;
    }
    return true;
  }

  // BFS Time: O(V + E), Space: O(V)
  public static boolean isAnyPlacementFeasible_bfs(List<GraphVertex> graph) {
    Queue<GraphVertex> queue = new ArrayDeque<>();

    for (GraphVertex graphVertex : graph) {
      queue.add(graphVertex);

      while (!queue.isEmpty()) {
        GraphVertex cur = queue.poll();
        for (GraphVertex v : cur.edges) {
          if (v.d == 0) { // never visited
            v.d = cur.d + 1;
            queue.add(v);
          } else if (v.d == cur.d) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @EpiUserType(ctorParams = {int.class, int.class})
  public static class Edge {
    public int from;
    public int to;

    public Edge(int from, int to) {
      this.from = from;
      this.to = to;
    }
  }

  @EpiTest(testfile = "is_circuit_wirable.tsv")
  public static boolean isAnyPlacementFeasibleWrapper(TimedExecutor executor,
                                                      int k, List<Edge> edges)
      throws Exception {
    if (k <= 0)
      throw new RuntimeException("Invalid k value");
    List<GraphVertex> graph = new ArrayList<>();
    for (int i = 0; i < k; i++)
      graph.add(new GraphVertex());
    for (Edge e : edges) {
      if (e.from < 0 || e.from >= k || e.to < 0 || e.to >= k)
        throw new RuntimeException("Invalid vertex index");
      graph.get(e.from).edges.add(graph.get(e.to));
    }

    return executor.run(() -> isAnyPlacementFeasible_bfs(graph));
  }

  public static void main(String[] args) {
    System.exit(GenericTest
        .runFromAnnotations(
            args, new Object() {
            }.getClass().getEnclosingClass())
        .ordinal());
  }
}
