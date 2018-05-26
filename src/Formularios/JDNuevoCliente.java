/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.ClienteDAO;
import DAO.ClienteTipoDocumentoDAO;
import Pojos.Cliente;
import Pojos.Cliente_Tipo_Documento;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author HERNAN
 */
public class JDNuevoCliente extends java.awt.Dialog {

    /**
     * Creates new form JDNuevoCliente
     */
    String doc;
    List<Cliente_Tipo_Documento> listdoc;
    public JDNuevoCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
     public JDNuevoCliente(java.awt.Frame parent, boolean modal,String doc) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.doc=doc;
        jtfrut.setText(doc);
        jbtnguardar.setEnabled(false);
        llenartipodoc();
    }
    
    public void llenartipodoc(){
        
        ClienteTipoDocumentoDAO docdao = new ClienteTipoDocumentoDAO();
        
        jcbtipodoc.addItem("<<Seleccione>>");
        
        listdoc = docdao.mostrar();
        for(Cliente_Tipo_Documento docs:listdoc){
            
            jcbtipodoc.addItem(docs.getOp()+" - "+docs.getDocumento());
        }
    
    
    }
    
    
    
    public void validaguardar(){
        String nombre= jtfnombrerazon.getText().replaceAll("\\s","");
        String rut= jtfrut.getText().replaceAll("\\s","");
        String direc=jtfdireccion.getText().replaceAll("\\s", "");
        String cel=jtfcelular.getText().replaceAll("\\s", "");
        String email=jtfemail.getText().replaceAll("\\s", "");
        
    if( nombre.length()>0 &&  rut.length()>0 && direc.length()>0 && cel.length()>0 && email.length()>0 &&
            jcbtipodoc.getSelectedIndex()!=0){
        jbtnguardar.setEnabled(true);
    }else {
        jbtnguardar.setEnabled(false);
    }
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jtfrut = new javax.swing.JTextField();
        jtfdireccion = new javax.swing.JTextField();
        jtfcelular = new javax.swing.JTextField();
        jtfemail = new javax.swing.JTextField();
        jtfnombrerazon = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcbtipodoc = new javax.swing.JComboBox();
        jbtnguardar = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(238, 238, 238));
        jPanel1.setForeground(new java.awt.Color(238, 238, 238));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel1.setText("NUEVO CLIENTE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfrut.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfrut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfrutFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfrutFocusLost(evt);
            }
        });
        jtfrut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfrutKeyReleased(evt);
            }
        });
        jPanel2.add(jtfrut, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 190, -1));

        jtfdireccion.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfdireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfdireccionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfdireccionFocusLost(evt);
            }
        });
        jtfdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfdireccionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfdireccionKeyTyped(evt);
            }
        });
        jPanel2.add(jtfdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 370, -1));

        jtfcelular.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfcelular.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfcelularFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfcelularFocusLost(evt);
            }
        });
        jtfcelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfcelularKeyReleased(evt);
            }
        });
        jPanel2.add(jtfcelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, 140, -1));

        jtfemail.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfemail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfemailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfemailFocusLost(evt);
            }
        });
        jtfemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfemailKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfemailKeyTyped(evt);
            }
        });
        jPanel2.add(jtfemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 540, 20));

        jtfnombrerazon.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfnombrerazon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfnombrerazonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfnombrerazonFocusLost(evt);
            }
        });
        jtfnombrerazon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfnombrerazonKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfnombrerazonKeyTyped(evt);
            }
        });
        jPanel2.add(jtfnombrerazon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 540, -1));

        jLabel3.setText("Sr(es):");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel4.setText("N° Doc.:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, -1, -1));

        jLabel5.setText("Dirección:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel6.setText("Celular:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, -1, -1));

        jLabel7.setText("E-mail:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel2.setText("Tipo de Doc.:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jcbtipodoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbtipodocActionPerformed(evt);
            }
        });
        jPanel2.add(jcbtipodoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 330, -1));

        jbtnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save20x20.png"))); // NOI18N
        jbtnguardar.setText("Guardar");
        jbtnguardar.setBorderPainted(false);
        jbtnguardar.setContentAreaFilled(false);
        jbtnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnguardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnguardar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnguardar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jtfrutFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfrutFocusGained
        // TODO add your handling code here:
        //         if (jtfrut.getText().equals("R.U.T")){
            //            jtfrut.setText("");
            //                }
    }//GEN-LAST:event_jtfrutFocusGained

    private void jtfrutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfrutFocusLost
        // TODO add your handling code here:
        //          if (jtfrut.getText().equals("")){
            //            jtfrut.setText("R.U.T");
            //                }
    }//GEN-LAST:event_jtfrutFocusLost

    private void jtfrutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfrutKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfrutKeyReleased

    private void jtfdireccionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfdireccionFocusGained
        // TODO add your handling code here:
        //        if (jtfdireccion.getText().equals("DIRECCION")){
            //            jtfdireccion.setText("");
            //                }
    }//GEN-LAST:event_jtfdireccionFocusGained

    private void jtfdireccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfdireccionFocusLost
        // TODO add your handling code here:
        //          if (jtfdireccion.getText().equals("")){
            //            jtfdireccion.setText("DIRECCION");
            //                }
    }//GEN-LAST:event_jtfdireccionFocusLost

    private void jtfdireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfdireccionKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfdireccionKeyReleased

    private void jtfdireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfdireccionKeyTyped
        // TODO add your handling code here:
        //         mayus.convertirmayus(jtfdireccion);
    }//GEN-LAST:event_jtfdireccionKeyTyped

    private void jtfcelularFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcelularFocusGained
        // TODO add your handling code here:
        //         if (jtfcelular.getText().equals("CELULAR")){
            //            jtfcelular.setText("");
            //                }
    }//GEN-LAST:event_jtfcelularFocusGained

    private void jtfcelularFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcelularFocusLost
        //        // TODO add your handling code here:
        //         if (jtfcelular.getText().equals("")){
            //            jtfcelular.setText("CELULAR");
            //                }
    }//GEN-LAST:event_jtfcelularFocusLost

    private void jtfcelularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcelularKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfcelularKeyReleased

    private void jtfemailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfemailFocusGained
        // TODO add your handling code here:
        //         if (jtfemail.getText().equals("EMAIL")){
            //            jtfemail.setText("");
            //                }
    }//GEN-LAST:event_jtfemailFocusGained

    private void jtfemailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfemailFocusLost
        // TODO add your handling code here:
        //           if (jtfemail.getText().equals("")){
            //            jtfemail.setText("EMAIL");
            //                }
    }//GEN-LAST:event_jtfemailFocusLost

    private void jtfemailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfemailKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfemailKeyReleased

    private void jtfemailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfemailKeyTyped
        // TODO add your handling code here:
        //        mayus.convertirmayus(jtfemail);
    }//GEN-LAST:event_jtfemailKeyTyped

    private void jtfnombrerazonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnombrerazonFocusGained
        // TODO add your handling code here:
        //         if (jtfnombrerazon.getText().equals("NOMBRE O RAZON SOCIAL")){
            //            jtfnombrerazon.setText("");
            //                }
    }//GEN-LAST:event_jtfnombrerazonFocusGained

    private void jtfnombrerazonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnombrerazonFocusLost
        // TODO add your handling code here:
        //          if (jtfnombrerazon.getText().equals("")){
            //            jtfnombrerazon.setText("NOMBRE O RAZON SOCIAL");
            //                }
    }//GEN-LAST:event_jtfnombrerazonFocusLost

    private void jtfnombrerazonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfnombrerazonKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfnombrerazonKeyReleased

    private void jtfnombrerazonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfnombrerazonKeyTyped
        // TODO add your handling code here:
        //         mayus.convertirmayus(jtfnombrerazon);
    }//GEN-LAST:event_jtfnombrerazonKeyTyped

    private void jcbtipodocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbtipodocActionPerformed
        // TODO add your handling code here:
        validaguardar();
        System.out.println("cvalida");
    }//GEN-LAST:event_jcbtipodocActionPerformed

    private void jbtnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnguardarActionPerformed
        // TODO add your handling code here:
        ClienteDAO daocliente= new ClienteDAO();
             boolean valida=daocliente.duplicado(0, jtfrut.getText(),"GUARDAR");
                if(valida==true){
                    Cliente cliente = new Cliente();
                    Cliente_Tipo_Documento cdoc = listdoc.get(jcbtipodoc.getSelectedIndex()-1);
                    cliente.setNombre_razons(jtfnombrerazon.getText().toUpperCase());
                    cliente.setDireccion(jtfdireccion.getText().toUpperCase());
                    cliente.setRut(jtfrut.getText());
                    cliente.setIdtipodoc(cdoc.getId());
                    cliente.setCelular(jtfcelular.getText());
                    cliente.setEmail(jtfemail.getText().toUpperCase());
                    long id=daocliente.insertarcliente(cliente);
                    cliente.setId_cliente(id);
                    
                    
                     
//                     if(id!=0){
//                         JOptionPane.showMessageDialog(null , "Cliente registrado con exito");
//                     }
                }else {
                    JOptionPane.showMessageDialog(null, "El cliente ya se encuentra registrado","",JOptionPane.INFORMATION_MESSAGE);
                    jtfrut.selectAll();
                    jtfrut.requestFocus();
                }
    }//GEN-LAST:event_jbtnguardarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDNuevoCliente dialog = new JDNuevoCliente(new java.awt.Frame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbtnguardar;
    private javax.swing.JComboBox jcbtipodoc;
    private javax.swing.JTextField jtfcelular;
    private javax.swing.JTextField jtfdireccion;
    private javax.swing.JTextField jtfemail;
    private javax.swing.JTextField jtfnombrerazon;
    private javax.swing.JTextField jtfrut;
    // End of variables declaration//GEN-END:variables
}
