package cs3500.pa04.Json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.ShipAdapter;
import java.util.List;

public record FleetJson(
    @JsonProperty("fleet") List<ShipAdapter> fleet) {
}
