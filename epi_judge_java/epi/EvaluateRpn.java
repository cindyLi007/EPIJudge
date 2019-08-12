package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.ToIntBiFunction;

// Time O(N)
public class EvaluateRpn {
  @EpiTest(testfile = "evaluate_rpn.tsv")

  public static int eval(String expression) {
    Deque<Integer> stack = new LinkedList<>();
    final Map<String, ToIntBiFunction<Integer, Integer>> map = new HashMap<String, ToIntBiFunction<Integer, Integer>>() {
      {
        put("+", (x, y) -> x+y);
        put("-", (y, x) -> x-y);
        put("*", (x, y) -> x*y);
        put("/", (y, x) -> x/y);
      }
    };
    String[] strings = expression.split(",");
    for (String str : strings) {
      if (map.containsKey(str)) {
        stack.push(map.get(str).applyAsInt(stack.pop(), stack.pop()));
      } else {
        stack.push(Integer.parseInt(str));
      }
    }
    return stack.pop();
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
