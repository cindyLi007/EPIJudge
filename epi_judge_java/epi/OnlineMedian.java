package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class OnlineMedian {

  // Time: O(logN) for each insert
  public static List<Double> onlineMedian(Iterator<Integer> sequence) {
    List<Double> res = new ArrayList<>();
    PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> right = new PriorityQueue<>();
    while (sequence.hasNext()) {
      Integer val = sequence.next();
      if (left.size() == 0) {
        left.offer(val);
      } else {
        Integer leftTop = left.peek();
        if (val < leftTop) {
          left.offer(val);
          // this is necessary, it balance the elem in left heap and right heap
          right.offer(left.poll());
        } else {
          right.offer(val);
        }
        // always guarantee right.size <= left.size
        if (right.size() > left.size()) {
          left.offer(right.poll());
        }
      }
      res.add(left.size() == right.size() ? 0.5 * (left.peek() + right.peek()) : left.peek().doubleValue());
    }
    return res;
  }

  @EpiTest(testfile = "online_median.tsv")
  public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
    return onlineMedian(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
