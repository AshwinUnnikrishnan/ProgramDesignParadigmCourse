/**
 * Class to test Queen Chess Pieces.
 */
public class QueenTest extends AbstractChessPieceTest {

  @Override
  ChessPiece createObj(int row, int col, Color c) {
    return new Queen(row, col, c);
  }

  @Override
  protected void setupResults(int row, int col) {
    for (int i = 0; i < 8; i++) {
      results[i][col] = true;
      results[row][i] = true;
      if ((row + i) < 8) {
        if ((col + i) < 8) {
          results[row + i][col + i] = true;
        }
        if (col >= i) {
          results[row + i][col - i] = true;
        }
      }
      if (row >= i) {
        if ((col + i) < 8) {
          results[row - i][col + i] = true;
        }
        if (col >= i) {
          results[row - i][col - i] = true;
        }
      }
    }
  }
}