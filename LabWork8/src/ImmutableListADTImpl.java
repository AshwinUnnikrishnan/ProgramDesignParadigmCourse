import java.util.function.Function;

/**
 * Class to represent immutable list adt it implements immutableListADT.
 *
 * @param <T> Data type of list
 */
public class ImmutableListADTImpl<T> implements ImmutableListADT<T> {

  ListADT<T> listObj = new ListADTImpl<>();

  @Override
  public MutableListADT<T> getMutableList() {
    MutableListADT<T> mutListADT = new MutableListADTImpl<T>();
    for (int i = 0; i < this.getSize(); i++) {
      mutListADT.addBack(this.get(i));
    }
    return mutListADT;
  }

  /**
   * Private helper method to add the contents to the immutable List ADT. Used from builder
   * function.
   *
   * @param node object to add.
   */
  private void addBack(T node) {
    this.listObj.addBack(node);
  }

  @Override
  public <R> ImmutableListADT<R> map(Function<T, R> converter) {
    ImmutableListADTImplBuilder<R> node = new ImmutableListADTImplBuilder();
    for (int i = 0; i < this.listObj.getSize(); i++) {
      node = node.addBack(converter.apply(this.get(i)));
    }
    return node.build();
  }

  @Override
  public int getSize() {
    return this.listObj.getSize();
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    return this.listObj.get(index);
  }

  @Override
  public String toString() {
    return this.listObj.toString();
  }

  /**
   * Helper class to build ImmutableListADT impl.
   *
   * @param <T> datatype of the node.
   */
  public static class ImmutableListADTImplBuilder<T> {

    ImmutableListADTImpl<T> builderImmutableADTImlp = new ImmutableListADTImpl<>();

    /**
     * Method to add element to the end of the list.
     *
     * @param node the element to be added.
     * @return builderObject.
     */
    public ImmutableListADTImplBuilder addBack(T node) {
      builderImmutableADTImlp.addBack(node);
      return this;
    }

    /**
     * Method to build the final object.
     *
     * @return an ImmutableList object.
     */
    public ImmutableListADTImpl<T> build() {
      return builderImmutableADTImlp;
    }
  }

}

