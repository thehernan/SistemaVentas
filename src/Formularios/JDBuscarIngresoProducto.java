/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.ComprasDAO;
import Pojos.Compras;
import Pojos.SucursalSingleton;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author info2017
 */
public class JDBuscarIngresoProducto extends javax.swing.JDialog {

    /**
     * Creates new form JDBuscarIngresoProducto
     */
    ComprasDAO daocompras= new ComprasDAO();
    SucursalSingleton sucursalsingleton = SucursalSingleton.getinstancia();  
    JIFIngresoProducto frmingresoprod;
     List<Compras> listcompra= new ArrayList<>();
     Compras compra = new Compras();
    public JDBuscarIngresoProducto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
      ;
    }
     public JDBuscarIngresoProducto(java.awt.Frame parent, boolean modal,JIFIngresoProducto frmingresoprod) {
        super(parent, modal);
        initComponents();
        listcompra=daocompras.mostrar(jtabla, sucursalsingleton.getId());
        this.frmingresoprod= frmingresoprod;
        jbtnaceptar.setEnabled(false);
        this.setLocationRelativeTo(null);
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
        jLabel1 = new javax.swing.JLabel();
        jtfnumero = new javax.swing.JTextField();
        jbtnaceptar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("N°: ");

        jtfnumero.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfnumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfnumeroKeyReleased(evt);
            }
        });

        jbtnaceptar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnaceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/accept2.png"))); // NOI18N
        jbtnaceptar.setText("Aceptar");
        jbtnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnaceptarActionPerformed(evt);
            }
        });

        jtabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtabla.getTableHeader().setReorderingAllowed(false);
        jtabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtablaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtabla);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179)
                .addComponent(jbtnaceptar))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnaceptar)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfnumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnaceptarActionPerformed
        // TODO add your handling code here:
        int index = jtabla.getSelectedRow();
        if (index >=0){
            compra = listcompra.get(index);
            frmingresoprod.setbuscar(compra.getId_compra());
            
            
            jbtnaceptar.setEnabled(true);
            this.dispose();
        }else {
            JOptionPane.showMessageDialog(null, "SELECCIONE DOCUMENTO DE LA TABLA");
        
        }
    }//GEN-LAST:event_jbtnaceptarActionPerformed

    private void jtablaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaMouseReleased
        // TODO add your handling code here:
        int index = jtabla.getSelectedRow();
        if(index >= 0){
            jbtnaceptar.setEnabled(true);
        }
    }//GEN-LAST:event_jtablaMouseReleased

    private void jtfnumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfnumeroKeyReleased
        // TODO add your handling code here:
        
        daocompras.busquedasensitiva(jtabla, sucursalsingleton.getId(), jtfnumero.getText());
    }//GEN-LAST:event_jtfnumeroKeyReleased

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
            java.util.logging.Logger.getLogger(JDBuscarIngresoProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDBuscarIngresoProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDBuscarIngresoProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDBuscarIngresoProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDBuscarIngresoProducto dialog = new JDBuscarIngresoProducto(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnaceptar;
    private javax.swing.JTable jtabla;
    private javax.swing.JTextField jtfnumero;
    // End of variables declaration//GEN-END:variables
}
