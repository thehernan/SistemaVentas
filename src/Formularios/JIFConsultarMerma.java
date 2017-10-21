/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.MermaDAO;

/**
 *
 * @author info2017
 */
public class JIFConsultarMerma extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFConsultarMerma
     */
    MermaDAO daomerma = new MermaDAO();
    public JIFConsultarMerma() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jPanel1 = new javax.swing.JPanel();
        jbtnimprimir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jdpdesde = new org.jdesktop.swingx.JXDatePicker();
        jdphasta = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlblmensaje = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("CONSULTA DE MERMAS");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtnimprimir.setBackground(new java.awt.Color(255, 255, 255));
        jbtnimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/print_46933.png"))); // NOI18N
        jbtnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnimprimirActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnimprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INGRESE FECHA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jdpdesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 120, -1));
        jPanel2.add(jdphasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 140, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("DESDE:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("HASTA:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, 20));

        jlblmensaje.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlblmensaje.setForeground(new java.awt.Color(255, 51, 51));
        jPanel2.add(jlblmensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 490, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 570, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnimprimirActionPerformed
        // TODO add your handling code here:
        if(jdpdesde.getDate()!=null && jdphasta.getDate()!=null){
        daomerma.imprimir(new java.util.Date(jdpdesde.getDate().getTime()),new java.util.Date(jdphasta.getDate().getTime()));
        jlblmensaje.setText("");
        }else {
        jlblmensaje.setText("INGRESE FECHA");
               
        }
    }//GEN-LAST:event_jbtnimprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JButton jbtnimprimir;
    private org.jdesktop.swingx.JXDatePicker jdpdesde;
    private org.jdesktop.swingx.JXDatePicker jdphasta;
    private javax.swing.JLabel jlblmensaje;
    // End of variables declaration//GEN-END:variables
}
