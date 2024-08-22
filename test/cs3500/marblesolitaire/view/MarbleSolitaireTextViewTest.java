package cs3500.marblesolitaire.view;

import org.junit.Test;

import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

import static org.junit.Assert.assertEquals;


/**
 * this is my class for the Text view tests.
 */
public class MarbleSolitaireTextViewTest {

  EnglishSolitaireModel testBoard = new EnglishSolitaireModel();
  MarbleSolitaireTextView example = new MarbleSolitaireTextView(testBoard);
  EuropeanSolitaireModel example2 = new EuropeanSolitaireModel();

  @Test
  public void testToString() {
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", example.toString());
    this.testBoard = new EnglishSolitaireModel(0, 2);
    this.example = new MarbleSolitaireTextView(testBoard);
  }
}
