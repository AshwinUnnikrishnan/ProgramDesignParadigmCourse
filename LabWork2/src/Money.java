/**
 * Interface to represent an amount of US money.
 */
public interface Money {
  /**
   * A method to add two money amounts.
   *
   * @param other the money object to add this.
   * @return money object after adding.
   */
  Money add(Money other);


  /**
   * A method to add a money amount with another given as a separate dollar and cent value.
   *
   * @param dollar dollar value.
   * @param cents  cents value.
   * @return Money object after adding.
   */
  // Removing the below from the interface as we can represent other systems using the same
  // interface.
  // As SimpleMoney is not the only implementation of this we remove it and make use of
  // Money add(Money) - by passing an object when SimpleMoney Calls with dollar and cent
  Money add(int dollar, int cents) throws IllegalArgumentException;

  /**
   * A method that returns the decimal value of the money in the format "xx.yy".
   *
   * @return Decimal value of the object.
   */
  double getDecimalValue();
}
