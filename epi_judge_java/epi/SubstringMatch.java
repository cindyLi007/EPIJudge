package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SubstringMatch {
    @EpiTest(testfile = "substring_match.tsv")

    // Returns the index of the first character of the substring if found, -1
    // otherwise.
    // Time: O(m+n)
    public static int rabinKarp(String t, String s) {
        int m=s.length(), n=t.length();
        if (m>n) {
            return -1;
        }
        final int BASE =26;
        int pow = 1;
        int tHashcode = 0, sHashcode = 0;
        for (int i=0; i<m; i++) {
            // NOTICE: must put pow calculation inside loop, that is because Math.pow could not convert overflow to negative
            // so when pow is overflow, Math.pow(BASE, m-1) always Integer.MAX_VALUE, but we need some negative number
            pow = i==0 ? 1 : pow * BASE;
            tHashcode = tHashcode * BASE + t.charAt(i);
            sHashcode = sHashcode * BASE + s.charAt(i);
        }
        // classic Hashcode is x = base*x + (y or y.hashcode)
        for (int i=m; i<=n; i++) {
            if (i>m) {
                tHashcode -= t.charAt(i-m-1) * pow;
                tHashcode = tHashcode * BASE + t.charAt(i-1);
            }
            if (tHashcode == sHashcode && s.equals(t.substring(i-m, i))) {
                return i-m;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {
                        }.getClass().getEnclosingClass())
                .ordinal());
    }
}