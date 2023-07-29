import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import controller.InvestorController;
import model.AlphaVantageAPI;
import model.Investor;
import model.JsonReader;
import model.StockAPI;
import model.StockMarketModel;
import model.StockMarketModelInterface;
import model.YahooAPI;
import model.GetEvaluation;
import view.InvestorView;

import static org.junit.Assert.assertEquals;

/**
 * Class to test functionality of StockMarketModel.
 */
public class StockMarketModelTest {

  private final String welcomeString = "Welcome to the Stock Exchange\nPress 1 : Existing User\n"
          + "Press 2 : New User\nPress 3 : Exit\n";
  private final String userOptionString = "Press 1 to create new Portfolio\n"
          + "Press 2 to examine composition of a portfolio\n"
          + "Press 3 to determine total value of a portfolio on a certain date\n"
          + "Press 4 to edit portfolio\n"
          + "Press 5 to determine cost basis of portfolio\n"
          + "Press 6 to see portfolio Performance\n"
          + "Press 7 to exit\n";

  private final String enterUserName = "Please Enter Username\n";

  private final String welcomeToStock = "Welcome to stock world, need couple of information "
          + "before you can start buying\n";

  private final String stockOptions = "Please select one of the options\n"
          + "Press 1 to Buy Stock\nPress 2 to Exit\n";

  private final String select = "Please select one of the options\n";
  private String userNameFile;
  private String tickerFile;
  private String userDetailsDatabase;


  private InvestorView theView;

  private Investor theInvestor;

  private ByteArrayOutputStream bytes;
  private StockMarketModelInterface stockMarketModel;
  private StringBuilder sb;
  private StockAPI stockMarketAPI;

  @Before
  public void setup() throws IOException {
    userNameFile = "data/userName1.txt";
    tickerFile = "data/tickerName.txt";
    userDetailsDatabase = "data/jsonFormat1.json";
    bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    stockMarketModel = new StockMarketModel(tickerFile, userNameFile, userDetailsDatabase);
    theView = new InvestorView(out);
    sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    stockMarketAPI = new YahooAPI("UTC");
  }

