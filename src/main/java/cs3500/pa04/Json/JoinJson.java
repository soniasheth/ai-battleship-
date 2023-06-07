package cs3500.pa04.Json;

import com.fasterxml.jackson.annotation.JsonProperty;


public record JoinJson(
    @JsonProperty("name") String gitName,
    @JsonProperty("game-type") String gameType) {

}
