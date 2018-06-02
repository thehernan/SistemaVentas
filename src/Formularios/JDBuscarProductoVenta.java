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
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
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
    JIFCotizacion jifcotiza;
    JIFMerma jifmerma;
    Familia fam = new Familia();
    List<Producto> listprodventa;
    public JDBuscarProductoVenta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
      
        
    }
    public JDBuscarProductoVenta(java.awt.Frame parent, boolean modal,JIFVenta frmventa, List<Producto> listprodventa) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
//       listprod= daoproducto.inventario(jtabla, sucursalsingleton.getId(),sucursalsingleton.getStockmin(),"");
         
        mostrar();
        this.frmventa=frmventa;
        this.listprodventa=listprodventa;
        from = "venta";
        listfamilia = daofamilia.llenarcombo(jcbfamilia);
        jbtntodo.setEnabled(false);
       jtfproducto.requestFocus();
       
    }
    public JDBuscarProductoVenta(java.awt.Frame parent, boolean modal,JIFOrdenSalida jifsalida) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
//        listprod=daoproducto.inventario(jtabla, sucursalsingleton.getId(),sucursalsingleton.getStockmin(),"");
       
        mostrar();
        this.jifsalida=jifsalida;
        
        from ="salida";
         listfamilia = daofamilia.llenarcombo(jcbfamilia);
          jtfproducto.requestFocus();
    }
    
    public JDBuscarProductoVenta(java.awt.Frame parent, boolean modal,JIFCotizacion jifcotiza) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
//        listprod=daoproducto.inventario(jtabla, sucursalsingleton.getId(),sucursalsingleton.getStockmin(),"");
       
         mostrar();
        this.jifcotiza=jifcotiza;
        
        jbtntodo.setEnabled(false);
        from ="cotiza";
         listfamilia = daofamilia.llenarcombo(jcbfamilia);
          jtfproducto.requestFocus();
    }
     public JDBuscarProductoVenta(java.awt.Frame parent, boolean modal,JIFMerma jifmerma) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
