/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.Mayusculas;
import DAO.ClienteDAO;
import DAO.DetalleVentaDAO;
import DAO.ProductoDAO;
import DAO.TipoComprobanteDAO;
import DAO.VentasDAO;
import Pojos.Cliente;
import Pojos.Producto;
import Pojos.SucursalSingleton;
import Pojos.Tipo_Comprobante;
import Pojos.UsuarioSingleton;
import Pojos.Ventas;
import java.awt.Frame;
import java.awt.Image;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author info2017
 */
public class JIFVenta extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFVenta
     */
    List<String> cantidad= new ArrayList<>();
    ProductoDAO daoproducto=new  ProductoDAO();
    Producto producto= new Producto();
    ClienteDAO daocliente = new ClienteDAO();
    Cliente cliente = null;
    byte[]  fotoB;
    Ventas venta= new Ventas();
    VentasDAO daoventa= new VentasDAO();
    DetalleVentaDAO daodetventa = new DetalleVentaDAO();
//    Empleado empleadologin;
//    Usuarios user;
    UsuarioSingleton user= UsuarioSingleton.getintancia();
    SucursalSingleton sucursalsingleton = SucursalSingleton.getinstancia();
    DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
    //      if (column == 5) return true;
    //else
    return false;
}
  }; 
  
    Mayusculas mayus = new Mayusculas();
    List<Producto> listprod= new ArrayList<>();
     int posx;
    int posy;
    
    DecimalFormat nf = new DecimalFormat("#.00");
    List<Tipo_Comprobante> listcomprobante;
    
    public JIFVenta() {
        initComponents();
        // tabla
        String titulos[]={"Codigo","Descripción","Cantidad","Precio","Importe"};
        modelo.setColumnIdentifiers(titulos);
        jtabla.setModel(modelo);
        TableColumnModel columnModel = jtabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(500);
        columnModel.getColumn(2).setPreferredWidth(90);
        columnModel.getColumn(3).setPreferredWidth(90);
        columnModel.getColumn(4).setPreferredWidth(90);
        /////////////////////////77
       jtfcantidad.grabFocus();
//        this.user= user;
//       jtfdescuento.setValue(0);
       jtadescripprod.setLineWrap(true);
        jlblcargarventa.setVisible(false);
    
       ////// IMAGEN DEL PRODUCTO POR DEFAULT////////////////
//       ImageIcon imageIcon= new ImageIcon( getClass().getResource("/imagenes/product.png"));
//       ImageIcon imageUser = imageIcon;
//       Image img = imageUser.getImage();
//       Image newimg = img.getScaledInstance(jlblimagen.getWidth(), jlblimagen.getHeight(), java.awt.Image.SCALE_AREA_AVERAGING);
//       imageUser = new ImageIcon(newimg);
//       jlblimagen.setIcon(imageUser);
       
       //////////////////////////////////////////////////////
       mostracomprobante();
    }
    
    public void mostracomprobante(){
        TipoComprobanteDAO comprobanteDAO = new TipoComprobanteDAO();
        
        jcbtipocomprobante.addItem("<<Seleccione>>");
        
        listcomprobante = comprobanteDAO.mostrar();
        for(Tipo_Comprobante comprobante: listcomprobante){
            jcbtipocomprobante.addItem(comprobante.getOp()+" - "+comprobante.getComprobante());
            
        
        }
    
    
    }
    
    
    
    public void imprimircant(){
        String s="";
    for (String lista : cantidad){
         s= s + lista;
        
    }
    jtfcantidad.setText(s);
    }
    public void calculatotal(){
        Double total=0.0,importe=0.0;
        double descuento= 0.0;
      for(Producto prod : listprod){
          importe = importe + (prod.getCantidad()*prod.getPrecio());
      
      }
      total = importe - descuento;
       
      jlbltotal.setValue(total);
//      jlblsubtotal.setValue(subtotal);
//      jlbliva.setValue(iva);
    }
    public void newventa(){
        //NUEVO MODELO
        
        for (int i = 0; i < jtabla.getRowCount(); i++) {
        modelo.removeRow(i);
        i-=1;
        }
        // NUEVOS OBJETOS 
        venta= new Ventas();
        producto= new Producto();
        cliente = null;
        listprod =new ArrayList<>();
        //LIMPIAR JLBL
        jlbltotal.setValue(0);
//        jlbliva.setText("");
//        jlblsubtotal.setText("");
        //TEXTO
       
        jtfrut.setText("DNI o R.U.C CLIENTE");
      
        jtfrut.setEnabled(true);
        jtfcompras.setValue(0);
        jtftotalcompras.setValue(0);
//        jtfdescuento.setValue(0);
    
    }
    public void nuevoprod(){
        producto= new Producto();
        jlblcodigo.setText("CODIGO");
        jlblproducto.setText("PRODUCTO");
        jlblstock.setValue(0);
        jlblprecio.setValue(0);
        jtadescripprod.setText("");
        jlblimagen.setIcon(null);
    
    }
    public void buscarproducto(String codigo){
        System.out.println("codgo "+codigo);
        
        
        producto=daoproducto.buscarproducto("CODIGO", 0,sucursalsingleton.getId(), codigo);
        System.out.println(producto.getIdproducto());
        if(producto.getIdproducto()!=0){
        JDFOpcionPrecio opprecio = new JDFOpcionPrecio(new Frame(), isVisible(), producto, this);
        opprecio.setVisible(true);
//        jlblcodigo.setText(producto.getCodigo());
//        jlblproducto.setText(producto.getDescripcion());
//        jtadescripprod.setText(producto.getObservacion());
//        producto.getFoto();
//        jlblprecio.setValue(producto.getPrecio());
//        jlblstock.setValue(producto.getCantidad());
//    
        jtfcantidad.requestFocus();
        }
        else {
            JOptionPane.showMessageDialog(null, "Producto no registrado");
        
        }
    }
    public void setproducto(Producto producto){
        
        this.producto=daoproducto.buscarproducto("CODIGO", 0,sucursalsingleton.getId(),producto.getCodigo());
        jlblcodigo.setText(this.producto.getCodigo());
        jlblproducto.setText(this.producto.getDescripcion());
        jtadescripprod.setText(this.producto.getObservacion());
        
        this.producto.setPrecio(producto.getPrecio());
        jlblprecio.setValue(producto.getPrecio());
        jlblstock.setValue(this.producto.getCantidad());
        
        /// mostrar imagen ///
        ImageIcon imageIcon = new ImageIcon(this.producto.getFoto());
        //ImageIcon imageUser = imageIcon;
        Image img = imageIcon.getImage();
        Image newimg = img.getScaledInstance(jlblimagen.getWidth(), jlblimagen.getHeight(), java.awt.Image.SCALE_AREA_AVERAGING);
        imageIcon = new ImageIcon(newimg);
        jlblimagen.setIcon(imageIcon);   
        //////////////////////////////////////////////
        jtfcantidad.requestFocus();
    
    
    
    }
    
    public void setcliente(Cliente cliente){
    this.cliente= cliente;
    jtfrut.setText(cliente.getRut());
    jlbldireccion.setText(cliente.getDireccion());
    jlblmensaje.setText("");
    }
    public void enter(){
         try {

            if(Double.parseDouble(jtfcantidad.getText())>0 && !jtfcantidad.getText().equals("") && !jtfcantidad.getText().equals("CANTIDAD")){ //VALIDO CANTIDAD INGRESADA
                //           boolean valida;
                System.out.println("enter");
                if(producto.getIdproducto()!=0){ //VALIDO BUSQUEDA DE PRODUCTO
                    //                List<Object> valida= new ArrayList<>();
                    //                List<String> returninsertvent= new ArrayList<>();
                    //                if(idcliente!=0){
                        producto.setCantidad(Double.parseDouble(jtfcantidad.getText()));
                        if(daoproducto.validastockrequerido(producto)==true){ ////valida stock ventaS

                            //                        System.out.println("valida"+valida.get(0));
                            //                if(String.valueOf(valida.get(0)).equals("true")){
                                //                    DecimalFormat formateador = new DecimalFormat("###");
                                
                                Object[] miarray = new Object[5];
                                //                    miarray[0]=valida.get(1);
                                //                    miarray[1]=producto.getIdproducto();
                                Double cant = Double.parseDouble(jtfcantidad.getText());
                                miarray[0]=producto.getCodigo();
                                miarray[1]=producto.getDescripcion();
                                miarray[2]=nf.format(cant);
                                miarray[3]=nf.format(producto.getPrecio());
                                Double importe= producto.getPrecio()*cant;

                                miarray[4]=nf.format(importe);

                                modelo.addRow(miarray);
                                TableColumnModel columnModel = jtabla.getColumnModel();
                                columnModel.getColumn(0).setPreferredWidth(100);
                                columnModel.getColumn(1).setPreferredWidth(500);
                                columnModel.getColumn(2).setPreferredWidth(90);
                                columnModel.getColumn(3).setPreferredWidth(90);
                                columnModel.getColumn(4).setPreferredWidth(90);
                                producto.setCantidad(cant);

                                listprod.add(producto);
                                calculatotal();
                                jtfcantidad.setText("0");
                                //                }
                            //
                            //            }else {
                            //                JOptionPane.showMessageDialog(null, "INGRESE DATOS DEL CLIENTE");
                            //
                            //                }
                        jtfingresecodigo.setText("INGRESE CODIGO DEL PRODUCTO (F1 BUSQUEDA)");
                        //                jtfingresecodigoKeyReleased(null);
                        nuevoprod();

                    }else{
                        JOptionPane.showMessageDialog(null, "No cuenta con stock para la venta del producto");
                    }

                }else {
                    JOptionPane.showMessageDialog(null, "Ingrese producto");
                }

            }else{
                JOptionPane.showMessageDialog(null, "INGRESE UNA CANTIDAD VALIDA");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "INGRESE UNA CANTIDAD VALIDA");
        }


    
    
    
    }
    
