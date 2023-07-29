package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that represents an investor, it stores the investor name and the portfolio he has.
 */
public class Investor {
  private final String name;
  private Map<String, Portfolio> portfolios;

  /**
   * Helper method to convert string that contains portfolio in json format to map.
   *
   * @param userDetails contains all the portfolio details as a string.
   * @return hashMap that contains portfolio name as key and the portfolio object as value.
   */
  private Map<String, Portfolio> getPortfoliosFromString(String userDetails) {
    Map<String, Portfolio> temp = new HashMap<>();
    String flexiblePortfolioRegex =
            "\"([a-zA-Z0-9]+)#+\" : \\[\\{\"Stock\" : \\{(.*?])}},\\{\"History\" : "
                    + "\\[([\\w+\",-\\.]*)]}]";
    String inflexiblePortfolioRegex =
            "\"([a-zA-Z0-9]+)\" : \\[\\{\"Stock\" : \\{(.*?])}},\\{\"History\" : "
                    + "\\[([\\w+\",-\\.]*)]}]";
    String strategicalPortfolioRegex = "\"([a-zA-Z0-9]+)%+\" : \\[\\{\"Stock\" : \\{(.*?])}},\\"
            + "{\"History\" : \\[([\\w+\",-\\.]*)]},\\{\"Strategy\" : \\[([\\w+\",-\\.]*)]}]";

    Pattern regexPatternFlexiblePortfolio = Pattern.compile(flexiblePortfolioRegex);
    Pattern regexPatternInflexiblePortfolio = Pattern.compile(inflexiblePortfolioRegex);
    Pattern regexPatternStrategicalPortfolio = Pattern.compile(strategicalPortfolioRegex);

    Matcher matcherFlexible;
    Matcher matcherInflexible;
    Matcher matcherStrategy;

    matcherFlexible = regexPatternFlexiblePortfolio.matcher(userDetails);
    matcherInflexible = regexPatternInflexiblePortfolio.matcher(userDetails);
    matcherStrategy = regexPatternStrategicalPortfolio.matcher(userDetails);

    while (true) {
      boolean inflexibleFind = matcherInflexible.find();
      boolean flexibleFind = matcherFlexible.find();
      boolean strategyFind = matcherStrategy.find();

      if (!flexibleFind && !inflexibleFind && !strategyFind) {
        break;
      }
      if (inflexibleFind) {
        String portfolioName = matcherInflexible.group(1);
        String stocks = matcherInflexible.group(2);
        String history = matcherInflexible.group(3);
        temp.put(portfolioName, new InflexiblePortfolio(portfolioName, stocks, history));
      }
      if (flexibleFind) {
        String portfolioName = matcherFlexible.group(1);
        String stocks = matcherFlexible.group(2);
        String history = matcherFlexible.group(3);
        temp.put(portfolioName, new FlexiblePortfolio(portfolioName, stocks, history));
      }
      if (strategyFind) {
        String portfolioName = matcherStrategy.group(1);
        String stocks = matcherStrategy.group(2);
        String history = matcherStrategy.group(3);
        String strategy = matcherStrategy.group(4);
        Portfolio nPortfolio = new StrategyPortfolio(portfolioName, stocks, history, strategy);

        // Find the last date of all transactions
        // From history get the last transaction date
        // Get the last date from strategy
        // Get interval from strategy
        // range, amount, first date, last date
        String[] partsOfStrategy = strategy.replace("\"", "").split(",");

        int range = Integer.parseInt(partsOfStrategy[0]);
        float amount = Float.parseFloat(partsOfStrategy[1]);
        LocalDate startDate = LocalDate.parse(partsOfStrategy[2]);
        String endD = partsOfStrategy[3];
        if (endD.equals("-1")) {
          endD = LocalDate.now() + "";
        }
        LocalDate endDate = LocalDate.parse(endD);
        LocalDate currentDate = LocalDate.now();
        if (!(currentDate.isBefore(startDate) || currentDate.isAfter(endDate)
                || currentDate.isEqual(endDate))) {
          if (history != null) {
            // need to find start and end date else the start and end date stays
            Pattern pattern = Pattern.compile("\"(.*?)\"");
            Matcher matcher = pattern.matcher(history);
            LocalDate lastTransactionDate = null;
            while (matcher.find()) {
              LocalDate dateOfTransaction = LocalDate.parse(matcher.group(1).split(",")[1]);
              if (lastTransactionDate == null || dateOfTransaction.isAfter(lastTransactionDate)) {
                lastTransactionDate = dateOfTransaction;
              }
            }

            startDate = lastTransactionDate;
            if (currentDate.isBefore(endDate)) {
              endDate = currentDate;
            }

            // part to get make the stockName array and the stockWeight array
            Set<Stock> tempList = new HashSet<>();
            String stockRegex = "\"([A-Z0-9]+)\" : \\[(.+?)]";
            Pattern regexStockPattern = Pattern.compile(stockRegex);

            Matcher stockMatcher = regexStockPattern.matcher(stocks);
            ArrayList<String> stockNames = null;
            ArrayList<String> stockWeight = null;
            while (stockMatcher.find()) {
              stockNames.add(stockMatcher.group(1));
              stockWeight.add(stockMatcher.group(2).split(",")[3]);
            }
            DollarCost investmentStrategy = new DollarCost(range, amount);
            nPortfolio = investmentStrategy.calculateTheInvestment(nPortfolio, stockNames,
                    stockWeight, startDate + "", endDate + "",
                    new AlphaVantageAPI());
          }
        }
        temp.put(portfolioName, nPortfolio);
      }
    }
    return temp;
  }

