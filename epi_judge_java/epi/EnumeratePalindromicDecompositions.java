package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import static epi.IsStringPalindromicPunctuation.isPalindrome;

public class EnumeratePalindromicDecompositions {
  @EpiTest(testfile = "enumerate_palindromic_decompositions.tsv")

  public static List<List<String>> palindromeDecompositions(String input) {
    List<List<String>> res = new ArrayList<>();
    palindrome(input, 0, res, new ArrayList<String>());
    return res;
  }

  private static void palindrome(String input, int start, List<List<String>> res, ArrayList<String> strings) {
    if (start == input.length()) {
      res.add(new ArrayList<>(strings));
    } else {
      // from start, build palindrome string
      for (int i = start; i < input.length(); i++) {
        String prefix = input.substring(start, i+1);
        if (isPalindrome(prefix)) {
          strings.add(prefix);
          palindrome(input, i+1, res, strings);
          strings.remove(strings.size() - 1);
        }
      }
    }
  }


  @EpiTestComparator
      public static BiPredicate < List<List<String>>,
      List < List<String>>> comp = (expected, result) -> {
    if (result == null) {
      return false;
    }
    expected.sort(new LexicographicalListComparator<>());
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
