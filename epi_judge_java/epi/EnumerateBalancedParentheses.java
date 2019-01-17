package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class EnumerateBalancedParentheses {
  @EpiTest(testfile = "enumerate_balanced_parentheses.tsv")

  public static List<String> generateBalancedParentheses(int numPairs) {
    List<String> res = new ArrayList<>();
    generateBalancedParentheses(numPairs, numPairs, "", res);
    return res;
  }

  private static void generateBalancedParentheses(int leftRemain, int rightRemain, String s, List<String> res) {
    if (rightRemain == 0) {
      res.add(s);
    } else {
      // we guarantee that each string is valid to be final result
      if (leftRemain > 0) {
        generateBalancedParentheses(leftRemain-1, rightRemain, s+"(", res);
      }
      if (leftRemain < rightRemain) {
        generateBalancedParentheses(leftRemain, rightRemain-1, s+ ")", res);
      }
    }
  }

  @EpiTestComparator
  public static BiPredicate<List<String>, List<String>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
