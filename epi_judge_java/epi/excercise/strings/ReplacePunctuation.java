package epi.excercise.strings;

/**
 * replace punctuation with text, for example, "." --> DOT, "?" --> "QUESTION MARK", "," --> "COMMA", "!" --> "EXCLAMATION MARK"
 */
public class ReplacePunctuation {

  public static int replaceAndRemove(int size, char[] s) {
    // 2 rounds, first compute the size, 2nd rewrite the array
    int res = 0, i=0;
    for (; i<size; i++) {
      char c = s[i];
      switch (c) {
        case '.' : res+=2; break;
        case ',' : res+=4; break;
        case '?' : res+=12; break;
        case '!' : res+=15; break;
      }
      res++;
    }
    i--;
    int index=res-1;
    while (index > i) {
      char c = s[i];
      switch (c) {
        case '.' : rewrite(s, 3, "DOT", index); index-=3; break;
        case ',' : rewrite(s, 5, "COMMA", index); index-=5; break;
        case '?' : rewrite(s, 13, "QUESTION MARK", index); index-=13; break;
        case '!' : rewrite(s, 16, "EXCLAMATION MARK", index); index-=16; break;
        default : s[index--]=c;
      }
      i--;
    }
    System.out.println(String.valueOf(s));
    return res;
  }

  // NOTICE: Java is pass by value, so inside this method change the index will not impact index value outside of this method
  private static void rewrite(char[] s, int i, String c, int index) {
    while (i > 0) {
      s[index--] = c.charAt(--i);
    }
  }

  public static void main(String... arg) {
    String s = "?Grace , will ! go to Google in 2 years !.";
    int size=s.length();
    char[] chars = new char[200];
    for (int i=0; i<s.length(); i++) {
      chars[i]=s.charAt(i);
    }
    int res = ReplacePunctuation.replaceAndRemove(size, chars);
    System.out.println("QUESTION MARKGrace COMMA will EXCLAMATION MARK go to Google in 2 years EXCLAMATION MARKDOT");
    System.out.println(res); // should be "QUESTION MARKGrace COMMA will EXCLAMATION MARK go to Google in 2 years EXCLAMATION MARKDOT"
  }
}
