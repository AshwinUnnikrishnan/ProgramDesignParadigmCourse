/**
 * Created by histravelstories.
 * This is an interface
 * Date : 11/3/22
 * Project Name : StockMarket
 */

package view;

/**
 * Interface to provide the functionalities an investorView should be implementing.
 */
public interface InvestorViewInterface {

  /**
   * Method to print welcome method when a user open the stockMarket Application.
   */
  void printWelcomeScreen();

  /**
   * Method to ask the user to input a particular component required by the program.
   *
   * @param output : name of the component to get from user.
   */
  void getStringOutput(String output);

  /**
   * Method to print welcome message when a user wants to create a new account.
   */
  void printWelcomeNewUser();

  /**
   * Method to print options the user has once he logs in.
   */
  void printAfterLoginScreen();

  /**
   * Method to print user options when he creates a portfolio and wants to add stocks to that.
   */
  void printStockBuyScreen();

  /**
   * Method to print the composition of a portfolio.
   *
   * @param printPortfolioComposition : InflexiblePortfolio composition.
   */
  void printPortfolioComposition(String printPortfolioComposition);

  /**
   * Method to print evaluation of a portfolio.
   *
   * @param portFolioEvaluation : Value of the whole portfolio.
   */
  void printPortfolioEvaluation(String portFolioEvaluation);

  /**
   * Method to print system offline in case when we see there is file reading or data related issue,
   * that cannot be corrected by user.
   */
  void printOfflineMessage();

  /**
   * Method to print message to screen when there is file corruption.
   *
   * @param toString : error that tells the issue that caused corruption.
   */
  void printCorruptFiles(String toString);

  /**
   * Method to print portfolio non creation due to no stock related to it.
   */
  void portfolioIssue();

  /**
   * Method to print login success.
   *
   * @param name name of the user logged in.
   */
  void printSuccessLogin(String name);

  /**
   * Method to print user options to select either flexible or inflexible portfolio.
   */
  void printPortfolioType();

  /**
   * Method to print that the portfolio is inflexible to update.
   * @param portfolioName name of the portfolio
   */
  void printInflexiblePortfolio(String portfolioName);

  void printPortfolioUpdateMessage();

  void printStockDeleteScreen();

  void printCostBasis(float costBasis, String portfolioName, String dateToCheck);

  void printPortfolioPerformance(String portfolioPerformance);
}