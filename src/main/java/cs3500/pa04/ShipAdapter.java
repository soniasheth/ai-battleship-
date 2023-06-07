package cs3500.pa04;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;
import java.util.Map;

public class ShipAdapter {

  private int length;
  private Coord start;
  private Direction direction;
  ShipAdapter(Ship ship) {
    Map<Coord, Boolean> positions = ship.getHitPositionsTracker();
    this.length = positions.size();
    //just get the first entry in the hashmap and that is the starting coordinate
    //this.start = position.

  }
}
