/**
 * Created by histravelstories.
 * This is an interface
 * Date : 11/13/22
 * Project Name : StockMarket
 */

package model;

import java.io.FileNotFoundException;

/**
 * Interface for any database reader. It has function to read data from a file.
 */
public interface DatabaseReader {

  /**
   * Method to read single userDetail from json if the user exists.
   *
   * @param name name of the user whose details we want to read.
   * @return returns the details of the user if it exists or returns null.
   * @throws FileNotFoundException : if file is not found throws this error.
   */
  String readUserDataBase(String name) throws FileNotFoundException;

}
