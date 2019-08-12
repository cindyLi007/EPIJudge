package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BuyAndSellStock {
  @EpiTest(testfile = "buy_and_sell_stock.tsv")
  // Time: O(N), Space: O(1)
  public static double computeMaxProfit(List<Double> prices) {
    double maxProfit = 0.0, minPrice = Double.MAX_VALUE;
    for (Double price : prices) {
      maxProfit = Math.max(maxProfit, price - minPrice);
      minPrice = Math.min(minPrice, price);
    }
    return maxProfit;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
