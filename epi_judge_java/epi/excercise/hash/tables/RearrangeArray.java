package epi.excercise.hash.tables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Given an array A, rearrange the elements so that the shortest subarray containing all the distinct values in A has
 * maximum possible length
 *
 * Put the least duplicates elements in the very end
 */
public class RearrangeArray {

  // Time: O(N) N is the # of strings, Space: O(N)
  public static List<String> findSmallestSubarrayCoveringSet(List<String> paragraph) {
    Map<String, Long> wordCountMap = paragraph.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    Deque<String> res = new LinkedList<>();
    long min=-1, min_1=-1;
    List<String> aux = new ArrayList<>();

    for (String key : wordCountMap.keySet()) {
      Long count = wordCountMap.get(key);
      if (min==-1 || count<min) {
        min=count;
        while (count-->0) {
          res.addFirst(key);
        }
      } else if (min_1==-1 || count<min_1){
        min_1=count;
        while (count-->0) {
          res.addLast(key);
        }
      } else {
        while (count-->0) {
          aux.add(key);
        }
      }
    }
    ((LinkedList<String>) res).addAll((int)min, aux);
    return (List<String>)res;
  }

  public static void main(String... args) {
    List<String> strings = Arrays.asList("apple", "banana", "apple", "apple", "dog", "cat", "apple", "dog", "banana", "apple", "cat", "dog");

    List<String> res = findSmallestSubarrayCoveringSet(strings);

    res.stream().forEach(System.out::println);
  }

}
