package epi.excercise.graph;

import java.util.*;

public class AdditionChainExponentiation {

  public static int getAdditionChainExponentiation(int n) {
    // map store minimum steps needed to get addition chain for value key
    Map<Integer, Integer> map = new HashMap<>();
    Queue<Item> queue = new ArrayDeque<>();
    Queue<Item> newItemQueue = new ArrayDeque<>();

    Item one = new Item(1, 0); // x^1 need 0 step
    map.put(one.exponentiation, one.distance);
    queue.add(one);
    newItemQueue.add(one);
    while (!newItemQueue.isEmpty()) {
      Queue<Item> temp = new ArrayDeque<>();

      // loop through all item's in newItemQueue
      while (!newItemQueue.isEmpty()) {
        Item x = newItemQueue.poll();
        if (x.exponentiation == n) return x.distance;
        if (!map.containsKey(x.exponentiation * 2)) {
          map.put(x.exponentiation * 2, x.distance + 1);
          temp.add(new Item(x.exponentiation * 2, x.distance + 1));
        }
        for (Item item : queue) {
          int ex = x.exponentiation + item.exponentiation, dist = x.distance + (item.distance==0 ? 1 : item.distance);
          if (!map.containsKey(ex)) {
            map.put(ex, dist);
            temp.add(new Item(ex, dist));
          }
        }
        queue.offer(x);
      }
      newItemQueue = temp;
    }
    return 0;
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
    for (int i = 1; i <= 15; i++) {
      int additionChainExponentiation = getAdditionChainExponentiation(i);
      System.out.println("a^" + i + " is " + additionChainExponentiation);
    }

  }
}
