/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.FormatoNumerico;
import DAO.ProductoDAO;
import Pojos.Producto;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

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
        jlblppublico.setText(fn.FormatoN(prod.getPrecio()));
        jlblpminpublico.setText(fn.FormatoN(prod.getPrecio1()));
        jlblpmayorista.setText(fn.FormatoN(prod.getPrecio2()));
        jlblpotros.setText(fn.FormatoN(prod.getPrecio3()));
        jtfprecio.setText(fn.FormatoN(prod.getPrecio()));
        jtfprecio.requestFocus();
        jtfprecio.selectAll();
        /////////// titulo panel //////////
        Border border = BorderFactory.createTitledBorder("X "+prod.getUnidmedv());
        jpunidv.setBorder(border);
        
        Border border1 = BorderFactory.createTitledBorder("X "+prod.getUnidmedc());
        jpunidc.setBorder(border1);
        
        //////////////////////////////////////////
        jlblunidv.setText("X "+prod.getUnidmedv());
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
         jlblppublico.setText(fn.FormatoN(prod.getPrecio()));
        jlblpminpublico.setText(fn.FormatoN(prod.getPrecio1()));
        jlblpmayorista.setText(fn.FormatoN(prod.getPrecio2()));
        jlblpotros.setText(fn.FormatoN(prod.getPrecio3()));
        
        jtfprecio.setText(fn.FormatoN(prod.getPrecio()));
        jtfprecio.selectAll();
          /////////// titulo panel //////////
        Border border = BorderFactory.createTitledBorder("X "+prod.getUnidmedv());
        jpunidv.setBorder(border);
        
        Border border1 = BorderFactory.createTitledBorder("X "+prod.getUnidmedc());
        jpunidc.setBorder(border1);
        
        //////////////////////////////////////////
        jlblunidv.setText("X "+prod.getUnidmedv());
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
        jlblppublico.setText(fn.FormatoN(prod.getPrecio()));
        jlblpminpublico.setText(fn.FormatoN(prod.getPrecio1()));
        jlblpmayorista.setText(fn.FormatoN(prod.getPrecio2()));
        jlblpotros.setText(fn.FormatoN(prod.getPrecio3()));
        
        jtfprecio.setText(fn.FormatoN(prod.getPrecio()));
        jtfprecio.selectAll();
          /////////// titulo panel //////////
        Border border = BorderFactory.createTitledBorder("X "+prod.getUnidmedv());
        jpunidv.setBorder(border);
        
        Border border1 = BorderFactory.createTitledBorder("X "+prod.getUnidmedc());
        jpunidc.setBorder(border1);
        
        //////////////////////////////////////////
        jlblunidv.setText("X "+prod.getUnidmedv());
        
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
        jlblppublico.setText(fn.FormatoN(prod.getPrecio()));
        jlblpminpublico.setText(fn.FormatoN(prod.getPrecio1()));
        jlblpmayorista.setText(fn.FormatoN(prod.getPrecio2()));
        jlblpotros.setText(fn.FormatoN(prod.getPrecio3()));
        jtfprecio.setText(fn.FormatoN(prod.getPrecio()));
        jtfprecio.selectAll();
          /////////// titulo panel //////////
        Border border = BorderFactory.createTitledBorder("X "+prod.getUnidmedv());
        jpunidv.setBorder(border);
        
        Border border1 = BorderFactory.createTitledBorder("X "+prod.getUnidmedc());
        jpunidc.setBorder(border1);
        
        //////////////////////////////////////////
        jlblunidv.setText("X "+prod.getUnidmedv());
        
        op="nota";
        
        this.setLocationRelativeTo(null);
