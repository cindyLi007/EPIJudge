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
    if (numRows <=0) return res;
    res.add(Arrays.asList(1));
    for (int i = 1; i < numRows; i++) {
      List<Integer> prev = res.get(i - 1);
      List<Integer> cur = new ArrayList<>();
      cur.add(prev.get(0));
      for (int j=1; j<prev.size(); j++) {
        cur.add(prev.get(j-1) + prev.get(j));
      }
      cur.add(prev.get(prev.size()-1));
      res.add(cur);
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
