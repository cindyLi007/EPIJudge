package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * multiply 2 numbers stored in array and return result as an array. The sign is represented by the first digit in the array
 */
public class IntAsArrayMultiply {
  @EpiTest(testfile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    int sign1 = num1.get(0), sign2 = num2.get(0);
    int sign = sign1 > 0 ^ sign2 > 0 ? -1 : 1;
    num1.set(0, Math.abs(num1.get(0)));
    num2.set(0, Math.abs(num2.get(0)));
    int M=num1.size(), N=num2.size(), L=M+N;

    /**
     * Collections.nCopies is an API to returns an immutable list consisting of N copies of the specified object.
     * Since it return an immutable list, we must create a new Arraylist of it.
     */
    List<Integer> res = new ArrayList<>(Collections.nCopies(L, 0));
    for (int i=M-1; i>=0; i--) {
      for (int j=N-1; j>=0; j--) {
        int v = res.get(i + j + 1) + num1.get(i) * num2.get(j);
        res.set(i+j, res.get(i+j) + v/10);
        res.set(i+j+1, v%10);
      }
    }

    int firstNonZero = 0;
    while (firstNonZero < L && res.get(firstNonZero)==0) firstNonZero++;

    if (firstNonZero == L) return Arrays.asList(0);
    res = res.subList(firstNonZero, res.size());
    res.set(0, res.get(0) * sign);
    return res;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
