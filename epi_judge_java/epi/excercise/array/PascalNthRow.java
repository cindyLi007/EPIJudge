package epi.excercise.array;

import java.util.ArrayList;
import java.util.List;

public class PascalNthRow {

  public static List<Integer> nthRowInPascalTriangel(int n) {
    List<Integer> res = new ArrayList<>();
    res.add(1);
    n--;
    while (n-->0) {
      res.add(1);
      for (int i = res.size() - 2; i > 0; i--) {
        res.set(i, res.get(i) + res.get(i - 1));
      }
    }
    return res;
  }

  public static void main(String... args) {
    List<Integer> res = nthRowInPascalTriangel(6);
    res.stream().forEach(o-> System.out.print(o + ", "));
  }
}
