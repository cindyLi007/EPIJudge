package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Iterator;
import java.util.List;

public class MajorityElement {

  public static String majoritySearch(Iterator<String> stream) {
    int count = 0;
    String res = "";
    while (stream.hasNext()) {
      String s = stream.next();
      if (count == 0) {
        res=s;
        count=1;
      } else if (res.equals(s)) {
        count++;
      } else {
        count--;
      }
    }
    return res;
  }

  @EpiTest(testfile = "majority_element.tsv")
  public static String majoritySearchWrapper(List<String> stream) {
    return majoritySearch(stream.iterator());
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
