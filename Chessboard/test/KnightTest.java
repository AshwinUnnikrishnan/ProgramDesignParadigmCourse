/**
 * Class to test Knight Chess Pieces.
 */
public class KnightTest extends AbstractChessPieceTest {
  @Override
  ChessPiece createObj(int row, int col, Color c) {
    return new Knight(row, col, c);
  }

  @Override
  protected void setupResults(int row, int col) {
    if (row + 2 < 8 && col + 1 < 8) {
      results[row + 2][col + 1] = true;
    }
    if (row + 2 < 8 && col - 1 >= 0) {
      results[row + 2][col - 1] = true;
    }
    if (row + 1 < 8 && col + 2 < 8) {
      results[row + 1][col + 2] = true;
    }
    if (row + 1 < 8 && col - 2 >= 0) {
      results[row + 1][col - 2] = true;
    }
    if (row - 2 >= 0 && col + 1 < 8) {
      results[row - 2][col + 1] = true;
    }
    if (row - 2 >= 0 && col - 1 >= 0) {
      results[row - 2][col - 1] = true;
    }
    if (row - 1 >= 0 && col + 2 < 8) {
      results[row - 1][col + 2] = true;
    }
    if (row - 1 >= 0 && col - 2 >= 0) {
      results[row - 1][col - 2] = true;
    }
  }
}