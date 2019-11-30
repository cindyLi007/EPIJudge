package epi.excercise.dynamic.programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * EPI 16.7 variant How would you compute a palindromic decomposition of a string s that uses a minimum number of substrings
 * for ex. "0204451881", can be decomposed as "020", "44", "5", "1881"
 */
public class PalindDecompositionMinSubstring {
  static int[][] palindrome;

  // Time: O(N * N * N), Space: O(N)
  public static int minDecomposition(String s) {
    if (s.length()<=1) return s.length();
    int L = s.length();
    palindrome = new int[L][L];
    for (int[] row : palindrome)
      Arrays.fill(row, -1);

    // dp[i] save min decomposition number end index i (inclusive)
    int[] dp = new int[L];
    // index[i] save in the min decomposition, the last word start index, for ex. index[3] = 0 means substring(0, 4) is a
    // Palindrome
    int[] index = new int[L];
    for (int i=0; i<L; i++) {
      if (isPalindrome(s, 0, i)) {
        dp[i] = 1;
        index[i] = 0;
      } else {
        dp[i] = dp[i-1] + 1;
        index[i] = i;
        for (int j=i-1; j>0; j--) {
          if (isPalindrome(s, j, i) && dp[j-1] + 1 < dp[i]) {
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
    Collections.reverse(ans);
    ans.stream().forEach(o-> System.out.print(o + ", "));
    System.out.println();
    return dp[L-1];

  }

  public static boolean isPalindrome(String s, int start, int end) {
    if (palindrome[start][end] != -1) {
      return palindrome[start][end] == 0;
    }
    while (start < end) {
      if (s.charAt(start) != s.charAt(end)) {
        palindrome[start][end] = 1;
        return false;
      }
      start++; end--;
    }
    palindrome[start][end] = 0;
    return true;
  }

  public static void main(String... args) {
    String s = "0204451881";
    System.out.println(s);
    int res = minDecomposition(s);
    System.out.println(res);
  }

}
