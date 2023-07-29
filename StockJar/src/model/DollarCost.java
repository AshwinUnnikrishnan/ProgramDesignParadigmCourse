package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class that determines Dollar cost of a portfolio.
 * This class implements Investment Strategy.
 */
public class DollarCost implements InvestmentStrategy {

  private final int range;
  private final float amount;


  /**
   * Constructor to initialize values of range and amount.
   *
   * @param range  range of dates.
   * @param amount Amount of money.
   */
  public DollarCost(int range, float amount) {
    this.range = range;
    this.amount = amount;
  }

  @Override
  public Portfolio calculateTheInvestment(Portfolio nPortfolio, ArrayList<String> stockNames,
                                          ArrayList<String> weight, String startDate,
                                          String endDate, StockAPI api) {
    if (endDate.equals("-1")) {
      endDate = LocalDate.now().toString();
    }
    GetEvaluation func = new GetEvaluation(new AlphaVantageAPI());
    LocalDate beginDate = LocalDate.parse(startDate);
    while (beginDate.isBefore(LocalDate.parse(endDate))
            || beginDate.isEqual(LocalDate.parse(endDate))) {
      int countOfStocks = stockNames.size();
      int i = 0;
      while (i < countOfStocks) {
        int stockWeight = Integer.parseInt(weight.get(i));
        String stockName = stockNames.get(i);
        float todayBuyingPrice = func.getPriceOnDate(stockName, beginDate);
        float quantity = (this.amount * (stockWeight / 100.0f)) / todayBuyingPrice;

        nPortfolio.addStockToPortfolio(stockName, quantity, todayBuyingPrice, beginDate,
                30.0f, stockWeight);
        i += 1;
      }
      beginDate = beginDate.plusDays(this.range);
    }
    return nPortfolio;
  }
}
