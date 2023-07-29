package view.panel;

import java.awt.*;

import javax.swing.*;

import controller.Features;

/**
 * Class to get Portfolio Performance using GUI.
 */
public class PortfolioPerformancePanel extends JPanel implements PanelInterface {

  private final JTextField portfolioName;
  private final JLabel warningMessage;
  private final JButton goBack;
  private final JTextField getDate;
  private final JTextField getDate2;
  private final JTextArea performanceResults;
  private final JButton getPerformance;

  /**
   * Constructor to initialise the JButton Values and add layouts to the JPanel.
   */
  public PortfolioPerformancePanel() {
    JLabel portfolioNameLabel = new JLabel("Enter Portfolio Name");
    this.portfolioName = new JTextField("");
    this.getPerformance = new JButton("Get Portfolio Performance");
    this.warningMessage = new JLabel("");
    this.performanceResults = new JTextArea("");
    JLabel getDateLabel = new JLabel("Enter Date 1 as yyyy-MM-dd");
    this.getDate = new JTextField("");
    JLabel getDateLabel2 = new JLabel("Enter Date 2 as yyyy-MM-dd");
    this.getDate2 = new JTextField("");
    this.goBack = new JButton("Back");
    JScrollPane performanceScroll = new JScrollPane(this.performanceResults);
    performanceScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


    this.setLayout(new GridLayout(10, 1, 1, 1));
    this.add(portfolioNameLabel);
    this.add(portfolioName);
    this.add(getDateLabel);
    this.add(getDate);
    this.add(getDateLabel2);
    this.add(getDate2);
    this.add(getPerformance);
    this.add(performanceScroll);
    this.add(goBack);
    this.add(warningMessage);
  }

  @Override
  public void addFeatures(Features features) {
    this.goBack.addActionListener(evt -> features.backToHome());
    this.getPerformance.addActionListener(evt -> {
      features.getPerformance(
              this.portfolioName.getText(), this.getDate.getText(), this.getDate2.getText());
    });
  }

  /**
   * Method to set warning.
   *
   * @param result resultant object.
   */
  public void setWarningResult(String result) {
    this.warningMessage.setText(result);
  }

  /**
   * Method to set Portfolio Performance.
   *
   * @param printPortfolioEvaluation string display portfolio performance.
   */
  public void setPerformance(String printPortfolioEvaluation) {
    this.performanceResults.setText(printPortfolioEvaluation);
  }

}
