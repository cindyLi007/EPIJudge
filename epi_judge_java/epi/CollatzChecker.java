package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.Set;

public class CollatzChecker {
  @EpiTest(testfile = "collatz_checker.tsv")

  public static boolean testCollatzConjecture(int n) {
    Set<Long> proved = new HashSet<>();
    Set<Long> thisTimeEncountered = new HashSet<>();
    // we skip even number, becasue if a odd number N is verified, then 2*N is verified, so we can skip all even number
    // we assume that 1 and 2 is proved
    for (int i=3; i<=n; i++) {
      long test = i;
      thisTimeEncountered.clear();
      while (test >= i) {
        if (!thisTimeEncountered.add(test)) {
          return false;
        }
        if (test % 2 == 1) {
          if (!proved.add(test)) {
            break;
          }
          long nextTest = test * 3 + 1;
          if (nextTest <= test) {
            throw new ArithmeticException();
          }
          test = nextTest;
        } else {
          test /=2;
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
