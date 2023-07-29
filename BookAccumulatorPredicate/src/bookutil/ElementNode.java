package bookutil;

import java.util.function.Predicate;

/**
 * This represents a non-empty node of the list. It contains a piece of data
 * along with the rest of the list
 */
public class ElementNode implements IListOfBooks {
  private Book book;
  private IListOfBooks rest;

  public ElementNode(Book p, IListOfBooks rest) {
    this.book = p;
    this.rest = rest;
  }

  @Override
  //public int count() { return 1+this.rest.count();}
  public int count() { return countHelp(0);}

  public int countHelp(int acc) {
    return this.rest.countHelp(1+acc);
  }

  @Override
  public float totalPrice() { return this.book.getPrice() + this.rest
          .totalPrice();}

  @Override
  public IListOfBooks allBefore(int year) {
    if (book.before(year)) {
      return new ElementNode(this.book,this.rest.allBefore(year));
    }
    else {
      return this.rest.allBefore(year);
    }
  }

  @Override
  public IListOfBooks getList(Predicate<Book> test) {
    if (test.test(book)) {
      return new ElementNode(this.book,this.rest.getList(test));
    }
    else {
      return this.rest.getList(test);
    }
  }

  @Override
  public IListOfBooks sortByPrice() {
    return this.rest.sortByPrice().insert(this.book);
  }

  @Override
  public IListOfBooks insert(Book book) {
    if (this.book.cheaperThan(book)) {
      return new ElementNode(this.book,this.rest.insert(book));
    }
    else {
      return new ElementNode(book,this);
    }
  }

  @Override
  public String toString() {
    return "("+this.book.toString()+")"+this.rest.toString();
  }


}
