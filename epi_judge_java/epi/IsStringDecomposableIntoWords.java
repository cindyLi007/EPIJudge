package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class IsStringDecomposableIntoWords {

  // Time: O(N*N), N is the domain's length, Space: O(N)
  public static List<String>
  decomposeIntoDictionaryWords(String domain, Set<String> dictionary) {
    // this Array is to indicate whether for [0, i+1] can be decomposed to words, if it can, recorde for this decompostion
    // the last word's start ponit
    int[] dp = new int[domain.length()];
    Arrays.fill(dp, -1);

    for (int i=0; i<domain.length(); i++) { // L
      if (dictionary.contains(domain.substring(0, i + 1))) {
        dp[i] = 0;
      }

      if (dp[i]==-1) { // the substring[0, i+1] is not a word, need decompose it
        // from i-1 to 0 is faster than from 0 to i-1
        for (int j=i-1; j>=0; j--) { // L
          if (dp[j]!=-1 && dictionary.contains(domain.substring(j+1, i+1))){
            dp[i] = j+1;
            // whenever we found one decompostion, break, it can improve performance
            break;
          }
        }
      }
    }

    if (dp[domain.length()-1] == -1) return Collections.emptyList();
    List<String> list = new ArrayList<>();
    for (int i=domain.length(); i>=0;) {
      list.add(domain.substring(dp[i], i));
      i=dp[i];
    }

    Collections.reverse(list);
    return list;
  }

  @EpiTest(testfile = "is_string_decomposable_into_words.tsv")
  public static void decomposeIntoDictionaryWordsWrapper(TimedExecutor executor,
                                                         String domain,
                                                         Set<String> dictionary,
                                                         Boolean decomposable)
      throws Exception {
    List<String> result =
        executor.run(() -> decomposeIntoDictionaryWords(domain, dictionary));

    if (!decomposable) {
      if (!result.isEmpty()) {
        throw new TestFailure("domain is not decomposable");
      }
      return;
    }

    if (result.stream().anyMatch(s -> !dictionary.contains(s))) {
      throw new TestFailure("Result uses words not in dictionary");
    }

    if (!String.join("", result).equals(domain)) {
      throw new TestFailure("Result is not composed into domain");
    }
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
