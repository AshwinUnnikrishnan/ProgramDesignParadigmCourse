
package model;

import java.time.LocalDate;

/**
 * Class to implement Weighted Stocks.
 */
public class WeightedStock extends Stock {

  private final int weight;

  /**
   * Constructor to create a stock object that takes in details as individual arguments.
   *
   * @param name     : name of the stock.
   * @param value    : buying value of the stock.
   * @param quantity : quantity of stock bought.
   * @param date     : buying date.
   */
  public WeightedStock(String name, float value, float quantity, LocalDate date, int weight) {
    super(name, value, quantity, date);
    this.weight = weight;
  }

  /**
   * Constructor to create a stock object, which takes stock name and other details as a single
   * string.
   *
   * @param name         : name of the stock.
   * @param stockDetails : value, quantity and date of the stock as a single string.
   */
  public WeightedStock(String name, String stockDetails) {
    super(name, stockDetails);
    String[] parts = stockDetails.split(",");
    this.weight = Integer.parseInt(parts[3]);
  }

  @Override
  public String toString() {
    return "\"" + this.getName() + "\" : [" + this.getValue() + "," + this.getQuantity() + ","
            + "\"" + this.getDate() + "\"" + "," + this.weight + "]";
  }
}
