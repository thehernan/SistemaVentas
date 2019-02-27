/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.FormatoNumerico;
import DAO.ProductoDAO;
import Pojos.Producto;
import javax.swing.JOptionPane;

/**
 *
 * @author HERNAN
 */
public class JDAjusteExistencia extends javax.swing.JDialog {

    /**
     * Creates new form JDAjusteExistencia
     */
    Producto prod;
    ProductoDAO proddao;
    JIFrmBuscarProductos fijbuscarprod;
        
    public JDAjusteExistencia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
     public JDAjusteExistencia(java.awt.Frame parent, boolean modal,Producto prod,JIFrmBuscarProductos fijbuscarprod) {
        super(parent, modal);
        initComponents();
         FormatoNumerico fn = new FormatoNumerico();
        proddao = new ProductoDAO();
        this.prod=prod;
        this.fijbuscarprod = fijbuscarprod;
        jlblunidmed.setText("X "+prod.getUnidmedc());
        jtfcantidad.setText(fn.FormatoN(prod.getCantidad()));
        jtfcantidad.selectAll();
        jlblproddescrip.setText(prod.getDescripcion());
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

        jLabel1 = new javax.swing.JLabel();
        jlblunidmed = new javax.swing.JLabel();
        jtfcantidad = new javax.swing.JTextField();
        jbtnajustar = new javax.swing.JButton();
        jlblproddescrip = new javax.swing.JLabel();
        jlblmensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Existencia Actual:");

        jlblunidmed.setText("jLabel2");

        jtfcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfcantidadKeyReleased(evt);
            }
        });

        jbtnajustar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/accept2.png"))); // NOI18N
        jbtnajustar.setText("Ajustar");
        jbtnajustar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnajustarActionPerformed(evt);
            }
        });

        jlblproddescrip.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblproddescrip.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jlblproddescrip)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfcantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(jlblmensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnajustar)
                            .addComponent(jlblunidmed))))
                .addContainerGap(230, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblproddescrip)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblunidmed))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnajustar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jlblmensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnajustarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnajustarActionPerformed
        // TODO add your handling code here:
        double cant = Double.parseDouble(jtfcantidad.getText());
        
        if(proddao.ajustarexistencia(prod.getIdproducto(),cant)==true){
            JOptionPane.showMessageDialog(null,"Existencias ajustadas con éxito","",JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            fijbuscarprod.buscar();
        }else{
             JOptionPane.showMessageDialog(null,"Error","",JOptionPane.ERROR_MESSAGE);
        
        }
           
        
       
        
       
    }//GEN-LAST:event_jbtnajustarActionPerformed

    private void jtfcantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcantidadKeyReleased
        // TODO add your handling code here:
         try {
            
            double cant = Double.parseDouble(jtfcantidad.getText());
            if(cant <= 0){
                jlblmensaje.setText("Cantidad invalida");
                jbtnajustar.setEnabled(false);
           }else{
                jlblmensaje.setText("");
                jbtnajustar.setEnabled(true);
            }
            
        } catch (Exception e) {
            jlblmensaje.setText("Cantidad invalida");
            jbtnajustar.setEnabled(false);
        }
         
         if(evt.getKeyCode()==10){
             jbtnajustar.doClick();
         }
    }//GEN-LAST:event_jtfcantidadKeyReleased

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
            java.util.logging.Logger.getLogger(JDAjusteExistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDAjusteExistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDAjusteExistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDAjusteExistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDAjusteExistencia dialog = new JDAjusteExistencia(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jbtnajustar;
    private javax.swing.JLabel jlblmensaje;
    private javax.swing.JLabel jlblproddescrip;
    private javax.swing.JLabel jlblunidmed;
    private javax.swing.JTextField jtfcantidad;
    // End of variables declaration//GEN-END:variables
}
