package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.Set;

public class IsStringPermutableToPalindrome {
  @EpiTest(testfile = "is_string_permutable_to_palindrome.tsv")

  // Time: O(N), N is s length, Space: O(c), c is the number of distinct char
  public static boolean canFormPalindrome(String s) {
    if (s.length()<=1) {
      return true;
    }

    Set<Character> charWithOddFrequency = new HashSet<>();
    for (int i=0; i<s.length(); i++) {
      char c = s.charAt(i);
      if (charWithOddFrequency.contains(c)) {
        charWithOddFrequency.remove(c);
      } else {
        charWithOddFrequency.add(c);
      }
    }
    return charWithOddFrequency.size()<=1;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
