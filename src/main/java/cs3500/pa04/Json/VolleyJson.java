package cs3500.pa04.Json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.Coord;
import java.util.List;

public record VolleyJson(
    @JsonProperty("coordinates") List<Coord> volley) {
}
