package cs3500.marblesolitaire.view;

import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;

/**
 * This is the class for my tests for all my methods.
 */
public class EnglishSolitaireModelTest {

  EnglishSolitaireModel testBoard = new EnglishSolitaireModel();
  EnglishSolitaireModel testBoard2 = new EnglishSolitaireModel(5);
  
  @Test(expected = IllegalArgumentException.class)
  public void testException() {
    new EnglishSolitaireModel(3, -2, -3);
  }

  @Test
  public void testInitBoard() {
    EnglishSolitaireModel testBoardInit = new EnglishSolitaireModel();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, testBoardInit.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, testBoardInit.getSlotAt(3, 4));
  }

  @Test
  public void testMove() {
    EnglishSolitaireModel testBoard3 = new EnglishSolitaireModel();
    testBoard3.move(3, 1, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, testBoard3.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, testBoard3.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, testBoard3.getSlotAt(3, 2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveException() {
    EnglishSolitaireModel testBoard4 = new EnglishSolitaireModel();
    testBoard4.move(3, 8, 3, 3);
  }


  @Test
  public void testisGameOver() {
    assertEquals(false, testBoard.isGameOver());
    assertEquals(false, testBoard2.isGameOver());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, testBoard.getBoardSize());
    assertEquals(13, testBoard2.getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, testBoard.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, testBoard.getSlotAt(2, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, testBoard.getSlotAt(0, 0));
  }


  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtException() {
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, testBoard.getSlotAt(8, 8));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, testBoard.getSlotAt(-1, -1));
  }


  @Test
  public void testGetScore() {
    assertEquals(32, testBoard.getScore());
    assertEquals(68, testBoard2.getScore());
  }


}


