package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.BitSet;
import java.util.List;

public class SearchForMissingElement {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class DuplicateAndMissing {
    public Integer duplicate;
    public Integer missing;

    public DuplicateAndMissing(Integer duplicate, Integer missing) {
      this.duplicate = duplicate;
      this.missing = missing;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      DuplicateAndMissing that = (DuplicateAndMissing)o;

      if (!duplicate.equals(that.duplicate)) {
        return false;
      }
      return missing.equals(that.missing);
    }

    @Override
    public int hashCode() {
      int result = duplicate.hashCode();
      result = 31 * result + missing.hashCode();
      return result;
    }

    @Override
    public String toString() {
      return "duplicate: " + duplicate + ", missing: " + missing;
    }
  }

  @EpiTest(testfile = "find_missing_and_duplicate.tsv")
  // Time: O(N), Space: O(1)
  public static DuplicateAndMissing findDuplicateMissing(List<Integer> A) {
    int xor = 0;
    for (int i=0; i<A.size(); i++) {
      xor ^= i ^ A.get(i);
    }
    // now xor is missing ^ duplicate
    // get the most right set-1 bit, for ex. 10010 & ~(10001) = 00010
    int lsOneBit = xor & ~(xor - 1);
    int candidate = 0;
    for (int i=0; i<A.size(); i++) {
      if ((lsOneBit & A.get(i))!=0) {
        candidate ^= A.get(i);
      }
      if ((lsOneBit & i)!=0) {
        candidate ^= i;
      }
    }
    if (A.contains(candidate)) {
      return new DuplicateAndMissing(candidate, candidate ^ xor);
    }
    return new DuplicateAndMissing(candidate ^ xor, candidate);
  }

  /* use Space: O(N), a hash table to store the appearance in list
  public static DuplicateAndMissing findDuplicateMissing(List<Integer> A) {
    BitSet bitSet = new BitSet(A.size());
    int duplicate = 0;
    int xor = 0;
    for (int i=0; i<A.size(); i++) {
      Integer val = A.get(i);
      if (bitSet.get(val)) {
        duplicate = val;
      }
      bitSet.set(val);
      xor ^= i^val;
    }
    return new DuplicateAndMissing(duplicate, duplicate^xor);
  }*/

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
