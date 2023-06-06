package cs3500.pa03.view;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa03.model.Cell;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.HitStatus;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BattleSalvoViewTest {
  BattleSalvoView view;

  @BeforeEach
  public void setUp() {
  }

  /**
   * Tests the displayAnything() method
   *
   * @throws IOException if unable to append
   */
  @Test
  public void testDisplayAnything() throws IOException {
    String test = "testing";
    view = new BattleSalvoView(new StringBuilder(), new StringReader(test));

    //empty to begin with
    assertEquals("", view.appendable.toString());

    view.displayAnything("hello");
    assertEquals("hello\n", view.appendable.toString());
  }

  @Test
  public void testGetBoardDimensions() throws IOException {
    String testDim =
        """
            6
            6
            """;
    view = new BattleSalvoView(new StringBuilder(), new StringReader(testDim));


    //empty to begin with
    assertEquals("", view.appendable.toString());

    String[] dim = view.getBoardDimension("Get the board dimensions");
    assertEquals("6", dim[0]);
    assertEquals("6", dim[1]);
    assertEquals("Get the board dimensions\n\n", view.appendable.toString());
  }

  /**
   * Tests the getFleet method
   *
   * @throws IOException if unable to append
   */
  @Test
  public void testGetFleet() throws IOException {
    String testFleet = "1 1 1 1";
    view = new BattleSalvoView(new StringBuilder(), new StringReader(testFleet));

    //empty to begin with
    assertEquals("", view.appendable.toString());

    String[] fleet = view.getFleet("get fleet");
    assertEquals("1", fleet[0]);
    assertEquals("1", fleet[1]);
    assertEquals("1", fleet[0]);
    assertEquals("1", fleet[1]);
    assertEquals("get fleet\n\n", view.appendable.toString());
  }

  /**
   * Tests the getShots method
   *
   * @throws IOException if unable to append
   */
  @Test
  public void testGetShots() throws IOException {
    String testShots =
        """
            0 0
            1 1
            """;
    view = new BattleSalvoView(new StringBuilder(), new StringReader(testShots));
    //empty to begin with
    assertEquals("", view.appendable.toString());

    List<Coord> shots = view.getShots(2);
    assertEquals(new Coord(0, 0), shots.get(0));
    assertEquals(new Coord(1, 1), shots.get(1));
    assertEquals("Please enter 2 shots.\nShot #1:\nShot #2:\n", view.appendable.toString());
  }

  /**
   * Tests the displayBoard method
   */
  @Test
  public void testDisplayBoard() {
    //smaller input
    Cell[][] board = new Cell[2][2];
    board[0][0] = new Cell(new Ship(ShipType.SUBMARINE), new Coord(0, 0));
    board[0][1] = new Cell(new Ship(ShipType.SUBMARINE), new Coord(1, 0));
    board[0][1].setHitStatus(true);
    board[1][0] = new Cell(new Coord(0, 1));
    board[1][0].setHitStatus(true);
    board[1][1] = new Cell(new Coord(1, 1));

    String boardDisp =
            """
            --------------------------------------
            Your Board:
            --------------------------------------
            """;
    String board2 = boardDisp + "X " + "H \n" + "M " + "- \n";
    view = new BattleSalvoView(new StringBuilder(), new StringReader("hello"));
    view.displayBoard(board);
    assertEquals(board2, view.appendable.toString());
  }

  /**
   * Tests displayOpBoard() method
   */
  @Test
  public void testDisplayOpBoard() {
    HitStatus[][] board = new HitStatus[2][2];
    board[0][0] = HitStatus.HIT;
    board[0][1] = HitStatus.HIT;
    board[1][0] = HitStatus.MISS;
    board[1][1] = HitStatus.NONE;

    String boardDisp =
        """
        --------------------------------------
        Opponent's Board Data:
        --------------------------------------
        """;
    String board2 = boardDisp + "H " + "H \n" + "M " + "- \n";
    view = new BattleSalvoView(new StringBuilder(), new StringReader("hello"));
    view.displayOpponentBoard(board);
    assertEquals(board2, view.appendable.toString());
  }

  /**
   * Tests a failing case for the displayBoard
   */
  @Test
  public void testFailingAppendable1() {
    BattleSalvoView view1 = new BattleSalvoView(new FailingAppendable(), new StringReader(""));
    assertThrows(
        RuntimeException.class,
        () -> view1.displayBoard(new Cell[2][2]));
  }

  /**
   * Tests a failing case for the displayOppBoard
   */
  @Test
  public void testFailingAppendable2() {
    BattleSalvoView view1 = new BattleSalvoView(new FailingAppendable(), new StringReader(""));
    assertThrows(
        RuntimeException.class,
        () -> view1.displayOpponentBoard(new HitStatus[2][2]));
  }

}