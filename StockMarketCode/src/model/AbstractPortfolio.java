/**
 * Created by histravelstories.
 * This is a class
 * Date : 11/13/22
 * Project Name : StockMarket
 */

package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstract class for Portfolio that implements common functions in a single place.
 */
abstract class AbstractPortfolio implements Portfolio {

  protected final String name;
  protected final Set<Stock> stockList;
  protected ArrayList<String> transactionHistory;

  /**
   * Helper method to convert a string into a set of stocks.
   *
   * @param stockInput String format of all the stocks as stored in the file.
   * @return Set of all the stocks in the string.
   */
  private Set<Stock> convertStringToStock(String stockInput) {
    Set<Stock> tempList = new HashSet<>();
    String stockRegex = "\"([A-Z0-9]+)\" : \\[(.+?)]";
    Pattern regexStockPattern = Pattern.compile(stockRegex);

    Matcher matcher = regexStockPattern.matcher(stockInput);
    while (matcher.find()) {
      String stockName = matcher.group(1);
      String stockDetails = matcher.group(2);
      tempList.add(new Stock(stockName, stockDetails));
    }
    return tempList;
  }

  /**
   * Constructor to assign values to the variables in the class extended to other classes.
   *
   * @param name name of the portfolio
   */
  public AbstractPortfolio(String name) {
    this.name = name;
    this.stockList = new HashSet<>();
  }

  /**
   * Constructor to assign values to the variables in the class extended to other classes. We pass
   * name and the stockList to this constructor.
   *
   * @param name      name of the portfolio
   * @param stockList list of stocks in the portfolio
   */
  public AbstractPortfolio(String name, String stockList) {
    this.name = name;
    this.stockList = convertStringToStock(stockList);
  }

  /**
   * Abstract Method to update the history of the portfolio, all sell and buy are stored in the
   * transaction history. Implemented in specific portfolio classes.
   *
   * @param stockName name of the stock bought or sold.
   * @param price     buying or selling price of stock.
   * @param quantity  quantity of stock bought or sold.
   * @param date      date of stock buy or sell.
   * @param buy       true if buy else false
   * @param broker    broker fees
   */
  abstract protected void updateHistory(String stockName, float price, float quantity,
                                        LocalDate date, boolean buy, float broker);

  /**
   * Method to add stock to portfolio.
   *
   * @param stockName Name of the stock.
   * @param quantity  Quantity of the stock.
   * @param price     Buying price of the stock.
   * @param date      Date the stock is bought on.
   * @param broker    broker fees.
   * @return returns true if stock added.
   */
  @Override
  public boolean addStockToPortfolio(String stockName, float quantity, float price, LocalDate date,
                                     float broker, int weighted) {
    Stock stock = null;
    if (weighted == -1) {
      stock = new Stock(stockName, price, quantity, date);
    } else {
      stock = new WeightedStock(stockName, price, quantity, date, weighted);
    }
    this.stockList.add(stock);
    this.updateHistory(stockName, price, quantity, date, true, broker);
    return true;
  }

  /**
   * Getter method to get name of the portfolio.
   *
   * @return name of the portfolio.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Method to get list of stocks in the portfolio.
   *
   * @return list of stocks in the portfolio.
   */
  @Override
  public Set<Stock> getStockList() {
    return this.stockList;
  }

  /**
   * Method to update portfolio to add stocks after portfolio are already created.
   *
   * @param stockName Name of the stock
   * @param quantity  Quantity of stocks
   * @param price     Buying price of stocks
   * @param date      Date of buying
   * @return true if able to add
   */
  @Override
  public boolean updatePortfolio(String stockName, float quantity, float price, LocalDate date,
                                 float broker) {
    return false;
  }

  /**
   * Helper method to convert History string to list of transactions.
   *
   * @param historyString history as a single string read from file.
   * @return returns an arraylist of strings containing all the transactions.
   */
  protected ArrayList<String> processHistoryToList(String historyString) {
    ArrayList<String> temp = new ArrayList<>();
    Pattern pattern = Pattern.compile("\"(.*?)\"");
    Matcher matcher = pattern.matcher(historyString);
    while (matcher.find()) {
      temp.add(matcher.group(1));
    }
    return temp;
  }

  /**
   * Method to evaluate cost basis for a given portfolio.
   *
   * @param dateToCheck date on which we need the cost basis.
   * @return the cost basis.
   */
  public float evaluateCostBasis(String dateToCheck) {
    Iterator<String> itr = this.transactionHistory.iterator();
    float costBasis = 0.0f;
    while (itr.hasNext()) {
      String temp = itr.next();
      String[] history = temp.split(",");

      if (LocalDate.parse(dateToCheck).isAfter(LocalDate.parse(history[1]))) {

        if (history[0].equals("Buy")) {
          costBasis = costBasis + Float.parseFloat(history[4]) * Float.parseFloat(history[2]);
        }
        costBasis = costBasis + Float.parseFloat(history[5]);
      }
    }
    return costBasis;
  }

  /**
   * Helper method to get transaction history.
   *
   * @return transaction history as an array list of strings.
   */
  public ArrayList<String> getTransactionHistory() {
    return this.transactionHistory;
  }

}