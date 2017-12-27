/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.Mayusculas;
import DAO.DetalleOrdenSalidaDAO;
import DAO.OrdenSalidaDAO;
import DAO.ProductoDAO;
import DAO.SucursalDAO;
import Pojos.DetalleOrdeSalidaEntrada;

import Pojos.OrdenSalidaEntrada;
import Pojos.Producto;
import Pojos.Sucursal;
import Pojos.SucursalSingleton;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author info2017
 */
public class JIFOrdenSalida extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFOrdenSalidaEntrada
     */
    ProductoDAO  daoproducto= new ProductoDAO();
    Producto producto= new Producto();
    SucursalSingleton sucursalsingleton = SucursalSingleton.getinstancia();
    SucursalDAO daosucursal= new SucursalDAO();
    DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
    //      if (column == 5) return true;
    //else
    return false;
}
  }; 
    
    OrdenSalidaEntrada orden = new OrdenSalidaEntrada();
    OrdenSalidaDAO daoorden = new OrdenSalidaDAO();
    DetalleOrdenSalidaDAO daodetorden = new DetalleOrdenSalidaDAO();
//    List<DetalleOrdeSalidaEntrada> listorden= new ArrayList<>();
    Mayusculas mayus = new Mayusculas();
    List<DetalleOrdeSalidaEntrada> listdet = new ArrayList<>();
    List<Sucursal> listsucur= new ArrayList<>();
    Sucursal sucursarep;
    public JIFOrdenSalida() {
        initComponents();
        String titulos[]={"CODIGO","DESCRIPCION","CANTIDAD"};
        modelo.setColumnIdentifiers(titulos);
        jtabla.setModel(modelo);
//        jtabla.getColumnModel().getColumn(0).setMaxWidth(0);
//        jtabla.getColumnModel().getColumn(0).setMinWidth(0);
//        jtabla.getColumnModel().getColumn(0).setPreferredWidth(0);
//        
//         jtabla.getColumnModel().getColumn(1).setMaxWidth(0);
//        jtabla.getColumnModel().getColumn(1).setMinWidth(0);
//        jtabla.getColumnModel().getColumn(1).setPreferredWidth(0);
       listsucur= daosucursal.llenarcombo(jcbsucursal);
        jlblsucursal.setText(sucursalsingleton.getNombre());
//        jbtnagregar.setEnabled(false);
        jbtnaceptar.setEnabled(false);
        jdfechapedido.setDate(new Date());
        jdfechaentrega.setDate(new Date());
      
    }
    
    
    
//    public void validaagregar(){
//        String autori= jtfautorizado.getText().replace("\\s", "");
//        String reci = jtfrecibido.getText().replace("\\s", "");
//        try {
//            if (modelo.getRowCount()>0
//            && jcbsucursal.getSelectedIndex()>0 && jdfechaentrega.getDate()!=null && jdfechapedido.getDate()!=null
//            && autori.length() >0 && reci.length() > 0){
////        jbtnagregar.setEnabled(true);
//    
//    }else {
//        jbtnagregar.setEnabled(false);
//    }
//            
//        } catch (NullPointerException e) {
//         jbtnagregar.setEnabled(false);
//        }
//    
//    
//    }
    
    
    
