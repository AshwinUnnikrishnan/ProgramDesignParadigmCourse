import org.junit.Test;

import java.util.Random;

import vehicle.ManualTransmission;
import vehicle.RegularManualTransmission;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the RegularManualTransmission class.
 */
public class RegularManualTransmissionTest {
  private ManualTransmission speedCheck;

  /*
  Testing format
    1. Status Testing ( Working of the Transmission )
    2. Object Creation Testing
    3. Argument Input Constraint Testing
    4.
   */

  /**
   * Test to check if speed changed successful increase and Ok Everything is Ok.
   * Test 1 (OK: everything is OK.)
   * Status 1
   */
  @Test
  public void testSpeedChangeNormalIncrease() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 10);
    String expectedOutput = "OK: everything is OK.";
    for (int i = 0; i < 5; i++) {
      speedCheck = speedCheck.increaseSpeed();
      assertEquals(expectedOutput, speedCheck.getStatus());
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseGear();
    }
  }

  /**
   * Test to check if speed changed successful decrease and Ok Everything is Ok.
   * Test 2 (OK: everything is OK.)
   * Status 1
   */
  @Test
  public void testSpeedChangeNormalDecrease() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 10);
    String expectedOutput = "OK: everything is OK.";
    for (int i = 0; i < 5; i++) {
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseGear();
    }
    speedCheck = speedCheck.increaseSpeed();
    for (int i = 0; i < 5; i++) {
      speedCheck = speedCheck.decreaseSpeed();
      assertEquals(expectedOutput, speedCheck.getStatus());
      speedCheck = speedCheck.decreaseSpeed();
      speedCheck = speedCheck.decreaseGear();
    }
  }

  /**
   * Test to check if gear increases successful and Ok Everything is Ok.
   * Test 3 (OK: everything is OK.)
   * Status 1
   */
  @Test
  public void testGearChangeNormalIncrease() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 10);
    String expectedOutput = "OK: everything is OK.";
    for (int i = 0; i < 4; i++) {
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseGear();
      assertEquals(expectedOutput, speedCheck.getStatus());
    }
  }

  /**
   * Test to check if gear decreases successful and Ok Everything is Ok.
   * Test 4 (OK: everything is OK.)
   * Status 1
   */
  @Test
  public void testGearChangeNormalDecrease() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 10);
    String expectedOutput = "OK: everything is OK.";
    for (int i = 0; i < 4; i++) {
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseGear();
    }
    speedCheck = speedCheck.increaseSpeed();
    speedCheck = speedCheck.increaseSpeed();
    for (int i = 0; i < 4; i++) {
      speedCheck = speedCheck.decreaseSpeed();
      speedCheck = speedCheck.decreaseSpeed();
      speedCheck = speedCheck.decreaseGear();
      assertEquals(expectedOutput, speedCheck.getStatus());
    }
  }

  /**
   * Test to check if speed changed successful increase and Ok Everything is Ok.
   * Test 5 (OK: everything is OK.)
   * Status 1
   */
  @Test
  public void testCreateObjectOkayStatus() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 10);
    String expectedOutput = "OK: everything is OK.";
    assertEquals(expectedOutput, speedCheck.getStatus());
  }

  /**
   * Test to check if speed changed successful increase.
   * Test 6 (OK: you may increase the gear.)
   * Status 2
   */
  @Test
  public void testSpeedChangeReachNextGearRange() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 10);
    String expectedOutput = "OK: you may increase the gear.";
    for (int i = 0; i < 4; i++) {
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseSpeed();
      assertEquals(expectedOutput, speedCheck.getStatus());
      speedCheck = speedCheck.increaseGear();
    }
  }

  /**
   * Test to check if speed changed successful decrease.
   * Test 7 (OK: you may decrease the gear.)
   * Status 3
   */
  @Test
  public void testSpeedChangeReachPreviousGearRange() {
    //(0, 2, 2, 4, 4, 6, 6, 8, 8, 10);
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 10);
    String expectedOutput = "OK: you may decrease the gear.";
    for (int i = 0; i < 4; i++) {
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseGear();
    }
    speedCheck = speedCheck.increaseSpeed();
    speedCheck = speedCheck.increaseSpeed();
    for (int i = 0; i < 4; i++) {
      speedCheck = speedCheck.decreaseSpeed();
      speedCheck = speedCheck.decreaseSpeed();
      assertEquals(expectedOutput, speedCheck.getStatus());
      speedCheck = speedCheck.decreaseGear();
    }
  }

  /**
   * Test to check if speed change cannot happen without increasing the gear.
   * Test 8 (Cannot increase speed, increase gear first.)
   * Status 4
   */
  @Test
  public void testCannotIncreaseSpeedWithoutGearIncrease() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 10);
    String expectedOutput = "Cannot increase speed, increase gear first.";
    for (int i = 0; i < 4; i++) {
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseSpeed();
      assertEquals(expectedOutput, speedCheck.getStatus());
      speedCheck = speedCheck.increaseGear();
    }
  }

  /**
   * Test to check if speed change cannot happen without increasing the gear.
   * Test 9 (Cannot decrease speed, decrease gear first.)
   * Status 5
   */
  @Test
  public void testCannotDecreaseSpeedWithoutGearDecrease() {
    //(0, 2, 2, 4, 4, 6, 6, 8, 8, 10);
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 10);
    String expectedOutput = "Cannot decrease speed, decrease gear first.";
    for (int i = 0; i < 4; i++) {
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseGear();
    }
    speedCheck = speedCheck.increaseSpeed();
    speedCheck = speedCheck.increaseSpeed();
    for (int i = 0; i < 4; i++) {
      speedCheck = speedCheck.decreaseSpeed();
      speedCheck = speedCheck.decreaseSpeed();
      speedCheck = speedCheck.decreaseSpeed();
      assertEquals(expectedOutput, speedCheck.getStatus());
      speedCheck = speedCheck.decreaseGear();
    }
  }

  /**
   * Test to check if gear change cannot happen without increasing the speed.
   * Test 10 (Cannot increase gear, increase speed first.)
   * Status 6
   */
  @Test
  public void testCannotIncreaseGearWithoutSpeedIncrease() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 10);
    String expectedOutput = "Cannot increase gear, increase speed first.";
    for (int i = 0; i < 4; i++) {
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseGear();
      assertEquals(expectedOutput, speedCheck.getStatus());
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseGear();
    }
  }

  /**
   * Test to check if gear change cannot happen without decreasing the speed.
   * Test 11 (Cannot decrease gear, decrease speed first.)
   * Status 7
   */
  @Test
  public void testCannotDecreaseGearWithoutSpeedDecrease() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 10);
    String expectedOutput = "Cannot decrease gear, decrease speed first.";
    for (int i = 0; i < 4; i++) {
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseGear();
    }
    speedCheck = speedCheck.increaseSpeed();
    speedCheck = speedCheck.increaseSpeed();
    for (int i = 0; i < 4; i++) {
      speedCheck = speedCheck.decreaseGear();
      assertEquals(expectedOutput, speedCheck.getStatus());
      speedCheck = speedCheck.decreaseGear();
      assertEquals(expectedOutput, speedCheck.getStatus());
      speedCheck = speedCheck.decreaseSpeed();
      speedCheck = speedCheck.decreaseSpeed();
      speedCheck = speedCheck.decreaseGear();
    }
  }

  /**
   * Test to check if speed change cannot happen once it reaches maximum speed.
   * Test 12 (Cannot increase speed, Reached Maximum speed.)
   * Status 8
   * Covers cases where max speed is reached in Gear 4 and Gear 5
   */
  @Test
  public void testCannotIncreaseSpeedMaxReached() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 8);
    String expectedOutput = "Cannot increase speed. Reached maximum speed.";
    for (int i = 0; i < 4; i++) {
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseGear();
    }
    speedCheck = speedCheck.increaseSpeed();
    assertEquals(expectedOutput, speedCheck.getStatus());
    speedCheck = speedCheck.decreaseGear();
    speedCheck = speedCheck.increaseSpeed();
    assertEquals(expectedOutput, speedCheck.getStatus());
  }

  /**
   * Test to check if positive speed change cannot happen once it reaches maximum speed.
   * Test 13 (Cannot increase speed, Reached Maximum speed.)
   * Status 8
   * Covers cases where max speed is reached Gear 5 ( Redundant of last case )
   */
  @Test
  public void testCannotIncreaseSpeedMaxReachedOnlyLastGear() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 9);
    String expectedOutput = "Cannot increase speed. Reached maximum speed.";
    for (int i = 0; i < 4; i++) {
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseGear();
    }
    speedCheck = speedCheck.increaseSpeed();
    speedCheck = speedCheck.increaseSpeed();
    assertEquals(expectedOutput, speedCheck.getStatus());
  }

  /**
   * Test to check if negative speed change cannot happen once it reaches minimum speed.
   * Test 14 (Cannot decrease speed, Reached minimum speed.)
   * Goes to Max Speed and down and try below 0.
   * Status 9
   */
  @Test
  public void testCannotDecreaseSpeedMinReached() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 9);
    String expectedOutput = "Cannot decrease speed. Reached minimum speed.";
    for (int i = 0; i < 4; i++) {
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseGear();
    }
    speedCheck = speedCheck.increaseSpeed();
    speedCheck = speedCheck.increaseSpeed();
    for (int i = 0; i < 5; i++) {
      speedCheck = speedCheck.decreaseGear();
      speedCheck = speedCheck.decreaseSpeed();
      speedCheck = speedCheck.decreaseSpeed();
    }
    speedCheck = speedCheck.decreaseSpeed();
    assertEquals(expectedOutput, speedCheck.getStatus());
  }

  /**
   * Test to check if negative speed change cannot happen once it reaches minimum speed.
   * Test 15 (Cannot decrease speed, Reached minimum speed.)
   * Checking without any speed changes or gear changes.
   * Status 9
   */
  @Test
  public void testCannotDecreaseSpeedMinReachedInitial() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 9);
    String expectedOutput = "Cannot decrease speed. Reached minimum speed.";
    speedCheck = speedCheck.decreaseSpeed();
    assertEquals(expectedOutput, speedCheck.getStatus());
  }

  /**
   * Test to check if gear can be increase from 5.
   * Test 16 (Cannot increase gear, Reached maximum gear.)
   * Status 10
   */
  @Test
  public void testCannotIncreaseGearMaxReached() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 9);
    String expectedOutput = "Cannot increase gear. Reached maximum gear.";
    for (int i = 0; i < 4; i++) {
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseGear();
    }
    speedCheck = speedCheck.increaseGear();
    assertEquals(expectedOutput, speedCheck.getStatus());
  }

  /**
   * Test to check if gear can be reduced from 1.
   * Test 17 (Cannot decrease gear, Reached minimum gear.)
   * Status 11
   */
  @Test
  public void testCannotIncreaseGearMinReachedInitial() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 9);
    String expectedOutput = "Cannot decrease gear. Reached minimum gear.";
    speedCheck.decreaseGear();
    assertEquals(expectedOutput, speedCheck.getStatus());
    speedCheck.increaseSpeed();
    speedCheck.decreaseGear();
    assertEquals(expectedOutput, speedCheck.getStatus());
  }

  /**
   * Test to check if gear can be reduced from 1 after increasing gears and decreasing.
   * Test 18 (Cannot decrease gear. Reached minimum gear.)
   * Goes to Max gear and down and try below 1.
   * Status 11
   */
  @Test
  public void testCannotDecreaseGearMinReached() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 9);
    String expectedOutput = "Cannot decrease gear. Reached minimum gear.";
    for (int i = 0; i < 4; i++) {
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseGear();
    }
    speedCheck = speedCheck.increaseSpeed();
    speedCheck = speedCheck.increaseSpeed();
    for (int i = 0; i < 5; i++) {
      speedCheck = speedCheck.decreaseGear();
      speedCheck = speedCheck.decreaseSpeed();
      speedCheck = speedCheck.decreaseSpeed();
    }
    speedCheck = speedCheck.decreaseGear();
    assertEquals(expectedOutput, speedCheck.getStatus());
  }

  /**
   * Test Positive non-zero low speed for gear 1.
   * Test 19 ( L1 > 0 )
   * Input Argument
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPositiveLowSpeedGearOne() {
    speedCheck = new RegularManualTransmission(1, 2, 2, 4, 4, 6, 6, 8, 8, 10);
  }

  /**
   * Test Negative non-zero low speed for gear 1.
   * Test 20 ( L1 < 0 )
   * Input Argument
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeLowSpeedGearOne() {
    speedCheck = new RegularManualTransmission(-1, 2, 2, 4, 4, 6, 6, 8, 8, 10);
  }

  /**
   * Test successful creation of Object and verify no exception is thrown as such.
   * Test 21
   * Input Argument
   */
  @Test(expected = Test.None.class)
  public void testCreateValidArgumentObject() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 10);
  }

  /**
   * Test if initial speed is set as 0 after creating the object.
   * Test 22
   * Input Argument
   */
  @Test
  public void testInitialSpeedSet() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 10);
    assertEquals(0, speedCheck.getSpeed());
  }

  /**
   * Test if initial gear is set as 1 after creating the object.
   * Test 23
   * Input Argument
   */
  @Test
  public void testInitialGearSet() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 10);
    assertEquals(1, speedCheck.getGear());
  }

  /**
   * Test if initial status is set as OK: everything is OK after creating the object.
   * Test 24
   * Input Argument
   */
  @Test
  public void testInitialStatusSet() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 8, 8, 10);
    assertEquals("OK: everything is OK.", speedCheck.getStatus());
  }

  /**
   * Test if high Speed lies in Gear 4 instead of Gear 5 then Exception is raised.
   * Test 25
   * Input Argument
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFinalSpeedInLastGear() {
    speedCheck = new RegularManualTransmission(0, 2, 2, 4, 4, 6, 6, 10, 7, 9);
  }

  /**
   * Test to check if the speed is set properly for low and high speed. Fuzzy Testing.
   * Test 26
   * Input Argument
   */
  @Test
  public void testBasicWorkingLowAndHighGearSpeedFuzzyTest() {
    Random r = new Random();
    r.setSeed(12313);
    for (int i = 0; i < 1000; i++) {

      int lowSpeedGearOne = 0;
      int highSpeedGearOne = r.nextInt(30 - lowSpeedGearOne) + (lowSpeedGearOne + 1);

      int lowSpeedGearTwo = r.nextInt(highSpeedGearOne - lowSpeedGearOne)
              + (lowSpeedGearOne + 1);
      int highSpeedGearTwo = r.nextInt(50 - highSpeedGearOne) + (highSpeedGearOne + 1);

      int lowSpeedGearThree = r.nextInt(highSpeedGearTwo - highSpeedGearOne)
              + (highSpeedGearOne + 1);
      int highSpeedGearThree = r.nextInt(70 - highSpeedGearTwo) + (highSpeedGearTwo + 1);

      int lowSpeedGearFour = r.nextInt(highSpeedGearThree - highSpeedGearTwo)
              + (highSpeedGearTwo + 1);
      int highSpeedGearFour = r.nextInt(90 - highSpeedGearThree) + (highSpeedGearThree + 1);

      int lowSpeedGearFive = r.nextInt(highSpeedGearFour - highSpeedGearThree)
              + (highSpeedGearThree + 1);
      int highSpeedGearFive = r.nextInt(120 - highSpeedGearFour) + (highSpeedGearFour);

      speedCheck = new RegularManualTransmission(lowSpeedGearOne, highSpeedGearOne, lowSpeedGearTwo,
              highSpeedGearTwo, lowSpeedGearThree, highSpeedGearThree, lowSpeedGearFour,
              highSpeedGearFour, lowSpeedGearFive, highSpeedGearFive);
      assertEquals("OK: everything is OK.", speedCheck.getStatus());
    }
  }

  /**
   * Test to check only adjacent gear ranges overlap.
   * Test 27
   * Gears
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultipleGearRangesOverlap() {
    speedCheck = new RegularManualTransmission(0, 10, 5, 12, 6, 13, 8, 15, 9, 20);
  }

  /**
   * Test to check if lower speed in a gear is less or equal(does not happen except for last gear)
   * to higher speed in that gear it raises illegal argument exception if not.
   * Test 28
   * L_1 <= H_1
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCompareLowSpeedHighSpeed() {
    speedCheck = new RegularManualTransmission(10, 15, 21, 20, 24, 25, 26, 30, 32, 35);
  }

  /**
   * Test to check if the alternate speed ranges overlap if the new object created doesn't overlap
   * it raises illegal argument exception.
   * Test 29
   * Alternate Gear Ranges Overlap.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSpeedOverLapIllegalArgumentExceptionThrown() {
    speedCheck = new RegularManualTransmission(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
  }

  /**
   * Test to check if the adjacent low speeds of a gear are strictly less than previous gear
   * it raises illegal argument exception if not.
   * Test 30
   * Lx < L_(x+1)
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLowerSpeedStrictLessThanAdjacentLower() {
    speedCheck = new RegularManualTransmission(10, 15, 8, 20, 7, 25, 26, 30, 32, 35);
  }

  /**
   * Test High speed on lower gear. On Gear 4 Reaching high speed.
   * Test 31
   */
  @Test
  public void testHighSpeedLowerGear() {
    speedCheck = new RegularManualTransmission(0, 1, 1, 2, 2, 3, 3, 4, 4, 4);
    for (int i = 0; i < 3; i++) {
      speedCheck = speedCheck.increaseSpeed();
      speedCheck = speedCheck.increaseGear();
    }
    speedCheck = speedCheck.increaseSpeed();
    speedCheck = speedCheck.increaseSpeed();
    String expectedOutput = "Cannot increase speed. Reached maximum speed.";
    assertEquals(expectedOutput, speedCheck.getStatus());
  }

}