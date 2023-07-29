package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.FlexiblePortfolio;
import model.InflexiblePortfolio;
import model.Investor;
import model.Portfolio;
import model.Stock;
import model.StockMarketModelInterface;
import model.StrategyPortfolio;
import view.IView;
import view.panel.BarChart;

/**
 * A class that represents the controller for the Graphical User Interface.
 * This class implements the Features Interface.
 */
public class ControllerGUI implements Features {

  private StockMarketModelInterface theStockMarketModel;
  private IView view;
  BarChart brchart = new BarChart();

  private Investor currentUser;

  /**
   * Constructor for the controller GUI which initialised the Model and View Objects.
   *
   * @param m Object of Stock Market Model.
   * @param v Object of the View Class.
   */
  public ControllerGUI(StockMarketModelInterface m, IView v) {
    this.theStockMarketModel = m;
    this.view = v;
  }

  /**
   * Method to make the features of the GUI visible.
   */
  public void setView() {
    //provide view with all the callbacks
    this.view.addFeatures(this);
    this.view.makeVisible();
  }

  @Override
  public void login(String userName, boolean createUser) {
    this.view.setLoginUserNameError(this.loginUser(createUser, userName));
  }

  /**
   * Method to check if the string contains Alphabets.
   *
   * @param s String to be verified.
   * @return boolean value true if it contains alphabets.
   */
  private static boolean isAlphabet(String s) {
    return s != null && s.matches("^[a-zA-Z]+$");

  }

  /**
   * Method to check if the string contains Integers.
   *
   * @param s String to be verified.
   * @return boolean value true if it contains alphabets.
   */
  private static boolean isInteger(String s) {
    return s != null && s.matches("^[0-9]+$");
  }

  /**
   * Method to check if the string contacts float values or not.
   *
   * @param s String to be verified.
   * @return boolean value true if it contains values.
   */
  private static boolean isFloat(String s) {

    return s != null && s.matches("^([0-9]+\\.[0-9]+)$");
  }

  /**
   * Method to check if the string is of date format.
   *
   * @param s String to be verified.
   * @return Boolean value.
   */
  private static boolean isDateFormat(String s) {
    return s != null && s.matches("^([1-2][0-9]{3}-(0[1-9]|1[0-2])-([0-2][0-9]|[3][0-1]))$");
  }

