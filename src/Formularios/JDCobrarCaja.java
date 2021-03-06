/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.FormatoNumerico;
import DAO.DetalleCajaDAO;
import DAO.VentasDAO;
import Facturacion.ConsumingPost;
import Pojos.DetalleCaja;
import Pojos.GuiaTipo;
import Pojos.Producto;
import Pojos.SerieNumeroRef;
import Pojos.Ventas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author info2017
 */
public class JDCobrarCaja extends javax.swing.JDialog {

    /**
     * Creates new form JDCobrarCaja
     */
//    JIFCaja frmcaja;
    DetalleCaja detcaja = new DetalleCaja();
//    String estado;
//   
//    Reparacion reparacion  = new Reparacion();
//    ReparacionDAO daoreparacion = new ReparacionDAO();
    Ventas venta;       
    List<Producto> listprod;
    List<GuiaTipo> listguia;
    JIFCaja jifcaja;
//    double total;
    FormatoNumerico fn = new FormatoNumerico();
    DetalleCajaDAO daodetcaja = new DetalleCajaDAO();
    VentasDAO daoventa= new VentasDAO();
    
    public JDCobrarCaja(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
   
    public JDCobrarCaja(java.awt.Frame parent, boolean modal,Ventas venta,List<Producto> listprod,List<GuiaTipo> listguia,long idcaja,JIFCaja jifcaja) {
        super(parent, modal);
        initComponents();
       
//        this.frmcaja= frmcaja;
////        Double total=frmcaja.calculatotal();
//        this.total=total;
        
        this.venta=venta;
        this.listprod=listprod;
        this.listguia=listguia;
        this.jifcaja=jifcaja;
        
        detcaja.setIdcaja(idcaja);
        jlbltotal.setText(fn.FormatoN(venta.getTotal()));
        jbtncobrar.setEnabled(false);
        jprogres.setVisible(false);
        
        this.setLocationRelativeTo(null);
//        this.reparacion=reparacion;
//        if(reparacion.getIdreparacion()==0){
//            jtfabono.setEnabled(false);
//            
//        }
        addEscapeListener(this);
    }
//    public boolean calculavuelto(){
//        Double vuelto=0.0,pago=0.0,saldo=0.0;
//        boolean valida=false;
//        System.out.println("tecla");
//        try {
////            double abono= Double.parseDouble(jtfabono.getText());
//            
////            double total = Double.parseDouble(jlbltotal.getValue().toString());
//            pago = Double.parseDouble(jtfpago.getText());
//            detcaja.setPagocon(pago);
//            if( pago  >= venta.getTotal()){
////                saldo= total-abono;
//                vuelto= venta.getTotal()-pago;
////                abonoo=abono;
//                jlblvuelto.setText(fn.FormatoN(vuelto));
////                jlblsaldo.setText(fn.FormatoN(saldo));
//                jlblmensaje.setText("");
//                jbtncobrar.setEnabled(true);
//                valida=true;
////                if(saldo==0){
////                    estado="COBRADO";
////                }else {
////                    estado="DEBE";
////                }
//            }else {
//            
//                jbtncobrar.setEnabled(false);
//                jlblvuelto.setText("* * *");
//        }
//            
//        } catch (Exception e) {
//            jlblmensaje.setText("Error");
//            jbtncobrar.setEnabled(false);
//            valida=false;
//            jlblvuelto.setText("Error");
//        }
//        
//    return valida;
//    }
    public void calculavueltoventa(){
        Double vuelto=0.0;
        try {
//             Double total = Double.parseDouble(jlbltotal.getValue().toString());
        Double pago = Double.parseDouble(jtfpago.getText());
        if(pago >= venta.getTotal()){
        vuelto= pago - venta.getTotal();
        jlblvuelto.setText(fn.FormatoN(vuelto));
        jbtncobrar.setEnabled(true);
        jlblmensaje.setText("");
//        estado="COBRADO";
        }else {
           
            jbtncobrar.setEnabled(false);
            jlblvuelto.setText("* * *");
        }
            
        } catch (Exception e) {
            jlblmensaje.setText("");
            jlblvuelto.setText("* * *");
        }
       
        
    }
    public static void addEscapeListener(final JDialog dialog) {
    ActionListener escListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            dialog.setVisible(false);
        }
    };

