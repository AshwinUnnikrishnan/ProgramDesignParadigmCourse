package bignumber;


/**
 * This represents an empty node of the generic list implementation.
 */
public class GenericEmptyNode implements GenericListADTNode {


  @Override
  public int length(int currentLength) {
    return currentLength;
  }

  /**
   * Takes the number of shifts as an argument and shifts this number to the left by that number.
   * A negative number of left-shifts will correspond to those many right shifts.
   *
   * @param shift : Number of shifts
   */
  @Override
  public GenericListADTNode shiftLeft(int shift) {
    return new GenericElementNode('0', this);
  }

  /**
   * Takes the number of shifts as an argument and shifts this number to the right by that number.
   * The number 0 can be right-shifted any positive number of times, yielding the same number 0 .
   * A negative number of right-shifts will correspond to those many left shifts.
   *
   * @param shift : Number of shifts
   */
  @Override
  public GenericListADTNode shiftRight(int shift) {
    return new GenericElementNode('0', this);
  }

  /**
   * Takes a single digit as an argument and adds it to this number.
   *
   * @param digit digit to add.
   * @throws IllegalArgumentException if the digit passed to it is not a single non-negative digit.
   */
  @Override
  public GenericListADTNode addDigit(char digit) throws IllegalArgumentException {
    return new GenericElementNode('1', this);
  }

  /**
   * Takes a position as an argument and returns the digit at that position.
   * Positions start at 0 (rightmost digit).
   *
   * @param position position to return the digit from.
   * @return Digit at that position.
   * @throws IllegalArgumentException If passed position is invalid throws this exception.
   */
  @Override
  public char getDigitAt(int position) throws IllegalArgumentException {
    return '0';
  }


  /**
   * Method to add two numbers.
   *
   * @param secondNumber Number to be added.
   * @return the sum of adding the two numbers.
   */
  @Override
  public GenericListADTNode add(GenericListADTNode secondNumber, int carry) {
    if (carry == 0) {
      if (this.equals(secondNumber)) { // checking if second number is 0 then we need not append
        return this;
      }
      return secondNumber;
    } else {
      return (secondNumber.addDigit('1'));
    }
  }

  @Override
  public GenericListADTNode addFront(char digit) {
    return new
            GenericElementNode(digit, this);
  }

  @Override
  public String toString() {
    return "";
  }

  @Override
  public int compareTo(GenericListADTNode secondNumber) {
    if (secondNumber.toString().equals("0")) {
      return 0;
    }
    return -1;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof GenericListADTNode)) {
      return false;
    }
    GenericListADTNode other = (GenericListADTNode) o;
    return !(other.length(0) > 1 || !other.toString().equals("0"));
  }

  @Override
  public StringBuilder toStringHelp(StringBuilder currentString) {
    return currentString;
  }
}
