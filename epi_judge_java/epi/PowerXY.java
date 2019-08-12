package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PowerXY {
    @EpiTest(testfile = "power_x_y.tsv")
    public static double power(double x, int y) {
        double res = 1.0;
        if (y < 0) {
            x = 1 / x;
            y = -y;
        }
        while (y != 0) {
            if ((y & 1) != 0) { // same as (y%2 == 1)
                res *= x;
            }
            x *= x;
            y >>>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {
                        }.getClass().getEnclosingClass())
                .ordinal());
    }
}
