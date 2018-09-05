/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.FormatoNumerico;
import DAO.ClienteDAO;
import DAO.CotizacionDAO;
import DAO.DetalleCotizacionDAO;
import Pojos.Cliente;
import Pojos.Cotizacion;
import Pojos.DetalleCotizacion;
import Pojos.Producto;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author HERNAN
 */
public class JIFCotizacion extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFCotizacion
     */
    DefaultTableModel modelo;
     Object datos[] = new Object[5];
     List<DetalleCotizacion> listdetc= new ArrayList<>();
     Cliente cliente= new Cliente();
     ClienteDAO clientedao= new ClienteDAO();
     CotizacionDAO cotizaciondao= new CotizacionDAO();
     DetalleCotizacionDAO detcotizadao= new DetalleCotizacionDAO();
     int posx;
     int posy;
     boolean validadoc=true;
     FormatoNumerico fn = new FormatoNumerico();
    public JIFCotizacion() {
        initComponents();
        jlblmensajecarga.setVisible(false);
        modeloT();
        jlblcarga.setVisible(false);  
        anadeListenerAlModelo(jtabla);
    }
    
    public void modeloT(){
        
       modelo=(DefaultTableModel) jtabla.getModel();
        TableColumnModel columnModel = jtabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(50);      
        columnModel.getColumn(2).setPreferredWidth(450);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(50);
       
      
    
    }
    
    
    /**
     * Se añade el listener al modelo y se llama a actualizaSumas(), que es el método 
     * encargado de actualizar las sumas de las celdas no editables.
     */
    private void anadeListenerAlModelo(JTable tabla) {
        tabla.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent evento) {
                actualizaimporte(evento);
            }
        });
    }
       protected void actualizaimporte(TableModelEvent evento)
    {
          
              if(evento.getType() == TableModelEvent.UPDATE)
        {

                // Se obtiene el modelo de la tabla y la fila/columna que han cambiado.
            TableModel model = ((TableModel) (evento.getSource()));
            int fila = evento.getFirstRow();
            int columna = evento.getColumn();

            // Los cambios en la ultima fila y columna se ignoran.
            // Este return es necesario porque cuando nuestro codigo modifique
            // los valores de las sumas en esta fila y columna, saltara nuevamente
            // el evento, metiendonos en un bucle recursivo de llamadas a este
            // metodo.
            if (columna == 0 || columna == 2 || columna==4) {
                return;
            }
            try {
                double totalf=0.0;
                DetalleCotizacion detalle= listdetc.get(fila);
                double cantI=detalle.getCantidad();
                double precioI=detalle.getPrecioprod();
                
                double cantF = Double.parseDouble(modelo.getValueAt(fila, 1).toString());
                double precioF =Double.parseDouble(modelo.getValueAt(fila, 3).toString());
                System.err.println("cantf"+cantF);
                System.err.println("preciof"+precioF);
                double importe=0.0;
                if(cantF>0)
                {
                    detalle.setCantidad(cantF);
                    importe= cantF*precioF;
                    modelo.setValueAt(importe, fila, 4);
                  
                
                }else {
                    detalle.setCantidad(cantI);
                    JOptionPane.showMessageDialog(null,"Ingrese una cantidad valida","",JOptionPane.ERROR_MESSAGE);
                    validadoc=false;
                }
                if(precioF>0)
                {
                    detalle.setPrecioprod(precioF);
                    importe= cantF*precioF;
                    modelo.setValueAt(importe, fila, 4);
                     
                }else {
                    detalle.setPrecioprod(precioI);
                    JOptionPane.showMessageDialog(null,"Ingrese un precio valido","",JOptionPane.ERROR_MESSAGE);
                    validadoc=false;
                }
                
                for (DetalleCotizacion deta: listdetc){
                totalf+=(deta.getPrecioprod()*deta.getCantidad());
                }
                

                jlbltotal.setText("Total: "+fn.FormatoN(totalf));
                
            } catch (Exception e) {
                jlbltotal.setText("Error");
                validadoc=false;
            }
            
            
        }
    }
    
    
     public void setagregar(Producto prod,double cant,double precio){
       
        DetalleCotizacion det = new DetalleCotizacion();
        double totalf=0.0 ;
        datos[0]=modelo.getRowCount()+1;
        datos[1]=cant;
        datos[2]=prod.getDescripcion()+" - "+prod.getCodigo();
        datos[3]=(fn.FormatoN(precio));
        
        Double total=(precio*cant);
        datos[4]=(fn.FormatoN(total));
        
        modelo.addRow(datos);
        det.setCantidad(cant);
        det.setPrecioprod(precio);
        det.setIdprod(prod.getIdproducto());
      
        
        listdetc.add(det);
        for (DetalleCotizacion deta: listdetc){
            totalf+=(deta.getPrecioprod()*deta.getCantidad());
        }
        jlbltotal.setText("Total: "+fn.FormatoN(totalf));
        
        validagenerar();
    
    }
     
     public void validagenerar(){
         
        if(cliente.getId_cliente()!=0 && listdetc.size() >0 && jtfcondicionp.getText().replaceAll(" ", "").length() >0 
                && jtfemitidapor.getText().replaceAll(" ", "").length()>0 && validadoc==true){
        jbtnaceptar.setEnabled(true);
        }else {
        jbtnaceptar.setEnabled(false);
        }
    
    }
     public void nuevacotizacion(){
     cliente = new Cliente();
      for (int i = 0; i < jtabla.getRowCount(); i++) {
        modelo.removeRow(i);
        i-=1;
        }
      jlbldireccion.setText("* * *");
      jlblnombre.setText("* * *");
      jlbltelefono.setText("* * *");
      jtfcondicionp.setText("");
      jtfemitidapor.setText("");
      jtfrucdni.setText("");
      
      listdetc = new ArrayList<>();
      jbtnaceptar.setEnabled(false);
      jlbltotal.setText("Total= 0.00");
         
     
     
     }
     public synchronized void mostrarcliente(){
      cliente = clientedao.buscarcliente("CODIGO", 0, jtfrucdni.getText(), jlblmensaje);
        jlblnombre.setText(cliente.getNombre_razons());
        jlbldireccion.setText(cliente.getDireccion());
        jlbltelefono.setText(cliente.getCelular());
        validagenerar();
     
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtfrucdni = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jlblnombre = new javax.swing.JLabel();
        jlbltelefono = new javax.swing.JLabel();
        jlbldireccion = new javax.swing.JLabel();
        jlblmensaje = new javax.swing.JLabel();
        jlblcarga = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jbtnagregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jbtnaceptar = new javax.swing.JButton();
        jtfcondicionp = new javax.swing.JTextField();
        jtfemitidapor = new javax.swing.JTextField();
        jlblmensajecarga = new javax.swing.JLabel();
        jlbltotal = new javax.swing.JLabel();
        jbtnretirar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Empresa / Cliente"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Doc.:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 31, -1, -1));

        jLabel4.setText("Dirección:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jtfrucdni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfrucdniActionPerformed(evt);
            }
        });
        jtfrucdni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfrucdniKeyReleased(evt);
            }
        });
        jPanel2.add(jtfrucdni, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 29, 199, -1));

        jLabel2.setText("Señor(es):");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 31, -1, -1));

        jLabel5.setText("Teléfono:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jlblnombre.setText("* * *");
        jPanel2.add(jlblnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(414, 31, -1, -1));

        jlbltelefono.setText("* * *");
        jPanel2.add(jlbltelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));

        jlbldireccion.setText("* * *");
        jPanel2.add(jlbldireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        jlblmensaje.setForeground(new java.awt.Color(255, 51, 51));
        jPanel2.add(jlblmensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 54, 272, 15));

        jlblcarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ring.gif"))); // NOI18N
        jPanel2.add(jlblcarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, -60, -1, 180));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 66, 944, 130));

        jPanel1.setBackground(new java.awt.Color(238, 238, 238));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel3.setText("NUEVA COTIZACION");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrarblanco.png"))); // NOI18N
        jLabel8.setToolTipText("Cerrar");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel8MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 689, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jbtnagregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/addproduct20x20.png"))); // NOI18N
        jbtnagregar.setText("Agregar");
        jbtnagregar.setBorder(null);
        jbtnagregar.setBorderPainted(false);
        jbtnagregar.setContentAreaFilled(false);
        jbtnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnagregarActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnagregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 90, 30));

        jtabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Cantidad", "Descripcion", "Precio C/U", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtabla);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 239, 944, 300));

        jLabel6.setText("Condición de pago:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 547, -1, -1));

        jLabel7.setText("Emitida por:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 572, -1, -1));

        jbtnaceptar.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jbtnaceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/print.png"))); // NOI18N
        jbtnaceptar.setText("Aceptar / Imprimir");
        jbtnaceptar.setBorderPainted(false);
        jbtnaceptar.setContentAreaFilled(false);
        jbtnaceptar.setEnabled(false);
        jbtnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnaceptarActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnaceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 555, -1, 40));

        jtfcondicionp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfcondicionpKeyReleased(evt);
            }
        });
        getContentPane().add(jtfcondicionp, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 545, 298, -1));

        jtfemitidapor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfemitidaporKeyReleased(evt);
            }
        });
        getContentPane().add(jtfemitidapor, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 570, 298, -1));

        jlblmensajecarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/loading4.gif"))); // NOI18N
        jlblmensajecarga.setText("Imprimiento Cotizacion . . .");
        getContentPane().add(jlblmensajecarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 550, -1, 50));

        jlbltotal.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jlbltotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlbltotal.setText("Total: 0.00");
        getContentPane().add(jlbltotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 550, 180, -1));

        jbtnretirar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/minus20x20.png"))); // NOI18N
        jbtnretirar.setText("Retirar");
        jbtnretirar.setBorderPainted(false);
        jbtnretirar.setContentAreaFilled(false);
        jbtnretirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnretirarActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnretirar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnagregarActionPerformed
        // TODO add your handling code here:
        JDBuscarProductoVenta bproducto = new JDBuscarProductoVenta(new Frame(), isVisible(),this);
        bproducto.setVisible(true);
    }//GEN-LAST:event_jbtnagregarActionPerformed

    private void jtfrucdniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfrucdniKeyReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jtfrucdniKeyReleased

    private void jbtnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnaceptarActionPerformed
        // TODO add your handling code here:
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                jlblmensajecarga.setVisible(true);
                Cotizacion cotizacion = new Cotizacion();
                cotizacion.setIdcliente(cliente.getId_cliente());
                cotizacion.setEmitida(jtfemitidapor.getText().toUpperCase());
                cotizacion.setCondicionpago(jtfcondicionp.getText().toUpperCase());

                cotizacion.setId(cotizaciondao.insert(cotizacion));

                detcotizadao.insert(listdetc,cotizacion.getId());
                cotizaciondao.imprimir(cotizacion.getId());
                jlblmensajecarga.setVisible(false);
                nuevacotizacion();
            }
        };
        Thread T = new Thread(runnable);
        T.start();
               
        
    }//GEN-LAST:event_jbtnaceptarActionPerformed

    private void jtfcondicionpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcondicionpKeyReleased
        // TODO add your handling code here:
        validagenerar();
    }//GEN-LAST:event_jtfcondicionpKeyReleased

    private void jtfemitidaporKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfemitidaporKeyReleased
        // TODO add your handling code here:
        validagenerar();
    }//GEN-LAST:event_jtfemitidaporKeyReleased

    private void jtfrucdniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfrucdniActionPerformed
        // TODO add your handling code here:
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                jlblcarga.setVisible(true);
                mostrarcliente();
                jlblcarga.setVisible(false);
            }
        };
        Thread T = new Thread(runnable);
        T.start();
    }//GEN-LAST:event_jtfrucdniActionPerformed

    private void jLabel8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseReleased
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel8MouseReleased

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        posy=evt.getY();
        posx=evt.getX();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        // TODO add your handling code here:
         int xp=evt.getXOnScreen() - posx;
        int yp=evt.getYOnScreen() - posy;
        this.setLocation(xp, yp);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jbtnretirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnretirarActionPerformed
        // TODO add your handling code here:
        int index= jtabla.getSelectedRow();
        double total =0.0;
        if(index>=0){
            listdetc.remove(index);
            modelo.removeRow(index);
            
            
            
            for (DetalleCotizacion det : listdetc)
                total+=det.getCantidad()*det.getPrecioprod();
            
            jlbltotal.setText("Total= "+fn.FormatoN(total));
            validagenerar();
        }else {
            JOptionPane.showMessageDialog(null,"Seleccione producto a retirar","",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jbtnretirarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnaceptar;
    private javax.swing.JButton jbtnagregar;
    private javax.swing.JButton jbtnretirar;
    private javax.swing.JLabel jlblcarga;
    private javax.swing.JLabel jlbldireccion;
    private javax.swing.JLabel jlblmensaje;
    private javax.swing.JLabel jlblmensajecarga;
    private javax.swing.JLabel jlblnombre;
    private javax.swing.JLabel jlbltelefono;
    private javax.swing.JLabel jlbltotal;
    private javax.swing.JTable jtabla;
    private javax.swing.JTextField jtfcondicionp;
    private javax.swing.JTextField jtfemitidapor;
    private javax.swing.JTextField jtfrucdni;
    // End of variables declaration//GEN-END:variables
}
