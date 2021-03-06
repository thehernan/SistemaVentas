/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;


import ClasesGlobales.CifradoMD5;
import ClasesGlobales.LookAndFeel;
//import Conexion.TestConexionServer;
import DAO.EmpleadoDAO;
import DAO.SucursalDAO;
import DAO.UsuariosDAO;
import Pojos.EmpleadoSingleton;
import Pojos.Sucursal;
import Pojos.SucursalSingleton;
import Pojos.UsuarioSingleton;
import com.sun.awt.AWTUtilities;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author info2017
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
 
     int posx;
    int posy;
//    Empleado empleado = new Empleado();
    EmpleadoDAO daoempleado = new EmpleadoDAO();
    UsuariosDAO daousuario= new UsuariosDAO();
    SucursalDAO daosucursal= new SucursalDAO();
    EmpleadoSingleton empleadosingleton;
    SucursalSingleton sucursalsingleton = SucursalSingleton.getinstancia();
    
    List<Sucursal> listsucursal= new ArrayList<>();
    LookAndFeel look = new LookAndFeel();
    
    Thread pc;
    public Login() {
        initComponents();
       try {
            setIconImage(new ImageIcon(getClass().getResource("/imagenes/codprod.png")).getImage());
            
        } catch (Exception e) {
        }
        
       setLocationRelativeTo(null);
       listsucursal=daosucursal.llenarcombo(jcbsucursal);
       jlblimagencarga.setVisible(false);
//       pruebaconexion();
       
    }
    
