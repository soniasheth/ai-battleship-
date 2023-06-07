package cs3500.pa03.view;

import cs3500.pa03.model.Cell;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.enums.HitStatus;
import cs3500.pa03.model.enums.ShipType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * MockView
 */
public class MockView implements View {

  public StringBuilder build;
  private int numTimesCalledDim;
  private int numTimesCalledShots;
  private int numTimesCalledFleet;

  /**
   * Constructor
   */
  public MockView() {
    this.build = new StringBuilder();
    this.numTimesCalledDim = 0;
    this.numTimesCalledShots = 0;
    this.numTimesCalledFleet = 0;

  }

  @Override
  public void displayAnything(String prompt) throws IOException {
    build.append("display ");

  }

  @Override
  public List<Integer> welcome() {
    build.append("welcome ");
    return new ArrayList<>(Arrays.asList(6, 6));
  }

  @Override
  public Map<ShipType, Integer> getFleet(int size) {
    return null;
  }


  @Override
  public void displayBoard(Cell[][] board) throws IOException {
    //build.append("displayBoard ");
  }

  @Override
  public void displayOpponentBoard(HitStatus[][] board) throws IOException {
    //build.append("displayOB ");
  }

  @Override
  public List<Coord> getShots(int numShots, int widthMax, int heightMax) throws IOException {
    return null;
  }


}