  /**
   * Constructor to create investor object when investor name, and InflexiblePortfolio and stock
   * details are provided as a string.
   *
   * @param name        : Investor name
   * @param userDetails : InflexiblePortfolio details
   */
  public Investor(String name, String userDetails) {
    this.name = name;
    this.portfolios = getPortfoliosFromString(userDetails);
  }

  /**
   * Constructor to create investor when only a name is passed.
   *
   * @param name name of the investor.
   */
  public Investor(String name) {
    this.name = name;
    this.portfolios = new HashMap<>();
  }

  /**
   * getter method to get the name of the investor.
   *
   * @return name of the investor.
   */
  public String getName() {
    return this.name;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("\"");
    sb.append(this.name);
    sb.append("\" :{\"Portfolio\":{");
    Iterator itr = this.portfolios.entrySet().iterator();
    while (itr.hasNext()) {
      sb.append(((Map.Entry) itr.next()).getValue().toString());
      sb.append(itr.hasNext() ? "," : "");
    }
    sb.append("}}");
    return String.valueOf(sb);
  }


  /**
   * Method to print the portfolio composition of an investor.
   *
   * @param portfolioName : portfolio name whose composition is to be printed.
   * @return Composition of the portfolio
   */
  public String printPortfolioComposition(String portfolioName) {
    Set<Stock> temp = this.portfolios.get(portfolioName).getStockList();
    StringBuilder sb = new StringBuilder();
    Iterator<Stock> itr = temp.iterator();
    while (itr.hasNext()) {
      sb.append(itr.next().toString());
      sb.append(itr.hasNext() ? "\n" : "");
    }
    return sb.toString();
  }

  /**
   * Getter method to get the Stock of a particular portfolio.
   *
   * @param portfolioName : name of the portfolio for which we need to get the stocks.
   * @return Set of stocks.
   */
  public Set<Stock> getStocksSet(String portfolioName) {
    return this.portfolios.get(portfolioName).getStockList();
  }

  /**
   * Getter method to get the hashmap of the portfolios.
   *
   * @return hashmap of portfolio.
   */
  public Map getPortfolio() {
    return this.portfolios;
  }

}
