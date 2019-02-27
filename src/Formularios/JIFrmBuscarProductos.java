/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.Mayusculas;
import DAO.FamiliaDAO;
import DAO.ProductoDAO;
import DAO.SucursalDAO;
import Pojos.Familia;
import Pojos.Producto;
import Pojos.Sucursal;
import Pojos.SucursalSingleton;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author info2017
 */
public class JIFrmBuscarProductos extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFrmInventario
     */
    ProductoDAO daoproducto = new ProductoDAO();
    SucursalSingleton sucursalsingleton = SucursalSingleton.getinstancia();
    SucursalDAO daosucursal = new SucursalDAO();
    Mayusculas mayus = new Mayusculas();
    FamiliaDAO daofamilia = new FamiliaDAO();
    List<Familia> listfamilia;
    Familia fam = new Familia();
    ////
    List<Producto> listprod = new ArrayList<>();
    Sucursal sucursa= new Sucursal();
    List<Sucursal> listsucur;
     int posx;
    int posy;
    public JIFrmBuscarProductos() {
        initComponents();
//        jlblimagencarga.setVisible(false);
//        jlblletracarga.setVisible(false);
       
        
//        double stockmin=Double.parseDouble(jsstockmin.getValue().toString());
       
        mostrar();
       jbtncerrar.setMnemonic(KeyEvent.VK_ESCAPE);
      
    }
    
    public void mostrar () {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
            jprogres.setMinimum(0);
            jprogres.setMaximum(50);
            jprogres.setStringPainted(true);
            int i = 0;
             
            while(i<=50)
            {
                 jprogres.setValue(i);


                if(i==25)
                {
//                jsstockmin.setEnabled(false);
                jtfdescripcion.setEnabled(false);
                jcbfamilia.setEnabled(false);
                jcbsucursal.setEnabled(false);
//                jsstockmin.setValue(sucursalsingleton.getStockmin());
                
                listprod= daoproducto.inventario(jtabla,sucursalsingleton.getId(),sucursalsingleton.getStockmin(),"TODO");
                jtfdescripcion.requestFocus();
                listfamilia = daofamilia.llenarcombo(jcbfamilia);
                listsucur = daosucursal.llenarcombo(jcbsucursal);
//               S jsstockmin.setEnabled(true);
                jtfdescripcion.setEnabled(true);
                jcbfamilia.setEnabled(true);
                jcbsucursal.setEnabled(true);

                
                
            }
                  if(i==50)
               {
                jprogres.setVisible(false); 
               
               }
               i++;
               
            }
            
            }
        };
        Thread T = new Thread(runnable);
        T.start();
    
    
    }
                
    
    public synchronized void sensitiva(){
       
     
     String descrip= jtfdescripcion.getText().toUpperCase();
       
        fam = listfamilia.get(jcbfamilia.getSelectedIndex());
        sucursa = listsucur.get(jcbsucursal.getSelectedIndex());
            
        if(jcbfamilia.getSelectedIndex()==0 && jcbsucursal.getSelectedIndex()==0){
           
         listprod=daoproducto.busquedasensitivainventario(jtabla, "TODOSUCURSAL", descrip,
        sucursa.getId(),fam.getIdfamilia(),sucursalsingleton.getStockmin());
        
        }
        if(jcbfamilia.getSelectedIndex() !=0  && jcbsucursal.getSelectedIndex()==0){
        listprod=daoproducto.busquedasensitivainventario(jtabla, "FAMILIASUCURSAL", descrip,
        sucursa.getId(),fam.getIdfamilia(),sucursalsingleton.getStockmin());
        
        }
         if(jcbfamilia.getSelectedIndex() ==0  && jcbsucursal.getSelectedIndex()!=0){
              listprod=daoproducto.busquedasensitivainventario(jtabla, "TODO", descrip,
        sucursa.getId(),fam.getIdfamilia(),sucursalsingleton.getStockmin());
         }
         
          if(jcbfamilia.getSelectedIndex() !=0  && jcbsucursal.getSelectedIndex()!=0){
              listprod=daoproducto.busquedasensitivainventario(jtabla, "FAMILIA", descrip,
        sucursa.getId(),fam.getIdfamilia(),sucursalsingleton.getStockmin());
         }
    
          if(jtabla.getSelectedRow()>=0)
          {
           jtabla.requestFocus();
          
          }
         
    }
    public void buscar(){
        Runnable runnable=new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                jprogres.setMinimum(0);
                jprogres.setMaximum(50);
                jprogres.setStringPainted(true);
                int i = 0;
                jprogres.setVisible(true); 
            while(i<=50)
            {
                 jprogres.setValue(i);


                if(i==25)
                {
               
                sensitiva();
                
                }
                  if(i==50)
               {
                jprogres.setVisible(false); 
               
               }
               i++; 
            }
             
            }
        };
        Thread T = new Thread(runnable);
        T.start();
    
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
        jLabel4 = new javax.swing.JLabel();
        jtfdescripcion = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();
        jbtnimprimir = new javax.swing.JButton();
        jcbsucursal = new javax.swing.JComboBox();
        jcbfamilia = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jbtncerrar = new javax.swing.JButton();
        jprogres = new javax.swing.JProgressBar();
        jbtnajustarexistencia = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setClosable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel4.setText("Buscar:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, 20));

        jtfdescripcion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfdescripcion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfdescripcionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfdescripcionFocusLost(evt);
            }
        });
        jtfdescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfdescripcionActionPerformed(evt);
            }
        });
        jtfdescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfdescripcionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfdescripcionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfdescripcionKeyTyped(evt);
            }
        });
        jPanel1.add(jtfdescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 600, -1));

        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));

        jtabla.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "P. Publico", "P. Min. Publico", "P. Mayorista", "P. Otros", "Stock Min.", "Stock Max.", "Existencias C. ", "UnidC.", "Existencias V.", "UnidV.", "Localización", "Familia", "Sucursal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jtabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtabla.getTableHeader().setReorderingAllowed(false);
        jtabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtablaMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jtabla);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 1160, 380));

        jbtnimprimir.setBackground(new java.awt.Color(255, 255, 255));
        jbtnimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/print.png"))); // NOI18N
        jbtnimprimir.setBorderPainted(false);
        jbtnimprimir.setContentAreaFilled(false);
        jbtnimprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jbtnimprimir.setRequestFocusEnabled(false);
        jbtnimprimir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnimprimirMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jbtnimprimirMousePressed(evt);
            }
        });
        jbtnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnimprimirActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnimprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 140, -1, -1));

        jcbsucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbsucursalActionPerformed(evt);
            }
        });
        jPanel1.add(jcbsucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 120, 323, -1));

        jcbfamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbfamiliaActionPerformed(evt);
            }
        });
        jPanel1.add(jcbfamilia, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 380, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel1.setText("Familia:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, 20));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel2.setText("Sucursal:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, -1, 20));

        jPanel2.setBackground(new java.awt.Color(34, 75, 139));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/trolley_40px.png"))); // NOI18N
        jLabel3.setText(" PRODUCTOS");

        jbtncerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar.png"))); // NOI18N
        jbtncerrar.setBorderPainted(false);
        jbtncerrar.setContentAreaFilled(false);
        jbtncerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncerrarActionPerformed(evt);
            }
        });

        jprogres.setString("");

        jbtnajustarexistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add_Shopping_Cart_30px_1.png"))); // NOI18N
        jbtnajustarexistencia.setText("Ajustar Existencia");
        jbtnajustarexistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnajustarexistenciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(jbtnajustarexistencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jprogres, javax.swing.GroupLayout.PREFERRED_SIZE, 1190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 150, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtncerrar)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbtnajustarexistencia)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(10, 10, 10)
                .addComponent(jprogres, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 70));

        jLabel5.setForeground(new java.awt.Color(248, 193, 186));
        jLabel5.setText("Menos o iguales al  stock Min.");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabel6.setForeground(new java.awt.Color(183, 219, 243));
        jLabel6.setText("Mayor al stock Max.");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1185, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfdescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfdescripcionKeyTyped
        // TODO add your handling code here:
