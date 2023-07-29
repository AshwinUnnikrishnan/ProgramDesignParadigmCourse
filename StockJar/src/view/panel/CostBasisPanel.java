package view.panel;

import java.awt.*;

import javax.swing.*;

import controller.Features;

/**
 * Class to represent the Cost Basis Panel.
 */
public class CostBasisPanel extends JPanel implements PanelInterface {

  private final JTextField portfolioName;
  private final JLabel warningMessage;
  private final JButton goBack;
  private final JTextField getDate;
  private final JButton getCostBasis;
  private final JTextArea costBasisResults;

  /**
   * Constructor to initialise the JButton Values.
   */
  public CostBasisPanel() {
    JLabel portfolioNameLabel = new JLabel("Enter Portfolio Name");
    this.portfolioName = new JTextField("");
    this.getCostBasis = new JButton("Get Cost Basis Evaluation");
    this.costBasisResults = new JTextArea("");
    JLabel getDateLabel = new JLabel("Enter Date as yyyy-MM-dd");
    this.getDate = new JTextField("");
    this.goBack = new JButton("Back");
    JScrollPane costBasisScroll = new JScrollPane(this.costBasisResults);
    costBasisScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    this.warningMessage = new JLabel("");


    this.setLayout(new GridLayout(4, 2, 1, 1));
    this.add(portfolioNameLabel);
    this.add(portfolioName);
    this.add(getDateLabel);
    this.add(getDate);
    this.add(getCostBasis);
    this.add(costBasisResults);
    this.add(goBack);
    this.add(warningMessage);
  }

  @Override
  public void addFeatures(Features features) {
    this.goBack.addActionListener(evt -> features.backToHome());
    this.getCostBasis.addActionListener(evt -> features.getCostBasis(
            this.portfolioName.getText(), this.getDate.getText()));
  }

  /**
   * Method to set Warning.
   *
   * @param result The resultant object.
   */
  public void setWarningResult(String result) {
    this.warningMessage.setText(result);
  }

  /**
   * Method to set Cost Basis.
   *
   * @param printCostBasis resultant object.
   */
  public void setCostBasis(String printCostBasis) {

    this.costBasisResults.setText(printCostBasis);
  }
}
