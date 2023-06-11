package cs3500.pa04.Enums;

public enum Message {
  JOIN("join"), SETUP("setup"),
  TAKESHOTS("take-shots"),
  REPORTDAMAGE("report-damage"),
  SUCCESSFULHITS("successful-hits"),
  ENDGAME("end-game");

  private final String messageName;

  Message(String message) {
    this.messageName = message;
  }

  public String message() {
    return messageName;
  }
}
