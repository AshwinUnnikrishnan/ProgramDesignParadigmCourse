package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to implement Strategic Investment of Portfolio.
 */
public class StrategyPortfolio extends FlexiblePortfolio {
  private final String startDate;
  private final String endDate;
  private final int range;
  private final float amount;


  /**
   * Constructor that creates a portfolio given the name and list of stocks as a string.
   *
   * @param name      name of the stock.
   * @param stockList list of all the stocks as string.
   * @param history   history as a string.
   */
  public StrategyPortfolio(String name, String stockList, String history, String strategy) {
    super(name, stockList, history);
    ArrayList<String> parsedData = this.getStrategyValues(strategy);
    this.range = Integer.parseInt(parsedData.get(0));
    this.startDate = parsedData.get(2);
    this.endDate = parsedData.get(3);
    this.amount = Float.parseFloat(parsedData.get(1));
  }

  /**
   * Constructor that creates portfolio object when only passed portfolio name. It creates an empty
   * portfolio set.
   *
   * @param name  : Name of the portfolio to create.
   * @param range : Range to be considered.
   */
  public StrategyPortfolio(String name, String range, String amount, String startDate,
                           String endDate) {
    super(name);
    this.range = Integer.parseInt(range);
    this.startDate = startDate;
    this.endDate = endDate;
    this.amount = Float.parseFloat(amount);
  }

  /**
   * Method to get the Strategy Values.
   *
   * @param strategy Array of values.
   * @return Resultant object.
   */
  private ArrayList<String> getStrategyValues(String strategy) {
    ArrayList<String> temp = new ArrayList<>();
    Pattern pattern = Pattern.compile("\"(.*?)\"");
    Matcher matcher = pattern.matcher(strategy);
    while (matcher.find()) {
      temp.add(matcher.group(1));
    }
    return temp;
  }

  @Override// TODO update this so that it shows flexible in the print as well
  public String toString() {

    StringBuilder sb = new StringBuilder("\"");
    sb.append(this.name);
    sb.append("%\" : [{\"Stock\" : {");
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
    sb.append("]},{\"Strategy\" : [\"");
    ArrayList<String> toStore = new ArrayList<>();
    toStore.add("" + this.range);
    toStore.add("" + this.amount);
    toStore.add(this.startDate);
    toStore.add(this.endDate);
    Iterator<String> strategy = toStore.iterator();
    while (strategy.hasNext()) {
      sb.append(strategy.next());
      sb.append(strategy.hasNext() ? "\",\"" : "\"");
    }
    sb.append("]}]");
    return String.valueOf(sb);
  }
}
