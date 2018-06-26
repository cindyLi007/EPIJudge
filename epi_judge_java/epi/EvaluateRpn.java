package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.function.ToIntBiFunction;

// Time O(N)
public class EvaluateRpn {
  @EpiTest(testfile = "evaluate_rpn.tsv")

  public static int eval(String expression) {
    String[] rpn = expression.split(",");
    Deque<Integer> deque = new ArrayDeque<>();
    final Map<String, ToIntBiFunction<Integer, Integer>> OPERATORS = new HashMap<String, ToIntBiFunction<Integer, Integer>>() {
      {
        put("+", (y, x) -> x+y);
        put("-", (y, x) -> x-y);
        put("*", (y, x) -> x*y);
        put("/", (y, x) -> x/y);
      }
    };

    for (String token : rpn) {
      if (OPERATORS.containsKey(token)) {
        deque.push(OPERATORS.get(token).applyAsInt(deque.pop(), deque.pop()));
      } else {
        deque.push(Integer.parseInt(token));
      }
    }
    return deque.pop();
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
