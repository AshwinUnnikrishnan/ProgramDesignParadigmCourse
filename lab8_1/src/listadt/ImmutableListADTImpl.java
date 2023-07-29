package listadt;

import java.util.function.Function;

/**
 * This class implements a Immutable list adt.
 *
 * @param <T> the datatype of the list.
 */
public class ImmutableListADTImpl<T> implements ImmutableListADT<T> {

  ListADT<T> list = new ListADTImpl<>();

  @Override
  public MutableListADT<T> getMutableList() {
    MutableListADT<T> mutableListADT = new MutableListADTImpl<T>();
    for (int i = 0; i < this.getSize(); i++) {
      mutableListADT.addBack(this.get(i));
    }
    return mutableListADT;
  }

  private void addBack(T b) {
    this.list.addBack(b);
  }

  @Override
  public <R> ImmutableListADT<R> map(Function<T, R> converter) {
    ImmutableListADTImplBuilder<R> b = new ImmutableListADTImplBuilder();
    for (int i = 0; i < list.getSize(); i++) {
      b = b.addBack(converter.apply(this.get(i)));
    }
    return b.build();
  }

  @Override
  public int getSize() {
    return list.getSize();
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    return list.get(index);
  }

  @Override
  public String toString() {
    return list.toString();
  }

  /**
   * This class builds a ImmutableListADTImpl class.
   *
   * @param <T> datatype of the string.
   */
  public static class ImmutableListADTImplBuilder<T> {

    ImmutableListADTImpl<T> bo = new ImmutableListADTImpl<T>();

    /**
     * This function adds an element to the back of the list.
     *
     * @param b the element to be added.
     * @return the current object.
     */
    public ImmutableListADTImplBuilder addBack(T b) {
      bo.addBack(b);
      return this;
    }

    /**
     * This builds a final object.
     *
     * @return an ImmutableList object.
     */
    public ImmutableListADTImpl<T> build() {
      return bo;
    }
  }

}
