/**
 * Created by histravelstories.
 * This is a class
 * Date : 10/25/22
 * Project Name : model.StockMarketModel
 */

package view;

import java.io.PrintStream;

/**
 * Class that contains all the functions that help print things to ouptut so that the user can see.
 * It takes a printStream as input, which tells where the output is supposed to be printed to.
 * Separate functions are created for different prints, even though it looks redundant this is done
 * in the consideration that it will be easier to implement when we move to a GUI based design.
 */
public class InvestorView implements InvestorViewInterface {

  final PrintStream out;      // To know where to data goes

  /**
   * Constructor that takes in the type of printStream where we need to print the data for user to
   * see.
   *
   * @param out : where to print.
   */
  public InvestorView(PrintStream out) {
    this.out = out;
  }

  /**
   * Method to print welcome method when a user open the stockMarket Application.
   */
  public void printWelcomeScreen() {
    out.println("Welcome to the Stock Exchange\nPress 1 : Existing User\nPress 2 : "
            + "New User\nPress 3 : Exit");
  }

  /**
   * Method to ask the user to input a particular component required by the program.
   *
   * @param output : name of the component to get from user.
   */
  public void getStringOutput(String output) {
    out.println("Please Enter " + output);
  }


  /**
   * Method to print welcome message when a user wants to create a new account.
   */
  public void printWelcomeNewUser() {
    out.println("Welcome to stock world, need couple of information before you can start buying");
  }

  /**
   * Method to print options the user has once he logs in.
   */
  public void printAfterLoginScreen() {
    out.println("Please select one of the options");
    out.println("Press 1 to create new Portfolio");
    out.println("Press 2 to examine composition of a portfolio");
    out.println("Press 3 to determine total value of a portfolio on a certain date");
    out.println("Press 4 to edit portfolio");
    out.println("Press 5 to determine cost basis of portfolio");
    out.println("Press 6 to see portfolio Performance");
    out.println("Press 7 to exit");
  }


  /**
   * Method to print user options when he creates a portfolio and wants to add stocks to that.
   */
  public void printStockBuyScreen() {
    out.println("Please select one of the options");
    out.println("Press 1 to Buy Stock");
    out.println("Press 2 to Exit");
  }

  /**
   * Method to print the composition of a portfolio.
   *
   * @param printPortfolioComposition : InflexiblePortfolio composition.
   */
  public void printPortfolioComposition(String printPortfolioComposition) {
    out.println("Stock Name : [Buying Price($), Quantity, Buying Date]");
    out.println(printPortfolioComposition);
  }

  /**
   * Method to print evaluation of a portfolio.
   *
   * @param portFolioEvaluation : Value of the whole portfolio.
   */
  public void printPortfolioEvaluation(String portFolioEvaluation) {
    out.println("The present evaluation is " + portFolioEvaluation);
  }

  /**
   * Method to print system offline in case when we see there is file reading or data related issue,
   * that cannot be corrected by user.
   */
  public void printOfflineMessage() {
    out.println("The system if offline, Please contact the stockMarker to check for tickerFiles");
  }

  /**
   * Method to print message to screen when there is file corruption.
   *
   * @param toString : error that tells the issue that caused corruption.
   */
  public void printCorruptFiles(String toString) {
    out.println("The files are corrupted so cannot run the System " + toString);
  }

  /**
   * Method to print portfolio non creation due to no stock related to it.
   */
  public void portfolioIssue() {
    out.println("InflexiblePortfolio not created because there were no stocks in portfolio");
  }

  /**
   * Method to print login success.
   *
   * @param name name of the user logged in.
   */
  public void printSuccessLogin(String name) {
    out.println("Successfully logged in " + name);
  }

  /**
   * Method to print user options to select either flexible or inflexible portfolio.
   */
  @Override
  public void printPortfolioType() {
    out.println("Please select one of the options");
    out.println("Press 1 for inflexible portfolio");
    out.println("Press 2 for flexible portfolio");
  }

  /**
   * Method to print portfolio is not flexible to make changes.
   *
   * @param portfolioName : name of the portfolio that is not flexible.
   */
  @Override
  public void printInflexiblePortfolio(String portfolioName) {
    out.println("The portfolio entered " + portfolioName + " is inflexible.");
  }

  /**
   * Method to print the portfolio update screen, to either add more stocks or delete stocks from
   * portfolio.
   */
  @Override
  public void printPortfolioUpdateMessage() {
    out.println("Please select one of the options");
    out.println("Press 1 adding more stocks to portfolio");
    out.println("Press 2 for deleting stocks from portfolio");
  }

  /**
   * Method to print options to sell stocks or exit selling.
   */
  @Override
  public void printStockDeleteScreen() {
    out.println("Please select one of the options");
    out.println("Press 1 to Sell Stock");
    out.println("Press 2 to Exit");
  }

  /**
   * Method to print cost basis output.
   *
   * @param costBasis     Amount spent on the portfolio
   * @param portfolioName portfolio name
   * @param dateToCheck   date to check the cost basis
   */
  @Override
  public void printCostBasis(float costBasis, String portfolioName, String dateToCheck) {
    out.println("Cost basis for portfolio " + portfolioName + " on date " + dateToCheck + " is "
            + costBasis + "$");
  }

  /**
   * Method to print portfolio performance.
   *
   * @param portfolioPerformance portfolio performance results.
   */
  @Override
  public void printPortfolioPerformance(String portfolioPerformance) {
    out.println(portfolioPerformance);
  }
}
