package cs3500.pa04.Json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.enums.GameResult;

public record EndGameJson(
    @JsonProperty("result") GameResult result,
    @JsonProperty("reason") String reason) {

  public GameResult getResult() {
    return this.result;
  }

  public String getReason() {
    return this.reason;
  }
}
