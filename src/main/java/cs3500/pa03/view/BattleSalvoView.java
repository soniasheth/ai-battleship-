package cs3500.pa03.view;

import cs3500.pa03.model.Cell;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.HitStatus;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a BattleSalvoView Object
 */
public class BattleSalvoView implements View {
  //fields
  Scanner userInput;
  Appendable appendable;
  Readable readable;

  /**
   * Instantiates a BattleSalvoView object
   *
   * @param appendable appendable
   * @param readable readable
   */
  public BattleSalvoView(Appendable appendable, Readable readable) {
    userInput = new Scanner(readable);
    this.appendable = appendable;
    this.readable = readable;
  }

  /**
   * Appends the given string to the appendable
   *
   * @param prompt String, message to display / append
   * @throws IOException if unable to append
   */
  public void displayAnything(String prompt) throws IOException {
    appendable.append(prompt + "\n");
    //appendable.append("\n");
  }

  /**
   * Gets the user's input for board dimensions
   *
   * @param prompt Message to display to the user
   * @return String Array with the user's inputted board dimensions
   * @throws IOException if unable to append
   */
  public String[] getBoardDimension(String prompt) throws IOException {
    String[] boardDimensions = new String[2];
    this.displayAnything(prompt);
    //only grab two numbers! stops after 2!
    for (int i = 0; i < 2; i++) {
      boardDimensions[i] = userInput.next();
    }
    appendable.append("\n");
    return boardDimensions;
  }

  /**
   * Gets user's input for the fleet
   *
   * @param prompt Message to display to console
   *
   * @return String array with the fleet numbers
   * @throws IOException if unable to append
   */
  public String[] getFleet(String prompt) throws IOException {
    this.displayAnything(prompt);
    String[] fleet = new String[4];
    for (int i = 0; i < 4; i++) {
      fleet[i] = userInput.next();
    }
    appendable.append("\n");

    return fleet;
  }

  /**
   * Dispays the player's board to the console
   *
   * @param board the gameboard
   */
  @Override
  public void displayBoard(Cell[][] board) {
    try {
      appendable.append("--------------------------------------\n");
      appendable.append("Your Board:\n");
      appendable.append("--------------------------------------\n");
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
          //there is a ship there and it is not hit
          if (board[i][j].getShip() != null && board[i][j].getHitStatus() == true) {
            appendable.append("H ");
          } else if (board[i][j].getShip() != null) {
            //there is a ship there and it has been hit
            appendable.append("X ");
          } else if (board[i][j].getHitStatus() == true) {
            // not ship but it has been hit
            appendable.append("M ");
          } else {
            //empty spot
            appendable.append("- ");
          }
        }
        appendable.append("\n");
      }
    } catch (IOException e) {
      throw new RuntimeException();
    }

  }

  /**
   * Displays the opponenet board data to the console
   *
   * @param board opponent board data
   */
  @Override
  public void displayOpponentBoard(HitStatus[][] board) {
    try {
      appendable.append("--------------------------------------\n");
      appendable.append("Opponent's Board Data:\n");
      appendable.append("--------------------------------------\n");
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
          if (board[i][j].equals(HitStatus.HIT)) {
            appendable.append("H ");
          } else if (board[i][j].equals(HitStatus.MISS)) {
            appendable.append("M ");
          } else {
            appendable.append("- ");
          }
        }
        appendable.append("\n");
      }
    } catch (IOException e) {
      throw new RuntimeException();
    }

  }

  /**
   * Gets the user's input for their shots
   *
   * @param numShots number of shots they can take
   * @return List of coordinates (shots)
   * @throws IOException if unable to append with the appendable
   */
  //for now need to assume that the coordinates are integers - will come back and fix later
  @Override
  public List<Coord> getShots(int numShots) throws IOException {
    List<Coord> shots = new ArrayList<>();
    this.displayAnything("Please enter " + numShots + " shots.");
    for (int i = 0; i < numShots; i++) {
      appendable.append("Shot #" + (i + 1) + ":\n");
      int x = userInput.nextInt();
      int y = userInput.nextInt();
      shots.add(new Coord(x, y));
    }
    return shots;
  }

}
