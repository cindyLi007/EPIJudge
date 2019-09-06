package epi.excercise.dynamic.programming;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static epi.IsStringPalindromicPunctuation.isPalindrome;

/**
 * EPI 16.7 variant How would you compute a palindromic decomposition of a string s that uses a minimum number of substrings
 * for ex. "0204451881", can be decomposed as "020", "44", "5", "1881"
 */
public class PalindDecompositionMinSubstring {

  // Time: O(N * N * N), Space: O(N)
  public static int minDecomposition(String s) {
    if (s.length()<=1) return s.length();
    int L = s.length();

    // dp[i] save min decomposition number end index i (inclusive)
    int[] dp = new int[L];
    // index[i] save in the min decomposition, the last word start index, for ex. index[3] = 0 means substring(0, 4) is a
    // Palindrome
    int[] index = new int[L];
    for (int i=0; i<L; i++) {
      if (isPalindrome(s.substring(0, i+1))) {
        dp[i] = 1;
        index[i] = 0;
      } else {
        dp[i] = dp[i-1] + 1;
        index[i] = i;
        for (int j=i-1; j>0; j--) {
          if (isPalindrome(s.substring(j, i+1)) && dp[j-1] + 1 < dp[i]) {
            dp[i] = dp[j-1]+1;
            index[i] = j;
          }
        }
      }
    }
    List<String> ans = new ArrayList<>();
    for (int i=L; i>0; ) {
      ans.add(s.substring(index[i-1], i));
      i = index[i-1];
    }
    ans.stream().forEach(o-> System.out.print(o + ", "));
    System.out.println();
    return dp[L-1];

  }

  public static void main(String... args) {
    int res = minDecomposition("0204451881");
    System.out.println(res);
  }

}
