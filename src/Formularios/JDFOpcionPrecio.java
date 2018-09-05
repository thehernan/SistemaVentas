/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.FormatoNumerico;
import DAO.ProductoDAO;
import Pojos.Producto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author HERNAN
 */
public class JDFOpcionPrecio extends javax.swing.JDialog {

    /**
     * Creates new form JDFOpcionPrecio
     */
    Producto prod;
    JDBuscarProductoVenta jdbuscar;
    JIFVenta jifventa;
    JIFCotizacion jifcotiza;
    String op;
    
    FormatoNumerico fn= new FormatoNumerico();
    JDBuscarProductoVenta jifbuscarprod;
    ProductoDAO  daoprod = new ProductoDAO();
    JDNotaCreditoDebito jdnota ;
    public JDFOpcionPrecio(javax.swing.JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
       
    }
    public JDFOpcionPrecio(javax.swing.JFrame parent, boolean modal,Producto prod,JIFVenta jifventa,JDBuscarProductoVenta jifbuscarprod) {
        super(parent, modal);
        initComponents();
        
        this.prod=prod;
        this.jifventa = jifventa;
        this.jifbuscarprod= jifbuscarprod; 
        jlblproducto.setText(prod.getCodigo()+" - "+(prod.getDescripcion()));
          jlblopprecio.setText("Precio a negociar > ó = "+fn.FormatoN(prod.getPrecio3()));
        
//        jlblcantidad.setVisible(false);
//        jtfcantidad.setVisible(false);
        
        op="venta";
        this.setLocationRelativeTo(null);
       addEscapeListener(this);
        
    }
     public JDFOpcionPrecio(javax.swing.JFrame parent, boolean modal,Producto prod,JIFVenta jifventa) {
        super(parent, modal);
        initComponents();
        this.prod=prod;
        this.jifventa = jifventa;
       
        jlblproducto.setText(prod.getCodigo()+" - "+(prod.getDescripcion()));
          jlblopprecio.setText("Precio a negociar > ó = "+fn.FormatoN(prod.getPrecio3()));
//        jlblcantidad.setVisible(false);
//        jtfcantidad.setVisible(false);
        
        op="venta";
        this.setLocationRelativeTo(null);
        addEscapeListener(this);
    }
    public JDFOpcionPrecio(javax.swing.JFrame parent, boolean modal,Producto prod,JIFCotizacion jifcotiza,JDBuscarProductoVenta jifbuscarprod) {
        super(parent, modal);
        initComponents();
        this.prod=prod;
        this.jifcotiza = jifcotiza;
        this.jifbuscarprod= jifbuscarprod; 
       jlblproducto.setText(prod.getCodigo()+" - "+(prod.getDescripcion()));
        jlblopprecio.setText("Precio a negociar > ó = "+fn.FormatoN(prod.getPrecio3()));
        
        op="cotiza";
        
        this.setLocationRelativeTo(null);
//        addEscapeListener(new JDialog(this));
        addEscapeListener(this);
    }
    public JDFOpcionPrecio(javax.swing.JFrame parent, boolean modal,Producto prod,JDNotaCreditoDebito jdnota,JDBuscarProductoVenta jifbuscarprod) {
        super(parent, modal);
        initComponents();
        this.prod=prod;
        this.jdnota=jdnota;
        this.jifbuscarprod= jifbuscarprod; 
       jlblproducto.setText(prod.getCodigo()+" - "+(prod.getDescripcion()));
        jlblopprecio.setText("Precio a negociar > ó = "+fn.FormatoN(prod.getPrecio3()));
        
        op="nota";
        
        this.setLocationRelativeTo(null);
//        addEscapeListener(new JDialog(this));
        addEscapeListener(this);
    }
    
