package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsNumberPalindromic {
  @EpiTest(testfile = "is_number_palindromic.tsv")
  // Time: O(N), Space: O(1) N is number of digits in x
  public static boolean isPalindromeNumber(int x) {
    if (x<0) {
      return false;
    }
    if (x<10) {
      return true;
    }
    // Important: how to get the num of digits in an Integer
    int numOfDigits = (int)Math.floor(Math.log10(x)) + 1;
    int d = (int)Math.pow(10, numOfDigits - 1);
    while (d>0) {
      int left = x/d, right = x%10;
      if (left!=right) {
        return false;
      }
      x = (x%d)/10;
      d /=100;
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
