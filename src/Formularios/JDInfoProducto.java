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
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

/**
 *
 * @author HERNAN
 */
public class JDInfoProducto extends javax.swing.JDialog {

    /**
     * Creates new form JDInfoProducto
     */
    FormatoNumerico fn;
    Producto prod;
    ProductoDAO proddao;
    JIFIngresoProducto jifingreso;
    public JDInfoProducto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public JDInfoProducto(java.awt.Frame parent, boolean modal,Producto prod,JIFIngresoProducto jifingreso) {
        super(parent, modal);
        initComponents();
        this.prod=prod;
         fn= new FormatoNumerico();
         proddao = new ProductoDAO();
        cargarprecios();
        cargarprecios1();
        validaguardar();
        this.jifingreso = jifingreso;
        this.setLocationRelativeTo(null);
        addEscapeListener(this);
    }
    
    public void validaguardar(){
             
        
        try {
            
            if(Double.parseDouble(jtfpreciof.getText()) >0 && Double.parseDouble(jtfpreciof1.getText()) >0 &&
                    Double.parseDouble(jtfpreciof2.getText()) >0 && Double.parseDouble(jtfpreciof3.getText()) >0 
                 && Double.parseDouble(jtffactor.getText()) >0 
                   && Double.parseDouble(jtfprecioc.getText()) >0 && Double.parseDouble(jtfcantidad.getText()) >0){
                jbtnguardar.setEnabled(true);
               
                
               
                
                
            }else {
                  jbtnguardar.setEnabled(false);

            }
   
        } catch (Exception e) {
            jbtnguardar.setEnabled(false);
        }
        
       colortext();
    
    
    
    
    }
    
