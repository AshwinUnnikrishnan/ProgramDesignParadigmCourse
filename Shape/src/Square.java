/**
 * This class represents a square shape. Since a square is a special instance of
 * rectangle, this class extends the {@link Rectangle} class. However in the
 * context of the application, a square is a separate kind of shape, and
 * therefore a square may not be deemed as equal to a rectangle of identical
 * dimensions.
 */
public class Square extends Rectangle {

  public Square(double x, double y, double side) {
    super(x, y, side, side);
  }

  public String toString() {
    return String.format("Square: LL corner (%.3f,%.3f) side %.3f",
            this.reference.getX(), this.reference.getY(), this.width);
  }


  @Override
  protected boolean equalsSquare(Square other) {
    return this.reference.equals(other.reference)
           && Math.abs(this.width - other.width) < 0.001
           && Math.abs(this.height - other.height) < 0.001;
  }

  /**
   * This method must be overridden to default back to returning false. This is
   * because the Rectangle method overrode this, and this class inherits it!
   *
   * @param other the square object to which this shape must be compared
   */
  protected boolean equalsRectangle(Rectangle other) {
    return false; //by default "this" shape is not equal to a rectangle
  }


  public boolean equals(Object other) {
    if (other instanceof AbstractShape) {
      AbstractShape ashape = (AbstractShape) other;
      //we know that this is a square, so we check rectangle equality
      return ashape.equalsSquare(this);
    }
    return false; //since it is not AbstractShape it is not a square either,
    // so return false
  }

}
