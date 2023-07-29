/**
 * Represents a simple US Money system.
 */
public class SimpleMoney implements Money {

  private final int dollar;
  private final int cent;

  /**
   * Constructor to create object with given dollar and cent value.
   * If the passed cent value is more than 99 we move the quotient value to dollar when divided by
   * 100 and remainder as cent
   *
   * @param dollar dollar value of the money
   * @param cent   cent value of the money
   */
  public SimpleMoney(int dollar, int cent) throws IllegalArgumentException {
    if (dollar < 0 || cent < 0) {
      throw new IllegalArgumentException("Dollar and cent value cannot be negative");
    }
    this.dollar = dollar + cent / 100;
    this.cent = cent % 100;
  }

  /**
   * A method to add two money amounts.
   *
   * @param other the money object to add this.
   * @return money object after adding.
   */
  @Override
  public Money add(Money other) throws IllegalArgumentException, ArithmeticException {
    //If null is passed as object
    if (other == null) {
      throw new IllegalArgumentException("Cannot pass null argument, need valid Object");
    }
    // if we pass Money object referencing to other inherited Money Classes (IndianSimpleMoney)
    if (!(other instanceof Money)) {
      throw new IllegalArgumentException("Need to pass object that is instance of SimpleMoney");
    }
    double totalMoneyInCent = Math.round(this.getDecimalValue() * 100) +
            Math.round(other.getDecimalValue() * 100);
    if (Integer.MAX_VALUE < ((long) totalMoneyInCent) / 100) {
      throw new ArithmeticException("Cannot add these two large amounts, system does not support.");
    }
    Money sum = new SimpleMoney(((int) totalMoneyInCent) / 100, (int) (totalMoneyInCent)
            % 100);
    return sum;
  }

  // I can make below add private and make sure addition is only done via passing object
  // This will better design as I can then implement private class to sum in different Money system

  /**
   * A method to add a money amount with another given as a separate dollar and cent value.
   *
   * @param dollar dollar value.
   * @param cents  cents value.
   * @return Money object after adding.
   */
  public Money add(int dollar, int cents) throws IllegalArgumentException {
    if (dollar < 0 || cents < 0) {
      throw new IllegalArgumentException("Dollar or cent value cannot be negative");
    }
    Money newObj = new SimpleMoney(dollar, cents);
    return this.add(newObj);
  }

  /**
   * A method that returns the decimal value of the money in the format "xx.yy".
   *
   * @return Decimal value of the object.
   */
  @Override
  public double getDecimalValue() {
    float value = this.dollar + this.cent / 100.0f;
    return Double.valueOf(String.format("%.2f", value));
  }

  /**
   * Overiding toString() function to get the dollar and cent in the format $xx.yy
   *
   * @return dollar and cent in format $xx.yy
   */
  @Override
  public String toString() {

    // Checking if cent value is more than 100 and adding it to the dollar to return string.
    // The update to dollar is temp
    float value = this.dollar + this.cent / 100.0f;
    return "$" + String.format("%.2f", value);
  }
}
