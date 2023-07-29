/**
 * Class for Knight ChessPiece.
 */
public class Knight extends Pieces {

  /**
   * Constructor to initialize current row, col and color of the Knight.
   *
   * @param row   row position of piece
   * @param col   col position of piece
   * @param color color of the chess piece
   * @throws IllegalArgumentException if invalid inputs throws this exception
   */
  public Knight(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  @Override
  public boolean canMove(int row, int col) {
    if (checkInvalidMove(row, col, 8)) {
      return false;
    }
    return ((Math.abs(this.row - row) == 2 && Math.abs(this.col - col) == 1) ||
            (Math.abs(this.row - row) == 1 && Math.abs(this.col - col) == 2));
  }

}
