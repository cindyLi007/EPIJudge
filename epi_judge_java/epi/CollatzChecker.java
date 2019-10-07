package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.Set;

public class CollatzChecker {
  @EpiTest(testfile = "collatz_checker.tsv")

  // hard to tell time complexity
  // in 2 cases should return false: 1st if when we calculate we encounter a previous number, that implies a loop
  // 2nd if sequence goes to infinity
  public static boolean testCollatzConjecture(int n) {
    Set<Long> proved = new HashSet<>();
    Set<Long> thisTimeEncountered = new HashSet<>();
    // we skip even number, because if a odd number N is verified, then 2*N is verified, so we can skip all even number
    // we assume that 1 and 2 is proved
    for (int i=3; i<=n; i+=2) {
      long test = i;
      thisTimeEncountered.clear();

      while (test >= i) {
        if (!thisTimeEncountered.add(test)) { // 1st fail case
          return false;
        }
        if (test % 2 == 1) {
          if (!proved.add(test)) {
            break;
          }
          long nextTest = test * 3 + 1;
          if (nextTest <= test) { // 2nd fail case, notice we can only throw Runtime Exception
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
