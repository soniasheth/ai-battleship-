package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.Coord;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Direction {
  VERTICAL("vertical"),
  HORIZONTAL("horizontal");

  private String name;

  Direction(String name) {
    this.name = name;
  }


  public String getName() {
    return name;
  }


  }
