package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;
import java.util.Map;

public class ShipAdapter {
  private int length;
  private Coord start;
  private Direction dir;
  ShipAdapter(Ship ship) {
    Map<Coord, Boolean> positions = ship.getHitPositionsTracker();
    this.length = positions.size();
    // just get the first entry in the hashmap
    this.start = positions.entrySet().stream().findFirst().get().getKey();
    this.dir = ship.getDirection();
  }

  @JsonCreator
  ShipAdapter(@JsonProperty("coord") Coord coord,
              @JsonProperty("length") int length,
              @JsonProperty("direction") Direction dir) {
  }

}
