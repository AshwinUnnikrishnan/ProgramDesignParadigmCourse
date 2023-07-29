import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.NoSuchElementException;

import controller.InvestorController;
import model.StockMarketModelInterface;
import view.InvestorView;

import static org.junit.Assert.assertEquals;

/**
 * Testcase to check the controller using mock model.
 */
public class MockModelTest {

  private InvestorView theView;
  private StringBuilder log;

  private StockMarketModelInterface stockMarketModel;

  @Before
  public void setup() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    theView = new InvestorView(out);
    log = new StringBuilder();
    stockMarketModel = new MockModel(log);
  }

  /**
   * Controller Test 1 MockModel.
   * Test to check if userName given to controller by user is what controller passes to model. Need
   * to pass the name that is not there in database.
   */
  @Test
  public void addUserName() {
    String userName = "ASHWIN";
    boolean res = true;
    InputStream in = new ByteArrayInputStream(("2\n" + userName + "\n").getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel, in);
    try {
      theController.runMarketInterface();
    } catch (Exception noSuchElementException) {
      assertEquals("Input: " + userName + "Input: " + res, log.toString());
    }
  }


  /**
   * Controller Test 2 MockModel.
   * Test to check if userName given to controller by user is what it passes when existing user
   * logs in. Need to pass existing user in database.
   *
   * @throws Exception exception
   */
  @Test
  public void checkUser() throws Exception {
    String userName = "Aish";
    boolean res = true;
    InputStream in = new ByteArrayInputStream(("1\n" + userName + "\n").getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel, in);
    try {
      theController.runMarketInterface();
    } catch (NoSuchElementException noSuchElementException) {
      assertEquals("Input: " + userName + "Input: " + res + "Input: " + userName,
              log.toString()); //inputs reached the model correctly
    }
  }


  /**
   * Controller Test 3 MockModel.
   * Tests if the getEvaluationTest gets input correctly.
   *
   * @throws Exception exception
   */
  @Test
  public void getEvaluationTest() throws Exception {
    String date = "2022-11-02";
    String userName = "Aish";
    boolean res = true;
    InputStream in = new
            ByteArrayInputStream(("1\n" + "Aish\n" + "3\n" + "two\n" + date + "\n").getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel, in);
    try {
      theController.runMarketInterface();
    } catch (NoSuchElementException noSuchElementException) {
      assertEquals("Input: AishInput: " + res + "Input: " + userName + "Input: [\"T\" : "
                      + "[18.43,12,\"" + date + "\"]]Input: " + date,
              log.toString()); //inputs reached the model correctly
    }
  }


  /**
   * Controller Test 4 MockModel.
   * Test to check if the checkUserOrStock is called when we get input from user.
   *
   * @throws Exception exception
   */
  @Test
  public void checkStockFirstInput() throws Exception {
    String stockName = "T";
    String userName = "Aish";
    boolean res = false;
    InputStream in = new ByteArrayInputStream(("1\n" + "Aish\n" + "1\n" + "twoo\n1\n" + stockName
            + "\n").getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel, in);
    try {
      theController.runMarketInterface();
    } catch (NoSuchElementException noSuchElementException) {
      assertEquals("Input: AishInput: " + !res + "Input: " + userName + "Input: "
              + stockName + "Input: " + res, log.toString()); //inputs reached the model correctly
    }
  }

  /**
   * Controller Test 5 MockModel.
   * Test to check if the writeUpdated User Test gets called with proper parameters.
   * @throws IOException exception
   */
  @Test
  public void writeUpdatedUserTest() throws IOException {
    String userName = "Aish";
    String stockName = "T";
    InputStream in = new ByteArrayInputStream(("1\n" + userName + "\n" + "1\n" + "twoo\n1\n"
            + stockName + "\n10\n2\n").getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel, in);
    try {
      theController.runMarketInterface();
    } catch (NoSuchElementException noSuchElementException) {
      assertEquals("Input: " + userName + "Input: true" + "Input: " + userName
              + "Input: TInput: falseInput: TInput: 2022-11-03Input: trueInput: \"Aish\" :"
              + "{\"InflexiblePortfolio\":{\"twoo\" : {\"Stock\" : {\"T\" : "
              + "[0.0,10,\"2022-11-03\"]}},\"two\""
              + " : {\"Stock\" : {\"T\" : [18.43,12,\"2022-11-02\"]}}}}Input: false",
              log.toString());
      //inputs reached the model correctly
    }
  }

  /**
   * Controller Test 6 MockModel.
   * Test to check if the checkPriceOnDate gets the inputs it's been passed.
   * @throws IOException exception
   */
  @Test
  public void checkPriceOnDateTest() throws IOException {
    String userName = "Aish";
    String stockName = "T";
    InputStream in = new ByteArrayInputStream(("1\n" + userName + "\n" + "1\n" + "twoo\n1\n"
            + stockName + "\n10\n2\n").getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel, in);
    try {
      theController.runMarketInterface();
    } catch (NoSuchElementException noSuchElementException) {
      assertEquals("Input: " + userName + "Input: true" + "Input: " + userName
              + "Input: TInput: falseInput: TInput: " + LocalDate.now() + "Input: trueInput:"
              + " \"Aish\" :{\"InflexiblePortfolio\":{\"twoo\" : {\"Stock\" : {\"T\" : "
              + "[0.0,10,\"2022-11-03\"]}},\"two\" : {\"Stock\" : {\"T\" : [18.43,12,"
              + "\"2022-11-02\"]}}}}Input: false", log.toString());
    }
  }

  /**
   * Controller Test 7 MockModel.
   * Test to check if load from existing is getting the inputs passed by user.
   */
  @Test
  public void checkLoadFromExisting() {
    String userName = "Aish";
    boolean res = true;
    InputStream in = new ByteArrayInputStream(("1\n" + userName + "\n").getBytes());
    InvestorController theController = new InvestorController(theView, stockMarketModel, in);
    try {
      theController.runMarketInterface();
    } catch (Exception noSuchElementException) {
      assertEquals("Input: " + userName + "Input: " + res + "Input: " + userName,
              log.toString()); //inputs reached the model correctly
    }
  }
}