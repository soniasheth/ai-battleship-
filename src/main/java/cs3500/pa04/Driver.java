package cs3500.pa04;

import cs3500.pa03.controller.Battleship;
import java.io.IOException;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    Battleship battle = new Battleship();
    battle.run();
  }
}