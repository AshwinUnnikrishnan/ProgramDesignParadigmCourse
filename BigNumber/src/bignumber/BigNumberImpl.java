package bignumber;


/**
 * This is the implementation of a Bignumber Interface, it is a bigNumber Linked list which has
 * single digit in each node. Stores number in little indian form.
 */
public class BigNumberImpl implements BigNumber {
  private GenericListADTNode head;

  /**
   * Constructor to initialize a new bignumberimpl when we have reference to least significant
   * digit.
   *
   * @param head new head.
   */
  private BigNumberImpl(GenericListADTNode head) {
    this.head = head;
  }

  /**
   * Constructor to create a bignumber when nothing is passed. It creates a 0 number.
   */
  public BigNumberImpl() {
    head = new GenericEmptyNode();
    this.head = head.addFront('0');
  }

  /**
   * Constructor to construct a Bignumber based on the string passed to it.
   *
   * @param numberString string to create a bignumber
   * @throws IllegalArgumentException If the string contains invalid characters throws error.
   */
  public BigNumberImpl(String numberString) throws IllegalArgumentException {
    // checking for null and empty string
    if (numberString == null || numberString.equals("")) {
      throw new IllegalArgumentException("Empty string is passed");
    }
    // checking for non digit characters
    int stringLength = numberString.length();
    for (int i = 0; i < stringLength; i++) {
      if (!Character.isDigit(numberString.charAt(i))) {
        throw new IllegalArgumentException("String contains non digit terms");
      }
    }

    this.head = new GenericEmptyNode();
    // skipping the initial 0 digits that means nothing in a number
    int i = 0;
    while (i < stringLength && numberString.charAt(i) == '0') {
      i++;
    }

    // if while going through 0's we reach the end then we create a 0 bignumber and return
    if (i == stringLength) {
      this.head = this.head.addFront('0');
      return;
    }

    // else with remaining numbers keep putting it to front
    for (; i < stringLength; i++) {
      this.head = this.head.addFront(numberString.charAt(i));
    }
  }


  @Override
  public int length() {
    return this.head.length(0);
  }

  /**
   * Takes the number of shifts as an argument and shifts this number to the left by that number.
   * A negative number of left-shifts will correspond to those many right shifts.
   *
   * @param shift : Number of shifts
   */
  @Override
  public void shiftLeft(int shift) {
    if (!this.equals(new BigNumberImpl())) {
      commonShift(Math.abs(shift), (shift < 0) ? false : true);
    }
  }

  /**
   * Method so that we can call correct shift based on the sign of shift value.
   *
   * @param shift number of shifts
   * @param flag  which shift to perform
   */
  private void commonShift(int shift, boolean flag) {
    if (flag) {
      while (shift > 0) {
        this.head = new GenericElementNode('0', this.head);
        shift--;
      }
    } else {
      this.head = this.head.shiftRight(shift);
    }

  }

  /**
   * Takes the number of shifts as an argument and shifts this number to the right by that number.
   * The number 0 can be right-shifted any positive number of times, yielding the same number 0 .
   * A negative number of right-shifts will correspond to those many left shifts.
   *
   * @param shift : Number of shifts
   */
  @Override
  public void shiftRight(int shift) {
    commonShift(Math.abs(shift), (shift > 0) ? false : true);
  }

  /**
   * Takes a single digit as an argument and adds it to this number.
   *
   * @param digit digit to add.
   * @throws IllegalArgumentException if the digit passed to it is not a single non-negative digit.
   */
  @Override
  public void addDigit(int digit) throws IllegalArgumentException {
    if (digit > 9 || digit < 0) {
      throw new IllegalArgumentException("Need to pass single positive digit");
    }
    this.head = this.head.addDigit(Character.forDigit(digit, 10));
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
  public int getDigitAt(int position) throws IllegalArgumentException {
    if (position < 0) {
      throw new IllegalArgumentException("Invalid Position");
    }
    return Character.getNumericValue(this.head.getDigitAt(position));
  }

  /**
   * Method to create an identical and independent copy of the number.
   *
   * @return identical and independent copy of this number.
   */
  @Override
  public BigNumber copy() {
    return new BigNumberImpl(this.toString());
  }


  @Override
  public BigNumber add(BigNumber secondNumber) {
    if (secondNumber instanceof BigNumberImpl) {
      if (this.length() < secondNumber.length()) { // complexity of adding is that of shortest
        return new BigNumberImpl(this.head.add(((BigNumberImpl) secondNumber).head, 0));
      }
      return new BigNumberImpl(((BigNumberImpl) secondNumber).head.add(this.head, 0));
    }
    // returns same first number if the second number is not compatible
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof BigNumberImpl)) {
      return false;
    }
    BigNumberImpl other = (BigNumberImpl) o;
    return this.head.equals(other.head);
  }

  @Override
  public int hashCode() {
    return this.head.hashCode();
  }

  @Override
  public int compareTo(BigNumber secondNumber) {
    if (!(secondNumber instanceof BigNumberImpl)) {
      throw new RuntimeException("Passed data is not similar");
    }
    return this.head.compareTo(((BigNumberImpl) secondNumber).head);
  }

  @Override
  public String toString() {
    return this.head.toString();
  }
}

