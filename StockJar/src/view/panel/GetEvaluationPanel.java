package view.panel;

import java.awt.*;

import javax.swing.*;

import controller.Features;

/**
 * Class to get evaluation panel.
 */
public class GetEvaluationPanel extends JPanel implements PanelInterface {

  private final JTextField portfolioName;
  private final JButton getEvaluation;
  private final JLabel warningMessage;
  private final JTextArea evaluationResults;
  private final JButton goBack;
  private final JTextField getDate;

  /**
   * Constructor to initialise the JButton Values and add layouts to the JPanel.
   */
  public GetEvaluationPanel() {
    JLabel portfolioNameLabel = new JLabel("Enter Portfolio Name");
    this.portfolioName = new JTextField("");
    this.getEvaluation = new JButton("Get Evaluation");
    this.warningMessage = new JLabel("");
    this.evaluationResults = new JTextArea("");
    JLabel getDateLabel = new JLabel("Enter Date as yyyy-MM-dd");
    this.getDate = new JTextField("");
    this.goBack = new JButton("Back");
    JScrollPane evaluationScroll = new JScrollPane(this.evaluationResults);
    evaluationScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


    this.setLayout(new GridLayout(4, 2, 1, 1));
    this.add(portfolioNameLabel);
    this.add(portfolioName);
    this.add(getDateLabel);
    this.add(getDate);
    this.add(getEvaluation);
    this.add(evaluationScroll);
    this.add(goBack);
    this.add(warningMessage);
  }

  @Override
  public void addFeatures(Features features) {
    this.goBack.addActionListener(evt -> features.backToHome());
    this.getEvaluation.addActionListener(evt -> features.getEvaluation(
            this.portfolioName.getText(), this.getDate.getText()));
  }

  /**
   * Set Warning for the class.
   *
   * @param result the warning string.
   */
  public void setWarningResult(String result) {
    this.warningMessage.setText(result);
  }

  /**
   * Set Evaluation for the class.
   *
   * @param printPortfolioEvaluation the resultant string.
   */
  public void setGetEvaluation(String printPortfolioEvaluation) {
    this.evaluationResults.setText(printPortfolioEvaluation);
  }
}
