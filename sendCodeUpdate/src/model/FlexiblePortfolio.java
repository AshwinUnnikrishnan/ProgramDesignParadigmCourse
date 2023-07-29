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
 * FlexiblePortfolio object store a list of stocks and name of the portfolio
 */
public class FlexiblePortfolio extends AbstractPortfolio {


  /**
   * Constructor that creates a portfolio given the name and list of stocks as a string.
   *
   * @param name      name of the stock.
   * @param stockList list of all the stocks as string.
   * @param history   history as a string.
   */
  public FlexiblePortfolio(String name, String stockList, String history) {
    //TODO get boolean value for flexible vs inflexible
    super(name, stockList);
    this.transactionHistory = this.processHistoryToList(history);
  }

  /**
   * Constructor that creates portfolio object when only passed portfolio name. It creates a empty
   * portfolio set.
   *
   * @param name : Name of the portfolio to create.
   */
  public FlexiblePortfolio(String name) { //TODO get boolean value for flexible vs inflexible
    super(name);
    this.transactionHistory = new ArrayList<>();
  }


  protected void updateHistory(String stockName, float price, float quantity, LocalDate date,
                               boolean buy, float broker) {
    String action = buy ? "Buy" : "Sell";
    this.transactionHistory.add(action + "," + date.toString() + "," + price + "," + stockName
            + "," + quantity + "," + broker);
  }

  @Override// TODO update this so that it shows flexible in the print as well
  public String toString() {

    StringBuilder sb = new StringBuilder("\"");
    sb.append(this.name);
    sb.append("#\" : [{\"Stock\" : {");
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

  @Override
  public boolean updatePortfolio(String stockName, float quantity, float price, LocalDate date,
                                 float broker) {
    this.addStockToPortfolio(stockName, quantity, price, date, broker, -1);
    return true;
  }

  /**
   * Method to check if the portfolio is flexible or not.
   *
   * @return true if it is, false if not
   */
  @Override
  public boolean isFlexible() {
    return true;
  }

  @Override
  public void editStockInPortfolio(String stockName, String quantity, float priceOnDate,
                                   String sellDate, float broker) {
    Iterator<Stock> itr = this.stockList.iterator();
    while (itr.hasNext()) {
      Stock currentStock = itr.next();
      if (currentStock.getName().equals(stockName)) {
        currentStock.setQuantity(currentStock.getQuantity() - Float.parseFloat(quantity));
        break;
      }
    }
    this.updateHistory(stockName, priceOnDate, Integer.parseInt(quantity),
            LocalDate.parse(sellDate), false, broker);
  }
}