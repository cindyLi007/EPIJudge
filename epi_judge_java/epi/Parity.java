package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class Parity {
  @EpiTest(testfile = "parity.tsv")
  public static short parity_brute_force(long x) {
    short res = 0;
    while (x>0) {
      res ^= (x&1);
      x>>>=1;
    }
    return res;
  }

  @EpiTest(testfile = "parity.tsv")
  /**
   * if k is the number of bits set to 1, this method is O(k) instead of O(N) N is # of bits
   */
  public static short parity_eraseLowestOne(long x) {
    short res = 0;
    while (x>0) {
      res ^= 1;
      // x & (x-1) will erase the lowest 1.
      x &= (x-1);
    }
    return res;
  }

  @EpiTest(testfile = "parity.tsv")
  /**
   * The XOR of a group of bits EQUALS to XOR of one bit by one bit. So The XOR of a group of bits is its parity.
   * This method is divide and conquer
   */
  public static short parity_usingXor(long x) {
    x ^= x>>>32; // first XOR left 32 bits and right 32 bits, only the right 32 bits counts for result
    x ^= x>>>16; // only the right 16 bits counts for result
    x ^= x>>>8;
    x ^= x>>>4;
    x ^= x>>>2;
    x ^= x>>>1;
    return (short) (x & 1);
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
