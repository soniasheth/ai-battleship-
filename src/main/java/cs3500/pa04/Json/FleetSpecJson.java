package cs3500.pa04.Json;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FleetSpecJson(
    @JsonProperty("BATTLESHIP") int numBattleShip,
    @JsonProperty("CARRIER") int numCarrier,
    @JsonProperty("DESTROYER") int numDestroyer,
    @JsonProperty("SUBMARINE") int numSubmarine) {
}
