package controller;

import java.util.ArrayList;

import model.Portfolio;

/**
 * Interface to incorporate the necessary features in the Graphical User Interface.
 */
public interface Features {
  /**
   * Method for Login Screen.
   *
   * @param userName   the current user.
   * @param createUser to check if it's a new user.
   */
  void login(String userName, boolean createUser);

  /**
   * Method to add features to the controller GUI.
   *
   * @param features Object of features to be added.
   */
  void addFeatures(Features features);

  /**
   * Method to exit the program.
   */
  void exitProgram();

  /**
   * Method to display the login buttons to the user.
   *
   * @param newUser to check if it's a new user.
   */
  void showUserLoginButtons(boolean newUser);

  /**
   * Method to create the portfolio screen of the program.
   */
  void createPortfolioScreen();

  /**
   * Method to add the Portfolio screen to the JFrame.
   *
   * @param portfolioName The name of the portfolio.
   * @param portfolioType Type of portfolio - flexible or inflexible.
   */
  void addPortfolio(String portfolioName, String portfolioType);

  /**
   * Method to persist the data and return to the previous screen.
   *
   * @param nPortfolio The name of the portfolio.
   */
  void saveAndBack(Portfolio nPortfolio);

  /**
   * Method to add stock to a portfolio.
   *
   * @param nPortfolio    The name of the portfolio.
   * @param stockName     The name of the stock.
   * @param stockQuantity The quantity of stocks to be purchased.
   * @param brokerFee     The broker fee for transaction.
   * @param buyingDate    The date on which the stocks need to be bought.
   * @param create        to check if it's a new user.
   */
  void addStock(Portfolio nPortfolio, String stockName, String stockQuantity,
                String brokerFee, String buyingDate, boolean create);

  /**
   * Method to display the screen with options to view the composition of a portfolio.
   */
  void getCompositionScreen();

  /**
   * Method to take the user back to the home screen of the GUI.
   */
  void backToHome();

  /**
   * Method to calculate the composition of a particular portfolio.
   *
   * @param portfolioName Name of the portfolio.
   */
  void getComposition(String portfolioName, String date);

  /**
   * Method to display the screen for editing the portfolio.
   */
  void getEditPortfolioScreen();

  /**
   * Method to display the screen for loading the portfolio.
   *
   * @param portfolioName Name of the portfolio.
   */
  void loadPortfolio(String portfolioName);

  /**
   * Method to delete stocks from a portfolio.
   *
   * @param nPortfolio  Name of the portfolio.
   * @param stockName   Name of the stock to be deleted.
   * @param quantity    Quantity of stocks to be deleted.
   * @param brokerFee   The broker fee for the transaction.
   * @param sellingDate The date on which the stocks need to be sold.
   * @param b           To check if it's a new user or not.
   */
  void deleteStock(Portfolio nPortfolio, String stockName, String quantity, String brokerFee,
                   String sellingDate, boolean b);

  /**
   * Method for getting the evaluation of a portfolio.
   *
   * @param text  Name of the portfolio.
   * @param text1 Date on which evaluation is to be performed.
   */
  void getEvaluation(String text, String text1);

  /**
   * Method to display the screen for getting the evaluation of a portfolio.
   */
  void getEvaluationScreen();

  /**
   * Method to get the Cost basis of a portfolio.
   *
   * @param text  Name of the portfolio.
   * @param text1 Date on which cost basis is to be performed.
   */
  void getCostBasis(String text, String text1);

  /**
   * Method to display the screen for getting the cost basis of a portfolio.
   */
  void getCostBasisScreen();

  /**
   * Method to display the screen for getting the Dollar Cost of a Portfolio.
   */
  void getDollarCostScreen();

  /**
   * Method to start adding stocks to a strategic portfolio.
   *
   * @param portfolioName Name of the portfolio.
   * @param totalStocks   Total number of stocks to be added.
   * @param amount        Total amount of money to be invested.
   * @param startDate     The date from which the portfolio should start buying stocks.
   * @param continuous    parameter to check if this is continuous configuration.
   * @param endDate       The date on which the portfolio should stop buying stocks.
   * @param range         Range of days to purchase stocks in a strategic portfolio.
   */
  void startAddingStock(String portfolioName, String totalStocks, String amount, String startDate,
                        String continuous, String endDate, String range);

  /**
   * Method to strategically buy stocks based on the weight provided by the user.
   *
   * @param stockName   Name of the stock to be added.
   * @param stockWeight Proposed Weight to purchase the stocks.
   */
  void addWeightStockToPortfolio(String stockName, String stockWeight);

  /**
   * Method to create Strategic Portfolio.
   *
   * @param nPortfolio     Name of the portfolio.
   * @param stockList      List of stocks to be added.
   * @param amount         The total amount to be invested.
   * @param weightOfStocks Proposed Weight to purchase the stocks.
   * @param startDate      The date from which the portfolio should start buying stocks.
   * @param endDate        The date on which the portfolio should stop buying stocks
   * @param range          Range of days to purchase stocks in a strategic portfolio.
   * @param i              Interval provided by the user.
   */
  void createStrategyPortfolio(Portfolio nPortfolio, ArrayList<String> stockList, String amount,
                               ArrayList<String> weightOfStocks, String startDate, String endDate,
                               String range, boolean i);

  /**
   * Method to get the performance of a portfolio.
   *
   * @param text  Name of the portfolio.
   * @param text1 Start date for performance analysis.
   * @param text2 End date for performance analysis.
   */
  void getPerformance(String text, String text1, String text2);

  /**
   * Method to display the screen for selecting Performance of a portfolio.
   */
  void getPerformanceScreen();
}