//    public void pruebaconexion(){
//     
//     Runnable runnablepc = new Runnable() {
//
//         @Override
//         public void run() {
//             int i=0;
////             throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//             TestConexionServer test= new TestConexionServer();
//             
//             while(true){
//                
//                 try {
//                     Thread.sleep(1000);
//                 } catch (InterruptedException ex) {
//                     Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
//                 }
//                  
//                 System.out.println("probando conexion...login");
//                 System.out.println("i"+i);
//                if(test.ping()==true){
//                   
//                   jlblrpruebaconexion.setText("<html><p style=\"color:#FFFFFF\" ;>"+"Cloud Habilitado");
//////                   jlblrpruebaconexion.setIcon(null);
////                   jcbsucursal.setEnabled(true);
////                   jtfusuario.setEnabled(true);
////                   jtfclave.setEnabled(true);
////                   jbtnaceptar.setEnabled(true);
//                   
//                   
//               } else{ 
//                   jlblrpruebaconexion.setText("<html><p style=\"color:#FF0000 \";>"+"Cloud Inhabilitado");
//                   
//                   JDReconexion reconexion= new JDReconexion(new Frame(), isVisible());
//                   AWTUtilities.setWindowOpaque(reconexion, false);
//                   reconexion.setVisible(true);
//                    try {
//                     Thread.sleep(3000);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//             
//                    jlblrpruebaconexion.setText("<html><p style=\"color:#FF0000 \";>"+"Reconectando ...");
//                  
//               }
//             
//              if(Login.this.isVisible()==false){
//                       break;
//                   }
//             }
//             
//         }
//     };
//     pc = new Thread(runnablepc);
//     pc.start();
//     
//     
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlblimagencarga = new javax.swing.JLabel();
        jtfusuario = new javax.swing.JTextField();
        jtfclave = new javax.swing.JPasswordField();
        jlblcerrar = new javax.swing.JLabel();
        jcbsucursal = new javax.swing.JComboBox();
        jbtnaceptar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblimagencarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ring.gif"))); // NOI18N
        getContentPane().add(jlblimagencarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, -1, -1));

        jtfusuario.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jtfusuario.setText("USUARIO");
        jtfusuario.setBorder(null);
        jtfusuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfusuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfusuarioFocusLost(evt);
            }
        });
        jtfusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfusuarioActionPerformed(evt);
            }
        });
        getContentPane().add(jtfusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, 220, 30));

        jtfclave.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jtfclave.setText("CLAVE");
        jtfclave.setBorder(null);
        jtfclave.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfclaveFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfclaveFocusLost(evt);
            }
        });
        jtfclave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfclaveActionPerformed(evt);
            }
        });
        getContentPane().add(jtfclave, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, 220, 30));

        jlblcerrar.setBackground(new java.awt.Color(255, 255, 255));
        jlblcerrar.setForeground(new java.awt.Color(255, 255, 255));
        jlblcerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblcerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel_close.png"))); // NOI18N
        jlblcerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblcerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jlblcerrarMousePressed(evt);
            }
        });
        getContentPane().add(jlblcerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 30, -1));

        jcbsucursal.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jcbsucursal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sin conexion" }));
        getContentPane().add(jcbsucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 220, 30));

        jbtnaceptar.setBackground(new java.awt.Color(0, 0, 0));
        jbtnaceptar.setForeground(new java.awt.Color(0, 0, 0));
        jbtnaceptar.setBorder(null);
        jbtnaceptar.setBorderPainted(false);
        jbtnaceptar.setContentAreaFilled(false);
        jbtnaceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnaceptar.setRequestFocusEnabled(false);
        jbtnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnaceptarActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnaceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 330, 90));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 9)); // NOI18N
        jLabel3.setText("Ultima actualización 02/01/2019.");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 610, -1, -1));

        fondo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/loginvtech.png"))); // NOI18N
        fondo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                fondoMouseDragged(evt);
            }
        });
        fondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                fondoMousePressed(evt);
            }
        });
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 350, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fondoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fondoMousePressed
        // TODO add your handling code here:
        
        posy=evt.getY();
        posx=evt.getX();
    }//GEN-LAST:event_fondoMousePressed

    private void fondoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fondoMouseDragged
        // TODO add your handling code here:
          int xp=evt.getXOnScreen() - posx;
        int yp=evt.getYOnScreen() - posy;
        this.setLocation(xp, yp);
    }//GEN-LAST:event_fondoMouseDragged

    private void jtfusuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfusuarioFocusGained
        // TODO add your handling code here:
        if (jtfusuario.getText().equals("USUARIO")){
            jtfusuario.setText("");
        
        }
        
    }//GEN-LAST:event_jtfusuarioFocusGained

    private void jtfusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfusuarioActionPerformed
        // TODO add your handling code here:
        jbtnaceptar.doClick();
    }//GEN-LAST:event_jtfusuarioActionPerformed

    private void jtfusuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfusuarioFocusLost
        // TODO add your handling code here:
        if(jtfusuario.getText().equals("")){
           jtfusuario.setText("USUARIO");
        }
    }//GEN-LAST:event_jtfusuarioFocusLost

    private void jtfclaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfclaveActionPerformed
        // TODO add your handling code here:
        jbtnaceptar.doClick();
    }//GEN-LAST:event_jtfclaveActionPerformed

    private void jlblcerrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblcerrarMousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jlblcerrarMousePressed

    private void jtfclaveFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfclaveFocusGained
        // TODO add your handling code here:
        if(new String(jtfclave.getPassword()).equals("CLAVE")){
            jtfclave.setText("");
        
        
        }
    }//GEN-LAST:event_jtfclaveFocusGained

    private void jtfclaveFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfclaveFocusLost
        // TODO add your handling code here:
        if(new String(jtfclave.getPassword()).equals("")){
            jtfclave.setText("CLAVE");
        
        
        }
    }//GEN-LAST:event_jtfclaveFocusLost

    private void jbtnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnaceptarActionPerformed
        // TODO add your handling code here:
         Runnable runnable= new Runnable() {

            @Override
            public void run() {
                System.out.println("thread login");
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
                if((jtfusuario.getText().length()>0 && !jtfusuario.getText().equals("USUARIO")) &&
                (new String(jtfclave.getPassword()).length()>0) && jcbsucursal.getSelectedIndex()>0){
                    jlblimagencarga.setVisible(true);
                    //////////////////////////
//                    pruebaconexion(false); /// pauso hilo de comprobacion de conexion
//                    System.out.println("prueba conexion pausa");
                    ////////////////////////////////
                    jtfusuario.setEnabled(false);
                    jtfclave.setEnabled(false);
                    jcbsucursal.setEnabled(false);
                    jbtnaceptar.setEnabled(false);
                    
                    UsuarioSingleton user;
                    user = daousuario.validauser(jtfusuario.getText(), CifradoMD5.encriptaEnMD5(new String(jtfclave.getPassword())));
                    System.out.println("user"+user.getUsuario());
                    System.out.println("Clave md5: "+CifradoMD5.encriptaEnMD5(new String(jtfclave.getPassword())));
                    if(user.getUsuario()!=null){

                        empleadosingleton = daoempleado.cargarempleadosinfoto("ID", user.getIdempleado());
                        System.out.println(listsucursal.get(1));
                        sucursalsingleton = sucursalsingleton.setcargarsucursal(listsucursal.get(jcbsucursal.getSelectedIndex()));
                        MDIMenu menu = new MDIMenu();
        //                AWTUtilities.setWindowOpaque(menu, false);
                        menu.cargarImagen();
                        
        //                look.setLook(user.getTema(), menu);
                        Login.this.dispose();
                        /*Se envia el objeto login para regresar en caso de cerrar sesión, y se
                        envía el objeto usuario, que contiene toda la información del usuario*/
                        menu.setVisible(true);
                      
                }else {
                    JOptionPane.showMessageDialog(null, "Usuario o clave erroneos","",JOptionPane.ERROR_MESSAGE);
                    jtfusuario.setEnabled(true);
                    jtfclave.setEnabled(true);
                    jcbsucursal.setEnabled(true);
                    jbtnaceptar.setEnabled(true);
                   
                    ////////////////////////
//                    pruebaconexion(true);// inicio thread de comprobacion de conexion
//                    System.out.println("prueba conexion inicio");
                    //////////////////////
                }
                    jlblimagencarga.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null, "Rellena todos los campos.","Error", JOptionPane.ERROR_MESSAGE);
             ////////////////////////
//            pruebaconexion(true);// inicio thread de comprobacion de conexion
//            System.out.println("prueba conexion inicio");
            //////////////////////
   
        }      
            }
        };
        Thread T = new Thread(runnable);
        T.start();
    }//GEN-LAST:event_jbtnaceptarActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               Login login =new Login();
                AWTUtilities.setWindowOpaque(login, false);
                login.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jbtnaceptar;
    private javax.swing.JComboBox jcbsucursal;
    private javax.swing.JLabel jlblcerrar;
    private javax.swing.JLabel jlblimagencarga;
    private javax.swing.JPasswordField jtfclave;
    private javax.swing.JTextField jtfusuario;
    // End of variables declaration//GEN-END:variables
}
