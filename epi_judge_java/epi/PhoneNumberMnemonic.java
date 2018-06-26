package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class PhoneNumberMnemonic {
  @EpiTest(testfile = "phone_number_mnemonic.tsv")
  // O(4^N * N)
  public static List<String> phoneMnemonic(String phoneNumber) {
    final String[] PHONE = new String[]{"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};
    List<String> res = new ArrayList<>();
    res.add("");
    char[] phoneChars = phoneNumber.toCharArray();
    for (int i=0; i<phoneChars.length; i++) {
      char[] str = PHONE[phoneChars[i] - '0'].toCharArray();
      List<String> temp = new ArrayList<>();
      for (char c : str) {
        for (String s : res) {
          temp.add(s + c);
        }
      }
      res=temp;
    }
    return res;
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
