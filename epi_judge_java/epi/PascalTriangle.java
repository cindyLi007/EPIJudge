package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalTriangle {
  @EpiTest(testfile = "pascal_triangle.tsv")

  public static List<List<Integer>> generatePascalTriangle(int numRows) {
    List<List<Integer>> res = new ArrayList<>();
    if (numRows<1) return res;
    res.add(Arrays.asList(1));
    for (int i = 1; i < numRows; i++) {
      List<Integer> curList = new ArrayList<>();
      List<Integer> prevList = res.get(i - 1);
      for (int j = 0; j <= i; j++) {
        curList.add( 0<j && j<i ? prevList.get(j-1) + prevList.get(j) : 1);
      }
      res.add(curList);
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
