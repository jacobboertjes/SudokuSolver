/*
 * SudokuSolver.java
 * 
 * Purpose: To solve given sudoku puzzles
 *
 * Author: Jacob Boertjes
 *
 * Revisions:
 *  Jan 3, 2017 - Created
 */
package sudokusolver;

import javax.swing.JOptionPane;

public class SudokuSolver {

    // Array to keep track of cell values and their posibilities
    //      example: cells[0][0][5] is 0 if 5 could'nt possibly go there 
    //               or 1 if it could
    private static int[][][] cells;
    
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
        cells = new int[9][9][9];
        
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
    public static void attemptSolve() {
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
     *      true  - If the array was successfully filled
     *      false - If the array was not successfully filled
    */
    private static boolean fillCellsArr() {
        
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
                }
                catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(frame, "Could not parse cell at\nrow: "+row+"\ncol: "+col+"\n\n"+e,"Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                
            }
        }
        return true;
    }
    
}
