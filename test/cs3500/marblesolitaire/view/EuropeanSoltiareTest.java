package cs3500.marblesolitaire.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing EuropeanSolitaireModel methods.
 */
public class EuropeanSoltiareTest {

  EuropeanSolitaireModel deafultBoard;
  EuropeanSolitaireModel boardThickness5;
  EuropeanSolitaireModel boardNoThickness;

  @Before
  public void constructor() {
    deafultBoard = new EuropeanSolitaireModel();
    boardThickness5 = new EuropeanSolitaireModel(5);
    boardNoThickness = new EuropeanSolitaireModel(3, 2);
  }


  @Test
  public void testToString() {
    assertEquals("        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O O O O O", new MarbleSolitaireTextView(boardThickness5).toString());

    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O _ O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", new MarbleSolitaireTextView(boardNoThickness).toString());

    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", new MarbleSolitaireTextView(deafultBoard).toString());

  }


  @Test
  public void testGetScore() {
    assertEquals(128, boardThickness5.getScore());
    assertEquals(36, deafultBoard.getScore());
    assertEquals(36, boardNoThickness.getScore());

  }

  @Test
  public void testGameOver() {
    MarbleSolitaireModel model = new EuropeanSolitaireModel(6, 2);
    assertEquals(false, model.isGameOver());

    model.move(4, 2, 6, 2);
    assertEquals(false, model.isGameOver());

    model.move(4, 4, 4, 2);
    assertEquals(false, model.isGameOver());

    model.move(6, 4, 4, 4);
    assertEquals(false, model.isGameOver());

    model.move(6, 3, 4, 3);
    assertEquals(false, model.isGameOver());

    model.move(3, 2, 5, 2);
    assertEquals(false, model.isGameOver());

    model.move(6, 2, 4, 2);
    assertEquals(false, model.isGameOver());

    model.move(3, 0, 3, 2);
    assertEquals(false, model.isGameOver());

    model.move(5, 1, 3, 1);
    assertEquals(false, model.isGameOver());

    model.move(4, 3, 4, 1);
    assertEquals(false, model.isGameOver());

    model.move(4, 0, 4, 2);
    assertEquals(false, model.isGameOver());

    model.move(2, 1, 4, 1);
    assertEquals(false, model.isGameOver());

    model.move(4, 1, 4, 3);
    assertEquals(false, model.isGameOver());

    model.move(2, 3, 2, 1);
    assertEquals(false, model.isGameOver());

    model.move(2, 0, 2, 2);
    assertEquals(false, model.isGameOver());

    model.move(0, 3, 2, 3);
    assertEquals(false, model.isGameOver());

    model.move(1, 1, 1, 3);
    assertEquals(false, model.isGameOver());

    model.move(2, 3, 0, 3);
    assertEquals(false, model.isGameOver());

    model.move(1, 5, 1, 3);
    assertEquals(false, model.isGameOver());

    model.move(3, 2, 1, 2);
    assertEquals(false, model.isGameOver());

    model.move(1, 2, 1, 4);
    assertEquals(false, model.isGameOver());

    model.move(2, 5, 2, 3);
    assertEquals(false, model.isGameOver());

    model.move(3, 4, 3, 2);
    assertEquals(false, model.isGameOver());

    model.move(3, 6, 3, 4);
    assertEquals(false, model.isGameOver());

    model.move(4, 4, 4, 2);
    assertEquals(false, model.isGameOver());

    model.move(4, 6, 4, 4);
    assertEquals(false, model.isGameOver());

    model.move(3, 2, 5, 2);
    assertEquals(false, model.isGameOver());

    model.move(3, 4, 5, 4);
    assertEquals(false, model.isGameOver());

    model.move(0, 4, 2, 4);
    assertEquals(false, model.isGameOver());

    model.move(2, 4, 2, 2);
    assertEquals(false, model.isGameOver());

    model.move(5, 5, 5, 3);
    assertEquals(false, model.isGameOver());

    model.move(0, 2, 0, 4);
    assertEquals(false, model.isGameOver());

    model.move(5, 2, 5, 4);
    assertEquals(true, model.isGameOver());
  }

  @Test
  public void testMove() {
    boolean over = false;
    Random rand = new Random();

    String lastMove = new MarbleSolitaireTextView(deafultBoard).toString();
    int score = deafultBoard.getScore();
    while (!over) {
      lastMove = new MarbleSolitaireTextView(deafultBoard).toString();
      score = deafultBoard.getScore();
      int fromr = rand.nextInt(deafultBoard.getBoardSize());
      int fromc = rand.nextInt(deafultBoard.getBoardSize());
      int tor = rand.nextInt(deafultBoard.getBoardSize());
      int toc = rand.nextInt(deafultBoard.getBoardSize());
      try {
        deafultBoard.move(fromr, fromc, tor, toc);
        assertEquals(false, lastMove.equals(new MarbleSolitaireTextView(deafultBoard).toString()));
        assertEquals(score - 1, deafultBoard.getScore());
      } catch (IllegalArgumentException e) {
        assertEquals(lastMove, new MarbleSolitaireTextView(deafultBoard).toString());
        assertEquals(score, deafultBoard.getScore());
      }
      over = over || deafultBoard.isGameOver();
    }
    assertNotEquals(lastMove, new MarbleSolitaireTextView(deafultBoard).toString());
    assertEquals(true, deafultBoard.isGameOver());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveEmpty() {
    deafultBoard.move(3, 3, 1, 3);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testMoveDiagonally() {
    boardThickness5.move(4, 4, 6, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoMove() {
    deafultBoard.move(1, 3, 1, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveOutOfBounds() {
    deafultBoard.move(-1, 3, 1, 3);
    deafultBoard.move(1, 8, 1, 3);
    deafultBoard.move(1, 3, -1, 3);
    deafultBoard.move(-1, 3, 1, 8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMove1() {
    deafultBoard.move(1, 2, 1, 3);
    deafultBoard.move(1, 3, 2, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMove3() {
    deafultBoard.move(1, 3, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalid() {
    deafultBoard.move(0, 0, 2, 0);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructor() {
    new EuropeanSolitaireModel(1, 1);
    new EuropeanSolitaireModel(1);
    new EuropeanSolitaireModel(10, 10, 3);
    new EuropeanSolitaireModel(8);
    new EuropeanSolitaireModel(-2, 3);
    new EuropeanSolitaireModel(4, 3, 3);
    new EuropeanSolitaireModel(-2, 3, 3);

  }

  @Test
  public void testGetSlotAt() {
    assertEquals(SlotState.Empty, deafultBoard.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, deafultBoard.getSlotAt(2, 3));
    assertEquals(SlotState.Invalid, deafultBoard.getSlotAt(0, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalGetSlotAt() {
    deafultBoard.getSlotAt(-1, 1);
    deafultBoard.getSlotAt(1, -1);
    deafultBoard.getSlotAt(8, 5);
    deafultBoard.getSlotAt(5, 8);
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, deafultBoard.getBoardSize());
    assertEquals(13, boardThickness5.getBoardSize());
    assertEquals(7, boardNoThickness.getBoardSize());


  }
}



