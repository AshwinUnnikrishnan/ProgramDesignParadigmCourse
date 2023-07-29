package listadt;

import java.util.function.Function;

/**
 * This interface represents a Immutable list and its functionalities.
 *
 * @param <T> This represents tha datatype of the list.
 */
public interface ImmutableListADT<T> extends CommonListADT<T> {

  /**
   * returns mutable counterpart of the immutable class.
   *
   * @return mutable counterpart of the immutable class.
   */
  MutableListADT<T> getMutableList();

  /**
   * creates a map using the function.
   *
   * @param converter the function that converts T into R
   * @param <R>       the conversion function.
   * @return the mapped list.
   */
  <R> ImmutableListADT<R> map(Function<T, R> converter);
}
