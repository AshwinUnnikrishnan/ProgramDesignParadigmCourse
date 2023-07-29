/**
 * Created by histravelstories.
 * This is a class
 * Date : 9/23/22
 * Project Name : calculator.Calculator
 */

package calculator;

/**
 * Smart Calculator Class. A smart calculator accepts inputs like a normal calculator.
 * Implements the input method to take inputs.
 * It extends abstract class that has common functionalities
 * and implements the methods from inherited and extended from Interface and Abstract class.
 * Has two constructors public constructor for the Initial state of calculator and private
 * constructor to create new objects from existing state(state change).
 * Only works for whole numbers upto 32bits.
 */
public class SmartCalculator extends AbstractCalculator {

  // In smart calculator it knows the previousOperator and the previous Operand
  // Stores the previous operand
  // Stores previous operator
  private final String secondOperand;

  private final char previousOperator;


  /**
   * Constructor to create object during operations, takes in operation and operand along with
   * current state.
   *
   * @param currentState     Current calculator output.
   * @param previousOperator Last performed operation.
   * @param operand          Last operand.
   */
  private SmartCalculator(String currentState, char previousOperator, String operand) {
    super(currentState);
    this.previousOperator = previousOperator;
    this.secondOperand = operand;
  }

  /**
   * Constructor to create initial state of the Calculator. By default, the previous operator is ' '
   * and second operand is "".
   */
  public SmartCalculator() {
    super();
    this.previousOperator = ' ';
    this.secondOperand = "";
  }

  /**
   * Getter to get the previous operator.
   *
   * @return the previous operator
   */
  private char getPreviousOperator() {
    return this.previousOperator;
  }

  /**
   * Getter to get the second operand.
   *
   * @return the second operand
   */
  private String getSecondOperand() {
    return this.secondOperand;
  }

  /**
   * A method that takes a single character as its only argument. This method should return
   * a calculator.Calculator object as a result of processing the input.
   *
   * @param singleChar Single Character whole number [0-9] or operation
   * @return New calculator Object.
   */
  @Override
  public Calculator input(char singleChar) {
    String currentResult = this.getResult();

    // If reset just create a new object empty
    checkInValidCharInput(singleChar);

    if (singleChar == 'C') {
      return new SmartCalculator();
    }

    /* If length of currentResult is 0 then
     1. Digit insert
     2. = or C : Reset
     3. -*+ Throw Error
    */
    else if (currentResult.length() == 0) {
      //return checkFirstCharacterValid(this, singleChar);
      if (Character.isDigit(singleChar)) {
        return new SmartCalculator(appendCharToString("", singleChar),
                getPreviousOperator(), getSecondOperand());
      } else if (singleChar == '=' || singleChar == '+') {
        return new SmartCalculator();
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
          return new SmartCalculator(appendCharToString("", singleChar),
                  getPreviousOperator(), getSecondOperand());
        }

        // Check if this is second number, Can be done by checking if the result contains operator
        else if (currentResult.contains("*") || currentResult.contains("+") ||
                currentResult.contains("-")) {
          currentResult = appendCharToString(currentResult, singleChar);
          String[] numbers = currentResult.split("[-+*]");
          Integer.parseInt(numbers[1]);
          // Adding the digit to second number and checking if it overflows
          // Will go to next step if it doesn't overflow
          return new SmartCalculator(currentResult, getPreviousOperator(), getSecondOperand());
        }

        // it should be part of first number in that case just add it to first number digit
        else {
          Integer.parseInt(appendCharToString(currentResult, singleChar));
          return new SmartCalculator(appendCharToString(currentResult, singleChar),
                  getPreviousOperator(), getSecondOperand());
        }
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Integer Overflow");
      }
    }

    /*
      Not Digit not C nor first character
      *-+=
     */
    else {
      String sign = "";
      if (currentResult.charAt(0) == '-') {
        currentResult = currentResult.substring(1);
        sign = "-";
      }
      String[] numbers = currentResult.split("[-+*]");
      char previousCharacter = this.result.charAt(this.result.length() - 1);
      //Previous 25 incoming +/=/-/*  Output 25+/25*/25=
      if (Character.isDigit(previousCharacter)) {
        if (numbers.length == 1) {
          return new SmartCalculator(appendCharToString(sign + currentResult, singleChar)
                  , getPreviousOperator(), getSecondOperand());
        } else {
          return getCalculator(singleChar, currentResult, sign, numbers);
        }
      }

      // Incoming is *-+ and previous = or not =
      else if (singleChar != '=') {
        //Previous 25= incoming +/-/*  Output 25+/25*

        int lengthBias = 1;
        // Below checks if result has = or not. If it has then we reset bias to 0 as currentString
        // would already been truncated
        if (this.result.length() != this.getResult().length()) {
          lengthBias = 0;
        }
        return new SmartCalculator(appendCharToString(sign +
                currentResult.substring(0, currentResult.length() - lengthBias), singleChar),
                getPreviousOperator(), getSecondOperand());
      }
      else {
        return getCalculator(singleChar, currentResult, sign, numbers);
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
    String secondNumber;
    String previousOperator;
    if (numbers.length == 1) {
      //Need to take second operand from storedValue or repeat as first
      secondNumber = numbers[0];
      previousOperator = "";

      if (getPreviousOperator() != ' ') {
        secondNumber = getSecondOperand();
        previousOperator = "" + getPreviousOperator();
      }
      // == += comes here += should go somewhere correct
      if (previousOperator.equals("") && this.result.contains("=")) {
        // numbers 1 and previous operators none means multiple equal to
        //if after operations also the previousOperator is ' ' means no operations performed till
        // now
        return new SmartCalculator(appendCharToString(currentResult, singleChar),
                getPreviousOperator(), getSecondOperand());
      }
      currentResult = currentResult + previousOperator + secondNumber;
      numbers = currentResult.split("[-+*]");
    }
    char currentOperator = currentResult.charAt(numbers[0].length());
    try {
      return new SmartCalculator(
            appendCharToString(calculateAndReturnResult(numbers, sign, currentOperator), singleChar)
              , currentOperator, numbers[1]);
    } catch (ArithmeticException e) {
      return new SmartCalculator("0", getPreviousOperator(), numbers[1]);
    }
  }
}
