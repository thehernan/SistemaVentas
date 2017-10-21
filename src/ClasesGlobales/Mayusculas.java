/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesGlobales;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author info2017
 */
public class Mayusculas {
    
    public void convertirmayus(final JTextField text){
    text.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                String cadena = (text.getText()).toUpperCase();
                text.setText(cadena);
//                repaint();

            }
        });
    
    
    }
    public void convertirmayusTA(final JTextArea text){
    text.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                String cadena = (text.getText()).toUpperCase();
                text.setText(cadena);
//                repaint();

            }
        });
    
    
    }
      
    
}
