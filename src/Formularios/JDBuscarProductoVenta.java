/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.Mayusculas;
import DAO.FamiliaDAO;
import DAO.ProductoDAO;
import Pojos.Familia;
import Pojos.Producto;
import Pojos.SucursalSingleton;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author info2017
 */
public class JDBuscarProductoVenta extends javax.swing.JDialog { 

    /**
     * Creates new form JDBuscarProductoVenta
     */
    ProductoDAO daoproducto= new ProductoDAO();
    SucursalSingleton sucursalsingleton = SucursalSingleton.getinstancia();
    JIFVenta frmventa;
    Mayusculas mayus= new Mayusculas();
    JIFOrdenSalida jifsalida;
    String from;
    List<Producto> listprod;
    Producto prod = new Producto();
    FamiliaDAO daofamilia = new FamiliaDAO();
    List<Familia> listfamilia= new ArrayList<>();
    Familia fam = new Familia();
    public JDBuscarProductoVenta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jtfproducto.requestFocus();
        
    }
    public JDBuscarProductoVenta(java.awt.Frame parent, boolean modal,JIFVenta frmventa) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
       listprod= daoproducto.inventario(jtabla, sucursalsingleton.getId(),sucursalsingleton.getStockmin(),"");
        this.frmventa=frmventa;
        jtfproducto.requestFocus();
        from = "venta";
        listfamilia = daofamilia.llenarcombo(jcbfamilia);
    }
    public JDBuscarProductoVenta(java.awt.Frame parent, boolean modal,JIFOrdenSalida jifsalida) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        listprod=daoproducto.inventario(jtabla, sucursalsingleton.getId(),sucursalsingleton.getStockmin(),"");
        this.jifsalida=jifsalida;
        jtfproducto.requestFocus();
        from ="salida";
         listfamilia = daofamilia.llenarcombo(jcbfamilia);
    }
    
    public void mostrar(){
     listprod= daoproducto.inventario(jtabla, sucursalsingleton.getId(),sucursalsingleton.getStockmin(),"");
    
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtfproducto = new javax.swing.JTextField();
        jbtnaceptar = new javax.swing.JButton();
        jcbfamilia = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(238, 238, 238));

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
        jtabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtablaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtabla);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Buscar:");

        jtfproducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfproductoActionPerformed(evt);
            }
        });
        jtfproducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfproductoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfproductoKeyTyped(evt);
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

        jcbfamilia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbfamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbfamiliaActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("BUSCAR PRODUCTO");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtfproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jcbfamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jbtnaceptar)))
                .addGap(14, 14, 14))
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnaceptar)
                    .addComponent(jcbfamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addContainerGap())
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

    private void jtfproductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfproductoKeyReleased
        // TODO add your handling code here:
          String descrip= jtfproducto.getText().toUpperCase();
       
            fam = listfamilia.get(jcbfamilia.getSelectedIndex());
        if(jcbfamilia.getSelectedIndex()==0){
           
         listprod=daoproducto.busquedasensitivainventario(jtabla, "TODO", descrip,
        sucursalsingleton.getId(),fam.getIdfamilia());
        
        }else{
          listprod=daoproducto.busquedasensitivainventario(jtabla, "FAMILIA", descrip,
        sucursalsingleton.getId(),fam.getIdfamilia());
        }
       
          
       
        
    }//GEN-LAST:event_jtfproductoKeyReleased

    private void jbtnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnaceptarActionPerformed
        // TODO add your handling code here:
        if(from.equals("salida")){
            prod= listprod.get(jtabla.getSelectedRow());
            JDCantidadSalida jdcantidad= new JDCantidadSalida(new Frame(), isVisible(),jifsalida,prod,this);
            
            jdcantidad.setVisible(true);
//            this.dispose();
        }else {
              int index = jtabla.getSelectedRow();
            if(index >=0){
                String codigo = jtabla.getValueAt(index, 0).toString();
                frmventa.setbuscarproducto(codigo);
                this.dispose();

            }else 
            {
                JOptionPane.showMessageDialog(null,"SELECCIONE UN PRODUCTO DE LA TABLA","SISTEMA",JOptionPane.INFORMATION_MESSAGE);
            }
        
        }
        
      
    }//GEN-LAST:event_jbtnaceptarActionPerformed

    private void jtfproductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfproductoKeyTyped
        // TODO add your handling code here:
        mayus.convertirmayus(jtfproducto);
    }//GEN-LAST:event_jtfproductoKeyTyped

    private void jtablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtablaKeyReleased
        // TODO add your handling code here:
//        if(evt.getKeyCode()==10){
//            jbtnaceptar.doClick();
//        }
    }//GEN-LAST:event_jtablaKeyReleased

    private void jtfproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfproductoActionPerformed
        // TODO add your handling code here:
        jbtnaceptar.doClick();
    }//GEN-LAST:event_jtfproductoActionPerformed

    private void jtablaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaMouseReleased
        // TODO add your handling code here:
    
        if(jtabla.getSelectedRow()>=0){
            prod = listprod.get(jtabla.getSelectedRow());
        }
        
    }//GEN-LAST:event_jtablaMouseReleased

    private void jcbfamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbfamiliaActionPerformed
        // TODO add your handling code here:
        
          String descrip= jtfproducto.getText().toUpperCase();
       if(listfamilia.size()>0){
           
                fam = listfamilia.get(jcbfamilia.getSelectedIndex());
            if(jcbfamilia.getSelectedIndex()==0){

             listprod=daoproducto.busquedasensitivainventario(jtabla, "TODO", descrip,
            sucursalsingleton.getId(),fam.getIdfamilia());

            }else{
              listprod=daoproducto.busquedasensitivainventario(jtabla, "FAMILIA", descrip,
            sucursalsingleton.getId(),fam.getIdfamilia());
            }
       
       }
       
        
    }//GEN-LAST:event_jcbfamiliaActionPerformed

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
            java.util.logging.Logger.getLogger(JDBuscarProductoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDBuscarProductoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDBuscarProductoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDBuscarProductoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDBuscarProductoVenta dialog = new JDBuscarProductoVenta(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox jcbfamilia;
    private javax.swing.JTable jtabla;
    private javax.swing.JTextField jtfproducto;
    // End of variables declaration//GEN-END:variables
}
