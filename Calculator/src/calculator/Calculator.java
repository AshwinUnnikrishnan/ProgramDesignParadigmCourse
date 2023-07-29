/**
 * Created by histravelstories.
 * This is an interface
 * Date : 9/23/22
 * Project Name : calculator.Calculator
 */

package calculator;

/**
 * Interface for Calculator. All the calculator which extends this should have an implementation to
 * take an input as char and return Calculator object and getResult() that tells the current state
 * of the calculator.
 */
public interface Calculator {
  /**
   * A method that takes a single character as its only argument. This method should return
   * a calculator.Calculator object as a result of processing the input.
   *
   * @param singleChar Single Character whole number [0-9] or operation
   * @return New calculator Object.
   */
  Calculator input(char singleChar);

  /**
   * A method that does not take any arguments and returns the current "result" of the
   * calculator (i.e. the message that we would normally see on the screen) as a String object.
   *
   * @return Result of the operations.
   */
  String getResult();
}
