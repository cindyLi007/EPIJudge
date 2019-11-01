package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * Replace each 'a' by two 'd's
 * Delete each entry containing a 'b'
 */
public class ReplaceAndRemove {

  /**
   * Time O(N), Space O(1)
   * @param size
   * @param s
   * @return
   */
  public static int replaceAndRemove(int size, char[] s) {
    // 2 rounds, first remove 'b' and compute the final size, 2nd from back to rewrite s change 'a'
    // remember if the changed array's size > original array's size (insert), should traverse from back to forth,
    // if the changed array's size < original array's size(delete), should from front to back, if remove and replace involves
    // insert and delete simutanously, we should traverse multiple times
    int i=0, j=0, As=0;
    for (; i<size; i++) {
      if (s[i]=='a') As++;
      if (s[i]!='b') {
        s[j++]=s[i];
      }
    }
    if (As==0) return j;
    int res = j+As;
    i=j-1;
    j=res-1;
    for (; j>i; i--) {
      if (s[i]=='a') {
        s[j--]='d';
        s[j--]='d';
      } else {
        s[j--] = s[i];
      }
    }
    return res;
  }

  @EpiTest(testfile = "replace_and_remove.tsv")
  public static List<String>
  replaceAndRemoveWrapper(TimedExecutor executor, Integer size, List<String> s)
      throws Exception {
    char[] sCopy = new char[s.size()];
    for (int i = 0; i < size; ++i) {
      if (!s.get(i).isEmpty()) {
        sCopy[i] = s.get(i).charAt(0);
      }
    }

    Integer resSize = executor.run(() -> replaceAndRemove(size, sCopy));

    List<String> result = new ArrayList<>();
    for (int i = 0; i < resSize; ++i) {
      result.add(Character.toString(sCopy[i]));
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
