package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;

public class IsValidParenthesization {
  @EpiTest(testfile = "is_valid_parenthesization.tsv")

  public static boolean isWellFormed(String s) {
    if (s.length()%2==1) return false;
    Deque<Character> deque = new ArrayDeque<>();
    for (int i=0; i<s.length(); i++) {
      char c = s.charAt(i);
      if (c=='(' || c=='{' || c=='[') {
        deque.push(c);
      } else {
        if (deque.isEmpty()) return false;
        char ch = deque.pop();
        if (c==')' && ch=='(' || c=='}' && ch=='{' || c==']' && ch=='[') {
          continue;
        } else {
          return false;
        }
      }
    }
    return deque.isEmpty();
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
