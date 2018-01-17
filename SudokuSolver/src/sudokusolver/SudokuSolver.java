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

    // The SudokuPuzzle
    static SudokuPuzzle puzzle;
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

        // Initialize puzzle
        puzzle = new SudokuPuzzle(frame);
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
        if (!fillPuzzleCells()) return;

        // Fill possible values for all cells
        puzzle.fillAllCellPossibilities();
    }

    /**
     * fillPuzzleCells()
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
    private static boolean fillPuzzleCells() {

        // Iterate over all textBoxes
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                // Try to parse a digit from the cell contents
                int val;

                // If the cell is empty, just use 0
                if (frame.textFields[row][col].getText().isEmpty()) {
                    puzzle.setCellValue(row,col,0);
                    continue;
                }
                try  {
                  // Parse out the integer
                  val = Integer.parseInt(frame.textFields[row][col].getText());

                  // Update the cell's value
                  puzzle.setCellValue(row,col,val);

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
}
