package bignumber;

/**
 * This is a non-empty node in a generic list. It contains the object data
 * and the rest of the list
 */
public class GenericElementNode implements GenericListADTNode {
  private char digit;
  private GenericListADTNode rest;

  public GenericElementNode(char p, GenericListADTNode rest) {
    this.digit = p;
    this.rest = rest;
  }


  @Override
  public int length(int currentLength) {
    return this.rest.length(1 + currentLength);
  }

  /**
   * Takes the number of shifts as an argument and shifts this number to the left by that number.
   * A negative number of left-shifts will correspond to those many right shifts.
   *
   * @param shift : Number of shifts
   */
  @Override
  public GenericListADTNode shiftLeft(int shift) {
    // if number is 0 no matter how many left shift it is still 0
    if (shift == 0) {
      return this;
    }
    return new GenericElementNode('0', this.shiftLeft(shift - 1));
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
    if (shift == 0) {
      return this;
    }
    return this.rest.shiftRight(shift - 1);
  }

  private int addTwoDigits(char digit1, char digit2) {
    return Character.getNumericValue(digit1) + Character.getNumericValue(digit2);
  }

  /**
   * Takes a single digit as an argument and adds it to this number.
   *
   * @param digit digit to add.
   * @throws IllegalArgumentException if the digit passed to it is not a single non-negative digit.
   */
  @Override
  public GenericListADTNode addDigit(char digit) throws IllegalArgumentException {
    int sumOfDigit = addTwoDigits(this.digit, digit);
    if (sumOfDigit > 9) {
      return new GenericElementNode(Character.forDigit(sumOfDigit % 10, 10),
              this.rest.addDigit('1'));
    }
    return new GenericElementNode(Character.forDigit(sumOfDigit, 10), this.rest);
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
    if (position == 0) {
      return this.digit;
    }
    char test = this.rest.getDigitAt(position - 1);
    return test;
  }


  /**
   * Method to add two numbers.
   *
   * @param secondNumber Number to be added.
   * @return the sum of adding the two numbers.
   */
  /*@Override
  public GenericListADTNode add_working(GenericListADTNode secondNumber, int carry) {

    GenericListADTNode temp = this.addDigit(secondNumber.getDigitAt(0));
    if (secondNumber.toString().equals("0") && secondNumber.length(0) == 1) {
      return temp;
    }
    return new GenericElementNode(temp.getDigitAt(0),
            temp.shiftRight(1).add(secondNumber.shiftRight(1), 0));
  }
*/
  @Override
  public GenericListADTNode add(GenericListADTNode secondNumber, int carry) {

    int sumOfDigit = this.addTwoDigits(this.getDigitAt(0),
            secondNumber.getDigitAt(0)) + carry;
    return new GenericElementNode(Character.forDigit(sumOfDigit % 10, 10),
            this.rest.add(secondNumber.shiftRight(1), sumOfDigit / 10));
  }

  @Override
  public GenericListADTNode addFront(char digit) {
    return new GenericElementNode(digit, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = toStringHelp(new StringBuilder());
    return sb.reverse().toString();
  }

  @Override
  public StringBuilder toStringHelp(StringBuilder currentString) {
    return this.rest.toStringHelp(currentString.append(this.digit));
  }


  @Override
  public int compareTo(GenericListADTNode secondNumber) {
    int num = this.rest.compareTo(secondNumber.shiftRight(1));
    if (num == 0) {
      if (this.digit > secondNumber.getDigitAt(0)) {
        return 1;
      } else if (this.digit < secondNumber.getDigitAt(0)) {
        return -1;
      }
    }
    return num;
  }

  @Override
  public int hashCode() {
    return (this.digit + this.rest.hashCode()) % 10000;
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
    if (this.digit != other.getDigitAt(0)) {
      return false;
    }
    return this.rest.equals(other.shiftRight(1));
  }
}
