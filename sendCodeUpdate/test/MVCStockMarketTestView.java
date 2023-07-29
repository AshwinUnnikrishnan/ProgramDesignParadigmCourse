import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import controller.InvestorController;
import model.StockMarketModel;
import model.StockMarketModelInterface;
import view.InvestorView;

import static org.junit.Assert.assertEquals;

/**
 * Class to test all the view test cases.
 */
public class MVCStockMarketTestView {

  private final String welcomeString = "Welcome to the Stock Exchange\nPress 1 : Existing User\n"
          + "Press 2 : New User\nPress 3 : Exit\n";
  private final String userOptionString = "Please select one of the options\n"
          + "Press 1 to create new Portfolio\n"
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
          + "Press 1 to Buy Stock\n"
          + "Press 2 to Exit\n";

  private final String stockOptionsSell = "Please select one of the options\n"
          + "Press 1 to Sell Stock\nPress 2 to Exit\n";

  private final String stockAdditionDeletion = "Please select one of the options\n"
          + "Press 1 adding more stocks to portfolio\n"
          + "Press 2 for deleting stocks from portfolio\n";

  private final String typeOfPortfolio = "Please select one of the options\n"
          + "Press 1 for inflexible portfolio\n"
          + "Press 2 for flexible portfolio\n";
  private final String stockBrokerFee = "Please Enter Brokerage Fees\n";
  private InvestorView theView;

  ByteArrayOutputStream bytes;
  StockMarketModelInterface stockMarketModel;
  StringBuilder sb;

  @Before
  public void setup() throws IOException {
    String userNameFile = "data/userName1.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat1.json";
    bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    stockMarketModel = new StockMarketModel(tickerFile, userNameFile, userDetailsDatabase);
    theView = new InvestorView(out);
    sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
  }