  /**
   * Test to check if a UserName is successfully added to the database.
   */
  @Test
  public void addUserName() {
    String currentUser = "NewUserADD";
    InputStream in = new ByteArrayInputStream(("2\n" + currentUser).getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);

    try {
      theController.runMarketInterface();

    } catch (Exception noSuchElementException) {
      //
    }
    sb.append("Successfully logged in NewUserADD\n");
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * The method is to Load the userDetails if the user exists in database,
   * or create a new investor with userName passed.
   */
  @Test
  public void loadFromExisting() {
    String currentUser = "Test";
    String newPortfolio = "NewPortf";
    String checkStock = "GOOG";
    InputStream in = new ByteArrayInputStream(("1\n" + currentUser + "\n1\n" + newPortfolio
            + "\n1\n" + checkStock + "\n\n10\n2\n2\n" + newPortfolio + "\n").getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);

    try {
      theController.runMarketInterface();

    } catch (Exception noSuchElementException) {
      //
    }
    sb.append("Successfully logged in ").append(currentUser).append("\n");
    sb.append(userOptionString);
    sb.append("Please Enter InflexiblePortfolio name\n");
    sb.append(stockOptions);
    sb.append("Please Enter Stock name\n");
    sb.append("Please Enter Integer Quantity\n");
    sb.append(stockOptions);
    sb.append(userOptionString);
    sb.append("Please Enter portfolio name to see composition\n"
            + "Please Enter InflexiblePortfolio name\n");
    sb.append("\"GOOG\" : [83.49,10,\"2022-11-03\"]\n");
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check checkUserOrStock method.
   * New profile creation - UserInput is 2.
   * If the userName exists, a new userName should be provided in order to create an account.
   */
  @Test
  public void checkUserOrStock() {
    String currentUser = "testmodeleval";
    InputStream in = new ByteArrayInputStream(("2\n" + currentUser).getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);

    try {
      theController.runMarketInterface();

    } catch (Exception noSuchElementException) {
      //
    }
    sb.append("Please Enter different userName, name already exists in the system\n");
    sb.append("Please Enter Username\n");
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check checkUserOrStock method.
   * Existing profile - The userInput is 1.
   * If the userName exists, successfully login and provide portfolio menu.
   */
  @Test
  public void checkUserOrStock1() {
    String currentUser = "testmodeleval";
    InputStream in = new ByteArrayInputStream(("1\n" + currentUser).getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);

    try {
      theController.runMarketInterface();

    } catch (Exception noSuchElementException) {
      //
    }
    sb.append("Successfully logged in testmodeleval\n");
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check checkUserOrStock method.
   * Existing profile - The userinput is 1.
   * If the userName does not exist, as user to enter the username again.
   */
  @Test
  public void checkUserOrStock2() {
    String currentUser = "testmodelevaluate";
    InputStream in = new ByteArrayInputStream(("1\n" + currentUser).getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);

    try {
      theController.runMarketInterface();

    } catch (Exception noSuchElementException) {
      //
    }
    sb.append("Please Enter username does not exist, enter correct userName\n");
    sb.append("Please Enter Username\n");
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check checkUserOrStock method.
   * Create new profile - The userinput is 2.
   * If the userName entered has invalid characters.
   */
  @Test
  public void checkUserOrStock3() {
    String currentUser = "test627%";
    InputStream in = new ByteArrayInputStream(("2\n" + currentUser).getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);

    try {
      theController.runMarketInterface();

    } catch (Exception noSuchElementException) {
      //
    }
    sb.append("Please Enter valid username, should contain only characters\n");
    sb.append("Please Enter Username\n");
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check checkUserOrStock method.
   * Create new profile - The userinput is 2.
   * If the userName entered has entered valid characters.
   * Successfully create profile and display portfolio menu.
   */
  @Test
  public void checkUserOrStock4() {
    String currentUser = "CheckNewProfile";
    InputStream in = new ByteArrayInputStream(("2\n" + currentUser).getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);

    try {
      theController.runMarketInterface();

    } catch (Exception noSuchElementException) {
      //
    }
    sb.append("Successfully logged in CheckNewProfile\n");
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check checkUserOrStock method.
   * Input - StockName entered is invalid.
   */
  @Test
  public void checkUserOrStock5() {
    String currentUser = "Test";
    String newPortfolio = "stockcheck";
    String checkStock = "ASHWIN";
    InputStream in = new ByteArrayInputStream(("1\n" + currentUser + "\n1\n" + newPortfolio
            + "\n1\n" + checkStock).getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);

    try {
      theController.runMarketInterface();

    } catch (Exception noSuchElementException) {
      //
    }
    sb.append("Successfully logged in " + currentUser + "\n");
    sb.append(userOptionString);
    sb.append("Please Enter InflexiblePortfolio name\n");
    sb.append(stockOptions);
    sb.append("Please Enter Stock name\n");
    sb.append("Please Enter Stock does not exists, please give a different name\n");
    sb.append("Please Enter Stock name\n");
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check checkUserOrStock method.
   * The StockName entered is Valid.
   */
  @Test
  public void checkUserOrStock6() {
    String currentUser = "Test";
    String newPortfolio = "stockcheck9";
    String checkStock = "GOOG";
    InputStream in = new ByteArrayInputStream(("1\n" + currentUser + "\n1\n" + newPortfolio
            + "\n1\n" + checkStock + "\n\n10\n2\n2\n" + newPortfolio + "\n").getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);

    try {
      theController.runMarketInterface();

    } catch (Exception noSuchElementException) {
      //
    }
    sb.append("Successfully logged in " + currentUser + "\n");
    sb.append(userOptionString);
    sb.append("Please Enter InflexiblePortfolio name\n");
    sb.append(stockOptions);
    sb.append("Please Enter Stock name\n");
    sb.append("Please Enter Integer Quantity\n");
    sb.append(stockOptions);
    sb.append(userOptionString);
    sb.append("Please Enter portfolio name to see composition\n"
            + "Please Enter InflexiblePortfolio name\n");
    sb.append("\"GOOG\" : [83.49,10,\"2022-11-03\"]\n");
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check if the price of a stock on a specific date is returned accurately.
   */
  @Test
  public void getPriceOnDate() {
    String stockTicker = "GOOG";
    String userInputDate = "2022-11-02";
    InputStream in = new ByteArrayInputStream(("1\nTest\n1\nTY4\n1\n" + stockTicker
            + "\n\n1\n2\n3\nTY4\n" + userInputDate + "\n").getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    String apiDataAmount = stockMarketAPI.getAPIdata(stockTicker, userInputDate, false);
    try {
      theController.runMarketInterface();

    } catch (Exception noSuchElementException) {
      //
    }
    sb.append("Successfully logged in Test\n");
    sb.append(userOptionString);
    sb.append("Please Enter InflexiblePortfolio name\n");
    sb.append(stockOptions);
    sb.append("Please Enter Stock name\n");
    sb.append("Please Enter Integer Quantity\n");
    sb.append(stockOptions);
    //exiting stock menu
    sb.append(userOptionString);
    sb.append("Please Enter portfolio name to see composition\n");
    sb.append("Please Enter InflexiblePortfolio name\n");
    //input 3 - value of portfolio on certain date
    sb.append("Please Enter Date to evaluate portfolio\n");
    sb.append("The present evaluation is " + apiDataAmount + "\n");
    //System.out.println(apiDataAmount);
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check if UserName is getting written in the JSON file accurately.
   *
   * @throws FileNotFoundException if file is not found.
   */
  @Test
  public void writeUpdatedUser() throws FileNotFoundException {
    String userName = "qwedcvf";
    Investor investorTest = new Investor(userName);
    InputStream in = new ByteArrayInputStream(("2\n" + userName + "\n").getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    Investor actual = null;
    String nameInInvestor = investorTest.toString();
    System.out.println(nameInInvestor);
    try {
      theController.runMarketInterface();
    } catch (Exception noSuchElementException) {
      //
    }
    JsonReader jsonReaderTest = new JsonReader(userDetailsDatabase);
    String detailsInJson = jsonReaderTest.readUserDataBase(userName);
    actual = new Investor(userName, detailsInJson);
    System.out.println(actual);
    sb.append("Successfully logged in " + userName + "\n");
    sb.append(userOptionString);
    assertEquals(investorTest.toString(), actual.toString());
  }

  /**
   * Test to check if the portfolio evaluation is returning the accurate details.
   */
  @Test
  public void getEvaluation() {
    String stockTicker = "GOOG";
    String userInputDate = "2022-11-01";
    InputStream in = new ByteArrayInputStream(("1\nTest\n1\nport7\n1\n" + stockTicker
            + "\n\n1\n2\n3\nport7\n" + userInputDate + "\n").getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    String apiDataAmount = stockMarketAPI.getAPIdata(stockTicker, userInputDate, true);
    try {
      theController.runMarketInterface();

    } catch (Exception noSuchElementException) {
      //
    }
    sb.append("Successfully logged in Test\n");
    sb.append(userOptionString);
    sb.append("Please Enter InflexiblePortfolio name\n");
    sb.append(stockOptions);
    sb.append("Please Enter Stock name\n");
    sb.append("Please Enter Integer Quantity\n");
    sb.append(stockOptions);
    //exiting stock menu
    sb.append(userOptionString);
    sb.append("Please Enter portfolio name to see composition\n");
    sb.append("Please Enter InflexiblePortfolio name\n");
    //input 3 - value of portfolio on certain date
    sb.append("Please Enter Date to evaluate portfolio\n");
    sb.append("The present evaluation is " + apiDataAmount + "\n");
    //System.out.println(apiDataAmount);
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check if the portfolio evaluation is returning the accurate details.
   *
   * @throws IOException Exception
   */
  @Test
  public void getEvaluationOldPortfolio() throws IOException {
    String stockTicker = "GOOG";
    String userInputDate = "2022-11-03";
    String testPortfolio = "porttest";
    String currentUser = "testmodeleval";
    InputStream in = new ByteArrayInputStream(("1\n" + currentUser + "\n3\n" + testPortfolio + "\n"
            + userInputDate + "\n").getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    String apiDataAmount = stockMarketAPI.getAPIdata(stockTicker, userInputDate, true);

    StockMarketModel theStockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);

    float sum = 0;
    float value = 0;
    String[] stocksInTest = {"GOOG", "T", "TA"};
    String[] qtyOfStocksInTest = {"2", "5", "3"};
    for (int i = 0; i < stocksInTest.length; i++) {
      sum += Float.parseFloat(stockMarketAPI.getAPIdata(stocksInTest[i], userInputDate, true))
              * (Float.parseFloat(qtyOfStocksInTest[i]));
    }
    try {
      theController.runMarketInterface();

    } catch (Exception noSuchElementException) {
      //
    }
    sb.append("Successfully logged in " + currentUser + "\n");
    sb.append(userOptionString);
    sb.append("Please Enter portfolio name to see composition\n");
    sb.append("Please Enter InflexiblePortfolio name\n");
    sb.append("Please Enter Date to evaluate portfolio\n");
    sb.append("The present evaluation is " + sum + "\n");
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check if for a portfolio, cost Averaging is returning accurate details.
   * This test case considers continuous days' configuration.
   */
  @Test
  public void costAveragingPortfolioWorking() {
    String stockTicker1 = "GOOG";
    String stockTicker2 = "T";
    String userName = "sampleuser";
    String userInputStartDate = "2022-11-27";
    String userInputEndDate = "2022-11-29";
    String evalDate = "2022-11-30";
    int range = 1;
    String portfolioName = "sampleportppppp";
    float totalAmount = 100.0f;
    int countOfStocks = 2;
    int w1 = 20;
    int w2 = 80;
    float commission = 30.0f;
    InputStream in = new ByteArrayInputStream(("1\n" + userName + "\n7\n" + portfolioName + "\n"
            + countOfStocks + "\n" + totalAmount + "\nGOOG\n20\nT\n80\n" + userInputStartDate
            + "\n1\n" + range + "\n3\n" + portfolioName + "\n" + evalDate + "\n").getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    GetEvaluation func = new GetEvaluation(new AlphaVantageAPI());
    try {
      theController.runMarketInterface();

    } catch (Exception noSuchElementException) {
      //
    }
    LocalDate beginDate = LocalDate.parse(userInputStartDate,
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    LocalDate endDate = LocalDate.now();
    float i1 = 0f;
    float i2 = 0f;
    long numOfDaysBetween = ChronoUnit.DAYS.between(beginDate, endDate) + 1;
    float finalCommission = commission * numOfDaysBetween * countOfStocks;
    while (numOfDaysBetween != 0) {
      float todayBuyingPrice = func.getPriceOnDate(stockTicker1, beginDate);
      float quantity = (totalAmount * (w1 / 100.0f)) / todayBuyingPrice;
      i1 += quantity;
      float todayBuyingPrice2 = func.getPriceOnDate(stockTicker2, beginDate);
      float quantity2 = (totalAmount * (w2 / 100.0f)) / todayBuyingPrice2;
      i2 += quantity2;
      numOfDaysBetween--;
      beginDate = beginDate.plusDays(1);
    }

    LocalDate evaluateDate = LocalDate.parse(evalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    float finalEvalatedAmount = (i1 * (func.getPriceOnDate(stockTicker1, evaluateDate))
            + i2 * (func.getPriceOnDate(stockTicker2, evaluateDate))) - finalCommission;
    sb.append("Successfully logged in " + userName + "\n");
    sb.append(select + userOptionString);
    sb.append("Please Enter Portfolio name\n");
    sb.append("Please Enter Total Number of Various Stocks\n");
    sb.append("Please Enter Total Amount to spend per month\n");
    sb.append("Please Enter Stock name\n");
    sb.append("Please Enter Weight of the stock\n");
    sb.append("Please Enter Stock name\n");
    sb.append("Please Enter Weight of the stock\n");
    sb.append("Please Enter the start date for the investment strategy\n");
    sb.append("Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd\n");
    sb.append("Please Enter 0 to enter end date or 1 for continuous\n");
    sb.append("Please Enter investment interval\n");
    sb.append(select + userOptionString);
    sb.append("Please Enter portfolio name to see composition\n");
    sb.append("Please Enter Portfolio name\n");
    sb.append("Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd\n");
    sb.append("The present evaluation is " + finalEvalatedAmount + "\n");
    sb.append(select + userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check if for a portfolio, cost Averaging is returning the accurate details,
   * if future date is provided.
   */
  @Test
  public void costAveragingPortfolioInvalidDate() {
    String stockTicker1 = "GOOG";
    String stockTicker2 = "T";
    String userName = "sampleuser";
    String userInputStartDate = "2023-11-27";
    String userInputEndDate = "2022-11-29";
    String evalDate = "2022-11-30";
    int range = 1;
    String portfolioName = "flexty";
    float totalAmount = 100.0f;
    int countOfStocks = 2;
    int w1 = 20;
    int w2 = 80;
    float commission = 30.0f;
    InputStream in = new ByteArrayInputStream(("1\n" + userName + "\n7\n" + portfolioName + "\n"
            + countOfStocks + "\n" + totalAmount + "\nGOOG\n20\nT\n80\n" + userInputStartDate
            + "\n").getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    GetEvaluation func = new GetEvaluation(new AlphaVantageAPI());
    try {
      theController.runMarketInterface();

    } catch (Exception noSuchElementException) {
      //
    }
    sb.append("Successfully logged in ").append(userName).append("\n");
    sb.append(select + userOptionString);
    sb.append("Please Enter Portfolio name\n");
    sb.append("Please Enter Total Number of Various Stocks\n");
    sb.append("Please Enter Total Amount to spend per month\n");
    sb.append("Please Enter Stock name\n");
    sb.append("Please Enter Weight of the stock\n");
    sb.append("Please Enter Stock name\n");
    sb.append("Please Enter Weight of the stock\n");
    sb.append("Please Enter the start date for the investment strategy\n");
    sb.append("Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd\n");
    sb.append("Please Enter date that is not in future\n");
    assertEquals(sb.toString(), bytes.toString());
  }

  /**
   * Test to check if for a portfolio, cost Averaging is returning accurate details,
   * if invalid date is provided.
   */
  @Test
  public void costAveragingPortfolioInvalidDate2() {
    String stockTicker1 = "GOOG";
    String stockTicker2 = "T";
    String userName = "sampleuser";
    String userInputStartDate = "209u-11-27";
    String userInputEndDate = "2022-11-29";
    String evalDate = "2022-11-30";
    int range = 1;
    String portfolioName = "flextyw";
    float totalAmount = 100.0f;
    int countOfStocks = 2;
    int w1 = 20;
    int w2 = 80;
    float commission = 30.0f;
    InputStream in = new ByteArrayInputStream(("1\n" + userName + "\n7\n" + portfolioName + "\n"
            + countOfStocks + "\n" + totalAmount + "\nGOOG\n20\nT\n80\n" + userInputStartDate
            + "\n").getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    GetEvaluation func = new GetEvaluation(new AlphaVantageAPI());
    try {
      theController.runMarketInterface();

    } catch (Exception noSuchElementException) {
      //
    }
    sb.append("Successfully logged in ").append(userName).append("\n");
    sb.append(select + userOptionString);
    sb.append("Please Enter Portfolio name\n");
    sb.append("Please Enter Total Number of Various Stocks\n");
    sb.append("Please Enter Total Amount to spend per month\n");
    sb.append("Please Enter Stock name\n");
    sb.append("Please Enter Weight of the stock\n");
    sb.append("Please Enter Stock name\n");
    sb.append("Please Enter Weight of the stock\n");
    sb.append("Please Enter the start date for the investment strategy\n");
    sb.append("Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd\n");
    sb.append("Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd\n");
    assertEquals(sb.toString(), bytes.toString());
  }

  /**
   * Test to check if for a portfolio cost Averaging is returning the accurate details.
   * This test case considers End date configuration.
   */
  @Test
  public void costAveragingPortfolioWorkingEndDate() {
    String stockTicker1 = "GOOG";
    String stockTicker2 = "T";
    String userName = "sampleuser";
    String userInputStartDate = "2022-11-27";
    String userInputEndDate = "2022-11-29";
    String evalDate = "2022-11-29";
    int range = 1;
    String portfolioName = "flexoopp";
    float totalAmount = 100.0f;
    int countOfStocks = 2;
    int w1 = 20;
    int w2 = 80;
    float commission = 30.0f;
    InputStream in = new ByteArrayInputStream(("1\n" + userName + "\n7\n" + portfolioName + "\n"
            + countOfStocks + "\n" + totalAmount + "\nGOOG\n20\nT\n80\n" + userInputStartDate
            + "\n0\n" + userInputEndDate + "\n" + range + "\n3\n" + portfolioName + "\n"
            + evalDate + "\n").getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    GetEvaluation func = new GetEvaluation(new AlphaVantageAPI());
    try {
      theController.runMarketInterface();

    } catch (Exception noSuchElementException) {
      //
    }
    LocalDate beginDate = LocalDate.parse(userInputStartDate,
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    LocalDate endDate = LocalDate.now();
    float i1 = 0f;
    float i2 = 0f;
    long numOfDaysBetween = ChronoUnit.DAYS.between(beginDate, endDate);
    float finalCommission = commission * numOfDaysBetween * countOfStocks;
    while (numOfDaysBetween != 0) {
      float todayBuyingPrice = func.getPriceOnDate(stockTicker1, beginDate);
      System.out.println(beginDate + " buying price of "
              + stockTicker1 + "is: " + todayBuyingPrice);
      float quantity = (totalAmount * (w1 / 100.0f)) / todayBuyingPrice;
      System.out.println("Quantity of " + stockTicker1 + "is: " + quantity);
      i1 += quantity;
      float todayBuyingPrice2 = func.getPriceOnDate(stockTicker2, beginDate);
      System.out.println(beginDate + " buying price of "
              + stockTicker2 + "is: " + todayBuyingPrice2);
      float quantity2 = (totalAmount * (w2 / 100.0f)) / todayBuyingPrice2;
      System.out.println("Quantity of " + stockTicker2 + "is: " + quantity);
      i2 += quantity2;
      numOfDaysBetween--;
      beginDate = beginDate.plusDays(1);
    }

    LocalDate evaluateDate = LocalDate.parse(evalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    float finalEvalatedAmount = (i1 * (func.getPriceOnDate(stockTicker1, evaluateDate))
            + i2 * (func.getPriceOnDate(stockTicker2, evaluateDate))) - finalCommission;
    System.out.println("Commission fee: " + finalCommission);
    System.out.println(finalEvalatedAmount);
    sb.append("Successfully logged in ").append(userName).append("\n");
    sb.append(select + userOptionString);
    sb.append("Please Enter Portfolio name\n");
    sb.append("Please Enter Total Number of Various Stocks\n");
    sb.append("Please Enter Total Amount to spend per month\n");
    sb.append("Please Enter Stock name\n");
    sb.append("Please Enter Weight of the stock\n");
    sb.append("Please Enter Stock name\n");
    sb.append("Please Enter Weight of the stock\n");
    sb.append("Please Enter the start date for the investment strategy\n");
    sb.append("Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd\n");
    sb.append("Please Enter 0 to enter end date or 1 for continuous\n");
    sb.append("Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd\n");
    sb.append("Please Enter investment interval\n");
    sb.append(select + userOptionString);
    sb.append("Please Enter portfolio name to see composition\n");
    sb.append("Please Enter Portfolio name\n");
    sb.append("Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd\n");
    sb.append("The present evaluation is ").append(finalEvalatedAmount).append("\n");
    sb.append(select + userOptionString);
    assertEquals(sb.toString(), bytes.toString());
  }

  /**
   * Test to check if for a portfolio, cost Averaging is returning accurate details.
   * When negative interval is provided.
   */
  @Test
  public void costAveragingPortfolioNegativeInterval() {
    String stockTicker1 = "GOOG";
    String stockTicker2 = "T";
    String userName = "sampleuser";
    String userInputStartDate = "2022-11-27";
    String userInputEndDate = "2022-11-29";
    String evalDate = "2022-11-29";
    int range = -8;
    String portfolioName = "fledf2";
    float totalAmount = 100.0f;
    int countOfStocks = 2;
    int w1 = 20;
    int w2 = 80;
    float commission = 30.0f;
    InputStream in = new ByteArrayInputStream(("1\n" + userName + "\n7\n" + portfolioName + "\n"
            + countOfStocks + "\n" + totalAmount + "\nGOOG\n20\nT\n80\n" + userInputStartDate
            + "\n0\n" + userInputEndDate + "\n" + range + "\n").getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    GetEvaluation func = new GetEvaluation(new AlphaVantageAPI());
    try {
      theController.runMarketInterface();

    } catch (Exception noSuchElementException) {
      //
    }
    LocalDate beginDate = LocalDate.parse(userInputStartDate,
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    LocalDate endDate = LocalDate.now();
    float i1 = 0f;
    float i2 = 0f;
    long numOfDaysBetween = ChronoUnit.DAYS.between(beginDate, endDate);
    float finalCommission = commission * numOfDaysBetween * countOfStocks;
    while (numOfDaysBetween != 0) {
      float todayBuyingPrice = func.getPriceOnDate(stockTicker1, beginDate);
      //System.out.println(beginDate+" buying price of "+stockTicker1+"is: "+todayBuyingPrice);
      float quantity = (totalAmount * (w1 / 100.0f)) / todayBuyingPrice;
      //System.out.println("Quantity of "+stockTicker1+"is: "+quantity);
      i1 += quantity;
      float todayBuyingPrice2 = func.getPriceOnDate(stockTicker2, beginDate);
      //System.out.println(beginDate+" buying price of "+stockTicker2+"is: "+todayBuyingPrice2);
      float quantity2 = (totalAmount * (w2 / 100.0f)) / todayBuyingPrice2;
      //System.out.println("Quantity of "+stockTicker2+"is: "+quantity);
      i2 += quantity2;
      numOfDaysBetween--;
      beginDate = beginDate.plusDays(1);
    }

    LocalDate evaluateDate = LocalDate.parse(evalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    float finalEvalatedAmount = (i1 * (func.getPriceOnDate(stockTicker1, evaluateDate))
            + i2 * (func.getPriceOnDate(stockTicker2, evaluateDate))) - finalCommission;
    //System.out.println("Commission fee: "+finalCommission);
    //System.out.println(finalEvalatedAmount);
    sb.append("Successfully logged in ").append(userName).append("\n");
    sb.append(select + userOptionString);
    sb.append("Please Enter Portfolio name\n");
    sb.append("Please Enter Total Number of Various Stocks\n");
    sb.append("Please Enter Total Amount to spend per month\n");
    sb.append("Please Enter Stock name\n");
    sb.append("Please Enter Weight of the stock\n");
    sb.append("Please Enter Stock name\n");
    sb.append("Please Enter Weight of the stock\n");
    sb.append("Please Enter the start date for the investment strategy\n");
    sb.append("Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd\n");
    sb.append("Please Enter 0 to enter end date or 1 for continuous\n");
    sb.append("Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd\n");
    sb.append("Please Enter investment interval\n");
    sb.append("Please Enter investment interval\n");
    assertEquals(sb.toString(), bytes.toString());
  }

  /**
   * Test to check if for a portfolio, cost Averaging is returning the accurate details.
   * When negative weight is provided.
   */
  @Test
  public void costAveragingPortfolioIncorrectWeights() {
    String stockTicker1 = "GOOG";
    String stockTicker2 = "T";
    String userName = "sampleuser";
    String userInputStartDate = "2022-11-27";
    String userInputEndDate = "2022-11-29";
    String evalDate = "2022-11-29";
    String portfolioName = "quidoba";
    float totalAmount = 100.0f;
    int countOfStocks = 2;
    int w1 = 20;
    int w2 = 80;
    float commission = 30.0f;
    InputStream in = new ByteArrayInputStream(("1\n" + userName + "\n7\n"
            + portfolioName + "\n"
            + countOfStocks + "\n" + totalAmount + "\nGOOG\n-20\n").getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    GetEvaluation func = new GetEvaluation(new AlphaVantageAPI());
    try {
      theController.runMarketInterface();

    } catch (Exception noSuchElementException) {
      //
    }
    LocalDate beginDate = LocalDate.parse(userInputStartDate,
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    LocalDate endDate = LocalDate.now();
    float i1 = 0f;
    float i2 = 0f;
    long numOfDaysBetween = ChronoUnit.DAYS.between(beginDate, endDate);
    float finalCommission = commission * numOfDaysBetween * countOfStocks;
    while (numOfDaysBetween != 0) {
      float todayBuyingPrice = func.getPriceOnDate(stockTicker1, beginDate);
      //System.out.println(beginDate+" buying price of "+stockTicker1+"is: "+todayBuyingPrice);
      float quantity = (totalAmount * (w1 / 100.0f)) / todayBuyingPrice;
      //System.out.println("Quantity of "+stockTicker1+"is: "+quantity);
      i1 += quantity;
      float todayBuyingPrice2 = func.getPriceOnDate(stockTicker2, beginDate);
      //System.out.println(beginDate+" buying price of "+stockTicker2+"is: "+todayBuyingPrice2);
      float quantity2 = (totalAmount * (w2 / 100.0f)) / todayBuyingPrice2;
      //System.out.println("Quantity of "+stockTicker2+"is: "+quantity);
      i2 += quantity2;
      numOfDaysBetween--;
      beginDate = beginDate.plusDays(1);
    }

    LocalDate evaluateDate = LocalDate.parse(evalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    float finalEvalatedAmount = (i1 * (func.getPriceOnDate(stockTicker1, evaluateDate))
            + i2 * (func.getPriceOnDate(stockTicker2, evaluateDate))) - finalCommission;
    //System.out.println("Commission fee: "+finalCommission);
    //System.out.println(finalEvalatedAmount);
    sb.append("Successfully logged in ").append(userName).append("\n");
    sb.append(select + userOptionString);
    sb.append("Please Enter Portfolio name\n");
    sb.append("Please Enter Total Number of Various Stocks\n");
    sb.append("Please Enter Total Amount to spend per month\n");
    sb.append("Please Enter Stock name\n");
    sb.append("Please Enter Weight of the stock\n");
    sb.append("Please Enter Weight of the stock\n");
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }
}