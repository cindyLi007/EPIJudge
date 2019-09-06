package epi.excercise.dynamic.programming;

import epi.Knapsack;

import java.util.*;

/**
 Solve the same problem using O(C) space, where C is the number of weights between 0 and w that can be achieved. For ex.
 if the weights are 100, 200, 200, 500, and w = 853, then C = 9, corresponding to the weights 0,100,200,300,400,500,600,700,800.
 */
public class KnapsackSaveSpace {
  // Time: O(N * W * lgW) here W is distinct weight for all combination, Space: O(C)
  public static int optimumSubjectToCapacity(List<Knapsack.Item> items, int capacity) {
    // O(N * lgN)
    Collections.sort(items, Comparator.comparingInt(o -> o.weight));
    TreeMap<Integer, Integer> weightValueMap = new TreeMap<>();
    // must first put (0, 0) as base case
    weightValueMap.put(0, 0);
    int max = 0;
    for (Knapsack.Item item : items) {
      TreeMap<Integer, Integer> temp = new TreeMap<>();
      // The entrySet's iterator returns the entries in ascending key order, we can count on the order of this entry set
      for (Map.Entry<Integer, Integer> entry : weightValueMap.entrySet()) {
        int weight = entry.getKey() + item.weight;
        // if weight > capacity, we know all following entry's weight + item.weight will definitely > capacity
        if (weight > capacity) break;
        // O(lgW) for search
        if (weightValueMap.containsKey(weight)) {
          temp.put(weight, Math.max(weightValueMap.get(weight), entry.getValue() + item.value));
        } else {
          temp.put(weight, entry.getValue() + item.value);
        }
        max = Math.max(temp.get(weight), max);
      }
      weightValueMap.putAll(temp);
    }
    return max;
  }


}
