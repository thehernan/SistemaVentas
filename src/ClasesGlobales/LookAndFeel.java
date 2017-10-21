/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesGlobales;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author info2017
 */
public class LookAndFeel {
    public void setLook( String laf,JFrame frame)
    {
 
            if (laf==null) {
                    laf=UIManager.getSystemLookAndFeelClassName();
            }
            else
            {
                try {
                        UIManager.setLookAndFeel(laf);
                } catch (Exception  ex) {
 
                }
            }
            SwingUtilities.updateComponentTreeUI(frame);
    }
    
}

