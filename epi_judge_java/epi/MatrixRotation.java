package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MatrixRotation {

  public static void rotateMatrix(List<List<Integer>> squareMatrix) {
    int N=squareMatrix.size()-1;
    for (int i = 0; i < squareMatrix.size() / 2; i++) {
      for (int j = i; j < N-i; j++) {
        int temp = squareMatrix.get(i).get(j);
        squareMatrix.get(i).set(j, squareMatrix.get(N-j).get(i));
        squareMatrix.get(N-j).set(i, squareMatrix.get(N-i).get(N-j));
        squareMatrix.get(N-i).set(N-j, squareMatrix.get(j).get(N-i));
        squareMatrix.get(j).set(N-i, temp);
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
