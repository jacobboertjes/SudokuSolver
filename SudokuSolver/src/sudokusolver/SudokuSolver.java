/*
 * SudokuSolver.java
 *
 * Purpose: To solve given sudoku puzzles
 *
 * Author: Jacob Boertjes
 *
 * Revisions:
 *  File Created - January 3, 2018
 *  fillCellsArr() added - January 12, 2018
 *  attemptSolve() added - January 12, 2018
 */
package sudokusolver;

import javax.swing.JOptionPane;

public class SudokuSolver {

    // Array to keep track of cell values and their posibilities
    //      example: cells[row][col][5] is 0 if 5 could'nt possibly go there
    //               or 1 if it could
    //      example: cells[row][col][0] is the value which is occupying the cell,
    //               or 0 if the cell is empty
    private static int[][][] cells;

    // An int to keep track of number of filled cells
    private static int filledCells = 0;

    // The frame which contains the sudoku puzzle
    static SudokuSolverWindow frame;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Create the frame
        frame = new SudokuSolverWindow();

        // Make the button Listener and add it to button
        ButtonPressActionListener buttonListener = new ButtonPressActionListener();
        frame.solveButton.addActionListener(buttonListener);

        // Bring up the frame for user
        frame.setVisible(true);

        // Initialize cells
        cells = new int[9][9][10];

    }

    /**
     * attemptSolve()
     *
     * Purpose:
     *      Attempts to solve the sudoku puzzle that the user inputed
     *
     * Input:
     *      None
     *
     * Output:
     *      None
    */
    public static void attemptSolve() {
        // Get all values from user input on screen
        //     If it failed (returns false) then return
        if (fillCellsArr() == false) return;


        // Start by filling all possible values for all cells
        fillCellPossibilities();

//        while (filledCells < 81) {
//
//        }

    }

    /**
     * fillCellsArr()
     *
     * Purpose:
     *      Fills the array cells with user inputed values from the text boxes
     *
     * Input:
     *      None
     *
     * Output:
     *      true  - If the array was successfully filled
     *      false - If the array was not successfully filled
     *
     * Effects:
     *      Displays error window if user inputed a value which was not 1-9
    */
    private static boolean fillCellsArr() {

        // Reset filledCells to 0
        filledCells = 0;

        // Iterate over all textBoxes
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                // Try to parse a digit from the cell contents
                int val;

                // If the cell is empty, just use 0
                if (frame.textFields[row][col].getText().isEmpty()) {
                    cells[row][col][0] = 0;
                    continue;
                }
                try  {
                    val = Integer.parseInt(frame.textFields[row][col].getText());
                    filledCells++;

                    // If the number was 0, throw an exception for same behaviour as a non-number
                    if (val == 0) {
                        throw new NumberFormatException();
                    }

                }
                catch (NumberFormatException e){

                    // Display error window for user
                    JOptionPane.showMessageDialog(frame, "Inproper input found at\nRow: "+(row+1)+"\nCol: "+(col+1)+"\nValue: \""+frame.textFields[row][col].getText()+"\"","Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

            }
        }
        return true;
    }

    /**
     * fillCellPossibilities()
     *
     * Purpose:
     *      Updates which values are possible for the all cells
     *
     * Input:
     *  None
     *
     * Output:
     *  None
     *
     * Assumption:
     *      The cell does not already have a value (it is an empty cell)
     */
    private static void fillCellPossibilities() {

        // Loop over cells and rows
        for (int i = 0; i <9; i++) {
            fillRowPossibilities(i);
        }

    }

    /**
     * fillRowPossibilities()
     *
     * Purpose:
     *      Fills the possible values in a given row
     *      (doesn't take columns or groups into account)
     *
     * Input:
     *  @param row - The row index which should be updated
     *
     * Output:
     *  None
     */
    private static void fillRowPossibilities(int row) {
        // An array to keep track of which numbers we have seen
        //    seenNumber[i]==false means we have not seen the number at i
        //    seenNumber[i]==true means we have seen it
        Boolean seenNumber[] = {false, false, false, false, false, false, false, false, false}

        // Loop over all cols in this row to first fill in which numbers are in this row
        for (int col = 0; col < 9; col++) {
          // If the cell is not empty, mark that we have seen the number in the cell
          if (cells[row][col][0] != 0) seenNumber[cells[row][col][0]] = true;
        }

        // Loop over all cols in the row to fill in which values still need to be in that row
        for (int col = 0; col < 9; col++) {
          // If the cell is empty, update possible values
          //    So if a cell could have the number 6, we say:
          //    cells[row][col][6]=1
          //if (cells[row][col][0] == 0) {}
        }

    }
}
