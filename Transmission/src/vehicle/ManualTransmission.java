/**
 * Created by histravelstories.
 * This is an interface
 * Date : 9/17/22
 * Project Name : Transmission
 */

package vehicle;

/**
 * This interface represents a set of operations on any manual transmission vehicle.
 */
public interface ManualTransmission {
  /**
   * Get the current status of vehicle.
   *
   * @return the status of transmission as a string.
   */
  String getStatus();

  /**
   * Get the current speed of vehicle.
   *
   * @return Current speed of vehicle as whole number.
   */
  int getSpeed();

  /**
   * Get the current gear of vehicle.
   *
   * @return Current gear of vehicle as whole number.
   */
  int getGear();

  /**
   * Increase the speed by a fixed(hardcoded amount), without changing gears. If speed cannot be
   * increased then it returns same speed object.
   *
   * @return Transmission object with updated speed.
   */
  ManualTransmission increaseSpeed();

  /**
   * Decrease the speed by a fixed(hardcoded amount), without changing gears. If speed cannot be
   * decreased then it returns same speed object.
   *
   * @return Transmission object with updated speed.
   */
  ManualTransmission decreaseSpeed();

  /**
   * Increase the gear by one, without changing speed. If gear cannot be
   * increased then it returns same gear object.
   *
   * @return Transmission object with updated gear.
   */
  ManualTransmission increaseGear();

  /**
   * Decrease the speed by one, without changing speed. If gear cannot be
   * decreased then it returns same speed object.
   *
   * @return Transmission object with updated gear.
   */
  ManualTransmission decreaseGear();


}
