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
    int L = domain.length();
    int[] dp = new int[L];
    if (dfs(domain, 0, dictionary, dp)) {
      return build(domain, dp);
    }
    return Collections.emptyList();
  }

  private static List<String> build(String domain, int[] dp) {
    List<String> res = new ArrayList<>();
    int i=0;
    while (i<domain.length()) {
      res.add(domain.substring(i, dp[i]));
      i=dp[i];
    }
    return res;
  }

  private static boolean dfs(String domain, int index, Set<String> dictionary, int[] dp) {
    if (index == domain.length()) return true;
    if (dp[index]!=0) return dp[index] > 0;
    for (int i = index+1; i<=domain.length(); i++) {
      if (dictionary.contains(domain.substring(index, i))) {
        if (dfs(domain, i, dictionary, dp)) {
          dp[index]=i;
          return true;
        }
      }
    }
    dp[index] = -1;
    return false;
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