//    public boolean validadescuento(){
//        boolean valida;
//    double descuento = Double.parseDouble(jtfdescuento.getValue().toString());
//    double total= Double.parseDouble(jlbltotal.getValue().toString());
////    double descuentomax= Double.parseDouble(jlbltotal.getValue().toString())*0.1;
//        if(descuento> total){
//            jlblmsjdescuento.setText("El descuento debe ser menor del total vendido");
//            valida = false;
//        }else {
//            valida=true;
//        }
//    return valida;
//    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        windowsUtil1 = new org.edisoncor.gui.util.WindowsUtil();
        jPanel2 = new javax.swing.JPanel();
        jtfvender = new org.edisoncor.gui.button.ButtonColoredAction();
        panelNice1 = new org.edisoncor.gui.panel.PanelNice();
        jtfingresecodigo = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jLabel7 = new javax.swing.JLabel();
        jlbltotal = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();
        jbtnbuscar = new javax.swing.JButton();
        jlblcargarventa = new javax.swing.JLabel();
        jlblmensajeproceso = new javax.swing.JLabel();
        jlblproducto = new javax.swing.JLabel();
        jlblcodigo = new javax.swing.JLabel();
        jlblprecio = new javax.swing.JFormattedTextField();
        jtfcantidad = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelRound3 = new org.edisoncor.gui.panel.PanelRound();
        jlblimagen = new javax.swing.JLabel();
        jlblstock = new javax.swing.JFormattedTextField();
        jtfrut = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jLabel3 = new javax.swing.JLabel();
        jtfcompras = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jtftotalcompras = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtadescripprod = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtamotivodesc = new javax.swing.JTextArea();
        jlblmensaje = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcbtipocomprobante = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jlbldireccion = new javax.swing.JLabel();
        jlblemail = new javax.swing.JLabel();
        jlblrazonsocial = new javax.swing.JLabel();

        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
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
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfvender.setBackground(new java.awt.Color(255, 0, 0));
        jtfvender.setForeground(new java.awt.Color(255, 0, 0));
        jtfvender.setText("Vender (F2)");
        jtfvender.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jtfvender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jtfvenderMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtfvenderMousePressed(evt);
            }
        });
        jtfvender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfvenderActionPerformed(evt);
            }
        });
        jPanel2.add(jtfvender, new org.netbeans.lib.awtextra.AbsoluteConstraints(1236, 49, 51, 110));

        panelNice1.setBackground(new java.awt.Color(255, 255, 255));
        panelNice1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelNice1.setBorderColor(new java.awt.Color(173, 173, 173));
        panelNice1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelNice1MouseDragged(evt);
            }
        });
        panelNice1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelNice1MousePressed(evt);
            }
        });
        panelNice1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfingresecodigo.setBackground(new java.awt.Color(204, 204, 204));
        jtfingresecodigo.setForeground(new java.awt.Color(255, 255, 255));
        jtfingresecodigo.setText("INGRESE CODIGO DEL PRODUCTO (F1 BUSQUEDA)");
        jtfingresecodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfingresecodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfingresecodigoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfingresecodigoFocusLost(evt);
            }
        });
        jtfingresecodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfingresecodigoKeyReleased(evt);
            }
        });
        panelNice1.add(jtfingresecodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 500, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 102));
        jLabel7.setText("Total:");
        panelNice1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, -1, -1));

        jlbltotal.setEditable(false);
        jlbltotal.setForeground(new java.awt.Color(255, 0, 0));
        jlbltotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jlbltotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        panelNice1.add(jlbltotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 550, 470, -1));

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
        jtabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtablaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtabla);

        panelNice1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 940, 400));

        jbtnbuscar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnbuscar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Searchx32.png"))); // NOI18N
        jbtnbuscar.setToolTipText("Buscar");
        jbtnbuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtnbuscar.setBorderPainted(false);
        jbtnbuscar.setContentAreaFilled(false);
        jbtnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscarActionPerformed(evt);
            }
        });
        panelNice1.add(jbtnbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 22, 40, 40));

        jlblcargarventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/loading_cart.gif"))); // NOI18N
        jlblcargarventa.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        panelNice1.add(jlblcargarventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 240, 180));

        jlblmensajeproceso.setBackground(new java.awt.Color(51, 51, 51));
        jlblmensajeproceso.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jlblmensajeproceso.setForeground(new java.awt.Color(255, 51, 51));
        jlblmensajeproceso.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlblmensajeproceso.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        panelNice1.add(jlblmensajeproceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 70, 210, 20));

        jlblproducto.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jlblproducto.setForeground(new java.awt.Color(102, 102, 102));
        jlblproducto.setText("PRODUCTO");
        panelNice1.add(jlblproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 830, 17));

        jlblcodigo.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jlblcodigo.setForeground(new java.awt.Color(102, 102, 102));
        jlblcodigo.setText("CODIGO");
        panelNice1.add(jlblcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 266, 17));

        jlblprecio.setEditable(false);
        jlblprecio.setBackground(new java.awt.Color(255, 0, 0));
        jlblprecio.setBorder(null);
        jlblprecio.setForeground(new java.awt.Color(255, 0, 0));
        jlblprecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jlblprecio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblprecio.setOpaque(false);
        jlblprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jlblprecioKeyReleased(evt);
            }
        });
        panelNice1.add(jlblprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 550, 20));

        jPanel2.add(panelNice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 78, -1, 590));

        jtfcantidad.setBackground(new java.awt.Color(204, 204, 204));
        jtfcantidad.setForeground(new java.awt.Color(255, 255, 255));
        jtfcantidad.setText("CANTIDAD");
        jtfcantidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfcantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfcantidadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfcantidadFocusLost(evt);
            }
        });
        jtfcantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfcantidadActionPerformed(evt);
            }
        });
        jtfcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfcantidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfcantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfcantidadKeyTyped(evt);
            }
        });
        jPanel2.add(jtfcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 363, 210, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("STOCK");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 79, -1, -1));

        jLabel4.setBackground(new java.awt.Color(153, 153, 153));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("x 1");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 32, -1, -1));

        panelRound3.setForeground(new java.awt.Color(255, 255, 255));
        panelRound3.setColorPrimario(new java.awt.Color(255, 255, 255));
        panelRound3.setColorSecundario(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblimagen, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblimagen, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 0, -1, -1));

        jlblstock.setEditable(false);
        jlblstock.setBorder(null);
        jlblstock.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jlblstock.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblstock.setOpaque(false);
        jlblstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlblstockActionPerformed(evt);
            }
        });
        jPanel2.add(jlblstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 100, 82, 26));

        jtfrut.setBackground(new java.awt.Color(204, 204, 204));
        jtfrut.setForeground(new java.awt.Color(255, 255, 255));
        jtfrut.setText("DNI o R.U.C CLIENTE");
        jtfrut.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jtfrut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfrutFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfrutFocusLost(evt);
            }
        });
        jtfrut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfrutActionPerformed(evt);
            }
        });
        jtfrut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfrutKeyReleased(evt);
            }
        });
        jPanel2.add(jtfrut, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 420, 220, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel3.setText("Compras:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 553, -1, 20));

        jtfcompras.setEditable(false);
        jtfcompras.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jPanel2.add(jtfcompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 579, 243, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel8.setText("Total Comprado:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 604, -1, 20));

        jtftotalcompras.setEditable(false);
        jtftotalcompras.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jPanel2.add(jtftotalcompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 630, 243, -1));

        jLabel9.setBackground(new java.awt.Color(255, 51, 51));
        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText("Datos Cliente:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 399, -1, -1));

        jtadescripprod.setEditable(false);
        jtadescripprod.setColumns(20);
        jtadescripprod.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jtadescripprod.setRows(5);
        jtadescripprod.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtadescripprodFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtadescripprodFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(jtadescripprod);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 193, 266, 70));

        jLabel5.setText("Observación:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 172, -1, -1));

        jLabel6.setText("Motivo del descuento:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 269, -1, -1));

        jtamotivodesc.setColumns(20);
        jtamotivodesc.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jtamotivodesc.setRows(5);
        jScrollPane3.setViewportView(jtamotivodesc);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 290, 266, 67));

        jlblmensaje.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jlblmensaje.setText("VARIOS - VENTAS MENORES A S/.700.00 Y OTROS");
        jPanel2.add(jlblmensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 266, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrarblanco.png"))); // NOI18N
        jLabel2.setToolTipText("Cerrar");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel2MouseReleased(evt);
            }
        });
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 12, -1, -1));

        jPanel2.add(jcbtipocomprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(391, 13, 510, -1));

        jLabel10.setText("Tipo comprobante:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 18, -1, -1));

        jlbldireccion.setText("Dirección");
        jPanel2.add(jlbldireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, -1, -1));

        jlblemail.setText("Email");
        jPanel2.add(jlblemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, -1, -1));

        jlblrazonsocial.setText("Razón Social");
        jPanel2.add(jlblrazonsocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfcantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcantidadFocusGained
        // TODO add your handling code here:
        if(jtfcantidad.getText().equals("CANTIDAD")){
            jtfcantidad.setText("");
        }
    }//GEN-LAST:event_jtfcantidadFocusGained

    private void jtfcantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcantidadFocusLost
        // TODO add your handling code here:
       if(jtfcantidad.getText().equals("")){
           jtfcantidad.setText("CANTIDAD");
       }
       
    }//GEN-LAST:event_jtfcantidadFocusLost

    private void jtfingresecodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfingresecodigoFocusGained
        // TODO add your handling code here:
        if(jtfingresecodigo.getText().equals("INGRESE CODIGO DEL PRODUCTO (F1 BUSQUEDA)")){
           jtfingresecodigo.setText("");
        }
    }//GEN-LAST:event_jtfingresecodigoFocusGained

    private void jtfingresecodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfingresecodigoFocusLost
        // TODO add your handling code here:
        if (jtfingresecodigo.getText().equals("")){
          jtfingresecodigo.setText("INGRESE CODIGO DEL PRODUCTO (F1 BUSQUEDA)");
        }
    }//GEN-LAST:event_jtfingresecodigoFocusLost

    private void jtfingresecodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfingresecodigoKeyReleased
        // TODO add your handling code here:
        String codigo=jtfingresecodigo.getText().replace("\\s", "");
        if(evt.getKeyCode()==10){
            buscarproducto(codigo);
            System.out.println("enter");
        }
        if(evt.getKeyCode()==112){
            JDBuscarProductoVenta buscarproducto = new JDBuscarProductoVenta(new java.awt.Frame(),
                    isVisible(),this);
            buscarproducto.setVisible(true);
                    
        
        }
        
       
        
      
    }//GEN-LAST:event_jtfingresecodigoKeyReleased

    private void jtfrutFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfrutFocusGained
        // TODO add your handling code here:
        if(jtfrut.getText().equals("DNI o R.U.C CLIENTE")){
            jtfrut.setText("");
        }
    }//GEN-LAST:event_jtfrutFocusGained

    private void jtfrutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfrutFocusLost
        // TODO add your handling code here:
        if(jtfrut.getText().equals("")){
            jtfrut.setText("DNI o R.U.C CLIENTE");
        }
    }//GEN-LAST:event_jtfrutFocusLost

    private void jtfrutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfrutKeyReleased
        // TODO add your handling code here:
     
        
           if(evt.getKeyCode()==113){
           jtfvender.doClick();
        
        }
     
               
    }//GEN-LAST:event_jtfrutKeyReleased

    private void jtfvenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfvenderActionPerformed
        // TODO add your handling code here:
        if(listprod.size()>0 ){
         if (JOptionPane.showConfirmDialog(null, "Seguro de realizar la venta","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
             
//              if(idcliente ==0 && !jtfrut.getText().equals("R.U.T CLIENTE") && !jtfcliente.getText().equals("NOMBRE CLIENTE")
//                      && !jtfrut.getText().equals("") && !jtfcliente.getText().equals("") ){ //VALIDO BUSQUEDA DE CLIENTE
//                    cliente.setRut(jtfrut.getText());
//                    cliente.setNombre_razons(jtfcliente.getText().trim().toUpperCase());
//                    idcliente=daocliente.insertarcliente(cliente);
//                }
              ////////////////////
                
                venta.setIdempleado(user.getIdempleado());
                venta.setId_sucursal(sucursalsingleton.getId());
                venta.setDescuento(0.0);
                venta.setMotivodescuento(jtamotivodesc.getText().toUpperCase());
                if(cliente!=null){
                 venta.setIdcliente(cliente.getId_cliente());
                 venta.setIdventa(daoventa.insertar(venta));
                }else {
                 venta.setIdventa(daoventa.insertarnocliente(venta));
                }
             
                daodetventa.insertar(listprod,venta.getIdventa(),jlblmensajeproceso,jlblcargarventa);
             
             
             System.out.println("idvnetqa"+venta.getIdventa());
         //   daoventa.imprimirticketcaja(venta.getIdventa());
            newventa();
            
            
        }
       
          
         
        
        }else{
            JOptionPane.showMessageDialog(null, "INGRESE DATOS VALIDOS");
            
        }
        
        
        ///
       
                   
        
        
        
       
        
        
    }//GEN-LAST:event_jtfvenderActionPerformed

    private void jtfcantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfcantidadActionPerformed
        // TODO add your handling code here:
