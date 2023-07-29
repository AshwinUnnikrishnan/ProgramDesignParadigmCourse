package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Class to calculate portfolio performance.
 */
public class PortfolioPerformance {
  private final DateTimeFormatter formatterDays = DateTimeFormatter.ofPattern("dd MMM yyyy");
  private final DateTimeFormatter formatterMonths = DateTimeFormatter.ofPattern("MMM yyyy");
  private final DateTimeFormatter formatterYears = DateTimeFormatter.ofPattern("yyyy");
  private int flagDaysMonthsYears;
  private final Portfolio portfolioName;
  List<Double> evaluationsList = new ArrayList();

  /**
   * Constructor to initialize portfolio with the portfolio for which we need performance.
   *
   * @param portfolioName Object of portfolio to get performance
   */
  public PortfolioPerformance(Portfolio portfolioName) {
    this.portfolioName = portfolioName;
  }


  /**
   * Method to calculate performance of the portfolio.
   *
   * @param date1 firstEnd of the date range.
   * @param date2 secondEnd of the date range.
   * @return the visualization
   */
  public String portfolioPerformance(String date1, String date2) {
    LocalDate finalDate1 = null;
    LocalDate finalDate2 = null;
    LocalDate formattedDate1 = LocalDate.parse(date1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    LocalDate formattedDate2 = LocalDate.parse(date2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    if (DAYS.between(formattedDate1, formattedDate2) > 0) {
      finalDate1 = formattedDate1;
      finalDate2 = formattedDate2;
    }
    String mess = "Performance of portfolio " + this.portfolioName.getName() + " from "
            + formattedDate1
            + " to " + formattedDate2 + "\n";
    long flag = noOfDaysMonthsYears(finalDate1, finalDate2);
    if (flag == -1) {
      return mess + "We do not support for ranges more than 30 years or less than 5 days";
    }
    return mess + performanceInDWMY(finalDate1, flag, flagDaysMonthsYears);
  }

  /**
   * Method to calculate the performance with respect to Days Weeks Month and Year.
   *
   * @param date          start Date
   * @param datesInterval Interval of dates printed
   * @param flagDWMY      flag to metion if interval is day, weeks, months etc.
   * @return the visualization string.
   */
  private String performanceInDWMY(LocalDate date, long datesInterval, int flagDWMY) {
    LocalDate startDate = date;
    String str = null;
    int iterator = 0;
    switch (flagDWMY) {
      case 1:
      case 2:
        iterator = (int) datesInterval;
        break;
      case 3:
        LocalDate day = startDate.with(TemporalAdjusters.lastDayOfMonth());

        startDate = LocalDate.of(day.getYear(), day.getMonth(), day.getDayOfMonth());
        iterator = (int) datesInterval;
        break;
      case 4:
        LocalDate midDay = startDate.with(TemporalAdjusters.lastDayOfMonth());
        startDate = LocalDate.of(startDate.getYear(), startDate.getMonth(), midDay.getDayOfMonth());
        iterator = (int) datesInterval / 3;
        break;
      case 5:
        startDate = LocalDate.of(startDate.getYear(), 12, 31);
        iterator = (int) datesInterval;
        break;
      default:
        break;
    }
    for (int i = 0; i < iterator; i++) {
      double f = getEvaluation(this.portfolioName, String.valueOf(startDate));
      this.evaluationsList.add(f);
      switch (flagDWMY) {
        case 1:
          startDate = startDate.plusDays(1);
          str = "days";
          break;
        case 2:
          startDate = startDate.plusWeeks(1);
          str = "weeks";
          break;
        case 3:
          startDate = startDate.plusMonths(1);
          str = "months";
          break;
        case 4:
          startDate = startDate.plusMonths(3);
          str = "threemonths";
          break;
        case 5:
          startDate = startDate.plusYears(1);
          str = "years";
          break;
        default:
          break;
      }
    }
    return String.valueOf(printAsterisk(scaleCalculation((Collections.max(evaluationsList))),
            evaluationsList, date, str));
  }

  /**
   * Method to print asterisk with scaling as well.
   *
   * @param scale    how much we need to divide each evaluation value by.
   * @param evalList list of all the evaluated values.
   * @param date     date to start
   * @param range    flag set to see days, weeks, months, year
   * @return the visualized output.
   */
  private StringBuilder printAsterisk(int scale, List<Double> evalList, LocalDate date,
                                      String range) {
    LocalDate startDate = date;
    StringBuilder outputString = new StringBuilder("");
    for (int i = 0; i < evalList.size(); i++) {
      String text = null;
      switch (range) {
        case "days":
          startDate = startDate.plusDays(1);
          text = startDate.format(formatterDays);
          break;
        case "weeks":
          startDate = startDate.plusWeeks(1);
          text = startDate.format(formatterDays);
          break;
        case "months":
          startDate = startDate.plusMonths(1);
          text = startDate.format(formatterMonths);
          break;
        case "threemonths":
          startDate = startDate.plusMonths(3);
          text = startDate.format(formatterMonths);
          break;
        case "years":
          startDate = startDate.plusYears(1);
          text = startDate.format(formatterYears);
          break;
        default:
          break;
      }
      outputString.append(text + ": ");
      for (int j = 0; j < evalList.get(i) / scale; j++) {
        outputString.append("*");
      }
      outputString.append("\n");
    }
    outputString.append("Scale: * = $").append(scale).append("\n");
    return outputString;
  }

  /**
   * Helper method to calculate the interval between the dates.
   *
   * @param finalDate1 first date
   * @param finalDate2 second date
   * @return the length of the interval.
   */
  private long noOfDaysMonthsYears(LocalDate finalDate1, LocalDate finalDate2) {

    long interval = 0;//TODO check and see if the dates are before portfolio createion

    long noOfDays = ChronoUnit.DAYS.between(finalDate1, finalDate2) + 1L;
    long noOfWeeks = ChronoUnit.WEEKS.between(finalDate1, finalDate2) + 1L;
    long noOfMonths = ChronoUnit.MONTHS.between(finalDate1, finalDate2) + 1L;
    long noOfYears = ChronoUnit.YEARS.between(finalDate1, finalDate2) + 1L;

    if (noOfDays >= 5 && noOfDays <= 30) {
      interval = noOfDays;
      flagDaysMonthsYears = 1;
    } else if (noOfDays > 30 && noOfMonths < 5) {
      interval = noOfWeeks;
      flagDaysMonthsYears = 2;
    } else if (noOfMonths >= 5 && noOfMonths <= 30) {
      interval = noOfMonths;
      flagDaysMonthsYears = 3;
    } else if (noOfMonths > 30 && noOfYears < 5) {
      interval = noOfMonths;
      flagDaysMonthsYears = 4;
    } else if (noOfYears > 5 && noOfYears < 30) {
      interval = noOfYears;
      flagDaysMonthsYears = 5;
    } else {
      return -1;
    }
    return interval;
  }

  /**
   * Method to calculate the scale for any list of values.
   *
   * @param evaluatedAmount maximum value in the evaluation.
   * @return the scaling factor
   */
  private int scaleCalculation(double evaluatedAmount) {
    return (int) ((evaluatedAmount < 50) ? evaluatedAmount : (evaluatedAmount / 50));
  }

  /**
   * Method to get evaluation of a particular stock sets.
   *
   * @param nPortfolio Portfolio.
   * @param date       date to get the values.
   * @return evaluated values.
   */
  private float getEvaluation(Portfolio nPortfolio, String date) {
    ArrayList<String> transactionHistory = ((AbstractPortfolio) nPortfolio).getTransactionHistory();
    Iterator<String> itr = transactionHistory.iterator();
    AlphaVantageAPI api = new AlphaVantageAPI();
    float totalEval = 0.0f;
    while (itr.hasNext()) {
      String temp = itr.next();
      String[] history = temp.split(",");

      if (LocalDate.parse(date).isAfter(LocalDate.parse(history[1]))) {
        if (history[0].equals("Sell")) {
          totalEval = totalEval + Float.parseFloat(history[4]) * Float.parseFloat(history[2]);
        } else {
          totalEval = totalEval + Float.parseFloat(history[4]) * this.getPriceOnDate(history[3],
                  LocalDate.parse(date), true, api);
        }
        totalEval = totalEval - Float.parseFloat(history[5]);
      }
    }
    return totalEval;
  }

  /**
   * Method to get price of a stock on a particular date.
   *
   * @param stockName name of the stock that we want the price for.
   * @param date      date for which we need price.
   * @param now       if today.
   * @param api       the stock api.
   * @return the price of stock on that date.
   */
  private float getPriceOnDate(String stockName, LocalDate date, boolean now, StockAPI api) {
    return Float.parseFloat(api.getAPIdata(stockName, date.toString(), now));
  }

}
