/**
 * Created by histravelstories.
 * This is an interface
 * Date : 11/13/22
 * Project Name : StockMarket
 */

package model;

import java.time.LocalDate;
import java.util.Set;

/**
 * Interface for portfolio which mentions the important methods suppoerted by different types of
 * portfolio.
 */
public interface Portfolio {


  /**
   * Method to add stock to the portfolio.
   *
   * @param stockName Name of the stock.
   * @param quantity  Quantity of the stock.
   * @param price     Buying price of the stock.
   * @param date      Date the stock is bought on.
   * @param broker    broker fees.
   * @param weighted  boolean to mention if the stock is weighted or not.
   * @return true if the stock is added to portfolio.
   */
  boolean addStockToPortfolio(String stockName, float quantity, float price, LocalDate date,
                              float broker, int weighted);

  /**
   * Getter to get the name of the portfolio.
   *
   * @return name of the portfolio.
   */
  String getName();

  /**
   * Getter to get the stocks in the portfolio.
   *
   * @return the list of stocks in the portfolio.
   */
  Set<Stock> getStockList();

  /**
   * Method to update portfolio to add stocks after portfolio are already created.
   *
   * @param stockName Name of the stock
   * @param quantity  Quantity of stocks
   * @param price     Buying price of stocks
   * @param date      Date of buying
   * @param broker    broker fees.
   * @return true if able to add
   */
  boolean updatePortfolio(String stockName, float quantity, float price, LocalDate date,
                          float broker);

  /**
   * Method to check if the portfolio is flexible or not.
   *
   * @return true if it is, flase if not
   */
  boolean isFlexible();

  /**
   * Method to edit the stocks in a portfolio, it can be add or sell.
   *
   * @param stockName   Name of the stock
   * @param quantity    Quantity of stocks
   * @param priceOnDate Buying price of stocks
   * @param sellDate    date of selling
   * @param broker      broker fee.
   */
  void editStockInPortfolio(String stockName, String quantity, float priceOnDate, String sellDate,
                            float broker);

  /**
   * Method to evaluate cost basis.
   *
   * @param dateToCheck date on which we need the cost basis.
   * @return cost basis.
   */
  float evaluateCostBasis(String dateToCheck);


}
