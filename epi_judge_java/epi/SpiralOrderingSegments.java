package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrderingSegments {
  @EpiTest(testfile = "spiral_ordering_segments.tsv")

  public static List<Integer>
  matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    List<Integer> res = new ArrayList<>();
    if (squareMatrix.size()==0 || squareMatrix.get(0).size()==0) {
      return res;
    }
    int r0 = 0, r1=squareMatrix.size()-1, c0=0, c1=squareMatrix.get(0).size()-1;
    while (r0<r1 && c0<c1) {
      for (int i=c0; i<c1; i++) res.add(squareMatrix.get(r0).get(i));
      for (int i=r0; i<r1; i++) res.add(squareMatrix.get(i).get(c1));
      for (int i=c1; i>c0; i--) res.add(squareMatrix.get(r1).get(i));
      for (int i=r1; i>r0; i--) res.add(squareMatrix.get(i).get(c0));
      r0++; r1--; c0++; c1--;
    }
    if (r0==r1) {
      res.add(squareMatrix.get(r0).get(r1));
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
