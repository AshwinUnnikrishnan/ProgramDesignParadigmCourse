/**
 * Class to test Rook Chess Pieces.
 */
public class RookTest extends AbstractChessPieceTest {
  @Override
  ChessPiece createObj(int row, int col, Color c) {
    return new Rook(row, col, c);
  }

  @Override
  protected void setupResults(int row, int col) {
    for (int i = 0; i < 8; i++) {
      results[i][col] = true;
      results[row][i] = true;
    }
  }
}