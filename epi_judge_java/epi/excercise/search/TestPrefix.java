package epi.excercise.search;

import java.util.Arrays;
import java.util.List;

// Tests is p is a prefix of a string in an array of sorted strings
public class TestPrefix {
  public static boolean testPrefix(String prefix, List<String> S) {
    int low = 0, high = S.size()-1;
    int len = prefix.length();
    while (low<=high) {
      int mid = low + (high-low)/2;
      int comp = prefix.compareTo(S.get(mid).substring(0, len));
      if (comp==0) {
        return true;
      }
      if (comp>0) low=mid+1;
      else high=mid-1;
    }
    return false;
  }

  public static void main(String... args) {
    List<String> strings = Arrays.asList("abcdef", "abklm", "gggoogle", "gggrace", "oikk", "wawa");
    boolean res = testPrefix("abc", strings);
    assert (res);

    res = testPrefix("ggg", strings);
    assert (res);

    res = testPrefix("gra", strings);
    assert (!res);
  }
}
