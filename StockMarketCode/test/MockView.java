import view.InvestorViewInterface;

/**
 * Class to mock the stockMarket view to test the controller to view outputs.
 */
public class MockView implements InvestorViewInterface {

  private StringBuilder log;

  public MockView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void printWelcomeScreen() {
    log.append("Input: printWelcomeScreen");
  }

  @Override
  public void getStringOutput(String output) {
    log.append("Input: getStringOutput");

  }

  @Override
  public void printWelcomeNewUser() {
    log.append("Input: printWelcomeNewUser");
  }

  @Override
  public void printAfterLoginScreen() {
    log.append("Input: printAfterLoginScreen");
  }

  @Override
  public void printStockBuyScreen() {
    log.append("Input: printStockBuyScreen");
  }

  @Override
  public void printPortfolioComposition(String printPortfolioComposition) {
    log.append("Input: printPortfolioComposition");
  }

  @Override
  public void printPortfolioEvaluation(String portFolioEvaluation) {
    log.append("Input: printPortfolioEvaluation");
  }

  @Override
  public void printOfflineMessage() {
    log.append("Input: printOfflineMessage");
  }

  @Override
  public void printCorruptFiles(String toString) {
    log.append("Input: printCorruptFiles");
  }

  @Override
  public void portfolioIssue() {
    log.append("Input: portfolioIssue");
  }

  @Override
  public void printSuccessLogin(String name) {
    log.append("Input: printSuccessLogin");
  }

  /**
   * Method to print user options to select either flexible or inflexible portfolio.
   */
  @Override
  public void printPortfolioType() {
    log.append("Input: printPortfolioType");
  }

  /**
   * Method to print that the portfolio is inflexible to update.
   *
   * @param portfolioName name of portfolio
   */
  @Override
  public void printInflexiblePortfolio(String portfolioName) {
    log.append("Input: printInflexiblePortfolio");
    log.append(portfolioName);
  }

  @Override
  public void printPortfolioUpdateMessage() {
    log.append("Input: printPortfolioUpdateMessage");
  }

  @Override
  public void printStockDeleteScreen() {
    log.append("Input: printStockDeleteScreen");
  }

  @Override
  public void printCostBasis(float costBasis, String portfolioName, String dateToCheck) {
    log.append("Input: printCostBasis");
    log.append(costBasis);
    log.append(portfolioName);
    log.append(dateToCheck);
  }

  @Override
  public void printPortfolioPerformance(String portfolioPerformance) {
    log.append("Input: printPortfolioPerformance");
    log.append(portfolioPerformance);
  }
}
