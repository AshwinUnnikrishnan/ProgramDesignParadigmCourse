package controller;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


import model.FlexiblePortfolio;
import model.InflexiblePortfolio;
import model.Portfolio;
import model.Stock;
import model.StockMarketModelInterface;
import model.Investor;
import model.StrategyPortfolio;
import view.InvestorViewInterface;


/**
 * Controller class to help the stock market work, and links between the view and models of
 * stock marker.
 */
public class InvestorController implements ControllerInterface {

  InvestorViewInterface theView;

  final StockMarketModelInterface theStockMarketModel;

  final InputStream in;

  Investor currentUser;
  final Scanner scan;

  /**
   * Constructor for the controller where we pass InvestorView, StockMarketModel and a input stream
   * from where we read data from user.
   *
   * @param theView             Object where we want to show the results.
   * @param theStockMarketModel Model object of stockMarket
   * @param in                  Where to take inputs from
   */
  public InvestorController(InvestorViewInterface theView, StockMarketModelInterface
          theStockMarketModel, InputStream in) {
    this.theView = theView;
    this.theStockMarketModel = theStockMarketModel;
    this.in = in;
    this.scan = new Scanner(this.in);
  }

  /**
   * Method that takes of welcome screen and helping the user log in to the system.
   *
   * @throws IOException : If file write for a new user fails.
   */
  private void loginUser() throws IOException {

    this.theView.printWelcomeScreen();
    String commandInput;
    while ((commandInput = this.scanLine("[1-3]")) == null) {
      this.theView.getStringOutput("Valid Input Button");
      this.theView.printWelcomeScreen();
    }
    char command = commandInput.charAt(0);
    if (command == '3') {
      System.exit(0);
    }
    if (command == '2') {
      this.currentUser = this.createNewUser(true);
    } else {
      this.currentUser = this.createNewUser(false);
    }
  }

  /**
   * Method that gets userName from user and based on the argument passed works either to create a
   * new user or to load an existing user from database.
   *
   * @param createUser : boolean variable to set if we are creating new user or loading existing.
   * @return Investor object created with the user.
   * @throws IOException thrown if file write when creating new user fails.
   */
  private Investor createNewUser(boolean createUser) throws IOException {
    this.theView.printWelcomeNewUser();
    boolean userNameSuccess = false;
    String userName = new String();
    while (!userNameSuccess) {
      this.theView.getStringOutput("Username");
      userName = this.scanLine("[a-zA-Z]+");
      if (userName == null) {
        this.theView.getStringOutput("valid username, should contain only characters");
        continue;
      }
      if (createUser && this.theStockMarketModel.checkUserOrStock(userName, true)) {
        this.theView.getStringOutput("different userName, name already exists in the system");
        continue;
      }
      if (!createUser && !this.theStockMarketModel.checkUserOrStock(userName, true)) {
        this.theView.getStringOutput("username does not exist, enter correct userName");
        continue;
      }
      userNameSuccess = true;
    }
    if (createUser) {
      Investor temp = this.theStockMarketModel.addUserName(userName);
      this.theStockMarketModel.writeUpdatedUser(temp, true);
      return temp;
    }

    Investor user = this.theStockMarketModel.loadFromExisting(userName);
    this.theStockMarketModel.writeUpdatedUser(user, false);
    return user;
  }

  /**
   * Method that runs when the user is logged in, it keeps running till the user does not exit.
   * It handles creating new portfolio, checking composition and evaluating portfolio.
   *
   * @throws IOException : thrown when there is file missing when we write the updated details of
   *                     user.
   */
  private void giveUserAccessAfterLogin() throws IOException {
    boolean userActive = true;
    while (userActive) {
      this.theView.printAfterLoginScreen();
      String commandInput;
      while ((commandInput = this.scanLine("[1-8]")) == null) {
        this.theView.getStringOutput("Valid Input Button");
      }
      char command = commandInput.charAt(0);

      if (command == '1') {
        // get the portfolio name and contents and as part of a feature we do not allow a portfolio
        // to exist without any stocks in it
        Portfolio newPortfolio = this.getPortfolioInputs();
        if (newPortfolio == null) {
          this.theView.portfolioIssue();
        } else {
          this.currentUser = this.theStockMarketModel.addPortfolio(this.currentUser, newPortfolio);
          this.theStockMarketModel.writeUpdatedUser(this.currentUser, false);
        }

      } else if (command == '2') { // to check composition
        this.theView.getStringOutput("portfolio name to see composition");

        this.theView.printPortfolioComposition(this.theStockMarketModel.printPortfolioComposition(
                currentUser, this.getPortfolioNameToCheck(false), getDate()));
      } else if (command == '3') {
        this.theView.getStringOutput("portfolio name to see composition");
        this.theView.printPortfolioEvaluation(Float.toString(
                this.theStockMarketModel.getEvaluation(this.currentUser,
                        this.getPortfolioNameToCheck(false), this.getDate())));
      } else if (command == '4') {
        this.updatePortfolio();
        this.theStockMarketModel.writeUpdatedUser(this.currentUser, false);
      } else if (command == '5') {
        this.determineCostBasis();
      } else if (command == '6') {
        this.theView.getStringOutput("portfolio name to see performance");
        String portfolio = this.getPortfolioNameToCheck(false);
        this.theView.printPortfolioPerformance(
                this.theStockMarketModel.calculatePortfolioPerformance(this.currentUser,
                        portfolio, this.getDate(),
                        this.getDate()));
      } else if (command == '7') {
        this.dollarCost();
      } else if (command == '8') {
        userActive = false;
      }
    }
  }

