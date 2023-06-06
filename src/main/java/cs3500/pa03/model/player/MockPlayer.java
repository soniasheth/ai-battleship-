package cs3500.pa03.model.player;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Mock Class
 */
public class MockPlayer implements Player {

  public StringBuilder build;
  private boolean calledShots;
  private boolean calledSuccHits;
  private List<Ship> ships;

  /**
   * Constructor
   */
  public MockPlayer() {
    build = new StringBuilder();
    ships = new ArrayList<>();
    calledShots = false;
    calledSuccHits = false;

  }

  @Override
  public String name() {
    //build.append("name ");
    return "";
  }

  /**
   * Mock set up
   *
   * @param height the height of the board, range: [6, 15] inclusive
   * @param width the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return list of ships
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    build.append("setup ");
    Ship ship = new Ship(ShipType.SUBMARINE);
    ship.addHitPositionsTracker(new Coord(0, 0));
    //ship.addHitPositionsTracker(new Coord(0, 1));
    //ship.addHitPositionsTracker(new Coord(0, 2));
    List<Ship> playShips = new ArrayList<>();
    playShips.add(ship);
    this.ships = playShips;
    return playShips;
  }

  /**
   * Mock takeShots
   *
   * @return list of coord of shots
   */
  @Override
  public List<Coord> takeShots() {
    build.append("takeshots ");
    List<Coord> listValid = new ArrayList<>();
    listValid.add(new Coord(0, 0));
    listValid.add(new Coord(1, 1));
    listValid.add(new Coord(2, 2));
    listValid.add(new Coord(3, 3));
    if (calledShots == false) {
      calledShots = true;
      return listValid;
    } else {
      return new ArrayList<>();
    }
  }

  /**
   * Mock Report Damage
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return null
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    build.append("reportDamage ");
    return null;
  }

  /**
   * Mock
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    build.append("hits ");
    if (calledShots == true && calledSuccHits == true) {
      ships.remove(0);
    }
    calledSuccHits = true;
    //ships.get(0).updateHitPositions(new Coord(0, 0));

  }

  /**
   * Mock end game
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
    //build.append("endGame ");
  }
}
