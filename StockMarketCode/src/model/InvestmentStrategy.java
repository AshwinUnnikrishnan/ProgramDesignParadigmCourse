package model;

import java.util.ArrayList;

/**
 * Interface to implement the Investment strategy of a portfolio.
 */
public interface InvestmentStrategy {
  /**
   * Method to calculate investment.
   *
   * @param nPortfolio Name of the portfolio.
   * @param stockNames Name of Stocks.
   * @param weight     Weight to be considered for stocks.
   * @param startDate  Start date for Investment.
   * @param endDate    End date for Investment.
   * @param api        STOCK API for values.
   * @return resultant object.
   */
  Portfolio calculateTheInvestment(Portfolio nPortfolio, ArrayList<String> stockNames,
                                   ArrayList<String> weight, String startDate,
                                   String endDate, StockAPI api);
}
