package cs3500.pa03.model.enums;

/**
 * Represents the different types of ships
 */
public enum ShipType {
  CARRIER(6),
  BATTLESHIP(5),
  DESTROYER(4),
  SUBMARINE(3);

  final int size;

  private ShipType(int size) {
    this.size = size;
  }


  public int getSize() {
    return size;
  }

}
