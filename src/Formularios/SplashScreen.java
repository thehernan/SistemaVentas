/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Conexion.TestConexionServer;
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
    javax.swing.Timer timer;
//    Login login= new Login() ;
   
    public  SplashScreen() {
         setUndecorated(true);
        initComponents();
        
         try {
            setIconImage(new ImageIcon(getClass().getResource("/imagenes/codprod.png")).getImage());
            
        } catch (Exception e) {
        }
        setLocationRelativeTo(null);
        

      
        
//        jProgressInicio.setMinimum(0);
//        jProgressInicio.setMaximum(100);
//        jProgressInicio.setStringPainted(true);
         
//        Thread t = new Thread() {
//            @Override
//            @SuppressWarnings("SleepWhileInLoop")
//            public void run() {
//                int i = 0;
//                while (i <= 100) {
//                    jProgressInicio.setValue(i);
//                    if(i==0){
//                        jlblMensaje.setText("Cargando componente de interfaz...");
//                    }
//                    if(i==23){
////                        jlblMensaje.setText("Comprobando base de datos...");
//                        
//                       
////                        valida=conexion.valida();
////                        System.out.println("val"+valida);
////                        if(valida==false){
////                            jlblMensaje.setText("No se obtuvo conexion con la base de datos...");
////                           
////                        }
//                            
//                    }
//                    if(i==67){
//                        jlblMensaje.setText("Cargando archivos de configuración...");
//                    }
//                    if(i==90){
////                         Conexion conexion= new Conexion();
////                         
////                        if(conexion.Conexion()==false){
////                        jlblMensaje.setText("No se obtuvo conexion con el servidor...");
////                        compruebaconexion=false;
////                        } else 
////                        {
////                        jlblMensaje.setText("Bienvenido al sistema");
////                         compruebaconexion=true;
////                        }
////                        dispose();
////                        AWTUtilities.setWindowOpaque(login, false);
////                        login.setVisible(true);
//                          
//                    }
//                    try {
//                        sleep(90);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
//                        
//                    }
//                    i++;
//                }
//            }
//        };
//         t.start();
//         
        carga();
    }
    
    
    public void carga(){
        
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        jProgressInicio.setMinimum(0);
        jProgressInicio.setMaximum(10);
        jProgressInicio.setStringPainted(true);
            int i = 0;
                while (i <= 10) {
                    jProgressInicio.setValue(i);
                    if(i==0){
                        jlblMensaje.setText("Cargando componente de interfaz...");
                    }
//                    if(i==23){
//                        jlblMensaje.setText("Cargando archivos de configuración...");
//                            
//                    }
                    if(i==5){
                        jlblMensaje.setText("Obteniendo conexión con el Servidor...");
                        int k;
                        TestConexionServer test= new TestConexionServer();
                         for (k=0;k<=3;k++){
                         
                         if(test.ping()==false){
                              try {
                                   sleep(900);
                               } catch (InterruptedException ex) {
                                   Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);

                               }
                            jlblMensaje.setText("<html>"+"No se obtuvo conexión con el servidor"+"<br>"+"pongase en contacto con el administrador del sistema");
                            
                                try {
                                   sleep(2000);
                               } catch (InterruptedException ex) {
                                   Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);

                               }
                               jlblMensaje.setText("Reconectando...");
                               try {
                                   sleep(4000);
                               } catch (InterruptedException ex) {
                                   Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);

                               }
                            
                            }else{
                             jlblMensaje.setText("Conexión exitosa...");
                            
                             try {
                                   sleep(1000);
                               } catch (InterruptedException ex) {
                                   Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);

                               }
                             break;
                            
                         }
                        
                            
                        }
                         System.out.println("k"+k);
                         if(k==4){
                             jlblMensaje.setText("<html><p style=\"color:#FF3333\";>"+"Imposible conectar con el servidor"+"<br>"+"pongase en contacto con el administrador del sistema");
                             while (true) {                                 
                                  System.out.println("k"+k);
                             }
                         
                         }
                    }
                    if(i==8){
                          jlblMensaje.setText("Accediendo a la interfaz del login...");
                    }
                    if(i==10){
                        Login nuevaSesion = new Login();
                        AWTUtilities.setWindowOpaque(nuevaSesion, false);
                        nuevaSesion.setVisible(true);
                        SplashScreen.this.dispose();
                    }
                    try {
                        sleep(90);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
                        
                    }
                    i++;
                }
//                SplashScreen bienvenido=new SplashScreen();
//                AWTUtilities.setWindowOpaque(bienvenido, false);
//                bienvenido.setVisible(true);
               
        }
        
    };
//    @Override
//    @SuppressWarnings("SleepWhileInLoop")
    Thread T = new Thread(runnable);
    T.start();
    
            
    
    
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
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlblMensaje.setBackground(new java.awt.Color(255, 255, 255));
        jlblMensaje.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlblMensaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/codprod.png"))); // NOI18N
        jlblMensaje.setText("jLabel2");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProgressInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                            .addComponent(jlblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblMensaje)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseReleased
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel1MouseReleased

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
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                 SplashScreen bienvenido=new SplashScreen();
//                  AWTUtilities.setWindowOpaque(bienvenido, false);
//                  bienvenido.setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressInicio;
    private javax.swing.JLabel jlblMensaje;
    // End of variables declaration//GEN-END:variables
}