//    public void enableencabezado(boolean val){
//        jdfechaentrega.setEnabled(val);
//        jdfechapedido.setEnabled(val);
//        jcbsucursal.setEnabled(val);
//        jtfautorizado.setEnabled(val);
//        jtfrecibido.setEnabled(val);
//    
//    }
    public void nuevoprod(){
//        jtfcodigo.setText("");
//        jtfstock.setValue(0);
//        jtfcantidad.setValue(0);
//        jlblproducto.setText(". . .");
//        jbtnagregar.setEnabled(false);
//        jlblimagen.setIcon(null);
        producto = new Producto();
        ///// enable orden /////
//        enableencabezado(false);
    
    }
    public void nuevoorden(){
//        jtfcodigo.setText("");
//        jtfstock.setValue(0);
//        jtfcantidad.setValue(0);
////        jlblproducto.setText(". . .");
//        jbtnagregar.setEnabled(false);
//        jlblimagen.setIcon(null);
        producto = new Producto();
        orden = new OrdenSalidaEntrada();
        jtfautorizado.setText("");
        jtfrecibido.setText("");
//         enableencabezado(true);
         ////////// NUEVO MODELO //////////////////////////
        for (int i = 0; i < jtabla.getRowCount(); i++) {
        modelo.removeRow(i);
        i-=1;
        }
        listdet = new ArrayList<>();
       
//        jtabla.getColumnModel().getColumn(0).setMaxWidth(0);
//        jtabla.getColumnModel().getColumn(0).setMinWidth(0);
//        jtabla.getColumnModel().getColumn(0).setPreferredWidth(0);
//        
//        jtabla.getColumnModel().getColumn(1).setMaxWidth(0);
//        jtabla.getColumnModel().getColumn(1).setMinWidth(0);
//        jtabla.getColumnModel().getColumn(1).setPreferredWidth(0);
        /////////////////////////////////////////////////
    
    
    
    }
    public void setagregar(Producto prod ,Double cant){
        System.out.println("idordem"+orden.getIdordensalidaentrada());
//        if(orden.getIdordensalidaentrada() ==0){
//          /// INSERT ORDEN
//            
//            sucursarep=(listsucur.get(jcbsucursal.getSelectedIndex()));
//            orden.setIdsucurrecep(sucursarep.getId());
//            orden.setFecha_entrega(new java.sql.Timestamp(jdfechaentrega.getDate().getTime()));
//            orden.setFecha_pedido(new java.sql.Timestamp(jdfechapedido.getDate().getTime()));
//            orden.setSucurenvia(jlblsucursal.getText());
//            orden.setSucursolicita(jcbsucursal.getSelectedItem().toString());
//            orden.setAutorizado(jtfautorizado.getText());
//            orden.setRecibido(jtfrecibido.getText());
//            orden.setTipoop("SALIDA");
//            orden.setIdordensalidaentrada(daoorden.insertar(orden));
//           ////// INSERT DETALLE
//            System.out.print("cant1"+cant);
//            detorden.setCantidad(cant);
//            detorden.setIdproducto(prod.getIdproducto());
//            detorden.setIdordensalidaentrada(orden.getIdordensalidaentrada());
//            
//            detorden.setId(daodetorden.insertar(detorden));
//            
//        }else {
           ////// INSERT DETALLE
            DetalleOrdeSalidaEntrada detorden = new DetalleOrdeSalidaEntrada();
              System.out.print("cant2"+cant);
            detorden.setCantidad(cant);
            detorden.setIdproducto(prod.getIdproducto());
//            detorden.setIdordensalidaentrada(orden.getIdordensalidaentrada());
//            detorden.setId(daodetorden.insertar(detorden));
//        }
        
        
             Object[] miarray = new Object[3];
                  
//                    miarray[0]=prod.getIdproducto();
                    miarray[0]=prod.getCodigo();
                    miarray[1]=prod.getDescripcion();
                    miarray[2]=cant;
                   
                   
               
                    modelo.addRow(miarray);
            
                   nuevoprod();
                   listdet.add(detorden);
                   validaaceptar();
    }
    public void validaaceptar(){
        
        String autoriza = jtfautorizado.getText().replaceAll("\\s", "");
        String recibido = jtfrecibido.getText().replaceAll("\\s", "");
        
        if(listdet.size() > 0 && jcbsucursal.getSelectedIndex()!=0 && autoriza.length()>0 && recibido.length()>0 )
            jbtnaceptar.setEnabled(true);
        else 
            jbtnaceptar.setEnabled(false);
    
    }
    public void setlistaproductos(List<Producto> listprod){
        double cant=0.0;
        int i=1;   
        for(Producto prod: listprod){   
//         if(orden.getIdordensalidaentrada() ==0){
          /// INSERT ORDEN
            
            
           ////// INSERT DETALLE
            System.out.print("cant1"+cant);
            DetalleOrdeSalidaEntrada detorden = new DetalleOrdeSalidaEntrada();
            detorden.setCantidad(cant);
            detorden.setIdproducto(prod.getIdproducto());
            System.out.println("idprod"+ detorden.getIdproducto());
//            detorden.setIdordensalidaentrada(orden.getIdordensalidaentrada());
            
//            detorden.setId(daodetorden.insertar(detorden));
//            
////        }else {
//           ////// INSERT DETALLE
//              System.out.print("cant2"+cant);
//            detorden.setCantidad(cant);
//            detorden.setIdproducto(prod.getIdproducto());
////            detorden.setIdordensalidaentrada(orden.getIdordensalidaentrada());
//            detorden.setId(daodetorden.insertar(detorden));
//        }
        
        
             Object[] miarray = new Object[3];
                  
//                    miarray[0]=prod.getIdproducto();
                    miarray[0]=prod.getCodigo();
                    miarray[1]=prod.getDescripcion();
                    miarray[2]=cant;
               
                    modelo.addRow(miarray);
            
                   nuevoprod();
                   listdet.add(detorden);
                   validaaceptar();
                   
                
//         jlblmensaje.setText("Productos en tabla: "+i);
//         i++;
        }
    }
    public void enableboton(boolean bot){
    jbtnsalir.setEnabled(bot);
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
        jLabel2 = new javax.swing.JLabel();
        jlblsucursal = new javax.swing.JLabel();
        jcbsucursal = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();
        jbtnsalir = new javax.swing.JButton();
        jbtnaceptar = new javax.swing.JButton();
        jdfechaentrega = new org.jdesktop.swingx.JXDatePicker();
        jdfechapedido = new org.jdesktop.swingx.JXDatePicker();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtfautorizado = new javax.swing.JTextField();
        jtfrecibido = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jbtnbusquedaavanzada = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtamensaje = new javax.swing.JTextArea();

        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(238, 238, 238));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel1.setText("Sucursal que envia:");

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("Sucursal que recepciona:");

        jlblsucursal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblsucursal.setText("jLabel3");

        jcbsucursal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbsucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbsucursalActionPerformed(evt);
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

        jbtnsalir.setBackground(new java.awt.Color(255, 255, 255));
        jbtnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        jbtnsalir.setText("Cancelar / Salir");
        jbtnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnsalirActionPerformed(evt);
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

        jdfechaentrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdfechaentregaActionPerformed(evt);
            }
        });

        jdfechapedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdfechapedidoActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel6.setText("Fecha de Entrega:");

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel7.setText("Fecha del Pedido:");

        jtfautorizado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfautorizadoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfautorizadoKeyTyped(evt);
            }
        });

        jtfrecibido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtfrecibidoMousePressed(evt);
            }
        });
        jtfrecibido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfrecibidoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfrecibidoKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 51));
        jLabel8.setText("Autorizado por:");

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText("Recibido por:");

        jbtnbusquedaavanzada.setText("Buscar");
        jbtnbusquedaavanzada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbusquedaavanzadaActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("ORDEN DE SALIDA");

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

        jtamensaje.setEditable(false);
        jtamensaje.setColumns(20);
        jtamensaje.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jtamensaje.setRows(5);
        jtamensaje.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setViewportView(jtamensaje);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jbtnaceptar)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnsalir))
                            .addComponent(jbtnbusquedaavanzada, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(jtfrecibido, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jdfechapedido, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jdfechaentrega, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jlblsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcbsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfautorizado, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 431, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jdfechapedido, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdfechaentrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfautorizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfrecibido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnbusquedaavanzada))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnaceptar)
                    .addComponent(jbtnsalir))
                .addGap(3, 3, 3))
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

    private void jdfechapedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdfechapedidoActionPerformed
        // TODO add your handling code here:
