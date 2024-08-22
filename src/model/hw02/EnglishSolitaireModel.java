package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;

/**
 * This is my class for my English Solitaire Model.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {
  private int armThickness;
  private int sRow;
  private int sColumn;

  ArrayList<ArrayList<SlotState>> board;

  /**
   * This is the first constructor for the board with arm thickness of 3.
   */
  public EnglishSolitaireModel() {
    this(3);
  }

  /**
   * This is the second constructor which takes in sRow and sColumn.
   * throws - an illegal argument exception.
   */
  public EnglishSolitaireModel(int r, int c) throws IllegalArgumentException {
    this(3, r, c);
  }

  /**
   * this is the third constructor for the board.
   *
   * @param a - this is the arm thickness of the board.
   *          throws - illegal argument exception.
   */
  public EnglishSolitaireModel(int a) throws IllegalArgumentException {
    this(a, (a + (2 * (a - 1))) / 2, (a + (2 * (a - 1))) / 2);
  }

  /**
   * this is the fourth constructor for the board.
   *
   * @param a - this is the arm thickness of the board.
   */
  public EnglishSolitaireModel(int a, int r, int c) throws IllegalArgumentException {
    this.armThickness = a;
    if ((a <= 2 || a % 2 == 0)) {
      throw new IllegalArgumentException("Please positive odd integer as arm thickness");
    }
    if (this.isInvalidSlot(r, c)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + r + "," + c + ")");
    }
    this.sRow = r;
    this.sColumn = c;
    this.initBoard();
  }

  /**
   * Checks for invalid slots.
   *
   * @param r - row passed in.
   * @param c - column passed in.
   * @return - true/false depending on the row/column.
   */
  private boolean isInvalidSlot(int r, int c) {
    return ((r < 0)
            || (c < 0)
            || (r >= this.getBoardSize())
            || (c >= this.getBoardSize())
            || (r < this.armThickness - 1 && c < this.armThickness - 1) // top left
            || (r < this.armThickness - 1
            && c >= this.getBoardSize() - this.armThickness + 1)
            || (r >= this.getBoardSize() - this.armThickness + 1
            && c < this.armThickness - 1) // top right
            || (r >= this.getBoardSize() - this.armThickness + 1
            && c >= this.getBoardSize() - this.armThickness + 1));
  }

  /**
   * This is the method which creates the board.
   * The way it is implemented is that first, I create a list of rows which are all marbles.
   * Then, I mutate those marbles into the correct positions that they should be using for loops.
   */
  public void initBoard() {
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
   * This method checks whether there is a valid move to be made.
   *
   * @param row    - this is the current position row of the marble.
   * @param col    - this is the current position col of the marble.
   * @param newRow - this is the new empty row position it will jump to.
   * @param newCol - this is the new empty col position it will jump to.
   * @return - true/false depending on whether the move is valid and meets all conditions.
   */
  private boolean isValidMove(int row, int col, int newRow, int newCol) {
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
    for (int i = 0; i < (this.armThickness + 2 * (this.armThickness - 1)); i++) {
      for (int j = 0; j < (this.armThickness + 2 * (this.armThickness - 1)); j++) {
        if (this.getSlotAt(i, j) == SlotState.Marble) {
          score++;
        }
      }
    }
    return score;
  }
}
