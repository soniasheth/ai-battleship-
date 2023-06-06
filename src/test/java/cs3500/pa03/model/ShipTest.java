package cs3500.pa03.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa03.model.enums.ShipType;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the Ship Class
 */
class ShipTest {
  Ship ship;

  @BeforeEach
  public void setup() {
    ship = new Ship(ShipType.SUBMARINE);
  }

  /**
   * Tests isSunk() method - answer is no
   */
  @Test
  public void testIsSunkNo() {
    ship.addHitPositionsTracker(new Coord(1, 1));
    ship.addHitPositionsTracker(new Coord(1, 2));
    ship.addHitPositionsTracker(new Coord(1, 3));

    assertEquals(false, ship.isSunk());
  }

  /**
   * Tests isSunk() method - answer is yes
   */
  @Test
  public void testIsSunkYes() {
    ship.addHitPositionsTracker(new Coord(1, 1));
    ship.addHitPositionsTracker(new Coord(1, 2));
    ship.addHitPositionsTracker(new Coord(1, 3));

    ship.updateHitPositions(new Coord(1, 1));
    ship.updateHitPositions(new Coord(1, 2));
    ship.updateHitPositions(new Coord(1, 3));

    assertEquals(true, ship.isSunk());
  }

  /**
   * Tests the addHitPositionsTracker method
   */
  @Test
  public void testAddHitPositionsTracker() {
    assertEquals(new HashMap<Coord, Boolean>(), ship.getHitPositionsTracker());

    ship.addHitPositionsTracker(new Coord(1, 1));
    ship.addHitPositionsTracker(new Coord(1, 2));
    ship.addHitPositionsTracker(new Coord(1, 3));

    HashMap<Coord, Boolean> tester = new HashMap<>();
    tester.put(new Coord(1, 1), false);
    tester.put(new Coord(1, 2), false);
    tester.put(new Coord(1, 3), false);

    assertEquals(tester, ship.getHitPositionsTracker());
  }

  /**
   * Tests the updateHitPositions() method
   */
  @Test
  public void testUpdateHitPositions() {
    ship.addHitPositionsTracker(new Coord(1, 1));
    assertEquals(false, ship.getHitPositionsTracker().get(new Coord(1, 1)));

    ship.updateHitPositions(new Coord(1, 1));

    assertEquals(true, ship.getHitPositionsTracker().get(new Coord(1, 1)));

  }

  /**
   * Tests the equals method - true
   */
  @Test
  public void testEqualsTrue() {
    Ship ship1 = new Ship(ShipType.SUBMARINE);
    assertEquals(true, ship.equals(ship1));

    ship1.addHitPositionsTracker(new Coord(0, 0));
    ship.addHitPositionsTracker(new Coord(0, 0));

    assertEquals(true, ship.equals(ship1));
  }

  /**
   * Tests the equals method - false
   */
  @Test
  public void testEqualsFalse() {
    Ship ship1 = new Ship(ShipType.DESTROYER);
    assertEquals(false, ship.equals(ship1));

    ship1.addHitPositionsTracker(new Coord(0, 0));
    ship.addHitPositionsTracker(new Coord(1, 0));

    assertEquals(false, ship.equals(ship1));
  }

  /**
   * Tests the equals method - exception
   */
  @Test
  public void testEqualsExeption() {
    assertThrows(
        IllegalArgumentException.class,
        () -> ship.equals(new Cell(new Coord(1, 1))));
  }

}