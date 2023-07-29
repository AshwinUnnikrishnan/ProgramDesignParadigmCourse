package view.panel;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import controller.Features;
import model.Portfolio;

/**
 * Class to display Dollar cost login panel.
 */
public class DollarCostLogin extends JPanel implements PanelInterface {
  private final JTextField portfolioName;
  private final JTextField totalNumberOfStocks;
  private final JTextField amount;
  private final JTextField startDate;
  private final JTextField endDate;
  private final JTextField continuous;
  private final JTextField intestmentRange;
  private final JLabel stockNameLabel;
  private final JTextField stockName;
  private final JLabel weightLabel;
  private final JTextField weight;
  private final JButton addStock;
  private final JLabel stockWeightRemLabel;
  private final JButton createPortfolio;
  private final JButton goBack;
  private final JLabel warningLabel;
  private final JButton startAddidngStock;
  private Portfolio nPortfolio;

  private final ArrayList<String> weightOfStocks;
  private final ArrayList<String> stockList;

  private int remainingWeightage;

  /**
   * Constructor to initialise the JButton Values.
   */
  public DollarCostLogin() {
    remainingWeightage = 100;
    weightOfStocks = new ArrayList<>();
    stockList = new ArrayList<>();
    nPortfolio = null;
    JLabel portfolioNameLabel = new JLabel("Portfolio Name");
    this.portfolioName = new JTextField("");
    JLabel totalNumberOfStocksLabel = new JLabel("Total Variety of stocks");
    this.totalNumberOfStocks = new JTextField("");
    JLabel amountLabel = new JLabel("Enter Amount to be invested recurrently");
    this.amount = new JTextField("");
    JLabel startDateLabel = new JLabel("Enter Start Date");
    this.startDate = new JTextField("");
    JLabel endDateLabel = new JLabel("Enter End Date");
    this.endDate = new JTextField("");
    JLabel continuousLabel = new JLabel("Enter 0 for End Date and 1 for no end");
    this.continuous = new JTextField("");
    JLabel investmentRangeLabel = new JLabel("Enter Interval of range");
    this.intestmentRange = new JTextField("");
    this.stockNameLabel = new JLabel("Enter Stock name");
    this.stockName = new JTextField("");
    this.weightLabel = new JLabel("Enter Weight");
    this.weight = new JTextField("");
    this.addStock = new JButton("Add Stock Remaining : " + this.totalNumberOfStocks.getText());
    this.stockWeightRemLabel = new JLabel("Remaining Weight " + remainingWeightage);
    this.createPortfolio = new JButton("Create Strategy Portfolio");
    this.goBack = new JButton("Back");
    this.warningLabel = new JLabel("");
    this.startAddidngStock = new JButton("Start Adding Stock");
    this.setLayout(new GridLayout(13, 2, 1, 1));
    this.add(portfolioNameLabel);
    this.add(this.portfolioName);
    this.add(totalNumberOfStocksLabel);
    this.add(this.totalNumberOfStocks);
    this.add(amountLabel);
    this.add(this.amount);
    this.add(startDateLabel);
    this.add(this.startDate);
    this.add(endDateLabel);
    this.add(this.endDate);
    this.add(continuousLabel);
    this.add(this.continuous);
    this.add(endDateLabel);
    this.add(this.endDate);
    this.add(investmentRangeLabel);
    this.add(this.intestmentRange);
    this.add(new JLabel());
    this.add(this.startAddidngStock);
    this.add(this.stockNameLabel);
    this.add(this.stockName);
    this.add(this.weightLabel);
    this.add(this.weight);
    this.add(this.stockWeightRemLabel);
    this.add(this.addStock);
    this.add(this.goBack);
    this.add(this.createPortfolio);
    this.add(this.warningLabel);
    this.setStockAdd(false);
    this.createPortfolio.setVisible(false);
  }

  /**
   * Method to set stockADD.
   *
   * @param flag resultant flag.
   */
  private void setStockAdd(boolean flag) {
    this.stockNameLabel.setVisible(flag);
    this.stockName.setVisible(flag);
    this.stockWeightRemLabel.setVisible(flag);
    this.weight.setVisible(flag);
    this.weightLabel.setVisible(flag);
    this.addStock.setVisible(flag);

  }


  @Override
  public void addFeatures(Features features) {
    this.goBack.addActionListener(evt -> features.backToHome());
    this.startAddidngStock.addActionListener(evt -> features.startAddingStock(
            this.portfolioName.getText(), this.totalNumberOfStocks.getText(), this.amount.getText(),
            this.startDate.getText(),
            this.continuous.getText(), this.endDate.getText(), this.intestmentRange.getText()));
    this.addStock.addActionListener(evt -> features.addWeightStockToPortfolio(
            this.stockName.getText(), this.weight.getText()));
    this.createPortfolio.addActionListener(evt -> features.createStrategyPortfolio(this.nPortfolio,
            this.stockList, this.amount.getText(), this.weightOfStocks, this.startDate.getText(),
            this.endDate.getText(),
            intestmentRange.getText(), false));
  }

  /**
   * Method to make stocks Visible.
   */
  public void getStocksVisible() {
    this.setStockAdd(true);
  }

  /**
   * Method to set warning message.
   *
   * @param result The resultant string.
   */
  public void setWarningMessage(String result) {
    this.warningLabel.setText(result);
  }

  /**
   * Method to set Portfolio.
   *
   * @param newPortfolio new Portfolio.
   */
  public void setPortfolio(Portfolio newPortfolio) {
    this.nPortfolio = newPortfolio;
    this.getStocksVisible();
    this.addStock.setText("Add Stock Remaining : " + this.totalNumberOfStocks.getText());
  }

  /**
   * Method to add stocks to the list.
   *
   * @param stockName   name of the stock.
   * @param stockWeight weight of the stock.
   */
  public void addStockToList(String stockName, String stockWeight) {
    this.stockList.add(stockName);
    this.weightOfStocks.add(stockWeight);
    this.remainingWeightage = this.remainingWeightage - Integer.parseInt(stockWeight);
    this.stockWeightRemLabel.setText(remainingWeightage + "");
    int temp = Integer.parseInt(this.totalNumberOfStocks.getText()) - 1;
    this.totalNumberOfStocks.setText(""
            + (Integer.parseInt(this.totalNumberOfStocks.getText()) - 1));
    this.addStock.setText("Add Stock Remaining : " + (temp));
    if (temp == 0) {
      this.createPortfolio.setVisible(true);
      this.addStock.setVisible(false);
    }
  }
}
