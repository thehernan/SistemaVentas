/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Pojos.Producto;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author HERNAN
 */
public class JDFOpcionPrecio extends java.awt.Dialog {

    /**
     * Creates new form JDFOpcionPrecio
     */
    Producto prod;
    JDBuscarProductoVenta jdbuscar;
    JIFVenta jifventa;
    JIFCotizacion jifcotiza;
    String op;
    Double precioselect;
    DecimalFormat nf =new  DecimalFormat("#.00");  
    JDBuscarProductoVenta jifbuscarprod;
    public JDFOpcionPrecio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public JDFOpcionPrecio(java.awt.Frame parent, boolean modal,Producto prod,JIFVenta jifventa,JDBuscarProductoVenta jifbuscarprod) {
        super(parent, modal);
        initComponents();
        this.prod=prod;
        this.jifventa = jifventa;
        this.jifbuscarprod= jifbuscarprod; 
        jlblproducto.setText(prod.getCodigo()+" - "+(prod.getDescripcion()));
        jlblopprecio.setText("0.-Precio: "+nf.format(prod.getPrecio()));
        jlblopprecio1.setText("1.-Precio: "+nf.format(prod.getPrecio1()));
        jlblopprecio2.setText("2.-Precio: "+nf.format(prod.getPrecio2()));
        jlblopprecio3.setText("3.-Precio: "+nf.format(prod.getPrecio3()));
        jlblcantidad.setVisible(false);
        jtfcantidad.setVisible(false);
        
        op="venta";
        this.setLocationRelativeTo(null);
        
    }
     public JDFOpcionPrecio(java.awt.Frame parent, boolean modal,Producto prod,JIFVenta jifventa) {
        super(parent, modal);
        initComponents();
        this.prod=prod;
        this.jifventa = jifventa;
       
        jlblproducto.setText(prod.getCodigo()+" - "+(prod.getDescripcion()));
        jlblopprecio.setText("0.-Precio: "+nf.format(prod.getPrecio()));
        jlblopprecio1.setText("1.-Precio: "+nf.format(prod.getPrecio1()));
        jlblopprecio2.setText("2.-Precio: "+nf.format(prod.getPrecio2()));
        jlblopprecio3.setText("3.-Precio: "+nf.format(prod.getPrecio3()));
        jlblcantidad.setVisible(false);
        jtfcantidad.setVisible(false);
        
        op="venta";
        this.setLocationRelativeTo(null);
        
    }
    public JDFOpcionPrecio(java.awt.Frame parent, boolean modal,Producto prod,JIFCotizacion jifcotiza,JDBuscarProductoVenta jifbuscarprod) {
        super(parent, modal);
        initComponents();
        this.prod=prod;
        this.jifcotiza = jifcotiza;
        this.jifbuscarprod= jifbuscarprod; 
        jlblproducto.setText(prod.getCodigo()+" - "+prod.getDescripcion());
        jlblopprecio.setText("0.-Precio: "+nf.format(prod.getPrecio()));
        jlblopprecio1.setText("1.-Precio: "+nf.format(prod.getPrecio1()));
        jlblopprecio2.setText("2.-Precio: "+nf.format(prod.getPrecio2()));
        jlblopprecio3.setText("3.-Precio: "+nf.format(prod.getPrecio3()));
        
        op="cotiza";
        
        this.setLocationRelativeTo(null);
        
    }
    
    public void validaaceptar(){
       
        try {
            if(op.equals("cotiza")){
                if(Double.parseDouble(jtfcantidad.getText())<=0){
                    
                    jbtnaceptar.setEnabled(false);
                }else {
                    jbtnaceptar.setEnabled(true);
                }
        
            }    
            
        } catch (Exception e) {
            jbtnaceptar.setEnabled(false);
        }
       
   
    }
    
