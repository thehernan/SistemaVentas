/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import javax.swing.JOptionPane;

/**
 *
 * @author info2017
 */
public class JIFAsistencia extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFAsistencia
     */
    public JIFAsistencia() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtfrut = new javax.swing.JTextField();
        jlblfotoasistencia = new javax.swing.JLabel();
        clockDigital = new org.edisoncor.gui.varios.ClockDigital();
        clockFace1 = new org.edisoncor.gui.varios.ClockFace();
        jlblestado = new javax.swing.JLabel();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("RUT:");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 90, 20));

        jtfrut.setToolTipText("");
        jtfrut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfrutActionPerformed(evt);
            }
        });
        jtfrut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfrutKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfrutKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfrutKeyTyped(evt);
            }
        });
        jPanel1.add(jtfrut, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 240, -1));

        jlblfotoasistencia.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jlblfotoasistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 340, 350));

        clockDigital.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 60)); // NOI18N
        jPanel1.add(clockDigital, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 420, 100));

        clockFace1.setBackground(new java.awt.Color(255, 255, 255));
        clockFace1.setRomano(false);
        jPanel1.add(clockFace1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 180, 180));

        jlblestado.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlblestado.setText("------------");
        jPanel1.add(jlblestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 967, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfrutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfrutActionPerformed
        // TODO add your handling code here:
        String rut= (jtfrut.getText());
        System.out.println("rut"+rut);

        if ((rut.length()==9) || (rut.length()==10)){
          

            if (JOptionPane.showConfirmDialog(this,"REGISTRAR ASISTENCIA?","",JOptionPane.YES_NO_CANCEL_OPTION)==JOptionPane.YES_OPTION){
                

            }}
    }//GEN-LAST:event_jtfrutActionPerformed

    private void jtfrutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfrutKeyPressed
        // TODO add your handling code here:
        //        int evento= evt.getKeyCode();
        //        if (evento==10){
            //            System.out.println("teclaenter");
            //        }
    }//GEN-LAST:event_jtfrutKeyPressed

    private void jtfrutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfrutKeyReleased
        // TODO add your handling code here:

        //        String rut= jtfrut.getText();
        //        idalum=buscar("RUT", rut, null);

        //        Integer evento = evt.getKeyCode();
        //        if (evento==10){
            //            if (buscarduplicado()==false){
                //
                //            }else {
                //                JOptionPane.showMessageDialog(this,"EL ALUMNO YA SE ENCUENTRA REGISTRADO");
                //            }
            //            System.out.println("enter");
            //
            //
            //
            //        }
    }//GEN-LAST:event_jtfrutKeyReleased

    private void jtfrutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfrutKeyTyped
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jtfrutKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.varios.ClockDigital clockDigital;
    private org.edisoncor.gui.varios.ClockFace clockFace1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlblestado;
    private javax.swing.JLabel jlblfotoasistencia;
    private javax.swing.JTextField jtfrut;
    // End of variables declaration//GEN-END:variables
}
