package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Makes a model and model view object.
 */
public interface Factory {

  /**
   * This method makes the model.
   *
   * @return This is the model returned.
   */
  public MarbleSolitaireModel makeModel();

  /**
   * This method makes a view without the appendable.
   *
   * @return a new MarbleSolitaireView object without passing in appendable.
   */
  public MarbleSolitaireView makeView();

  /**
   * This method makes a view using the appendable.
   *
   * @param appendable - This is the appendable passed into the method.
   * @return - this is the view made after using the appendable.
   */
  public MarbleSolitaireView makeView(Appendable appendable);
}
