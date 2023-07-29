package view;

import controller.Features;
import model.Portfolio;

/**
 * The interface for our view class for the GUI view.
 */
public interface IView {

  /**
   * Method to add the features link the controller methods to view.
   *
   * @param features : object of controller.
   */
  void addFeatures(Features features);

  /**
   * Method to make the view visible.
   */
  void makeVisible();

  /**
   * Method to set the error when the Jpanel is in login page.
   *
   * @param newUser username
   */
  void setLoginUserNameError(String newUser);

  /**
   * Method to make the login jPanel visible.
   *
   * @param createUser the username
   */
  void makeLoginVisible(boolean createUser);

  /**
   * Method to change the Jpanel to createPortfolio.
   */
  void changeToCreatePortfolio();

  /**
   * Method to add empty portfolio while creating the stock in create stock.
   *
   * @param newPortfolio Portfolio to create
   */
  void addEmptyPortfolio(Portfolio newPortfolio);

  /**
   * Method to set the errors in Jpanel of create Portfolio.
   *
   * @param result error message
   */
  void setCreatePortfolioError(String result);

  /**
   * Method to add stock to portfolio.
   *
   * @param nPortfolio porfolio to add the stock to.
   * @param create     if we are creating or editing.
   */
  void addStockPortfolio(Portfolio nPortfolio, boolean create);

  /**
   * Method to set the view to After successful login.
   */
  void setToAfterLogin();

  /**
   * Method to change the Jpanel to Portfolio composition screen.
   */
  void setToPortfolioComposition();

  /**
   * Method to set warnings in the composition page.
   *
   * @param result warning mesage
   */
  void setCompositionWarning(String result);

  /**
   * Method to return portfolio composition.
   *
   * @param printPortfolioComposition portfolio composition.
   */
  void printPortfolioComposition(String printPortfolioComposition);

  /**
   * Method to move to edit portfolio screen.
   */
  void setEditPortfolioScreen();

  /**
   * Method to show the portfolio composition results on screen.
   *
   * @param printPortfolioComposition result of portfolio composition.
   */
  void showPortfolioComposition(String printPortfolioComposition);

  /**
   * Method to set the warning in the edit portfolio page.
   *
   * @param error error Message
   */
  void setEditPortfolioWarning(String error);

  /**
   * Method to set the panel to portfolio edit panel.
   *
   * @param portfolio portfolio to edit
   */
  void setPortfolioEditPanel(Portfolio portfolio);

  /**
   * Method to set warning messages in the evaluation screen.
   *
   * @param errorMessage errorMessage
   */
  void setEvaluationWarning(String errorMessage);

  /**
   * Method to print portfolioEvaluation.
   *
   * @param evaluation evaluation
   */
  void printPortfolioEvaluation(String evaluation);

  /**
   * Method to set the screen to portfolio Evaluation screen.
   */
  void setToPortfolioEvaluation();

  /**
   * Method to set the warning messages in cost basis.
   *
   * @param eMessages error message
   */
  void setCostBasisWarning(String eMessages);

  /**
   * Method to print cost basis onj the screen.
   *
   * @param value cost basis result.
   */
  void printCostBasis(String value);

  /**
   * Method to set the screen to cost basis panel.
   */
  void setCostBasisPanel();

  /**
   * Method to set the screen to dollar cost screen.
   */
  void setDollarCostScreen();

  /**
   * Method to set the warning messages in the dollar strategy screen.
   *
   * @param result warning messages
   */
  void setDollarStrategyWarning(String result);

  /**
   * Method to set the portfolio in stragtegy screen.
   *
   * @param newPortfolio portfolio to set.
   */
  void setPortfolioInStrategy(Portfolio newPortfolio);

  /**
   * Method to add the stock to list in dollar strategy.
   *
   * @param stockName   stock Name.
   * @param stockWeight stock weight.
   */
  void addStockToList(String stockName, String stockWeight);

  /**
   * Method to set warning messages in print performance screen.
   *
   * @param result warning message
   */
  void setPerformanceWarning(String result);

  /**
   * Method to print performance.
   *
   * @param valueOf performance result
   */
  void printPerformance(String valueOf);

  /**
   * Method to set panel to performance.
   */
  void setPerformancePanel();

}
