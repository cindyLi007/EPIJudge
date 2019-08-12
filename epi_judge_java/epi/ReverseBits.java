package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseBits {
  private static long[] precomputedReverse = new long[(1 << 16)];

  static {
    for (int i=0; i < 1<<16; i++) {
      precomputedReverse[i] = reverse(i);
    }
  }

  private static long reverse(long x) {
    for (int i = 0, j = 15; i < j; i++, j--) {
      x = SwapBits.swapBits(x, i, j);
    }
    return x;
  }

  @EpiTest(testfile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    final int BIT_MASK = 0xFFFF;
    // why must use |, could not use +
    long res = precomputedReverse[(int)(x & BIT_MASK)] << 48 | precomputedReverse[(int)((x >> 16) & BIT_MASK)] << 32 |
            precomputedReverse[(int)((x>>32) & BIT_MASK)] << 16 | precomputedReverse[(int)((x>>48) & BIT_MASK)];
    return res;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
