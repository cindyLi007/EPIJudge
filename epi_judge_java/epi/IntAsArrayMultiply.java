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
    // boolean can do XOR
    int sign = num1.get(0) < 0 ^ num2.get(0) < 0 ? -1 : 1;
    int len1 = num1.size(), len2 = num2.size(), len = len1 + len2;
    /**
     * Collections.nCopies is an API to returns an immutable list consisting of N copies of the specified object.
     */
    List<Integer> res = new ArrayList<>(Collections.nCopies(len, 0));
    for (int i = len2 - 1; i >= 0; i--) {
      for (int j = len1 - 1; j >= 0; j--) {
        int mul = Math.abs(num2.get(i) * num1.get(j));
        res.set(i + j + 1, mul + res.get(i + j + 1));
//        res.set(i+j, res.get(i+j+1)/10 + res.get(i+j));
//        res.set(i+j+1, res.get(i+j+1)%10);
      }
    }
    int carry = 0;
    for (int i = len - 1; i >= 0; i--) {
      int sum = res.get(i) + carry;
      res.set(i, sum % 10);
      carry = sum / 10;
    }
    // Remove the leading zero
    int firstNotZero = 0;
    while (firstNotZero < len && res.get(firstNotZero) == 0) {
      firstNotZero++;
    }
    // Notice API asList and subList
    if (firstNotZero == len) return Arrays.asList(0);
    res = res.subList(firstNotZero, len);
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