//       mayus.convertirmayus(jtfdescripcion);
    }//GEN-LAST:event_jtfdescripcionKeyTyped

    private void jtablaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaMouseReleased
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jtablaMouseReleased

    private void jtfdescripcionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfdescripcionFocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jtfdescripcionFocusGained

    private void jtfdescripcionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfdescripcionFocusLost
       
    }//GEN-LAST:event_jtfdescripcionFocusLost

    private void jtfdescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfdescripcionKeyReleased
        // TODO add your handling code here:
       
       
    }//GEN-LAST:event_jtfdescripcionKeyReleased

    private void jbtnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnimprimirActionPerformed
        // TODO add your handling code here:
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            jprogres.setMinimum(0);
            jprogres.setMaximum(50);
            jprogres.setStringPainted(true);
                int i = 0;
                jprogres.setVisible(true);
            while(i<=50)
            {
                 jprogres.setValue(i);


                if(i==25)
                {
                
                 daoproducto.imprimirinventario(sucursalsingleton.getId());
                
                }
                  if(i==50)
               {
                jprogres.setVisible(false); 
               
               }
               i++; 
            }
              
                
                
             
            }
        };
        Thread T = new Thread(runnable);
        T.start();
        
         
    }//GEN-LAST:event_jbtnimprimirActionPerformed

    private void jcbfamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbfamiliaActionPerformed
        // TODO add your handling code here:
         Runnable runnable=new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 jprogres.setMinimum(0);
            jprogres.setMaximum(50);
            jprogres.setStringPainted(true);
                int i = 0;
                jprogres.setVisible(true); 
            while(i<=50)
            {
                 jprogres.setValue(i);


                if(i==25)
                {
               
                sensitiva();
                
                }
                  if(i==50)
               {
                jprogres.setVisible(false); 
               
               }
               i++; 
            }
             
            }
        };
        Thread T = new Thread(runnable);
        T.start();
        
          
    }//GEN-LAST:event_jcbfamiliaActionPerformed

    private void jcbsucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbsucursalActionPerformed
        // TODO add your handling code here:
          Runnable runnable=new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 jprogres.setMinimum(0);
            jprogres.setMaximum(50);
            jprogres.setStringPainted(true);
                int i = 0;
                jprogres.setVisible(true);
            while(i<=50)
            {
                 jprogres.setValue(i);


                if(i==25)
                {
                
                sensitiva();
                
                }
                  if(i==50)
               {
                jprogres.setVisible(false); 
               
               }
               i++; 
            }
                
            }
        };
        Thread T = new Thread(runnable);
        T.start();
    }//GEN-LAST:event_jcbsucursalActionPerformed

    private void jbtnimprimirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnimprimirMousePressed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jbtnimprimirMousePressed

    private void jbtnimprimirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnimprimirMouseExited
        // TODO add your handling code here:
         
    }//GEN-LAST:event_jbtnimprimirMouseExited

    private void jtfdescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfdescripcionActionPerformed
        // TODO add your handling code here:
       
       
    }//GEN-LAST:event_jtfdescripcionActionPerformed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        // TODO add your handling code here:
        int xp=evt.getXOnScreen() - posx;
        int yp=evt.getYOnScreen() - posy;
        this.setLocation(xp, yp);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        // TODO add your handling code here:
          posy=evt.getY();
        posx=evt.getX();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jtfdescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfdescripcionKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
         Runnable runnable=new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            jprogres.setMinimum(0);
            jprogres.setMaximum(50);
            jprogres.setStringPainted(true);
            int i = 0;
            jprogres.setVisible(true);
            while(i<=50)
            {
                 jprogres.setValue(i);


                if(i==25)
                {
               
                sensitiva();
                
                }
              
               if(i==50)
               {
                jprogres.setVisible(false); 
               
               } 
               i++;
            }
//                
            }
        };
        Thread T = new Thread(runnable);
        T.start();
         
        }
    }//GEN-LAST:event_jtfdescripcionKeyPressed

    private void jbtncerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncerrarActionPerformed
        // TODO add your handling code here:
           this.dispose();
    }//GEN-LAST:event_jbtncerrarActionPerformed

    private void jbtnajustarexistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnajustarexistenciaActionPerformed
        // TODO add your handling code here:
        int index = jtabla.getSelectedRow();
        if(index >=0){
            JDAjusteExistencia ajuste = new JDAjusteExistencia(new JFrame(), isVisible(),listprod.get(index),this);
            ajuste.setVisible(true);
        
        }else {
            JOptionPane.showMessageDialog(null, "Seleccione Producto");
        }
    }//GEN-LAST:event_jbtnajustarexistenciaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnajustarexistencia;
    private javax.swing.JButton jbtncerrar;
    private javax.swing.JButton jbtnimprimir;
    private javax.swing.JComboBox jcbfamilia;
    private javax.swing.JComboBox jcbsucursal;
    private javax.swing.JProgressBar jprogres;
    private javax.swing.JTable jtabla;
    private javax.swing.JTextField jtfdescripcion;
    // End of variables declaration//GEN-END:variables
}
