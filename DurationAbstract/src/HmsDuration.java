/**
 * Durations represented as hours, minutes, and seconds.
 */
public final class HmsDuration extends AbstractDuration {
  private final int hours;
  private final int minutes;
  private final int seconds;

  /**
   * Constructs a duration in terms of its length in hours, minutes, and
   * seconds.
   *
   * @param hours   the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @throws IllegalArgumentException if any argument is negative
   */
  public HmsDuration(int hours, int minutes, int seconds)
          throws IllegalArgumentException {
    if ((hours < 0) || (minutes < 0) || (seconds < 0)) {
      throw new IllegalArgumentException("Negative durations are not supported");
    }

    long totalSeconds = inSeconds(hours, minutes, seconds);

    this.hours = hoursOf(totalSeconds);
    this.minutes = minutesOf(totalSeconds);
    this.seconds = secondsOf(totalSeconds);
  }

  /**
   * Constructs a duration in terms of its length in seconds.
   *
   * @param inSeconds the number of seconds (non-negative)
   * @throws IllegalArgumentException {@code inSeconds} is negative
   */
  public HmsDuration(long inSeconds) {
    if (inSeconds < 0) {
      throw new IllegalArgumentException("must be non-negative");
    }

    hours = hoursOf(inSeconds);
    minutes = minutesOf(inSeconds);
    seconds = secondsOf(inSeconds);
  }

  @Override
  public long inSeconds() {
    return inSeconds(hours, minutes, seconds);
  }


  @Override
  public Duration plus(Duration other) {
    long thisSeconds = this.inSeconds();
    long otherSeconds = other.inSeconds();
    long total = thisSeconds + otherSeconds;
    return new HmsDuration(total);
  }


}
