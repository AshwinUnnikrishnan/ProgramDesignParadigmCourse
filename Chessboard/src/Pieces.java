/*
 * Created by histravelstories.
 * This is a class
 * Date : 9/26/22
 * Project Name : Chessboard
 */

/**
 * Abstract class for chessPieces to store common functionalities and then provide static functions
 * that are to be implemented by classes that extend this.
 */
abstract class Pieces implements ChessPiece {
  protected final int row;
  protected final int col;
  protected final Color color;

  /**
   * Constructor to initialize variables in the abstract class.
   *
   * @param row   row position of piece
   * @param col   col position of piece
   * @param color color of the chess piece
   * @throws IllegalArgumentException if invalid inputs throws this exception
   */
  Pieces(int row, int col, Color color) throws IllegalArgumentException {
    // Any chessboard the pieces cannot be negative indexed
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Illegal position");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  @Override
  public int getRow() {
    return row;
  }

  @Override
  public int getColumn() {
    return col;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    return (this.getColor() != piece.getColor()) && canMove(piece.getRow(), piece.getColumn());
  }

  /**
   * Method to check if the row and col after move is valid or not.
   *
   * @param row  row value to check
   * @param col  col value to check
   * @param size size of the chessboard
   * @return true if it is invalid move and false for valid move
   */
  protected Boolean checkInvalidMove(int row, int col, int size) {
    if ((row < 0) || (col < 0) || (row >= size) || (col >= size)) {
      return true;
    }
    return false;
  }
}
