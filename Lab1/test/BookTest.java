import org.junit.Before;
import org.junit.Test;

import cs5010.hw01.publication.Book;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertEquals;


/**
 * A JUnit test class for the Book class.
 */
public class BookTest {

  private Book govtDocument;
  public Book publicNotice;

  @Before
  public void setUp() {

    govtDocument = new Book("TopSecret", "President", "GOVT", "Somewhere", 2087);
    publicNotice = new Book("PublicNotice", "Librarian", "NEU", "Boston", 2010);

  }

  /**
   * Test to make sure constructor constructs objects with provided arguments rather than default
   * arguments.
   */
  @Test
  public void whenAssertingNotSameObject_thenDifferent() {
    assertNotSame(govtDocument, publicNotice);
  }

  /**
   * Test to check if the citeApa() function returns string in the expected format of the citeApa
   * from the inputs initialized by constructor.
   */
  @Test
  public void testCiteApa() {
    String expectedOutput = "President (2087). TopSecret. Somewhere: GOVT.";
    assertEquals(expectedOutput, govtDocument.citeApa());
  }

  /**
   * Test to check if the citeMla() function returns string in the expected format of the citeMla
   * from the inputs initialized by constructor.
   */
  @Test
  public void testCiteMla() {
    String expectedOutput = "President. TopSecret. Somewhere: GOVT, 2087.";
    govtDocument.citeMla();
    assertEquals(expectedOutput, govtDocument.citeMla());
  }
}