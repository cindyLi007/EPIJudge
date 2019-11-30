package epi.excercise.dynamic.programming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DivideSpoilsFairly {

  public static String divideFairly(Item[] items) {
    HashMap<Integer, String> set = new HashMap<>();
    set.put(0, "");
    int sum = 0;
    for (Item item : items) {
      sum += item.value;
      HashMap<Integer, String> temp = new HashMap<>();
      for (Integer i : set.keySet()) {
        temp.put(i + item.value, set.get(i)  + ", " + item.label);
      }
      set.putAll(temp);
    }
    List<Integer> list = new ArrayList<>(set.keySet());
    Collections.sort(list);
    int i = Collections.binarySearch(list, sum / 2);
    int diff1 = Math.abs(list.get(i) - sum / 2), diff2 = Math.abs(list.get(i + 1) - sum / 2);
    if (diff1 <= diff2) return set.get(list.get(i));
    return set.get(list.get(i + 1));
  }

  public static void main(String... args) {
    Item[] items = new Item[16];
    items[0] = new Item("A", 65);
    items[1] = new Item("B", 35);
    items[2] = new Item("C", 245);
    items[3] = new Item("D", 195);
    items[4] = new Item("E", 65);
    items[5] = new Item("F", 150);
    items[6] = new Item("G", 275);
    items[7] = new Item("H", 155);
    items[8] = new Item("I", 120);
    items[9] = new Item("J", 320);
    items[10] = new Item("K", 75);
    items[11] = new Item("L", 40);
    items[12] = new Item("M", 200);
    items[13] = new Item("N", 100);
    items[14] = new Item("O", 220);
    items[15] = new Item("P", 99);
    String s = divideFairly(items);
    System.out.println(s);
    String[] labels = s.split(",");
    int sum = 0;
    for (String label : labels) {
      for (int i = 0; i < items.length; i++) {
        if (label.trim().equals(items[i].label)) {
          sum += items[i].value;
          break;
        }
      }
    }
    System.out.println("the sum of above pick is " + sum);
  }
}

class Item {
  String label;
  int value;

  Item(String l, int v) {
    label = l;
    value = v;
  }
}
