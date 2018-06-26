package epi.excercise.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.function.ToIntBiFunction;

/**
 * Solve RPN for expression in Polish notation, when A,B,o is replaced by o,A,B
 */
public class ReverseEvaluateRpn {

  public static int eval(String expression) {
    Deque<String> deque = new ArrayDeque<>();
    String[] tokens = expression.split(",");

    final Map<String, ToIntBiFunction<Integer, Integer>> OPERATORS = new HashMap<String, ToIntBiFunction<Integer, Integer>>() {
      {
        put("+", (x, y) -> x+y);
        put("-", (x, y) -> x-y);
        put("*", (x, y) -> x*y);
        put("/", (x, y) -> x/y);
      }
    };

    for (String token : tokens) {
      if (!OPERATORS.containsKey(token)) {
        int tempRes = Integer.parseInt(token);
        while (!deque.isEmpty() && !OPERATORS.containsKey(deque.peek())) {
          int v1 = Integer.parseInt(deque.pop());
          tempRes = OPERATORS.get(deque.pop()).applyAsInt(v1, tempRes);
        }
        deque.push(String.valueOf(tempRes));
      } else {
        deque.push(token);
      }
    }
    return Integer.parseInt(deque.pop());
  }

  public static void main(String... args) {
    int eval = ReverseEvaluateRpn.eval("+,+,*,10,/,6,*,+,9,3,-11,17,5");
    System.out.println(eval);
  }
}
