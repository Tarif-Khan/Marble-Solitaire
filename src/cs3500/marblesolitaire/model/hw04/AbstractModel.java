package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This is an abstract class which will serve as an abstract class for all shapes of boards
 * where the method is not affected by whatever shapes the board takes place.
 */
public abstract class AbstractModel implements MarbleSolitaireModel {

  protected final int armThickness;
  protected final int sRow;
  protected final int sColumn;
  ArrayList<ArrayList<SlotState>> board;


  /**
   * This is the constructor for an Abstract Model.
   *
   * @param a - this is the arm thickness.
   * @param r - this is the row.
   * @param c - this is the colomn.
   * @throws IllegalArgumentException - this is thrown if conditions are not met.
   */
  protected AbstractModel(int a, int r, int c)
          throws IllegalArgumentException {
    if (this.oddValue(a)) {
      throw new IllegalArgumentException("Please positive odd integer as arm thickness");
    }
    this.armThickness = a;
    if (this.isInvalidSlot(r, c)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + r + "," + c + ")");
    }
    this.sRow = r;
    this.sColumn = c;
    this.initBoard();
  }

  /**
   * This is for the triangle where it only takes in arm thickness.
   *
   * @param a - this is the arm thickness.
   */
  protected AbstractModel(int a) {
    this.armThickness = a;
    this.sRow = 0;
    this.sColumn = 0;
    this.initBoard();
  }

  protected abstract boolean isInvalidSlot(int r, int c);

  /**
   * This is the move method which has to be in all games.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException - this is thrown if the marble is on board.
   */
  public abstract void move(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException;

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
        }
      }
    }
    return true;
  }

  /**
   * This implementation for isValid move remains the same for European Solitaire model.
   *
   * @param row    - this is the row of the original.
   * @param col    - this is the column  of the original.
   * @param newRow - this is the new row.
   * @param newCol - this is the new column.
   * @return - it returns a boolean to check whether the valid move can be made or not.
   */
  protected boolean isValidMove(int row, int col, int newRow, int newCol) {
    return 0 <= row && row < this.getBoardSize()
            && 0 <= col && col < this.getBoardSize()
            && 0 <= newRow && newRow < this.getBoardSize()
            && 0 <= newCol && newCol < this.getBoardSize()
            && (row == newRow || col == newCol)
            && !(row == newRow && col == newCol)
            && (Math.abs(row - newRow) == 2 || Math.abs(col - newCol) == 2)
            && !(Math.abs(row - newRow) == 2 && Math.abs(col - newCol) == 2)
            && this.getSlotAt(newRow, newCol) == SlotState.Empty
            && this.getSlotAt(row, col) == SlotState.Marble
            && (row == newRow
            ? this.getSlotAt(row, Math.max(col, newCol) - 1) == SlotState.Marble
            : this.getSlotAt((Math.max(row, newRow)) - 1, col) == SlotState.Marble);
  }

  @Override
  public int getBoardSize() {
    return this.armThickness + (2 * (this.armThickness - 1));
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0 || (row >= this.getBoardSize())
            || col >= this.getBoardSize()) {
      throw new IllegalArgumentException("Please use a valid row/column.");
    }
    return this.board.get(row).get(col);
  }

  @Override
  public int getScore() {
    int score = 0;
    for (int i = 0; i < (this.getBoardSize()); i++) {
      for (int j = 0; j < (this.getBoardSize()); j++) {
        if (this.getSlotAt(i, j) == SlotState.Marble) {
          score++;
        }
      }
    }
    return score;
  }

  /**
   * This method is needed for the board to be initialized.
   */
  protected void initBoard() {
    {
      this.board = new ArrayList<ArrayList<SlotState>>();
      for (int i = 0; i < this.getBoardSize(); i++) {
        ArrayList<SlotState> row = new ArrayList<>();
        for (int j = 0; j < this.getBoardSize(); j++) {
          if (i == this.sRow && j == this.sColumn) {
            row.add(SlotState.Empty);
          } else if (this.isInvalidSlot(i, j)) {
            row.add(SlotState.Invalid);
          } else {
            row.add(SlotState.Marble);
          }
        }
        this.board.add(row);
      }
    }
  }

  protected boolean oddValue(int a) {
    return (a <= 2 || a % 2 == 0);
  }
}
