package view.panel;

import java.awt.*;

import javax.swing.*;

import controller.Features;

/**
 * Class to implement display panel after logging in.
 */

public class AfterLogin extends JPanel implements PanelInterface {
  private final JButton createPortfolio;
  private final JButton compositionPortfolio;
  private final JButton evaluationPortfolio;
  private final JButton editPortfolio;
  private final JButton costBasisPortfolio;
  private final JButton dollarCostPortfolio;
  private final JButton portfolioPerformance;
  private final JButton exitButton;

  private boolean createUser;

  /**
   * Constructor to initialise the JButton Values and add layouts to the JPanel.
   */
  public AfterLogin() {
    this.createPortfolio = new JButton("Create Portfolio");
    this.compositionPortfolio = new JButton("Get Portfolio Composition");
    this.evaluationPortfolio = new JButton("Get Portfolio Evaluation");
    this.editPortfolio = new JButton("Edit Portfolio");
    this.costBasisPortfolio = new JButton("Get Cost Basis Portfolio");
    this.dollarCostPortfolio = new JButton("Create dollar cost Portfolio");
    this.portfolioPerformance = new JButton("Print Portfolio Performance");
    this.exitButton = new JButton("Exit Program");

    this.exitButton.setBackground(Color.RED);
    this.exitButton.setOpaque(true);
    this.exitButton.setBorderPainted(false);

    this.setLayout(new GridLayout(8, 1, 1, 1));
    this.add(this.createPortfolio);
    this.add(this.compositionPortfolio);
    this.add(this.evaluationPortfolio);
    this.add(this.editPortfolio);
    this.add(this.costBasisPortfolio);
    this.add(this.dollarCostPortfolio);
    this.add(this.portfolioPerformance);
    this.add(this.exitButton);
  }

  @Override
  public void addFeatures(Features features) {

    this.createPortfolio.addActionListener(evt -> features.createPortfolioScreen());
    this.compositionPortfolio.addActionListener(evt -> features.getCompositionScreen());
    this.evaluationPortfolio.addActionListener(evt -> features.getEvaluationScreen());
    this.costBasisPortfolio.addActionListener(evt -> features.getCostBasisScreen());

    this.dollarCostPortfolio.addActionListener(evt -> features.getDollarCostScreen());
    this.editPortfolio.addActionListener(evt -> features.getEditPortfolioScreen());
    //this.existingUser.addActionListener(evt -> features.showUserLoginButtons(false));
    this.exitButton.addActionListener(evt -> features.exitProgram());
    this.portfolioPerformance.addActionListener(evt -> features.getPerformanceScreen());

  }
}