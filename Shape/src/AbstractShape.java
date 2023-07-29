/**
 * Created by ashesh on 1/26/2017.
 */
public abstract class AbstractShape implements Shape {
  protected Point2D reference;

  protected AbstractShape(Point2D reference) {
    this.reference = reference;
  }

  @Override
  public double distToOrigin() {
    return reference.dist(new Point2D(0, 0));
  }


  @Override
  public int compareTo(Shape s) {
    double areaThis = this.area();
    double areaOther = s.area();

    if (areaThis < areaOther) {
      return -1;
    } else if (areaOther < areaThis) {
      return 1;
    } else {
      return 0;
    }
  }

  /**
   * Determine whether this shape is equal to a circle object
   *
   * @param other the circle object to which this shape must be compared
   * @return false by default, subclasses may override
   */
  protected boolean equalsCircle(Circle other) {
    return false; //by default "this" shape is not equal to a circle
  }

  /**
   * Determine whether this shape is equal to a rectangle object
   *
   * @param other the rectangle object to which this shape must be compared
   * @return false by default, subclasses may override
   */
  protected boolean equalsRectangle(Rectangle other) {
    return false; //by default "this" shape is not equal to a rectangle
  }

  /**
   * Determine whether this shape is equal to a square object
   *
   * @param other the square object to which this shape must be compared
   * @return false by default, subclasses may override
   */
  protected boolean equalsSquare(Square other) {
    return false; //by default "this" shape is not equal to a square
  }
}
