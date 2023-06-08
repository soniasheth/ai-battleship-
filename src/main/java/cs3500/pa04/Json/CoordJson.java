package cs3500.pa04.Json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Record that represents a Coordinate
 *
 * @param x x coord
 * @param y y coord
 */
public record CoordJson (
    @JsonProperty("x") int x,
    @JsonProperty("y") int y)
{
}
