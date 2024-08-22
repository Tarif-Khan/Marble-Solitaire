package cs3500.marblesolitaire.model.hw04;

/**
 * This is the class for the European model.
 */
public class EuropeanSolitaireModel extends AbstractModel {

  /**
   * This is the constructor for the default.
   */
  public EuropeanSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * Creates a game with empty space on passed in row and col.
   *
   * @param r - the row for empty space.
   * @param c - the column for empty space.
   * @throws IllegalArgumentException if the row or column are invalid.
   */
  public EuropeanSolitaireModel(int r, int c) throws IllegalArgumentException {
    super(3, r, c);
  }

  /**
   * Makes the game with given arm thickness.
   *
   * @param a - the arm thickness of the board.
   * @throws IllegalArgumentException - this is thrown if the a is less than 3 or even.
   */
  public EuropeanSolitaireModel(int a) throws IllegalArgumentException {
    super(a, (a + ((a - 1) * 2)) / 2,
            (a + ((a - 1) * 2)) / 2);
  }

  /**
   * Creates a game with the parameters passed in.
   *
   * @param a - the arm thickness of the board.
   * @param r - the row for empty space.
   * @param c - the column for empty space.
   * @throws IllegalArgumentException - this is thrown if the a is less than 3 or even
   *                                  or if the slotstate at r, c is invalid.
   */
  public EuropeanSolitaireModel(int a, int r, int c) throws IllegalArgumentException {
    super(a, r, c);
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (!this.isValidMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("Please make a valid move");
    }
    if (this.isValidMove(fromRow, fromCol, toRow, toCol)) {
      board.get(fromRow).set(fromCol, SlotState.Empty);
      board.get(toRow).set(toCol, SlotState.Marble);
      if (fromRow == toRow) {
        board.get(fromRow).set((Math.max(fromCol, toCol)) - 1, SlotState.Empty);
      } else if (fromCol == toCol) {
        board.get((Math.max(fromRow, toRow)) - 1).set(fromCol, SlotState.Empty);
      }
    }
  }

  @Override
  protected boolean isInvalidSlot(int r, int c) {
    for (int i = 0; i < this.armThickness - 1; i++) {
      if ((r == i) && (c < armThickness - 1 - i || c >= this.getBoardSize()
              - this.armThickness + 1 + i)) {
        return true;
      }
    }
    for (int j = this.getBoardSize() - armThickness + 1; j < this.getBoardSize(); j++) {
      if ((r == j) && (c < armThickness - (this.getBoardSize() - j) || c >= this.getBoardSize() -
              (armThickness - (this.getBoardSize() - j)))) {
        return true;
      }
    }
    return false;
  }
}

