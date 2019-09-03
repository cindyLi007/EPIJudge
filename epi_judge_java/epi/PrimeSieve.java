package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class PrimeSieve {
  @EpiTest(testfile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  // Time: O(N), Space: O(N) for notPrimesNumber
  public static List<Integer> generatePrimes(int n) {
    if (n<2) {
      return Collections.emptyList();
    }
    List<Integer> res = new ArrayList<>();
    res.add(2);
    Set<Integer> notPrimesNumber = new HashSet<>();
    // each time stripe is 2, because we can ignore all even number
    for (int i = 3; i <= n; i+=2) {
      if (!notPrimesNumber.contains(i)) {
        res.add(i);
        // j start from i because we should start from i*i, all non Prime number less than i*i has been hit by previous smaller primes
        for (int j=i; j<=n/i; j+=2) { // the reson we stripe 2 is i must be a odd, so j=i=> j+1 is an even number which must be a nonprime
          notPrimesNumber.add(j*i);
        }
      }
    }

    return res;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
