/**
 * This class represents a 2D point. This point is denoted in Cartesian
 * coordinates as (x,y).
 */
public class Point2D {
  private double x;
  private double y;

  /**
   * Construct a 2d point with the given coordinates
   *
   * @param x the x-coordinate of this point
   * @param y the y-coordinate of this point
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Compute and return the Euclidean distance between this point and another
   * point passed as its argument
   *
   * @param other the other point to which the distance from this point is to be
   *              computed
   * @return the Euclidean distance between this point and the other point
   */
  public double dist(Point2D other) {
    return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) *
                                                               (this.y - other.y));
  }

  /**
   * Return the x-coordinate of this point
   *
   * @return x-coordinate of this point
   */
  public double getX() {
    return x;
  }

  /**
   * Return the y-coordinate of this point
   *
   * @return y-coordinate of this point
   */
  public double getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) {
      return true;
    }

    if (!(o instanceof Point2D)) {
      return false;
    }

    Point2D other = (Point2D)o;
    return this.dist(other)<0.001;
  }
}
