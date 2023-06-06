package cs3500.pa03.controller;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import cs3500.pa03.model.ShotHolder;
import cs3500.pa03.model.player.Player;
import cs3500.pa03.view.View;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Runs the BattleSalvo Game
 */
public class BattleSalvoController implements Controller {
  // fields
  // players are the model
  Player player1;
  Player player2;
  View view;
  ShotHolder shotHolder;

  /**
   * Instantiates a BattleSalvoController object
   *
   * @param player1 user player
   * @param player2 ai player
   * @param view view
   * @param shotHolder holds the user's shots
   */
  BattleSalvoController(Player player1, Player player2, View view, ShotHolder shotHolder) {
    this.player1 = player1;
    this.player2 = player2;
    this.view = view;
    this.shotHolder = shotHolder;
  }

  /**
   * Runs through each step of the game, including taking user input and displaying content
   *
   * @throws IOException if unable to append
   */
  public void run() throws IOException {
    //introduce user
    view.displayAnything("Hello! Welcome to BattleSalvo! Let's play!");

    //get board dimensions
    String[] dimensions = view.getBoardDimension(
        "Please enter a valid height and width below [between 6 and 15 inclusive]:");
    while (!checkDimensions(dimensions)) {
      dimensions = view.getBoardDimension(
          "Invalid board size inputs. Please"
              + " remember to enter a number between 6 and 15 inclusive. Try again: ");
    }
    int height = Integer.parseInt(dimensions[0]);
    int width =  Integer.parseInt(dimensions[1]);
    int maxNumShips = getMin(height, width);

    //get the fleet
    String[] fleet = view.getFleet("Please enter your fleet in the order"
            + " [Carrier, Battleship, Destroyer, Submarine] \n"
        + "Remember, your fleet may not exceed size " + maxNumShips);
    while (!checkFleet(fleet, maxNumShips)) {
      fleet = view.getFleet("Invalid fleet. Try again: ");
    }
    Map<ShipType, Integer> finalFleet = processFleet(fleet);

    //init the game
    List<Ship> player1Ships = player1.setup(height, width, finalFleet); //need to know how many
    List<Ship> player2Ships = player2.setup(height, width, finalFleet);
    //System.out.println("Size: "+ player1Ships.size());
    //System.out.println("Size: "+ player2Ships.size());

    //init the shot arraylist for the loop and give them one shot to begin with in order to kick
    //game off - those coordinates will be over written
    List<Coord> player1Shots = new ArrayList<>();
    player1Shots.add(new Coord(0, 0));
    List<Coord> player2Shots = new ArrayList<>();
    player2Shots.add(new Coord(0, 0));

    //game loop
    while (getShotNum(player1Ships) != 0 && getShotNum(player2Ships) != 0) {
      //System.out.println("here!");
      //player2.toString();
      // get the shots and keep looping until they are valid
      List<Coord> shots;
      do {
        //System.out.println("Shots: "+ getShotNum(player1Ships));
        shots = view.getShots(getShotNum(player1Ships));
      } while (!validateShots(shots, height, width)); //process the shots
      shotHolder.setShots(shots);
      //each player takes shots
      player1Shots = player1.takeShots();
      player2Shots = player2.takeShots();
      //shots are sent to other player
      List<Coord> player1ShipsHit = player1.reportDamage(player2Shots);
      List<Coord> player2ShipsHit = player2.reportDamage(player1Shots);
      //the players are informed of the hit ships
      player1.successfulHits(player2ShipsHit);
      player2.successfulHits(player1ShipsHit);
    }

    //end message
    if (player1Shots.isEmpty() && player2Shots.isEmpty()) {
      view.displayAnything("GAME OVER. Draw.");
    } else if (player1Shots.isEmpty()) {
      view.displayAnything("GAME OVER. Computer won!");
    } else {
      view.displayAnything("GAME OVER. You won!");
    }
  }

  /**
   * Take a list of ships and returns the number of ships which have not been sunken yet
   *
   * @param ships list of ships
   * @return number of ships which haven't sunk yet, which is equal to the shot number of the player
   */
  private int getShotNum(List<Ship> ships) {
    int num = 0;
    for (Ship ship : ships) {
      if (ship.isSunk() == false) {
        num++;
      }
    }
    return num;
  }

  /**
   * Valids the user input for shots
   *
   * @param shots unfiltered user's input of shots
   * @param height rows on the board
   * @param width columns on the board
   *
   * @return boolean, whether the user inputted valid shots
   */
  private boolean validateShots(List<Coord> shots, int height, int width) {
    boolean valid = true;
    //run through each coordinate
    for (Coord coord : shots) {
      if (coord.getX() >= height || coord.getY() >= width || coord.getX() < 0 || coord.getY() < 0) {
        valid = false;
      }
    }
    return valid;
  }

  /**
   * Given two numbers, returns the smaller of the two
   *
   * @param num1 int
   * @param num2 int
   * @return int, the smaller number between the given numbers
   */
  private int getMin(int num1, int num2) {
    if (num1 <= num2) {
      return num1;
    } else {
      return num2;
    }
  }

  /**
   * Validates the user given board dimensions
   *
   * @param dimensions String Array of user input, size 2
   * @return whether the given dimensions foe the board are valid
   */
  private boolean checkDimensions(String [] dimensions) {
    int height;
    int width;
    //ensure numbers
    try {
      height = Integer.parseInt(dimensions[0]);
      width = Integer.parseInt(dimensions[1]);
    } catch (NumberFormatException e) {
      return false;
    }
    return (height >= 6 && height <= 15 && width >= 6 && height <= 15);
  }

  /**
   * Validates the user given fleet
   *
   * @param fleet String array of user inputed fleet, size 4
   * @param max maxinum number of ships there can be
   * @return boolean, whether the given fleet numbers are valid
   */
  private boolean checkFleet(String[] fleet, int max) {
    int carrier;
    int battle;
    int dest;
    int sub;
    //ensure that they are numbers
    try {
      carrier = Integer.parseInt(fleet[0]);
      battle = Integer.parseInt(fleet[1]);
      dest = Integer.parseInt(fleet[2]);
      sub = Integer.parseInt(fleet[3]);
    } catch (NumberFormatException e) {
      return false;
    }
    return (carrier + battle + dest + sub <= max);
  }

  /**
   * Processes the fleet into a HashMap of ship type & the number of ships per ship type
   *
   * @param fleet String Array of fleet numbers
   * @return HashMap of shipe types to number of each ship type
   */
  private Map<ShipType, Integer> processFleet(String[] fleet) {
    Map<ShipType, Integer> totalFleet  = new HashMap<>();
    totalFleet.put(ShipType.CARRIER, Integer.parseInt(fleet[0]));
    totalFleet.put(ShipType.BATTLESHIP, Integer.parseInt(fleet[1]));
    totalFleet.put(ShipType.DESTROYER, Integer.parseInt(fleet[2]));
    totalFleet.put(ShipType.SUBMARINE, Integer.parseInt(fleet[3]));
    return totalFleet;
  }



}
