package cs3500.marblesolitaire;

import cs3500.marblesolitaire.model.hw04.EnglishMVFactory;
import cs3500.marblesolitaire.model.hw04.EuropeanMVFactory;
import cs3500.marblesolitaire.model.hw04.Factory;
import cs3500.marblesolitaire.model.hw04.TriangleMVFactory;
import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is my main class to play the game.
 */
public final class MarbleSolitaire {

  /**
   * This is the main method which plays the game.
   *
   * @param args these are the string commands for the inputs.
   */
  public static void main(String[] args) {

    MarbleSolitaireController controller;
    List<Integer> inputList;
    Factory factory;


    int pastArgs = 0;
    for (String s : args) {
      switch (s) {
        case "english":
          inputList = new ArrayList<Integer>(Arrays.asList(3, 3, 3));
          inputList = findParameters(args, pastArgs, inputList);
          factory = new EnglishMVFactory(inputList);
          controller = new MarbleSolitaireControllerImpl(
                  factory.makeModel(), factory.makeView(), new InputStreamReader(System.in));
          controller.playGame();
          break;

        case "triangular":
          inputList = new ArrayList<Integer>(Arrays.asList(3, 3, 3));
          inputList = findParameters(args, pastArgs, inputList);
          factory = new TriangleMVFactory(inputList);
          controller = new MarbleSolitaireControllerImpl(
                  factory.makeModel(), factory.makeView(), new InputStreamReader(System.in));
          controller.playGame();
          break;

        case "european":
          inputList = new ArrayList<Integer>(Arrays.asList(5, 0, 0));
          inputList = findParameters(args, pastArgs, inputList);
          factory = new EuropeanMVFactory(inputList);
          controller = new MarbleSolitaireControllerImpl(
                  factory.makeModel(), factory.makeView(), new InputStreamReader(System.in));
          controller.playGame();
          break;

        default:
          System.out.println("");
      }
      pastArgs++;
    }


  }

  /**
   * These are the integers for the input for the game.
   *
   * @param inputArgs  This is the input array to be parsed through.
   * @param pastArgs   This is the past arguments to find the other arguments.
   * @param defaultArg This is the default number if nothing is called through.
   * @return - the updated list with the inputs asked for by the user.
   */
  private static List<Integer> findParameters(String[] inputArgs, int pastArgs,
                                              List<Integer> defaultArg) {

    int i = pastArgs;
    for (String s : inputArgs) {
      switch (s) {
        case "-size":
          try {
            defaultArg.set(0, Integer.parseInt(inputArgs[i + 1]));

          } catch (NumberFormatException e) {
            System.out.println("enter a number");
          }
          break;

        case "-hole":
          try {
            defaultArg.set(1, Integer.parseInt(inputArgs[i + 1]));
            defaultArg.set(2, Integer.parseInt(inputArgs[i + 2]));
          } catch (NumberFormatException e) {
            System.out.println("enter a number");
          }
          break;
        default:
          System.out.println("unknown command");
      }
      i++;
    }
    return defaultArg;
  }
}