    public double precio(int op){
        double precio=0.0;
//        try {
      
            switch(op){
                case 0:
                    jlblprecio.setText(nf.format(prod.getPrecio()));
                    precio=prod.getPrecio();
                    
                    jbtnaceptar.setEnabled(true);
                    validaaceptar();
                    break;
                case 1:
                    jlblprecio.setText(nf.format(prod.getPrecio1()));
                    precio=prod.getPrecio1();
                   
                    jbtnaceptar.setEnabled(true);
                    validaaceptar();
                    break;
                case 2:
                    jlblprecio.setText(nf.format(prod.getPrecio2()));
                    precio=prod.getPrecio2();
                    
                    jbtnaceptar.setEnabled(true);
                    validaaceptar();
                    break;
                case 3:
                    jlblprecio.setText(nf.format(prod.getPrecio3()));
                    precio=prod.getPrecio3();
                   
                    jbtnaceptar.setEnabled(true);
                    validaaceptar();
                    break;
                default:
                    jlblprecio.setText("Opción invalida");
                    jbtnaceptar.setEnabled(false);
                    break;
       
       
            }
//        } catch (Exception e) {
//            jlblprecio.setText("Opción invalida");
//            jbtnaceptar.setEnabled(false);
//        }
       
            
        return precio;
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlblproducto = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfopcionprecio = new javax.swing.JTextField();
        jbtnaceptar = new javax.swing.JButton();
        jlblprecio = new javax.swing.JLabel();
        jlblopprecio = new javax.swing.JLabel();
        jlblopprecio1 = new javax.swing.JLabel();
        jlblopprecio2 = new javax.swing.JLabel();
        jlblopprecio3 = new javax.swing.JLabel();
        jlblcantidad = new javax.swing.JLabel();
        jtfcantidad = new javax.swing.JTextField();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jlblproducto.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        jlblproducto.setForeground(new java.awt.Color(255, 51, 51));
        jlblproducto.setText("jLabel1");

        jLabel2.setText("Opción de Precio");

        jtfopcionprecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfopcionprecioActionPerformed(evt);
            }
        });
        jtfopcionprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfopcionprecioKeyReleased(evt);
            }
        });

        jbtnaceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/accept2.png"))); // NOI18N
        jbtnaceptar.setText("Aceptar");
        jbtnaceptar.setEnabled(false);
        jbtnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnaceptarActionPerformed(evt);
            }
        });

        jlblprecio.setText("* * *");

        jlblopprecio.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jlblopprecio.setForeground(new java.awt.Color(255, 51, 51));
        jlblopprecio.setText("jLabel1");
        jlblopprecio.setAutoscrolls(true);

        jlblopprecio1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jlblopprecio1.setForeground(new java.awt.Color(255, 51, 51));
        jlblopprecio1.setText("jLabel1");
        jlblopprecio1.setAutoscrolls(true);

        jlblopprecio2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jlblopprecio2.setForeground(new java.awt.Color(255, 51, 51));
        jlblopprecio2.setText("jLabel1");
        jlblopprecio2.setAutoscrolls(true);

        jlblopprecio3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jlblopprecio3.setForeground(new java.awt.Color(255, 51, 51));
        jlblopprecio3.setText("jLabel1");
        jlblopprecio3.setAutoscrolls(true);

        jlblcantidad.setText("Cantidad:");

        jtfcantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfcantidadActionPerformed(evt);
            }
        });
        jtfcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfcantidadKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblproducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblopprecio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblopprecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblopprecio2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblopprecio3, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jtfopcionprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jlblprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jtfcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(104, 104, 104)
                                                .addComponent(jbtnaceptar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                .addGap(102, 102, 102))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jlblcantidad)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jlblproducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblopprecio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblopprecio1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblopprecio2)
                .addGap(3, 3, 3)
                .addComponent(jlblopprecio3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfopcionprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblprecio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnaceptar)
                    .addComponent(jlblcantidad)
                    .addComponent(jtfcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void jtfopcionprecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfopcionprecioKeyReleased
        // TODO add your handling code here:
        try{
        
            int opc =Integer.parseInt(jtfopcionprecio.getText());
            precioselect=precio(opc);
        }catch(Exception e){
            jlblprecio.setText("Opción invalida");
        
        }
         
                
    }//GEN-LAST:event_jtfopcionprecioKeyReleased

    private void jbtnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnaceptarActionPerformed
        // TODO add your handling code here:
        if(op.equals("venta")){
            prod.setPrecio(precioselect);
            jifventa.setproducto(prod);
             this.dispose();
             if(jifbuscarprod!=null){
                jifbuscarprod.dispose();
             }
                 
             
        
        }
        if(op.equals("cotiza")){
            int opc =Integer.parseInt(jtfopcionprecio.getText());
         
           jifcotiza.setagregar(prod, Double.parseDouble(jtfcantidad.getText()),precio(opc));
           jifcotiza.validagenerar();
           this.dispose();
           jifbuscarprod.dispose();
        }
        
        
    }//GEN-LAST:event_jbtnaceptarActionPerformed

    private void jtfopcionprecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfopcionprecioActionPerformed
        // TODO add your handling code here:
        jbtnaceptar.doClick();
    }//GEN-LAST:event_jtfopcionprecioActionPerformed

    private void jtfcantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcantidadKeyReleased
        // TODO add your handling code here:
        validaaceptar();
    }//GEN-LAST:event_jtfcantidadKeyReleased

    private void jtfcantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfcantidadActionPerformed
        // TODO add your handling code here:
        jbtnaceptar.doClick();
    }//GEN-LAST:event_jtfcantidadActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDFOpcionPrecio dialog = new JDFOpcionPrecio(new java.awt.Frame(), true);
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbtnaceptar;
    private javax.swing.JLabel jlblcantidad;
    private javax.swing.JLabel jlblopprecio;
    private javax.swing.JLabel jlblopprecio1;
    private javax.swing.JLabel jlblopprecio2;
    private javax.swing.JLabel jlblopprecio3;
    private javax.swing.JLabel jlblprecio;
    private javax.swing.JLabel jlblproducto;
    private javax.swing.JTextField jtfcantidad;
    private javax.swing.JTextField jtfopcionprecio;
    // End of variables declaration//GEN-END:variables
}
