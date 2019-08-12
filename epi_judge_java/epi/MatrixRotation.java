package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MatrixRotation {

  public static void rotateMatrix(List<List<Integer>> squareMatrix) {
    int N=squareMatrix.size()-1;
    for (int i = 0; i < squareMatrix.size() / 2; i++) {
      for (int j = i; j < N-i; j++) {
        int t2 = squareMatrix.get(j).get(i);
        int t3 = squareMatrix.get(i).get(N-j);
        int t4 = squareMatrix.get(N-j).get(N-i);
        int t1 = squareMatrix.get(N-i).get(j);
        squareMatrix.get(j).set(i, t1);
        squareMatrix.get(i).set(N-j, t2);
        squareMatrix.get(N-j).set(N-i, t3);
        squareMatrix.get(N-i).set(j, t4);
      }
    }
  }

  @EpiTest(testfile = "matrix_rotation.tsv")
  public static List<List<Integer>>
  rotateMatrixWrapper(List<List<Integer>> squareMatrix) {
    rotateMatrix(squareMatrix);
    return squareMatrix;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
