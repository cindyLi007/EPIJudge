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
  public static List<Integer>  mergeSortedArrays_heap(List<List<Integer>> sortedArrays) {
    List<Integer> res = new ArrayList<>();
    if (sortedArrays==null || sortedArrays.size()==0) return res;
    if (sortedArrays.size()==1) return sortedArrays.get(0);

    // since each is a list, we need use Iterator to record the loop position
    List<Iterator<Integer>> iteratorList = new ArrayList<>(sortedArrays.size());
    for (List<Integer> list : sortedArrays) {
      iteratorList.add(list.iterator());
    }

    // take advantage of every list is sorted, init PQ with first elem in each array
    PriorityQueue<ArrayEntry> pq = new PriorityQueue<>(iteratorList.size(),
        Comparator.comparingInt(o -> o.val));
    for (int i=0; i<iteratorList.size(); i++) {
      Iterator<Integer> currentIt = iteratorList.get(i);
      if (currentIt.hasNext()) {
        // must record which iterator in the listIterator
        pq.add(new ArrayEntry(currentIt.next(), i));
      }
    }

    // begin merge, since priorityQueue is sorted, each time pop the min one and insert it's next from iterator
    while (!pq.isEmpty()) {
      ArrayEntry min = pq.poll();
      res.add(min.val);
      Iterator<Integer> curIterator = iteratorList.get(min.listIndex);
      if (curIterator.hasNext()) {
        pq.offer(new ArrayEntry(curIterator.next(), min.listIndex));
      }
    }

    return res;
  }

  //@EpiTest(testfile = "sorted_arrays_merge.tsv")
  public static List<Integer>  mergeSortedArrays_DivideAndConquer(List<List<Integer>> sortedArrays) {
    List<Integer> res = new ArrayList<>();
    if (sortedArrays==null || sortedArrays.size()==0) return res;
    if (sortedArrays.size()==1) return sortedArrays.get(0);

    return mergeKLists(sortedArrays, 0, sortedArrays.size()-1);
  }

  private static List<Integer> mergeKLists(List<List<Integer>> sortedArrays, int low, int high) {
    if (low==high) {
      return sortedArrays.get(low);
    }
    if (low==high-1) {
      return merge(sortedArrays.get(low), sortedArrays.get(high));
    }
    int mid = low+(high-low)/2;
    return merge(mergeKLists(sortedArrays, low, mid),
        mergeKLists(sortedArrays, mid+1, high));
  }

  private static List<Integer> merge(List<Integer> list0, List<Integer> list1) {
    List<Integer> list = new ArrayList<>();
    int i0=0, i1=0;
    while (i0 < list0.size() && i1 < list1.size()) {
      if (list0.get(i0) < list1.get(i1)) {
        list.add(list0.get(i0++));
      } else {
        list.add(list1.get(i1++));
      }
    }
    while (i0 < list0.size()) list.add(list0.get(i0++));
    while (i1 < list1.size()) list.add(list1.get(i1++));
    return list;
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

