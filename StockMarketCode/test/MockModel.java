import java.time.LocalDate;
import java.util.ArrayList;

import model.Investor;
import model.Portfolio;
import model.Stock;
import model.StockMarketModelInterface;

/**
 * Class to mock the stockMarket model to test the controller to model passing inputs.
 */
public class MockModel implements StockMarketModelInterface {

  private StringBuilder log;

  public MockModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public float getEvaluation(Investor currentUser, String portfolioName, String dateToCheck) {
    log.append("Input: " + portfolioName);
    log.append("Input: " + dateToCheck);
    return 0;
  }

  @Override
  public void writeUpdatedUser(Investor currentUser, boolean newUser) {
    log.append("Input: " + currentUser);
    log.append("Input: " + newUser);
  }

  @Override
  public float getPriceOnDate(String stockName, LocalDate date, boolean now) {
    log.append("Input: " + stockName);
    log.append("Input: " + date);
    log.append("Input: " + now);
    return 0;
  }

  @Override
  public boolean checkUserOrStock(String name, boolean userNameCheck) {
    log.append("Input: " + name);
    log.append("Input: " + userNameCheck);
    return true;
    //sreturn true;
  }

  /**
   * Method to check if entered stock exists in the portfolio of not.
   *
   * @param nPortfolio portfolio to chekc the stock.
   * @param stockName  name of the stock to be checked.
   * @return true if stock exists, false if does not.
   */
  @Override
  public Stock checkStockInPortfolio(Portfolio nPortfolio, String stockName) {
    return null;
  }

  @Override
  public Investor loadFromExisting(String userName) {
    log.append("Input: " + userName);
    return new Investor(userName,
            "{\"two\" : {\"Stock\" : {\"T\" : [18.43,12,\"2022-11-02\"]}}}");
  }

  @Override
  public Investor addUserName(String userName) {
    log.append("Input: " + userName);
    return new Investor(userName);
  }

  /**
   * Method to check if a given user contains a portfolio with a given name or not.
   *
   * @param currentUser   User to check.
   * @param portfolioName portfolio name to check.
   * @return true if it exists false if it does not.
   */
  @Override
  public boolean portfolioExists(Investor currentUser, String portfolioName) {
    return false;
  }

  /**
   * Method to check if the portfolio is flexible or not.
   *
   * @param currentUser   investor object.
   * @param portfolioName portfolioName.
   * @return true if flexible else false.
   */
  @Override
  public boolean checkPortfolioFlexible(Investor currentUser, String portfolioName) {
    return false;
  }

  /**
   * Method to add stocks to portfolio for a given user.
   *
   * @param portfolio InflexiblePortfolio to add the stock to
   * @param stockName name of the stock
   * @param quantity  number of stocks
   * @return added portfolio
   */
  @Override
  public Portfolio addStockToPortfolio(Portfolio portfolio, String stockName, String quantity,
                                       String date, boolean create, float broker) {
    return null;
  }

  /**
   * Method to add the newly created portfolio to the user.
   *
   * @param investor     the investor.
   * @param newPortfolio portfolio to be added.
   * @return returns the investor
   */
  @Override
  public Investor addPortfolio(Investor investor, Portfolio newPortfolio) {
    return null;
  }

  /**
   * Method to print portfolio composition for a given profile.
   *
   * @param investor      name of the investor whose portfolio composition we want.
   * @param portfolioName name of the portfolio.
   * @return returns the composition.
   */
  @Override
  public String printPortfolioComposition(Investor investor, String portfolioName, String date) {
    return null;
  }

  @Override
  public Portfolio deleteStockFromPortfolio(Portfolio nPortfolio, String stockName,
                                            String quantity, String sellDate, float broker) {
    return null;
  }

  @Override
  public float evaluateCostBasis(Investor currentUser, String portfolioName, String dateToCheck) {
    return 0;
  }

  @Override
  public String calculatePortfolioPerformance(Investor currentUser, String portfolioName,
                                              String dateInitial, String dateFinal) {
    return null;
  }

  @Override
  public Portfolio strategyInvestment(Portfolio nPortfolio, ArrayList<String> stockNames,
                                      String amount, ArrayList<String> weight, String startDate,
                                      String endDate, String range, int flag) {
    return null;
  }
}
