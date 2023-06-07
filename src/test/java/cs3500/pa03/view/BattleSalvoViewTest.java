package cs3500.pa03.view;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa03.model.Cell;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.enums.HitStatus;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.enums.ShipType;
import java.io.IOException;
import java.io.StringReader;
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
    assertEquals("", view.append.toString());

    view.displayAnything("hello");
    assertEquals("hello\n", view.append.toString());
  }







  /**
   * Tests the displayBoard method
   */
  @Test
  public void testDisplayBoard() {
    //smaller input
    Cell[][] board = new Cell[2][2];
    board[0][0] = new Cell(new Ship(ShipType.SUBMARINE));
    board[0][1] = new Cell(new Ship(ShipType.SUBMARINE));
    board[0][1].setHitStatus(true);
    board[1][0] = new Cell();
    board[1][0].setHitStatus(true);
    board[1][1] = new Cell();

    String boardDisp =
            """
            --------------------------------------
            Your Board:
            --------------------------------------
            """;
    String board2 = boardDisp + "X " + "H \n" + "M " + "- \n";
    view = new BattleSalvoView(new StringBuilder(), new StringReader("hello"));
    view.displayBoard(board);
    assertEquals(board2, view.append.toString());
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
    assertEquals(board2, view.append.toString());
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