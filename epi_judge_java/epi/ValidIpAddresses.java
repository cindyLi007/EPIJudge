package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.EpiTestComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class ValidIpAddresses {
  @EpiTest(testfile = "valid_ip_addresses.tsv")
  // this problem is about how to put "." in the middle of s
  public static List<String> getValidIpAddress(String s) {
    List<String> res = new ArrayList<>();
    getValidIpAddress(s, 0, res, "");
    return res;
  }

  private static void getValidIpAddress(String s, int index, List<String> res, String ip) {
    if (index == 4) {
      if (s.length() == 0) {
        res.add(ip);
      }
      return;
    }
    for (int i = 1; i < 4 && i <= s.length(); i++) {
      String currentIP = s.substring(0, i);
      if (isValid(currentIP)) {
        getValidIpAddress(s.substring(i), index + 1, res, ip.length() == 0 ? currentIP : ip + "." + currentIP);
      }
    }
  }

  private static boolean isValid(String currentIP) {
    if (currentIP.length()>1 && currentIP.startsWith("0")) {
      return false;
    }
    int i = Integer.parseInt(currentIP);
    return i>=0 && i<=255;
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
