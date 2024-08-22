package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This is the controller class.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable readable;

  /**
   * This is the constructor for the controller.
   *
   * @param model    this is the model.
   * @param view     this is the view.
   * @param readable this is the readable.
   * @throws IllegalArgumentException if any of the parameters are null.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model,
                                       MarbleSolitaireView view, Readable readable)
          throws IllegalArgumentException {
    if (model == null || view == null || readable == null) {
      throw new IllegalArgumentException("Parameter passed in cannot be null.");
    }

    this.model = model;
    this.view = view;
    this.readable = readable;
  }

  @Override
  public void playGame() {
    Scanner scanner = new Scanner(readable);

    while (!(this.model.isGameOver())) {
      try {
        this.playOnce(scanner);
      } catch (IllegalArgumentException e) {
        if (e.getMessage().equals("User has quit game.")) {
          // When user quits.
          try {
            this.view.renderMessage("Game quit!\n");
            this.view.renderMessage("State of game when quit:\n");
            this.view.renderBoard();
            this.view.renderMessage("\nScore: " + this.model.getScore() + "\n");
            break;
          } catch (IOException ioe) {
            throw new IllegalStateException("Appendable not working.");
          }
        } else if (e.getMessage().equals("Please make a valid move")) {
          try {
            this.view.renderMessage("Invalid move. Play again.\n");
          } catch (IOException ioe) {
            throw new IllegalStateException("Appendable not working.");
          }
        }
      }
    }

    if (this.model.isGameOver()) {
      try {
        this.view.renderMessage("Game over!\n");
        this.view.renderBoard();
        this.view.renderMessage("\nScore: " + this.model.getScore() + "\n");
      } catch (IOException e) {
        throw new IllegalStateException("Appendable not working.");
      }
    }
  }

  /**
   * This method runs the game once.
   *
   * @param scanner - this is the scanner from play game.
   * @throws IllegalStateException    - this is thrown if the game is quit.
   * @throws IllegalArgumentException - if illegal move was made.
   */
  private void playOnce(Scanner scanner) throws IllegalStateException, IllegalArgumentException {
    try {
      this.view.renderBoard();
      this.view.renderMessage("\nScore: " + this.model.getScore() + "\n");
      this.model.move(validInt(scanner) - 1,
              validInt(scanner) - 1,
              validInt(scanner) - 1,
              validInt(scanner) - 1);
    } catch (IOException e) {
      throw new IllegalStateException("Appendable not working.");
    }
  }

  /**
   * Returns a valid integer value for the move to continue playing.
   *
   * @param scanner - scanner for inputs.
   * @return integer for one of the move parameters.
   */
  private int validInt(Scanner scanner) throws IllegalArgumentException, IllegalStateException {
    try {
      String next = scanner.next();
      while (!next.equalsIgnoreCase("q")
              && !this.greaterThanOrEqualToZero(next)) {
        next = scanner.next();
      }
      if (next.equalsIgnoreCase("q")) {
        throw new IllegalArgumentException("User has quit game.");
      }
      return Integer.parseInt(next);
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("Ran out of inputs.");
    }
  }

  private boolean greaterThanOrEqualToZero(String next) {
    try {
      Integer num = Integer.parseInt(next);
      return num >= 0;
    } catch (Exception e) {
      return false;
    }
  }

}
