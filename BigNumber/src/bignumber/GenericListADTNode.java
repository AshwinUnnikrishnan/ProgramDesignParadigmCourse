package bignumber;


/**
 * This generic interface represents all the operations to be supported by a
 * list of digits.
 */
public interface GenericListADTNode extends Comparable<GenericListADTNode> {

  int length(int currentLength);

  /**
   * Takes the number of shifts as an argument and shifts this number to the left by that number.
   * A negative number of left-shifts will correspond to those many right shifts.
   *
   * @param shift : Number of shifts
   */
  GenericListADTNode shiftLeft(int shift);  // Need to take care when negative

  /**
   * Takes the number of shifts as an argument and shifts this number to the right by that number.
   * The number 0 can be right-shifted any positive number of times, yielding the same number 0 .
   * A negative number of right-shifts will correspond to those many left shifts.
   *
   * @param shift : Number of shifts
   */
  GenericListADTNode shiftRight(int shift);  // Need to take care when negative

  /**
   * Takes a single digit as an argument and adds it to this number.
   *
   * @param digit digit to add.
   * @throws IllegalArgumentException if the digit passed to it is not a single non-negative digit.
   */
  GenericListADTNode addDigit(char digit) throws IllegalArgumentException;//Can we use byte


  /**
   * Takes a position as an argument and returns the digit at that position.
   * Positions start at 0 (rightmost digit).
   *
   * @param position position to return the digit from.
   * @return Digit at that position.
   * @throws IllegalArgumentException If passed position is invalid throws this exception.
   */
  char getDigitAt(int position) throws IllegalArgumentException;

  /**
   * Method to add two numbers.
   *
   * @param secondNumber Number to be added.
   * @return the sum of adding the two numbers.
   */
  GenericListADTNode add(GenericListADTNode secondNumber, int carry);

  GenericListADTNode addFront(char digit);

  StringBuilder toStringHelp(StringBuilder acc);
}
