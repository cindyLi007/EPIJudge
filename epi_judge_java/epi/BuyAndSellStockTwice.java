package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BuyAndSellStockTwice {
  @EpiTest(testfile = "buy_and_sell_stock_twice.tsv")
  public static double buyAndSellStockTwice(List<Double> prices) {
    double[] firstProfit = new double[prices.size()];
    double minPrice = Double.MAX_VALUE, maxProfit = 0.0;
    for (int i = 0; i < prices.size(); i++) {
      minPrice = Math.min(prices.get(i), minPrice);
      maxProfit = Math.max(prices.get(i) - minPrice, maxProfit);
      firstProfit[i] = maxProfit;
    }
    maxProfit = 0.0;
    double maxPrice = 0.0, res = 0.0;
    for (int i = prices.size() - 1; i >= 0; i--) {
      res = Math.max(res, firstProfit[i] + maxProfit);
      maxPrice = Math.max(maxPrice, prices.get(i));
      maxProfit = Math.max(maxProfit, maxPrice - prices.get(i));
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
