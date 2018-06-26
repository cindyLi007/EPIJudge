package epi.excercise.primitive.types;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayOperations {

  public static void main(String[] args) {
    // instantiate a 2D array, must either define both dimension, or explicitly assign value.
    // this "new int[3][1]{{1}, {2}, {3}}" or new int[3][]{{1}, {2}, {3}}could not compile
    int[][] A = new int[3][1];
    System.out.println("rows is " + A.length + " column is " + A[0].length);

    int[] B = new int[]{4, 10, 20, 7, 5};
    // we can only use binarySearch API when array is sorted, otherwise could not return correct index
    int i = Arrays.binarySearch(B, 7);

    // if the new copied array length > original array length, the last elements are 0
    int[] C = Arrays.copyOf(B, B.length+1);
    // two arrays are equal only when they have same elements and same length
    System.out.println("B and C are equals " + Arrays.equals(B, C));

    // D is [10, 20], the "to" index is exclusive
    int[] D = Arrays.copyOfRange(B, 1, 3);

    Arrays.fill(D, 42);

    Arrays.sort(B);
    Arrays.stream(B).forEach(num-> System.out.printf("%1$3s", num));
    System.out.println();

    // Comparator can only apply for Object array, not primitive array, primitive array could not be auto-box or cast to
    // Object array. for example, (Integer[])B could NOT compile
    Integer[] E = new Integer[]{4, 5, 20, 1};
    Arrays.sort(E, Comparator.reverseOrder());
    Arrays.stream(E).forEach(num -> System.out.printf("%1$3s", num));
    System.out.println();

    // array.toString() return its hashcode, which is useless
    System.out.println(B.toString());
  }
}
