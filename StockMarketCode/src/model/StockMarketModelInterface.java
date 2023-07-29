package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Interface that contains all the functions a stock market should implement.
 */
public interface StockMarketModelInterface {

  /**
   * Method to calculate evaluation of certain set of stocks on a particular date.
   *
   * @param currentUser A set containing all the stocks
   * @param portfolio   name of the portfolio.
   * @param date        Date where we want to evaluate.
   * @return returns a float of evaluation of profit.
   */
  float getEvaluation(Investor currentUser, String portfolio, String date);


  /**
   * Method to update the user details to the database file.
   *
   * @param currentUser Details about the user passed as investor object.
   * @param newUser     boolean that tells if the call is for newUser or existing
   * @throws IOException Thrown when write fails, when writing new user or existing.
   */
  void writeUpdatedUser(Investor currentUser, boolean newUser) throws IOException;


  /**
   * methdod to get current price of particular stock on a date.
   *
   * @param stockName Stock name
   * @param date      Date
   * @param now       if true then we calculate for current time "RegularMarketPrice", else
   *                  we take close price,
   * @return closing price of stock on that date.
   */
  float getPriceOnDate(String stockName, LocalDate date, boolean now);


  /**
   * Helper method to check if the username is present in the list.
   *
   * @param name          name of the stock or name to check.
   * @param userNameCheck boolean to mention if checking userName or stock
   * @return true if the thing searching exists.
   */
  boolean checkUserOrStock(String name, boolean userNameCheck);

  /**
   * Method to check if entered stock exists in the portfolio of not.
   *
   * @param nPortfolio portfolio to chekc the stock.
   * @param stockName  name of the stock to be checked.
   * @return successful stock if exists, null if not.
   */
  Stock checkStockInPortfolio(Portfolio nPortfolio, String stockName);

  /**
   * Load the userDetails if the user exists in database or create a new investor with userName
   * passed.
   *
   * @param userName userName of the person logged in.
   * @return Investor object of the user.
   * @throws FileNotFoundException : thrown if the user details database does not exist.
   */
  Investor loadFromExisting(String userName) throws FileNotFoundException;


  /**
   * Method to add username to username file and database file when new user is created.
   *
   * @param userName : username with which we want to create profile.
   * @return : returns the username.
   * @throws IOException : Thrown when write exception happens in file.
   */
  Investor addUserName(String userName) throws IOException;

  /**
   * Method to check if a given user contains a portfolio with a given name or not.
   *
   * @param currentUser   User to check.
   * @param portfolioName portfolio name to check.
   * @return true if it exists false if it does not.
   */
  boolean portfolioExists(Investor currentUser, String portfolioName);


  /**
   * Method to check if the portfolio is flexible or not.
   *
   * @param currentUser   investor object.
   * @param portfolioName portfolioName.
   * @return true if flexible else false.
   */
  boolean checkPortfolioFlexible(Investor currentUser, String portfolioName);

  /**
   * Method to add stocks to portfolio for a given user.
   *
   * @param portfolio InflexiblePortfolio to add the stock to
   * @param stockName name of the stock
   * @param quantity  number of stocks
   * @param date      date to buy the stock
   * @param create    true if to add stock
   * @param broker    broker fees charged
   * @return added portfolio
   */
  Portfolio addStockToPortfolio(Portfolio portfolio, String stockName, String quantity,
                                String date, boolean create, float broker);

  /**
   * Method to add the newly created portfolio to the user.
   *
   * @param investor     Investor to which we want to add the portfolio
   * @param newPortfolio portfolio to be added.
   * @return updated investor
   */
  Investor addPortfolio(Investor investor, Portfolio newPortfolio);

  /**
   * Method to print portfolio composition for a given profile.
   *
   * @param investor      name of the investor whose portfolio composition we want.
   * @param portfolioName name of the portfolio.
   * @param date          date of the composition.
   * @return returns the composition.
   */
  String printPortfolioComposition(Investor investor, String portfolioName, String date);

  /**
   * Method to sell stocks from a portfolio.
   *
   * @param nPortfolio Portfolio from which we need to sell the stock.
   * @param stockName  Name of the stock to sell.
   * @param quantity   Number of stocks to sell.
   * @param sellDate   Date on which we want to sell the stocks.
   * @param brokerfees broker fees.
   * @return the updated portfolio after selling the stock.
   */
  Portfolio deleteStockFromPortfolio(Portfolio nPortfolio, String stockName, String quantity,
                                     String sellDate, float brokerfees);

  /**
   * Method to evaluate the cost basis of a given portfolio for a given user.
   *
   * @param currentUser   Investor whose cost basis we want to calculate.
   * @param portfolioName portfolioName that we want to evaluate cost basis.
   * @param dateToCheck   date to evaluate cost basis.
   * @return the costbasis
   */
  float evaluateCostBasis(Investor currentUser, String portfolioName, String dateToCheck);

  /**
   * Method to calculate the portfolio performance.
   *
   * @param currentUser   Investor to see performance.
   * @param portfolioName Portfolio name to check portfolio performance.
   * @param dateInitial   first date.
   * @param dateFinal     last date.
   * @return the visualization
   * @throws IOException throws IO exception while write.
   */
  String calculatePortfolioPerformance(Investor currentUser, String portfolioName,
                                       String dateInitial, String dateFinal) throws IOException;

  Portfolio strategyInvestment(Portfolio nPortfolio, ArrayList<String> stockNames, String amount,
                               ArrayList<String> weight, String startDate, String endDate,
                               String range, int flag);
}