//        validaagregar();
    }//GEN-LAST:event_jdfechapedidoActionPerformed

    private void jdfechaentregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdfechaentregaActionPerformed
        // TODO add your handling code here:
//        validaagregar();
    }//GEN-LAST:event_jdfechaentregaActionPerformed

    private void jtfautorizadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfautorizadoKeyReleased
        // TODO add your handling code here:
//        validaagregar();
        validaaceptar();
    }//GEN-LAST:event_jtfautorizadoKeyReleased

    private void jtfrecibidoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfrecibidoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfrecibidoMousePressed

    private void jtfrecibidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfrecibidoKeyReleased
        // TODO add your handling code here:}
//        validaagregar();
        validaaceptar();
    }//GEN-LAST:event_jtfrecibidoKeyReleased

    private void jtfautorizadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfautorizadoKeyTyped
        // TODO add your handling code here:
        mayus.convertirmayus(jtfautorizado);
//        validaagregar();
    }//GEN-LAST:event_jtfautorizadoKeyTyped

    private void jtfrecibidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfrecibidoKeyTyped
        // TODO add your handling code here:
        mayus.convertirmayus(jtfrecibido);
//        validaagregar();
    }//GEN-LAST:event_jtfrecibidoKeyTyped

    private void jcbsucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbsucursalActionPerformed
        // TODO add your handling code here:
        validaaceptar();
    }//GEN-LAST:event_jcbsucursalActionPerformed

    private void jtablaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaMouseReleased
        // TODO add your handling code here:
         int index= jtabla.getSelectedRow();
        if(index >=0){
            if(evt.getClickCount()==2){

                if(index>=0){
                    if (JOptionPane.showConfirmDialog(null,"Seguro de retirar producto de la orden","",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                        DetalleOrdeSalidaEntrada detorden= new DetalleOrdeSalidaEntrada();
                          detorden= listdet.get(jtabla.getSelectedRow());
//                        detorden.setId(Long.parseLong(modelo.getValueAt(index, 0).toString()));
//                        detorden.setIdproducto(Long.parseLong(modelo.getValueAt(index, 1).toString()));
//                        detorden.setCantidad(Double.parseDouble(modelo.getValueAt(index, 4).toString()));
                        daodetorden.eliminar(detorden);
                        modelo.removeRow(index);
                        validaaceptar();
                        
                    }

                }

            }

        }
    }//GEN-LAST:event_jtablaMouseReleased

    private void jbtnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnsalirActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(null, "SEGURO DE CERRAR LA VENTANA ORDEN DE SALIDA, SE PERDERAN LOS DATOS INGRESADOS","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
           
//            if(modelo.getRowCount()>0){
             
//              DetalleOrdeSalidaEntrada detor= new DetalleOrdeSalidaEntrada();
//              int cont=0;
            jbtnbusquedaavanzada.setEnabled(false);
            jbtnsalir.setEnabled(false);
            jbtnaceptar.setEnabled(false);
            jtabla.setEnabled(false);
             System.out.println("modelorow"+modelo.getRowCount()); 
              System.out.println("listdet"+listdet.size()); 
                
             daoproducto.devolverstockrequerido(listdet,jtamensaje,this);
                   
           
//                if(orden.getIdordensalidaentrada()!=0){
//                    daoorden.eliminar(orden.getIdordensalidaentrada());
//                
//                }
             
      
        
        }
    }//GEN-LAST:event_jbtnsalirActionPerformed

    private void jbtnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnaceptarActionPerformed
        // TODO add your handling code here:
        String autoriza = jtfautorizado.getText().replaceAll("\\s", "");
        String recibido = jtfrecibido.getText().replaceAll("\\s", "");
        if(jcbsucursal.getSelectedIndex()!=0 && autoriza.length()>0 && recibido.length()>0){
            
            sucursarep=(listsucur.get(jcbsucursal.getSelectedIndex()));
            orden.setIdsucurrecep(sucursarep.getId());
            orden.setFecha_entrega(new java.sql.Timestamp(jdfechaentrega.getDate().getTime()));
            orden.setFecha_pedido(new java.sql.Timestamp(jdfechapedido.getDate().getTime()));
            orden.setSucurenvia(jlblsucursal.getText());
            orden.setSucursolicita(jcbsucursal.getSelectedItem().toString());
            orden.setAutorizado(jtfautorizado.getText());
            orden.setRecibido(jtfrecibido.getText());
            orden.setTipoop("SALIDA");
            orden.setIdordensalidaentrada(daoorden.insertar(orden));
            

            
            
            daodetorden.insertar(listdet,orden.getIdordensalidaentrada(),jtamensaje,jbtnsalir);
        
        
        
        
        nuevoorden(); 
        jbtnaceptar.setEnabled(false);
            
            
        }else {
        JOptionPane.showMessageDialog(null, "Ingrese datos obligatorios");
        jcbsucursal.requestFocus();
        }
            
        
    }//GEN-LAST:event_jbtnaceptarActionPerformed

    private void jbtnbusquedaavanzadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnbusquedaavanzadaActionPerformed
        // TODO add your handling code here:
        
