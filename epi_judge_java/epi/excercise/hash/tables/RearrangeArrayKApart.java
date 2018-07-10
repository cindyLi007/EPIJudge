package epi.excercise.hash.tables;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Given an array A and a positive integer K, rearrange the elements so that no two equal elements are k or less apart
 *
 * Sort elements by count in descending order
 */
public class RearrangeArrayKApart {

  private static class Node implements Comparable {
    int count;
    String word;

    public Node(int count, String word) {
      this.count = count;
      this.word = word;
    }

    @Override
    public int compareTo(Object o) {
      return ((Node) o).count - this.count;
    }
  }

  // Time: O(N + mlgm) N is the # of strings, m is the distinct strings Space: O(N)
  public static List<String> findSmallestSubarrayCoveringSet(List<String> paragraph, int k) {
    Map<String, Long> wordCountMap = paragraph.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    // must use PriorityQueue instead of TreeSet, because TreeSet does not allow same key, but now we use count to compare 2 nodes
    // if there are 2 nodes with same count, set can only contain one
    PriorityQueue<Node> nodeSortedList = new PriorityQueue<>();
    for (String key : wordCountMap.keySet()) {
      nodeSortedList.add(new Node(wordCountMap.get(key).intValue(), key));
    }

    String[] array = new String[paragraph.size()];
    while (!nodeSortedList.isEmpty()) {
      Node node = nodeSortedList.remove();
      // Every (k+1) positions can only have one equal element
      if ((k+1) * (node.count-1) >= paragraph.size()) {
        throw new IllegalArgumentException("error");
      }
      int start=0;
      while (array[start]!=null) start++;
      for (int i=0; i<node.count; i++) {
        if (start>=array.length) {
          throw new IllegalArgumentException("error");
        }
        array[start] = node.word;
        start += k+1;
      }
    }

    return Arrays.asList(array);
  }

  public static void main(String... args) {
    List<String> strings = Arrays.asList("apple", "banana", "apple", "apple", "dog", "apple", "dog", "dog", "banana", "apple", "cat", "dog", "dog");

    List<String> res = findSmallestSubarrayCoveringSet(strings, 1);

    for (String s : res) {
      System.out.print(s + " ");
    }
  }

}
