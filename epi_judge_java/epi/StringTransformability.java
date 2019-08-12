package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class StringTransformability {

  @EpiTest(testfile = "string_transformability.tsv")

  // Uses BFS to find the least steps of transformation.
  public static int transformString(Set<String> D, String s, String t) {
    // build Graph from s
    Set<String> clonedD = new HashSet<>(D);
    Vertex vertexS = createVertex(clonedD, s, 0);

    Queue<Vertex> queue = new ArrayDeque<>();
    queue.add(vertexS);

    while (!queue.isEmpty()) {
      Vertex cur = queue.remove();
      for (String n : cur.neighbors) {
        if (n.equals(t)) {
          return cur.dist + 1;
        }
        Vertex vertex = createVertex(clonedD, n, cur.dist+1);
        if (vertex.neighbors.size() > 0) {
          queue.add(vertex);
        }
      }
    }

    return -1;
  }

  private static Vertex createVertex(Set<String> D, String s, int dist) {
    Vertex vertexS = new Vertex(s, dist);
    for (int i=0; i<s.length(); i++) {
      char[] chars = s.toCharArray();
      for (char c='a'; c<='z'; c++) {
        chars[i] = c;
        if (D.contains(String.valueOf(chars))) {
          vertexS.neighbors.add(String.valueOf(chars));
          D.remove(String.valueOf(chars));
        }
      }
    }
    return vertexS;
  }

  private static class Vertex {
    String label;
    List<String> neighbors;
    int dist;

    public Vertex(String label, int dist) {
      this.label = label;
      this.neighbors = new ArrayList<>();
      this.dist = dist;
    }
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