//        String autoriza = jtfautorizado.getText().replaceAll("\\s", "");
//        String recibido = jtfrecibido.getText().replaceAll("\\s", "");
//        if(jcbsucursal.getSelectedIndex()!=0 && autoriza.length()>0 && recibido.length()>0){
        JDBuscarProductoVenta bprod = new JDBuscarProductoVenta(new Frame(), isVisible(),this);
        bprod.setVisible(true);
        
//        }else {
//        JOptionPane.showMessageDialog(null, "Ingrese datos obligatorios");
//        jcbsucursal.requestFocus();
//        }
        
    }//GEN-LAST:event_jbtnbusquedaavanzadaActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnaceptar;
    private javax.swing.JButton jbtnbusquedaavanzada;
    private javax.swing.JButton jbtnsalir;
    private javax.swing.JComboBox jcbsucursal;
    private org.jdesktop.swingx.JXDatePicker jdfechaentrega;
    private org.jdesktop.swingx.JXDatePicker jdfechapedido;
    private javax.swing.JLabel jlblsucursal;
    private javax.swing.JTable jtabla;
    private javax.swing.JTextArea jtamensaje;
    private javax.swing.JTextField jtfautorizado;
    private javax.swing.JTextField jtfrecibido;
    // End of variables declaration//GEN-END:variables
}
