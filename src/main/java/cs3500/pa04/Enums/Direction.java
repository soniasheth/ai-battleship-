package cs3500.pa04.Enums;


/**
 * Represents a direction
 */
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
