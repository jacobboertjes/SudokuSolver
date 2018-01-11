/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jacob
 */
public class ButtonPressActionListener implements ActionListener {
    
    // ActionHandler for a button press
    @Override
    public void actionPerformed(ActionEvent e) {
        SudokuSolver.attemptSolve();
    }
}
