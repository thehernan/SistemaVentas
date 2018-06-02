/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.Mayusculas;
import DAO.ClienteDAO;
import DAO.DetalleVentaDAO;
import DAO.MonedaDAO;
import DAO.ProductoDAO;
import DAO.TipoComprobanteDAO;
import DAO.VentasDAO;
import Pojos.Cliente;
import Pojos.Moneda;
import Pojos.Producto;
import Pojos.SucursalSingleton;
import Pojos.Tipo_Comprobante;
import Pojos.UsuarioSingleton;
import Pojos.Ventas;
import java.awt.Frame;
import java.awt.Image;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
          if (column == 2 || column ==3) return true;
        else
        return false;
    }
  }; 
  
    Mayusculas mayus = new Mayusculas();
    List<Producto> listprod= new ArrayList<>();
     int posx;
    int posy;
    DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
    
       
    
    List<Tipo_Comprobante> listcomprobante;
    List<Moneda> listmoneda;
    public JIFVenta() {
        initComponents();
        
        // tabla
        simbolos.setDecimalSeparator('.');
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
//       jtfcantidad.grabFocus();
//        this.user= user;
//       jtfdescuento.setValue(0);
//       jtadescripprod.setLineWrap(true);
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
       mostrarmoneda();
       jtfingresecodigo.requestFocus();
       
        
    }
    
    
    public void mostracomprobante(){
        TipoComprobanteDAO comprobanteDAO = new TipoComprobanteDAO();
        
        jcbtipocomprobante.addItem("<<Seleccione>>");
        
        listcomprobante = comprobanteDAO.mostrar();
        for(Tipo_Comprobante comprobante: listcomprobante){
            jcbtipocomprobante.addItem(comprobante.getOp()+" - "+comprobante.getComprobante());
            
        
        }
    
    
    }
    public void mostrarmoneda(){
        MonedaDAO  monedadao= new MonedaDAO();
        listmoneda = monedadao.mostrar();
        for(Moneda m: listmoneda)
        
        {   
            jcbmoneda.addItem(m.getOp()+" - "+m.getMoneda());
            if(m.getMoneda().equals("SOLES"))
            {
            
            jcbmoneda.setSelectedItem(m.getOp()+" - "+m.getMoneda());
            }
            
        
        }
    }
    
    
//    
//    public void imprimircant(){
//        String s="";
//    for (String lista : cantidad){
//         s= s + lista;
//        
//    }
//    jtfcantidad.setText(s);
//    }
    public void calculatotal(){
        DecimalFormat nf = new DecimalFormat("#.00",simbolos);
        Double total=0.0,importe=0.0;
        double descuento= 0.0;
      for(Producto prod : listprod){
          importe = importe + (prod.getCantidad()*prod.getPrecio());
      
      }
      total = importe - descuento;
       
      jlbltotal.setText("Total: "+nf.format(total));
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
        jlbltotal.setText("* * *");
//        jlbliva.setText("");
//        jlblsubtotal.setText("");
        //TEXTO
       
        jtfrut.setText("DNI o R.U.C CLIENTE");
      
        jtfrut.setEnabled(true);
        jtfcompras.setValue(0);
        jtftotalcompras.setValue(0);
//        jtfdescuento.setValue(0);
    
    }
