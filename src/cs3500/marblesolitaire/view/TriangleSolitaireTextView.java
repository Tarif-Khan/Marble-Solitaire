package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This is the class for the text view for the triangle.
 */
public class TriangleSolitaireTextView implements MarbleSolitaireView {
  protected final MarbleSolitaireModelState model;
  protected final Appendable appendable;

  /**
   * Constructor for triangle Solitaire Text.
   *
   * @param model the given MarbleSolitaireModelState
   * @throws IllegalArgumentException if model is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
          throws IllegalArgumentException {
    this(model, System.out);
  }

  /**
   * Constructs a TriangleSolitaireTextView object with model and appendable passed in.
   *
   * @param model      - the given MarbleSolitaireModelState
   * @param appendable - the given appendable
   * @throws IllegalArgumentException if m or a are null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable appendable)
          throws IllegalArgumentException {
    if ((model == null) || (appendable == null)) {
      throw new IllegalArgumentException("Model or appendable cannot be null");
    }
    this.model = model;
    this.appendable = appendable;
  }

  @Override
  public String toString() {
    String out = "";
    int size = model.getBoardSize();
    int row = 0;
    while (row <= size - 1) {
      int column = 0;
      int space = 0;
      while (space < size - (row + 1)) {
        out = out + " ";
        space++;
      }
      while (column <= size - 1) {
        MarbleSolitaireModelState.SlotState s = model.getSlotAt(row, column);
        if (s.equals(MarbleSolitaireModelState.SlotState.Empty)) {
          if (column == size - 1
                  || model.getSlotAt(row, column + 1)
                  .equals(MarbleSolitaireModelState.SlotState.Invalid)) {
            out = out + "_";
            column = size;
          } else {
            out = out + "_ ";
          }
        } else if (s.equals(MarbleSolitaireModelState.SlotState.Marble)) {
          if (column == size - 1) {
            out = out + "O";
            column = size;
          } else if (model.getSlotAt(row, column + 1)
                  .equals(MarbleSolitaireModelState.SlotState.Invalid)) {
            out = out + "O";
            column = size;
          } else {
            out = out + "O ";
          }
        } else if (s.equals(MarbleSolitaireModelState.SlotState.Invalid)) {
          out = out + " ";
        }
        column++;
      }
      if (row < size - 1) {
        out = out + "\n";
      } else {
        out = out + "";
      }
      row++;
    }
    return out;
  }

  @Override
  public void renderBoard() throws IOException {
    this.renderMessage(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }
}