package listadt;

import java.util.function.Function;

/**
 * This is the implementation of a generic list. Specifically it implements the listadt.ListADT
 * interface
 */
public class ListADTImpl<T> implements ListADT<T> {

  protected GenericListADTNode<T> head;

  /**
   * This initialises a list object.
   */
  public ListADTImpl() {
    head = new GenericEmptyNode<T>();
  }

  //a private constructor that is used internally (see map)
  private ListADTImpl(GenericListADTNode<T> head) {
    this.head = head;
  }

  @Override
  public void addFront(T b) {
    head = head.addFront(b);
  }

  @Override
  public void addBack(T b) {
    head = head.addBack(b);
  }

  @Override
  public void add(int index, T b) {
    head = head.add(index, b);
  }

  @Override
  public int getSize() {
    return head.count();
  }

  @Override
  public void remove(T b) {
    head = head.remove(b);
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    if ((index >= 0) && (index < getSize())) {
      return head.get(index);
    }
    throw new IllegalArgumentException("Invalid index");

  }

  @Override
  public <R> ListADT<R> map(Function<T, R> converter) {
    return new ListADTImpl(head.map(converter));
  }

  @Override
  public String toString() {
    return "(" + head.toString() + ")";
  }
}
