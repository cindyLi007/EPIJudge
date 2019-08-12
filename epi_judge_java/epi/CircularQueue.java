package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class CircularQueue {
  /*
    Using a resizing array to implement a queue API
   */
  public static class Queue {
    int head, tail, numberOfElem;
    final static int SIZE_FACTOR = 2;
    Integer[] entries;

    public Queue(int capacity) {
      entries = new Integer[capacity];
      head = 0;
      tail = 0;
      numberOfElem = 0;
    }

    public void enqueue(Integer x) {
      if (numberOfElem == entries.length) {
        // rotate array, to put index head as the idx 0 element
        // use this rotate API must apply to List<Integer>, so must create Integer[] array, int[] could not make effective rotatation
        Collections.rotate(Arrays.asList(entries), -head);
        head = 0;
        tail = entries.length;
        entries = Arrays.copyOf(entries, SIZE_FACTOR * numberOfElem);
      }
      numberOfElem++;
      entries[tail++] = x;
      tail %= entries.length;
    }

    public Integer dequeue() {
      if (numberOfElem == 0) {
        throw new NoSuchElementException();
      }
      Integer res = entries[head++];
      head %= entries.length;
      numberOfElem--;
      return res;
    }

    public int size() {
      return numberOfElem;
    }

    @Override
    public String toString() {
      // Implement this placeholder.
      return super.toString();
    }
  }

  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }

    @Override
    public String toString() {
      return op;
    }
  }

  @EpiTest(testfile = "circular_queue.tsv")
  public static void queueTest(List<QueueOp> ops) throws TestFailure {
    Queue q = new Queue(1);
    int opIdx = 0;
    for (QueueOp op : ops) {
      switch (op.op) {
      case "Queue":
        q = new Queue(op.arg);
        break;
      case "enqueue":
        q.enqueue(op.arg);
        break;
      case "dequeue":
        int result = q.dequeue();
        if (result != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, result);
        }
        break;
      case "size":
        int s = q.size();
        if (s != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, s);
        }
        break;
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
