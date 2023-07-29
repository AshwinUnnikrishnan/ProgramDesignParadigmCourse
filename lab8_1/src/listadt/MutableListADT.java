package listadt;

import java.util.function.Function;

/**
 * This class represents a mutable list and its functionalities.
 *
 * @param <T> Datatype of the list.
 */
public interface MutableListADT<T> extends ListADT<T> {

  /**
   * This function returns the immutable counterpart of the mutable class.
   *
   * @return mutable class.
   */
  ImmutableListADT<T> getImmutableList();

  /**
   * maps one list to another list.
   *
   * @param converter the function that converts T into R
   * @param <R>       the map function.
   * @return type R list.
   */
  <R> MutableListADT<R> map(Function<T, R> converter);
}
