/**
 * Represents a simple Newtonian particle.
 */
public class SimpleProjectile implements Particle {

  private final float initialPositionX;
  private final float initialPositionY;
  private final float initialVelocityX;
  private final float initialVelocityY;


  /**
   * Constructs a {@code SimpleProjectile} object.
   *
   * @param initialPositionX initial horizontal position of the particle
   * @param initialPositionY initial vertical position of the particle
   * @param initialVelocityX initial horizontal velocity of the particle
   * @param initialVelocityY initial vertical velocity of the particle
   */
  public SimpleProjectile(float initialPositionX, float initialPositionY, float initialVelocityX,
                          float initialVelocityY) {

    this.initialPositionX = initialPositionX;
    this.initialPositionY = initialPositionY;
    this.initialVelocityX = initialVelocityX;
    this.initialVelocityY = initialVelocityY;
  }


  /**
   * Given the time the function calculates the new position based on the initial position and
   * initial velocity.
   *
   * @param time the time at which the state must be obtained
   * @return the state of the particle as a string formatted as
   * <code>At time [t]: position is ([x],[y])</code>
   */
  @Override
  public String getState(float time) {

    // if time is less than 0 or initial vertical velocity is less than or equal to 0 assuming
    // particle in rest state.
    if (time < 0 || this.initialVelocityY <= 0) {
      return "At time " + String.format("%.2f", time) + ": position is ("
              + String.format("%.2f", this.initialPositionX) + ","
              + String.format("%.2f", this.initialPositionY) + ")";
    }

    float gravity = 9.81f;

    // Finding the first time the particle hits the ground as once it hits the ground it stays in
    // that position. We substitute vertical displacement by 0 and get
    // time = (2*initial vertical velocity)/gravity

    final float timeToHitGround = (float) (2 * this.initialVelocityY / gravity);

    float horizontalDisplacement = 0.00f;
    float verticalDisplacement = 0.00f;

    // Once we calculate timeToHitGround, checking if the given time is more than timeToHitGround.

    if (time > timeToHitGround) {
      // Horizontal displacement is only dependent on horizontal velocity and not gravity so
      // removing the term from newtons equation, and vertical displacement is 0 as time exceeds
      // timeToHitGround
      horizontalDisplacement = this.initialVelocityX * timeToHitGround;
    } else {
      horizontalDisplacement = this.initialVelocityX * time;
      verticalDisplacement = (this.initialVelocityY * time) - (gravity * time * time) / 2;
    }
    return "At time " + String.format("%.2f", time) + ": position is (" + String.format("%.2f",
            (this.initialPositionX + horizontalDisplacement)) + ","
            + String.format("%.2f", (this.initialPositionY + verticalDisplacement)) + ")";
  }

}
