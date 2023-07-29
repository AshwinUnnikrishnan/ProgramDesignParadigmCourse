/**
 * Created by histravelstories.
 * This is a class
 * Date : 9/17/22
 * Project Name : Transmission
 */

package vehicle;

/**
 * Represents a Regular Manual Transmission.
 */
public class RegularManualTransmission implements ManualTransmission {

  //speed, gear, status keep changing and lowSpeed and highSpeed is set/
  private int speed;
  private int gear;
  private String status;
  private final int[] lowSpeed;
  private final int[] highSpeed;

  /**
   * Function to check if the input arguments passed to the constructor are all valid.
   * If not valid then raise exception.
   *
   * @throws IllegalArgumentException Thrown when the input lowSpeed and highSpeed does not satisfy
   *                                  constraints provided.
   */
  private void checkSpeedRange() throws IllegalArgumentException {

    // To check if lowSpeed in less than or equal to high speed for a particular gear.
    for (int i = 0; i < 5; i++) {
      if (this.lowSpeed[i] > this.highSpeed[i]) {
        throw new IllegalArgumentException("Lower Speed should be less than Higher Speed for "
                + "each gear");
      }
    }

    // To check if only adjacent gear ranges overlap.
    if (this.lowSpeed[2] <= this.highSpeed[0] || this.lowSpeed[3] <= this.highSpeed[1]
            || this.lowSpeed[4] <= this.highSpeed[2]) {
      throw new IllegalArgumentException("Only Adjacent Gear speed values should overlap");
    }

    // To check lowSpeed is strictly less than lowSpeed of higher gear.
    for (int i = 0; i < 4; i++) {
      if (this.lowSpeed[i] >= this.lowSpeed[i + 1]) {
        throw new IllegalArgumentException("Lower Speed of lesser gears should be strictly less "
                + "than lower speed of higher gears");
      }
    }

    // To check if the speed ranges overlap such that adjacent gears have overlapping speeds
    for (int i = 0; i < 4; i++) {
      if (this.lowSpeed[i + 1] > this.highSpeed[i]) {
        throw new IllegalArgumentException("Adjacent gear speed ranges doesn't overlap");
      }
    }
  }

  /**
   * Function to add speed.
   *
   * @param speedUpdate Speed value to be added to the current speed Can be Poisitive or Negative.
   */
  private void setSpeed(int speedUpdate) {
    this.speed = this.speed + speedUpdate;
  }

  /**
   * Function to add gear.
   *
   * @param gearUpdate Gear value to be added to current gear, can be positive or negative.
   */
  private void setGear(int gearUpdate) {
    this.gear = this.gear + gearUpdate;
  }

  /**
   * Function to set the status based on the flag passed in the argument.
   *
   * @param flag Integer value that will help in setting the status for the transmission.
   */
  private void setStatus(int flag) {
    switch (flag) {
      case 1:
        // if the speed was changed successfully without changing gears, or the gear was
        // changed successfully without changing speed
        this.status = "OK: everything is OK.";
        break;

      case 2:
        // if the speed was increased successfully, but it is now within the range of the next gear.
        this.status = "OK: you may increase the gear.";
        break;
      case 3:
        // if the speed was decreased successfully, but it is now within the range of the previous
        // gear.
        this.status = "OK: you may decrease the gear.";
        break;
      case 4:
        // if the speed cannot be increased more unless the gear is increased first.
        this.status = "Cannot increase speed, increase gear first.";
        break;
      case 5:
        // if the speed cannot be decreased more unless the gear is decreased first.
        this.status = "Cannot decrease speed, decrease gear first.";
        break;
      case 6:
        // if the gear cannot be increased more unless the speed is increased first.
        this.status = "Cannot increase gear, increase speed first.";
        break;
      case 7:
        // if the gear cannot be decreased more unless the speed is decreased first.
        this.status = "Cannot decrease gear, decrease speed first.";
        break;
      case 8:
        // if the speed cannot be increased as it will go beyond the speed limit of the vehicle.
        this.status = "Cannot increase speed. Reached maximum speed.";
        break;
      case 9:
        // if the speed cannot be decreased as it is already 0.
        this.status = "Cannot decrease speed. Reached minimum speed.";
        break;
      case 10:
        // if the gear cannot be increased as it is already in gear 5.
        this.status = "Cannot increase gear. Reached maximum gear.";
        break;
      case 11:
        // if the gear cannot be decreased as it is already in gear 1.
        this.status = "Cannot decrease gear. Reached minimum gear.";
        break;
      default:
    }
  }