  @Test
  public void existingUserCheck() throws Exception {
    InputStream in = new ByteArrayInputStream("1".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel, in);
    try {
      theController.runMarketInterface();
    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void newUserCheck() throws Exception {
    InputStream in = new ByteArrayInputStream("2".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();
    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }


  @Test
  public void invalidInput() throws Exception {
    InputStream in = new ByteArrayInputStream("a".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();
    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append("Please Enter Valid Input Button\n");
    sb.append(welcomeString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void enterValidUserName() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nAish".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName1.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat1.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    sb.append("Successfully logged in Aish\n");
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void enterInvalidUserName() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nAOPOP".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    sb.append("Please Enter username does not exist, enter correct userName\n");
    sb.append(enterUserName);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void enterInvalidCharacter() throws Exception {
    InputStream in = new ByteArrayInputStream("1\n*".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    sb.append("Please Enter valid username, should contain only characters\n");
    sb.append(enterUserName);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void newValidUser() throws Exception {
    String userName = "TrialUser2";
    InputStream in = new ByteArrayInputStream(("2\n" + userName).getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName1.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat1.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();
    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    sb.append("Successfully logged in " + userName + "\n");
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void newInvalidUser() throws Exception {
    InputStream in = new ByteArrayInputStream("2\n**^".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    sb.append("Please Enter valid username, should contain only characters\n");
    sb.append(enterUserName);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void newInvalidUser2() throws Exception {
    InputStream in = new ByteArrayInputStream("2\n  ".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    sb.append("Please Enter valid username, should contain only characters\n");
    sb.append(enterUserName);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void newInvalidUser3() throws Exception {
    InputStream in = new ByteArrayInputStream("2\nAISH*&".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    sb.append("Please Enter valid username, should contain only characters\n");
    sb.append(enterUserName);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void existingUser() throws Exception {
    InputStream in = new ByteArrayInputStream("2\nSideesh".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName1.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat1.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }

    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    sb.append("Please Enter different userName, name already exists in the system\n");
    sb.append(enterUserName);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void existingUserNewPortfolio() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nAish\n1".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName1.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat1.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }

    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    sb.append("Successfully logged in Aish\n");
    sb.append(userOptionString);
    sb.append("Please Enter Portfolio name\n");
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void existingUserExistingPortfolio() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nAish\n2".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName1.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat1.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }

    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    sb.append("Successfully logged in Aish\n");
    sb.append(userOptionString);
    sb.append("Please Enter portfolio name to see composition\n");
    sb.append("Please Enter Portfolio name\n");
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void existingUserExistingPortfolioComposition() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nAish\n2\nflexi".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName1.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat1.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }

    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    sb.append("Successfully logged in Aish\n");
    sb.append(userOptionString);
    sb.append("Please Enter portfolio name to see composition\n");
    sb.append("Please Enter Portfolio name\n");
    sb.append("Stock Name : [Buying Price($), Quantity, Buying Date]\n"
            + "\"HI\" : [39.49,50,\"2022-10-04\"]\n"
            + "\"RT\" : [2.4,12,\"2022-04-12\"]\n"
            + "\"POR\" : [53.12,10,\"2022-09-12\"]\n"
            + "\"TR\" : [33.6,2,\"2022-02-12\"]\n"
            + "\"EW\" : [97.36,120,\"2022-09-12\"]\n"
            + "\"GOOG\" : [2567.49,90,\"2022-04-12\"]\n"
            + "\"PQ\" : [0.5764,12,\"2022-10-09\"]\n"
            + "\"ABC\" : [135.71,87,\"2022-01-12\"]\n"
            + "\"AAP\" : [208.9,12,\"2022-05-12\"]\n"
            + "\"T\" : [20.6,120,\"2022-07-12\"]\n"
            + "\"TY\" : [26.09,12,\"2022-10-09\"]\n");
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void existingUserExistingPortfolioEvaluation2() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nAish\n3\nflexi\n2022-10-21".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName1.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat1.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }

    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    sb.append("Successfully logged in Aish\n");
    sb.append(userOptionString);
    sb.append("Please Enter portfolio name to see composition\n");
    sb.append("Please Enter Portfolio name\n");
    sb.append("Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd\n");
    sb.append("The present evaluation is 42915.26\n");
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void existingUserExistingPortfolioEvaluation() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nAish\n3\nflexi\n".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName1.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat1.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }

    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    sb.append("Successfully logged in Aish\n");
    sb.append(userOptionString);
    sb.append("Please Enter portfolio name to see composition\n");
    sb.append("Please Enter Portfolio name\n");
    sb.append("Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd\n");
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void checkPortfolioComposition() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nAish\n2\nflexi".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName1.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat1.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }

    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    sb.append("Successfully logged in Aish\n");
    sb.append(userOptionString);
    sb.append("Please Enter portfolio name to see composition\n");
    sb.append("Please Enter Portfolio name\n");
    sb.append("Stock Name : [Buying Price($), Quantity, Buying Date]\n"
            + "\"TR\" : [33.6,2,\"2022-02-12\"]\n"
            + "\"RT\" : [2.4,12,\"2022-04-12\"]\n"
            + "\"POR\" : [53.12,10,\"2022-09-12\"]\n"
            + "\"GOOG\" : [2567.49,90,\"2022-04-12\"]\n"
            + "\"EW\" : [97.36,120,\"2022-09-12\"]\n"
            + "\"PQ\" : [0.5764,10,\"2022-10-09\"]\n"
            + "\"AAP\" : [208.9,12,\"2022-05-12\"]\n"
            + "\"TY\" : [26.09,12,\"2022-10-09\"]\n"
            + "\"T\" : [20.6,110,\"2022-07-12\"]\n"
            + "\"MN\" : [12.8,20,\"2022-08-12\"]\n"
            + "\"HI\" : [39.49,50,\"2022-10-04\"]\n"
            + "\"ABC\" : [135.71,87,\"2022-01-12\"]\n");
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void checkInvalidFutureDate() throws Exception {

    InputStream in = new ByteArrayInputStream(("1\nAish\n4\nflexi\n1\n1\nDAL"
            + "\n100\n20.50\n2029-01-09").getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName1.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat1.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }

    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    sb.append("Successfully logged in Aish\n");
    sb.append(userOptionString);
    sb.append("Please Enter portfolio name to update\n");
    sb.append("Please Enter Portfolio name\n");
    sb.append("Stock Name : [Buying Price($), Quantity, Buying Date]\n"
            + "\"TR\" : [33.6,2,\"2022-02-12\"]\n"
            + "\"RT\" : [2.4,12,\"2022-04-12\"]\n"
            + "\"POR\" : [53.12,10,\"2022-09-12\"]\n"
            + "\"GOOG\" : [2567.49,90,\"2022-04-12\"]\n"
            + "\"EW\" : [97.36,120,\"2022-09-12\"]\n"
            + "\"PQ\" : [0.5764,10,\"2022-10-09\"]\n"
            + "\"AAP\" : [208.9,12,\"2022-05-12\"]\n"
            + "\"TY\" : [26.09,12,\"2022-10-09\"]\n"
            + "\"T\" : [20.6,110,\"2022-07-12\"]\n"
            + "\"MN\" : [12.8,20,\"2022-08-12\"]\n"
            + "\"HI\" : [39.49,50,\"2022-10-04\"]\n"
            + "\"ABC\" : [135.71,87,\"2022-01-12\"]\n");
    sb.append(stockAdditionDeletion);
    sb.append(stockOptions);
    sb.append("Please Enter Stock name\n");
    sb.append(stockBrokerFee);
    sb.append("Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd\n");
    sb.append("Please Enter date that is not in future\n");
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void checkInvalidDate() throws Exception {
    InputStream in = new ByteArrayInputStream(("1\nAish\n4\nflexi\n1\n1\nDD\n100"
            + "\n20.50\n2029-09-31").getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName1.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat1.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }

    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    sb.append("Successfully logged in Aish\n");
    sb.append(userOptionString);
    sb.append("Please Enter portfolio name to update\n");
    sb.append("Please Enter Portfolio name\n");
    sb.append("Stock Name : [Buying Price($), Quantity, Buying Date]\n"
            + "\"TR\" : [33.6,2,\"2022-02-12\"]\n"
            + "\"RT\" : [2.4,12,\"2022-04-12\"]\n"
            + "\"POR\" : [53.12,10,\"2022-09-12\"]\n"
            + "\"GOOG\" : [2567.49,90,\"2022-04-12\"]\n"
            + "\"EW\" : [97.36,120,\"2022-09-12\"]\n"
            + "\"PQ\" : [0.5764,10,\"2022-10-09\"]\n"
            + "\"AAP\" : [208.9,12,\"2022-05-12\"]\n"
            + "\"TY\" : [26.09,12,\"2022-10-09\"]\n"
            + "\"T\" : [20.6,110,\"2022-07-12\"]\n"
            + "\"MN\" : [12.8,20,\"2022-08-12\"]\n"
            + "\"HI\" : [39.49,50,\"2022-10-04\"]\n"
            + "\"ABC\" : [135.71,87,\"2022-01-12\"]\n");
    sb.append(stockAdditionDeletion);
    sb.append(stockOptions);
    sb.append("Please Enter Stock name\n");
    sb.append(stockBrokerFee);
    sb.append("Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd\n");
    sb.append("Please Enter a valid date that falls in the month\n");
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void checkStockValue() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nAish\n4\nflexi\n2\n1\nHI\n70\n".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName1.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat1.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }

    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    sb.append("Successfully logged in Aish\n");
    sb.append(userOptionString);
    sb.append("Please Enter portfolio name to update\n");
    sb.append("Please Enter Portfolio name\n");
    sb.append("Stock Name : [Buying Price($), Quantity, Buying Date]\n"
            + "\"TR\" : [33.6,2,\"2022-02-12\"]\n"
            + "\"RT\" : [2.4,12,\"2022-04-12\"]\n"
            + "\"POR\" : [53.12,10,\"2022-09-12\"]\n"
            + "\"GOOG\" : [2567.49,90,\"2022-04-12\"]\n"
            + "\"EW\" : [97.36,120,\"2022-09-12\"]\n"
            + "\"PQ\" : [0.5764,10,\"2022-10-09\"]\n"
            + "\"AAP\" : [208.9,12,\"2022-05-12\"]\n"
            + "\"TY\" : [26.09,12,\"2022-10-09\"]\n"
            + "\"T\" : [20.6,110,\"2022-07-12\"]\n"
            + "\"MN\" : [12.8,20,\"2022-08-12\"]\n"
            + "\"HI\" : [39.49,50,\"2022-10-04\"]\n"
            + "\"ABC\" : [135.71,87,\"2022-01-12\"]\n");
    sb.append(stockAdditionDeletion);
    sb.append(stockOptionsSell);
    sb.append("Please Enter Stock name\n");
    sb.append("Please Enter Integer Quantity\n");
    sb.append("Please Enter quantity within range 0 and 50\n");
    sb.append("Please Enter Integer Quantity\n");
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void checkIncorrectCommissionFee() throws Exception {
    InputStream in = new ByteArrayInputStream(("1\nAish\n4\nflexi\n1\n1"
            + "\nDD\n100\n-20.50\n2022-09-31").getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName1.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat1.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }

    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    sb.append("Successfully logged in Aish\n");
    sb.append(userOptionString);
    sb.append("Please Enter portfolio name to update\n");
    sb.append("Please Enter Portfolio name\n");
    sb.append("Stock Name : [Buying Price($), Quantity, Buying Date]\n"
            + "\"TR\" : [33.6,2,\"2022-02-12\"]\n"
            + "\"RT\" : [2.4,12,\"2022-04-12\"]\n"
            + "\"POR\" : [53.12,10,\"2022-09-12\"]\n"
            + "\"GOOG\" : [2567.49,90,\"2022-04-12\"]\n"
            + "\"EW\" : [97.36,120,\"2022-09-12\"]\n"
            + "\"PQ\" : [0.5764,10,\"2022-10-09\"]\n"
            + "\"AAP\" : [208.9,12,\"2022-05-12\"]\n"
            + "\"TY\" : [26.09,12,\"2022-10-09\"]\n"
            + "\"T\" : [20.6,110,\"2022-07-12\"]\n"
            + "\"MN\" : [12.8,20,\"2022-08-12\"]\n"
            + "\"HI\" : [39.49,50,\"2022-10-04\"]\n"
            + "\"ABC\" : [135.71,87,\"2022-01-12\"]\n");
    sb.append(stockAdditionDeletion);
    sb.append(stockOptions);
    sb.append("Please Enter Stock name\n");
    sb.append(stockBrokerFee);
    sb.append(stockBrokerFee);
    sb.append(stockBrokerFee);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  @Test
  public void checkValidInputs() throws Exception {
    //String profileName = "friendsforall";
    InputStream in = new ByteArrayInputStream(("1\nAish\n1\ncupn\n2\n1\nGOOG\n100\n20.50\n"
            + "2022-10-12\n1\nTA\n200\n10.00\n2022-10-04\n"
            + "2\n5\ncupn\n2022-11-17\n").getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    String userNameFile = "data/userName1.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat1.json";

    InvestorView theView = new InvestorView(out);
    StockMarketModel stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
            userDetailsDatabase);
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }

    StringBuilder sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
    sb.append("Successfully logged in Aish\n");
    sb.append(userOptionString);
    sb.append("Please Enter Portfolio name\n");
    sb.append(typeOfPortfolio);
    sb.append(stockOptions);
    sb.append("Please Enter Stock name\n");
    sb.append(stockBrokerFee);
    sb.append("Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd\n");
    sb.append(stockOptions);
    sb.append("Please Enter Stock name\n");
    sb.append(stockBrokerFee);
    sb.append("Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd\n");
    sb.append(stockOptions);
    sb.append(userOptionString);
    sb.append("Please Enter portfolio name to update\n"
            + "Please Enter Portfolio name\n");
    sb.append("Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd\n");
    sb.append("Cost basis for portfolio cupn on date 2022-11-17 is 21442.5$\n");
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }
}