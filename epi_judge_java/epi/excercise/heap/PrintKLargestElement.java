package epi.excercise.heap;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Design an O(n*Logk) time algorithm that reads a sequence of n elements and for each element, starting from the Kth
 * element, prints the Kth largest element read up to that point. The length of the sequence is not known in advance.
 * Your algorithm cannot use more than O(K) additional storage. What are the worst-case input for your algorithm.
 * 1. keep a k-size heap, compare native order. minHeap
 * 2. each time to add a new element, if size > K, remove the smallest one.
 * 3. worst case is each time we add a largest one. We need remove the min and restructure heap
 */
public class PrintKLargestElement {

  private static void printKlargestElem(Iterator<Integer> sequence, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

    while (sequence.hasNext()) {
      Integer next = sequence.next();
      if (minHeap.size()==k) {
        if (Integer.compare(next, minHeap.peek())>0) {
          minHeap.remove();
          minHeap.add(next);
        }
        print(next, minHeap);
      } else {
        minHeap.add(next);
      }
    }
  }

  private static void print(int elem, PriorityQueue<Integer> heap) {
    Iterator<Integer> iterator = heap.iterator();
    System.out.print(elem + ": ");
    while (iterator.hasNext()) {
      System.out.print(iterator.next() + " ");
    }
    System.out.println();
  }

  public static void main(String... args) {
    List<Integer> list = Arrays.asList(4, 5, -2, 3, 12, 19, 20, 90, -2);
    PrintKLargestElement.printKlargestElem(list.iterator(), 4);
  }

}