  /**
   * Method to check the Login details.
   *
   * @param createUser To check if it is a new user.
   * @param userName   String username.
   * @return resultant String.
   */
  private String loginUser(boolean createUser, String userName) {
    if (!isAlphabet(userName)) {
      return "userName cannot be empty and contain just alphabets";
    }

    if (createUser && this.theStockMarketModel.checkUserOrStock(userName, true)) {
      return "Please enter a different userName, name already exists";
    }
    if (!createUser && !this.theStockMarketModel.checkUserOrStock(userName, true)) {
      return "UserName does not exist, enter correct username";
    }
    try {
      if (createUser) {
        Investor temp = this.theStockMarketModel.addUserName(userName);
        this.theStockMarketModel.writeUpdatedUser(temp, true);
        this.currentUser = temp;
      }

      Investor user = this.theStockMarketModel.loadFromExisting(userName);
      this.theStockMarketModel.writeUpdatedUser(user, false);
      this.currentUser = user;
      return "Login Successful";
    } catch (IOException e) {
      return e.toString();
    }
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void showUserLoginButtons(boolean createUser) {
    this.view.makeLoginVisible(createUser);
  }

  @Override
  public void createPortfolioScreen() {
    this.view.changeToCreatePortfolio();
  }

  @Override
  public void addPortfolio(String portfolioName, String portfolioType) {
    String result = this.getPortfolioNameToCheck(portfolioName, true);
    if (result.equals("portfolio successful")) {
      Portfolio newPortfolio;
      if (portfolioType.equals("1")) {
        newPortfolio = new InflexiblePortfolio(portfolioName);
      } else if (portfolioType.equals("0")) {
        newPortfolio = new FlexiblePortfolio(portfolioName);
      } else {
        this.view.setCreatePortfolioError("Please select a valid portfolio type");
        return;
      }
      this.view.addEmptyPortfolio(newPortfolio);

    }
    this.view.setCreatePortfolioError(result);
  }

  @Override
  public void saveAndBack(Portfolio nPortfolio) {
    if (nPortfolio == null) {
      this.view.setToAfterLogin();
    }
    if (nPortfolio.getStockList().size() == 0) {
      this.view.setCreatePortfolioError("No Stocks so not creating");
    } else {
      this.currentUser = this.theStockMarketModel.addPortfolio(this.currentUser, nPortfolio);
      try {
        this.theStockMarketModel.writeUpdatedUser(this.currentUser, false);
      } catch (IOException e) {
        this.view.setCreatePortfolioError("Write Issue");
      }
    }
    this.view.setToAfterLogin();

  }

  @Override
  public void addStock(Portfolio nPortfolio, String stockName, String stockQuantity,
                       String brokerFee, String buyingDate, boolean create) {
    String result = this.getStockNameToCheck(nPortfolio, stockName, true);
    if (result.equals("stock successful")) {
      if (!isInteger(stockQuantity)) {
        String error = "Please set stock Quantity as integer";
        if (create) {
          this.view.setCreatePortfolioError(error);
        } else {
          this.view.setEditPortfolioWarning(error);
        }
        return;
      }
      if (!isFloat(brokerFee)) {
        String error = "broker Fee should be float";
        if (create) {
          this.view.setCreatePortfolioError(error);
        } else {
          this.view.setEditPortfolioWarning(error);
        }
        return;
      }
      if (!isDateFormat(buyingDate)) {
        String error = "please format the buying date properly";
        if (create) {
          this.view.setCreatePortfolioError(error);
        } else {
          this.view.setEditPortfolioWarning(error);
        }
        return;
      }
      if (LocalDate.parse(buyingDate).isAfter(LocalDate.now())) {
        String error = "cannot accept future buying dates";
        if (create) {
          this.view.setCreatePortfolioError(error);
        } else {
          this.view.setEditPortfolioWarning(error);
        }
        return;
      }
      nPortfolio = this.theStockMarketModel.addStockToPortfolio(nPortfolio,
              stockName, stockQuantity,
              buyingDate, true, Float.parseFloat(brokerFee));
      try {
        this.theStockMarketModel.writeUpdatedUser(this.currentUser, false);
      } catch (IOException e) {
        //
      }

      if (create) {
        this.view.addStockPortfolio(nPortfolio, true);
      } else {
        this.view.addStockPortfolio(nPortfolio, false);
      }
    } else {
      if (create) {
        this.view.setCreatePortfolioError(result);
      } else {
        this.view.setEditPortfolioWarning(result);
      }
    }

  }

  @Override
  public void getCompositionScreen() {
    this.view.setToPortfolioComposition();
  }

  @Override
  public void backToHome() {
    this.view.setToAfterLogin();
  }

  @Override
  public void getComposition(String portfolioName, String date) {
    String result = this.getPortfolioNameToCheck(portfolioName, false);
    if (!this.isDateFormat(date)) {
      this.view.setCompositionWarning("Please enter proper date format");
      return;
    }
    if (result.equals("portfolio successful")) {
      this.view.printPortfolioComposition(this.theStockMarketModel.printPortfolioComposition(
              currentUser, portfolioName, date));//TODO
    } else {
      this.view.setCompositionWarning(result);
    }
  }

  @Override
  public void getEditPortfolioScreen() {
    this.view.setEditPortfolioScreen();
  }

  @Override
  public void loadPortfolio(String portfolioName) {
    String result = this.getPortfolioNameToCheck(portfolioName, false);
    if (result.equals("portfolio successful")) {
      if (!this.theStockMarketModel.checkPortfolioFlexible(this.currentUser, portfolioName)) {
        this.view.setEditPortfolioWarning("This is inflexible portfolio cannot edit it");
        return;
      }
      this.view.showPortfolioComposition(this.theStockMarketModel.printPortfolioComposition(
              this.currentUser, portfolioName, LocalDate.now().toString()));
      this.view.setPortfolioEditPanel(((Portfolio)
              this.currentUser.getPortfolio().get(portfolioName)));
    } else {
      this.view.setCompositionWarning(result);
    }
  }

  @Override
  public void deleteStock(Portfolio nPortfolio, String stockName, String quantity, String brokerFee,
                          String sellingDate, boolean b) {
    String result = this.getStockNameToCheck(nPortfolio, stockName, false);
    if (result.equals("stock successful")) {
      if (!isInteger(quantity)) {
        this.view.setEditPortfolioWarning("Please enter a valid quantity in integer");
        return;
      }
      Stock stockObj = this.theStockMarketModel.checkStockInPortfolio(nPortfolio, stockName);
      if (stockObj.getQuantity() < Integer.parseInt(quantity)) {
        this.view.setEditPortfolioWarning("Please select the quantity less than "
                + stockObj.getQuantity());
        return;
      }
      if (LocalDate.parse(sellingDate).isAfter(LocalDate.now())) {
        this.view.setEditPortfolioWarning("Please enter a date not in future");
        return;
      }
      if (!(stockObj.getDate()).isBefore(LocalDate.parse(sellingDate))) {
        this.view.setEditPortfolioWarning("Please enter a date after buying date");
        return;
      }
      if (!isFloat(brokerFee)) {
        this.view.setEditPortfolioWarning("Please enter a float broker Fee");
        return;
      }
      nPortfolio = this.theStockMarketModel.deleteStockFromPortfolio(nPortfolio, stockName,
              quantity, sellingDate, Float.parseFloat(brokerFee));
      try {
        this.theStockMarketModel.writeUpdatedUser(this.currentUser, false);
      } catch (IOException e) {
        //
      }
      this.view.setPortfolioEditPanel(nPortfolio);
    } else {
      this.view.setEditPortfolioWarning(result);
    }
  }

  @Override
  public void getEvaluation(String portfolioName, String date) {
    String result = this.getPortfolioNameToCheck(portfolioName, false);
    if (result.equals("portfolio successful")) {
      if (LocalDate.parse(date).isAfter(LocalDate.now())) {
        this.view.setEvaluationWarning("Cannot accept future buying dates");
        return;
      }
      this.view.printPortfolioEvaluation(String.valueOf(this.theStockMarketModel.getEvaluation(
              currentUser, portfolioName, date)));
    } else {
      this.view.setEvaluationWarning(result);
    }
  }

  @Override
  public void getEvaluationScreen() {
    this.view.setToPortfolioEvaluation();
  }

  @Override
  public void getCostBasis(String portfolioName, String date) {
    String result = this.getPortfolioNameToCheck(portfolioName, false);
    if (result.equals("portfolio successful")) {
      if (LocalDate.parse(date).isAfter(LocalDate.now())) {
        this.view.setCostBasisWarning("Cannot accept future buying dates");
        return;
      }
      this.view.printCostBasis(String.valueOf(this.theStockMarketModel.evaluateCostBasis(
              currentUser, portfolioName, date)));
    } else {
      this.view.setCostBasisWarning(result);
    }
  }

  @Override
  public void getCostBasisScreen() {
    this.view.setCostBasisPanel();
  }

  @Override
  public void getDollarCostScreen() {
    this.view.setDollarCostScreen();
  }

  @Override
  public void startAddingStock(String portfolioName, String totalStocks, String amount,
                               String startDate,
                               String continuous, String endDate, String range) {
    String result = this.getPortfolioNameToCheck(portfolioName, true);
    if (result.equals("portfolio successful")) {
      Portfolio newPortfolio = new FlexiblePortfolio(portfolioName);
      if (!this.isInteger(totalStocks)) {
        this.view.setDollarStrategyWarning("Please enter integer for total stocks");
        return;
      }
      if (!this.isFloat(amount)) {
        this.view.setDollarStrategyWarning("Please enter a floating amount");
        return;
      }
      if (!this.isInteger(range)) {
        this.view.setDollarStrategyWarning("Please enter range in integer");
        return;
      }
      if (!this.isDateFormat(startDate)) {
        this.view.setDollarStrategyWarning("Please enter start date in proper format");
        return;
      }
      if (!continuous.equals("0") && !continuous.equals("1")) {
        this.view.setDollarStrategyWarning("Please select the end date provide option");
        return;
      }
      if (continuous.equals("0") && !this.isDateFormat(endDate)) {
        this.view.setDollarStrategyWarning("Please enter a end date in proper format");
        return;
      }
      if (continuous.equals("1")) {
        endDate = "-1";
      }
      newPortfolio = new StrategyPortfolio(portfolioName, range, amount, startDate, endDate);
      this.view.setPortfolioInStrategy(newPortfolio);

    }
    this.view.setDollarStrategyWarning(result);
  }

  @Override
  public void addWeightStockToPortfolio(String stockName, String stockWeight) {
    this.view.addStockToList(stockName, stockWeight);
  }

  @Override
  public void createStrategyPortfolio(Portfolio nPortfolio, ArrayList<String> stockList,
                                      String amount, ArrayList<String> weightOfStocks,
                                      String startDate, String endDate, String range, boolean i) {
    nPortfolio = this.theStockMarketModel.strategyInvestment(nPortfolio, stockList, amount,
            weightOfStocks,
            startDate, endDate, range, 0);
    // G

    this.currentUser = this.theStockMarketModel.addPortfolio(this.currentUser, nPortfolio);
    try {
      this.theStockMarketModel.writeUpdatedUser(this.currentUser, false);
    } catch (IOException e) {
      //
    }
  }

  @Override
  public void getPerformance(String portfolioName, String date1, String date2) {
    String result = this.getPortfolioNameToCheck(portfolioName, false);
    try {
      if (result.equals("portfolio successful")) {

        this.view.printPerformance(String.valueOf(
                this.theStockMarketModel.calculatePortfolioPerformance(
                        currentUser, portfolioName, date1, date2)));
        String inputString = String.valueOf(
                this.theStockMarketModel.calculatePortfolioPerformance(
                        currentUser, portfolioName, date1, date2));
        brchart.setValues(inputString);

        JFrame frame = new JFrame("Performance of Portfolio Flex");
        frame.setSize(400, 500);
        frame.setContentPane(brchart);
        frame.setVisible(true);
      } else {
        this.view.setPerformanceWarning(result);
      }
    } catch (IOException e) {
      //
    }

  }


  private String getStockNameToCheck(Portfolio nPortfolio, String stockName, boolean create) {
    if (!isAlphabet(stockName)) {
      return "stockName cannot be empty and contain just alphabets";
    }
    if (!this.theStockMarketModel.checkUserOrStock(stockName, false)) {
      return "Stock does not exists, please give a different name";
    }

    Stock stockObj = this.theStockMarketModel.checkStockInPortfolio(nPortfolio, stockName);
    if (create) {
      if (stockObj != null) {
        return "Stock already exists in portfolio, give a different name";
      }
      return "stock successful";
    } else {
      if (stockObj != null) {
        return "stock successful";
      }
      return "stock not present in portfolio";
    }

  }

  @Override
  public void addFeatures(Features features) {
    //
  }


  private String getPortfolioNameToCheck(String portfolioName, boolean createNew) {
    if (!isAlphabet(portfolioName)) {
      return "portfolioName cannot be empty and contain just alphabets";
    }
    if (createNew && this.theStockMarketModel.portfolioExists(this.currentUser, portfolioName)) {
      return "portfolio with this name already exists choose different name";
    }
    if (!createNew && !this.theStockMarketModel.portfolioExists(this.currentUser, portfolioName)) {
      return "portfolio with this name does not exist choose valid";
    }
    return "portfolio successful";
  }

  @Override
  public void getPerformanceScreen() {
    this.view.setPerformancePanel();
  }
}
