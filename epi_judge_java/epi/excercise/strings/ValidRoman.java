package epi.excercise.strings;

import java.util.LinkedHashMap;

/**
 * 判断一个Roman是否是valid 包括2个方面: 是否有效char 相连chars之间的关系
 * for the 2nd part, smaller char can only appears before consecutive greater char, for ex. I can only appears before V and X
 * and a same char can appears no more than 3 times
 */
public class ValidRoman {
    // Time: O(N), Space: O(1)
    public boolean isValidRoman(String romanInt) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<Character, Integer>() {
            {
                put('I', 1);
                put('V', 5);
                put('X', 10);
                put('L', 50);
                put('C', 100);
                put('D', 500);
                put('M', 1000);
            }
        };
        int len = romanInt.length();
        int count = 0;
        for (int i=len-1; i>=0; i--) {
            char c = romanInt.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            }
            if (i<len-1 && map.get(c)<map.get(romanInt.charAt(i+1))) {
                int dif = map.get(romanInt.charAt(i + 1)) / map.get(c);
                if (dif!=5 && dif!=10) {
                    return false;
                }
            }
            if (i<len-1 && map.get(c)==map.get(romanInt.charAt(i+1))) {
                count++;
                if (count==4) {
                    return false;
                }
            } else {
                count=1;
            }
        }
        return true;
    }

    public static void main(String... arg) {
        String s = "LIX";
        ValidRoman validRoman = new ValidRoman();
        boolean validRoman1 = validRoman.isValidRoman(s);
        System.out.println(validRoman1);
    }

}
