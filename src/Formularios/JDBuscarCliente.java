/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.Mayusculas;
import DAO.ClienteDAO;
import Pojos.Cliente;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author info2017
 */
public class JDBuscarCliente extends javax.swing.JDialog {

    /**
     * Creates new form JDBuscarCliente
     */
    ClienteDAO daocliente= new ClienteDAO();
    JIFReparaciones frmreparacion;
    Cliente cliente = new Cliente();
    JIFrmReparacionConsultar frmreparaconsul;
    JIFVentaConsultar frmventaconsul;
    String tipo="";
    Mayusculas mayus= new Mayusculas();
    List<Cliente> listcliente= new ArrayList<>();
    JDNotaCreditoDebito jdnotacreditodebito;
    
    public JDBuscarCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
    }
    
    public JDBuscarCliente(java.awt.Frame parent, boolean modal,JIFReparaciones frmreparacion) {
        super(parent, modal);
        initComponents();
//       listcliente= daocliente.mostrarcliente(jtabla);
        
         mostrar();
        this.frmreparacion= frmreparacion;
        this.setLocationRelativeTo(null);
        tipo="reparacion";
        jtfnombre.requestFocus();
    }
       public JDBuscarCliente(java.awt.Frame parent, boolean modal,JIFrmReparacionConsultar frmreparaconsul) {
        super(parent, modal);
        initComponents();
//        listcliente=daocliente.mostrarcliente(jtabla);
   
          mostrar();
        this.frmreparaconsul=frmreparaconsul;
        this.setLocationRelativeTo(null);
        tipo="reparaconsul";
        jtfnombre.requestFocus();
       
    }
       public JDBuscarCliente(java.awt.Frame parent, boolean modal,JIFVentaConsultar frmventaconsul) {
        super(parent, modal);
        initComponents();
//        listcliente=daocliente.mostrarcliente(jtabla);
      
        mostrar();
        this.frmventaconsul=frmventaconsul;
        this.setLocationRelativeTo(null);
        tipo="ventaconsul";
        jtfnombre.requestFocus();
         
    }
     public JDBuscarCliente(java.awt.Frame parent, boolean modal,JDNotaCreditoDebito jdnotacreditodebito) {
        super(parent, modal);
        initComponents();
        this.jdnotacreditodebito = jdnotacreditodebito;
        mostrar();
        this.setLocationRelativeTo(null);
        tipo="nota";
        jtfnombre.requestFocus();
        
    }
    
       
       public void mostrar(){
       Runnable  runnable = new Runnable() {

           @Override
           public void run() {
//               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                jlblimagencarga.setVisible(true);
                jlblletracarga.setVisible(true);
                listcliente=daocliente.mostrarcliente(jtabla);
                jlblimagencarga.setVisible(false);
                jlblletracarga.setVisible(false);
           }
       };
           Thread T = new Thread(runnable);
           T.start();
       
       
       
       }
       
       public synchronized  void sensitiva (){
           jlblimagencarga.setVisible(true);
           jlblletracarga.setVisible(true);
          listcliente=daocliente.busquedasensitivacliente("NOMBRE",jtfnombre.getText().trim().toUpperCase(),jtabla);
          jlblimagencarga.setVisible(false);
          jlblletracarga.setVisible(false);
       
       }
       


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jtfnombre = new javax.swing.JTextField();
        jbtnaceptar = new javax.swing.JButton();
        jlblimagencarga = new javax.swing.JLabel();
        jlblletracarga = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfnombre.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfnombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfnombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfnombreFocusLost(evt);
            }
        });
        jtfnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfnombreActionPerformed(evt);
            }
        });
        jtfnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfnombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfnombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfnombreKeyTyped(evt);
            }
        });
        jPanel1.add(jtfnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 550, -1));

        jbtnaceptar.setBackground(new java.awt.Color(77, 161, 227));
        jbtnaceptar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jbtnaceptar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnaceptar.setText("Aceptar");
        jbtnaceptar.setToolTipText("");
        jbtnaceptar.setBorderPainted(false);
        jbtnaceptar.setContentAreaFilled(false);
        jbtnaceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnaceptar.setOpaque(true);
        jbtnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnaceptarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnaceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, 130, 40));

        jlblimagencarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ring.gif"))); // NOI18N
        jPanel1.add(jlblimagencarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 190, 240));

        jlblletracarga.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        jlblletracarga.setText("Cargando Registros ...");
        jPanel1.add(jlblletracarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, -1, -1));

        jtabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtabla.getTableHeader().setReorderingAllowed(false);
        jtabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtablaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jtabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 800, 450));

        jPanel7.setBackground(new java.awt.Color(238, 238, 238));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel11.setText("BUSCAR CLIENTE");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, -1));

        jLabel1.setText("Señor(es):");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfnombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnombreFocusGained
        // TODO add your handling code here:
     
    }//GEN-LAST:event_jtfnombreFocusGained

    private void jtfnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfnombreKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jtfnombreKeyReleased

    private void jbtnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnaceptarActionPerformed
        // TODO add your handling code here:
       
            int index=jtabla.getSelectedRow();
            if(index>=0){
                cliente=listcliente.get(index);
                 if(tipo.equals("reparacion")){
                    
                    frmreparacion.setcliente(cliente);
                    frmreparacion.validaaceptar();
                    this.dispose();
                 }
                 if(tipo.equals("nota"))
                 {
                     jdnotacreditodebito.setcliente(cliente);
                     this.dispose();
                 
                 }
                 
//                 if(tipo.equals("reparaconsul")){
//                     frmreparaconsul.setcliente(cliente);
//                     this.dispose();
//        
//        
//                }
//                 if(tipo.equals("ventaconsul")){
//                     frmventaconsul.setcliente(cliente);
//                     this.dispose();
//        
//        
//                }
                 
                 
            }else {
            
                JOptionPane.showMessageDialog(null, "Seleccione Item");
                       
            
            }
            
            
//            else {
//                jlblmensaje.setText("SELECCIONE UN CLIENTE ");
//
//            }


            
        
            
        
        
    }//GEN-LAST:event_jbtnaceptarActionPerformed

    private void jtfnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfnombreKeyTyped
        // TODO add your handling code here:
//        mayus.convertirmayus(jtfnombre);
    }//GEN-LAST:event_jtfnombreKeyTyped

    private void jtfnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfnombreActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jtfnombreActionPerformed

    private void jtablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtablaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10)
        {
            jbtnaceptar.doClick();
        
        }
    }//GEN-LAST:event_jtablaKeyPressed

    private void jtfnombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfnombreKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            Runnable runnable = new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                
                sensitiva();
                if(jtabla.getSelectedRow()>=0)
                {
                    jtabla.requestFocus();
                }
                
            }
        };
        Thread T = new Thread(runnable);
        T.start();
        
        
        }
         
    }//GEN-LAST:event_jtfnombreKeyPressed

    private void jtfnombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnombreFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfnombreFocusLost

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
            java.util.logging.Logger.getLogger(JDBuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDBuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDBuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDBuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDBuscarCliente dialog = new JDBuscarCliente(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnaceptar;
    private javax.swing.JLabel jlblimagencarga;
    private javax.swing.JLabel jlblletracarga;
    private javax.swing.JTable jtabla;
    private javax.swing.JTextField jtfnombre;
    // End of variables declaration//GEN-END:variables
}
