import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import controller.InvestorController;
import model.StockMarketModel;
import model.StockMarketModelInterface;
import view.InvestorView;
import view.InvestorViewInterface;

import static org.junit.Assert.assertEquals;

/**
 * Testcase to check the controller using mock view.
 */
public class MockViewTest {

  private StringBuilder log;

  private InvestorViewInterface stockMarketView;
  private StockMarketModelInterface stockMarketModel;

  private final String welcomeString = "Welcome to the Stock Exchange\nPress 1 : Existing User\n"
          + "Press 2 : New User\nPress 3 : Exit\n";

  @Before
  public void setup() throws IOException {
    String userNameFile = "data/userName.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat.json";
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    InvestorView theView = new InvestorView(out);
    log = new StringBuilder();
    stockMarketView = new MockView(log);
    stockMarketModel = new StockMarketModel(tickerFile, userNameFile, userDetailsDatabase);
  }

  /**
   * Test to verify if the view is called in order by the controller.
   */
  @Test
  public void printWelcomeScreen() {
    InputStream in = new ByteArrayInputStream(("1").getBytes());
    InvestorController theController =
            new InvestorController(stockMarketView, stockMarketModel, in);
    try {
      theController.runMarketInterface();
    } catch (Exception noSuchElementException) {
      assertEquals("Input: printWelcomeScreenInput: printWelcomeNewUserInput: "
              + "getStringOutput", log.toString());
    }
  }

}
