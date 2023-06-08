package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;
import java.util.Map;

public record ShipAdapter (
  @JsonProperty("coord") Coord coord,
  @JsonProperty("length") int length,
  @JsonProperty("direction") String dir) {

  /*
  @JsonCreator
  ShipAdapter(@JsonProperty("coord") Coord coord,
              @JsonProperty("length") int length,
              @JsonProperty("direction") String dir) {
    this.start = coord;
    this.length = length;
    this.dir = dir;
  }
  */

}
