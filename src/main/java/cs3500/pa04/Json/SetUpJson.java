package cs3500.pa04.Json;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SetUpJson(
    @JsonProperty("width") int width,
    @JsonProperty("height") int height,
    @JsonProperty ("fleet-spec") FleetSpecJson fleetSpec)
{

}
