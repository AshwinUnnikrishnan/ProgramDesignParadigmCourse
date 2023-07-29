package listadt;

import java.util.function.Function;
import listadt.ImmutableListADTImpl.ImmutableListADTImplBuilder;

/**
 * This class implements the Mutable list adt interface.
 *
 * @param <T> datatype of the list.
 */
public class MutableListADTImpl<T> extends ListADTImpl<T> implements MutableListADT<T> {

  /**
   * this constructor initializes the class and the super class.
   */
  public MutableListADTImpl() {
    super();
  }

  /**
   * this constructor initializes the class and the super class with the head node.
   */
  private MutableListADTImpl(GenericListADTNode<T> head) {
    this.head = head;
  }

  @Override
  public ImmutableListADT<T> getImmutableList() {
    ImmutableListADTImplBuilder immutableListADTBuilder =
        new ImmutableListADTImplBuilder();
    for (int i = 0; i < this.getSize(); i++) {
      immutableListADTBuilder = immutableListADTBuilder.addBack(get(i));
    }
    return immutableListADTBuilder.build();
  }

  @Override
  public <R> MutableListADT<R> map(Function<T, R> converter) {
    return new MutableListADTImpl<>(head.map(converter));
  }
}
