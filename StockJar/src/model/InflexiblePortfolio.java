/**
 * Created by histravelstories.
 * This is a class
 * Date : 10/29/22
 * Project Name : StockMarket
 */

package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class that describes a portfolio object and its related functions.
 * InflexiblePortfolio object store a list of stocks and name of the portfolio
 */
public class InflexiblePortfolio extends AbstractPortfolio {

  /**
   * Constructor that creates a portfolio given the name and list of stocks as a string.
   *
   * @param name      name of the stock.
   * @param stockList list of all the stocks as string.
   * @param history   history of transactions as a string.
   */
  public InflexiblePortfolio(String name, String stockList, String history) {
    super(name, stockList);
    this.transactionHistory = this.processHistoryToList(history);
  }

  /**
   * Method to update transaction histories.
   *
   * @param stockName name of the stock we bought or sold.
   * @param price     price at which we bought or sold.
   * @param quantity  quantity of stocks.
   * @param date      date at which operation was carried out.
   */
  @Override
  protected void updateHistory(String stockName, float price, float quantity, LocalDate date,
                               boolean buy, float broker) {
    String action = buy ? "Buy" : "Sell";
    this.transactionHistory.add(action + "," + date.toString() + "," + price + ","
            + stockName + "," + quantity + "," + broker);
  }


  /**
   * Constructor that creates portfolio object when only passed portfolio name. It creates a empty
   * portfolio set.
   *
   * @param name : Name of the portfolio to create.
   */
  public InflexiblePortfolio(String name) { //TODO get boolean value for flexible vs inflexible
    super(name);
    this.transactionHistory = new ArrayList<String>();
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder("\"");
    sb.append(this.name);
    sb.append("\" : [{\"Stock\" : {");
    Iterator<Stock> itr = this.stockList.iterator();
    while (itr.hasNext()) {
      sb.append(itr.next().toString());
      sb.append(itr.hasNext() ? "," : "");
    }
    sb.append("}},{\"History\" : [\"");
    Iterator<String> transactions = this.transactionHistory.iterator();
    while (transactions.hasNext()) {
      sb.append(transactions.next());
      sb.append(transactions.hasNext() ? "\",\"" : "\"");
    }
    sb.append("]}]");
    return String.valueOf(sb);
  }

  /**
   * Method to check if the portfolio is flexible or not.
   *
   * @return true if it is, flase if not
   */
  @Override
  public boolean isFlexible() {
    return false;
  }

  @Override
  public void editStockInPortfolio(String stockName, String quantity, float priceOnDate,
                                   String sellDate, float broker) {
    return;
  }

}
