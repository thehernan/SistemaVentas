/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.ProductoDAO;
import Pojos.Producto;
import Pojos.SucursalSingleton;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author HERNAN
 */
public class JDVistaPreviaProducto extends java.awt.Dialog {

    /**
     * Creates new form JDVistaPreviaProducto
     */
    Producto prod;
    ProductoDAO daoproducto= new ProductoDAO();
    SucursalSingleton sucursalsingleton=SucursalSingleton.getinstancia();
    public JDVistaPreviaProducto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
      public JDVistaPreviaProducto(java.awt.Frame parent, boolean modal,Producto prod) {
        super(parent, modal);
        initComponents();
        this.prod=prod;
        jtadescripprod.setLineWrap(true);
        this.setLocationRelativeTo(null);
        verproducto();
    }
      
     public void verproducto(){
        Producto producto=daoproducto.buscarproducto("CODIGO", 0,sucursalsingleton.getId(),prod.getCodigo());
        jlblcodigo.setText(producto.getCodigo());
        jlblproducto.setText(producto.getDescripcion());
        jtadescripprod.setText(producto.getObservacion());
    
         /// mostrar imagen ///
        ImageIcon imageIcon = new ImageIcon(producto.getFoto());
        //ImageIcon imageUser = imageIcon;
        Image img = imageIcon.getImage();
        Image newimg = img.getScaledInstance(jlblimagen.getWidth(), jlblimagen.getHeight(), java.awt.Image.SCALE_AREA_AVERAGING);
        imageIcon = new ImageIcon(newimg);
        jlblimagen.setIcon(imageIcon);   
        //////////////////////////////////////////////
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlblproducto = new javax.swing.JLabel();
        jlblcodigo = new javax.swing.JLabel();
        jlblimagen = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtadescripprod = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jlblproducto.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jlblproducto.setText("* * *");
        jlblproducto.setToolTipText("");

        jlblcodigo.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblcodigo.setText("* * *");

        jlblimagen.setBackground(new java.awt.Color(0, 0, 0));
        jlblimagen.setForeground(new java.awt.Color(0, 0, 0));

        jtadescripprod.setColumns(20);
        jtadescripprod.setRows(5);
        jtadescripprod.setEnabled(false);
        jScrollPane1.setViewportView(jtadescripprod);

        jLabel1.setText("Observación:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jlblproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblproducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jlblcodigo)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1)
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==27)
            this.dispose();
    }//GEN-LAST:event_formKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDVistaPreviaProducto dialog = new JDVistaPreviaProducto(new java.awt.Frame(), true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlblcodigo;
    private javax.swing.JLabel jlblimagen;
    private javax.swing.JLabel jlblproducto;
    private javax.swing.JTextArea jtadescripprod;
    // End of variables declaration//GEN-END:variables
}
