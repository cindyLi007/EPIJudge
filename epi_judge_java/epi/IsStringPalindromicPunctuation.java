package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

/**
 * define a palindromic string to be a string which when all teh non-alphanumeric are removed it reads the same front to back ignoring case.
 */
public class IsStringPalindromicPunctuation {
  @EpiTest(testfile = "is_string_palindromic_punctuation.tsv")
  // Time O(N), space O(1)
  public static boolean isPalindrome(String s) {
    int i = 0, j = s.length() - 1;
    while (i < j) {
      while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
        i++;
      }
      while (j > i && !Character.isLetterOrDigit(s.charAt(j))) {
        j--;
      }
      if (i < j) {
        if (Character.toLowerCase(s.charAt(i++)) != Character.toLowerCase(s.charAt(j--))) {
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
