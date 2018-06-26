package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class SunsetView {

  private static class BuildingWithHeight {
    int index;
    int height;

    public BuildingWithHeight(int index, int height) {
      this.index = index;
      this.height = height;
    }
  }

  public static List<Integer>
  examineBuildingsWithSunset(Iterator<Integer> sequence) {
    List<Integer> res = new ArrayList<>();
    Deque<BuildingWithHeight> stack = new ArrayDeque<>();
    int id = 0;

    while (sequence.hasNext()) {
      Integer height = sequence.next();
      // this guarantee that the stack is in decreasing order
      while (!stack.isEmpty() && stack.peek().height <= height) {
        stack.pop();
      }
      stack.push(new BuildingWithHeight(id++, height));
    }

    // Notice, stack.stream() is still top to bottom order
    res = stack.stream().map(c -> c.index).collect(Collectors.toList());
    return res;
  }

  @EpiTest(testfile = "sunset_view.tsv")
  public static List<Integer>
  examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
    return examineBuildingsWithSunset(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
