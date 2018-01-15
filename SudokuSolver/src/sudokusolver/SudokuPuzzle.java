/*
 * SudokuPuzzle.java
 *
 * Purpose: A class for the SudokuPuzzle to keep useful information
 *
 * Author: Jacob Boertjes
 *
 * Revisions:
 *  Jan 15, 2018 - Created
 */
package sudokusolver;

import java.util.ArrayList;

public class SudokuPuzzle {

  // 2D array to keep track of all cell values
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

  public SudokuPuzzle() {

    // Initialize the cells
    cells = new int[9][9];

    // Initialize the row, column and box contents
    rowContents = new int[9][10];
    colContents = new int[9][10];
    boxContents = new int[9][10];
  }

  /**
   * getCellValue()
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
   * setCellValue()
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
    if (val > 9 || val < 1)
      throw new IllegalArgumentException("The given value was not between 1 and 9");

    if (row > 8 || row < 0 || col > 8 || col < 0)
      throw new IndexOutOfBoundsException();

    cells[row][col] = val;
  }
}