//        listprod=daoproducto.inventario(jtabla, sucursalsingleton.getId(),sucursalsingleton.getStockmin(),"");
       
        mostrar();
        this.jifmerma=jifmerma;
        
        jbtntodo.setEnabled(false);
        from ="merma";
         listfamilia = daofamilia.llenarcombo(jcbfamilia);
         jtfproducto.requestFocus();
    }
    
    public void mostrar(){
     Runnable runnable= new Runnable() {

         @Override
         public void run() {
//             throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             jlblimagencarga.setVisible(true);
             jlblletracarga.setVisible(true);
             jtfproducto.setEnabled(false);
             jcbfamilia.setEnabled(false);
             listprod= daoproducto.inventario(jtabla, sucursalsingleton.getId(),sucursalsingleton.getStockmin(),"");
              jtfproducto.setEnabled(true);
             jcbfamilia.setEnabled(true);
             jlblimagencarga.setVisible(false);
             jlblletracarga.setVisible(false);
         }
     };
     Thread T = new Thread(runnable);
     T.start();
     
    
    }
    public void sensitiva(){
        
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                String descrip= jtfproducto.getText().toUpperCase();
       
                fam = listfamilia.get(jcbfamilia.getSelectedIndex());
                jlblimagencarga.setVisible(true);
                jlblletracarga.setVisible(true);
                if(jcbfamilia.getSelectedIndex()==0){

                 listprod=daoproducto.busquedasensitivainventario(jtabla, "TODO", descrip,
                sucursalsingleton.getId(),fam.getIdfamilia(),sucursalsingleton.getStockmin());

                }else{
                  listprod=daoproducto.busquedasensitivainventario(jtabla, "FAMILIA", descrip,
                sucursalsingleton.getId(),fam.getIdfamilia(),sucursalsingleton.getStockmin());
                }
                jlblimagencarga.setVisible(false);
                jlblletracarga.setVisible(false);
       
                
            }
        };
        Thread T = new Thread(runnable);
        T.start();
     
    
    }
    

  ///////////////////////////////////////////////////////////  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlblletracarga = new javax.swing.JLabel();
        jlblimagencarga = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtfproducto = new javax.swing.JTextField();
        jbtnaceptar = new javax.swing.JButton();
        jcbfamilia = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jbtnver = new javax.swing.JButton();
        jbtntodo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblletracarga.setBackground(new java.awt.Color(255, 255, 255));
        jlblletracarga.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        jlblletracarga.setForeground(new java.awt.Color(0, 0, 0));
        jlblletracarga.setText("Cargando Productos ...");
        jPanel1.add(jlblletracarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 400, -1, -1));

        jlblimagencarga.setBackground(new java.awt.Color(255, 255, 255));
        jlblimagencarga.setForeground(new java.awt.Color(255, 255, 255));
        jlblimagencarga.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblimagencarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ring.gif"))); // NOI18N
        jPanel1.add(jlblimagencarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, 430, 350));

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
        jtabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jtabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtabla.getTableHeader().setReorderingAllowed(false);
        jtabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtablaMouseReleased(evt);
            }
        });
        jtabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtablaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtablaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 108, 1160, 394));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel1.setText("Buscar:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jtfproducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfproductoActionPerformed(evt);
            }
        });
        jtfproducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfproductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfproductoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfproductoKeyTyped(evt);
            }
        });
        jPanel1.add(jtfproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 70, 490, -1));

        jbtnaceptar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnaceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/accept2.png"))); // NOI18N
        jbtnaceptar.setText("Aceptar");
        jbtnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnaceptarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnaceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 70, -1, -1));

        jcbfamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbfamiliaActionPerformed(evt);
            }
        });
        jPanel1.add(jcbfamilia, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 340, -1));

        jPanel7.setBackground(new java.awt.Color(238, 238, 238));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel11.setText("BUSCAR PRODUCTO");

        jbtnver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view25px.png"))); // NOI18N
        jbtnver.setMnemonic('v');
        jbtnver.setText("(Alt + v)");
        jbtnver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 830, Short.MAX_VALUE)
                .addComponent(jbtnver)
                .addGap(34, 34, 34))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jbtnver))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, -1));

        jbtntodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/selectall.png"))); // NOI18N
        jbtntodo.setToolTipText("Agregar todos los productos en tabla");
        jbtntodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtntodoActionPerformed(evt);
            }
        });
        jPanel1.add(jbtntodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel2.setText("Familia:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, -1, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfproductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfproductoKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
          if(jtabla.getSelectedRow()>=0)
             jtabla.requestFocus();
        
        }
       
      
          
       
        
    }//GEN-LAST:event_jtfproductoKeyReleased

    private void jbtnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnaceptarActionPerformed
        // TODO add your handling code here:
        int index = jtabla.getSelectedRow();
            if(index >=0){
                 prod = listprod.get(index);
                
                if(from.equals("venta")){
                boolean repetido=false;               
                for(Producto p:listprodventa ){
                    if(p.getIdproducto()==prod.getIdproducto()){
                        repetido=true;
                    }
                }
                if(repetido==false)
                {
                    JDFOpcionPrecio opprecio= new JDFOpcionPrecio(new Frame(), isVisible(),prod,frmventa,this);
                    opprecio.setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null,"El producto ya se encuentra agregado","", JOptionPane.ERROR_MESSAGE);
                
                }
//                frmventa.setbuscarproducto(prod.getCodigo());
//                this.dispose();

                } 
                if(from.equals("cotiza")){
                JDFOpcionPrecio opprecio= new JDFOpcionPrecio(new Frame(), isVisible(),prod,jifcotiza,this); 
                opprecio.setVisible(true);
                
//                this.dispose();
                
                }
                if(from.equals("merma")){
                    jifmerma.setproducto(prod);
                    this.dispose();
                }
                
                
                
            }else 
            {
                JOptionPane.showMessageDialog(null,"SELECCIONE UN PRODUCTO DE LA TABLA","SISTEMA",JOptionPane.INFORMATION_MESSAGE);
            }
        
        if(from.equals("salida")){
            prod= listprod.get(jtabla.getSelectedRow());
            JDCantidadSalida jdcantidad= new JDCantidadSalida(new Frame(), isVisible(),jifsalida,prod,this);
            
            jdcantidad.setVisible(true);
//            this.dispose();
        }
        
        
      
    }//GEN-LAST:event_jbtnaceptarActionPerformed

    private void jtfproductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfproductoKeyTyped
        // TODO add your handling code here:
