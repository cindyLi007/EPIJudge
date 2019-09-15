package epi.excercise.dynamic.programming;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * EPI 16.11 Variant 2  Suppose the messiness of a line ending with b blank characters is defined to be b. Can you solve
 * the messiness minimization problem in O{n) time and O(1) space?
 */
public class MessinessWithSum {

  public static int minimumMessiness(List<String> words, int lineLength) {
    int N = words.size(), len = words.get(0).length(), sum=0;
    for (int i=1; i<N; i++) {
      int temp = len + words.get(i).length() + 1;
      if (temp > lineLength) {
        sum += lineLength - len;
        len = words.get(i).length();
      } else {
        len = temp;
      }
    }
    // for last line
    sum += lineLength - len;
    return sum;
  }

  public static void main(String... args) {
    String s = "I have inserted a large number of new examples from the papers for the Mathematical Tripos during the last twenty years, which should be useful to Cambridge students.";
    String[] array = s.split("\\s+");
    int messiness = minimumMessiness(Arrays.asList(array), 36);
    System.out.println(messiness);
  }
}
