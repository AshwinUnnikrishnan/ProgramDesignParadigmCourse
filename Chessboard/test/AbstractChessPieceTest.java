/*
 * Created by histravelstories.
 * This is a class
 * Date : 9/26/22
 * Project Name : Chessboard
 */

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Abstract class to store common test cases.
 */
abstract class AbstractChessPieceTest {
  protected boolean[][] results;

  /**
   * Abstract function that will be later implemented by the classes that extends this, to create
   * respective objects.
   *
   * @param row row position of the piece
   * @param col col position of th piece
   * @param c   color of the piece
   * @return newly created object reference.
   */
  abstract ChessPiece createObj(int row, int col, Color c);

  /**
   * Method to set up valid positions in the chessboard for a particular chesspiece, based on the
   * input row and col.
   *
   * @param row row position of the piece
   * @param col col position of the piece
   */
  abstract void setupResults(int row, int col);

  /**
   * Method to check if canMove or canKill method works based on the input parameter functionName.
   *
   * @param functionName Function that has to be verified.
   */
  private String createVariousPositions(String functionName) {
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        initializeResults();
        ChessPiece piece = createObj(row, col, Color.BLACK);
        setupResults(row, col);
        for (int i = 0; i < 8; i++) {
          for (int j = 0; j < 8; j++) {
            if ((i == piece.getRow()) && (j == piece.getColumn())) {
              continue;
            }
            try {
              if (functionName.equals("canKill")) {
                ChessPiece another = new Rook(i, j, Color.values()[(piece.getColor().ordinal() + 1)
                        % Color.values().length]);
                assertEquals("Unexpected canKill result for " + "i=" + i + " j=" + j + "",
                        results[i][j], piece.canKill(another));
              } else if (functionName.equals("canMove")) {
                assertEquals("Piece at :" + piece.getRow() + "," + piece.getColumn() +
                                ", Unexpected canMove result " + "for " + "i=" + i + " j=" + j + "",
                        results[i][j], piece.canMove(i, j));
              }
            } catch (AssertionError e) {
              return e.getMessage();
            }
          }
        }
      }
    }
    return "Pass";
  }

  /**
   * Setup to be performed before each test case.
   */
  @Before
  public void setup() {
    results = new boolean[8][8];
  }

  /**
   * Method to initialize all the cells of chess as false.
   */
  private void initializeResults() {
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        results[row][col] = false;
      }
    }
  }

  /**
   * Test to check if the row, col, color is set based on the values provided to the constructor.
   */
  @Test(timeout = 500)
  public void testGetters() {
    ChessPiece piece;
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        for (Color c : Color.values()) {
          piece = createObj(row, col, c);
          assertEquals("Row number does not match what was initialized", row,
                  piece.getRow());
          assertEquals("Column number does not match what was initialized", col,
                  piece.getColumn());
          assertEquals("solution.Color does not match what was initialized", c,
                  piece.getColor());
        }
      }
    }
  }

  /**
   * Test to check if invalidArgumentException is thrown when we try to create chess board with
   * invalid positions.
   */
  @Test(timeout = 500)
  public void testInvalidConstructions() {
    for (Color c : Color.values()) {
      for (int i = 0; i < 8; i++) {
        try {
          createObj(i, -1, c);
          fail("Did not throw an exception when object is created with invalid row");
        } catch (IllegalArgumentException e) {
          //System.out.println("Threw exception when col is -ve while creating Chess Piece");
        }
        try {
          createObj(-1, i, c);
          fail("Did not throw an exception when object is created with invalid column");
        } catch (IllegalArgumentException e) {
          //System.out.println("Threw exception when row is -ve while creating Chess Piece");
        }
      }
    }
  }

  /**
   * Test to check if canKill method works properly. For each of the 8*8 combinations we initialize
   * results and then fill the known positions it can move and then check against the results of
   * canKill. It should match.
   */
  @Test(timeout = 500)
  public void testChessPieceKills() {
    assertEquals("Pass", createVariousPositions("canKill"));
  }

  /**
   * Test to check if canMove method works properly. For each of the 8*8 combinations we initialize
   * results and then fill the known positions it can move and then check against the results of
   * canMove. It should match.
   */
  @Test(timeout = 500)
  public void testObjectMoves() {
    assertEquals("Pass", createVariousPositions("canMove"));
  }
}
