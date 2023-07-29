import java.util.function.Function;

/**
 * Interface to represent mutable list adt.
 *
 * @param <T> Data type of list
 */
public interface MutableListADT<T> extends ListADT<T> {


  /**
   * Method to return a immutable list of the mutable list.
   *
   * @return immutable list.
   */
  ImmutableListADT<T> getImmutableList();


  /**
   * A method that returns a MutableListADT object by looping through this list and creating
   * a mapped list.
   *
   * @param converter the function that converts T into R
   * @param <R>       type of returned immutable list adt.
   * @return mutable list adt.
   */
  <R> MutableListADT<R> map(Function<T, R> converter);
}
