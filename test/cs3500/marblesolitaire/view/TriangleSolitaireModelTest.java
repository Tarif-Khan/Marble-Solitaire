package cs3500.marblesolitaire.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * class for testing unique TriangleSolitaireModel methods.
 */
public class TriangleSolitaireModelTest {

  TriangleSolitaireModel defaultBoard;
  TriangleSolitaireModel boardThickness6;
  TriangleSolitaireModel noArmThickness;

  @Before
  public void constructors() {
    defaultBoard = new TriangleSolitaireModel();
    boardThickness6 = new TriangleSolitaireModel(6);
    noArmThickness = new TriangleSolitaireModel(3, 2);
  }


  @Test
  public void testGetScore() {
    assertEquals(20, boardThickness6.getScore());
    assertEquals(14, defaultBoard.getScore());
    assertEquals(14, noArmThickness.getScore());

  }

  @Test
  public void testGameOver() {

    assertEquals(false, defaultBoard.isGameOver());

    defaultBoard.move(2, 0, 0, 0);
    assertEquals(false, defaultBoard.isGameOver());

    defaultBoard.move(2, 2, 2, 0);
    assertEquals(false, defaultBoard.isGameOver());

    defaultBoard.move(0, 0, 2, 2);
    assertEquals(false, defaultBoard.isGameOver());

    defaultBoard.move(3, 0, 1, 0);
    assertEquals(false, defaultBoard.isGameOver());

    defaultBoard.move(3, 3, 1, 1);
    assertEquals(false, defaultBoard.isGameOver());

    defaultBoard.move(4, 1, 2, 1);
    assertEquals(false, defaultBoard.isGameOver());

    defaultBoard.move(4, 3, 4, 1);
    assertEquals(false, defaultBoard.isGameOver());

    defaultBoard.move(4, 0, 4, 2);
    assertEquals(false, defaultBoard.isGameOver());

    defaultBoard.move(4, 2, 2, 2);
    assertEquals(false, defaultBoard.isGameOver());

    defaultBoard.move(1, 1, 3, 3);
    assertEquals(false, defaultBoard.isGameOver());

    defaultBoard.move(4, 4, 2, 2);
    assertEquals(false, defaultBoard.isGameOver());

    defaultBoard.move(2, 2, 2, 0);
    assertEquals(false, defaultBoard.isGameOver());

    defaultBoard.move(2, 0, 0, 0);
    assertEquals(true, defaultBoard.isGameOver());
  }

  @Test
  public void testMove() {
    boolean baseOver = false;
    Random rand = new Random();

    String lastMove = new TriangleSolitaireTextView(defaultBoard).toString();
    int score = defaultBoard.getScore();
    while (!baseOver) {
      lastMove = new TriangleSolitaireTextView(defaultBoard).toString();
      score = defaultBoard.getScore();
      int fromr = rand.nextInt(defaultBoard.getBoardSize());
      int fromc = rand.nextInt(defaultBoard.getBoardSize());
      int tor = rand.nextInt(defaultBoard.getBoardSize());
      int toc = rand.nextInt(defaultBoard.getBoardSize());
      try {
        defaultBoard.move(fromr, fromc, tor, toc);
        assertEquals(false,
                lastMove.equals(new TriangleSolitaireTextView(defaultBoard).toString()));
      } catch (IllegalArgumentException e) {
        assertEquals(lastMove, new TriangleSolitaireTextView(defaultBoard).toString());
        assertEquals(score, defaultBoard.getScore());
      }
      baseOver = baseOver || defaultBoard.isGameOver();
    }
    assertNotEquals(lastMove, new TriangleSolitaireTextView(defaultBoard).toString());
    assertEquals(true, defaultBoard.isGameOver());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveEmpty() {
    defaultBoard.move(0, 0, 2, 0);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNoMove() {
    defaultBoard.move(2, 0, 2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveOutOfBounds() {
    defaultBoard.move(-1, 3, 1, 3);
    defaultBoard.move(1, 8, 1, 3);
    defaultBoard.move(1, 3, -1, 3);
    defaultBoard.move(-1, 3, 1, 8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMove1() {
    defaultBoard.move(1, 0, 0, 0);
    defaultBoard.move(1, 1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMove2() {
    defaultBoard.move(5, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalid() {
    defaultBoard.move(2, 0, 0, 4);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructor() {
    new TriangleSolitaireModel(0);
    new TriangleSolitaireModel(-1);
    new TriangleSolitaireModel(-4, 4);
    new TriangleSolitaireModel(5, -4);
    new TriangleSolitaireModel(5, 3, 6);
    new TriangleSolitaireModel(5, -8, 4);

  }

  @Test
  public void testGetBoardSize() {
    assertEquals(5, defaultBoard.getBoardSize());
    assertEquals(6, boardThickness6.getBoardSize());
    assertEquals(5, noArmThickness.getBoardSize());


  }

  @Test
  public void testGetSlotAt() {
    assertEquals(SlotState.Empty, defaultBoard.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, defaultBoard.getSlotAt(2, 2));
    assertEquals(SlotState.Invalid, defaultBoard.getSlotAt(0, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalGetSlotAt() {
    defaultBoard.getSlotAt(-5, 3);
    defaultBoard.getSlotAt(1, -5);
    defaultBoard.getSlotAt(9, 5);
    defaultBoard.getSlotAt(6, 9);
  }
}