    public void colortext(){
       
         ////////////////////////////////////////////////////////////////////////
     
       
            try {
                if(Double.parseDouble(jtfpreciof.getText()) ==0 || jtfpreciof.getText().equals("")){
                jtfpreciof.setBackground(new Color(248, 193, 186));
                }else {
                    jtfpreciof.setBackground(Color.WHITE);
                }

        } catch (Exception e) {
             jtfpreciof.setBackground(new Color(248, 193, 186));
        }
                try {
                    if(Double.parseDouble(jtfpreciof1.getText()) ==0 || jtfpreciof1.getText().equals("")){
                    jtfpreciof1.setBackground(new Color(248, 193, 186));
                    }else {
                        jtfpreciof1.setBackground(Color.WHITE);
                    }
                
            } catch (Exception e) {
                jtfpreciof1.setBackground(new Color(248, 193, 186));
            }
                    
                try {
                    if(Double.parseDouble(jtfpreciof2.getText()) ==0 || jtfpreciof.getText().equals("")){
                    jtfpreciof2.setBackground(new Color(248, 193, 186));
                    }else {
                        jtfpreciof2.setBackground(Color.WHITE);
                    }
                
            } catch (Exception e) {
                jtfpreciof2.setBackground(new Color(248, 193, 186));
            }
                    
                try {
                    if(Double.parseDouble(jtfpreciof3.getText()) ==0 || jtfpreciof3.getText().equals("")){
                    jtfpreciof3.setBackground(new Color(248, 193, 186));
                    }else {
                        jtfpreciof3.setBackground(Color.WHITE);
                    }
                
            } catch (Exception e) {
                jtfpreciof3.setBackground(new Color(248, 193, 186));
            }
                
                 try {
                    if(Double.parseDouble(jtfprecioc.getText()) ==0 || jtfprecioc.getText().equals("")){
                    jtfprecioc.setBackground(new Color(248, 193, 186));
                    }else {
                        jtfprecioc.setBackground(Color.WHITE);
                    }
                
            } catch (Exception e) {
                jtfprecioc.setBackground(new Color(248, 193, 186));
            }
                 
                try {
                if(Double.parseDouble(jtffactor.getText()) ==0 || jtffactor.getText().equals("")){
                    jtffactor.setBackground(new Color(248, 193, 186));
                }else {
                    jtffactor.setBackground(Color.WHITE);
                }
            } catch (Exception e) {
                jtffactor.setBackground(new Color(248, 193, 186));
            }
                
                
                 try {
                if(Double.parseDouble(jtfcantidad.getText()) ==0 || jtfcantidad.getText().equals("")){
                    jtfcantidad.setBackground(new Color(248, 193, 186));
                }else {
                    jtfcantidad.setBackground(Color.WHITE);
                }
            } catch (Exception e) {
                jtfcantidad.setBackground(new Color(248, 193, 186));
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
    
     public  void cargarprecios(){
//        try {
        
//        producto = daoproducto.buscarproducto("ID", prod.getIdproducto(),sucursalsingleton.getId(),"");
        
        /// mostrar imagen ///
         
        //////////////////////////////////////////////
        jlblcodigo.setText(prod.getCodigo());
        jlbldescripcion.setText(prod.getDescripcion());
        jlblexistencias.setText(fn.FormatoN(prod.getCantidad()));
        jlblfactor1.setText(fn.FormatoN(prod.getFactor()));
        jlbllocalizacion.setText(prod.getLocalizacion());
        jlblultimoprecio.setText(fn.FormatoN(prod.getPrecioc()));
        jlblunidcompra.setText("X "+prod.getUnidmedc());
        jlblunidmedv.setText("X "+prod.getUnidmedv());
        //jtffamilia.setText(producto.getDireccion());
 
        jtfpreciof.setText(fn.FormatoN(prod.getPrecio()));
        jtfpreciof1.setText(fn.FormatoN(prod.getPrecio1()));
        jtfpreciof2.setText(fn.FormatoN(prod.getPrecio2()));
        jtfpreciof3.setText(fn.FormatoN(prod.getPrecio3()));
        
//        jcbmoneda.setSelectedItem(producto.getMoneda());
//        jspmargen.setValue(producto.getMargenG());
        /////////////////////////////////////////////////
//        jcbunidadventa.setSelectedItem(producto.getUnidmedv());
//        jcbunidcompra.setSelectedItem(producto.getUnidmedc());
        jtfprecioc.setText(fn.FormatoN(prod.getPrecioc()));
//        jtfprecioc.selectAll();
        jtffactor.setText(fn.FormatoN(prod.getFactor()));
//        jtfinventariomin.setText(fn.FormatoN(producto.getStockmin()));
//        jtfinventariomax.setText(fn.FormatoN(producto.getStockmax()));
//        jtflocalizacion.setText(producto.getLocalizacion());
        
//        jtfcodigosunat.setText(producto.getCodigosunat());
        
          try {
                
           
        ///////////////////// calcular utilidad //////////////////
        double preciocuniv = prod.getPrecioc()/prod.getFactor();
        jtfpreciocuniv.setText(fn.FormatoN(preciocuniv));
        /////////// precio 1 
            double sinigv =prod.getPrecio()-(prod.getPrecio()*0.18);
            Double utilidad = ((prod.getPrecio()-preciocuniv)/preciocuniv)*100;
            jtfutilidad.setText(fn.FormatoN(utilidad));
            jtfprecio.setText(fn.FormatoN(sinigv));
            
            /////////// precio 2 
            double sinigv1 =prod.getPrecio1()-(prod.getPrecio1()*0.18);
            Double utilidad1 = ((prod.getPrecio1()-preciocuniv)/preciocuniv)*100;
            jtfutilidad1.setText(fn.FormatoN(utilidad1));
            jtfprecio1.setText(fn.FormatoN(sinigv1));
                /////////// precio 3 
            double sinigv2 =prod.getPrecio2()-(prod.getPrecio2()*0.18);
            Double utilidad2 = ((prod.getPrecio2()-preciocuniv)/preciocuniv)*100;
            jtfutilidad2.setText(fn.FormatoN(utilidad2));
            jtfprecio2.setText(fn.FormatoN(sinigv2));
                /////////// precio 4 
            double sinigv3 =prod.getPrecio3()-(prod.getPrecio3()*0.18);
            Double utilidad3 = ((prod.getPrecio3()-preciocuniv)/preciocuniv)*100;
            jtfutilidad3.setText(fn.FormatoN(utilidad3));
            jtfprecio3.setText(fn.FormatoN(sinigv3));
        //////////////////////////////////////////////////////
         } catch (Exception e) {
            }
        
       
        }
     
     
     
       public  void cargarprecios1(){
//        try {
       

 
        jtfprecioff.setText(fn.FormatoN(prod.getPrecio()));
        jtfprecioff1.setText(fn.FormatoN(prod.getPrecio1()));
        jtfprecioff2.setText(fn.FormatoN(prod.getPrecio2()));
        jtfprecioff3.setText(fn.FormatoN(prod.getPrecio3()));

          try {
                
           
        ///////////////////// calcular utilidad //////////////////
        double preciocuniv = prod.getPrecioc()/prod.getFactor();
        jtfpreciocuniv.setText(fn.FormatoN(preciocuniv));
        /////////// precio 1 
            double sinigv =prod.getPrecio()-(prod.getPrecio()*0.18);
            Double utilidad = ((prod.getPrecio()-preciocuniv)/preciocuniv)*100;
            jtfutilidadf.setText(fn.FormatoN(utilidad));
            jtfprecioo.setText(fn.FormatoN(sinigv));
            
            /////////// precio 2 
            double sinigv1 =prod.getPrecio1()-(prod.getPrecio1()*0.18);
            Double utilidad1 = ((prod.getPrecio1()-preciocuniv)/preciocuniv)*100;
            jtfutilidad1f.setText(fn.FormatoN(utilidad1));
            jtfprecioo1.setText(fn.FormatoN(sinigv1));
                /////////// precio 3 
            double sinigv2 =prod.getPrecio2()-(prod.getPrecio2()*0.18);
            Double utilidad2 = ((prod.getPrecio2()-preciocuniv)/preciocuniv)*100;
            jtfutilidadf2.setText(fn.FormatoN(utilidad2));
            jtfprecioo2.setText(fn.FormatoN(sinigv2));
                /////////// precio 4 
            double sinigv3 =prod.getPrecio3()-(prod.getPrecio3()*0.18);
            Double utilidad3 = ((prod.getPrecio3()-preciocuniv)/preciocuniv)*100;
            jtfutilidadf3.setText(fn.FormatoN(utilidad3));
            jtfprecioo3.setText(fn.FormatoN(sinigv3));
        //////////////////////////////////////////////////////
         } catch (Exception e) {
            }
        
       
        }
    
         


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jlblcodigo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlbldescripcion = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jlblexistencias = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jlblfactor1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jlbllocalizacion = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jlblultimoprecio = new javax.swing.JLabel();
        jtfprecio1 = new javax.swing.JLabel();
        jtfprecio2 = new javax.swing.JLabel();
        jtfprecio3 = new javax.swing.JLabel();
        jtfprecio = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jtfpreciof = new javax.swing.JTextField();
        jtfutilidad = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jtfutilidad3 = new javax.swing.JTextField();
        jtfutilidad2 = new javax.swing.JTextField();
        jtfutilidad1 = new javax.swing.JTextField();
        jtfpreciof1 = new javax.swing.JTextField();
        jtfpreciof2 = new javax.swing.JTextField();
        jtfpreciof3 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jtfcantidad = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jtffactor = new javax.swing.JTextField();
        jtfprecioo1 = new javax.swing.JLabel();
        jtfprecioo2 = new javax.swing.JLabel();
        jtfprecioo3 = new javax.swing.JLabel();
        jtfprecioo = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jtfprecioff = new javax.swing.JTextField();
        jtfutilidadf = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jtfutilidadf3 = new javax.swing.JTextField();
        jtfutilidadf2 = new javax.swing.JTextField();
        jtfutilidad1f = new javax.swing.JTextField();
        jtfprecioff1 = new javax.swing.JTextField();
        jtfprecioff2 = new javax.swing.JTextField();
        jtfprecioff3 = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jtfprecioc = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jlblunidcompra = new javax.swing.JLabel();
        jtfpreciocuniv = new javax.swing.JTextField();
        jlblunidmedv = new javax.swing.JLabel();
        jbtnguardar = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel11.setText("INFORMACION DEL PRODUCTO");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(jLabel11)
                .addContainerGap(212, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 50));

        jLabel1.setText("Código:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jlblcodigo.setText("jLabel2");
        getContentPane().add(jlblcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        jLabel2.setText("Descripción:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jlbldescripcion.setText("jLabel3");
        getContentPane().add(jlbldescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, -1));

        jLabel4.setText("Existencias:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jlblexistencias.setText("jLabel5");
        getContentPane().add(jlblexistencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        jLabel6.setText("Factor:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, -1, -1));

        jlblfactor1.setText("jLabel7");
        getContentPane().add(jlblfactor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, -1, -1));

        jLabel8.setText("Localización:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, -1));

        jlbllocalizacion.setText("jLabel9");
        getContentPane().add(jlbllocalizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, -1, -1));

        jLabel10.setText("Último Precio Compra:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 550, 10));

        jlblultimoprecio.setText("jLabel12");
        getContentPane().add(jlblultimoprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, -1, -1));

        jtfprecio1.setText("0");
        getContentPane().add(jtfprecio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 520, -1, 20));

        jtfprecio2.setText("0");
        getContentPane().add(jtfprecio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 520, -1, 20));

        jtfprecio3.setText("0");
        getContentPane().add(jtfprecio3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 520, -1, 20));

        jtfprecio.setText("0");
        getContentPane().add(jtfprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 520, -1, 20));

        jLabel23.setText("Precio de Venta sin igv:");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, -1, -1));

        jLabel21.setText("Márgenes de Utilidad %:");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, -1, -1));

        jLabel22.setText("Precio de Venta neto:");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, -1, -1));

        jtfpreciof.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfpreciofKeyReleased(evt);
            }
        });
        getContentPane().add(jtfpreciof, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 550, 80, -1));

        jtfutilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfutilidadActionPerformed(evt);
            }
        });
        jtfutilidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfutilidadKeyReleased(evt);
            }
        });
        getContentPane().add(jtfutilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 490, 82, -1));

        jLabel27.setBackground(new java.awt.Color(255, 51, 51));
        jLabel27.setForeground(new java.awt.Color(255, 51, 51));
        jLabel27.setText("Público");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 470, -1, -1));

        jLabel26.setBackground(new java.awt.Color(255, 51, 51));
        jLabel26.setForeground(new java.awt.Color(255, 51, 51));
        jLabel26.setText("Min. Publico");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 470, -1, -1));

        jLabel20.setForeground(new java.awt.Color(0, 153, 0));
        jLabel20.setText("Detalle de la Compra");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, -1, -1));

        jLabel25.setBackground(new java.awt.Color(255, 51, 51));
        jLabel25.setForeground(new java.awt.Color(255, 51, 51));
        jLabel25.setText("Mayorista");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 470, -1, -1));

        jLabel24.setBackground(new java.awt.Color(255, 51, 51));
        jLabel24.setForeground(new java.awt.Color(255, 51, 51));
        jLabel24.setText("Otros");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 470, -1, -1));

        jtfutilidad3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfutilidad3ActionPerformed(evt);
            }
        });
        jtfutilidad3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfutilidad3KeyReleased(evt);
            }
        });
        getContentPane().add(jtfutilidad3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 490, 83, -1));

        jtfutilidad2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfutilidad2ActionPerformed(evt);
            }
        });
        jtfutilidad2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfutilidad2KeyReleased(evt);
            }
        });
        getContentPane().add(jtfutilidad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 490, 82, -1));

        jtfutilidad1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfutilidad1KeyReleased(evt);
            }
        });
        getContentPane().add(jtfutilidad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 490, 79, -1));

        jtfpreciof1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfpreciof1KeyReleased(evt);
            }
        });
        getContentPane().add(jtfpreciof1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 550, 80, -1));

        jtfpreciof2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfpreciof2KeyReleased(evt);
            }
        });
        getContentPane().add(jtfpreciof2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 550, 80, -1));

        jtfpreciof3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfpreciof3ActionPerformed(evt);
            }
        });
        jtfpreciof3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfpreciof3KeyReleased(evt);
            }
        });
        getContentPane().add(jtfpreciof3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 550, 80, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 550, 10));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 540, 10));

        jLabel13.setText("Cantidad:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));

        jtfcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfcantidadKeyReleased(evt);
            }
        });
        getContentPane().add(jtfcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, 120, -1));

        jLabel14.setText("Factor:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 360, -1, -1));

        jtffactor.setEnabled(false);
        getContentPane().add(jtffactor, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 360, 90, -1));

        jtfprecioo1.setText("0");
        getContentPane().add(jtfprecioo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, -1, 20));

        jtfprecioo2.setText("0");
        getContentPane().add(jtfprecioo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, -1, 20));

        jtfprecioo3.setText("0");
        getContentPane().add(jtfprecioo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, -1, 20));

        jtfprecioo.setText("0");
        getContentPane().add(jtfprecioo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, -1, 20));

        jLabel29.setText("Precio de Venta sin igv:");
        getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        jLabel30.setText("Márgenes de Utilidad %:");
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel31.setText("Precio de Venta neto:");
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        jtfprecioff.setEnabled(false);
        jtfprecioff.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfprecioffKeyReleased(evt);
            }
        });
        getContentPane().add(jtfprecioff, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 80, -1));

        jtfutilidadf.setEnabled(false);
        jtfutilidadf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfutilidadfActionPerformed(evt);
            }
        });
        jtfutilidadf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfutilidadfKeyReleased(evt);
            }
        });
        getContentPane().add(jtfutilidadf, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 82, -1));

        jLabel32.setBackground(new java.awt.Color(255, 51, 51));
        jLabel32.setForeground(new java.awt.Color(255, 51, 51));
        jLabel32.setText("Público");
        getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, -1, -1));

        jLabel33.setBackground(new java.awt.Color(255, 51, 51));
        jLabel33.setForeground(new java.awt.Color(255, 51, 51));
        jLabel33.setText("Min. Publico");
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, -1, -1));

        jLabel34.setForeground(new java.awt.Color(0, 153, 0));
        jLabel34.setText("Precios de Venta");
        getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 440, -1, -1));

        jLabel35.setBackground(new java.awt.Color(255, 51, 51));
        jLabel35.setForeground(new java.awt.Color(255, 51, 51));
        jLabel35.setText("Mayorista");
        getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, -1, -1));

        jLabel36.setBackground(new java.awt.Color(255, 51, 51));
        jLabel36.setForeground(new java.awt.Color(255, 51, 51));
        jLabel36.setText("Otros");
        getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 210, -1, -1));

        jtfutilidadf3.setEnabled(false);
        jtfutilidadf3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfutilidadf3ActionPerformed(evt);
            }
        });
        jtfutilidadf3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfutilidadf3KeyReleased(evt);
            }
        });
        getContentPane().add(jtfutilidadf3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 230, 83, -1));

        jtfutilidadf2.setEnabled(false);
        jtfutilidadf2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfutilidadf2ActionPerformed(evt);
            }
        });
        jtfutilidadf2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfutilidadf2KeyReleased(evt);
            }
        });
        getContentPane().add(jtfutilidadf2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, 82, -1));

        jtfutilidad1f.setEnabled(false);
        jtfutilidad1f.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfutilidad1fKeyReleased(evt);
            }
        });
        getContentPane().add(jtfutilidad1f, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 79, -1));

        jtfprecioff1.setEnabled(false);
        jtfprecioff1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfprecioff1KeyReleased(evt);
            }
        });
        getContentPane().add(jtfprecioff1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 80, -1));

        jtfprecioff2.setEnabled(false);
        jtfprecioff2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfprecioff2KeyReleased(evt);
            }
        });
        getContentPane().add(jtfprecioff2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, 80, -1));

        jtfprecioff3.setEnabled(false);
        jtfprecioff3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfprecioff3ActionPerformed(evt);
            }
        });
        jtfprecioff3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfprecioff3KeyReleased(evt);
            }
        });
        getContentPane().add(jtfprecioff3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 290, 80, -1));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 540, 10));

        jtfprecioc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfpreciocKeyReleased(evt);
            }
        });
        getContentPane().add(jtfprecioc, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 90, -1));

        jLabel19.setText("Precio de Compra:");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, -1));

        jlblunidcompra.setText("X ...");
        getContentPane().add(jlblunidcompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, -1, -1));

        jtfpreciocuniv.setEnabled(false);
        jtfpreciocuniv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfpreciocunivActionPerformed(evt);
            }
        });
        jtfpreciocuniv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfpreciocunivKeyReleased(evt);
            }
        });
        getContentPane().add(jtfpreciocuniv, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, 130, -1));

        jlblunidmedv.setText("X ...");
        getContentPane().add(jlblunidmedv, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 390, -1, -1));

        jbtnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/accept2.png"))); // NOI18N
        jbtnguardar.setText("Aceptar");
        jbtnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnguardarActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 610, -1, -1));
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, 540, 10));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfpreciofKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfpreciofKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode()==10){
            jtfpreciof1.requestFocus();
            jtfpreciof1.selectAll();
        }
        try {

            double preciosneto = Double.parseDouble(jtfpreciof.getText());
            double sinigv =preciosneto-(preciosneto*0.18);

            double preciocuniv = Double.parseDouble(jtfpreciocuniv.getText());

            Double utilidad = ((preciosneto-preciocuniv)/preciocuniv)*100;

            jtfutilidad.setText(fn.FormatoN(utilidad));
            jtfprecio.setText(fn.FormatoN(sinigv));

        } catch (Exception e) {

            jtfutilidad.setText("0");
            jtfprecio.setText("0");
        }
        validaguardar();

    }//GEN-LAST:event_jtfpreciofKeyReleased

    private void jtfutilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfutilidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfutilidadActionPerformed

    private void jtfutilidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfutilidadKeyReleased
        // TODO add your handling code here:

        if(evt.getKeyCode()==10){
            jtfutilidad1.requestFocus();
            jtfutilidad1.selectAll();
        }

        try {
            double utilid= Double.parseDouble(jtfutilidad.getText());
            double precioc = Double.parseDouble(jtfpreciocuniv.getText());
            double precio =  precioc * (1 + (utilid/100));

            double psinigv = precio - (precio*0.18);

            jtfprecio.setText(fn.FormatoN(psinigv));
            jtfpreciof.setText(fn.FormatoN(precio));

        } catch (Exception e) {

            jtfprecio.setText("0");
            jtfpreciof.setText("0");
        }

        validaguardar();

    }//GEN-LAST:event_jtfutilidadKeyReleased

    private void jtfutilidad3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfutilidad3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfutilidad3ActionPerformed

    private void jtfutilidad3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfutilidad3KeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            jtfpreciof.requestFocus();
            jtfpreciof.selectAll();
        }
        try {
            double utilid= Double.parseDouble(jtfutilidad3.getText());
            double precioc = Double.parseDouble(jtfpreciocuniv.getText());
            double precio =  precioc * (1 + (utilid/100));

            double psinigv = precio - (precio*0.18);

            jtfprecio3.setText(fn.FormatoN(psinigv));
            jtfpreciof3.setText(fn.FormatoN(precio));

        } catch (Exception e) {

            jtfprecio3.setText("0");
            jtfpreciof3.setText("0");
        }
        validaguardar();
    }//GEN-LAST:event_jtfutilidad3KeyReleased

    private void jtfutilidad2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfutilidad2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfutilidad2ActionPerformed

    private void jtfutilidad2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfutilidad2KeyReleased
        // TODO add your handling code here:

        if(evt.getKeyCode()==10){
            jtfutilidad3.requestFocus();
            jtfutilidad3.selectAll();
        }

        try {
            double utilid= Double.parseDouble(jtfutilidad2.getText());
            double precioc = Double.parseDouble(jtfpreciocuniv.getText());
            double precio =  precioc * (1 + (utilid/100));

            double psinigv = precio - (precio*0.18);

            jtfprecio2.setText(fn.FormatoN(psinigv));
            jtfpreciof2.setText(fn.FormatoN(precio));

        } catch (Exception e) {

            jtfprecio2.setText("0");
            jtfpreciof2.setText("0");
        }
        validaguardar();
    }//GEN-LAST:event_jtfutilidad2KeyReleased

    private void jtfutilidad1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfutilidad1KeyReleased
        // TODO add your handling code here:

        if(evt.getKeyCode()==10){
            jtfutilidad2.requestFocus();
            jtfutilidad2.selectAll();
        }

        try {
            double utilid= Double.parseDouble(jtfutilidad1.getText());
            double precioc = Double.parseDouble(jtfpreciocuniv.getText());
            double precio =  precioc * (1 + (utilid/100));

            double psinigv = precio - (precio*0.18);

            jtfprecio1.setText(fn.FormatoN(psinigv));
            jtfpreciof1.setText(fn.FormatoN(precio));

        } catch (Exception e) {

            jtfprecio1.setText("0");
            jtfpreciof1.setText("0");
        }
        validaguardar();
    }//GEN-LAST:event_jtfutilidad1KeyReleased

    private void jtfpreciof1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfpreciof1KeyReleased
        // TODO add your handling code here:
          if(evt.getKeyCode()==10){
            jtfpreciof2.requestFocus();
            jtfpreciof2.selectAll();
        }
        try {

            double preciosneto = Double.parseDouble(jtfpreciof1.getText());
            double sinigv =preciosneto-(preciosneto*0.18);

            double preciocuniv = Double.parseDouble(jtfpreciocuniv.getText());

            Double utilidad = ((preciosneto-preciocuniv)/preciocuniv)*100;

            jtfutilidad1.setText(fn.FormatoN(utilidad));
            jtfprecio1.setText(fn.FormatoN(sinigv));
        } catch (Exception e) {

            jtfutilidad1.setText("0");
            jtfprecio1.setText("0");
        }
        validaguardar();
    }//GEN-LAST:event_jtfpreciof1KeyReleased

    private void jtfpreciof2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfpreciof2KeyReleased
        // TODO add your handling code here:
          if(evt.getKeyCode()==10){
            jtfpreciof3.requestFocus();
            jtfpreciof3.selectAll();
        }
        try {

            double preciosneto = Double.parseDouble(jtfpreciof2.getText());
            double sinigv =preciosneto-(preciosneto*0.18);

            double preciocuniv = Double.parseDouble(jtfpreciocuniv.getText());

            Double utilidad = ((preciosneto-preciocuniv)/preciocuniv)*100;

            jtfutilidad2.setText(fn.FormatoN(utilidad));
            jtfprecio2.setText(fn.FormatoN(sinigv));
        } catch (Exception e) {

            jtfutilidad2.setText("0");
            jtfprecio2.setText("0");
        }
        validaguardar();
    }//GEN-LAST:event_jtfpreciof2KeyReleased

    private void jtfpreciof3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfpreciof3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfpreciof3ActionPerformed

    private void jtfpreciof3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfpreciof3KeyReleased
        // TODO add your handling code here:
          if(evt.getKeyCode()==10){
            jbtnguardar.doClick();
        }

        try {

            double preciosneto = Double.parseDouble(jtfpreciof3.getText());
            double sinigv =preciosneto-(preciosneto*0.18);

            double preciocuniv = Double.parseDouble(jtfpreciocuniv.getText());

            Double utilidad = ((preciosneto-preciocuniv)/preciocuniv)*100;

            jtfutilidad3.setText(fn.FormatoN(utilidad));
            jtfprecio3.setText(fn.FormatoN(sinigv));
        } catch (Exception e) {

            jtfutilidad3.setText("0");
            jtfprecio3.setText("0");
        }
        validaguardar();
    }//GEN-LAST:event_jtfpreciof3KeyReleased

    private void jtfprecioffKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfprecioffKeyReleased
        // TODO add your handling code here:
        try {

            double preciosneto = Double.parseDouble(jtfpreciof.getText());
            double sinigv =preciosneto-(preciosneto*0.18);

            double preciocuniv = Double.parseDouble(jtfpreciocuniv.getText());

            Double utilidad = ((preciosneto-preciocuniv)/preciocuniv)*100;

            jtfutilidad.setText(fn.FormatoN(utilidad));
            jtfprecio.setText(fn.FormatoN(sinigv));

        } catch (Exception e) {

            jtfutilidad.setText("0");
            jtfprecio.setText("0");
        }
        validaguardar();

    }//GEN-LAST:event_jtfprecioffKeyReleased

    private void jtfutilidadfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfutilidadfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfutilidadfActionPerformed

    private void jtfutilidadfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfutilidadfKeyReleased
        // TODO add your handling code here:

        if(evt.getKeyCode()==10){
            jtfutilidad1.requestFocus();
        }

        try {
            double utilid= Double.parseDouble(jtfutilidad.getText());
            double precioc = Double.parseDouble(jtfpreciocuniv.getText());
            double precio =  precioc * (1 + (utilid/100));

            double psinigv = precio - (precio*0.18);

            jtfprecio.setText(fn.FormatoN(psinigv));
            jtfpreciof.setText(fn.FormatoN(precio));

        } catch (Exception e) {

            jtfprecio.setText("0");
            jtfpreciof.setText("0");
        }

        validaguardar();

    }//GEN-LAST:event_jtfutilidadfKeyReleased

    private void jtfutilidadf3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfutilidadf3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfutilidadf3ActionPerformed

    private void jtfutilidadf3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfutilidadf3KeyReleased
        // TODO add your handling code here:

        try {
            double utilid= Double.parseDouble(jtfutilidad3.getText());
            double precioc = Double.parseDouble(jtfpreciocuniv.getText());
            double precio =  precioc * (1 + (utilid/100));

            double psinigv = precio - (precio*0.18);

            jtfprecio3.setText(fn.FormatoN(psinigv));
            jtfpreciof3.setText(fn.FormatoN(precio));

        } catch (Exception e) {

            jtfprecio3.setText("0");
            jtfpreciof3.setText("0");
        }
        validaguardar();
    }//GEN-LAST:event_jtfutilidadf3KeyReleased

    private void jtfutilidadf2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfutilidadf2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfutilidadf2ActionPerformed

    private void jtfutilidadf2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfutilidadf2KeyReleased
        // TODO add your handling code here:

        if(evt.getKeyCode()==10){
            jtfutilidad3.requestFocus();
        }

        try {
            double utilid= Double.parseDouble(jtfutilidad2.getText());
            double precioc = Double.parseDouble(jtfpreciocuniv.getText());
            double precio =  precioc * (1 + (utilid/100));

            double psinigv = precio - (precio*0.18);

            jtfprecio2.setText(fn.FormatoN(psinigv));
            jtfpreciof2.setText(fn.FormatoN(precio));

        } catch (Exception e) {

            jtfprecio2.setText("0");
            jtfpreciof2.setText("0");
        }
        validaguardar();
    }//GEN-LAST:event_jtfutilidadf2KeyReleased

    private void jtfutilidad1fKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfutilidad1fKeyReleased
        // TODO add your handling code here:

        if(evt.getKeyCode()==10){
            jtfutilidad2.requestFocus();
        }

        try {
            double utilid= Double.parseDouble(jtfutilidad1.getText());
            double precioc = Double.parseDouble(jtfpreciocuniv.getText());
            double precio =  precioc * (1 + (utilid/100));

            double psinigv = precio - (precio*0.18);

            jtfprecio1.setText(fn.FormatoN(psinigv));
            jtfpreciof1.setText(fn.FormatoN(precio));

        } catch (Exception e) {

            jtfprecio1.setText("0");
            jtfpreciof1.setText("0");
        }
        validaguardar();
    }//GEN-LAST:event_jtfutilidad1fKeyReleased

    private void jtfprecioff1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfprecioff1KeyReleased
        // TODO add your handling code here:
        try {

            double preciosneto = Double.parseDouble(jtfpreciof1.getText());
            double sinigv =preciosneto-(preciosneto*0.18);

            double preciocuniv = Double.parseDouble(jtfpreciocuniv.getText());

            Double utilidad = ((preciosneto-preciocuniv)/preciocuniv)*100;

            jtfutilidad1.setText(fn.FormatoN(utilidad));
            jtfprecio1.setText(fn.FormatoN(sinigv));
        } catch (Exception e) {

            jtfutilidad1.setText("0");
            jtfprecio1.setText("0");
        }
        validaguardar();
    }//GEN-LAST:event_jtfprecioff1KeyReleased

    private void jtfprecioff2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfprecioff2KeyReleased
        // TODO add your handling code here:
        try {

            double preciosneto = Double.parseDouble(jtfpreciof2.getText());
            double sinigv =preciosneto-(preciosneto*0.18);

            double preciocuniv = Double.parseDouble(jtfpreciocuniv.getText());

            Double utilidad = ((preciosneto-preciocuniv)/preciocuniv)*100;

            jtfutilidad2.setText(fn.FormatoN(utilidad));
            jtfprecio2.setText(fn.FormatoN(sinigv));
        } catch (Exception e) {

            jtfutilidad2.setText("0");
            jtfprecio2.setText("0");
        }
        validaguardar();
    }//GEN-LAST:event_jtfprecioff2KeyReleased

    private void jtfprecioff3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfprecioff3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfprecioff3ActionPerformed

    private void jtfprecioff3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfprecioff3KeyReleased
        // TODO add your handling code here:

        try {

            double preciosneto = Double.parseDouble(jtfpreciof3.getText());
            double sinigv =preciosneto-(preciosneto*0.18);

            double preciocuniv = Double.parseDouble(jtfpreciocuniv.getText());

            Double utilidad = ((preciosneto-preciocuniv)/preciocuniv)*100;

            jtfutilidad3.setText(fn.FormatoN(utilidad));
            jtfprecio3.setText(fn.FormatoN(sinigv));
        } catch (Exception e) {

            jtfutilidad3.setText("0");
            jtfprecio3.setText("0");
        }
        validaguardar();
    }//GEN-LAST:event_jtfprecioff3KeyReleased

    private void jtfpreciocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfpreciocKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            jtfutilidad.requestFocus();
            jtfutilidad.selectAll();
        }

        try {
            double precioc = Double.parseDouble(jtfprecioc.getText());
            //            Double preciocsinigv = precioc -(precioc*0.18);
            //            jtfpreciocunic.setText(fn.FormatoN(preciocsinigv));
            double factor = Double.parseDouble(jtffactor.getText());
            Double preciov = precioc/factor;
            jtfpreciocuniv.setText(fn.FormatoN(preciov));

        } catch (Exception e) {

            //            jtfpreciocunic.setText("0");
            jtfpreciocuniv.setText("0");
        }

        try {

            ///////////////////  precio 1 /////////////////

            double preciocuniv = Double.parseDouble(jtfpreciocuniv.getText());

            /////////// precio neto
            double utilid= Double.parseDouble(jtfutilidad.getText());
            //            double precioc = Double.parseDouble(jtfpreciocuniv.getText());
            double precio =  preciocuniv * (1 + (utilid/100));

            double psinigv = precio - (precio*0.18);

            jtfprecio.setText(fn.FormatoN(psinigv));
            jtfpreciof.setText(fn.FormatoN(precio));
            ////////////// precio 2

            double utilid1= Double.parseDouble(jtfutilidad1.getText());
            //            double precioc = Double.parseDouble(jtfpreciocuniv.getText());
            double precio1 =  preciocuniv * (1 + (utilid1/100));

            double psinigv1 = precio1 - (precio1*0.18);

            jtfprecio1.setText(fn.FormatoN(psinigv1));
            jtfpreciof1.setText(fn.FormatoN(precio1));

            /////////////// precio 3 ////////////////////
            double utilid2= Double.parseDouble(jtfutilidad2.getText());
            //            double precioc2 = Double.parseDouble(jtfpreciocuniv.getText());
            double precio2 =  preciocuniv * (1 + (utilid2/100));

            double psinigv2 = precio2 - (precio2*0.18);

            jtfprecio2.setText(fn.FormatoN(psinigv2));
            jtfpreciof2.setText(fn.FormatoN(precio2));
            /////////////////// precio 4 /////////////

            double utilid3= Double.parseDouble(jtfutilidad3.getText());
            //            double precioc3 = Double.parseDouble(jtfpreciocuniv.getText());
            double precio3 =  preciocuniv * (1 + (utilid3/100));

            double psinigv3 = precio3 - (precio3*0.18);

            jtfprecio3.setText(fn.FormatoN(psinigv3));
            jtfpreciof3.setText(fn.FormatoN(precio3));

        } catch (Exception e) {
        }

        validaguardar();

    }//GEN-LAST:event_jtfpreciocKeyReleased

    private void jtfpreciocunivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfpreciocunivActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfpreciocunivActionPerformed

    private void jtfpreciocunivKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfpreciocunivKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfpreciocunivKeyReleased

    private void jbtnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnguardarActionPerformed
        // TODO add your handling code here:
         double precio= Double.parseDouble(jtfpreciof.getText());
         double precio1= Double.parseDouble(jtfpreciof1.getText());
         double precio2= Double.parseDouble(jtfpreciof2.getText());
         double precio3= Double.parseDouble(jtfpreciof3.getText());
         double precioc= Double.parseDouble(jtfprecioc.getText());
        prod.setPrecio(precio);
        prod.setPrecio1(precio1);
        prod.setPrecio2(precio2);
        prod.setPrecio3(precio3);
        prod.setPrecioc(precioc);
        prod.setCantidad(Double.parseDouble(jtfcantidad.getText()));
        