  /**
   * Copy constructor for the RegularManualTransmission, used to create new object from old by
   * passing the object.
   *
   * @param vehicle : New Object created and where you want to store the values.
   * @throws IllegalArgumentException when a null object is passed as an argument.
   */
  private RegularManualTransmission(RegularManualTransmission vehicle)
          throws IllegalArgumentException {
    if (vehicle == null) {
      throw new IllegalArgumentException("You need to pass a valid RegularManualTransmission," +
              " passed a null instead");
    }
    // if passed object is not null then update the values to new object
    speed = vehicle.speed;
    status = vehicle.status;
    gear = vehicle.gear;
    lowSpeed = vehicle.lowSpeed.clone();
    highSpeed = vehicle.highSpeed.clone();
  }

  /**
   * Constructor of regularManualTransmission that takes in lowSpeed and highSpeed of each gear.
   *
   * @param gearOneLowSpeed    : LowSpeed in Gear 1
   * @param gearOneHighSpeed   : HighSpeed in Gear 1
   * @param gearTwoLowSpeed    : LowSpeed in Gear 2
   * @param gearTwoHighSpeed   : HighSpeed in Gear 2
   * @param gearThreeLowSpeed  : LowSpeed in Gear 3
   * @param gearThreeHighSpeed : HighSpeed in Gear 3
   * @param gearFourLowSpeed   : LowSpeed in Gear 4
   * @param gearFourHighSpeed  : HighSpeed in Gear 4
   * @param gearFiveLowSpeed   : LowSpeed in Gear 5
   * @param gearFiveHighSpeed  : HighSpeed in Gear 5
   */
  public RegularManualTransmission(int gearOneLowSpeed, int gearOneHighSpeed, int gearTwoLowSpeed,
                                   int gearTwoHighSpeed, int gearThreeLowSpeed,
                                   int gearThreeHighSpeed, int gearFourLowSpeed,
                                   int gearFourHighSpeed, int gearFiveLowSpeed,
                                   int gearFiveHighSpeed) {
    if (gearOneLowSpeed != 0) {
      throw new IllegalArgumentException("Low Speed of first gear should be 0 always.");
    }
    if (gearFiveHighSpeed < gearFourHighSpeed) {
      throw new IllegalArgumentException("High Speed should be there in the last gear not " +
              "in the earlier gear.");
    }
    this.speed = 0;
    this.gear = 1;
    this.status = "OK: everything is OK.";
    this.lowSpeed = new int[5];
    this.highSpeed = new int[5];
    this.lowSpeed[0] = gearOneLowSpeed; // Can autocorrect as well here
    this.lowSpeed[1] = gearTwoLowSpeed;
    this.lowSpeed[2] = gearThreeLowSpeed;
    this.lowSpeed[3] = gearFourLowSpeed;
    this.lowSpeed[4] = gearFiveLowSpeed;
    this.highSpeed[0] = gearOneHighSpeed;
    this.highSpeed[1] = gearTwoHighSpeed;
    this.highSpeed[2] = gearThreeHighSpeed;
    this.highSpeed[3] = gearFourHighSpeed;
    this.highSpeed[4] = gearFiveHighSpeed;

    // To check the constraints of input argument and raise exception if it does not follow.
    this.checkSpeedRange();
  }


  /**
   * Get the current status of vehicle.
   *
   * @return the status of transmission as a string.
   */
  @Override
  public String getStatus() {
    return this.status;
  }

  /**
   * Get the current speed of vehicle.
   *
   * @return Current speed of vehicle as whole number.
   */
  @Override
  public int getSpeed() {
    return this.speed;
  }

