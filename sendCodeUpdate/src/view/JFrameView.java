package view;

import java.awt.CardLayout;


import javax.swing.JPanel;
import javax.swing.JFrame;


import controller.Features;
import model.Portfolio;
import view.panel.AfterLogin;
import view.panel.CostBasisPanel;
import view.panel.CreatePortfolio;
import view.panel.DollarCostLogin;
import view.panel.GetEvaluationPanel;
import view.panel.PortfolioComposition;
import view.panel.PortfolioEditPanel;
import view.panel.PortfolioPerformancePanel;
import view.panel.SignInPanel;

/**
 * Class that implements the view for GUI and has implementation of all the methods.
 */
public class JFrameView extends JFrame implements IView {

  private JPanel baseP = new JPanel();
  private SignInPanel signInPanel;
  private AfterLogin afterLogin;
  private PortfolioComposition portfolioComposition;
  private CreatePortfolio createPortfolio;
  private PortfolioEditPanel portfolioEditPanel;
  private GetEvaluationPanel getEvaluationPanel;
  private CostBasisPanel costBasisPanel;
  private DollarCostLogin dollarCostLogin;
  private PortfolioPerformancePanel portfolioPerformancePanel;
  private CardLayout cardLayout = new CardLayout();

  /**
   * Constructor for the GUI view.
   *
   * @param caption caption to set the initial.
   */
  public JFrameView(String caption) {
    super(caption);
    setSize(500, 500);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    baseP.setLayout(cardLayout);
    this.add(this.baseP);
    signInPanel = new SignInPanel();
    afterLogin = new AfterLogin();
    createPortfolio = new CreatePortfolio();
    portfolioComposition = new PortfolioComposition();
    portfolioEditPanel = new PortfolioEditPanel();
    getEvaluationPanel = new GetEvaluationPanel();
    costBasisPanel = new CostBasisPanel();
    dollarCostLogin = new DollarCostLogin();
    portfolioPerformancePanel = new PortfolioPerformancePanel();
    baseP.add(signInPanel, "signInPanel");
    baseP.add(afterLogin, "afterLogin");
    baseP.add(createPortfolio, "createPortfolio");
    baseP.add(portfolioComposition, "portfolioComposition");
    baseP.add(portfolioEditPanel, "portfolioEditPanel");
    baseP.add(getEvaluationPanel, "getEvaluationPanel");
    baseP.add(costBasisPanel, "getCostBasisPanel");
    baseP.add(dollarCostLogin, "dollarCost");
    baseP.add(portfolioPerformancePanel, "getPortfolioPerformancePanel");
  }

  @Override
  public void addFeatures(Features features) {
    this.signInPanel.addFeatures(features);
    this.afterLogin.addFeatures(features);
    this.createPortfolio.addFeatures(features);
    this.portfolioComposition.addFeatures(features);
    this.portfolioEditPanel.addFeatures(features);
    this.getEvaluationPanel.addFeatures(features);
    this.costBasisPanel.addFeatures(features);
    this.dollarCostLogin.addFeatures(features);
    this.portfolioPerformancePanel.addFeatures(features);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setLoginUserNameError(String newUser) {
    this.signInPanel.setWarningLabel(newUser);
    if (newUser.equals("Login Successful")) {
      this.cardLayout.show(this.baseP, "afterLogin");
    }
  }


  @Override
  public void makeLoginVisible(boolean createUser) {
    this.signInPanel.makeLoginVisible(createUser);
  }

  @Override
  public void changeToCreatePortfolio() {
    this.cardLayout.show(this.baseP,
            "createPortfolio");
  }

  @Override
  public void addEmptyPortfolio(Portfolio newPortfolio) {
    this.createPortfolio.setPortfolio(newPortfolio, false);
  }

  @Override
  public void setCreatePortfolioError(String result) {
    this.createPortfolio.setWarning(result);
  }

  @Override
  public void addStockPortfolio(Portfolio nPortfolio, boolean create) {
    if (create) {
      this.createPortfolio.setPortfolio(nPortfolio, true);
    } else {
      this.portfolioEditPanel.setPortfolio(nPortfolio);
    }
  }

  @Override
  public void setToAfterLogin() {
    this.cardLayout.show(this.baseP, "afterLogin");
  }

  @Override
  public void setToPortfolioComposition() {
    this.cardLayout.show(this.baseP, "portfolioComposition");
  }

  @Override
  public void setCompositionWarning(String result) {
    this.portfolioComposition.setWarningResult(result);
  }

  @Override
  public void printPortfolioComposition(String printPortfolioComposition) {
    this.portfolioComposition.setCompositionResults(printPortfolioComposition);
  }

  @Override
  public void setEditPortfolioScreen() {
    this.cardLayout.show(this.baseP,
            "portfolioEditPanel");
  }

  @Override
  public void showPortfolioComposition(String printPortfolioComposition) {
    this.portfolioEditPanel.setCompositionResults(printPortfolioComposition);
  }

  @Override
  public void setEditPortfolioWarning(String warningMessage) {
    this.portfolioEditPanel.setWarningMessage(warningMessage);
  }

  @Override
  public void setPortfolioEditPanel(Portfolio portfolio) {
    this.portfolioEditPanel.setPortfolio(portfolio);
  }

  @Override
  public void setEvaluationWarning(String result) {
    this.getEvaluationPanel.setWarningResult(result);
  }

  @Override
  public void printPortfolioEvaluation(String printPortfolioEvaluation) {
    this.getEvaluationPanel.setGetEvaluation(printPortfolioEvaluation);
  }

  @Override
  public void setToPortfolioEvaluation() {
    this.cardLayout.show(this.baseP, "getEvaluationPanel");
  }

  @Override
  public void setCostBasisWarning(String result) {
    this.costBasisPanel.setWarningResult(result);
  }

  @Override
  public void printCostBasis(String printCostBasis) {
    this.costBasisPanel.setCostBasis(printCostBasis);
  }

  @Override
  public void setCostBasisPanel() {
    this.cardLayout.show(this.baseP, "getCostBasisPanel");
  }

  @Override
  public void setDollarCostScreen() {
    this.cardLayout.show(this.baseP, "dollarCost");
  }

  @Override
  public void setDollarStrategyWarning(String result) {
    this.dollarCostLogin.setWarningMessage(result);
  }

  @Override
  public void setPortfolioInStrategy(Portfolio newPortfolio) {
    this.dollarCostLogin.setPortfolio(newPortfolio);
  }

  @Override
  public void addStockToList(String stockName, String stockWeight) {
    this.dollarCostLogin.addStockToList(stockName, stockWeight);
  }

  @Override
  public void printPerformance(String printPortfolioPerformance) {
    this.portfolioPerformancePanel.setPerformance(printPortfolioPerformance);
  }

  @Override
  public void setPerformanceWarning(String result) {
    this.portfolioPerformancePanel.setWarningResult(result);
  }

  @Override
  public void setPerformancePanel() {
    this.cardLayout.show(this.baseP, "getPortfolioPerformancePanel");
  }
}
