package cs3500.pa03.view;

import cs3500.pa03.model.Cell;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.HitStatus;
import java.io.IOException;
import java.util.List;

/**
 * Interface for the view
 */
public interface View {

  /**
   * Appends the given string to the appendable
   *
   * @param prompt String, message to display / append
   * @throws IOException if unable to append
   */
  void displayAnything(String prompt) throws IOException;

  /**
   * Gets the user's input for board dimensions
   *
   * @param prompt Message to display to the user
   * @return String Array with the user's inputted board dimensions
   * @throws IOException if unable to append
   */
  String[] getBoardDimension(String prompt) throws IOException;

  /**
   * Gets user's input for the fleet
   *
   * @param prompt Message to display to console
   *
   * @return String array with the fleet numbers
   * @throws IOException if unable to append
   */
  String[] getFleet(String prompt) throws IOException;

  /**
   * Dispays the player's board to the console
   *
   * @param board the gameboard
   */
  void displayBoard(Cell[][] board) throws IOException;

  /**
   * Displays the opponenet board data to the console
   *
   * @param board opponent board data
   */
  void displayOpponentBoard(HitStatus[][] board) throws IOException;

  /**
   * Gets the user's input for their shots
   *
   * @param numShots number of shots they can take
   * @return List of coordinates (shots)
   * @throws IOException if unable to append with the appendable
   */
  List<Coord> getShots(int numShots) throws IOException;


}
