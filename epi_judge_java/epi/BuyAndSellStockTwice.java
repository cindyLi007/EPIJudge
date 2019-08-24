package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class BuyAndSellStockTwice {
  @EpiTest(testfile = "buy_and_sell_stock_twice.tsv")
  public static double buyAndSellStockTwice(List<Double> prices) {
    List<Double> firstProfit = new ArrayList<>();
    Double max = 0.0, min = Double.MAX_VALUE;
    for (Double price : prices) {
      min = Math.min(min, price);
      max = Math.max(max, price - min);
      firstProfit.add(max);
    }
    double res = 0;
    max = 0.0;
    double profit = 0.0;
    for (int i=prices.size()-1; i>=0; i--) {
      res = Math.max(res, profit + firstProfit.get(i));
      profit = Math.max(profit, max - prices.get(i));
      max = Math.max(max, prices.get(i));
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
