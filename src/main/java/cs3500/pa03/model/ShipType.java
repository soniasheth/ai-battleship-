package cs3500.pa03.model;

/**
 * Represents the different types of ships
 */
public enum ShipType {
  CARRIER(6),
  BATTLESHIP(5),
  DESTROYER(4),
  SUBMARINE(3);

  int size;

  private ShipType(int size) {
    this.size = size;
  }


  public int getSize() {
    return size;
  }

  /**
   * Returns the name of the ship type
   *
   * @return String, name of the ship type
   */
  public String toString() {
    return this.name();
  }

}
