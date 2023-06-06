package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests Cell Class
 */
class CellTest {

  Cell cellEmpty;
  Cell cellShip;

  /**
   * Initial set up
   */
  @BeforeEach
  public void setUp() {
    cellEmpty = new Cell(new Coord(0, 0));
    cellShip = new Cell(new Ship(ShipType.BATTLESHIP), new Coord(0, 1));
  }

  /**
   * Tests the getHitStatus methods
   */
  @Test
  public void getHitStatus() {
    assertEquals(false, cellEmpty.getHitStatus());
    assertEquals(false, cellShip.getHitStatus());
  }

  /**
   * Tests the getShipMethods
   */
  @Test
  public void getShip() {
    assertEquals(null, cellEmpty.getShip());
    assertEquals(new Ship(ShipType.BATTLESHIP), cellShip.getShip());
  }

  /**
   * Tests the getShipStatus method
   */
  @Test
  public void getShipStatus() {
    assertEquals(Status.EMPTY, cellEmpty.getShipStatus());
    assertEquals(Status.SHIP, cellShip.getShipStatus());
  }

  /**
   * Tests the setHitStatus method
   */
  @Test
  public void setHitStatus() {
    assertEquals(false, cellEmpty.getHitStatus());
    assertEquals(false, cellShip.getHitStatus());

    cellEmpty.setHitStatus(true);
    cellShip.setHitStatus(true);

    assertEquals(true, cellEmpty.getHitStatus());
    assertEquals(true, cellShip.getHitStatus());

  }

}