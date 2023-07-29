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
public class MVCStockMarketTest {

  private final String welcomeString = "Welcome to the Stock Exchange\nPress 1 : Existing User\n"
          + "Press 2 : New User\nPress 3 : Exit\n";
  private final String userOptionString = "Please select one of the options\nPress 1 to create "
          + "new InflexiblePortfolio\nPress 2 to examine composition of a portfolio\nPress 3 to "
          + "determine total value of a portfolio on a certain date\nPress 4 to exit\n";

  private final String enterUserName = "Please Enter Username\n";

  private final String welcomeToStock = "Welcome to stock world, need couple of information "
          + "before you can start buying\n";

  private final String stockOptions = "Please select one of the options\n"
          + "Press 1 to Buy Stock\nPress 2 to Exit\n";

  private InvestorView theView;

  ByteArrayOutputStream bytes;
  StockMarketModelInterface stockMarketModel;
  StringBuilder sb;

  @Before
  public void setup() throws IOException {
    String userNameFile = "data/userName.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat.json";
    bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    stockMarketModel = new StockMarketModel(tickerFile, userNameFile, userDetailsDatabase);
    theView = new InvestorView(out);
    sb = new StringBuilder(welcomeString);
    sb.append(welcomeToStock);
    sb.append(enterUserName);
  }

