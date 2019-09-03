package epi.excercise.bit;

public class Whether2IntegerDiffSign {
    public static void main(String[] arg) {
        int num1 = 9, num2 = 8;
        // since num1 and num2 are sign int, the most significat bit is sign bit, is xor is 0
        // means they are both neg or pos, otherwise (-1) means they are neg/pos pair
        int sign = (num1 ^ num2) >> 31;

        System.out.println(sign);
    }
}