  private void dollarCost() throws IOException {
    //get portfolioName
    String portfolioName = this.getPortfolioNameToCheck(true);
    Portfolio newPortfolio = new FlexiblePortfolio(portfolioName);
    // Get number of stocks
    String totalVarietyOfStocks;
    // get valid stock quantity
    this.theView.getStringOutput("Total Number of Various Stocks");

    while ((totalVarietyOfStocks = this.scanLine("[0-9]+")) == null) {
      this.theView.getStringOutput("Total Number of Various Stocks");
    }
    String totalAmount;
    // get valid stock quantity
    this.theView.getStringOutput("Total Amount to spend per month");
    while ((totalAmount = this.scanLine("(\\d+\\.\\d+)")) == null) {
      this.theView.getStringOutput("Total Amount to spend per month");
    }
    // Get the stock and weightage in percentage
    int stockCount = Integer.parseInt(totalVarietyOfStocks);

    ArrayList<String> stocksList = new ArrayList<>();
    ArrayList<String> stockWeight = new ArrayList<>();

    while (stockCount > 0) {
      //Get stock Name
      // Get weightage
      // Create stock add it to the
      stocksList.add(this.getStockName(newPortfolio));
      // for now assume that user enters percentage properly
      String weightStock;
      // get valid stock quantity
      this.theView.getStringOutput("Weight of the stock");

      while ((weightStock = this.scanLine("[0-9]+")) == null) {
        this.theView.getStringOutput("Weight of the stock");
      }
      stockWeight.add(weightStock);
      stockCount -= 1;
    }

    this.theView.getStringOutput("the start date for the investment strategy");
    String startDate = this.getDate();
    String endDatePresent;
    // get valid stock quantity
    this.theView.getStringOutput("0 to enter end date or 1 for continuous");

    while ((endDatePresent = this.scanLine("[0-1]")) == null) {
      this.theView.getStringOutput("0 to enter end date or 1 for continuous");
    }
    String endDate = "-1";
    if (endDatePresent.equals("0")) {
      endDate = this.getDate();
    }

    // for now doesn't handle endDate issues.

    String intervalOfInvestment;
    // get valid stock quantity
    this.theView.getStringOutput("investment interval");

    while ((intervalOfInvestment = this.scanLine("[0-9]+")) == null) {
      this.theView.getStringOutput("investment interval");
    }
    newPortfolio = new StrategyPortfolio(portfolioName, intervalOfInvestment,
            totalAmount, startDate, endDate);


    newPortfolio = this.theStockMarketModel.strategyInvestment(newPortfolio, stocksList,
            totalAmount, stockWeight,
            startDate, endDate, intervalOfInvestment, 0);
    // G

    this.currentUser = this.theStockMarketModel.addPortfolio(this.currentUser, newPortfolio);
    this.theStockMarketModel.writeUpdatedUser(this.currentUser, false);
  }

  /**
   * Method to determine cost basis (i.e. the total amount of money invested in a portfolio) by a
   * specific date. This would include all the purchases made in that portfolio till that date
   */
  private void determineCostBasis() {
    this.theView.getStringOutput("portfolio name to update");
    String portfolioName = this.getPortfolioNameToCheck(false);
    String dateToCheck = this.getDate();
    float costBasis = this.theStockMarketModel.evaluateCostBasis(this.currentUser, portfolioName,
            dateToCheck);
    this.theView.printCostBasis(costBasis, portfolioName, dateToCheck);
  }

