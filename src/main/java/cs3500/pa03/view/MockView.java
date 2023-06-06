package cs3500.pa03.view;

import cs3500.pa03.model.Cell;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.HitStatus;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
  public String[] getBoardDimension(String prompt) throws IOException {
    build.append("boardDim ");
    String[] testValid = new String[2];
    testValid[0] = "6";
    testValid[1] = "6";

    String[] testInValid1 = new String[2];
    testInValid1[0] = "30";
    testInValid1[1] = "20";

    String[] testInValid2 = new String[2];
    testInValid2[0] = "30";
    testInValid2[1] = "r";

    if (numTimesCalledDim == 0) {
      numTimesCalledDim++;
      return testInValid1;
    } else if (numTimesCalledDim == 1) {
      numTimesCalledDim++;
      return testInValid2;
    } else {
      numTimesCalledDim++;
      return testValid;
    }
  }

  @Override
  public String[] getFleet(String prompt) throws IOException {
    build.append("getFleet ");
    String[] testValid = new String[4];
    testValid[0] = "1";
    testValid[1] = "1";
    testValid[2] = "1";
    testValid[3] = "1";

    String[] testInValid1 = new String[4];
    testInValid1[0] = "10";
    testInValid1[1] = "10";
    testInValid1[2] = "20";
    testInValid1[3] = "10";

    String[] testInValid2 = new String[4];
    testInValid1[0] = "10";
    testInValid1[1] = "a";
    testInValid1[2] = "20";
    testInValid1[3] = "10";

    if (numTimesCalledFleet == 0) {
      numTimesCalledFleet++;
      return testInValid1;
    } else if (numTimesCalledFleet == 1) {
      numTimesCalledFleet++;
      return testInValid2;
    } else {
      numTimesCalledFleet++;
      return testValid;
    }
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
  public List<Coord> getShots(int numShots) throws IOException {
    build.append("getShots ");
    List<Coord> listValid = new ArrayList<>();
    listValid.add(new Coord(0, 0));
    listValid.add(new Coord(1, 1));
    listValid.add(new Coord(2, 2));
    listValid.add(new Coord(3, 3));

    List<Coord> listInValid1 = new ArrayList<>();
    listInValid1.add(new Coord(0, 0));
    listInValid1.add(new Coord(30, 1));
    listInValid1.add(new Coord(2, 2));
    listInValid1.add(new Coord(3, 3));

    List<Coord> listInValid2 = new ArrayList<>();
    listInValid1.add(new Coord(0, 30));
    listInValid2.add(new Coord(30, 1));
    listInValid2.add(new Coord(2, -1));
    listInValid2.add(new Coord(3, -2));

    if (numTimesCalledShots == 0) {
      numTimesCalledShots++;
      return listInValid1;
    } else if (numTimesCalledShots == 1) {
      numTimesCalledShots++;
      return listInValid1;
    } else {
      numTimesCalledShots++;
      return listValid;
    }
  }

}