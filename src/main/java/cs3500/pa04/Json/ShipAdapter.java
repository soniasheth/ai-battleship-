package cs3500.pa04.Json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;
import java.util.Map;

/**
 * Record that represents a ship in terms of starting coord, length, and direction
 *
 * @param coord starting position
 * @param length how long
 * @param dir horizontal or vertical
 */
public record ShipAdapter (
  @JsonProperty("coord") Coord coord,
  @JsonProperty("length") int length,
  @JsonProperty("direction") String dir) {

}
