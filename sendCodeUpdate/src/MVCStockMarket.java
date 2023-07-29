import java.io.File;
import java.io.IOException;

import controller.ControllerGUI;
import controller.InvestorController;
import model.StockMarketModel;
import model.StockMarketModelInterface;
import view.IView;
import view.InvestorViewDollarCost;
import view.InvestorViewInterface;
import view.JFrameView;


/**
 * Starting point of our stock market code. It checks if the tickerName.txt is there, if not
 * exits and shows to contact stockMarket.
 * Else it checks if userName is present or not and then creates  userName.txt and jsonFormat.json
 */
public class MVCStockMarket {

  /**
   * method that gets called when we start the stockMarket.
   *
   * @param args arguments that are to be passed to start the market.
   */
  public static void main(String[] args) throws IOException {
    String userNameFile = "data/userName1.txt";
    String tickerFile = "data/tickerName.txt";
    String userDetailsDatabase = "data/jsonFormat1.json";
    if (args[0].equals("0")) {
      InvestorViewInterface theView = new InvestorViewDollarCost(System.out);
      if (!(new File(tickerFile)).exists()) {
        theView.printOfflineMessage();
        return;
      }
      try {
        StockMarketModelInterface stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
                userDetailsDatabase);
        InvestorController theController = new InvestorController(theView, stockMarketModel,
                System.in);
        theController.runMarketInterface();
      } catch (IOException e) {
        theView.printCorruptFiles(e.toString());
      }
    } else {
      StockMarketModelInterface stockMarketModel = new StockMarketModel(tickerFile, userNameFile,
              userDetailsDatabase);
      IView view = new JFrameView("StockMarketModel");

      ControllerGUI controller = new ControllerGUI(stockMarketModel, view);
      controller.setView();
    }
  }

}
