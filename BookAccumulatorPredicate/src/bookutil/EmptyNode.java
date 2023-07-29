package bookutil;

import java.util.function.Predicate;

//import listadt.BookListADTNode;

/**
 * This represents an empty node in the list
 */
public class EmptyNode implements IListOfBooks {

  @Override
  public int count(){return 0;}

  public int countHelp(int acc) {
    return acc;
  }

  @Override
  public float totalPrice() {return 0.0f;}

  @Override
  public IListOfBooks allBefore(int year) { return new EmptyNode();}

  @Override
  public IListOfBooks getList(Predicate<Book> test) { return new EmptyNode();}

  @Override
  public IListOfBooks sortByPrice() { return new EmptyNode();}

  @Override
  public IListOfBooks insert(Book book) { return new ElementNode(book,this);}

  @Override
  public String toString() {
    return "";
  }


}
