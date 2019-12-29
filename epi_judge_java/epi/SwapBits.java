package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SwapBits {
  @EpiTest(testfile = "swap_bits.tsv")
  public static long swapBits(long x, int i, int j) {
    // a bit can be only 0 or 1, so we can flip the bit instead of exchange

    // first check whether the 2 bits are diff, if not, no need to swap
    long bit_i = (x>>>i & 1), bit_j = (x>>>j & 1);
    if (bit_i == bit_j) return x;

    // do not use reverse bit, since it will change the sign of bit, for example, ~0 is -1
    // now the value of bit_i and bit_j does not matter, we only need flip the ith bit and jth bit
    // any bit XOR 1 will flip, so we can use a bit mask, any bit XOR 0 still it self
    long y = (1L<<i) | (1L<<j);
    return x ^ y;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