  /**
   * Get the current gear of vehicle.
   *
   * @return Current gear of vehicle as whole number.
   */
  @Override
  public int getGear() {
    return this.gear;
  }

  /**
   * Increase the speed by a fixed(hardcoded amount), without changing gears. If speed cannot be
   * increased then it returns same speed object.
   *
   * @return Transmission object with updated speed.
   */
  @Override
  public ManualTransmission increaseSpeed() {

    // Getting the current gear and speed, reducing gear value to get to right index in array
    int currentGear = this.getGear() - 1;
    int currentSpeed = this.getSpeed();
    int speedChange = 1;

    //Case when the current gear is 4 and current speed is equal to the max speed
    if (currentSpeed == this.highSpeed[4]) {
      this.setStatus(8);
    }
    // Case when the speed is already high in the current gear and cannot increase more without
    // increasing gear
    else if (currentSpeed == this.highSpeed[currentGear]) {
      this.setStatus(4);
    }
    // Case when increasing the speed is less or equal to high of current gear
    else if (currentSpeed + speedChange <= this.highSpeed[currentGear]) {
      // Case when increasing the speed did not reach the next gear low speed or when gear is 5
      // increasing speed is less than or equal to highSpeed
      if ((currentGear < 4 && currentSpeed + speedChange < this.lowSpeed[currentGear + 1])
              || (currentGear == 4 && currentSpeed + speedChange <= this.highSpeed[currentGear])) {
        this.setStatus(1);
      }
      // Case when the increased speed falls under the range of next gear.
      else {
        this.setStatus(2);
      }
      this.setSpeed(speedChange);
    }
    return new RegularManualTransmission(this);
  }

  /**
   * Decrease the speed by a fixed(hardcoded amount), without changing gears. If speed cannot be
   * decreased then it returns same speed object.
   *
   * @return Transmission object with updated speed.
   */
  @Override
  public ManualTransmission decreaseSpeed() {
    int currentGear = this.getGear() - 1;
    int currentSpeed = this.getSpeed();
    int speedChange = -1;

    //Case when the speed is equal to the low speed
    if (currentSpeed == this.lowSpeed[0]) {
      this.setStatus(9);
    }
    //Case when the speed is already low and cannot decrease more
    else if (currentSpeed == this.lowSpeed[currentGear]) {
      this.setStatus(5);
    }
    //Case when speed is more than lowerspeed Need to check this based on increaseSpeed
    else if (currentSpeed + speedChange >= this.lowSpeed[currentGear]) {
      if ((currentGear > 0 && currentSpeed + speedChange > this.highSpeed[currentGear - 1])
              || (currentGear == 0 && currentSpeed - 1 >= this.lowSpeed[0])) {
        this.setStatus(1);  //Everything is okay
      } else {
        this.setStatus(3);//You may increase the gear
      }
      this.setSpeed(speedChange);
    }

    return new RegularManualTransmission(this);
  }

  /**
   * Increase the gear by one, without changing speed. If gear cannot be
   * increased then it returns same gear object.
   *
   * @return Transmission object with updated gear.
   */
  @Override
  public ManualTransmission increaseGear() {
    //need to add check for updating the speed or object
    int currentGear = this.getGear();
    int currentSpeed = this.getSpeed();
    int gearChange = 1;

    if (currentGear == 5) {
      this.setStatus(10);
    } else if (currentSpeed < this.lowSpeed[currentGear]) {
      this.setStatus(6);
    } else {
      this.setGear(gearChange);
      this.setStatus(1);
    }
    return new RegularManualTransmission(this);
  }

  /**
   * Decrease the speed by one, without changing speed. If gear cannot be
   * decreased then it returns same speed object.
   *
   * @return Transmission object with updated gear.
   */
  @Override
  public ManualTransmission decreaseGear() {
    int currentGear = this.getGear();
    int currentSpeed = this.getSpeed();
    int gearChange = -1;

    if (currentGear == 1) {
      this.setStatus(11);
    } else if (currentSpeed > this.highSpeed[currentGear - 2]) {
      this.setStatus(7);
    } else {
      this.setGear(gearChange);
      this.setStatus(1);
    }
    return new RegularManualTransmission(this);
  }
}
