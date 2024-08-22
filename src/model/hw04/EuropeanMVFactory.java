package cs3500.marblesolitaire.model.hw04;

import java.util.List;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This is the factory class for an European model view where it makes the model and view
 * with a given list of integers from the main method.
 */
public class EuropeanMVFactory implements Factory {
  private EuropeanSolitaireModel model;

  /**
   * This is the constructor where the size used for inputs tells us which constructor to use.
   */
  public EuropeanMVFactory(List<Integer> inputList) {
    if (inputList.size() == 0) {
      this.model = new EuropeanSolitaireModel();
    }
    if (inputList.size() == 1) {
      this.model = new EuropeanSolitaireModel(inputList.get(0));
    }
    if (inputList.size() == 2) {
      this.model = new EuropeanSolitaireModel(inputList.get(0), inputList.get(1));
    }
    if (inputList.size() == 3) {
      this.model = new EuropeanSolitaireModel(inputList.get(0), inputList.get(1), inputList.get(2));
    }
  }

  @Override
  public MarbleSolitaireModel makeModel() {
    return this.model;
  }

  @Override
  public MarbleSolitaireView makeView() {
    return new MarbleSolitaireTextView(this.model);
  }

  @Override
  public MarbleSolitaireView makeView(Appendable appendable) {
    return new MarbleSolitaireTextView(this.model, appendable);
  }
}
