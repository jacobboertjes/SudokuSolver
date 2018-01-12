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
     * udpateCellPossibilities
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
        
        
    }
}
