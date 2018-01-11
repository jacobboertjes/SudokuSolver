/*
 * SudokuSolver.java
 * 
 * Purpose: To solve given sudoku puzzles
 *
 * Author: Jacob Boertjes
 *
 * Revisions:
 *  Oct 16, 2017 - Created
 */
package sudokusolver;

public class SudokuSolver {

    // Array to keep track of cell values and their posibilities
    //      example: cells[0][0][5] is 0 if 5 could'nt possibly go there 
    //               or 1 if it could
    private int[][][] cells;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Create the frame
        SudokuSolverWindow frame = new SudokuSolverWindow();
        
        // Make the button Listener and add it to button
        ButtonPressActionListener buttonListener = new ButtonPressActionListener();
        frame.solveButton.addActionListener(buttonListener);
        
        // Bring up the frame for user
        frame.setVisible(true);
        
    }

    /**
     * fillCellsArr()
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
    public void attemptSolve() {
        // Get all values from user input on screen
        fillCellsArr();
        
        
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
     *      None
    */
    private void fillCellsArr() {
        
    }
    
}