  /**
   * Test to check the welcome screen.
   * User gives input as 1 and gets the welcome message to enter username.
   *
   * @throws Exception exception
   */
  @Test
  public void existingUserCheckInput1() throws Exception {
    InputStream in = new ByteArrayInputStream("1".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel, in);
    try {
      theController.runMarketInterface();
    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check the welcome screen.
   * User gives input as 1 and gets the welcome message for creating new userid.
   *
   * @throws Exception exception
   */
  @Test
  public void newUserCheckInput2() throws Exception {
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

  /**
   * Test to check when an invalid option is selected.
   *
   * @throws Exception exception
   */
  @Test
  public void invalidInputAplhabet() throws Exception {
    InputStream in = new ByteArrayInputStream("a".getBytes());
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

  /**
   * Test to check when an invalid option is selected.
   *
   * @throws Exception exception
   */
  @Test
  public void invalidInputSpecialCharacter() throws Exception {
    InputStream in = new ByteArrayInputStream("*".getBytes());
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

  /**
   * Test to check when an invalid option is selected.
   *
   * @throws Exception exception
   */
  @Test
  public void invalidInputSpace() throws Exception {
    InputStream in = new ByteArrayInputStream(" 3".getBytes());
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

  /**
   * Test to check when user enters a valid username which exists in the username file.
   *
   * @throws Exception exception
   */
  @Test
  public void enterValidUserName() throws Exception {
    String userName = "AISH";
    InputStream in = new ByteArrayInputStream(("1\n" + userName).getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    sb.append("Successfully logged in " + userName + "\n");
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }


  /**
   * Test to check when user enters a valid username which does not exist in the username file.
   *
   * @throws Exception exception
   */
  @Test
  public void enterInvalidUserName() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nA".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    sb.append("Please Enter username does not exist, enter correct userName\n");
    sb.append(enterUserName);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check when user enters an invalid string as existing username.
   *
   * @throws Exception exception
   */
  @Test
  public void enterInvalidCharacter() throws Exception {
    InputStream in = new ByteArrayInputStream("1\n*".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    sb.append("Please Enter valid username, should contain only characters\n");
    sb.append(enterUserName);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check when user enters a valid string to create new username.
   *
   * @throws Exception exception
   */
  @Test
  public void newValidUser() throws Exception {
    InputStream in = new ByteArrayInputStream("2\nRohan".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    sb.append("Successfully logged in Rohan\n");
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check when user enters an invalid string to create new username.
   *
   * @throws Exception exception
   */
  @Test
  public void newInvalidUser() throws Exception {
    InputStream in = new ByteArrayInputStream("2\n**^".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    sb.append("Please Enter valid username, should contain only characters\n");
    sb.append(enterUserName);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check when user enters an invalid string(Spaces) to create new username.
   *
   * @throws Exception exception
   */
  @Test
  public void newInvalidUser2() throws Exception {
    InputStream in = new ByteArrayInputStream("2\n  ".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    sb.append("Please Enter valid username, should contain only characters\n");
    sb.append(enterUserName);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check when user enters an invalid string(String+Special Char) to create new username.
   *
   * @throws Exception exception
   */
  @Test
  public void newInvalidUser3() throws Exception {
    InputStream in = new ByteArrayInputStream("2\nAISH*&".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    sb.append("Please Enter valid username, should contain only characters\n");
    sb.append(enterUserName);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check when user enters a valid username which exists in the username file.
   *
   * @throws Exception exception
   */
  @Test
  public void existingUser() throws Exception {
    InputStream in = new ByteArrayInputStream("2\nSid".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    sb.append("Please Enter different userName, name already exists in the system\n");
    sb.append(enterUserName);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check the creation of New InflexiblePortfolio print screen,
   * when an existing user enters 1 in portfolio menu.
   *
   * @throws Exception exception
   */
  @Test
  public void existingUserNewPortfolio() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nAISH\n1".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    sb.append("Successfully logged in AISH\n");
    sb.append(userOptionString);
    sb.append("Please Enter InflexiblePortfolio name\n");
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check the composition of portfolio when an existing user enters 2 in portfolio menu.
   *
   * @throws Exception exception
   */
  @Test
  public void existingUserExistingPortfolio() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nAISH\n2".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    sb.append("Successfully logged in AISH\n");
    sb.append(userOptionString);
    sb.append("Please Enter portfolio name to see composition\n");
    sb.append("Please Enter InflexiblePortfolio name\n");
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check the existing portfolio composition of an existing user.
   *
   * @throws Exception exception
   */
  @Test
  public void existingUserExistingPortfolioComposition() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nAISH\n2\nRETIREMENT".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    sb.append("Successfully logged in AISH\n");
    sb.append(userOptionString);
    sb.append("Please Enter portfolio name to see composition\n");
    sb.append("Please Enter InflexiblePortfolio name\n");
    sb.append("\"T\" : [1.2,9,\"2022-10-30\"]\n");
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check when existing user wants to see InflexiblePortfolio composition.
   * To see the menu under portfolio composition.
   *
   * @throws Exception exception
   */
  @Test
  public void existingUserExistingPortfolioEvaluation() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nAISH\n3\nRETIREMENT".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    sb.append("Successfully logged in AISH\n");
    sb.append(userOptionString);
    sb.append("Please Enter portfolio name to see composition\n");
    sb.append("Please Enter InflexiblePortfolio name\n");
    sb.append("Please Enter Date to evaluate portfolio\n");
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check when existing user wants to see InflexiblePortfolio composition and enters date.
   *
   * @throws Exception exception
   */
  @Test
  public void existingUserExistingPortfolioEvaluation2() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nAISH\n3\nRETIREMENT\n2022-10-21".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    sb.append("Successfully logged in AISH\n");
    sb.append(userOptionString);
    sb.append("Please Enter portfolio name to see composition\n");
    sb.append("Please Enter InflexiblePortfolio name\n");
    sb.append("Please Enter Date to evaluate portfolio\n");
    sb.append("The present evaluation is 165.87\n");
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check when existing user wants to exit from the InflexiblePortfolio Menu.
   *
   * @throws Exception exception
   */
  @Test
  public void existingUserExistingPortfolioExitMenu() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nAISH\n4".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    sb.append("Successfully logged in AISH\n");
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check when existing user creates a InflexiblePortfolio but does not enter stocks.
   *
   * @throws Exception exception
   */
  @Test
  public void existingUserNewPortfolioNoStocks() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nAISH\n1\nQWERTY\n2".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    sb.append("Successfully logged in AISH\n");
    sb.append(userOptionString);
    sb.append("Please Enter InflexiblePortfolio name\n");
    sb.append(stockOptions);
    sb.append("InflexiblePortfolio not created because no stocks created in portfolio\n");
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check when existing user creates a valid InflexiblePortfolio and wants see Stocks menu.
   *
   * @throws Exception exception
   */
  @Test
  public void userNewPortfolioStockMenu() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nAISH\n1\nQWERTY\n1".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    sb.append("Successfully logged in AISH\n");
    sb.append(userOptionString);
    sb.append("Please Enter InflexiblePortfolio name\n");
    sb.append(stockOptions);
    sb.append("Please Enter Stock name\n");
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check when existing user creates a valid InflexiblePortfolio and wants to buy a Stock.
   *
   * @throws Exception exception
   */
  @Test
  public void userNewPortfolioBuyStock() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nAISH\n1\nQWERTY\n1\nGOOG\n\n5\n".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    sb.append("Successfully logged in AISH\n");
    sb.append(userOptionString);
    sb.append("Please Enter InflexiblePortfolio name\n");
    sb.append(stockOptions);
    sb.append("Please Enter Stock name\n");
    sb.append("Please Enter Integer Quantity\n");
    sb.append(stockOptions);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }

  /**
   * Test to check when a user creates a valid InflexiblePortfolio and buys a Stock and exits.
   * The view shows the portfolio menu once the user exits from the stock menu.
   *
   * @throws Exception exception
   */
  @Test
  public void userNewPortfolioBuyStockandExit() throws Exception {
    InputStream in = new ByteArrayInputStream("1\nAISH\n1\nPOIU\n1\nGOOG\n\n5\n2\n".getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel,
            in);
    try {
      theController.runMarketInterface();

    } catch (NoSuchElementException noSuchElementException) {
      //
    }
    sb.append("Successfully logged in AISH\n");
    sb.append(userOptionString);
    sb.append("Please Enter InflexiblePortfolio name\n");
    sb.append(stockOptions);
    sb.append("Please Enter Stock name\n");
    sb.append("Please Enter Integer Quantity\n");
    sb.append(stockOptions);
    sb.append(userOptionString);
    assertEquals(sb.toString(), new String(bytes.toByteArray()));
  }
}