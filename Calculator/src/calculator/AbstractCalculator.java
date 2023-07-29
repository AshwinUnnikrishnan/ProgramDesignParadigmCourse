/**
 * Created by histravelstories.
 * This is a class
 * Date : 9/30/22
 * Project Name : Calculator
 */

package calculator;

/**
 * Abstract base class for implementations of {@link Calculator}. This class
 * contains all the method definitions that are common to the concrete
 * implementations of the {@link Calculator} interface. A new implementation of
 * the interface has the option of extending this class, or directly
 * implementing all the methods.
 * String result stores the current state of the calculator.
 */
abstract class AbstractCalculator implements Calculator {

  protected String result;

  /**
   * Constructor for when we need to start the calculator, so does not take any arguments.
   */
  protected AbstractCalculator() {
    //TODO check if this can be combined with below constructor by passing "" as input to this from
    // simple
    this.result = "";
  }

  /**
   * Method to check if the input is one among digit, operator /*- or = or C.
   *
   * @param input character to check
   * @return True if invalid, False if not
   */
  protected void checkInValidCharInput(char input) throws IllegalArgumentException {
    if (!(Character.isDigit(input) || "+-*=C".contains("" + input))) {
      throw new IllegalArgumentException((
              new StringBuilder("Cannot have " + input + "as an input")).toString());
    }
  }

  /**
   * Constructor to set the current status of the calculator after operations.
   *
   * @param updateResult current calculator screen
   */
  protected AbstractCalculator(String updateResult) {
    this.result = updateResult;
  }

  /**
   * A method that does not take any arguments and returns the current "result" of the
   * calculator (i.e. the message that we would normally see on the screen) as a String object.
   *
   * @return Result of the operations.
   */
  @Override
  public String getResult() {
    if (this.result.contains("=")) {
      // if Equal to is present in result then remove it and send it as result.
      // Storing equal to so that when we can differentiate with previous operation or part of
      // operand
      return this.result.split("=")[0];
    }
    return this.result;
  }

  /**
   * Method to calculate the opration on two numbers and returns a string of the result.
   *
   * @param numbers   Contains the two operands
   * @param sign      Sign of the first operand
   * @param operation Operation to be performed
   * @return the result of the operation as a string
   * @throws ArithmeticException thrown when there is an overflow, the classes extending the
   *                             abstract class should handle it.
   */
  protected String calculateAndReturnResult(String[] numbers, String sign, char operation)
          throws ArithmeticException {
    if (operation == '+') {
      return (Integer.toString(Math.addExact(Integer.parseInt(sign + numbers[0]),
              Integer.parseInt(numbers[1]))));
    } else if (operation == '-') {
      return (Integer.toString(Math.subtractExact(Integer.parseInt(sign + numbers[0]),
              Integer.parseInt(numbers[1]))));
    } else {
      return (Integer.toString(Math.multiplyExact(Integer.parseInt(sign + numbers[0]),
              Integer.parseInt(numbers[1]))));
    }

  }

  /**
   * Helper function to append a char to string.
   *
   * @param tempRes    String to append the char to
   * @param singleChar char to append
   * @return Character appended string
   */
  protected String appendCharToString(String tempRes, char singleChar) {
    return tempRes + singleChar;
  }

  /*protected Calculator checkFirstCharacterValid(Calculator obj, char singleChar){
    if (Character.isDigit(singleChar)) {
      return new SmartCalculator(appendCharToString("", singleChar),
              getPreviousOperator(), getSecondOperand());
    } else if (singleChar == '=' || singleChar == '+') {)
      return new SmartCalculator();
    } else {
      throw new IllegalArgumentException("Cannot have an operator as first element");
    }
  }*/
}
