package epi.excercise.graph;

import java.util.*;

/**
 */
public class AdditionChainExponentiation {

  // Time: O(n * n), Space: O(n*n)
  public static int getAdditionChainExponentiation(int n) {
    // map store minimum steps needed to get addition chain for value key, for ex. map.get(4) return 2 means we need 2 step (a^2)^2 to return a^4
    Map<Integer, Integer> map = new HashMap<>();
    Queue<Item> queue = new ArrayDeque<>();
    TreeMap<Integer, Item> newItemQueue = new TreeMap<>();

    Item one = new Item(1, 0); // x^1 need 0 step
    map.put(one.exponentiation, one.distance);
    newItemQueue.put(1, one);

    while (true) {
      TreeMap<Integer, Item> temp = new TreeMap<>();

      // each time, loop through all item's in newItemQueue, combine it with all "old items" in queue
      // newItemQueue store "most recently added nodes"
      while (!newItemQueue.isEmpty()) {
        // pollFirstEntry in TreeMap to remove the first entry in TreeMap
        Item x = newItemQueue.pollFirstEntry().getValue();

        if (x.exponentiation == n) return map.get(n);

        int ex = x.exponentiation * 2, dist = x.distance + 1;
        if (!map.containsKey(ex) || dist < map.get(ex)) {
          map.put(ex, dist);
          temp.put(ex, new Item(ex, dist));
        }
        for (Item item : queue) {
          ex = x.exponentiation + item.exponentiation;
          dist = x.distance + (item.distance==0 ? 1 : item.distance);
          // for ex. a^5=3, a^10=4, to compute a^15, if we directly use a^5 * a^10, the dist is 7, however, it should be
          // (a^5)*(a^5)*(a^5), should be 5, that is because the distance from a^10 to a^5 is 1 (a^10 / a^5 - 1)
          if (x.exponentiation % item.exponentiation==0) {
            dist = Math.min(dist, (x.exponentiation / item.exponentiation - 1) + x.distance);
          }
          if (!map.containsKey(ex) || dist < map.get(ex)) {
            map.put(ex, dist);
            temp.put(ex, new Item(ex, dist));
          }
        }
        // only after we combine all "most-recently-added" nodes with old nodes, we add them to old nodes list
        queue.offer(x);
      }
      newItemQueue = temp;
    }
  }

  private static class Item {
    int exponentiation;
    int distance;

    Item(int e, int distance) {
      exponentiation = e;
      this.distance = distance;
    }
  }

  public static void main(String... args) {
    int[] res = new int[]{0, 0, 1, 2, 2, 3, 3, 4, 3, 4, 4, 5, 4, 5, 5, 5, 4};
    for (int i = 1; i <= 16; i++) {
      int additionChainExponentiation = getAdditionChainExponentiation(i);
      System.out.println("a^" + i + " is " + additionChainExponentiation + " should be " + res[i] + (res[i]==additionChainExponentiation ? " correct" : " wrong"));
    }

  }
}
