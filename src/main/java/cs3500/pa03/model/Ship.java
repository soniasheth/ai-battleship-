package cs3500.pa03.model;

import cs3500.pa03.model.enums.ShipType;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a singular Ship
 */
public class Ship {
  //fields
  private ShipType type;
  private HashMap<Coord, Boolean> hitPositionsTracker;

  /**
   * Constructor - Initializes a Ship object
   *
   * @param type the type of this ship
   */
  public Ship(ShipType type) {
    this.type = type;
    this.hitPositionsTracker = new HashMap<>();
  }


  /**
   * Returns whether the ship has sunken yet
   *
   * @return false if the ship is not sunk or true is the ship is sunk
   */

  public boolean isSunk() {
    // returns true if the ship is sunk
    boolean sunk = true;
    for (Map.Entry<Coord, Boolean> element : hitPositionsTracker.entrySet()) {
      // return false if their is a position not hit yet
      if (element.getValue() == false) {
        return false;
      }
    }
    return sunk;
  }

  /**
   * Updates the hitPositionsTracker HashMap and change the boolean corresponding to the given coord
   *        to true
   */
  public void updateHitPositions(Coord position) {
    //hitPositionsTracker.get(position);
    for (Map.Entry<Coord, Boolean> coordBooleanEntry : this.hitPositionsTracker.entrySet()) {
      if (coordBooleanEntry.getKey().equals(position)) {
        this.hitPositionsTracker.put(position, true);
      }
    }
  }

  /**
   * Adds an entry with the give Coord as the key and false as the value
   *
   * @param position Key Value (A Coord)
   */
  public void addHitPositionsTracker(Coord position) {
    // add the value and intitial value is false
    this.hitPositionsTracker.put(position, false);
  }

  /**
   * Checks if the given object is equal to this ship - needed for testing
   *
   * @param other given object
   * @return boolean, whether the given object is the same as this ship
   */
  public boolean equals(Object other) {
    if (!(other instanceof Ship)) {
      throw new IllegalArgumentException("Not a Ship");
    }
    Ship that = (Ship) other;
    return this.hitPositionsTracker.equals(that.hitPositionsTracker)
       && this.type.equals(that.type);
  }

  public HashMap<Coord, Boolean> getHitPositionsTracker() {
    return this.hitPositionsTracker;
  }

  /*
  public String toString() {
    String test = "";
    for (Map.Entry<Coord, Boolean> element : this.hitPositionsTracker.entrySet()) {
      test = this.type.name()+ test + element.getKey().toString() + "\n";
    }
    return test;
  }*/

}