//        proddao.actualizaprecios(prod);
        
        jifingreso.agregar(prod);
        this.dispose();

    }//GEN-LAST:event_jbtnguardarActionPerformed

    private void jtfcantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcantidadKeyReleased
        // TODO add your handling code here:
        validaguardar();
        if(evt.getKeyCode()==10){
           jtfprecioc.requestFocus();
           jtfprecioc.selectAll();
        
        }
    }//GEN-LAST:event_jtfcantidadKeyReleased

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
            java.util.logging.Logger.getLogger(JDInfoProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDInfoProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDInfoProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDInfoProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDInfoProducto dialog = new JDInfoProducto(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JButton jbtnguardar;
    private javax.swing.JLabel jlblcodigo;
    private javax.swing.JLabel jlbldescripcion;
    private javax.swing.JLabel jlblexistencias;
    private javax.swing.JLabel jlblfactor1;
    private javax.swing.JLabel jlbllocalizacion;
    private javax.swing.JLabel jlblultimoprecio;
    private javax.swing.JLabel jlblunidcompra;
    private javax.swing.JLabel jlblunidmedv;
    private javax.swing.JTextField jtfcantidad;
    private javax.swing.JTextField jtffactor;
    private javax.swing.JLabel jtfprecio;
    private javax.swing.JLabel jtfprecio1;
    private javax.swing.JLabel jtfprecio2;
    private javax.swing.JLabel jtfprecio3;
    private javax.swing.JTextField jtfprecioc;
    private javax.swing.JTextField jtfpreciocuniv;
    private javax.swing.JTextField jtfpreciof;
    private javax.swing.JTextField jtfpreciof1;
    private javax.swing.JTextField jtfpreciof2;
    private javax.swing.JTextField jtfpreciof3;
    private javax.swing.JTextField jtfprecioff;
    private javax.swing.JTextField jtfprecioff1;
    private javax.swing.JTextField jtfprecioff2;
    private javax.swing.JTextField jtfprecioff3;
    private javax.swing.JLabel jtfprecioo;
    private javax.swing.JLabel jtfprecioo1;
    private javax.swing.JLabel jtfprecioo2;
    private javax.swing.JLabel jtfprecioo3;
    private javax.swing.JTextField jtfutilidad;
    private javax.swing.JTextField jtfutilidad1;
    private javax.swing.JTextField jtfutilidad1f;
    private javax.swing.JTextField jtfutilidad2;
    private javax.swing.JTextField jtfutilidad3;
    private javax.swing.JTextField jtfutilidadf;
    private javax.swing.JTextField jtfutilidadf2;
    private javax.swing.JTextField jtfutilidadf3;
    // End of variables declaration//GEN-END:variables
}
