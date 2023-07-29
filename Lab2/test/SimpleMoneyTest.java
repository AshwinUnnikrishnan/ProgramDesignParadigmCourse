import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the SimpleMoney class.
 */
public class SimpleMoneyTest {

  /**
   * Test to check if IllegalArgumentException is being raised successfully for negative dollar and
   * cent values.
   * Tests : Constructor
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBothNegativeValue() {
    new SimpleMoney(-2, -4);
  }

  /**
   * Test to check if IllegalArgumentException is being raised successfully for negative dollar
   * value.
   * Tests : Constructor
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDollarNegativeValue() {
    new SimpleMoney(-2, 2);
  }

  /**
   * Test to check if 0 dollars and 0 cents works properly.
   * Tests : Constructor ( Created object as specified )
   */
  @Test
  public void testZeroMoney() {
    Money val = new SimpleMoney(0, 0);
    double expectedOutput = 0.0d;
    assertEquals(expectedOutput, val.getDecimalValue(), 0);
  }

  /**
   * Test to check if constructor created object as specified.
   * Tests : Constructor ( Created object as specified )
   */
  @Test
  public void testToCheckConstructorCreatedWithSpecifiedValues() {
    Money val = new SimpleMoney(2, 30);
    double expectedOutput = 2.30d;
    assertEquals(expectedOutput, val.getDecimalValue(), 0);
  }

  /**
   * Test to check if constructor created object as specified and the cent value is rounded to two
   * places and extra dollars are added to dollar.
   * Tests : Constructor ( Created object after destructing the cent to two digits )
   */
  @Test
  public void testToCheckConstructorDestructionTwoDigitCents() {
    Money val = new SimpleMoney(3, 108);
    double expectedOutput = 4.08d;
    assertEquals(expectedOutput, val.getDecimalValue(), 0);
  }

  /**
   * Test to check if constructor created object as specified and the cent value is rounded to two
   * places and extra dollars are added to dollar.
   * Tests : Constructor
   */
  @Test
  public void testDoubleDigitDollarCent() {
    Money val = new SimpleMoney(343, 108);
    double expectedOutput = 344.08d;
    assertEquals(expectedOutput, val.getDecimalValue(), 0);
  }

  /**
   * Test to toString method working when cent<100.
   * Test : toString()
   */
  @Test
  public void testToStringMethod() {
    Money val = new SimpleMoney(1, 8);
    String expectedOutput = "$1.08";
    assertEquals(expectedOutput, val.toString());
  }

  /**
   * Test to toString method working when cent>100.
   * Test : toString()
   */
  @Test
  public void testToStringMethodCentMoreThanDollar() {
    Money val = new SimpleMoney(1, 80);
    String expectedOutput = "$1.80";
    assertEquals(expectedOutput, val.toString());
  }

  /**
   * Test to toString method with trailing 0 in cents.
   * Test : toString()
   */
  @Test
  public void testToStringTrailingZeroCent() {
    Money val = new SimpleMoney(110, 8);
    String expectedOutput = "$110.08";
    assertEquals(expectedOutput, val.toString());
  }


  /**
   * Test to check if add method by passing Money object works.
   * Test : add(Money)
   */
  @Test
  public void testAddingMoneyWithObject() {
    Money val1 = new SimpleMoney(0, 30);
    Money val2 = new SimpleMoney(10, 0);
    Money sum = val2.add(val1);
    String expectedOutput = "$10.30";
    assertEquals(expectedOutput, sum.toString());
  }

  /**
   * Test to check if add method by passing Money object works, when cents are single digits and
   * result is also single digit.
   * Test : add(Money)
   */
  @Test
  public void testAddingMoneyWithObjectSingleDigitResult() {
    Money val1 = new SimpleMoney(0, 4);
    Money val2 = new SimpleMoney(10, 5);
    Money sum = val2.add(val1);
    String expectedOutput = "$10.09";
    assertEquals(expectedOutput, sum.toString());
  }

  /**
   * Test to check if add method by passing Money object works, when cents are single digits and
   * result is two digit cent.
   * Test : add(Money)
   */
  @Test
  public void testAddingMoneyWithObjectTwoDigitResultFromSingleDigits() {
    Money val1 = new SimpleMoney(0, 5);
    Money val2 = new SimpleMoney(10, 5);
    Money sum = val2.add(val1);
    String expectedOutput = "$10.10";
    assertEquals(expectedOutput, sum.toString());
  }

  /**
   * Test to check if add method by passing Money object works, when cents are single digits and
   * result cent overflows to digit.
   * Test : add(Money)
   */
  @Test
  public void testAddingMoneyWithObjectTwoDigitResultFromSingleDigitsOverflowCent() {
    Money val1 = new SimpleMoney(0, 96);
    Money val2 = new SimpleMoney(10, 5);
    Money sum = val2.add(val1);
    String expectedOutput = "$11.01";
    assertEquals(expectedOutput, sum.toString());
  }

  /**
   * Test to check if add method throws exception when dollar exceeds int range.
   * Test : add(Money)
   */
  @Test(expected = ArithmeticException.class)
  public void testAddOverFlow() {
    Money val1 = new SimpleMoney(2147483647, 96);
    Money val2 = new SimpleMoney(2147483647, 5);
    val2.add(val1);
  }

