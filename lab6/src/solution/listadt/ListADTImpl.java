package solution.listadt;

import java.util.LinkedList;
import java.util.function.Function;

/**
 * This is the implementation of a generic list. Specifically it implements
 * the listadt.ListADT interface
 */
public class ListADTImpl<T> implements ListADT<T> {
  private GenericListADTNode<T> head;

  public ListADTImpl() {
    head = new GenericEmptyNode();
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
  public <R> ListADT<R> map(Function<T, R> converter) {
    LinkedList<Pair<String, GenericListADTNode<T>>> tS = new LinkedList<>();
    LinkedList<Integer> rS = new LinkedList<>();
    ListADT current = new ListADTImpl();
    tS.push(new Pair<>("recur", head));
    while (!tS.isEmpty()) {
      Pair temp = tS.pop();
      if (temp.sObject.equals("recur")) {
        if (temp.tObject instanceof GenericEmptyNode) {
          //simply process this node, no recursive call
          tS.push(new Pair<>("process", (GenericEmptyNode) temp.tObject));
        }

        if (temp.tObject instanceof GenericElementNode) {
          //first make the recursive call, then process this node (add 1 to result)
          //added in reverse order so that they come out of stack in correct order
          tS.push(new Pair<>("process", (GenericListADTNode<T>) temp.tObject));
          rS.push(((String) (((GenericElementNode) temp.tObject).getData())).length());
          tS.push(new Pair<>("recur", ((GenericElementNode) temp.tObject).getRest()));
        }
      }
      if (temp.sObject.equals("process")) {
        if (temp.tObject instanceof GenericElementNode) {
          current.addFront(rS.pop());
        }
      }
    }
    return current;
  }

  @Override
  public int getSize() {
    LinkedList<Pair<String, GenericListADTNode<T>>> tS = new LinkedList<>();
    LinkedList<Integer> rS = new LinkedList<>();

    tS.push(new Pair<>("recur", head));
    while (!tS.isEmpty()) {
      Pair temp = tS.pop();
      if (temp.sObject.equals("recur")) {
        if (temp.tObject instanceof GenericEmptyNode) {
          //simply process this node, no recursive call
          tS.push(new Pair<>("process", (GenericEmptyNode) temp.tObject));
        }
        if (temp.tObject instanceof GenericElementNode) {
          //first make the recursive call, then process this node (add 1 to result)
          //added in reverse order so that they come out of stack in correct order
          tS.push(new Pair<>("process", (GenericListADTNode<T>) temp.tObject));
          tS.push(new Pair<>("recur", ((GenericElementNode) temp.tObject).getRest()));
        }
      }
      if (temp.sObject.equals("process")) {
        if (temp.tObject instanceof GenericEmptyNode) {
          //an empty node simply returns 0, as per above implementation
          rS.push(0);
        }
        if (temp.tObject instanceof GenericElementNode) {
          //add 1 to the "last result", as per above implementation
          int num = rS.pop();
          rS.push(1 + num);
        }
      }
    }
    return rS.pop(); //the last thing remaining is the final result
  }

  @Override
  public void remove(T b) {
    head = head.remove(b);
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    if ((index >= 0) && (index < getSize())) {
      return head.get(index);
    } else {
      throw new IllegalArgumentException("Invalid index");
    }

  }


  @Override
  public String toString() {
    return "(" + head.toString() + ")";
  }

  /**
   * New generic class which is an inner helper class in ListADTImpl .
   * It stores two fields: one of type S and another of type T.
   *
   * @param <S> First part of the pair
   * @param <T> Second part of the pair storing the next address
   */
  public class Pair<S, T> {
    S sObject;
    T tObject;

    public Pair(S s, T t) {
      this.sObject = s;
      this.tObject = t;
    }

  }
}


