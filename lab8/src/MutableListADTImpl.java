import java.util.function.Function;

/**
 * Class to implement mutable list adt.
 *
 * @param <T> Data type of list
 */
public class MutableListADTImpl<T> extends ListADTImpl<T> implements MutableListADT<T> {

  /**
   * Public contructor to initialize the class.
   */
  public MutableListADTImpl() {
    super();
  }

  /**
   * Private constructor which initialize the class with head.
   */
  private MutableListADTImpl(GenericListADTNode<T> head) {
    this.head = head;
  }

  @Override
  public ImmutableListADT<T> getImmutableList() {
    ImmutableListADTImpl.ImmutableListADTImplBuilder immutableListADTBuilder =
            new ImmutableListADTImpl.ImmutableListADTImplBuilder();
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

