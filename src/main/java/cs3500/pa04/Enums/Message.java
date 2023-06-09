package cs3500.pa04.Enums;

public enum Message {
  JOIN("join"), SETUP("setup"), TAKESHOTS("take-shots"), REPORTDAMAGE("report-damage"),
  SUCCESSFULHITS("successful-hits"), ENDGAME("end-game");

  private final String message;

  Message(String message) {
    this.message = message;
  }

  public String message() {
    return message;
  }
}
