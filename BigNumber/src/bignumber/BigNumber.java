package bignumber;

/**
 * This interface represents a big number. It has operations like shifting digit to left, right,
 * adding a digit, adding a number.
 */
public interface BigNumber extends Comparable<BigNumber> {

  /**
   * To find the length of the big number.
   *
   * @return number of digits in this number.
   */
  int length();

  /**
   * Takes the number of shifts as an argument and shifts this number to the left that many times.
   * A negative number of left-shifts will correspond to those many right shifts.
   *
   * @param shift : Number of shifts
   */
  void shiftLeft(int shift);  // Need to take care when negative

  /**
   * Takes the number of shifts as an argument and shifts this number to the right by that number.
   * The number 0 can be right-shifted any positive number of times, yielding the same number 0 .
   * A negative number of right-shifts will correspond to those many left shifts.
   *
   * @param shift : Number of shifts
   */
  void shiftRight(int shift);  // Need to take care when negative

  /**
   * Takes a single digit as an argument and adds it to this number.
   *
   * @param digit digit to add.
   * @throws IllegalArgumentException if the digit passed to it is not a single non-negative digit.
   */
  void addDigit(int digit) throws IllegalArgumentException;


  /**
   * Takes a position as an argument and returns the digit at that position.
   * Positions start at 0 (rightmost digit).
   *
   * @param position position to return the digit from.
   * @return Digit at that position.
   * @throws IllegalArgumentException If passed position is invalid throws this exception.
   */
  int getDigitAt(int position) throws IllegalArgumentException;

  /**
   * Method to create an identical and independent copy of the number.
   *
   * @return identical and independent copy of this number.
   */
  BigNumber copy();

  /**
   * Method to add two numbers.
   *
   * @param secondNumber Number to be added.
   * @return the sum of adding the two numbers.
   */
  BigNumber add(BigNumber secondNumber);

}
