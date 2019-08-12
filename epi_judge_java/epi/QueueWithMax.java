package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.*;

// Time: O(1), Space: O(N)
public class QueueWithMax <T extends Comparable<T>> {
  Queue<T> queue = new ArrayDeque<>();
  Deque<T> deque = new ArrayDeque<>();

  public void enqueue(T x) {
    queue.add(x);
    // old items which less than new item will be evict, because it will not be the max item (it will be earlier evict
    // which the greater one is still in queue
    while (!deque.isEmpty() && deque.peekLast().compareTo(x) < 0) {
      deque.removeLast();
    }
    // new item always be put in deque, in case all percessors evict
    deque.addLast(x);
  }

  public T dequeue() {
    if (queue.isEmpty()) {
      throw new NoSuchElementException();
    }
    T res = queue.poll();
    if (deque.peekFirst().compareTo(res) == 0) {
      deque.removeFirst();
    }
    return res;
  }

  public T max() {
    if (deque.isEmpty()) {
      throw new NoSuchElementException();
    }
    return deque.peekFirst();
  }

  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }
  }

  @EpiTest(testfile = "queue_with_max.tsv")
  public static void queueTest(List<QueueOp> ops) throws TestFailure {
    try {
      QueueWithMax<Integer> q = new QueueWithMax<>();

      for (QueueOp op : ops) {
        switch (op.op) {
        case "QueueWithMax":
          q = new QueueWithMax();
          break;
        case "enqueue":
          q.enqueue(op.arg);
          break;
        case "dequeue":
          Integer result = q.dequeue();
          if (result != op.arg) {
            throw new TestFailure("Dequeue: expected " +
                                  String.valueOf(op.arg) + ", got " +
                                  String.valueOf(result));
          }
          break;
        case "max":
          int s = q.max();
          if (s != op.arg) {
            throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(s));
          }
          break;
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
