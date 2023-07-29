package model;

/**
 * Class to implement model of the GUI.
 */
public class Model implements IModel {
  private String input;

  /**
   * Constructor to initialise the imput of the Model.
   */
  public Model() {
    input = "";
  }

  @Override
  public void setString(String i) {
    input = i;
  }

  @Override
  public String getString() {
    return input;
  }
}