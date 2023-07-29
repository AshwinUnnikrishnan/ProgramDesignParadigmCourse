import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the SimpleProjectile class.
 */
public class SimpleProjectileTest {

  private Particle negativeVerticalVelocityParticle;
  private Particle zeroHorizontalVelocity;
  private Particle zeroVelocity;
  private Particle negativeHorizontalVelocityParticle;
  private Particle negativeInitialPositions;
  private Particle positiveVelocities;


  @Before
  public void setUp() {
    negativeVerticalVelocityParticle = new SimpleProjectile(1, 1,
            0, -10);
    zeroHorizontalVelocity = new SimpleProjectile(0, 0, 0,
            14);
    zeroVelocity = new SimpleProjectile(0, 0, 0,
            0);
    negativeHorizontalVelocityParticle = new SimpleProjectile(1, 1,
            -10, 10);
    negativeInitialPositions = new SimpleProjectile(-1, -1,
            -10, 10);
    positiveVelocities = new SimpleProjectile(1, 1,
            10, 10);

  }

  /**
   * To test functionality when negative time is provided.
   */
  @Test
  public void testNegativeTime() {
    String expectedOutput = "At time -1.00: position is (0.00,0.00)";
    assertEquals(expectedOutput, zeroHorizontalVelocity.getState(-1));
  }

  /**
   * To test functionality when horizontal velocity is 0 and the particle goes up and reaches the
   * origin.
   */
  @Test
  public void testZeroHorizontalVelocityRest() {
    String expectedOutput = "At time 5.00: position is (0.00,0.00)";
    assertEquals(expectedOutput, zeroHorizontalVelocity.getState(5.00f));
  }

  /**
   * To test functionality when horizontal velocity is 0 and the particle goes up and before
   * reaching origin.
   */
  @Test
  public void testZeroHorizontalVelocityInAir() {
    String expectedOutput = "At time 2.00: position is (0.00,8.38)";
    assertEquals(expectedOutput, zeroHorizontalVelocity.getState(2.00f));
  }

  /**
   * To test functionality when both velocities are 0.
   */
  @Test
  public void testZeroVelocity() {
    String expectedOutput = "At time 5.00: position is (0.00,0.00)";
    assertEquals(expectedOutput, zeroVelocity.getState(5.00f));
  }

  /**
   * To test functionality when vertical velocity is negative.
   */
  @Test
  public void testNegativeVerticalVelocity() {
    String expectedOutput = "At time 2.04: position is (1.00,1.00)";
    assertEquals(expectedOutput, negativeVerticalVelocityParticle.getState(2.04f));
  }

  /**
   * To test functionality when horizontal velocity is negative.
   */
  @Test
  public void testNegativeHorizontalVelocity() {
    String expectedOutput = "At time 1.00: position is (-9.00,6.09)";
    assertEquals(expectedOutput, negativeHorizontalVelocityParticle.getState(1.00f));
  }

  /**
   * To test functionality when initial positions are negative.
   */
  @Test
  public void testNegativeInitialPosition() {
    String expectedOutput = "At time 1.00: position is (-11.00,4.09)";
    assertEquals(expectedOutput, negativeInitialPositions.getState(1.00f));
  }

  /**
   * To test functionality when both velocities are positive.
   */
  @Test
  public void testPositiveVelocity() {
    String expectedOutput = "At time 1.00: position is (11.00,6.09)";
    assertEquals(expectedOutput, positiveVelocities.getState(1.00f));
  }


}