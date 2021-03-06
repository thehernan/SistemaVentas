/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.DocumentoDAO;
import DAO.VentasDAO;
import Facturacion.ConsultarEstado;
import Facturacion.ConsumingPost;
import Pojos.Ventas;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author HERNAN
 */
public class JIFBoletas extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFFacturas
     */
    List<Ventas> listdoc;
     int posx;
    int posy;        
    VentasDAO daovent= new VentasDAO();
    MDIMenu menu;
    DocumentoDAO daodoc = new DocumentoDAO();
    public JIFBoletas() {
        
    }
    public JIFBoletas(MDIMenu menu) {
        initComponents();
        jdpdesde.setDate(new Date());
        jdphasta.setDate(new Date());
        filtrar();
        this.menu=menu;
    }
     public String formatnumeric(Object n){
     DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
     simbolos.setDecimalSeparator('.');   
     DecimalFormat nf=new DecimalFormat("#.00",simbolos);
     String num = nf.format(n);
     
     return num;
     }
    
    public void filtrar(){
        
        Runnable runnable= new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(jdpdesde.getDate()!= null && jdphasta.getDate()!=null)
                {
                    double sum=0.0;
                jlblloading.setVisible(true);
                DocumentoDAO daodoc = new DocumentoDAO();
                listdoc=daodoc.mostrar(2, jtfbuscar.getText().toUpperCase(), "comprobante", new Timestamp(jdpdesde.getDate().getTime()),
                new Timestamp(jdphasta.getDate().getTime()), jtablafactura);
                for(Ventas venta:listdoc){
                   sum=sum+ venta.getTotal();
                }
                jlbltotal.setText("Total: "+formatnumeric(sum));
                jlblloading.setVisible(false);
                
                }else {
                    JOptionPane.showMessageDialog(null,"Ingrese fecha","",JOptionPane.ERROR_MESSAGE);
                    
                
                }
                
            }
        };
        Thread t = new Thread(runnable);
        t.start();      
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlblloading = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtablafactura = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtfbuscar = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jdpdesde = new org.jdesktop.swingx.JXDatePicker();
        jdphasta = new org.jdesktop.swingx.JXDatePicker();
        jbtnimprimir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jlblcerrar = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jbtnverdetalle = new javax.swing.JButton();
        jlbltotal = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jbtnnotacredito = new javax.swing.JButton();
        jbtnnotadebito = new javax.swing.JButton();
        imprimirbusqueda = new javax.swing.JButton();
        jbtnticket = new javax.swing.JButton();
        btnconsultarsunat = new javax.swing.JButton();
        jprogres = new javax.swing.JProgressBar();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblloading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ring.gif"))); // NOI18N
        getContentPane().add(jlblloading, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, 180, 180));

        jtablafactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtablafactura);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 204, 1090, 337));

        jLabel1.setText("Doc./ Cliente: ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 167, -1, -1));

        jtfbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfbuscarKeyReleased(evt);
            }
        });
        getContentPane().add(jtfbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 165, 450, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Rango de Fecha"));

        jLabel2.setText("Desde:");

        jLabel3.setText("Hasta:");

        jdpdesde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdpdesdeActionPerformed(evt);
            }
        });

        jdphasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdphastaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addComponent(jdpdesde, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdphasta, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jdpdesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdphasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jbtnimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/print.png"))); // NOI18N
        jbtnimprimir.setText("Print documento");
        jbtnimprimir.setToolTipText("Imprimir Comprobante");
        jbtnimprimir.setBorderPainted(false);
        jbtnimprimir.setContentAreaFilled(false);
        jbtnimprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnimprimirActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnimprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, -1, -1));

        jPanel2.setBackground(new java.awt.Color(238, 238, 238));
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

        jlblcerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrarblanco.png"))); // NOI18N
        jlblcerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblcerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblcerrarMouseReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel4.setText("BOLETAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1028, Short.MAX_VALUE)
                .addComponent(jlblcerrar)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jlblcerrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, -1));

        jbtnverdetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view25px.png"))); // NOI18N
        jbtnverdetalle.setToolTipText("Ver detalle del comprobante");
        jbtnverdetalle.setBorderPainted(false);
        jbtnverdetalle.setContentAreaFilled(false);
        jbtnverdetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnverdetalleActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnverdetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, 40, -1));

        jlbltotal.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jlbltotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlbltotal.setText("* * *");
        getContentPane().add(jlbltotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(874, 550, 230, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jbtnnotacredito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/attachnegro20x20.png"))); // NOI18N
        jbtnnotacredito.setText("Nota Crédito");
        jbtnnotacredito.setToolTipText("Nota de Credito");
        jbtnnotacredito.setBorderPainted(false);
        jbtnnotacredito.setContentAreaFilled(false);
        jbtnnotacredito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnnotacredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnnotacreditoActionPerformed(evt);
            }
        });

        jbtnnotadebito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/attachceleste20x20.png"))); // NOI18N
        jbtnnotadebito.setText("Nota Débito");
        jbtnnotadebito.setBorderPainted(false);
        jbtnnotadebito.setContentAreaFilled(false);
        jbtnnotadebito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnnotadebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnnotadebitoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtnnotacredito)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnnotadebito)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnnotacredito)
                    .addComponent(jbtnnotadebito))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 80, -1, 50));

        imprimirbusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/print.png"))); // NOI18N
        imprimirbusqueda.setText("Print busqueda");
        imprimirbusqueda.setBorderPainted(false);
        imprimirbusqueda.setContentAreaFilled(false);
        imprimirbusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirbusquedaActionPerformed(evt);
            }
        });
        getContentPane().add(imprimirbusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, -1, -1));

        jbtnticket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/print.png"))); // NOI18N
        jbtnticket.setText("Ticket");
        jbtnticket.setBorderPainted(false);
        jbtnticket.setContentAreaFilled(false);
        jbtnticket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnticketActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnticket, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 150, -1, -1));

        btnconsultarsunat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logosunat.png"))); // NOI18N
        btnconsultarsunat.setText("Consultar estado");
        btnconsultarsunat.setToolTipText("Consultar estado SUNAT");
        btnconsultarsunat.setBorderPainted(false);
        btnconsultarsunat.setContentAreaFilled(false);
        btnconsultarsunat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultarsunatActionPerformed(evt);
            }
        });
        getContentPane().add(btnconsultarsunat, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 150, 180, 40));

        jprogres.setString("");
        getContentPane().add(jprogres, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 60, 1170, 10));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlblcerrarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblcerrarMouseReleased
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jlblcerrarMouseReleased

    private void jdpdesdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdpdesdeActionPerformed
        // TODO add your handling code here:
        filtrar();
    }//GEN-LAST:event_jdpdesdeActionPerformed

    private void jdphastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdphastaActionPerformed
        // TODO add your handling code here:
        filtrar();
    }//GEN-LAST:event_jdphastaActionPerformed

    private void jtfbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfbuscarKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            filtrar();
        }
    }//GEN-LAST:event_jtfbuscarKeyReleased

    private void jbtnverdetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnverdetalleActionPerformed
        // TODO add your handling code here:
        if(jtablafactura.getSelectedRow()>=0)
        {   
            Ventas v = listdoc.get(jtablafactura.getSelectedRow());
            JDMostrarVenta jventa = new JDMostrarVenta(new Frame(), isVisible(),v.getIdventa());
            jventa.setVisible(true);
        }else {
            JOptionPane.showMessageDialog(null,"Seleccione comprobante","",JOptionPane.ERROR_MESSAGE);
        
        }
    }//GEN-LAST:event_jbtnverdetalleActionPerformed

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        // TODO add your handling code here:
            posy=evt.getY();
        posx=evt.getX();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        // TODO add your handling code here:
        int xp=evt.getXOnScreen() - posx;
        int yp=evt.getYOnScreen() - posy;
        this.setLocation(xp, yp);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void jbtnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnimprimirActionPerformed
        // TODO add your handling code here:
        int index=jtablafactura.getSelectedRow();
        if(index>=0)
        {
            Ventas v = listdoc.get(index);
            daovent.imprimir(v.getIdventa());
        
        }else {
            JOptionPane.showMessageDialog(null,"Seleccione item","",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jbtnimprimirActionPerformed

    private void jbtnnotacreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnnotacreditoActionPerformed
        // TODO add your handling code here:
        if(jtablafactura.getSelectedRow()>=0)
        {
            Ventas v = listdoc.get(jtablafactura.getSelectedRow());
            JDNotaCreditoDebito ncd=new JDNotaCreditoDebito(new Frame() ,isVisible(),"","NOTA DE CRÉDITO",3,v,menu);
            ncd.setVisible(true);
        }else
        {
            JOptionPane.showMessageDialog(null,"Seleccione Comprobante","",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtnnotacreditoActionPerformed

    private void jbtnnotadebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnnotadebitoActionPerformed
        // TODO add your handling code here:
        if(jtablafactura.getSelectedRow()>=0)
        {
            Ventas v = listdoc.get(jtablafactura.getSelectedRow());
            JDNotaCreditoDebito ncd=new JDNotaCreditoDebito(new Frame(), isVisible(),"","NOTA DE DÉBITO",4,v,menu);
            ncd.setVisible(true);

        }else
        {
            JOptionPane.showMessageDialog(null,"Seleccione Comprobante","",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtnnotadebitoActionPerformed

    private void imprimirbusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirbusquedaActionPerformed
        // TODO add your handling code here:
        daodoc.imprimir(2, jtfbuscar.getText().toUpperCase(), "comprobante", new Timestamp(jdpdesde.getDate().getTime()),
                new Timestamp(jdphasta.getDate().getTime()));
    }//GEN-LAST:event_imprimirbusquedaActionPerformed

    private void jbtnticketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnticketActionPerformed
        // TODO add your handling code here:
        int index=jtablafactura.getSelectedRow();
        if(index>=0)
        {
            Ventas v = listdoc.get(index);
            daovent.imprimirticket(v.getIdventa());
        
        }else {
            JOptionPane.showMessageDialog(null,"Seleccione item","",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jbtnticketActionPerformed

    private void btnconsultarsunatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultarsunatActionPerformed
        // TODO add your handling code here:
           int index=jtablafactura.getSelectedRow();
        if(index>=0)
        {
            Ventas v = listdoc.get(index);

            Runnable runnable=new Runnable() {

                            @Override
                            public void run() {
                                ConsumingPost api;
                          
                            jprogres.setVisible(true);
                            jprogres.setMinimum(0);
                            jprogres.setMaximum(100);
                            jprogres.setStringPainted(true);
                            Ventas nv=null;
                            ConsultarEstado consultars=null;
                                int i = 0;
                            while(i<=100)
                            {
                                 jprogres.setValue(i);
                            
                             
                                if(i==10){
                                     consultars= new ConsultarEstado(v);
                                    
                                }
                                if(i==15){
                                    nv= consultars.apiConsume();
                                    
                                
                                }
                                if(i==25)
                                {
                                    if(nv!=null){
                                        System.out.println("insertando cab");
                                        JDConsultarEstadoSunat consE= new JDConsultarEstadoSunat(new JFrame(), isVisible(),nv);
                                        consE.setVisible(true);
                                    }else{
                                        JOptionPane.showMessageDialog(null,"Error al consultar sunat","Error",JOptionPane.ERROR_MESSAGE);
                                    }
                                    
                                    
                                }
                                if(i==40)
                                {
                                    if(nv!=null){
//                                    daodoc.actualizarestadosunat(nv.isAceptadasunat(), nv.getIdventa());
//                                     if(cab!=null){
                                        System.out.println("actualizando datos sunat");
                                        daovent.actualizarsunat(nv);
//                                         
                                     }
                                   
                                    
                                }
                                if(i==50){
                                    filtrar();
                                
                                }
   
                              
                                    
                                
                               
                            i++;
                            }
                           jprogres.setVisible(false);
                            }
                        };

                        Thread t = new Thread(runnable);
                        t.start();
            

        }else {
            JOptionPane.showMessageDialog(null,"Seleccione item","",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnconsultarsunatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnconsultarsunat;
    private javax.swing.JButton imprimirbusqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnimprimir;
    private javax.swing.JButton jbtnnotacredito;
    private javax.swing.JButton jbtnnotadebito;
    private javax.swing.JButton jbtnticket;
    private javax.swing.JButton jbtnverdetalle;
    private org.jdesktop.swingx.JXDatePicker jdpdesde;
    private org.jdesktop.swingx.JXDatePicker jdphasta;
    private javax.swing.JLabel jlblcerrar;
    private javax.swing.JLabel jlblloading;
    private javax.swing.JLabel jlbltotal;
    private javax.swing.JProgressBar jprogres;
    private javax.swing.JTable jtablafactura;
    private javax.swing.JTextField jtfbuscar;
    // End of variables declaration//GEN-END:variables
}
