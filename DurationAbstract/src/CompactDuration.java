/**
 * Durations represented compactly, with a range of 0 to 2<sup>63</sup>-1
 * seconds.
 */
public final class CompactDuration extends AbstractDuration {
  private final long inSeconds;

  /**
   * Constructs a duration in terms of its length in hours, minutes, and
   * seconds.
   *
   * @param hours   the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @throws IllegalArgumentException if any argument is negative
   */
  public CompactDuration(int hours, int minutes, int seconds)
          throws IllegalArgumentException {
    if ((hours < 0) || (minutes < 0) || (seconds < 0)) {
      throw new IllegalArgumentException("Negative durations are not supported");
    }
    this.inSeconds = inSeconds(hours, minutes, seconds);
  }

  /**
   * Constructs a duration in terms of its length in seconds.
   *
   * @param inSeconds the number of seconds (non-negative)
   * @throws IllegalArgumentException {@code inSeconds} is negative
   */
  public CompactDuration(long inSeconds) {
    if (inSeconds < 0) {
      throw new IllegalArgumentException("must be non-negative");
    }

    this.inSeconds = inSeconds;
  }

  @Override
  public long inSeconds() {
    return this.inSeconds;
  }


  @Override
  public Duration plus(Duration other) {
    long thisSeconds = this.inSeconds();
    long otherSeconds = other.inSeconds();
    long total = thisSeconds + otherSeconds;
    return new HmsDuration(total);
  }
}