    public void validaaceptar(){
       
        try {
           double precio = Double.parseDouble(jtfprecio.getText());
           double canti=Double.parseDouble(jtfcantidad.getText());
                if(canti>0 && precio >=prod.getPrecio3()){
                    
                    jbtnaceptar.setEnabled(true);
                    jlblmensaje.setText("");
                }else {
                    jbtnaceptar.setEnabled(false);
                    
                }
        if(precio < prod.getPrecio3()){
                         jlblmensaje.setText("Ingrese un precio mayor a "+prod.getPrecio());
                    
                    }
        if(canti<=0){
                        jlblmensaje.setText("Ingrese una cantidad valida ");
                    }
              
            
        } catch (Exception e) {
            jbtnaceptar.setEnabled(false);
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
    
//    public double precio(int op){
//        double precio=0.0;
////        try {
//      
//            switch(op){
//                case 0:
//                    jlblprecio.setText(nf.format(prod.getPrecio()));
//                    precio=prod.getPrecio();
//                    
//                    jbtnaceptar.setEnabled(true);
//                    validaaceptar();
//                    break;
//                case 1:
//                    jlblprecio.setText(nf.format(prod.getPrecio1()));
//                    precio=prod.getPrecio1();
//                   
//                    jbtnaceptar.setEnabled(true);
//                    validaaceptar();
//                    break;
//                case 2:
//                    jlblprecio.setText(nf.format(prod.getPrecio2()));
//                    precio=prod.getPrecio2();
//                    
//                    jbtnaceptar.setEnabled(true);
//                    validaaceptar();
//                    break;
//                case 3:
//                    jlblprecio.setText(nf.format(prod.getPrecio3()));
//                    precio=prod.getPrecio3();
//                   
//                    jbtnaceptar.setEnabled(true);
//                    validaaceptar();
//                    break;
//                default:
//                    jlblprecio.setText("Opción invalida");
//                    jbtnaceptar.setEnabled(false);
//                    break;
//       
//       
//            }
////        } catch (Exception e) {
////            jlblprecio.setText("Opción invalida");
////            jbtnaceptar.setEnabled(false);
////        }
//       
//            
//        return precio;
//    
//    }


  ///////////////////////////////////////////////////////////  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlblproducto = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfprecio = new javax.swing.JTextField();
        jbtnaceptar = new javax.swing.JButton();
        jlblmensaje = new javax.swing.JLabel();
        jlblopprecio = new javax.swing.JLabel();
        jlblcantidad = new javax.swing.JLabel();
        jtfcantidad = new javax.swing.JTextField();

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

        jlblproducto.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlblproducto.setText("jLabel1");

        jLabel2.setText("Precio:");

        jtfprecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfprecioActionPerformed(evt);
            }
        });
        jtfprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfprecioKeyReleased(evt);
            }
        });

        jbtnaceptar.setBackground(new java.awt.Color(77, 161, 227));
        jbtnaceptar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jbtnaceptar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnaceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/accept2.png"))); // NOI18N
        jbtnaceptar.setText("Aceptar");
        jbtnaceptar.setBorderPainted(false);
        jbtnaceptar.setContentAreaFilled(false);
        jbtnaceptar.setEnabled(false);
        jbtnaceptar.setOpaque(true);
        jbtnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnaceptarActionPerformed(evt);
            }
        });

        jlblmensaje.setText("* * *");

        jlblopprecio.setText("jLabel1");
        jlblopprecio.setAutoscrolls(true);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblopprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(12, 12, 12)
                        .addComponent(jtfprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jlblmensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlblcantidad)
                        .addGap(0, 0, 0)
                        .addComponent(jtfcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnaceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jlblproducto)
                .addGap(12, 12, 12)
                .addComponent(jlblopprecio)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jlblmensaje))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jbtnaceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jlblcantidad))
                            .addComponent(jtfcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))))
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

    private void jtfprecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfprecioKeyReleased
        // TODO add your handling code here:
//        try{
//        
//            int opc =Integer.parseInt(jtfopcionprecio.getText());
//            precioselect=precio(opc);
//        }catch(Exception e){
//            jlblprecio.setText("Opción invalida");
//        
//        }
        
        
        ////////////////////////////////////////////////
        //
      validaaceptar();
         
                
    }//GEN-LAST:event_jtfprecioKeyReleased

    private void jbtnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnaceptarActionPerformed
        // TODO add your handling code here:
        Double precio = Double.parseDouble(jtfprecio.getText());
        Double cant = Double.parseDouble(jtfcantidad.getText());
        if(op.equals("venta")){
            
            System.out.println("unidmed"+prod.getUnidadm());
            if(prod.getUnidadm().equals("PRODUCTO"))
            {
                if(daoprod.validastockrequerido(prod.getIdproducto(),cant)==true){ ////valida stock venta

                prod.setPrecio(precio);
                prod.setCantidad(cant);
                jifventa.setproducto(prod);
                 this.dispose();
                 if(jifbuscarprod!=null){
                    jifbuscarprod.dispose();
                 }
                }else {
                    JOptionPane.showMessageDialog(null,"No cuenta con stock suficiente","",JOptionPane.INFORMATION_MESSAGE);
                }     
            
            
            
            }else {
                prod.setPrecio(precio);
                prod.setCantidad(cant);
                jifventa.setproducto(prod);
                 this.dispose();
                 if(jifbuscarprod!=null){
                    jifbuscarprod.dispose();
                 }
            
            }
            
             
        
        }
        if(op.equals("cotiza")){
           
            
           jifcotiza.setagregar(prod, cant,precio);
           jifcotiza.validagenerar();
           this.dispose();
           jifbuscarprod.dispose();
        }
        
        if(op.equals("nota")){
             if(prod.getUnidadm().equals("PRODUCTO"))
            {
            
             if(daoprod.validastockrequerido(prod.getIdproducto(),cant)==true){ ////valida stock venta
                 
                 
            
            prod.setPrecio(precio);
            prod.setCantidad(cant);
          
            jdnota.addproducto(prod);
             this.dispose();
             if(jifbuscarprod!=null){
                jifbuscarprod.dispose();
             }
            }else {
                JOptionPane.showMessageDialog(null,"No cuenta con stock suficiente","",JOptionPane.INFORMATION_MESSAGE);
            } 
             
            }else {
                prod.setPrecio(precio);
                prod.setCantidad(cant);
                jdnota.addproducto(prod);
                this.dispose();
                if(jifbuscarprod!=null){
                    jifbuscarprod.dispose();
             }
             
             
             
             }
        
        
        }
        
       
        
    }//GEN-LAST:event_jbtnaceptarActionPerformed

    private void jtfprecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfprecioActionPerformed
        // TODO add your handling code here:
        jbtnaceptar.doClick();
    }//GEN-LAST:event_jtfprecioActionPerformed

    private void jtfcantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcantidadKeyReleased
        // TODO add your handling code here:
        validaaceptar();
    }//GEN-LAST:event_jtfcantidadKeyReleased

    private void jtfcantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfcantidadActionPerformed
        // TODO add your handling code here:
        jbtnaceptar.doClick();
    }//GEN-LAST:event_jtfcantidadActionPerformed

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
                JDFOpcionPrecio dialog = new JDFOpcionPrecio(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jlblmensaje;
    private javax.swing.JLabel jlblopprecio;
    private javax.swing.JLabel jlblproducto;
    private javax.swing.JTextField jtfcantidad;
    private javax.swing.JTextField jtfprecio;
    // End of variables declaration//GEN-END:variables
}
