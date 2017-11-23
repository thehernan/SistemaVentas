/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.Mayusculas;
import DAO.ReparacionDAO;
import DAO.VentasDAO;
import Pojos.Reparacion;
import Pojos.Ventas;
import javax.swing.JOptionPane;

/**
 *
 * @author info2017
 */
public class JDMotivoExtorno extends javax.swing.JDialog {

    /**
     * Creates new form JDMotivoExtorno
     */
    VentasDAO daoventa=new VentasDAO();
   
    Mayusculas mayus = new Mayusculas();
    String identifi;
    ReparacionDAO daorepa = new ReparacionDAO();
    Reparacion reparacion = new Reparacion();
    JDReparacionesEmpleado jdrepara;
    JIFrmReparacionConsultar jifreparaconsul;
    Ventas venta;
    JDVentasEmpleado jdventas;
    JIFVentaConsultar jifventaconsu;
    public JDMotivoExtorno(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public JDMotivoExtorno(java.awt.Frame parent, boolean modal,Ventas venta, JDVentasEmpleado jdventas) {
        super(parent, modal);
        initComponents();
        this.venta=venta;
        this.jdventas=jdventas;
        this.setLocationRelativeTo(null);
        identifi="VENTAEMPLEADO";
    }
    
    public JDMotivoExtorno(java.awt.Frame parent, boolean modal,Ventas venta, JIFVentaConsultar jifventaconsu) {
        super(parent, modal);
        initComponents();
        this.venta=venta;
        this.jifventaconsu=jifventaconsu;
        this.setLocationRelativeTo(null);
        identifi="VENTACLIENTE";
    }
    
     public JDMotivoExtorno(java.awt.Frame parent, boolean modal,Reparacion repara,JDReparacionesEmpleado jdrepara) {
        super(parent, modal);
        initComponents();
       
        this.reparacion=repara;
        this.jdrepara=jdrepara;
        this.setLocationRelativeTo(null);
        identifi="REPARAEMPLEADO";
    }
      public JDMotivoExtorno(java.awt.Frame parent, boolean modal,Reparacion repara,JIFrmReparacionConsultar jifreparaconsul) {
        super(parent, modal);
        initComponents();
       
        this.reparacion=repara;
        this.jifreparaconsul=jifreparaconsul;
        this.setLocationRelativeTo(null);
        identifi="REPARACLIENTE";
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtamotivo = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jbtnaceptar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MOTIVO DE ANULACION");
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtamotivo.setColumns(20);
        jtamotivo.setRows(5);
        jtamotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtamotivoKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jtamotivo);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 450, 160));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel1.setText("MOTIVO:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jbtnaceptar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnaceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/accept2.png"))); // NOI18N
        jbtnaceptar.setText("Aceptar");
        jbtnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnaceptarActionPerformed(evt);
            }
        });
        jPanel2.add(jbtnaceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnaceptarActionPerformed
        // TODO add your handling code here:
        
                 if(jtamotivo.getText().replaceAll("\\s", "").length()>0){
                     if(identifi.equals("VENTAEMPLEADO")){
                     if(JOptionPane.showConfirmDialog(null,"ESTA SEGURO DE ANULAR LA VENTA, ESTA OPERACION NO SE PODRA REVERTIR","MENSAJE SISTEMA",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                     daoventa.extornarconcretada(venta.getIdventa(),jtamotivo.getText());
                     jdventas.refrescar();
                     
                     this.dispose();
                     }                     
                     
                     }
                     
                      if(identifi.equals("VENTACLIENTE")){
                     if(JOptionPane.showConfirmDialog(null,"ESTA SEGURO DE ANULAR LA VENTA, ESTA OPERACION NO SE PODRA REVERTIR","MENSAJE SISTEMA",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                     daoventa.extornarconcretada(venta.getIdventa(),jtamotivo.getText());
                     jifventaconsu.refrecar();
                     
                     this.dispose();
                     }                     
                     
                     }
                      
                      if(identifi.equals("REPARAEMPLEADO")){
                          
                        if(JOptionPane.showConfirmDialog(null,"ESTA SEGURO DE ANULAR LA REPARACION, ESTA OPERACION NO SE PODRA REVERTIR","MENSAJE SISTEMA",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                         reparacion.setMotivo(jtamotivo.getText());
                         reparacion.getIdreparacion();
                            System.out.println("idrepara"+reparacion.getIdreparacion());
                         daorepa.extornar(reparacion);
                         
                         
                         jdrepara.mostrar();
                        this.dispose();
                      }
                      }
                      
                       if(identifi.equals("REPARACLIENTE")){
                          
                        if(JOptionPane.showConfirmDialog(null,"ESTA SEGURO DE ANULAR LA REPARACION, ESTA OPERACION NO SE PODRA REVERTIR","MENSAJE SISTEMA",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                         reparacion.setMotivo(jtamotivo.getText());
                         reparacion.getIdreparacion();
                            System.out.println("idrepara"+reparacion.getIdreparacion());
                         daorepa.extornar(reparacion);
                         
                         
                         jifreparaconsul.refrescar();
                        this.dispose();
                      }
                      }
                     
                     
                 }else {
                     JOptionPane.showMessageDialog(null, "INGRESE MOTIVO");
                 
                 }
                 
             
                
    }//GEN-LAST:event_jbtnaceptarActionPerformed

    private void jtamotivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtamotivoKeyTyped
        // TODO add your handling code here:
        mayus.convertirmayusTA(jtamotivo);
    }//GEN-LAST:event_jtamotivoKeyTyped

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
            java.util.logging.Logger.getLogger(JDMotivoExtorno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDMotivoExtorno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDMotivoExtorno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDMotivoExtorno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDMotivoExtorno dialog = new JDMotivoExtorno(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnaceptar;
    private javax.swing.JTextArea jtamotivo;
    // End of variables declaration//GEN-END:variables
}