package cs3500.marblesolitaire.view;

import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing methods for MarbleSoiltaireControllerImpl methods.
 */
public class MarbleSolitaireControllerImplTest {

  EnglishSolitaireModel defaultExample;
  EnglishSolitaireModel exampleThickness5;
  EnglishSolitaireModel example3;
  MarbleSolitaireTextView defaultView;
  MarbleSolitaireTextView exampleThickness5View;
  MarbleSolitaireTextView example3View;
  StringBuilder s1;
  StringBuilder s2;
  StringBuilder s3;


  @Before
  public void testConstructors() {
    defaultExample = new EnglishSolitaireModel();
    example3 = new EnglishSolitaireModel(3, 2);
    exampleThickness5 = new EnglishSolitaireModel(5);
    s1 = new StringBuilder();
    s2 = new StringBuilder();
    s3 = new StringBuilder();
    defaultView = new MarbleSolitaireTextView(defaultExample, s1);
    exampleThickness5View = new MarbleSolitaireTextView(exampleThickness5, s2);
    example3View = new MarbleSolitaireTextView(example3, s3);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructors() {
    new MarbleSolitaireControllerImpl(example3, null, new InputStreamReader(System.in));
    new MarbleSolitaireControllerImpl(exampleThickness5, null, null);
    new MarbleSolitaireControllerImpl(null, null, null);
    new MarbleSolitaireControllerImpl(null, null, new InputStreamReader(System.in));
    new MarbleSolitaireControllerImpl(null, defaultView, new InputStreamReader(System.in));
    new MarbleSolitaireControllerImpl(defaultExample, defaultView, null);
    new MarbleSolitaireControllerImpl(null, example3View, null);
  }


  @Test
  public void testQuitGame() {
    StringReader quitList = new StringReader("q 5 4 3 2 1 ");
    StringBuilder builderExample = new StringBuilder();
    MarbleSolitaireTextView viewExample = new MarbleSolitaireTextView(defaultExample,
            builderExample);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(defaultExample,
            viewExample, quitList);
    String before = viewExample.toString();
    controller.playGame();
    assertEquals(true, viewExample.toString().equals(before));
    assertEquals(true, builderExample.toString().contains("Game quit!"));
  }

  class Mock implements MarbleSolitaireModel {
    private StringBuilder log;

    public Mock(StringBuilder log) {
      this.log = log;
    }

    @Override
    public void move(int fromRow, int fromCol, int toRow, int toCol)
            throws IllegalArgumentException {
      log.append(
              "Tried to move: \n From (" + fromRow + ", " + fromCol + ")\n To ("
                      + toRow + ", " + toCol
                      + ")\n");
    }

    @Override
    public boolean isGameOver() {
      return false;
    }

    @Override
    public int getBoardSize() {
      return 7;
    }

    @Override
    public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
      return null;
    }

    @Override
    public int getScore() {
      return 0;
    }
  }

  class MockView implements MarbleSolitaireView {

    @Override
    public void renderBoard() throws IOException {
      return;
    }

    @Override
    public void renderMessage(String message) throws IOException {
      return;
    }
  }

  @Test
  public void testInvalidReadables() {

    StringReader s1 = new StringReader("2 lucia nunez is the best");
    StringReader s2 = new StringReader("2, 4, 8, 10");
    StringReader s3 = new StringReader("2, aomqpofmp");
    StringReader s4 = new StringReader("2, 4  tarif khan");
    StringReader s5 = new StringReader("2, awd84a6d");
    StringReader s6 = new StringReader("56, a54sadd");
    StringReader s7 = new StringReader("6as5a65");
    List<StringReader> a = new ArrayList<StringReader>(Arrays.asList(s1, s2, s3, s4, s5, s6, s7));

    for (StringReader s : a) {
      MarbleSolitaireModel model = new Mock(new StringBuilder());
      MarbleSolitaireView mock = new MockView();
      MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, mock, s);

      try {
        c.playGame();
      } catch (IllegalStateException e) {
        assertEquals("Ran out of inputs.", e.getMessage());
      }
    }
  }

  class AppendableModel implements Appendable {
    @Override
    public Appendable append(CharSequence csq) throws IOException {
      throw new IOException(csq.toString());
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
      throw new IOException(csq.toString());
    }

    @Override
    public Appendable append(char c) throws IOException {
      throw new IOException();
    }
  }

  @Test
  public void testInvalidAppendables() {
    Random r = new Random();
    for (int i = 0; i < 1000; i++) {
      MarbleSolitaireView view = new MarbleSolitaireTextView(defaultExample, new AppendableModel());
      try {
        view.renderMessage("message");
      } catch (IOException e) {
        assertEquals("message", e.getMessage());
      }
      try {
        view.renderBoard();
      } catch (IOException e2) {
        assertEquals(new MarbleSolitaireTextView(defaultExample).toString(), e2.getMessage());
      }
    }
  }

}