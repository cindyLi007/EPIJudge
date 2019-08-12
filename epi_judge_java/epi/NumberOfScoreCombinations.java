package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class NumberOfScoreCombinations {
  /** Time: O(S*N), s is score, n is len of plays, Space: O(S)
    We can improve Space Complexity by using dp[] = new int[finalScore] Variant 1
    For Variant 2, return Sequence, first build a sorted list for ex. for score 8 from dp[i-1][j], we got {2,2,2,2}, from dp[i][j-3]
    we got {2, 3} + {3}, which is {2, 3, 3} finally we permutation the set of comb to return a sequence of list
  */
  @EpiTest(testfile = "number_of_score_combinations.tsv")
  public static int numCombinationsForFinalScore(int finalScore, List<Integer> individualPlayScores) {
    int N = individualPlayScores.size();
    int[] dp = new int[finalScore+1];
    for (int i = 0; i < N; i++) {
      int scores = individualPlayScores.get(i);
      dp[0]=1;
      for (int j = 1; j <= finalScore; j++) {
        if (i == 0) {
          dp[j] = j % scores == 0 ? 1 : 0;
        } else {
          dp[j] += j >= scores ? dp[j - scores] : 0;
        }
      }
    }
    return dp[finalScore];
  }

  // this one will time limit exceed
  public static int
  numCombinationsForFinalScore_recursive(int finalScore,
                                         List<Integer> individualPlayScores) {
    int res=0;
    Collections.sort(individualPlayScores);
    for (int i = 0; i < individualPlayScores.size(); i++) {
      res+=dfs(finalScore-individualPlayScores.get(i), individualPlayScores, i);
    }
    return res;
  }

  private static int dfs(int score, List<Integer> individualPlayScores, int index) {
    if (score==0) {
      return 1;
    }
    if (score<0) return 0;
    int res = 0;
    for (int i=index; i<individualPlayScores.size() && score >= individualPlayScores.get(i); i++) {
      res += dfs(score - individualPlayScores.get(i), individualPlayScores, i);
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
