package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * multiply 2 numbers stored in array and return result as an array. The sign is represented by the first digit in the array
 */
public class IntAsArrayMultiply {
  @EpiTest(testfile = "int_as_array_multiply.tsv")
  // Time: O(M*N), Space: O(1)
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    int M=num1.size(), N=num2.size(), len = M+N;
    // boolean can also XOR
    int sign = (num1.get(0) < 0) ^ (num2.get(0) < 0) ? -1 : 1;
    num1.set(0, Math.abs(num1.get(0)));
    num2.set(0, Math.abs(num2.get(0)));

    List<Integer> res = new ArrayList<>(Collections.nCopies(M+N, 0));
    for (int i = M - 1; i >= 0; i--) {
      for (int j = N - 1; j >= 0; j--) {
        int v = res.get(i+j+1) + num1.get(i) * num2.get(j);
        res.set(i+j, res.get(i+j) + v/10);
        res.set(i+j+1, v%10);
      }
    }
    int firstNonZeroIndex = 0;
    while (firstNonZeroIndex<len && res.get(firstNonZeroIndex)==0) firstNonZeroIndex++;
    if (firstNonZeroIndex==len) return Arrays.asList(0);
    res=res.subList(firstNonZeroIndex, len);
    res.set(0, sign * res.get(0));
    return res;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