//        jbtnenter.doClick();
        enter();
    }//GEN-LAST:event_jtfcantidadActionPerformed

    private void jtablaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaMouseReleased
        // TODO add your handling code here:
          int index= jtabla.getSelectedRow();
        if(index >=0){
            if(evt.getClickCount()==2){

                if(index>=0){
                    if (JOptionPane.showConfirmDialog(null,"ESTA SEGURO DE RETIRAR EL PRODUCTO DE LA VENTA","",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                       Producto proddelete;
                       proddelete=listprod.get(index);
                       
                        daoproducto.devolver(proddelete, jlblmensajeproceso);
                        modelo.removeRow(index);
                        listprod.remove(index);
                        calculatotal();
//                        JOptionPane.showMessageDialog(null,"PRODUCTO RETIRADO DE LA VENTA CORRECTAMENTE");
                    }

                }

            }

        }
    }//GEN-LAST:event_jtablaMouseReleased

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_formKeyPressed

    private void jtfcantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcantidadKeyReleased
        // TODO add your handling code here:
        
         if(evt.getKeyCode()==113){
           jtfvender.doClick();
          
        
        }
    }//GEN-LAST:event_jtfcantidadKeyReleased

    private void jtfrutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfrutActionPerformed
        // TODO add your handling code here:
        String rut= jtfrut.getText().trim();
        
        cliente=daocliente.buscarclientevent(rut,jlblmensaje,jtftotalcompras,jtfcompras);
        if(cliente==null){
            jlblrazonsocial.setText("Razón Social");
            jlbldireccion.setText("Dirección");
            jlblemail.setText("Email");
         if(JOptionPane.showConfirmDialog(null,"Nuevo cliente identificado desea registrarlo","",JOptionPane.YES_NO_CANCEL_OPTION)==JOptionPane.YES_OPTION)
         {
            JDNuevoCliente ncliente= new JDNuevoCliente(new JFrame(), isVisible(),rut);
            ncliente.setVisible(true);
         }
        
             
             
        }else {
            jlblrazonsocial.setText("R.S: "+cliente.getNombre_razons());
            jlbldireccion.setText("Dirección: "+cliente.getDireccion());
            jlblemail.setText("Email: "+cliente.getEmail());
        }
    }//GEN-LAST:event_jtfrutActionPerformed

    private void jtadescripprodFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtadescripprodFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtadescripprodFocusGained

    private void jtadescripprodFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtadescripprodFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jtadescripprodFocusLost

    private void jtfvenderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfvenderMousePressed
        // TODO add your handling code here:
         
       
    }//GEN-LAST:event_jtfvenderMousePressed

    private void jtfvenderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfvenderMouseExited
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jtfvenderMouseExited

    private void jtfcantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcantidadKeyPressed
        // TODO add your handling code here:
//         if(evt.getKeyCode()==113){
//             if(listprod.size()>0 && validadescuento()==true){
//               jlblcargarventa.setVisible(true);
//          }   
//               
//               }
    }//GEN-LAST:event_jtfcantidadKeyPressed

    private void jtfcantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcantidadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfcantidadKeyTyped

    private void jbtnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnbuscarActionPerformed
        // TODO add your handling code here:
         JDBuscarProductoVenta buscarproducto = new JDBuscarProductoVenta(new java.awt.Frame(),
                    isVisible(),this);
            buscarproducto.setVisible(true);
    }//GEN-LAST:event_jbtnbuscarActionPerformed

    private void jlblprecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlblprecioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jlblprecioKeyReleased

    private void jLabel2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseReleased
        // TODO add your handling code here:
          if(JOptionPane.showConfirmDialog(null, "Seguro que desea cerrar la ventana","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){

                    daoproducto.devolverstockrequerido(listprod,jlblmensajeproceso,this);

            this.dispose();
        
        }
    }//GEN-LAST:event_jLabel2MouseReleased

    private void panelNice1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNice1MousePressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_panelNice1MousePressed

    private void panelNice1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNice1MouseDragged
        // TODO add your handling code here:
       
    }//GEN-LAST:event_panelNice1MouseDragged

    private void jlblstockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlblstockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jlblstockActionPerformed

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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtnbuscar;
    private javax.swing.JComboBox jcbtipocomprobante;
    private javax.swing.JLabel jlblcargarventa;
    private javax.swing.JLabel jlblcodigo;
    private javax.swing.JLabel jlbldireccion;
    private javax.swing.JLabel jlblemail;
    private javax.swing.JLabel jlblimagen;
    private javax.swing.JLabel jlblmensaje;
    private javax.swing.JLabel jlblmensajeproceso;
    private javax.swing.JFormattedTextField jlblprecio;
    private javax.swing.JLabel jlblproducto;
    private javax.swing.JLabel jlblrazonsocial;
    private javax.swing.JFormattedTextField jlblstock;
    private javax.swing.JFormattedTextField jlbltotal;
    private javax.swing.JTable jtabla;
    private javax.swing.JTextArea jtadescripprod;
    private javax.swing.JTextArea jtamotivodesc;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jtfcantidad;
    private javax.swing.JFormattedTextField jtfcompras;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jtfingresecodigo;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jtfrut;
    private javax.swing.JFormattedTextField jtftotalcompras;
    private org.edisoncor.gui.button.ButtonColoredAction jtfvender;
    private org.edisoncor.gui.panel.PanelNice panelNice1;
    private org.edisoncor.gui.panel.PanelRound panelRound3;
    private org.edisoncor.gui.util.WindowsUtil windowsUtil1;
    // End of variables declaration//GEN-END:variables
}