//    public void nuevoprod(){
//        producto= new Producto();
//        jlblcodigo.setText("CODIGO");
//        jlblproducto.setText("PRODUCTO");
////        jlblstock.setValue(0);
//        jlblprecio.setValue(0);
//        jtadescripprod.setText("");
//        jlblimagen.setIcon(null);
//    
//    }
    public void buscarproducto(String codigo){
        System.out.println("codgo "+codigo);
         producto=daoproducto.buscarproducto("CODIGO", 0,sucursalsingleton.getId(), codigo);
        System.out.println(producto.getIdproducto());
        boolean repetido = false;
        if(producto.getIdproducto()!=0){
            for(Producto p: listprod)
            {
                if(p.getIdproducto()==producto.getIdproducto())
                {
                    repetido=true;
                }
            }
            if(repetido==false)
            {
                JDFOpcionPrecio opprecio = new JDFOpcionPrecio(new Frame(), isVisible(), producto, this);
                opprecio.setVisible(true);
            }else {
                JOptionPane.showMessageDialog(null,"El producto ya se encuentra agregado","", JOptionPane.ERROR_MESSAGE);
            
            }
            
    //        jlblcodigo.setText(producto.getCodigo());
    //        jlblproducto.setText(producto.getDescripcion());
    //        jtadescripprod.setText(producto.getObservacion());
    //        producto.getFoto();
    //        jlblprecio.setValue(producto.getPrecio());
    //        jlblstock.setValue(producto.getCantidad());
    //    
       
        }
        else {
            JOptionPane.showMessageDialog(null, "Producto no registrado");
        
        }
    }
    
    
    public void setproducto(Producto producto){
        this.producto=producto;
        jtabla.setColumnSelectionInterval(jtabla.getRowCount(), 0);
        jtabla.requestFocus();
        
        enter();
    
    
    }
    
    
    
    public void setcliente(Cliente cliente){
    this.cliente= cliente;
    jtfrut.setText(cliente.getRut());
    jlbldireccion.setText(cliente.getDireccion());
    jlblmensaje.setText("");
    }
    public void enter(){
         DecimalFormat nf = new DecimalFormat("#.00",simbolos);
         try {

//            if(Double.parseDouble(jtfcantidad.getText())>0 && !jtfcantidad.getText().equals("") && !jtfcantidad.getText().equals("CANTIDAD")){ //VALIDO CANTIDAD INGRESADA
                //           boolean valida;
                System.out.println("enter");
                if(producto.getIdproducto()!=0){ //VALIDO BUSQUEDA DE PRODUCTO
                    //                List<Object> valida= new ArrayList<>();
                    //                List<String> returninsertvent= new ArrayList<>();
                    //                if(idcliente!=0){
//                        producto.setCantidad(Double.parseDouble(jtfcantidad.getText()));
//                        if(daoproducto.validastockrequerido(producto.getIdproducto(),producto.getCantidad())==true){ ////valida stock ventaS

                            //                        System.out.println("valida"+valida.get(0));
                            //                if(String.valueOf(valida.get(0)).equals("true")){
                                //                    DecimalFormat formateador = new DecimalFormat("###");
                                
                                Object[] miarray = new Object[5];
                                //                    miarray[0]=valida.get(1);
                                //                    miarray[1]=producto.getIdproducto();
//                                Double cant = Double.parseDouble(jtfcantidad.getText());
                                miarray[0]=producto.getCodigo();
                                miarray[1]=producto.getDescripcion();
                                miarray[2]=nf.format(producto.getCantidad());
                                miarray[3]=nf.format(producto.getPrecio());
                                Double importe= producto.getPrecio()*producto.getCantidad();

                                miarray[4]=nf.format(importe);

                                modelo.addRow(miarray);
                                TableColumnModel columnModel = jtabla.getColumnModel();
                                columnModel.getColumn(0).setPreferredWidth(100);
                                columnModel.getColumn(1).setPreferredWidth(500);
                                columnModel.getColumn(2).setPreferredWidth(90);
                                columnModel.getColumn(3).setPreferredWidth(90);
                                columnModel.getColumn(4).setPreferredWidth(90);
                                producto.setCantidad(producto.getCantidad());

                                listprod.add(producto);
                                calculatotal();
//                                jtfcantidad.setText("0");
                                //                }
                            //
                            //            }else {
                            //                JOptionPane.showMessageDialog(null, "INGRESE DATOS DEL CLIENTE");
                            //
                            //                }
                        jtfingresecodigo.setText("INGRESE CODIGO DEL PRODUCTO (Alt + b  BUSQUEDA)");
                        //                jtfingresecodigoKeyReleased(null);
                        producto= new Producto();

//                    }else{
//                        JOptionPane.showMessageDialog(null, "No cuenta con stock para la venta del producto");
//                    }

                }else {
                    JOptionPane.showMessageDialog(null, "Ingrese producto");
                }

//            }else{
//                JOptionPane.showMessageDialog(null, "INGRESE UNA CANTIDAD VALIDA");
//
//            }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();
        jbtnbuscar = new javax.swing.JButton();
        jlblcargarventa = new javax.swing.JLabel();
        jlblmensajeproceso = new javax.swing.JLabel();
        jbtnretirar = new javax.swing.JButton();
        jlbltotal = new javax.swing.JLabel();
        jtfrut = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jLabel3 = new javax.swing.JLabel();
        jtfcompras = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jtftotalcompras = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jlblmensaje = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcbtipocomprobante = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jlbldireccion = new javax.swing.JLabel();
        jlblemail = new javax.swing.JLabel();
        jlblrazonsocial = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jcbmoneda = new javax.swing.JComboBox();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        jtfvender.setForeground(new java.awt.Color(0, 0, 0));
        jtfvender.setMnemonic('s');
        jtfvender.setText("Vender (Alt + s)");
        jtfvender.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
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
        jPanel2.add(jtfvender, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 10, 120, 50));

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
        jtfingresecodigo.setText("INGRESE CODIGO DEL PRODUCTO (Alt + b  BUSQUEDA)");
        jtfingresecodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfingresecodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfingresecodigoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfingresecodigoFocusLost(evt);
            }
        });
        jtfingresecodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfingresecodigoActionPerformed(evt);
            }
        });
        jtfingresecodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfingresecodigoKeyReleased(evt);
            }
        });
        panelNice1.add(jtfingresecodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 500, 30));

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
        jtabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jtabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtablaMouseReleased(evt);
            }
        });
        jtabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtablaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtablaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtabla);

        panelNice1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 980, 390));

        jbtnbuscar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnbuscar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Searchx32.png"))); // NOI18N
        jbtnbuscar.setMnemonic('b');
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
        panelNice1.add(jlblmensajeproceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, 210, 20));

        jbtnretirar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/minus20x20.png"))); // NOI18N
        jbtnretirar.setMnemonic('x');
        jbtnretirar.setText("Retirar (Alt + x)");
        jbtnretirar.setBorderPainted(false);
        jbtnretirar.setContentAreaFilled(false);
        jbtnretirar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnretirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnretirarActionPerformed(evt);
            }
        });
        panelNice1.add(jbtnretirar, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 60, -1, -1));

        jlbltotal.setFont(new java.awt.Font("Dialog", 0, 48)); // NOI18N
        jlbltotal.setText("* * *");
        panelNice1.add(jlbltotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 660, 60));

        jPanel2.add(panelNice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 78, 980, 590));

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
        jPanel2.add(jtfrut, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 280, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel3.setText("Compras:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, 20));

        jtfcompras.setEditable(false);
        jtfcompras.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jPanel2.add(jtfcompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 243, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel8.setText("Total Comprado:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, 20));

        jtftotalcompras.setEditable(false);
        jtftotalcompras.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jPanel2.add(jtftotalcompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 243, -1));

        jLabel9.setBackground(new java.awt.Color(255, 51, 51));
        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText("Datos Cliente:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jlblmensaje.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jlblmensaje.setText("VARIOS - VENTAS MENORES A S/.700.00 Y OTROS");
        jPanel2.add(jlblmensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 266, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrarblanco.png"))); // NOI18N
        jLabel2.setToolTipText("Cerrar");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel2MouseReleased(evt);
            }
        });
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 12, -1, -1));

        jPanel2.add(jcbtipocomprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 520, -1));

        jLabel10.setText("Tipo comprobante:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, 20));

        jlbldireccion.setText("Dirección");
        jPanel2.add(jlbldireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jlblemail.setText("Email");
        jPanel2.add(jlblemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jlblrazonsocial.setText("Razón Social");
        jPanel2.add(jlblrazonsocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel1.setText("Moneda:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, -1, 20));

        jcbmoneda.setEnabled(false);
        jcbmoneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbmonedaActionPerformed(evt);
            }
        });
        jPanel2.add(jcbmoneda, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 270, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1287, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfingresecodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfingresecodigoFocusGained
        // TODO add your handling code here:
        if(jtfingresecodigo.getText().equals("INGRESE CODIGO DEL PRODUCTO (Alt + b  BUSQUEDA)")){
           jtfingresecodigo.setText("");
        }
    }//GEN-LAST:event_jtfingresecodigoFocusGained

    private void jtfingresecodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfingresecodigoFocusLost
        // TODO add your handling code here:
        if (jtfingresecodigo.getText().equals("")){
          jtfingresecodigo.setText("INGRESE CODIGO DEL PRODUCTO (Alt + b  BUSQUEDA)");
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
                    isVisible(),this,listprod);
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
            if(jcbtipocomprobante.getSelectedIndex()!=0){
            
                if (JOptionPane.showConfirmDialog(null, "Seguro de realizar la venta","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
             
//              if(idcliente ==0 && !jtfrut.getText().equals("R.U.T CLIENTE") && !jtfcliente.getText().equals("NOMBRE CLIENTE")
//                      && !jtfrut.getText().equals("") && !jtfcliente.getText().equals("") ){ //VALIDO BUSQUEDA DE CLIENTE
//                    cliente.setRut(jtfrut.getText());
//                    cliente.setNombre_razons(jtfcliente.getText().trim().toUpperCase());
//                    idcliente=daocliente.insertarcliente(cliente);
//                }
              ////////////////////
                boolean validacliente=false;
             
                venta.setIdempleado(user.getIdempleado());
                venta.setId_sucursal(sucursalsingleton.getId());
                venta.setDescuento(0.0);
//                venta.setMotivodescuento(jtamotivodesc.getText().toUpperCase());
                if(cliente!=null){
                 venta.setIdcliente(cliente.getId_cliente());
                 validacliente=true; 
//                 venta.setIdventa(daoventa.insertar(venta));
                }
             
                    JOptionPane.showMessageDialog(null,daoventa.insertar(venta,listprod,validacliente),"",JOptionPane.ERROR_MESSAGE);



             //   daoventa.imprimirticketcaja(venta.getIdventa());
                newventa();
            
            
                }
            
            }else {
                   JOptionPane.showMessageDialog(null, "Seleccione tipo de comprobante","",JOptionPane.INFORMATION_MESSAGE);
                  
                   jcbtipocomprobante.requestFocus();
            }
     
       
          
         
        
        }else{
            JOptionPane.showMessageDialog(null, "Ingrese productos");
            jtfingresecodigo.selectAll();
            jtfingresecodigo.requestFocus();
            
        }
        
        
        ///
       
                   
        
        
        
       
        
        
    }//GEN-LAST:event_jtfvenderActionPerformed

    private void jtablaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaMouseReleased
        // TODO add your handling code here:
        
//        verproducto();
    }//GEN-LAST:event_jtablaMouseReleased

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_formKeyPressed

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

    private void jtfvenderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfvenderMousePressed
        // TODO add your handling code here:
         
       
    }//GEN-LAST:event_jtfvenderMousePressed

    private void jtfvenderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfvenderMouseExited
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jtfvenderMouseExited

    private void jbtnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnbuscarActionPerformed
        // TODO add your handling code here:
         JDBuscarProductoVenta buscarproducto = new JDBuscarProductoVenta(new java.awt.Frame(),
                    isVisible(),this,listprod);
            buscarproducto.setVisible(true);
    }//GEN-LAST:event_jbtnbuscarActionPerformed

    private void jLabel2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseReleased
        // TODO add your handling code here:
          if(JOptionPane.showConfirmDialog(null, "Seguro que desea cerrar la ventana","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){

//                    daoproducto.devolverstockrequerido(listprod,jlblmensajeproceso,this);

            this.dispose();
        
        }
    }//GEN-LAST:event_jLabel2MouseReleased

    private void panelNice1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNice1MousePressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_panelNice1MousePressed

    private void panelNice1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNice1MouseDragged
        // TODO add your handling code here:
       
    }//GEN-LAST:event_panelNice1MouseDragged

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

    private void jbtnretirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnretirarActionPerformed
        // TODO add your handling code here:
        int index= jtabla.getSelectedRow();
        if(index >=0){
//            if(evt.getClickCount()==2){

                if(index>=0){
                    if (JOptionPane.showConfirmDialog(null,"Seguro de retirar el producto","",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
//                       Producto proddelete;
//                       proddelete=listprod.get(index);
//                       
//                        daoproducto.devolver(proddelete, jlblmensajeproceso);
                        modelo.removeRow(index);
                        listprod.remove(index);
                        calculatotal();
//                        JOptionPane.showMessageDialog(null,"PRODUCTO RETIRADO DE LA VENTA CORRECTAMENTE");
                    }

                }

//            }

        }else {
            JOptionPane.showMessageDialog(null,"Seleccione producto","",JOptionPane.INFORMATION_MESSAGE);
        
        }
    }//GEN-LAST:event_jbtnretirarActionPerformed

    private void jtablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtablaKeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jtablaKeyPressed

    private void jtablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtablaKeyReleased
        // TODO add your handling code here:
         int index=jtabla.getSelectedRow();
       try {
        if(evt.getKeyCode()==10){
        
           
            DecimalFormat nf = new DecimalFormat("#.00",simbolos);   
            System.out.println("index "+index);
           Producto prod= listprod.get(index);
           Double precio= Double.parseDouble(jtabla.getValueAt(index, 3).toString());
           Double cantidad= Double.parseDouble(jtabla.getValueAt(index, 2).toString());

   //        boolean vprecio=true, vcantidad=true;

               System.out.println("cantidad en tabla "+cantidad);
               System.out.println("Precio en tabla "+precio);
       
        
        
        
        
        
            if(prod.getPrecio3()<=precio){
                prod.setPrecio(precio);

                jtabla.setValueAt(nf.format(prod.getPrecio()*prod.getCantidad()), index,4);


            }else {
            JOptionPane.showMessageDialog(null,"Ingrese un precio valido","",JOptionPane.ERROR_MESSAGE);
                jtabla.setValueAt(nf.format(prod.getPrecio()), index, 3);
    //            vprecio = false;

            }
            if(daoproducto.validastockrequerido(prod.getIdproducto(),cantidad)==true && cantidad>0){
                prod.setCantidad(cantidad);

                jtabla.setValueAt(nf.format(prod.getPrecio()*prod.getCantidad()), index,4);

            }else {
                JOptionPane.showMessageDialog(null,"No cuenta con el stock requerido","",JOptionPane.ERROR_MESSAGE);
                jtabla.setValueAt(nf.format(prod.getCantidad()), index, 2);
                System.out.println("cantidadanterior "+prod.getCantidad());
    //            vcantidad=false;
            }

                System.out.print("cantidadenlista" +prod.getCantidad());

            calculatotal();



                System.out.print("entertabla");
    //        if(vcantidad==true && vprecio==true){
    //        jtabla.setValueAt(newimporte, index,4);
    //        
    //        }
        
        }
            } catch (Exception e) {
                
                jlbltotal.setText("Error");
            }
           
         
    }//GEN-LAST:event_jtablaKeyReleased

    private void jtfingresecodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfingresecodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfingresecodigoActionPerformed

    private void jcbmonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbmonedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbmonedaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnbuscar;
    private javax.swing.JButton jbtnretirar;
    private javax.swing.JComboBox jcbmoneda;
    private javax.swing.JComboBox jcbtipocomprobante;
    private javax.swing.JLabel jlblcargarventa;
    private javax.swing.JLabel jlbldireccion;
    private javax.swing.JLabel jlblemail;
    private javax.swing.JLabel jlblmensaje;
    private javax.swing.JLabel jlblmensajeproceso;
    private javax.swing.JLabel jlblrazonsocial;
    private javax.swing.JLabel jlbltotal;
    private javax.swing.JTable jtabla;
    private javax.swing.JFormattedTextField jtfcompras;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jtfingresecodigo;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jtfrut;
    private javax.swing.JFormattedTextField jtftotalcompras;
    private org.edisoncor.gui.button.ButtonColoredAction jtfvender;
    private org.edisoncor.gui.panel.PanelNice panelNice1;
    private org.edisoncor.gui.util.WindowsUtil windowsUtil1;
    // End of variables declaration//GEN-END:variables
}
