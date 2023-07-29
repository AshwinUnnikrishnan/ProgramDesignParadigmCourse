/**
 * This interface contains all operations that all types of shapes should
 * support.
 */
public interface Shape extends Comparable<Shape> {

  /**
   * Returns the distance of this shape from the origin. The distance is
   * measured from whatever reference position a shape is (e.g. a center for a
   * circle)
   *
   * @return the distance from the origin
   */
  double distToOrigin();

  /**
   * Computes and returns the area of this shape.
   *
   * @return the area of the shape
   */
  double area();

  /**
   * Computes and returns the perimeter of this shape.
   *
   * @return the perimeter of the shape
   */
  double perimeter();

  /**
   * Create and return a shape of the same kind as this one, resized in area by
   * the provided factor
   *
   * @param factor factor of resizing
   * @return the resized Shape
   */
  Shape resize(double factor);
}
