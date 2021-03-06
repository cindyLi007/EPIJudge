package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

public class StackWithMax {

  private static class MaxWithCount {
    Integer value;
    Integer count;

    public MaxWithCount(Integer value, Integer count) {
      this.value = value;
      this.count = count;
    }
  }

  public static class Stack {

    Deque<Integer> deque = new ArrayDeque();
    Deque<MaxWithCount> maxDeque = new ArrayDeque();

    public boolean empty() {
      return deque.isEmpty();
    }

    public Integer max() {
      if (maxDeque.isEmpty()) {
        throw new IllegalStateException("max(): empty stack");
      }
      return maxDeque.peek().value;
    }

    public Integer pop() {
      if (empty()) {
        throw new IllegalStateException("max(): empty stack");
      }
      Integer res = deque.pop();
      if (res==max()) {
        if (maxDeque.peek().count == 1) {
          maxDeque.pop();
        } else {
          maxDeque.peek().count--;
        }
      }
      return res;
    }

    public void push(Integer x) {
      deque.push(x);
      if (maxDeque.isEmpty() || x > maxDeque.peek().value) {
        maxDeque.push(new MaxWithCount(x, 1));
      } else if (x == maxDeque.peek().value) {
        maxDeque.peek().count++;
      }
    }
  }

  @EpiUserType(ctorParams = {String.class, int.class})
  public static class StackOp {
    public String op;
    public int arg;

    public StackOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }
  }

  @EpiTest(testfile = "stack_with_max.tsv")
  public static void stackTest(List<StackOp> ops) throws TestFailure {
    try {
      Stack s = new Stack();
      int result;
      for (StackOp op : ops) {
        switch (op.op) {
        case "Stack":
          s = new Stack();
          break;
        case "push":
          s.push(op.arg);
          break;
        case "pop":
          result = s.pop();
          if (result != op.arg) {
            throw new TestFailure("Pop: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(result));
          }
          break;
        case "max":
          result = s.max();
          if (result != op.arg) {
            throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(result));
          }
          break;
        case "empty":
          result = s.empty() ? 1 : 0;
          if (result != op.arg) {
            throw new TestFailure("Empty: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(s));
          }
          break;
        default:
          throw new RuntimeException("Unsupported stack operation: " + op.op);
        }
      }
    } catch (NoSuchElementException e) {
      throw new TestFailure("Unexpected NoSuchElement exception");
    }
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
