
package model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class to get the Evaluation of a portfolio.
 */
public class GetEvaluation {

  private final StockAPI api;

  /**
   * Constructor to initialise the API of stocks.
   *
   * @param api Stock API.
   */
  public GetEvaluation(StockAPI api) {
    this.api = api;
  }

  /**
   * Method to get the evaluation of a portfolio.
   *
   * @param nPortfolio  Name of the portfolio.
   * @param dateToCheck date to evaluate the portfolio.
   * @return the resultant object.
   */

  public float getEvaluationF(Portfolio nPortfolio, String dateToCheck) {
    ArrayList<String> transactionHistory = ((AbstractPortfolio) nPortfolio).getTransactionHistory();
    Iterator<String> itr = transactionHistory.iterator();
    float totalEval = 0.0f;
    while (itr.hasNext()) {
      String temp = itr.next();
      String[] history = temp.split(",");

      if (LocalDate.parse(dateToCheck).isAfter(LocalDate.parse(history[1]))) {
        if (history[0].equals("Sell")) {
          totalEval = totalEval + Integer.parseInt(history[4]) * Float.parseFloat(history[2]);
        } else {
          totalEval = totalEval + Integer.parseInt(history[4]) * this.getPriceOnDate(history[3],
                  LocalDate.parse(dateToCheck));
        }
        totalEval = totalEval - Float.parseFloat(history[5]);
      }
    }
    return totalEval;
  }

  /**
   * Method to get Price of a stock on a particular date.
   *
   * @param stockName Name of the stock.
   * @param date      date of evaluation.
   * @return price of the stock on the given date.
   */
  public float getPriceOnDate(String stockName, LocalDate date) {
    return Float.parseFloat(this.api.getAPIdata(stockName, date.toString(), true));
  }
}
