/*
 * SudokuPuzzle.java
 *
 * Purpose: A class for the SudokuPuzzle to keep useful information
 *
 * Author: Jacob Boertjes
 *
 * Revisions:
 *  Jan 15, 2018 - Created
 *  Jan 16, 2018 - Functions Implemented
 */
package sudokusolver;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.HashMap;


/**
 * Coordinate
 *
 * Purpose:
 *      A small class to hold a coordinate which will be used in the HashMap
 */
class Coordinate {
  private int x;
  private int y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getx() {
    return x;
  }

  public int gety() {
    return y;
  }
}


public class SudokuPuzzle {

  // 2D array to keep track of all cell values
  // Format: cells[rowIndex][colIndex]
  private int[][] cells;

  // A 2D array to keep track of numbers which exist in each row
  // Format: rowContents[rowIndex][numberValue] == 1 if numberValue is in row
  //                                               0 Otherwise
  // rowContents[rowIndex][0] indicates the number of filled cells in the row
  private int[][] rowContents;

  // A 2D array to keep track of numbers which exist in each column
  // Format: rowContents[colIndex][numberValue] == 1 if numberValue is in column
  //                                               0 Otherwise
  // colContents[rowIndex][0] indicates the number of filled cells in the col
  private int[][] colContents;

  // A 2D array to keep track of numbers which exist in each 3x3 box
  // Format: rowContents[boxIndex][numberValue] == 1 if numberValue is in box
  //                                               0 Otherwise
  // rowContents[boxIndex][0] indicates the number of filled cells in the box
  // The index of boxes are:
  //   ---------------------------
  //  |        |        |        |
  //  |    0   |   1    |   2    |
  //  |        |        |        |
  //  ----------------------------
  //  |        |        |        |
  //  |   3    |   4    |   5    |
  //  |        |        |        |
  //  ----------------------------
  //  |        |        |        |
  //  |   6    |   7    |   8    |
  //  |        |        |        |
  //  ----------------------------
  private int[][] boxContents;

  // A map for each cell to keep track of possibilities for that cell
  // Format: possibilities[rowIndex][colIndex]
  private HashMap<Coordinate,TreeSet<Integer>> possibilities;

  // We need access to the frame
  SudokuSolverWindow frame;

  // Keep track of the number of filled filled cells
  private int filledCells;

  public SudokuPuzzle(SudokuSolverWindow frame) {

    // Initialize the frame
    this.frame = frame;

    // Initialize the cells
    cells = new int[9][9];

    // Initialize the row, column and box contents
    rowContents = new int[9][10];
    colContents = new int[9][10];
    boxContents = new int[9][10];

    // Initialize possibilities
    possibilities = new HashMap<Coordinate, TreeSet<Integer>>();

    // Initialize filledCells
    filledCells = 0;
  }


  /**
   * getNumFilledCells()
   *
   * Purpose:
   *      Returns the number of filled cells
   *
   * Input:
   *      None
   *
   * Output:
   *      @return - The number of filled cells
  */
  public int getNumFilledCells() {
    return filledCells;
  }

  /**
   * getCellValue(row, col)
   *
   * Purpose:
   *      Returns the value in a given cell
   *
   * Input:
   *      @param row - The index of the cell's row
   *      @param col - The index of the cell's col
   *
   * Output:
   *      @return - 0 If the cell is empty, or the cell's value (1-9)
  */
  public int getCellValue(int row, int col) {
    if (row > 8 || row < 0 || col > 8 || col < 0)
      throw new IndexOutOfBoundsException();

    return cells[row][col];
  }

  /**
   * setCellValue(row, col, val)
   *
   * Purpose:
   *      Sets the value of a given cell
   *
   * Input:
   *      @param row - The index of the cell's row (0-8)
   *      @param col - The index of the cell's col (0-8)
   *      @param val - The value to update the cell to (1-9)
   *
   * Output:
   *      None
  */
  public void setCellValue(int row, int col, int val) {
    // Check for valid input arguments
    if (val > 9 || val < 0)
      throw new NumberFormatException();

    if (row > 8 || row < 0 || col > 8 || col < 0)
      throw new IndexOutOfBoundsException();

    cells[row][col] = val;
    if (val != 0) frame.textFields[row][col].setText(String.valueOf(val));

    // If we are making a cell non-empty, add to number of filled cells
    if (val != 0) {

      filledCells++;

      int boxIndex = coordinateToBoxIdx(row,col);

      // Add to the number of filled cells in each row, column and box
      rowContents[row][0]++;
      colContents[col][0]++;
      boxContents[boxIndex][0]++;

      // Also update that val exists in the row, col and box
      rowContents[row][val] = 1;
      colContents[col][val] = 1;
      boxContents[boxIndex][val] = 1;
    }
  }

  /**
   * coordinateToBoxIdx(row, col)
   *
   * Purpose:
   *      Gives the coresponding box index for a given cell coordinate
   *
   * Input:
   *      @param row - The index of the cell's row (0-8)
   *      @param col - The index of the cell's col (0-8)
   *
   * Output:
   *      @return - The index of the box which contains the cell's coordinates
  */
  private int coordinateToBoxIdx(int row, int col) {
    if (row > 8 || row < 0 || col > 8 || col < 0)
      throw new IndexOutOfBoundsException();

    // Seems silly to divide by 3 then multiply by 3
    //  but it will remove remainder before multiplying, giving proper answer
    return (row/3)*3 + (col/3);
  }

  /**
   * fillAllCellPossibilities()
   *
   * Purpose:
   *      Fills all cells' possible values. Will fill any
   *      cell's true values if they only had one possibility
   *
   * Input:
   *      @param row - The index of the cell's row (0-8)
   *      @param col - The index of the cell's col (0-8)
   *
   * Output:
   *      @return - The number of cells whose true value was found
   *                OR -1 if an empty cell had no possible values
   *
   * Assumption:
  *       The row, col and box contents have been filled already
  */
  public int fillAllCellPossibilities() {
    int rt = 0;

    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {

        // We only need to fill in for empty cells
        if (cells[row][col] != 0) continue;

        // Fill in the cell's possible values
        switch (fillCellPossibilities(row,col)) {
          case 0:
            return -1;
          case 1:
            rt++;
            break;
        }
      }
    }
    return rt;
  }

  /**
   * fillCellPossibilities(row, col)
   *
   * Purpose:
   *      Fills a given cell's possible values. If it
   *      only has one possibility, it fills it in as the true value
   *
   * Input:
   *      @param row - The index of the cell's row (0-8)
   *      @param col - The index of the cell's col (0-8)
   *
   * Output:
   *      @return - The number of possible values for this cell
   *
   * Assumption:
  *       The row, col and box contents have been filled already
  */
  public int fillCellPossibilities(int row, int col) {
    // Make an TreeSet for this cell's possible values
    TreeSet<Integer> vals = new TreeSet<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));

    // default return is 9
    int rt = 9;

    // Remove each value which is inside this row, column or box
    for (int i = 1; i <= 9; i++) {
      if (rowContents[row][i] == 1) {if (vals.remove(i)) rt--;}
      if (colContents[col][i] == 1) {if (vals.remove(i)) rt--;}
      if (boxContents[coordinateToBoxIdx(row,col)][i] == 1) {if (vals.remove(i)) rt--;}
    }

    if (rt == 1) {
      setCellValue(row, col, vals.first());
    }

    // Now that we hve made our list, put it in the HashMap
    possibilities.put(new Coordinate(row,col), vals);

    return rt;
  }
}
