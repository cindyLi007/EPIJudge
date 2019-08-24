package epi.excercise.array;

import java.util.Arrays;

/**
 * write a program to compute the minimum number of steps needed to advance to the last location
 * refer to {@link epi.AdvanceByOffsets}
 * must use DP to do it
 */
public class StepOfAdvanceByOffsets {

  // Time: O(N*N), Space: O(N)
  public int stepsOfAdvanceByOffsets_dp(int[] arrays) {
    int len = arrays.length;
    int[] min = new int[len], steps = new int[len];
    Arrays.fill(min, Integer.MAX_VALUE);
    min[0] = 0;

    for (int i = 0; i < len; i++) {
      for (int j=1; j<=arrays[i] && i+j < len; j++) {
        min[i+j] = Math.min(min[i+j], min[i]+1);
      }
    }

    return min[len - 1];
  }

  public int stepsOfAdvanceByOffsets(int[] arrays) {
    int len = arrays.length;
    int[] min = new int[len], steps = new int[len];
    Arrays.fill(min, Integer.MAX_VALUE);
    min[0] = 0;

    for (int i = 1; i < len; i++) {
      for (int j = 0; j < i; j++) {
        if (j + arrays[j] >= i && min[j] + 1 < min[i]) {
          min[i] = min[j] + 1;
          steps[i] = j;
        }
      }
    }

    int i = len - 1;
    StringBuilder sb = new StringBuilder().append(len - 1);
    while (i > 0) {
      sb.insert(0, steps[i]+"-->");
      i=steps[i];
    }
    System.out.println(sb);
    return min[len - 1];
  }

  public static void main(String[] args) {
    StepOfAdvanceByOffsets stepOfAdvanceByOffsets = new StepOfAdvanceByOffsets();
    int[] arrays_1 = {3, 3, 1, 0, 2, 0, 1};
    int[] arrays_2 = {5, 4, 3, 0, 2};
    int[] arrays = {6, 1, 2, 3, 6, 7, 2, 0, 2, 10, 0, 9, 2, 6};
    int result = stepOfAdvanceByOffsets.stepsOfAdvanceByOffsets(arrays);
    System.out.println(result);
    int result_1 = stepOfAdvanceByOffsets.stepsOfAdvanceByOffsets(arrays_1);
    System.out.println(result_1);
    int result_2 = stepOfAdvanceByOffsets.stepsOfAdvanceByOffsets(arrays_2);
    System.out.println(result_2);

    result = stepOfAdvanceByOffsets.stepsOfAdvanceByOffsets_dp(arrays);
    result_1 = stepOfAdvanceByOffsets.stepsOfAdvanceByOffsets_dp(arrays_1);
    result_2 = stepOfAdvanceByOffsets.stepsOfAdvanceByOffsets_dp(arrays_2);
    System.out.println("The step is " + result);
    System.out.println("The step is " + result_1);
    System.out.println("The step is " + result_2);
  }
}
