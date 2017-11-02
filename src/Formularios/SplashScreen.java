/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Conexion.Conexion;
import com.sun.awt.AWTUtilities;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author info2017
 */
public class SplashScreen extends javax.swing.JFrame {

    /**
     * Creates new form SplashCreen
     */
    
   
    boolean valida;
    Login login= new Login() ;
   
    public  SplashScreen() {
         setUndecorated(true);
        initComponents();
        
         try {
            setIconImage(new ImageIcon(getClass().getResource("/imagenes/logoicono.png")).getImage());
            
        } catch (Exception e) {
        }
        setLocationRelativeTo(null);
        //Image icon3 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../images/favicon.png"));
        //setIconImage(icon3);
        setLocationRelativeTo(null);
        jProgressInicio.setMinimum(0);
        jProgressInicio.setMaximum(100);
        jProgressInicio.setStringPainted(true);
         
        Thread t = new Thread() {
            @Override
            @SuppressWarnings("SleepWhileInLoop")
            public void run() {
                int i = 0;
                while (i <= 100) {
                    jProgressInicio.setValue(i);
                    if(i==0){
                        jlblMensaje.setText("Cargando componente de interfaz...");
                    }
                    if(i==23){
//                        jlblMensaje.setText("Comprobando base de datos...");
                        
                       
//                        valida=conexion.valida();
//                        System.out.println("val"+valida);
//                        if(valida==false){
//                            jlblMensaje.setText("No se obtuvo conexion con la base de datos...");
//                           
//                        }
                            
                    }
                    if(i==67){
                        jlblMensaje.setText("Cargando archivos de configuración...");
                    }
                    if(i==90){
//                         Conexion conexion= new Conexion();
//                         
//                        if(conexion.Conexion()==false){
//                        jlblMensaje.setText("No se obtuvo conexion con el servidor...");
//                        compruebaconexion=false;
//                        } else 
//                        {
//                        jlblMensaje.setText("Bienvenido al sistema");
//                         compruebaconexion=true;
//                        }
//                        dispose();
//                        AWTUtilities.setWindowOpaque(login, false);
//                        login.setVisible(true);
                          
                    }
                    try {
                        sleep(90);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
                        
                    }
                    i++;
                }
            }
        };
         t.start();
//        if(valida==false){
//           t.stop();
//       
//        }
         
//         try {
//            Thread.sleep(10000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(SistemaVentas20.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        this.dispose();
     
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressInicio = new javax.swing.JProgressBar();
        jlblMensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlblMensaje.setBackground(new java.awt.Color(255, 255, 255));
        jlblMensaje.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jProgressInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jlblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(139, Short.MAX_VALUE)
                .addComponent(jProgressInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jlblMensaje)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SplashScreen bienvenido=new SplashScreen();
                  AWTUtilities.setWindowOpaque(bienvenido, false);
                  bienvenido.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar jProgressInicio;
    private javax.swing.JLabel jlblMensaje;
    // End of variables declaration//GEN-END:variables
}