package view.panel;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;


import controller.Features;
import model.Portfolio;

/**
 * Class to create Portfolio using GUI.
 */
public class CreatePortfolio extends JPanel implements PanelInterface {
  private final JButton portfolioCreate;
  private final JTextField portfolioName;
  private final JTextField portfolioType;
  private final JLabel stockNameLabel;
  private final JTextField stockName;
  private final JLabel stockQuantityLabel;
  private final JTextField stockQuantity;
  private final JLabel stockBrokerFeeLabel;
  private final JTextField stockBrokerFee;
  private final JLabel stockBuyingDateLabel;
  private final JTextField stockBuyingDate;
  private final JButton addStock;
  private final JButton goBack;
  private final JLabel warningLabel;

  private Portfolio nPortfolio = null;

  /**
   * Constructor to initialise the JButton Values.
   */
  public CreatePortfolio() {
    JLabel portfolioNameLabel = new JLabel("Enter Portfolio Name");
    this.portfolioName = new JTextField("");
    JLabel portfolioTypeLabel = new JLabel("Enter 0 for Flexible and 1 for Inflexible portfolio");
    this.portfolioType = new JTextField("");
    this.portfolioCreate = new JButton("Create");
    this.goBack = new JButton("Back");
    this.stockNameLabel = new JLabel("Enter Stock Name");
    this.stockName = new JTextField("");
    this.stockQuantityLabel = new JLabel("Enter Stock Quantity(Int)");
    this.stockQuantity = new JTextField("");
    this.stockBrokerFeeLabel = new JLabel("Enter Broker Fee");
    this.stockBrokerFee = new JTextField("");
    this.stockBuyingDateLabel = new JLabel("Enter Buying Date as yyyy-MM-dd");
    this.stockBuyingDate = new JTextField("");
    this.addStock = new JButton("Add Stock");
    this.warningLabel = new JLabel("");

    this.setLayout(new GridLayout(9, 2, 1, 1));

    this.add(portfolioNameLabel);
    this.add(this.portfolioName);
    this.add(portfolioTypeLabel);
    this.add(this.portfolioType);
    this.add(new JLabel());
    this.add(this.portfolioCreate);
    this.add(this.stockNameLabel);
    this.add(this.stockName);
    this.add(this.stockQuantityLabel);
    this.add(this.stockQuantity);
    this.add(this.stockBrokerFeeLabel);
    this.add(this.stockBrokerFee);
    this.add(this.stockBuyingDateLabel);
    this.add(this.stockBuyingDate);
    this.add(this.addStock);
    this.add(this.goBack);
    this.add(this.warningLabel);
    this.setStockAdd(false);
  }


  @Override
  public void addFeatures(Features features) {
    this.goBack.addActionListener(evt -> features.saveAndBack(nPortfolio));
    this.portfolioCreate.addActionListener(evt -> features.addPortfolio(
            this.portfolioName.getText(), this.portfolioType.getText()));
    this.addStock.addActionListener(evt -> features.addStock(nPortfolio,
            this.stockName.getText(),
            this.stockQuantity.getText(), this.stockBrokerFee.getText(),
            this.stockBuyingDate.getText(), true));
  }

  /**
   * Method to set StockADD values.
   *
   * @param flag flag to check true or false.
   */
  private void setStockAdd(boolean flag) {
    this.stockNameLabel.setVisible(flag);
    this.stockName.setVisible(flag);
    this.stockQuantityLabel.setVisible(flag);
    this.stockQuantity.setVisible(flag);
    this.stockBrokerFeeLabel.setVisible(flag);
    this.stockBrokerFee.setVisible(flag);
    this.stockBuyingDateLabel.setVisible(flag);
    this.stockBuyingDate.setVisible(flag);
  }

  /**
   * Method to set Portfolio.
   *
   * @param newPortfolio new portfolio.
   * @param createdStock created stock.
   */
  public void setPortfolio(Portfolio newPortfolio, boolean createdStock) {
    if (!createdStock) {
      this.setStockAdd(true);
    } else {
      this.stockName.setText("");
      this.stockQuantity.setText("");
      this.stockBrokerFee.setText("");
      this.stockBuyingDate.setText("");
    }
    this.nPortfolio = newPortfolio;
  }

  /**
   * Method to set warning.
   *
   * @param result resultant object.
   */
  public void setWarning(String result) {
    this.warningLabel.setText(result);
  }
}
