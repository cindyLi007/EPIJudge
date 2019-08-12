package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IsAnonymousLetterConstructible {
  @EpiTest(testfile = "is_anonymous_letter_constructible.tsv")

  // Time: O(M+N) m and n is the size of letter and magazine text, Space: O(c) c is the distinct char in letter text
  public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                          String magazineText) {
    if (letterText.length()==0) {
      return true;
    }

    // Suppose letterText is shorter than magazineText, first build up a map of chars in letter text
    Map<Character, Long> letterCharFreqMap =
        letterText.chars()
            .mapToObj(c -> (char) c)
            // Function.identity() mean direct use the value of Collectors.counting(), identity means the equality of two
            // expression
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    for (char c : magazineText.toCharArray()) {
      if (letterCharFreqMap.containsKey(c)) {
        letterCharFreqMap.put(c, letterCharFreqMap.get(c) - 1);
        /* Removes the entry for the specified key only if it is currently mapped to the specified value.*/
        letterCharFreqMap.remove(c, 0L);
        if (letterCharFreqMap.isEmpty()) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
