package epi.excercise.recursive;

/*
Given an n×n board, find the domination number, which is the minimum number of queens (or other pieces) needed to attack
or occupy every square. For the 8×8 board, the queen's domination number is 5.
EPI 15.2 Variant 2
 */

import java.util.ArrayList;
import java.util.List;

public class NQueenMinAttack {

  int min;

  public int minNumberAttack(int n) {
    List<Integer> list = new ArrayList<>();
    min = n;
    dfs(n, 0, list, "");
    return min;
  }

  private void dfs(int n, int row, List<Integer> list, String key) {
    if (row == n || row+1 >= min) {
      return;
    }
    boolean foundValidOne = false;

    for (int col=0; col<n; col++) {
      // 如果到达某一层后所有的put 都不能valid，说明这个placement可以导致cover all squares
      if (isValid(row, list, col)) {
        list.add(col);
        dfs(n, row+1, list, key + col);
        list.remove(list.size() -1);
        foundValidOne = true;
      }
    }
    if (!foundValidOne) {
      min = Math.min(min, row+1);
    }
  }

  private boolean isValid(int row, List<Integer> list, int col) {
    for (int i = 0; i<row; i++) {
      int diff = Math.abs(list.get(i) - col);
      if (diff==0 || diff == row - i) return false;
    }
    return true;
  }

  public static void main(String... args) {
    NQueenMinAttack minAttack = new NQueenMinAttack();
    System.out.println(minAttack.minNumberAttack(16));
  }

}
