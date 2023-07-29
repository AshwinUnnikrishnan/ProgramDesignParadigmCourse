package view.panel;

import java.awt.*;

import javax.swing.*;

import controller.Features;
import model.Portfolio;

/**
 * Class to get Edit Portfolio using GUI.
 */

public class PortfolioEditPanel extends JPanel implements PanelInterface {
  private final JTextField portfolioName;
  private final JButton portfolioLoad;
  private final JTextArea compositionResults;
  private final JLabel stockNameLabel;
  private final JTextField stockName;
  private final JLabel stockQuantityLabel;
  private final JTextField stockQuantity;
  private final JLabel stockBrokerFeeLabel;
  private final JTextField stockBrokerFee;
  private final JLabel stockBuyingDateLabel;
  private final JTextField stockBuyingDate;
  private final JButton addStock;
  private final JButton deleteStock;
  private final JButton goBack;
  private final JLabel warningMessages;
  private Portfolio nPortfolio = null;

  /**
   * Constructor to initialise the JButton Values and add layouts to the JPanel.
   */
  public PortfolioEditPanel() {
    JLabel portfolioNameLabel = new JLabel("Portfolio Name");
    this.portfolioName = new JTextField("");
    this.portfolioLoad = new JButton("Load Portfolio");
    this.compositionResults = new JTextArea("");
    JScrollPane compositionScroll = new JScrollPane(this.compositionResults);
    compositionScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    this.stockNameLabel = new JLabel("Stock Name");
    this.stockName = new JTextField("");
    this.stockQuantityLabel = new JLabel("Stock Quantity");
    this.stockQuantity = new JTextField("");
    this.stockBrokerFeeLabel = new JLabel("Broker Fee");
    this.stockBrokerFee = new JTextField("");
    this.stockBuyingDateLabel = new JLabel("Buying Date");
    this.stockBuyingDate = new JTextField("");
    this.addStock = new JButton("Add Stock");
    this.deleteStock = new JButton("Delete Stock");
    this.goBack = new JButton("Back");
    this.warningMessages = new JLabel("");
    this.setLayout(new GridLayout(10, 2, 1, 1));

    this.add(portfolioNameLabel);
    this.add(this.portfolioName);
    this.add(new JLabel());
    this.add(this.portfolioLoad);
    this.add(new JLabel());
    this.add(compositionScroll);
    this.add(this.stockNameLabel);
    this.add(this.stockName);
    this.add(this.stockQuantityLabel);
    this.add(this.stockQuantity);
    this.add(this.stockBrokerFeeLabel);
    this.add(this.stockBrokerFee);
    this.add(this.stockBuyingDateLabel);
    this.add(this.stockBuyingDate);
    this.add(this.addStock);
    this.add(this.deleteStock);
    this.add(this.goBack);
    this.add(this.warningMessages);
    this.setStockEdit(false);
  }

  /**
   * Method to set stocks and display name, quantity, broker fee and buying date.
   *
   * @param flag flag to check value.
   */
  private void setStockEdit(boolean flag) {
    this.stockNameLabel.setVisible(flag);
    this.stockName.setVisible(flag);
    this.stockQuantityLabel.setVisible(flag);
    this.stockQuantity.setVisible(flag);
    this.stockBrokerFeeLabel.setVisible(flag);
    this.stockBrokerFee.setVisible(flag);
    this.stockBuyingDateLabel.setVisible(flag);
    this.stockBuyingDate.setVisible(flag);
  }

  @Override
  public void addFeatures(Features features) {
    this.goBack.addActionListener(evt -> features.backToHome());
    this.portfolioLoad.addActionListener(evt -> features.loadPortfolio(
            this.portfolioName.getText()));
    this.addStock.addActionListener(evt -> features.addStock(
            nPortfolio, this.stockName.getText(), this.stockQuantity.getText(),
            this.stockBrokerFee.getText(), this.stockBuyingDate.getText(), false));
    this.deleteStock.addActionListener(evt -> features.deleteStock(
            nPortfolio, this.stockName.getText(), this.stockQuantity.getText(),
            this.stockBrokerFee.getText(), this.stockBuyingDate.getText(), false));
  }

  /**
   * Method to set Portfolio Composition.
   *
   * @param printPortfolioComposition string displaying portfolio composition.
   */
  public void setCompositionResults(String printPortfolioComposition) {
    this.compositionResults.setText(printPortfolioComposition);
    this.setStockEdit(true);
  }

  /**
   * Method to set warning.
   *
   * @param warningMessage resultant object.
   */
  public void setWarningMessage(String warningMessage) {
    this.warningMessages.setText(warningMessage);
  }

  /**
   * Method to set Portfolio.
   *
   * @param nPortfolio string display portfolio composition.
   */
  public void setPortfolio(Portfolio nPortfolio) {
    this.stockName.setText("");
    this.stockQuantity.setText("");
    this.stockBrokerFee.setText("");
    this.stockBuyingDate.setText("");
    this.nPortfolio = nPortfolio;
  }
}
