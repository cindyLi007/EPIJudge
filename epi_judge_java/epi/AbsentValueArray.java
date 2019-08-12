package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.BitSet;
import java.util.Iterator;

public class AbsentValueArray {

  // Time: O(N), Space: O(2^16 * 32bit) = 4 bytes * 2^6 K = 1/4 MB
  @EpiTest(testfile = "absent_value_array.tsv")
  public static int findMissingElement(Iterable<Integer> stream) {
    final int NUMBER_BUCKET = 1<<16;
    int[] count = new int[NUMBER_BUCKET];
    Iterator<Integer> iterator = stream.iterator();
    // first round of reading the file, count for each 16-bit MSB group of int in stream (the 16-bit LSB should be different
    // for each group, how many int
    while (iterator.hasNext()) {
      // >>> is logical shift, not keep the MSBit
      int idx = iterator.next() >>> 16;
      count[idx]++;
    }

    for (int i=0; i<NUMBER_BUCKET; i++) {
      if (count[i] < NUMBER_BUCKET) {
        BitSet bitSet = new BitSet(NUMBER_BUCKET);
        // second round of reading the file, match the 16-bit MSB and set the corresponding 16-bit LSB (mark this lower
        // 16-bit LSB is in file
        Iterator<Integer> it = stream.iterator();
        while (it.hasNext()) {
          int x = it.next();
          if ((x>>>16) == i) { // match 16-bit MSB
            bitSet.set((NUMBER_BUCKET-1) & x); // get the lower 16 bits of x
          }
        }
        for (int j=0; j<NUMBER_BUCKET; j++) {
          if (!bitSet.get(j)) {
            return i<<16 | j;
          }
        }
      }
    }
    throw new IllegalArgumentException("no missing element");
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
