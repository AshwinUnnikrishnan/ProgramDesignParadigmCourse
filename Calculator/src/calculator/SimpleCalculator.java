/**
 * Created by histravelstories.
 * This is a class
 * Date : 9/23/22
 * Project Name : calculator.Calculator
 */

package calculator;

/**
 * Simple Calculator Class. A simple calculator takes straightforward inputs.
 * Implements the input method to take inputs.
 * It extends abstract class that has common functionalities
 * and implements the methods from inherited and extended from Interface and Abstract class.
 * Has two constructors public constructor for the Initial state of calculator and private
 * constructor to create new objects from existing state(state change).
 * Only works for whole numbers upto 32bits.
 */
public class SimpleCalculator extends AbstractCalculator {

  /**
   * Constructor to set the calculator to running state and update based on button press.
   *
   * @param updateValue Value to be updated
   */
  private SimpleCalculator(String updateValue) {
    super(updateValue);
  }

  /**
   * Constructor so that user can turn on the calculator. More like a power button.
   */
  public SimpleCalculator() {
    super();
  }


  /**
   * A method that takes a single character as its only argument. This method should return
   * a calculator.Calculator object as a result of processing the input.
   *
   * @param singleChar Single Character whole number [0-9] or operation
   * @return New calculator Object.
   */
  @Override
  public Calculator input(char singleChar) throws IllegalArgumentException {
    String currentResult = this.getResult();

    // If reset just create a new object empty

    checkInValidCharInput(singleChar);
    if (singleChar == 'C') {
      return new SimpleCalculator();
    }
    /* If length of currentResult is 0 then
     1. Digit insert
     2. = or C : Reset
     3. -*+ Throw Error TODO Check if to throw error or Exception
    */
    else if (currentResult.length() == 0) {
      if (Character.isDigit(singleChar)) {
        return new SimpleCalculator(appendCharToString("", singleChar));
      } else if (singleChar == '=') {
        return new SimpleCalculator();
      } else {
        throw new IllegalArgumentException("Cannot have an operator as first element");
      }
    }


    /* If Integer Input '2' then
     * 1. After equal to then 21=   2
     * 2. Check if digit part of second number 21+ or 21+23    21+2 or 21+232
     * 3. Part of first number
     */
    else if (Character.isDigit(singleChar)) {
      try {
        //Check if digit after = operation then only this digit remains
        if (this.result.charAt(this.result.length() - 1) == '=') {
          return new SimpleCalculator(appendCharToString("", singleChar));
        }

        // Check if this is second number, Can be done by checking if the result contains operator
        else if (currentResult.contains("*") || currentResult.contains("+") ||
                currentResult.contains("-")) {
          currentResult = appendCharToString(currentResult, singleChar);
          String[] numbers = currentResult.split("[-+*]");
          Integer.parseInt(numbers[1]); // Adding the digit to second number and
          return new SimpleCalculator(currentResult);
        }

        // it should be part of first number in that case just add it to first number digit
        else {
          Integer.parseInt(appendCharToString(currentResult, singleChar));
          return new SimpleCalculator(appendCharToString(currentResult, singleChar));
        }
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Integer Overflow when adding digit to number");
      }
    }

    /*
      Not Digit not C nor first character
      *-+=
     */
    else {
      boolean isPreviousCharOperator =
              !Character.isDigit(currentResult.charAt(currentResult.length() - 1));

      // If operators came consecutively throw error
      if (isPreviousCharOperator && !Character.isDigit(singleChar)) {
        throw new IllegalArgumentException("You cannot have another operator followed by operator");
      }
      // Operations till now could have made -ve first operand
      String sign = "";
      if (currentResult.charAt(0) == '-') {
        currentResult = currentResult.substring(1);
        sign = "-";
      }
      //Splitting the string into numbers, with operator as delimiter
      String[] numbers = currentResult.split("[-+*]");

      //Calculate operations and then return result this should be inside try catch block
      if (numbers.length == 2) {
        return getCalculator(singleChar, currentResult, sign, numbers);
      } else {
        return new SimpleCalculator(appendCharToString(sign + currentResult, singleChar));
      }
    }
  }

  /**
   * Helper function to calculate the required arithmetic operation and return.
   *
   * @param singleChar    Input character
   * @param currentResult Result till now
   * @param sign          Sign of first operand
   * @param numbers       Numbers to perform operation
   * @return Returns a Simple Calculator after operations. If overflows returns 0.
   */
  private Calculator getCalculator(char singleChar, String currentResult, String sign,
                                   String[] numbers) {
    char currentOperator = currentResult.charAt(numbers[0].length());
    try {
      return new SimpleCalculator(
          appendCharToString(calculateAndReturnResult(numbers, sign, currentOperator), singleChar));
    } catch (ArithmeticException e) {
      return new SimpleCalculator("0");
    }
  }
}
