/**
 * Class for Rook ChessPiece.
 */
public class Rook extends Pieces {

  /**
   * Constructor to initialize current row, col and color of the Rook.
   *
   * @param row   row position of piece
   * @param col   col position of piece
   * @param color color of the chess piece
   * @throws IllegalArgumentException if invalid inputs throws this exception
   */
  public Rook(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  @Override
  public boolean canMove(int row, int col) {
    if (checkInvalidMove(row, col, 8)) {
      return false;
    }
    return ((this.row == row) || (this.col == col));
  }

}
