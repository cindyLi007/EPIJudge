package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class FindSalaryThreshold {
  @EpiTest(testfile = "find_salary_threshold.tsv")

  // Time: O(n*lgN)
  public static double findSalaryCap(int targetPayroll,
                                     List<Integer> currentSalaries) {
    Collections.sort(currentSalaries);
    int nochangeSalarySum = 0;
    for (int i=0; i<currentSalaries.size(); i++) {
      // how many people will use current item's salary
      int numberOfChangeSalaryPeople = currentSalaries.size() - i;
      int changedSalariesSum = currentSalaries.get(i) * numberOfChangeSalaryPeople;
      // threshold is to find a point which after this point, sth. happend, before this point, everything is fine
      // for this question, we need find the item which from this item (inclusive it), use its salary will exceed the targetPayroll
      // the strategy is from low to high salary, one by one to find one item if all succesor items use this items salary,
      // whether will exceed the targetPayroll, if not, that means we can go to next one, if yes, that means we need cut
      // salary for all items from this item.
      if (changedSalariesSum + nochangeSalarySum >= targetPayroll) {
        return (double) (targetPayroll - nochangeSalarySum) / numberOfChangeSalaryPeople;
      }
      nochangeSalarySum += currentSalaries.get(i);
    }
    // this means no need to set threshold
    return -1.0;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
