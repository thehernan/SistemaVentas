/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaventas2.pkg0;

import Formularios.Login;
import Formularios.SplashScreen;
import com.sun.awt.AWTUtilities;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author info2017
 */
public class SistemaVentas20 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
          try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//             UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//             UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//              UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
//              UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");// 1
//              UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//               UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
//              UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(Exception e){
            System.err.println("Error Look GUI: "+e.getMessage());
        }
         
         
        SplashScreen s=new SplashScreen();
        AWTUtilities.setWindowOpaque(s, false);
        s.setVisible(true);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SistemaVentas20.class.getName()).log(Level.SEVERE, null, ex);
        }
        s.dispose();
        SwingUtilities.invokeLater(new Runnable(){
//            @Override
            public void run(){
                /*Aquí crean el objeto hacía su aplicación, para hacer visible*/
                Login nuevaSesion = new Login();
                AWTUtilities.setWindowOpaque(nuevaSesion, false);
                nuevaSesion.setVisible(true);
            }
        });        
        // TODO code application logic here
    }
    
}
