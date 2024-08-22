package cs3500.marblesolitaire.controller;

/**
 * This is the intercface for the controller.
 */
public interface MarbleSolitaireController {
  /**
   * This makes and plays a game for Marble Solitaire.
   *
   * @throws IllegalStateException - this is thrown if inputs cannot be read or transmitted.
   */
  void playGame() throws IllegalStateException;
}
