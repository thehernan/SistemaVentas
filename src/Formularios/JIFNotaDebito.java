/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.DocumentoDAO;
import Pojos.Ventas;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author HERNAN
 */
public class JIFNotaDebito extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFFacturas
     */
    List<Ventas> listdoc;
     int posx;
    int posy;        
    public JIFNotaDebito() {
        initComponents();
        jdpdesde.setDate(new Date());
        jdphasta.setDate(new Date());
        filtrar();
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
                listdoc=daodoc.mostrar(4, jtfbuscar.getText().toUpperCase(), "sucursal", new Timestamp(jdpdesde.getDate().getTime()),
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
        getContentPane().add(jtfbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 165, 506, -1));

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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 74, -1, -1));

        jbtnimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/print.png"))); // NOI18N
        jbtnimprimir.setToolTipText("Imprimir Comprobante");
        jbtnimprimir.setBorderPainted(false);
        jbtnimprimir.setContentAreaFilled(false);
        jbtnimprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(jbtnimprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1043, 149, -1, -1));

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
        jLabel4.setText("NOTA DE DÉBITO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1114, -1));

        jbtnverdetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view25px.png"))); // NOI18N
        jbtnverdetalle.setToolTipText("Ver detalle del comprobante");
        jbtnverdetalle.setBorderPainted(false);
        jbtnverdetalle.setContentAreaFilled(false);
        jbtnverdetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnverdetalleActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnverdetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(979, 149, -1, -1));

        jlbltotal.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jlbltotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlbltotal.setText("* * *");
        getContentPane().add(jlbltotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(874, 550, 230, -1));

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnimprimir;
    private javax.swing.JButton jbtnverdetalle;
    private org.jdesktop.swingx.JXDatePicker jdpdesde;
    private org.jdesktop.swingx.JXDatePicker jdphasta;
    private javax.swing.JLabel jlblcerrar;
    private javax.swing.JLabel jlblloading;
    private javax.swing.JLabel jlbltotal;
    private javax.swing.JTable jtablafactura;
    private javax.swing.JTextField jtfbuscar;
    // End of variables declaration//GEN-END:variables
}
