package view.panel;

import java.awt.*;

import javax.swing.*;

import controller.Features;

/**
 * Class to get Portfolio Composition using GUI.
 */

public class PortfolioComposition extends JPanel implements PanelInterface {
  private final JButton getComposition;
  private final JTextField portfolioName;
  private final JLabel warningMessage;
  private final JTextArea compositionResults;
  private final JLabel compositionDateLabel;
  private final JTextField compositionDate;
  private final JButton goBack;

  /**
   * Constructor to initialise the JButton Values and add layouts to the JPanel.
   */
  public PortfolioComposition() {
    JLabel portfolioNameLabel = new JLabel("Enter Portfolio Name");
    this.portfolioName = new JTextField("");
    this.compositionDateLabel = new JLabel("Enter Date");
    this.compositionDate = new JTextField("");
    this.getComposition = new JButton("Get Composition");
    this.warningMessage = new JLabel("");
    this.compositionResults = new JTextArea("");
    this.goBack = new JButton("Back");
    JScrollPane compositionScroll = new JScrollPane(this.compositionResults);
    compositionScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


    this.setLayout(new GridLayout(8, 1, 1, 1));
    this.add(portfolioNameLabel);
    this.add(this.portfolioName);
    this.add(this.compositionDateLabel);
    this.add(this.compositionDate);
    this.add(getComposition);
    this.add(compositionScroll);
    this.add(goBack);
    this.add(warningMessage);
  }

  @Override
  public void addFeatures(Features features) {
    this.goBack.addActionListener(evt -> features.backToHome());
    this.getComposition.addActionListener(evt -> features.getComposition(
            this.portfolioName.getText(), this.compositionDate.getText()));
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
   * Method to set Portfolio Composition.
   *
   * @param printPortfolioComposition string displaying portfolio composition.
   */
  public void setCompositionResults(String printPortfolioComposition) {
    this.compositionResults.setText(printPortfolioComposition);
  }
}