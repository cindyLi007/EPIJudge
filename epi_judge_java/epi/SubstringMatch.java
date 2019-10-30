package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SubstringMatch {
  @EpiTest(testfile = "substring_match.tsv")

  // Returns the index of the first character of the substring if found, -1
  // otherwise.
  // Time: O(m+n)
  public static int rabinKarp(String t, String s) {
    int M = s.length(), N = t.length();
    if (M >= N) return t.equals(s) ? 0 : -1;

    int BASE = 26;
    long hashS = 0, hashT = 0;
    long last = 1;
    // first compute s hash code
    for (int i = 0; i < M; i++) {
      hashS = hashS * BASE + s.charAt(i);
      hashT = hashT * BASE + t.charAt(i);
      last = i == 0 ? 1 : last * BASE;
    }

    for (int i = M; i <= N; i++) {
      if (hashT == hashS && t.substring(i - M, i).equals(s)) {
        return i - M;
      } else {
        if (i==N)
          return -1;
        hashT -= last * t.charAt(i - M);
        hashT = hashT * BASE + t.charAt(i);
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
        .runFromAnnotations(
            args, new Object() {
            }.getClass().getEnclosingClass())
        .ordinal());
  }
}