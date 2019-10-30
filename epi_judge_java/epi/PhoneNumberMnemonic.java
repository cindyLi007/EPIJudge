package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class PhoneNumberMnemonic {
  static final String[] PHONE = new String[]{"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

  @EpiTest(testfile = "phone_number_mnemonic.tsv")
  // Time: O(4^N * N)
  public static List<String> phoneMnemonic(String phoneNumber) {
    List<String> res = new ArrayList();
    res.add("");
    for (int i=0; i<phoneNumber.length(); i++) {
      char[] chars = PHONE[phoneNumber.charAt(i) - '0'].toCharArray();
      List<String> temp = new ArrayList<>();
      for (char c : chars) {
        for (String s : res) {
          temp.add(s + c);
        }
      }
      res = temp;
    }
    return res;
  }

  // O(4^N * N) where N is the length of phoneNumber, the latter N is for copying string to result list
  public static List<String> phoneMnemonic_recursion(String phoneNumber) {
    List<String> res = new ArrayList<>();
    dfs(res, phoneNumber.toCharArray(), 0, new char[phoneNumber.length()]);
    return res;
  }

  private static void dfs(List<String> res, char[] phoneNumber, int index, char[] prefix) {
    if (index == phoneNumber.length) {
      res.add(String.valueOf(prefix));
    }
    else {
      char[] chars = PHONE[phoneNumber[index] - '0'].toCharArray();
      for (char c : chars) {
        prefix[index] = c;
        dfs(res, phoneNumber, index + 1, prefix);
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
            args, new Object() {
            }.getClass().getEnclosingClass())
        .ordinal());
  }
}
