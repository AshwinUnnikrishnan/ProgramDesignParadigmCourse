import org.junit.Before;
import org.junit.Test;

import bignumber.BigNumber;
import bignumber.BigNumberImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class to test Bignumber implementation.
 */
public class BigNumberImplTest {

  private BigNumber number;

  @Before
  public void setup() {
    number = new BigNumberImpl();
  }

  //Constructor
  @Test
  public void testConstructorEmpty() {
    assertEquals("0", number.toString());
  }

  @Test
  public void testStringConstructor() {
    number = new BigNumberImpl("123");
    assertEquals("123", number.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStringConstructorNegative() {
    number = new BigNumberImpl("-123");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStringConstructorRandomChars() {
    number = new BigNumberImpl("4^y7");
  }

  @Test
  public void testStringConstructorSingleDigit() {
    number = new BigNumberImpl("1");
    assertEquals("1", number.toString());
  }

  @Test
  public void testStringConstructorSingleDigitMultipleLeadingZero() {
    number = new BigNumberImpl("0065");
    assertEquals("65", number.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStringConstructorEmptyString() {
    number = new BigNumberImpl("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStringConstructorNull() {
    number = new BigNumberImpl(null);
  }

  @Test
  public void testLengthAfterConstructor() {
    assertEquals(1, number.length());
  }

  @Test
  public void testLengthSingleDigit() {
    number.shiftLeft(1);
    number.addDigit(2);
    assertEquals(1, number.length());
  }

  @Test
  public void testLengthMultiDigit() {
    number.shiftLeft(1);
    number.addDigit(2);
    number.shiftLeft(2);
    number.addDigit(2);
    assertEquals(3, number.length());
  }

  @Test
  public void testLengthShiftRightEmpty() {
    number.shiftRight(3);
    assertEquals(1, number.length());
  }

  @Test
  public void testLengthShiftRight() {
    number.shiftLeft(1);
    number.addDigit(2);
    number.shiftLeft(2);
    number.addDigit(2);
    number.shiftRight(1);
    assertEquals(2, number.length());
  }

  @Test
  public void testShiftLeftEmpty() {
    number.shiftLeft(1);
    assertEquals("0", number.toString());
  }

  @Test
  public void testShiftLeftZero() {
    number = new BigNumberImpl("123");
    number.shiftLeft(0);
    assertEquals("123", number.toString());
  }

  @Test
  public void testShiftLeftPositive() {
    number = new BigNumberImpl("123");
    number.shiftLeft(2);
    number.shiftLeft(2);

    assertEquals("1230000", number.toString());
  }

  @Test
  public void testShiftLeftNegative() {
    number = new BigNumberImpl("123");
    number.shiftLeft(-2);
    assertEquals("1", number.toString());
  }


  @Test
  public void testShiftRight() {
    number.shiftRight(2);
    assertEquals("0", number.toString());
  }


  @Test
  public void testShiftRightNumbers() {
    number = new BigNumberImpl("123");
    number.shiftRight(2);
    assertEquals("1", number.toString());
  }

  @Test
  public void testShiftRightNumbersExceed() {
    number = new BigNumberImpl("123");
    number.shiftRight(5);
    assertEquals("0", number.toString());
  }

  @Test
  public void testShiftRightNumbersZero() {
    number = new BigNumberImpl("123");
    number.shiftRight(0);
    assertEquals("123", number.toString());
  }

  @Test
  public void testShiftRightNegative() {
    number = new BigNumberImpl("123");
    number.shiftRight(-2);
    assertEquals("12300", number.toString());
  }

  @Test
  public void testAddDigitToZero() {
    number.addDigit(5);
    assertEquals("5", number.toString());
  }

  @Test
  public void testAddDigitZero() {
    number.addDigit(0);
    assertEquals("0", number.toString());
  }

  @Test
  public void testAddDigitNumber() {
    number = new BigNumberImpl("123");
    number.addDigit(5);
    assertEquals("128", number.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddDigitNumberNegative() {
    number = new BigNumberImpl("123");
    number.addDigit(-10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddDigitNumberTwoDigit() {
    number = new BigNumberImpl("123");
    number.addDigit(10);
  }

  @Test
  public void testAddDigitNumberShiftLeft() {
    number = new BigNumberImpl("123");
    number.shiftLeft(2);
    number.addDigit(5);
    assertEquals("12305", number.toString());
  }

  @Test
  public void testAddDigitNumberShiftRight() {
    number = new BigNumberImpl("123");
    number.shiftRight(2);
    number.addDigit(5);
    assertEquals("6", number.toString());
  }

  @Test
  public void testAddDigitNumberCarry() {
    number = new BigNumberImpl("123");
    number.addDigit(8);
    assertEquals("131", number.toString());
  }

  @Test
  public void testAddDigitNumberNewDigit() {
    number = new BigNumberImpl("999");
    number.addDigit(2);
    assertEquals("1001", number.toString());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testGetDigitNegative() {
    number = new BigNumberImpl("123");
    number.getDigitAt(-2);
  }

  @Test
  public void testGetDigitGreater() {
    number = new BigNumberImpl("123");
    assertEquals(0, number.getDigitAt(3));
  }


  @Test
  public void testGetDigit() {
    number = new BigNumberImpl("123");
    assertEquals(2, number.getDigitAt(1));
  }

  @Test
  public void testGetDigitZero() {
    assertEquals(0, number.getDigitAt(0));
  }

  @Test
  public void testCopy() {
    BigNumber temp = number.copy();
    assertEquals("0", temp.toString());
  }

  @Test
  public void testCopyNumber() {
    number = new BigNumberImpl("432");
    BigNumber temp = number.copy();
    assertEquals("432", temp.toString());
  }

  @Test
  public void testCompareToEmpty() {
    BigNumber newNumber = new BigNumberImpl();
    assertEquals(0, number.compareTo(newNumber));
  }


  @Test
  public void testCompareToNumber() {
    number = new BigNumberImpl("123");
    BigNumber newNumber = new BigNumberImpl("123");
    assertEquals(0, number.compareTo(newNumber));
  }

  @Test
  public void testCompareToNumberFirstGreaterSameDigit() {
    number = new BigNumberImpl("1233");
    BigNumber newNumber = new BigNumberImpl("1200");
    assertEquals(1, number.compareTo(newNumber));
  }

  @Test
  public void testCompareToNumberFirstGreaterDifferentDigit() {
    number = new BigNumberImpl("1233");
    BigNumber newNumber = new BigNumberImpl("987");
    assertEquals(1, number.compareTo(newNumber));
  }

  @Test
  public void testCompareToNumberSecondGreaterDifferentDigit() {
    number = new BigNumberImpl("12");
    BigNumber newNumber = new BigNumberImpl("9879");
    assertEquals(-1, number.compareTo(newNumber));
  }

  @Test
  public void testCompareToNumberSecondGreaterSameDigit() {
    number = new BigNumberImpl("1233");
    BigNumber newNumber = new BigNumberImpl("9879");
    assertEquals(-1, number.compareTo(newNumber));
  }


  @Test
  public void testEqualsValid() {
    number = new BigNumberImpl("123");
    BigNumber newNumber = new BigNumberImpl("123");
    assertEquals(true, number.equals(newNumber));
  }

  @Test
  public void testEqualsInvalid() {
    number = new BigNumberImpl("123");
    BigNumber newNumber = new BigNumberImpl("1223");
    assertEquals(false, number.equals(newNumber));
  }


  @Test
  public void testAddSecondNumberSmaller() {
    number = new BigNumberImpl("123");
    BigNumber newNumber = new BigNumberImpl("12");
    BigNumber result = number.add(newNumber);
    assertEquals("135", result.toString());
  }


  @Test
  public void testAddSecondNumberLargerAndCarry() {
    number = new BigNumberImpl("123");
    BigNumber newNumber = new BigNumberImpl("996");
    BigNumber result = number.add(newNumber);
    assertEquals("1119", result.toString());
  }

  @Test
  public void testAddFirstNumberLargerAndCarry() {
    number = new BigNumberImpl("123");
    BigNumber newNumber = new BigNumberImpl("996");
    BigNumber result = number.add(newNumber);
    assertEquals("1119", result.toString());
  }

  @Test
  public void testAddSecondNumberVeryLargerAndCarry() {
    number = new BigNumberImpl("123");
    BigNumber newNumber = new BigNumberImpl("999999999996");
    BigNumber result = number.add(newNumber);
    assertEquals("1000000000119", result.toString());
  }

  @Test
  public void testAddFirstNumberVeryLargerAndCarry() {
    number = new BigNumberImpl("999999999996");
    BigNumber newNumber = new BigNumberImpl("123");
    BigNumber result = number.add(newNumber);
    assertEquals("1000000000119", result.toString());
  }

  @Test
  public void testAddFirstNumberSmaller() {
    number = new BigNumberImpl("12");
    BigNumber newNumber = new BigNumberImpl("123");
    BigNumber result = number.add(newNumber);
    assertEquals("135", result.toString());
  }

  @Test
  public void testAddTwoZero() {
    number = new BigNumberImpl();
    BigNumber newNumber = new BigNumberImpl();
    BigNumber result = number.add(newNumber);
    assertEquals("0", result.toString());
  }


  @Test
  public void testConstructorNoArgument() {
    BigNumberImpl number = new BigNumberImpl();
    assertEquals("0", number.toString());
    number.shiftLeft(1);
    assertEquals("0", number.toString());
    number.addDigit(2);
    assertEquals("2", number.toString());
    number.addDigit(5);
    assertEquals("7", number.toString());
    number.addDigit(5);
    assertEquals("12", number.toString());

  }

  @Test(timeout = 5000)
  public void testAddBigNumber() {
    number = new BigNumberImpl("9".repeat(4000));
    assertEquals("9".repeat(4000), number.toString());
  }


  @Test
  public void testHashCode() {
    number = new BigNumberImpl("987");
    assertEquals(168, number.hashCode());
  }

  @Test
  public void testHashCodeReverse() {
    number = new BigNumberImpl("789");
    assertEquals(168, number.hashCode());
  }

  @Test
  public void testHashCodeDifferent() {
    number = new BigNumberImpl("123");
    assertEquals(150, number.hashCode());
  }

  @Test
  public void testTwoSimilarHash() {
    number = new BigNumberImpl("123");
    BigNumber secondNumber = new BigNumberImpl("123");
    assertEquals(number.hashCode(), secondNumber.hashCode());
  }
}