package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents a stock. It has name, value, quantity and buyingDate as data.
 */
public class Stock implements StockInterface {
  private final String name;
  private final float value;
  private float quantity;
  private LocalDate buyingDate;

  /**
   * Constructor to create a stock object that takes in details as individual arguments.
   *
   * @param name     : name of the stock.
   * @param value    : buying value of the stock.
   * @param quantity : quantity of stock bought.
   * @param date     : buying date.
   */
  public Stock(String name, float value, float quantity, LocalDate date) {
    this.name = name;
    this.value = value;
    this.quantity = quantity;
    this.buyingDate = date;
  }

  /**
   * Constructor to create a stock object, which takes stock name and other details as a single
   * string.
   *
   * @param name         : name of the stock.
   * @param stockDetails : value, quantity and date of the stock as a single string.
   */
  public Stock(String name, String stockDetails) {
    this.name = name;
    String[] parts = stockDetails.split(",");
    this.value = Float.parseFloat(parts[0]);
    this.quantity = Float.parseFloat(parts[1]);
    this.buyingDate = LocalDate.parse(parts[2].substring(1, parts[2].length() - 1),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }

  /**
   * Getter method to get the buying value of the stock.
   *
   * @return value of stock.
   */
  public float getValue() {
    return this.value;
  }

  /**
   * Getter method to get the quantity of stock.
   *
   * @return quantity of stock.
   */
  public float getQuantity() {
    return this.quantity;
  }

  public void setQuantity(float quantity) {
    this.quantity = quantity;
  }

  /**
   * Getter method to get the name of the stock.
   *
   * @return name of the stock.
   */
  public String getName() {
    return this.name;
  }


  /**
   * Getter method to get the buying date.
   *
   * @return date the stock was bought on.
   */
  public LocalDate getDate() {
    return this.buyingDate;
  }

  @Override
  public String toString() {
    return "\"" + this.getName() + "\" : [" + this.getValue() + "," + this.getQuantity() + ","
            + "\"" + this.buyingDate + "\"]";
  }
}
