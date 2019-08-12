package epi.excercise.graph;

import java.util.*;

public class AdditionChainExponentiation {

    public static String getAdditionChainExponentiation(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Item> queue = new ArrayList<>();

        Item one = new Item(1, 1, " 1");
        map.put(1, 1);
        int idx=0;
        queue.add(one);
        while (true) {
            int size = queue.size();
            for (int i=idx; i<size; i++) {
                Item cur = queue.get(i);
                int newValue = cur.exponentiation * 2;
                queue.add(new Item(newValue, cur.path + 1, cur.exponentiation + "^2"));
                if (newValue == n) {
                    return cur.exponentiation + "^2";
                }
                map.put(newValue, cur.path + 1);
            }
            for (int i=idx; i<size; i++) {
                for (int j=0; j<idx; j++) {
                    Item outer = queue.get(i);
                    Item inner = queue.get(j);

                    int newValue = outer.exponentiation + inner.exponentiation;
                    if (!map.containsKey(newValue) || map.get(newValue) > outer.path + inner.path) {
                        map.put(newValue, outer.path + inner.path);
                    }
                }
            }

            idx = size;
        }
    }

    private static class Item {
        int exponentiation;
        int path;
        String s;

        Item(int e, int path, String s) {
            exponentiation = e;
            this.path = path;
            this.s = s;
        }
    }

    public static void main(String... args) {
        String additionChainExponentiation = getAdditionChainExponentiation(15);
        System.out.println(additionChainExponentiation);
    }
}
