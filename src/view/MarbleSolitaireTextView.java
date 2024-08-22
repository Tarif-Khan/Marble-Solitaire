package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This is the class for a Marble Solitaire Text View.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  private MarbleSolitaireModelState game;
  private Appendable appendable;

  /**
   * This is the constructor for the view.
   *
   * @param game - this is a game put in.
   * @throws IllegalArgumentException - this is if the game is not valid.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState game) throws IllegalArgumentException {
    this(game, System.out);
  }

  /**
   * This is the constructor for a view.
   *
   * @param game       - this is the game passed in.
   * @param appendable - this is the appendable.
   * @throws IllegalArgumentException - this is thrown if either game or appendable are null;
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState game, Appendable appendable)
          throws IllegalArgumentException {
    if (game == null) {
      throw new IllegalArgumentException("Please use a proper state.");
    }
    if (appendable == null) {
      throw new IllegalArgumentException("Appendable is null.");
    }
    this.game = game;
    this.appendable = appendable;
  }


  @Override
  public String toString() {
    StringBuilder board = new StringBuilder();
    for (int i = 0; i < this.game.getBoardSize(); i++) {
      for (int j = 0; j < this.game.getBoardSize(); j++) {
        if (this.game.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble) {
          board.append("O ");
        }
        if (this.game.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          board.append("_ ");
        }
        if (this.game.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          board.append("  ");
        }
      }
      board = this.trim(board);
      board.append("\n");
    }
    board.deleteCharAt(board.length() - 1);
    return board.toString();
  }

  @Override
  public void renderBoard() throws IOException {
    this.appendable.append(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }

  /**
   * This method trims the spaces from the board.
   *
   * @param s - this is a string builder where spaces are removed.
   * @return
   */
  private StringBuilder trim(StringBuilder s) {
    StringBuilder use = new StringBuilder(s.toString());
    int i = use.length() - 1;
    while (i >= 0 && Character.isWhitespace(use.charAt(i))) {
      i--;
    }
    return new StringBuilder(use.substring(0, i + 1));
  }
}



