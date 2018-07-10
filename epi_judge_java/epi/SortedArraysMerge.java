package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This question the most difficult part is using iterator to loop through each list
 */
public class SortedArraysMerge {

  //Time: O(n*LogK), K is the number of list, n is number of total integer, Space: O(K)
  @EpiTest(testfile = "sorted_arrays_merge.tsv")
  public static List<Integer>  mergeSortedArrays(List<List<Integer>> sortedArrays) {
    List<Integer> res = new ArrayList<>();
    if (sortedArrays==null || sortedArrays.size()==0) return res;
    if (sortedArrays.size()==1) return sortedArrays.get(0);

    // since each is a list, we need use Iterator to record the loop position
    List<Iterator<Integer>> iteratorList = new ArrayList<>(sortedArrays.size());
    for (List<Integer> list : sortedArrays) {
      iteratorList.add(list.iterator());
    }

    /*Iterator<Integer> mergedIterator = merge(iteratorList, 0, iteratorList.size() - 1);
    while (mergedIterator.hasNext()) {
      res.add(mergedIterator.next());
    }*/

    // take advantage of every list is sorted, init PQ with first elem in each array
    PriorityQueue<ArrayEntry> priorityQueue = new PriorityQueue<>(iteratorList.size(),
        Comparator.comparingInt(o -> o.val));
    for (int i=0; i<iteratorList.size(); i++) {
      Iterator<Integer> currentIt = iteratorList.get(i);
      if (currentIt.hasNext()) {
        // must record which iterator in the listIterator
        priorityQueue.add(new ArrayEntry(currentIt.next(), i));
      }
    }

    // begin merge, since priorityQueue is sorted, each time pop the min one and insert it's next from iterator
    while (!priorityQueue.isEmpty()) {
      ArrayEntry min = priorityQueue.poll();
      res.add(min.val);
      Iterator<Integer> iterator = iteratorList.get(min.listIndex);
      if (iterator.hasNext()) {
        priorityQueue.add(new ArrayEntry(iterator.next(), min.listIndex));
      }
    }

    return res;
  }

  private static Iterator<Integer> merge(List<Iterator<Integer>> listIterator, int low, int high) {
    if (low==high) {
      return listIterator.get(low);
    }
    if (low==high-1) {
      return mergeIterator(listIterator.get(low), listIterator.get(high));
    }
    int mid = low+(high-low)/2;
    return mergeIterator(merge(listIterator, low, mid),
        merge(listIterator, mid+1, high));
  }

  private static Iterator<Integer> mergeIterator(Iterator<Integer> iterator0, Iterator<Integer> iterator1) {
    PriorityQueue<ArrayEntry> pq = new PriorityQueue<>(2, Comparator.comparingInt(o -> o.val));
    List<Integer> list = new ArrayList<>();

    if (iterator0.hasNext()) {
      pq.offer(new ArrayEntry(iterator0.next(), 0));
    }
    if (iterator1.hasNext()) {
      pq.offer(new ArrayEntry(iterator1.next(), 1));
    }
    while (!pq.isEmpty()) {
      ArrayEntry min = pq.poll();
      list.add(min.val);
      if (min.listIndex==0) {
        if (iterator0.hasNext()) {
          pq.offer(new ArrayEntry(iterator0.next(), 0));
        }
      } else if (iterator1.hasNext()) {
        pq.offer(new ArrayEntry((iterator1.next()), 1));
      }
    }
    return list.iterator();
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }

  private static class ArrayEntry {
    int val;
    int listIndex;

    public ArrayEntry(int val, int listIndex) {
      this.val = val;
      this.listIndex = listIndex;
    }
  }
}