//        mayus.convertirmayus(jtfproducto);
    }//GEN-LAST:event_jtfproductoKeyTyped

    private void jtablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtablaKeyReleased
        // TODO add your handling code here:
//        if(evt.getKeyCode()==10){
//            jbtnaceptar.doClick();
//        }
    }//GEN-LAST:event_jtablaKeyReleased

    private void jtfproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfproductoActionPerformed
        // TODO add your handling code here:
    
    }//GEN-LAST:event_jtfproductoActionPerformed

    private void jtablaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaMouseReleased
        // TODO add your handling code here:
    
        if(jtabla.getSelectedRow()>=0){
            prod = listprod.get(jtabla.getSelectedRow());
        }
        
    }//GEN-LAST:event_jtablaMouseReleased

    private void jcbfamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbfamiliaActionPerformed
        // TODO add your handling code here:
//        
//          String descrip= jtfproducto.getText().toUpperCase();
//       if(listfamilia.size()>0){
//           
//                fam = listfamilia.get(jcbfamilia.getSelectedIndex());
//            if(jcbfamilia.getSelectedIndex()==0){
//
//             listprod=daoproducto.busquedasensitivainventario(jtabla, "TODO", descrip,
//            sucursalsingleton.getId(),fam.getIdfamilia(),sucursalsingleton.getStockmin());
//
//            }else{
//              listprod=daoproducto.busquedasensitivainventario(jtabla, "FAMILIA", descrip,
//            sucursalsingleton.getId(),fam.getIdfamilia(),sucursalsingleton.getStockmin());
//            }
//       
//       }
        sensitiva();
       
        
    }//GEN-LAST:event_jcbfamiliaActionPerformed

    private void jbtntodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtntodoActionPerformed
        // TODO add your handling code here:
        jifsalida.setlistaproductos(listprod);
        this.dispose();
    }//GEN-LAST:event_jbtntodoActionPerformed

    private void jtfproductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfproductoKeyPressed
        // TODO add your handling code here:
           if(evt.getKeyCode()==10){
             sensitiva();
            
         }
    }//GEN-LAST:event_jtfproductoKeyPressed

    private void jtablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtablaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
        
            jbtnaceptar.doClick();
        }
    }//GEN-LAST:event_jtablaKeyPressed

    private void jbtnverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnverActionPerformed
        // TODO add your handling code here:
        int index=jtabla.getSelectedRow() ;
        if(index>=0){
            Producto produc= listprod.get(index);
            JDVistaPreviaProducto vprod= new JDVistaPreviaProducto(new JFrame(), isVisible(),produc);
            vprod.setVisible(true);
            
        
        
        
        }else {
            JOptionPane.showMessageDialog(null, "Seleccione producto","",JOptionPane.INFORMATION_MESSAGE);
        
        }
        
        
        
    }//GEN-LAST:event_jbtnverActionPerformed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==27)
            this.dispose();
    }//GEN-LAST:event_formKeyReleased

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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnaceptar;
    private javax.swing.JButton jbtntodo;
    private javax.swing.JButton jbtnver;
    private javax.swing.JComboBox jcbfamilia;
    private javax.swing.JLabel jlblimagencarga;
    private javax.swing.JLabel jlblletracarga;
    private javax.swing.JTable jtabla;
    private javax.swing.JTextField jtfproducto;
    // End of variables declaration//GEN-END:variables
}