//        addEscapeListener(new JDialog(this));
        addEscapeListener(this);
    }
    
    public void validaaceptar(){
       
        try {
           double precio = Double.parseDouble(jtfprecio.getText());
           double canti=Double.parseDouble(jtfcantidad.getText());
           double importe = Double.parseDouble(jtfimporte.getText());
                if(canti>0 && precio >=prod.getPrecio3() && importe>0){
                    
                    jbtnaceptar.setEnabled(true);
                    jlblmensaje.setText("");
                }else {
                    jbtnaceptar.setEnabled(false);
                     
                }
        if(precio < prod.getPrecio3()){
                        jlblmensaje.setText("Ingrese un precio mayor o igual "+fn.FormatoN(prod.getPrecio3()));
                    
                    }
        if(canti<=0){
                        jlblmensaje.setText("Ingrese una cantidad valida ");
                    }
              
            
        } catch (Exception e) {
            jbtnaceptar.setEnabled(false);
        }
        
        
        try {
            double precio = Double.parseDouble(jtfprecio.getText());
            if(precio<=0 || jtfprecio.getText().equals("")){
                jtfprecio.setBackground(new Color(248, 193, 186));
                 jtfcantidad.setText("");
            }else {
                jtfprecio.setBackground(Color.WHITE);
               
//                jtfimporte.setText("");
            }
            
        } catch (Exception e) {
            jtfprecio.setBackground(new Color(248, 193, 186));
        }
        
        try {
            double cant = Double.parseDouble(jtfcantidad.getText());
            if(cant<=0 && jtfcantidad.getText().equals("")){
                jtfcantidad.setBackground(new Color(248, 193, 186));
            }else {
                jtfcantidad.setBackground(Color.WHITE);
            
            }
            
        } catch (Exception e) {
            jtfcantidad.setBackground(new Color(248, 193, 186));
//            jtfimporte.setText("");
        }
        
        try {
            double importe = Double.parseDouble(jtfimporte.getText());
            if(importe<=0 && jtfimporte.getText().equals("")){
                jtfimporte.setBackground(new Color(248, 193, 186));
            }else {
                jtfimporte.setBackground(Color.WHITE);
            }
            
        } catch (Exception e) {
             jtfimporte.setBackground(new Color(248, 193, 186));
//             jtfcantidad.setText("");
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
        jlblmensaje = new javax.swing.JLabel();
        jlblcantidad = new javax.swing.JLabel();
        jtfcantidad = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jlblppublico = new javax.swing.JLabel();
        jlblpotros = new javax.swing.JLabel();
        jlblpmayorista = new javax.swing.JLabel();
        jlblpminpublico = new javax.swing.JLabel();
        jlblunidv = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jpunidv = new javax.swing.JPanel();
        jbtnunounidv = new javax.swing.JButton();
        jtfmediounidv = new javax.swing.JButton();
        jbtncuartounidv = new javax.swing.JButton();
        jpunidc = new javax.swing.JPanel();
        jbtncuartounic = new javax.swing.JButton();
        jbtnmediaunidv = new javax.swing.JButton();
        jbtnunounidc = new javax.swing.JButton();
        jbtnaceptar = new javax.swing.JButton();
        jtfimporte = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(600, 196));
        setModal(true);
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblproducto.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlblproducto.setText("jLabel1");
        getContentPane().add(jlblproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 774, -1));

        jLabel2.setText("Precio:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

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
        getContentPane().add(jtfprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 154, -1));

        jlblmensaje.setText("* * *");
        getContentPane().add(jlblmensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 240, -1));

        jlblcantidad.setText("Cantidad:");
        getContentPane().add(jlblcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

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
        getContentPane().add(jtfcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 154, -1));

        jLabel1.setForeground(new java.awt.Color(30, 113, 69));
        jLabel1.setText("P. Publico");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jLabel3.setForeground(new java.awt.Color(0, 163, 0));
        jLabel3.setText("P. Min. Publico");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, -1, -1));

        jLabel4.setForeground(new java.awt.Color(218, 83, 44));
        jLabel4.setText("P. Mayorista");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, -1, -1));

        jLabel5.setForeground(new java.awt.Color(185, 29, 71));
        jLabel5.setText("P. Otros");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, -1, -1));

        jlblppublico.setText("***");
        getContentPane().add(jlblppublico, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jlblpotros.setText("***");
        getContentPane().add(jlblpotros, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, -1, -1));

        jlblpmayorista.setText("***");
        getContentPane().add(jlblpmayorista, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, -1, -1));

        jlblpminpublico.setText("***");
        getContentPane().add(jlblpminpublico, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, -1, -1));

        jlblunidv.setText("***");
        getContentPane().add(jlblunidv, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpunidv.setBorder(javax.swing.BorderFactory.createTitledBorder("X unidV"));
        jpunidv.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtnunounidv.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jbtnunounidv.setMnemonic('1');
        jbtnunounidv.setText("1 (alt + 1)");
        jbtnunounidv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnunounidvActionPerformed(evt);
            }
        });
        jpunidv.add(jbtnunounidv, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        jtfmediounidv.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jtfmediounidv.setMnemonic('2');
        jtfmediounidv.setText("1/2 (alt + 2)");
        jtfmediounidv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfmediounidvActionPerformed(evt);
            }
        });
        jpunidv.add(jtfmediounidv, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, -1, -1));

        jbtncuartounidv.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jbtncuartounidv.setMnemonic('4');
        jbtncuartounidv.setText("1/4 (alt + 4)");
        jbtncuartounidv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncuartounidvActionPerformed(evt);
            }
        });
        jpunidv.add(jbtncuartounidv, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jPanel1.add(jpunidv, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 360, 80));

        jpunidc.setBorder(javax.swing.BorderFactory.createTitledBorder("X UnidC"));
        jpunidc.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtncuartounic.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jbtncuartounic.setMnemonic('a');
        jbtncuartounic.setText("1/4 (alt + a)");
        jbtncuartounic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncuartounicActionPerformed(evt);
            }
        });
        jpunidc.add(jbtncuartounic, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jbtnmediaunidv.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jbtnmediaunidv.setMnemonic('b');
        jbtnmediaunidv.setText("1/2 (alt + b)");
        jbtnmediaunidv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnmediaunidvActionPerformed(evt);
            }
        });
        jpunidc.add(jbtnmediaunidv, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, -1, -1));

        jbtnunounidc.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jbtnunounidc.setMnemonic('c');
        jbtnunounidc.setText("1 (alt + c)");
        jbtnunounidc.setToolTipText("");
        jbtnunounidc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnunounidcActionPerformed(evt);
            }
        });
        jpunidc.add(jbtnunounidc, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        jPanel1.add(jpunidc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 360, 90));

        jbtnaceptar.setBackground(new java.awt.Color(34, 75, 139));
        jbtnaceptar.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
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
        jPanel1.add(jbtnaceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 360, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 380, 260));

        jtfimporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfimporteKeyReleased(evt);
            }
        });
        getContentPane().add(jtfimporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 155, -1));

        jLabel6.setText("Importe:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

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
      if(evt.getKeyCode()==10){
          jtfcantidad.requestFocus();
          jtfcantidad.selectAll();
      }  
      
        try {
            
            double cant =Double.parseDouble(jtfcantidad.getText());
            double precio = Double.parseDouble(jtfprecio.getText());
            double importe = cant*precio;
            
            jtfimporte.setText(fn.FormatoN(importe));                  
            
        } catch (Exception e) {
            jtfimporte.setText("0");
        }
      
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
       
             try {
            
            double cant =Double.parseDouble(jtfcantidad.getText());
            double precio = Double.parseDouble(jtfprecio.getText());
            double importe = cant*precio;
            
            jtfimporte.setText(fn.FormatoN(importe));                  
            
        } catch (Exception e) {
            jtfimporte.setText("0");
        }
          if(evt.getKeyCode()==10){
              jtfimporte.requestFocus();
              jtfimporte.selectAll();
          }  
     
        
        validaaceptar();
    }//GEN-LAST:event_jtfcantidadKeyReleased

    private void jtfcantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfcantidadActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jtfcantidadActionPerformed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode()==27)
            this.dispose();
    }//GEN-LAST:event_formKeyReleased

    private void jtfimporteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfimporteKeyReleased
        // TODO add your handling code here:
          try {
            
            
            double precio = Double.parseDouble(jtfprecio.getText());
            double importe = Double.parseDouble(jtfimporte.getText());
            
            double cant =importe/precio;
            jtfcantidad.setText(fn.FormatoN(cant));                  
            
        } catch (Exception e) {
            jtfcantidad.setText("0");
        }
         validaaceptar();
         
         if(evt.getKeyCode()==10){
          jbtnaceptar.doClick();
         }
         
        
        
    }//GEN-LAST:event_jtfimporteKeyReleased

    private void jbtnmediaunidvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnmediaunidvActionPerformed
        // TODO add your handling code here:
        double cant = prod.getFactor()*0.50;
        jtfcantidad.setText(fn.FormatoN(cant));
        try {
            double precio = Double.parseDouble(jtfprecio.getText());
            double importe = precio * cant;
            jtfimporte.setText(fn.FormatoN(importe));
            
        } catch (Exception e) {
            jtfimporte.setText("0");
        }
        
        validaaceptar();
        
        jtfimporte.requestFocus();
        jtfimporte.selectAll();
    }//GEN-LAST:event_jbtnmediaunidvActionPerformed

    private void jbtncuartounidvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncuartounidvActionPerformed
        // TODO add your handling code here:
        jtfcantidad.setText("0.250");
        try {
            double precio = Double.parseDouble(jtfprecio.getText());
            double importe = precio * 0.250;
            jtfimporte.setText(fn.FormatoN(importe));
            
        } catch (Exception e) {
            jtfimporte.setText("0");
        }
        validaaceptar();
        
        jtfimporte.requestFocus();
        jtfimporte.selectAll();
        
        
    }//GEN-LAST:event_jbtncuartounidvActionPerformed

    private void jtfmediounidvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfmediounidvActionPerformed
        // TODO add your handling code here:
         jtfcantidad.setText("0.500");
        try {
            double precio = Double.parseDouble(jtfprecio.getText());
            double importe = precio * 0.500;
            jtfimporte.setText(fn.FormatoN(importe));
            
        } catch (Exception e) {
            jtfimporte.setText("0");
        }
        
        validaaceptar();
        
        jtfimporte.requestFocus();
        jtfimporte.selectAll();
    }//GEN-LAST:event_jtfmediounidvActionPerformed

    private void jbtnunounidvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnunounidvActionPerformed
        // TODO add your handling code here:
        jtfcantidad.setText("1.000");
        try {
            double precio = Double.parseDouble(jtfprecio.getText());
            double importe = precio * 1.000;
            jtfimporte.setText(fn.FormatoN(importe));
            
        } catch (Exception e) {
            jtfimporte.setText("0");
        }
        
        validaaceptar();
        
        jtfimporte.requestFocus();
        jtfimporte.selectAll();
    }//GEN-LAST:event_jbtnunounidvActionPerformed

    private void jbtncuartounicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncuartounicActionPerformed
        // TODO add your handling code here:
        double cant = prod.getFactor()*0.250;
        jtfcantidad.setText(fn.FormatoN(cant));
        try {
            double precio = Double.parseDouble(jtfprecio.getText());
            double importe = precio * cant;
            jtfimporte.setText(fn.FormatoN(importe));
            
        } catch (Exception e) {
            jtfimporte.setText("0");
        }
        
        validaaceptar();
        
        jtfimporte.requestFocus();
        jtfimporte.selectAll();
    }//GEN-LAST:event_jbtncuartounicActionPerformed

    private void jbtnunounidcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnunounidcActionPerformed
        // TODO add your handling code here:
        double cant = prod.getFactor();
        jtfcantidad.setText(fn.FormatoN(cant));
        try {
            double precio = Double.parseDouble(jtfprecio.getText());
            double importe = precio * cant;
            jtfimporte.setText(fn.FormatoN(importe));
            
        } catch (Exception e) {
            jtfimporte.setText("0");
        }
        
        validaaceptar();
        
        jtfimporte.requestFocus();
        jtfimporte.selectAll();
    }//GEN-LAST:event_jbtnunounidcActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtnaceptar;
    private javax.swing.JButton jbtncuartounic;
    private javax.swing.JButton jbtncuartounidv;
    private javax.swing.JButton jbtnmediaunidv;
    private javax.swing.JButton jbtnunounidc;
    private javax.swing.JButton jbtnunounidv;
    private javax.swing.JLabel jlblcantidad;
    private javax.swing.JLabel jlblmensaje;
    private javax.swing.JLabel jlblpmayorista;
    private javax.swing.JLabel jlblpminpublico;
    private javax.swing.JLabel jlblpotros;
    private javax.swing.JLabel jlblppublico;
    private javax.swing.JLabel jlblproducto;
    private javax.swing.JLabel jlblunidv;
    private javax.swing.JPanel jpunidc;
    private javax.swing.JPanel jpunidv;
    private javax.swing.JTextField jtfcantidad;
    private javax.swing.JTextField jtfimporte;
    private javax.swing.JButton jtfmediounidv;
    private javax.swing.JTextField jtfprecio;
    // End of variables declaration//GEN-END:variables
}