  /**
   * Method called when we want to update a portfolio. It can be add stock or sell stocks.
   */
  private void updatePortfolio() {
    this.theView.getStringOutput("portfolio name to update");
    String portfolioName = this.getPortfolioNameToCheck(false);
    this.theView.printPortfolioComposition(this.theStockMarketModel.printPortfolioComposition(
            currentUser, portfolioName, LocalDate.now().toString()));

    if (!this.theStockMarketModel.checkPortfolioFlexible(this.currentUser, portfolioName)) {
      this.theView.printInflexiblePortfolio(portfolioName);
      return;
    }
    this.theView.printPortfolioUpdateMessage();
    String commandInput;
    while ((commandInput = this.scanLine("[1-2]")) == null) {
      this.theView.getStringOutput("Valid Input Button");
      this.theView.printPortfolioUpdateMessage();
    }
    Portfolio portfolio = ((Portfolio) this.currentUser.getPortfolio().get(portfolioName));
    if (commandInput.charAt(0) == '1') {
      getStockDetailsForPortfolio(portfolio);
    } else {
      getStockDetailsForDelete(portfolio);
    }
  }

  /**
   * Get stock details to sell from the account. Need to enter a valid stocks name date.
   *
   * @param nPortfolio portfolio from which we want to sell the stocks.
   * @return updated portfolio.
   */
  private Portfolio getStockDetailsForDelete(Portfolio nPortfolio) {
    boolean stockDeletionDone = false;
    while (!stockDeletionDone) {
      this.theView.printStockDeleteScreen();
      String commandInput;
      while ((commandInput = this.scanLine("[1-2]")) == null) {
        this.theView.getStringOutput("Valid Input Button");
        this.theView.printStockDeleteScreen();
      }
      char command = commandInput.charAt(0);
      if (command == '2') {
        stockDeletionDone = true;
        continue;
      }

      boolean stockNameSuccess = false;
      String stockName = null;
      Stock stockObj = null;
      // get valid stockName
      while (!stockNameSuccess) {
        this.theView.getStringOutput("Stock name");
        stockName = this.scanLine("[a-zA-Z]+");
        if (stockName == null) {
          this.theView.getStringOutput("Valid stockName, should contain only characters");
          continue;
        }
        stockObj = this.theStockMarketModel.checkStockInPortfolio(nPortfolio, stockName);
        if (stockObj == null) {
          this.theView.getStringOutput("Stock does not exist in portfolio, please give a "
                  + "different name");
          continue;
        }
        stockNameSuccess = true;
      }

      String quantity = null;
      // get valid stock quantity

      boolean quantitySuccess = false;

      while (!quantitySuccess) {
        this.theView.getStringOutput("Integer Quantity");
        quantity = this.scanLine("[0-9]+");
        if (quantity == null) {
          continue;
        } else if (stockObj.getQuantity() < Integer.parseInt(quantity)) {
          this.theView.getStringOutput("quantity within range 0 and " + stockObj.getQuantity());
          continue;
        }
        quantitySuccess = true;
      }
      String sellDate = this.getDate();
      while (!(stockObj.getDate()).isBefore(LocalDate.parse(sellDate))) {
        this.theView.getStringOutput("date after stock was bought");
        sellDate = this.getDate();
      }
      String broker;
      // get valid stock quantity
      this.theView.getStringOutput("Brokerage Fees");
      while ((broker = this.scanLine("(\\d+\\.\\d+)")) == null) {
        this.theView.getStringOutput("Brokerage Fees");
      }
      nPortfolio = this.theStockMarketModel.deleteStockFromPortfolio(nPortfolio, stockName,
              quantity, sellDate, Float.parseFloat(broker));
    }
    return nPortfolio;
  }

  /**
   * Method to get the date from user.
   *
   * @return the right date taken from user.
   */
  private String getDate() {
    boolean dateNameSuccess = false;
    String date = new String();
    this.theView.getStringOutput("date to evaluate portfolio, should be in format yyyy-MM-dd");
    while (!dateNameSuccess) {
      date = this.scanLine("([1-2][0-9]{3}-(0[1-9]|1[0-2])-([0-2][0-9]|[3][0-1]))");
      if (date == null) {
        this.theView.getStringOutput("date to evaluate portfolio, should be in format yyyy-MM-dd");
        continue;
      }
      LocalDate enteredDate = null;
      LocalDate currentDate = null;
      try {
        enteredDate = LocalDate.parse(date);
        currentDate = LocalDate.now().plusDays(1);
      } catch (DateTimeParseException e) {
        this.theView.getStringOutput("a valid date that falls in the month");
        continue;
      }
      if (!enteredDate.isBefore(currentDate)) {
        this.theView.getStringOutput("date that is not in future");
        continue;
      }
      dateNameSuccess = true;
    }
    return date;
  }