  /* Can uncomment when we have more implementations of Money
  @Test(expected = IllegalArgumentException.class)
  public void testAnotherMoneyImplementClass(){
    Money val1 = new SimpleMoney(123,2);
    Money val2 = new IndianMoney(12,3);
    val1.add(val2); //Will raise Illegal argument as val2 is instanceof IndianMoney
  }
  */

  /**
   * Test to check if add method by passing Money object works.
   * Test : add(dollar, cent)
   */
  @Test
  public void testAddingMoneyDollarCent() {
    Money val2 = new SimpleMoney(10, 245);
    Money sum = val2.add(0, 30);
    String expectedOutput = "$12.75";
    assertEquals(expectedOutput, sum.toString());
  }

  /**
   * Test to check if add method by passing Money object works.
   * Test : add(dollar, cent)
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMoneyNegative() {
    Money val2 = new SimpleMoney(10, 245);
    val2.add(-10, 30);
  }

  /**
   * Fuzzy Testing for testing Rounding function when divided and multiplied by 100.
   * More for checking if round works to get back value when we divide and multiply by 100.
   */
  @Test
  public void testRandomFuzzyTestingForInt() {
    int maxVal = 99;
    int minVal = 0;
    Random r = new Random();
    r.setSeed(15678);
    for (int i = 0; i < 1500; i++) {
      double val = r.nextInt(maxVal - minVal + 1) + minVal;
      assertEquals(val, Math.round((val / 100.0d) * 100), 0);
    }

  }

  /**
   * Fuzzy test to check decimal value conversions in getDecimal method.
   * Generate random values for dollar and cent between 0 and 99, combine this using string combine,
   * check if getDecimal returns same value.
   * Test : getDecimal()
   */
  @Test
  public void testGetDecimalFuzzy() {
    int maxVal = 99;
    int minVal = 0;
    Random r = new Random();
    r.setSeed(12313);
    for (int i = 0; i < 3000; i++) {
      int dollar = r.nextInt(maxVal - minVal + 1) + minVal;
      int cent = r.nextInt(maxVal - minVal + 1) + minVal;
      Money val = new SimpleMoney(dollar, cent);
      // Concatenating the integers to form decimal value.
      double expectedOutput = Double.parseDouble(dollar + "." + cent);
      if (cent < 10) {
        expectedOutput = Double.parseDouble(dollar + ".0" + cent);
      }
      assertEquals(expectedOutput, val.getDecimalValue(), 0);
    }
  }


  /**
   * Fuzzy test to check add() method.
   * Generates random number between [0,49] lower bound is such that we can add two values and get
   * value in two digits, upperbound is such that adding two values will be less than 100, inorder
   * to make sure we don't have to handle cent value overflow during testing.
   * We combine two numbers together so dollar and cent would always be in range [10,99] and then
   * concatenate it to string.
   */
  @Test
  public void testAddMethodFuzzy() {
    int maxVal = 99;
    int minVal = 0;
    Random r = new Random();
    r.setSeed(12313);
    for (int i = 0; i < 1500; i++) {
      int dollarVal1 = r.nextInt(maxVal - minVal + 1) + minVal;
      int centVal1 = r.nextInt(maxVal - minVal + 1) + minVal;
      int dollarVal2 = r.nextInt(maxVal - minVal + 1) + minVal;
      int centVal2 = r.nextInt(maxVal - minVal + 1) + minVal;

      Money val = new SimpleMoney(dollarVal1, centVal1);
      val = val.add(dollarVal2, centVal2);
      // Adding individual dollar together and cent together and then concatenating the integers to
      // form decimal value.
      String dec;
      if ((centVal1 + centVal2) % 100 < 10) {
        dec = ".0";
      } else {
        dec = ".";
      }
      double expectedOutput = Double.parseDouble((dollarVal1 + dollarVal2
              + (centVal1 + centVal2) / 100) + dec + ((centVal1 + centVal2) % 100));
      // as we don't have a method or access to get dollar or cent separately from object we compare
      // results of getDecimalValue() by taking leap of faith that getDecimalValue() works
      // correctly.
      assertEquals(expectedOutput, val.getDecimalValue(), 0);
    }
  }

  /**
   * Fuzzy test to check tostring() method.
   * Generates random number between [0,49] lower bound is such that we can add two values and get
   * value in two digits, upperbound is such that adding two values will be less than 100, inorder
   * to make sure we don't have to handle cent value overflow during testing.
   * We combine two numbers together so dollar and cent would always be in range [10,99] and then
   * concatenate it to string.
   */
  @Test
  public void testAddMethodFuzzyToString() {
    int maxVal = 49;
    int minVal = 0;
    Random r = new Random();
    r.setSeed(12313);
    for (int i = 0; i < 1500; i++) {
      int dollarVal1 = r.nextInt(maxVal - minVal + 1) + minVal;
      int centVal1 = r.nextInt(maxVal - minVal + 1) + minVal;
      int dollarVal2 = r.nextInt(maxVal - minVal + 1) + minVal;
      int centVal2 = r.nextInt(maxVal - minVal + 1) + minVal;

      Money val = new SimpleMoney(dollarVal1, centVal1);
      Money sum = val.add(dollarVal2, centVal2);
      // Adding individual dollar together and cent together and then concatenating the integers to
      // form decimal value.
      String dec;
      if ((centVal1 + centVal2) < 10) {
        dec = ".0";
      } else {
        dec = ".";
      }
      String expectedOutput = "$" + (dollarVal1 + dollarVal2) + dec
              + (centVal1 + centVal2);
      assertEquals(expectedOutput, sum.toString());
    }
  }

}
