import java.util.function.Function;


/**
 * Interface to represent immutable list adt.
 *
 * @param <T> Data type of list
 */
public interface ImmutableListADT<T> extends CommonListADT<T> {

  /**
   * Method to return a mutable list of the immatable list.
   *
   * @return mutable list.
   */
  MutableListADT<T> getMutableList();

  /**
   * A method that returns an ImmutableListADT object by looping through this list and creating
   * a mapped list.
   *
   * @param converter the function that converts T into R
   * @param <R>       type of returned immutable list adt.
   * @return immutable list adt.
   */
  <R> ImmutableListADT<R> map(Function<T, R> converter);
}
