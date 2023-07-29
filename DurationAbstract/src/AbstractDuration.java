/**
 * Abstract base class for implementations of {@link Duration}. This class
 * contains all the method definitions that are common to the concrete
 * implementations of the {@link Duration} interface. A new implementation of
 * the interface has the option of extending this class, or directly
 * implementing all the methods.
 */

abstract class AbstractDuration implements Duration {

  @Override
  public int compareTo(Duration that) {
    return Long.compare(this.inSeconds(), that.inSeconds());
  }


  @Override
  public boolean equals(Object o) {
    // Fast path for pointer equality:
    if (this == o) { //backward compatibility with default equals
      return true;
    }

    // If o isn't the right class then it can't be equal:
    if (!(o instanceof Duration)) {
      return false;
    }

    // The successful instanceof check means our cast will succeed:
    Duration that = (Duration) o;

    return this.inSeconds() == that.inSeconds();
  }

  @Override
  public int hashCode() {
    return Long.hashCode(this.inSeconds());
  }

  @Override
  public String asHms() {
    return String.format("%d:%02d:%02d", hoursOf(this.inSeconds()), minutesOf(this.inSeconds()), secondsOf(this.inSeconds()));
  }

  /**
   * Returns the number of whole hours in the given number of seconds.
   *
   * @param inSeconds the total number of seconds
   * @return the number of hours
   * @throws ArithmeticException if the correct result cannot fit in an {@code
   *                             int}.
   */
  protected static int hoursOf(long inSeconds) {
    if (inSeconds / 3600 > Integer.MAX_VALUE) {
      throw new ArithmeticException("result cannot fit in type");
    }

    return (int) (inSeconds / 3600);
  }

  /**
   * Returns the number of whole minutes in the given number of seconds, less
   * the number of whole hours.
   *
   * @param inSeconds the total number of seconds
   * @return the number of remaining minutes
   */
  protected static int minutesOf(long inSeconds) {
    return (int) (inSeconds / 60 % 60);
  }

  /**
   * Returns the number of seconds remaining after all full minutes are removed
   * from the given number of seconds.
   *
   * @param inSeconds the total number of seconds
   * @return the number of remaining seconds
   */
  protected static int secondsOf(long inSeconds) {
    return (int) (inSeconds % 60);
  }

  /**
   * Converts an unpacked hours-minutes-seconds duration to its length in
   * seconds.
   *
   * @param hours   the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @return the duration in seconds
   */
  protected static long inSeconds(int hours, int minutes, int seconds) {
    return 3600 * hours + 60 * minutes + seconds;
  }

}
