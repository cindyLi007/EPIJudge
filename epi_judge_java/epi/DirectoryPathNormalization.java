package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class DirectoryPathNormalization {
  @EpiTest(testfile = "directory_path_normalization.tsv")

  public static String shortestEquivalentPath(String path) {
    if (path.isEmpty()) {
      return path;
    }

    Deque<String> deque = new ArrayDeque<>();
    // special case, absolute path
    if (path.startsWith("/")) {
      deque.push("/");
    }

    String[] tokens = path.split("/");
    for (String token : tokens) {
      if (token.equals("..")) {
        // no upper level can go to, should record as is
        if (deque.isEmpty() || deque.peek().equals("..")) {
          deque.push(token);
        } else if (deque.peek().equals("/")) {
          throw new IllegalArgumentException("Path error, trying to go up root " + path);
        } else {
          deque.pop();
        }
      } else if (!token.equals(".") && !token.isEmpty()) {
        deque.push(token);
      }
    }

    StringBuilder sb = new StringBuilder();
    if (!deque.isEmpty()) {
      // Notice, we can get a reverse-order elements from bottom to top
      Iterator<String> iterator = deque.descendingIterator();
      String prev = iterator.next();
      sb.append(prev);
      while (iterator.hasNext()) {
        if (!prev.equals("/")) {
          sb.append("/");
        }
        prev = iterator.next();
        sb.append(prev);
      }
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
