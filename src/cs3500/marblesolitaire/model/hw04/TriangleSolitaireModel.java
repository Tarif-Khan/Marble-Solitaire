package cs3500.marblesolitaire.model.hw04;

/**
 * This is the class for a triangle Solitaire model.
 */
public class TriangleSolitaireModel extends AbstractModel {

  /**
   * This is the default constructor for the triangle model.
   */
  public TriangleSolitaireModel() {
    super(5, 0, 0);
  }

  /**
   * This is the constructor which only takes in arm thickness.
   *
   * @param a - arm thickness.
   * @throws IllegalArgumentException - this is thrown if conditions are not met.
   */
  public TriangleSolitaireModel(int a) {
    super(a);
  }

  /**
   * This is the constructor which takes in row/col only.
   *
   * @param r - row of the empty.
   * @param c - col of the empty.
   * @throws IllegalArgumentException - thrown if col/row invalid.
   */
  public TriangleSolitaireModel(int r, int c) throws IllegalArgumentException {
    super(5, r, c);
  }


  /**
   * Constructor which takes in all 3 items.
   *
   * @param a - this is armthickness.
   * @param r - this is row.
   * @param c - this is col.
   * @throws IllegalArgumentException - thrown if col/row invalid.
   */
  public TriangleSolitaireModel(int a, int r, int c) throws IllegalArgumentException {
    super(a, r, c);

  }

  @Override
  public int getBoardSize() {
    return this.armThickness;
  }


  /**
   * This method checks for the marble and an empty space.
   *
   * @param row    the original row.
   * @param col    the original col.
   * @param newRow the new row.
   * @param newCol the new col.
   * @return true if there is a marble and empty after.
   */
  protected boolean checkSpaces(int row, int col, int newRow, int newCol) {
    boolean answer;
    try {
      answer = this.getSlotAt(row, col).equals(SlotState.Marble) && this.getSlotAt(newRow,
              newCol).equals(SlotState.Empty);
    } catch (IllegalArgumentException i) {
      answer = false;
    }
    return answer;
  }

  @Override
  protected boolean isValidMove(int row, int col, int newRow, int newCol) {
    if (!(this.getSlotAt(row, col).equals(SlotState.Marble))) {
      return false;
    }
    return this.checkSpaces(row + 1, col + 1, row + 2, col + 2)
            || this.checkSpaces(row + 1, col - 1, row + 2, col - 2)
            || this.checkSpaces(row - 1, col + 1, row - 2, col + 2)
            || this.checkSpaces(row - 1, col - 1, row - 2, col - 2)
            || this.checkSpaces(row + 1, col, row + 2, col)
            || this.checkSpaces(row, col - 1, row, col - 2)
            || this.checkSpaces(row, col + 1, row, col + 2)
            || this.checkSpaces(row - 1, col, row - 2, col);
  }

  /**
   * Meant to help see if row/col is the same or if I need to math.max for the middle.
   *
   * @param from - original position input.
   * @param to   - new position input.
   * @return - integer which represents whether it can be done.
   */
  protected int findJumpOver(int from, int to) {
    if (from == to) {
      return from;
    } else {
      return Math.max(to, from) - 1;
    }
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    this.moveHelper(fromRow, fromCol, toRow, toCol);
    int jumpedOverRow = this.findJumpOver(fromRow, toRow);
    int jumpedOverCol = this.findJumpOver(fromCol, toCol);
    if (this.getSlotAt(fromRow, fromCol).equals(SlotState.Marble) && this.checkSpaces(jumpedOverRow,
            jumpedOverCol, toRow, toCol)) {
      this.board.get(fromRow).set(fromCol, SlotState.Empty);
      this.board.get(jumpedOverRow).set(jumpedOverCol, SlotState.Empty);
      this.board.get(toRow).set(toCol, SlotState.Marble);
    } else {
      throw new IllegalArgumentException("Move cannot be made");
    }
  }

  /**
   * Helper method for move.
   *
   * @param fromRow - original row.
   * @param fromCol - original col.
   * @param toRow   - new row.
   * @param toCol   - new col.
   * @throws IllegalArgumentException - thrown if invalid.
   */
  public void moveHelper(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    if ((fromRow < 0)
            || (fromCol < 0)
            || (toRow < 0)
            || (toCol < 0)
            || (fromRow > this.getBoardSize() - 1)
            || (fromCol > this.board.get(fromCol).size() - 1)
            || (toRow > this.getBoardSize() - 1)
            || (toCol > this.board.get(toRow).size() - 1)
            || (Math.abs(fromRow - toRow) > 2
            || Math.abs(fromCol - toCol) > 2)
            || (Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 1)) {
      throw new IllegalArgumentException("This is not a valid move.");
    }
  }

  @Override
  protected boolean isInvalidSlot(int r, int c) {
    return (r < 0 || c < 0 || r >= this.getBoardSize() || c >= this.getBoardSize() || c > r);
  }

  @Override
  protected boolean oddValue(int a) {
    return false;
  }


  @Override
  public boolean isGameOver() {
    if (this.getScore() == 1) {
      return true;
    }
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.getSlotAt(i, j) == SlotState.Marble) {
          if (j <= this.getBoardSize() - this.armThickness) {
            if (this.isValidMove(i, j, i, j + 2)) {
              return false;
            }
          }
          if (i >= this.armThickness - 1) {
            if (this.isValidMove(i, j, i - 2, j)) {
              return false;
            }
          }
          if (i <= this.getBoardSize() - this.armThickness) {
            if (this.isValidMove(i, j, i + 2, j)) {
              return false;
            }
          }
          if (j >= this.armThickness - 1) {
            if (this.isValidMove(i, j, i, j - 2)) {
              return false;
            }
          }
          if (this.isValidMove(i, j, i + 2, j + 2)) {
            return false;
          }
          if (this.isValidMove(i, j, i - 2, j - 2)) {
            return false;
          }
        }
      }
    }
    return true;
  }
}
