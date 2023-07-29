package model;

/**
 * Interface to implement to the GUI Model.
 */
public interface IModel {
  /**
   * Method to set a string.
   *
   * @param i String to be set.
   */
  void setString(String i);

  /**
   * Method to access the string.
   *
   * @return resultant string.
   */
  String getString();
}
