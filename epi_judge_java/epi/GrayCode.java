package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

/**
 * from wiki grayCode = (num >> 1) ^ num
 * num is from 0 to N
 */
public class GrayCode {

  public static List<Integer> grayCode(int numBits) {
    /*List<Integer> res = new ArrayList<>();
    int size = (int) Math.pow(2, numBits);
    for (int i = 0; i < size; i++) {
      res.add((i >> 1) ^ i);
    }*/
    return grayCodeHelper(numBits);
  }

  // Time: O(2^N), because we render 2^N numbers Space: O(N)
  private static List<Integer> grayCodeHelper(int numBits) {
    if (numBits == 0) {
      return new ArrayList<>(Arrays.asList(0));
    }

    List<Integer> lowerBitRes = grayCodeHelper(numBits - 1);
    int x = 1 << (numBits - 1);
    for (int i = lowerBitRes.size() - 1; i >= 0; i--) {
      lowerBitRes.add(x ^ lowerBitRes.get(i));
    }

    return lowerBitRes;
  }

  private static boolean differsByOneBit(int x, int y) {
    // which bits differ between 2 intergers can be calculated by x ^ y, for example, 3^6
    // 011 ^ 110 = 101, which means there are 2 bits differ. If there is only one bit differ,
    // must satisfy (bitDifference & (bitDifference - 1)) == 0, because there is only one bit is 1
    // so bitDifference - 1 is sth. like 001111, & 010000 must be 0
    int bitDifference = x ^ y;
    return bitDifference != 0 && (bitDifference & (bitDifference - 1)) == 0;
  }

  @EpiTest(testfile = "gray_code.tsv")
  public static void grayCodeWrapper(TimedExecutor executor, int numBits)
      throws Exception {
    List<Integer> result = executor.run(() -> grayCode(numBits));

    int expectedSize = (1 << numBits);
    if (result.size() != expectedSize) {
      throw new TestFailure("Length mismatch: expected " +
                            String.valueOf(expectedSize) + ", got " +
                            String.valueOf(result.size()));
    }
    for (int i = 1; i < result.size(); i++)
      if (!differsByOneBit(result.get(i - 1), result.get(i))) {
        if (result.get(i - 1).equals(result.get(i))) {
          throw new TestFailure("Two adjacent entries are equal");
        } else {
          throw new TestFailure(
              "Two adjacent entries differ by more than 1 bit");
        }
      }

    Set<Integer> uniq = new HashSet<>(result);
    if (uniq.size() != result.size()) {
      throw new TestFailure("Not all entries are distinct: found " +
                            String.valueOf(result.size() - uniq.size()) +
                            " duplicates");
    }
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
