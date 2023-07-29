/**
 * Created by histravelstories.
 * This is an interface
 * Date : 11/7/22
 * Project Name : StockMarket
 */

package controller;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface which should be extended by all stockmarket controller.
 */
public interface ControllerInterface {

  /**
   * Method that is called from MVC to start the controller of a stock market.
   *
   * @throws IOException    Thrown when IO related exception happens in file write.
   * @throws ParseException Thrown when parseException occurs when parse exception raises.
   */
  void runMarketInterface() throws IOException, ParseException;
}
