package model;

/**
 * Interface class which should be implemented by all the classes that make API calls to get stock
 * details.
 */
public interface StockAPI {

  /**
   * Method to fetch the stock value of a particular stock on a given date.
   *
   * @param stockSymbol stockName : name of the stock to get the data.
   * @param inputDate   inputDate : date for which we need the details.
   * @param now         to get regularMarketPrice or closing price
   * @return returns the float value as string of the price of the stock.
   */
  String getAPIdata(String stockSymbol, String inputDate, boolean now);

}