    dialog.getRootPane().registerKeyboardAction(escListener,
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_IN_FOCUSED_WINDOW);

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
        jLabel3 = new javax.swing.JLabel();
        jbtncobrar = new javax.swing.JButton();
        jlblmensaje = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jlbltotal = new javax.swing.JLabel();
        jtfpago = new javax.swing.JTextField();
        jlblvuelto = new javax.swing.JLabel();
        jprogres = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel1.setText("Total:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel3.setText("Vuelto:");

        jbtncobrar.setBackground(new java.awt.Color(255, 255, 255));
        jbtncobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cashier_icon-icons.64X64.png"))); // NOI18N
        jbtncobrar.setBorderPainted(false);
        jbtncobrar.setContentAreaFilled(false);
        jbtncobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncobrarActionPerformed(evt);
            }
        });

        jlblmensaje.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel5.setText("Efectivo:");

        jlbltotal.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jlbltotal.setText("* * *");

        jtfpago.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jtfpago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfpagoActionPerformed(evt);
            }
        });
        jtfpago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfpagoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfpagoKeyTyped(evt);
            }
        });

        jlblvuelto.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jlblvuelto.setText("* * *");

        jprogres.setBorder(null);
        jprogres.setBorderPainted(false);
        jprogres.setString("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jprogres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblmensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123)
                        .addComponent(jbtncobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jlblvuelto, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                        .addComponent(jlbltotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtfpago)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jprogres, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jlbltotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtfpago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblvuelto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblmensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtncobrar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtncobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncobrarActionPerformed
        // TODO add your handling code here:
        
//         frmcaja.generadocumentosunat();
//         System.out.println("repara"+reparacion.getIdreparacion());
       
        detcaja.setIdventa(venta.getIdventa());
        detcaja.setAbono(venta.getTotal());
        detcaja.setPagocon(Double.parseDouble(jtfpago.getText()));
        
        
         ////////////////////    ///////////////////////////  nuevo hilo ////////////////
                        Runnable runnable=new Runnable() {

                            @Override
                            public void run() {
                                ConsumingPost api;
                          
                            jprogres.setVisible(true);
                            jprogres.setMinimum(0);
                            jprogres.setMaximum(100);
                            jprogres.setStringPainted(true);
                            Ventas cab=null;
                                int i = 0;
                            while(i<=100)
                            {
                                 jprogres.setValue(i);
                                 if(i==0)
                                 {
                                     jtfpago.setEnabled(false);
                                     jbtncobrar.setEnabled(false);
                                     JDCobrarCaja.this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                                 }
                                     
                             
                                if(i==10){
                                    System.out.println("genera numero ref");
                                    SerieNumeroRef sn;
                                    sn=(daoventa.generarserienumref(venta.getComprobante()));
                                    venta.setSerie(sn.getSerie());
                                    venta.setNumero(sn.getNumero());
                                }
                                if(i==15){
                                    System.out.println("Consumiendo api");
                                    api=new ConsumingPost(venta,listprod,listguia);

                                    cab=api.apiConsume();
                                    
                                
                                }
                                if(i==25)
                                {
                                    if(cab!=null){
                                        System.out.println("insertando cab");
                                        daodetcaja.insertar(detcaja,cab.getSerie(),cab.getNumero());
                                    }
                                    
                                    
                                }
                                if(i==40)
                                {
                                     if(cab!=null){
                                        System.out.println("actualizando datos sunat");
                                        daoventa.actualizarsunat(cab);
                                         
                                     }
                                   
                                    
                                }
   
                                if(i==50){
                                    if(cab!=null){
                                        JDCobrarCaja.this.setVisible(false);
                                        
                                        
//                                         daoventa.imprimir(cab.getIdventa());
//                                         if(cab.getIdcomprobante()==1){
//                                             
//                                         }
                                         ////////////// imprimir ticket en la boleta
//                                         else if(cab.getIdcomprobante()==2){
                                             daoventa.imprimirticket(cab.getIdventa());
//                                         }
                                    }
                                    
                                }
                                    
                                
                                if(i==100)
                                {
                                        if(cab!=null)
                                        {
                                            
                                            jifcaja.newcobro();
                                            JDCobrarCaja.this.dispose();
                                        
                                        }else {
                                            jtfpago.setEnabled(true);
                                            jbtncobrar.setEnabled(true);
                                            JDCobrarCaja.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                                            
                                        }
                                        
                                    
                                   
                                      
                                
                                }
                            i++;
                            }
                           jprogres.setVisible(false);
                            }
                        };

                        Thread t = new Thread(runnable);
                        t.start();

       
    }//GEN-LAST:event_jbtncobrarActionPerformed

    private void jtfpagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfpagoKeyReleased
        // TODO add your handling code here:
//         if(reparacion.getIdreparacion()==0){
            calculavueltoventa();
        
//        }else {
//            calculavuelto();
//        }
    }//GEN-LAST:event_jtfpagoKeyReleased

    private void jtfpagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfpagoKeyTyped
        // TODO add your handling code here:
                 char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9')) 
        && (caracter != KeyEvent.VK_BACK_SPACE)
        && (caracter != '.' || jtfpago.getText().contains(".")) ) {
            evt.consume();
}
    }//GEN-LAST:event_jtfpagoKeyTyped

    private void jtfpagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfpagoActionPerformed
        // TODO add your handling code here:
        jbtncobrar.doClick();
    }//GEN-LAST:event_jtfpagoActionPerformed

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
            java.util.logging.Logger.getLogger(JDCobrarCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDCobrarCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDCobrarCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDCobrarCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDCobrarCaja dialog = new JDCobrarCaja(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtncobrar;
    private javax.swing.JLabel jlblmensaje;
    private javax.swing.JLabel jlbltotal;
    private javax.swing.JLabel jlblvuelto;
    private javax.swing.JProgressBar jprogres;
    private javax.swing.JTextField jtfpago;
    // End of variables declaration//GEN-END:variables
}