  /**
   * This method performs two functionalities based on the argument passed, if we pass true,
   * it gets a new portfolio name from user and if false it checks if the portfolio name entered
   * by user exists and if exists returns that name.
   *
   * @param createNew : to specify if the function is called while creating a new portfolio.
   * @return returns user entered portfolio name.
   */
  private String getPortfolioNameToCheck(boolean createNew) {
    boolean portfolioNameSuccess = false;
    String portfolioName = new String();
    while (!portfolioNameSuccess) {
      this.theView.getStringOutput("Portfolio name");
      portfolioName = this.scanLine("[a-zA-Z0-9]+");
      if (portfolioName == null) {
        this.theView.getStringOutput("Valid Portfolio name, should contain only characters");
        continue;
      }
      if (createNew ^ this.theStockMarketModel.portfolioExists(this.currentUser, portfolioName)) {
        return portfolioName;
      }
    }
    return null;
  }

  /**
   * Method to get details about the stocks from the user and then append it to portfolio.
   *
   * @param nPortfolio Portfolio to add the stocks to.
   * @return the updated portfolio
   */
  private Portfolio getStockDetailsForPortfolio(Portfolio nPortfolio) {
    boolean stockCreationDone = false;
    while (!stockCreationDone) {
      this.theView.printStockBuyScreen();
      String commandInput;
      while ((commandInput = this.scanLine("[1-2]")) == null) {
        this.theView.getStringOutput("Valid Input Button");
        this.theView.printStockBuyScreen();
      }

      char command = commandInput.charAt(0);
      if (command == '2') {
        stockCreationDone = true;
        continue;
      }

      String stockName = this.getStockName(nPortfolio);

      String quantity;
      // get valid stock quantity
      while ((quantity = this.scanLine("[0-9]+")) == null) {
        this.theView.getStringOutput("Integer Quantity");
      }

      String broker;
      // get valid stock quantity
      this.theView.getStringOutput("Brokerage Fees");
      while ((broker = this.scanLine("(\\d+\\.\\d+)")) == null) {
        this.theView.getStringOutput("Brokerage Fees");
      }
      // add the new stock to the portfolio by fetching the current price from the api
      nPortfolio = this.theStockMarketModel.addStockToPortfolio(nPortfolio, stockName, quantity,
              this.getDate(), true, Float.parseFloat(broker));
    }
    return nPortfolio;
  }

  private String getStockName(Portfolio nPortfolio) {
    boolean stockNameSuccess = false;
    String stockName = null;

    while (!stockNameSuccess) {
      this.theView.getStringOutput("Stock name");
      stockName = this.scanLine("[a-zA-Z]+");
      if (stockName == null) {
        this.theView.getStringOutput("Valid stockName, should contain only characters");
        continue;
      }
      if (!this.theStockMarketModel.checkUserOrStock(stockName, false)) {
        this.theView.getStringOutput("Stock does not exists, please give a different name");
        continue;
      }
      Stock stockObj = this.theStockMarketModel.checkStockInPortfolio(nPortfolio, stockName);
      if (stockObj != null) {
        this.theView.getStringOutput("Stock already exists in portfolio, give a different name");
        continue;
      }
      stockNameSuccess = true;
    }
    return stockName;
  }

  /**
   * First takes portfolio name and then the stocks contained in the portfolio.
   * Takes stockName and quantity from user.
   *
   * @return returns a portfolio object.
   */
  private Portfolio getPortfolioInputs() {
    String portfolioName = this.getPortfolioNameToCheck(true);

    this.theView.printPortfolioType();
    String flexibleCommand;
    while ((flexibleCommand = this.scanLine("[1-2]")) == null) {
      this.theView.getStringOutput("Valid Input Button");
      this.theView.printPortfolioType();
    }

    Portfolio newPortfolio;
    if (flexibleCommand.equals("1")) {
      newPortfolio = new InflexiblePortfolio(portfolioName);
    } else {
      newPortfolio = new FlexiblePortfolio(portfolioName);
    }

    newPortfolio = getStockDetailsForPortfolio(newPortfolio);
    if (newPortfolio.getStockList().size() == 0) {
      return null;
    }
    return newPortfolio;
  }

  /**
   * Method to read a line from user, based on the pattern.
   *
   * @param pattern : Patter of input.
   * @return : Returns read input if it matches with pattern, else returns null.
   */
  private String scanLine(String pattern) {
    String input = this.scan.nextLine();
    return (input.matches(pattern)) ? input : null;
  }

  /**
   * Method that runs from the MVC, two stages one to get user details old or new
   * Second Stage to work once logged in.
   *
   * @throws IOException : for input output errors.
   */
  public void runMarketInterface() throws IOException {
    loginUser();
    this.theView.printSuccessLogin(this.currentUser.getName());
    giveUserAccessAfterLogin();
  }

}
