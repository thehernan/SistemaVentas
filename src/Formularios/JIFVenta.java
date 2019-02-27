/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.FormatoNumerico;
import ClasesGlobales.Mayusculas;
import DAO.ClienteDAO;
import DAO.DetalleVentaDAO;
import DAO.MonedaDAO;
import DAO.ProductoDAO;
import DAO.SunatTransaccionDAO;
import DAO.TipoComprobanteDAO;
import DAO.VentasDAO;
import DAO.identGuiaDAO;
import Pojos.Cliente;
import Pojos.Codigo_sunat;
import Pojos.GuiaTipo;
import Pojos.Ident_Guia;
import Pojos.Moneda;
import Pojos.Producto;
import Pojos.SucursalSingleton;
import Pojos.Sunat_Transaction;
import Pojos.Tipo_Comprobante;
import Pojos.Ventas;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

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
    boolean errorventa=false ;
    List<GuiaTipo> listguiatipo= new ArrayList<>();

    DetalleVentaDAO daodetventa = new DetalleVentaDAO();
//    Empleado empleadologin;
//    Usuarios user;
    SunatTransaccionDAO tipoopdao = new SunatTransaccionDAO();
    identGuiaDAO idenguiadao = new identGuiaDAO();
    SucursalSingleton sucursalsingleton = SucursalSingleton.getinstancia();
    DefaultTableModel modelo;
  
    Mayusculas mayus = new Mayusculas();
    List<Producto> listprod= new ArrayList<>();
     int posx;
    int posy;
 
    
       
    
    List<Tipo_Comprobante> listcomprobante;
    List<Sunat_Transaction> listtipoop;
    List<Ident_Guia> listidetguia;
    List<Moneda> listmoneda;
    FormatoNumerico fn = new FormatoNumerico();
    MDIMenu menu;
     public JIFVenta() {
         
     }
    public JIFVenta(MDIMenu menu) {
        initComponents();
        
        // tabla
     
//        String titulos[]={"Codigo","Descripción","Cantidad","Precio","Importe"};
//        modelo.setColumnIdentifiers(titulos);
//        jtabla.setModel(modelo);
        modelo= (DefaultTableModel)jtabla.getModel();
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
//        jlblcargarventa.setVisible(false);
    
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
       mostrartipoop();
       mostraridentguia();
//       jtfingresecodigo.requestFocus();
    
       jbtnretirar.setMnemonic(KeyEvent.VK_DELETE);
        anadeListenerAlModelo(jtabla);
        jprogres.setVisible(false);
        this.menu=menu;
       
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
    
    
    public void mostraridentguia(){
        listidetguia=idenguiadao.mostrar();
        for (Ident_Guia ig: listidetguia){
            jcbtipoguia.addItem(ig.getDescripcion());
        
        }
    }
    
    public void mostrartipoop(){
        listtipoop=tipoopdao.mostrar();
        
        for(Sunat_Transaction st : listtipoop ){
            jcbtipoop.addItem(st.getTransaction());
        
        
        }

    }
    
    protected void actualizaimporte(TableModelEvent evento)
    {
          
              if(evento.getType() == TableModelEvent.UPDATE)
        {

                // Se obtiene el modelo de la tabla y la fila/columna que han cambiado.
            TableModel modelo = ((TableModel) (evento.getSource()));
            int fila = evento.getFirstRow();
            int columna = evento.getColumn();

            // Los cambios en la ultima fila y columna se ignoran.
            // Este return es necesario porque cuando nuestro codigo modifique
            // los valores de las sumas en esta fila y columna, saltara nuevamente
            // el evento, metiendonos en un bucle recursivo de llamadas a este
            // metodo.
            if (columna == 0 || columna == 1 || columna==4) {
                return;
            }
                
        try {
          
           System.out.println("index "+fila);
           Producto prod= listprod.get(fila);
           Double precio= Double.parseDouble(modelo.getValueAt(fila, 3).toString());
           Double cantidad= Double.parseDouble(modelo.getValueAt(fila, 2).toString());

   //        boolean vprecio=true, vcantidad=true;

               System.out.println("cantidad en tabla "+cantidad);
               System.out.println("Precio en tabla "+precio);
       
        
        
        
        
        
            if(prod.getPrecio3()<=precio){
                prod.setPrecio(precio);

                modelo.setValueAt(fn.FormatoN(prod.getPrecio()*prod.getCantidad()), fila,4);


            }else {
            JOptionPane.showMessageDialog(null,"Ingrese un precio valido","",JOptionPane.ERROR_MESSAGE);
                modelo.setValueAt((fn.FormatoN(prod.getPrecio())), fila, 3);
    //            vprecio = false;

            }
            if(cantidad>0){
                prod.setCantidad(cantidad);

                modelo.setValueAt(fn.FormatoN(prod.getPrecio()*prod.getCantidad()), fila,4);

            }else {
                JOptionPane.showMessageDialog(null,"No cuenta con el stock requerido","",JOptionPane.ERROR_MESSAGE);
                modelo.setValueAt(fn.FormatoN(prod.getCantidad()), fila, 2);
                System.out.println("cantidadanterior "+prod.getCantidad());
    //            vcantidad=false;
            }

                System.out.print("cantidadenlista" +prod.getCantidad());

            calculatotal();

            errorventa=false;

                System.out.print("entertabla");
    //        if(vcantidad==true && vprecio==true){
    //        jtabla.setValueAt(newimporte, index,4);
    //        
    //        }

            } catch (NumberFormatException | HeadlessException e) {
                
                jlbltotal.setText("Error");
                errorventa=true;
            }
    
    }
    }
    
    
    
    
    
    
    
    public void mostracomprobante(){
        TipoComprobanteDAO comprobanteDAO = new TipoComprobanteDAO();
        
     
        
        listcomprobante = comprobanteDAO.mostrar();
        for(Tipo_Comprobante comprobante: listcomprobante){
            if(comprobante.getOp()==1 || comprobante.getOp()==2)
            {
                jcbtipocomprobante.addItem(comprobante.getOp()+" - "+comprobante.getComprobante());
                if(comprobante.getOp()==2)
                {
                    jcbtipocomprobante.setSelectedItem(comprobante.getOp()+" - "+comprobante.getComprobante());
                }
            
            
            }
            
        
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
            jlbltipocambio.setText(String.valueOf(m.getTipo_cambio()));
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
    public double calculatotal(){
      
        Double total=0.0,importe=0.0;
        double descuento= 0.0;
      for(Producto prod : listprod){
          importe = importe + (prod.getCantidad()*prod.getPrecio());
      
      }
      total = importe - descuento;
      Moneda moneda = listmoneda.get(jcbmoneda.getSelectedIndex());
      jlbltotal.setText(moneda.getSimbolo()+fn.FormatoN(total));
//      jlbltipocambio.setText(String.valueOf(moneda.getTipo_cambio()));
//      jlblsubtotal.setValue(subtotal);
//      jlbliva.setValue(iva);
      return total;
    }
    
    
    public void newventa(){
        //NUEVO MODELO
        
        
        for (int i = 0; i < jtabla.getRowCount(); i++) {
        modelo.removeRow(i);
        i-=1;
        }
        //////// guia /////////////////
        DefaultTableModel modeloguia = (DefaultTableModel)jtablaguia.getModel();
        for (int i = 0; i < jtablaguia.getRowCount(); i++) {
        modeloguia.removeRow(i);
        i-=1;
        }
        // NUEVOS OBJETOS 
        venta= new Ventas();
        producto= new Producto();
        cliente = null;
        listprod =new ArrayList<>();
        listguiatipo =new ArrayList<>();
        //LIMPIAR JLBL
//        jlbltotal.setText("* * *");
//        jlbliva.setText("");
//        jlblsubtotal.setText("");
        //TEXTO
       
        jtfrut.setText("");
        jlblrazonsocial.setText("Razón Social");
        jlbldireccion.setText("Dirección");
        jlblemail.setText("Email");
        jtfrut.setEnabled(true);
        jtfcondicionp.setText("");
        jckdetraccion.setSelected(false);
        jtfordencompra.setText("");
        jtfplacavehiculo.setText("");
        jtfguiaserie.setText("");
        calculatotal();
//        jtfcompras.setValue(0);
//        jtftotalcompras.setValue(0);
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
                JDFOpcionPrecio opprecio = new JDFOpcionPrecio(new JFrame(), isVisible(), producto, this);
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
        System.out.println("Abreviaturaunidmed "+producto.getUnidabrev());
//        jtabla.setColumnSelectionInterval(jtabla.getRowCount(), 0);
//        jtabla.requestFocus();
        
        enter();
    
    
    }
    
    
    
    public void setcliente(Cliente cliente){
        this.cliente= cliente;
         if(cliente.getTipodoc().equals("6")){
            
               jcbtipocomprobante.setSelectedItem("1 - FACTURA");
            }else {
                jcbtipocomprobante.setSelectedItem("2 - BOLETA");
            
            }
        jtfrut.setText(cliente.getRut());
        jlbldireccion.setText(cliente.getDireccion());
        jlblrazonsocial.setText(cliente.getNombre_razons());
        jlblemail.setText(cliente.getEmail());
        jlblmensaje.setText("");
    
    }
    
    public void enter(){
         
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
                                miarray[2]=(producto.getCantidad());
                                miarray[3]=fn.FormatoN(producto.getPrecio());
                                Double importe= producto.getPrecio()*producto.getCantidad();

                                miarray[4]=fn.FormatoN(importe);

                                modelo.addRow(miarray);
                                TableColumnModel columnModel = jtabla.getColumnModel();
                                columnModel.getColumn(0).setPreferredWidth(100);
                                columnModel.getColumn(1).setPreferredWidth(500);
                                columnModel.getColumn(2).setPreferredWidth(90);
                                columnModel.getColumn(3).setPreferredWidth(90);
                                columnModel.getColumn(4).setPreferredWidth(90);
                                producto.setCantidad(producto.getCantidad());
//                                producto.setIdtipoigv(1);
                                producto.setTotal(importe);
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
        panelNice1 = new org.edisoncor.gui.panel.PanelNice();
        jtfingresecodigo = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jtfrut = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jlblmensaje = new javax.swing.JLabel();
        jlblrazonsocial = new javax.swing.JLabel();
        jlbldireccion = new javax.swing.JLabel();
        jlblemail = new javax.swing.JLabel();
        jlbltotal = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jcbtipocomprobante = new javax.swing.JComboBox();
        jcbmoneda = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jlbltipocambio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jcbtipoop = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jtfcondicionp = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jckdetraccion = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jtfordencompra = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jtfplacavehiculo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jtfguiaserie = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtablaguia = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jcbtipoguia = new javax.swing.JComboBox<>();
        jbtnagregarguia = new javax.swing.JButton();
        jbtnretirarguia = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jbtncerrar = new javax.swing.JButton();
        jprogres = new javax.swing.JProgressBar();
        jbtnbuscar = new javax.swing.JButton();
        jtfvender = new javax.swing.JButton();
        jbtnretirar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

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
        panelNice1.add(jtfingresecodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 850, 30));

        jtabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Cantidad", "Precio", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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

        panelNice1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 890, 410));

        jPanel2.add(panelNice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 88, 890, 510));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setBackground(new java.awt.Color(255, 51, 51));
        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText("Datos Cliente: RUC / DNI");
        jPanel8.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        jtfrut.setBackground(new java.awt.Color(255, 255, 255));
        jtfrut.setForeground(new java.awt.Color(34, 75, 139));
        jtfrut.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
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
        jPanel8.add(jtfrut, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 320, 30));

        jlblmensaje.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jlblmensaje.setText("VARIOS - VENTAS MENORES A S/.700.00 Y OTROS");
        jPanel8.add(jlblmensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 266, -1));

        jlblrazonsocial.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jlblrazonsocial.setText("Razón Social");
        jPanel8.add(jlblrazonsocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, -1));

        jlbldireccion.setText("Dirección");
        jPanel8.add(jlbldireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, -1));

        jlblemail.setText("Email");
        jPanel8.add(jlblemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, -1, -1));

        jlbltotal.setBackground(new java.awt.Color(34, 75, 139));
        jlbltotal.setFont(new java.awt.Font("Dialog", 0, 48)); // NOI18N
        jlbltotal.setForeground(new java.awt.Color(255, 255, 255));
        jlbltotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sale250x50.png"))); // NOI18N
        jlbltotal.setText("* * *");
        jlbltotal.setOpaque(true);
        jPanel8.add(jlbltotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 356, 60));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setText("Comprobante:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 20));

        jcbtipocomprobante.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jcbtipocomprobante.setEnabled(false);
        jPanel1.add(jcbtipocomprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 220, -1));

        jcbmoneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbmonedaActionPerformed(evt);
            }
        });
        jPanel1.add(jcbmoneda, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 220, -1));

        jLabel1.setText("Moneda:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 50, 20));

        jlbltipocambio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jlbltipocambioKeyTyped(evt);
            }
        });
        jPanel1.add(jlbltipocambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 70, 70, -1));

        jLabel6.setText("Tipo operación:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jPanel1.add(jcbtipoop, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 320, -1));

        jLabel7.setText("Condición de pago:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jtfcondicionp.setToolTipText("Ejemplo: 15 días");
        jPanel1.add(jtfcondicionp, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 220, -1));

        jLabel17.setText("Tipo de cambio:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jckdetraccion.setBackground(new java.awt.Color(255, 255, 255));
        jckdetraccion.setText("Detracción");
        jPanel1.add(jckdetraccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 90, -1));

        jTabbedPane1.addTab("General", jPanel1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setText("Órden de compra/servicio: ");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));
        jPanel4.add(jtfordencompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 170, -1));

        jLabel12.setText("Placa de vehículo:");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jtfplacavehiculo.setToolTipText("Venta de combustible o mantenimiento");
        jPanel4.add(jtfplacavehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 170, -1));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel13.setText("Guía de remisión:");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jtfguiaserie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfguiaserieKeyReleased(evt);
            }
        });
        jPanel4.add(jtfguiaserie, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 130, -1));

        jtablaguia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Serie - Numero", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtablaguia);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 300, 90));

        jLabel14.setText("Serie - N°:");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel15.setText("Tipo:");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jPanel4.add(jcbtipoguia, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 270, -1));

        jbtnagregarguia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add20X20.png"))); // NOI18N
        jbtnagregarguia.setToolTipText("Agregar Guia");
        jbtnagregarguia.setBorderPainted(false);
        jbtnagregarguia.setContentAreaFilled(false);
        jbtnagregarguia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnagregarguiaActionPerformed(evt);
            }
        });
        jPanel4.add(jbtnagregarguia, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, -1, -1));

        jbtnretirarguia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/minus20x20.png"))); // NOI18N
        jbtnretirarguia.setToolTipText("Retirar Guia");
        jbtnretirarguia.setBorderPainted(false);
        jbtnretirarguia.setContentAreaFilled(false);
        jbtnretirarguia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnretirarguiaActionPerformed(evt);
            }
        });
        jPanel4.add(jbtnretirarguia, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, -1));

        jLabel16.setText("Lista de Guias:");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jScrollPane3.setViewportView(jPanel4);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Avanzado", jPanel3);

        jPanel8.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 340, -1));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, 520));

        jPanel7.setBackground(new java.awt.Color(34, 75, 139));
        jPanel7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel7MouseDragged(evt);
            }
        });
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel7MousePressed(evt);
            }
        });
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/barcode240x40.png"))); // NOI18N
        jLabel11.setText("NUEVA VENTA");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 14, -1, -1));

        jbtncerrar.setBackground(new java.awt.Color(255, 255, 255));
        jbtncerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar.png"))); // NOI18N
        jbtncerrar.setBorderPainted(false);
        jbtncerrar.setContentAreaFilled(false);
        jbtncerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncerrarActionPerformed(evt);
            }
        });
        jPanel7.add(jbtncerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 10, 40, -1));

        jprogres.setBorderPainted(false);
        jprogres.setPreferredSize(new java.awt.Dimension(148, 9));
        jprogres.setString("");
        jPanel7.add(jprogres, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, 65, 1470, 7));

        jbtnbuscar.setBackground(new java.awt.Color(34, 75, 139));
        jbtnbuscar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Searchx32.png"))); // NOI18N
        jbtnbuscar.setMnemonic('b');
        jbtnbuscar.setText("Buscar Producto");
        jbtnbuscar.setToolTipText(" Buscar (Alt + b)");
        jbtnbuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtnbuscar.setBorderPainted(false);
        jbtnbuscar.setContentAreaFilled(false);
        jbtnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscarActionPerformed(evt);
            }
        });
        jPanel7.add(jbtnbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 140, 40));

        jtfvender.setBackground(new java.awt.Color(34, 75, 139));
        jtfvender.setForeground(new java.awt.Color(255, 255, 255));
        jtfvender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/accept2.png"))); // NOI18N
        jtfvender.setMnemonic('s');
        jtfvender.setText("Guardar");
        jtfvender.setToolTipText("Alt + s");
        jtfvender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtfvender.setBorderPainted(false);
        jtfvender.setContentAreaFilled(false);
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
        jPanel7.add(jtfvender, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 139, 41));

        jbtnretirar.setBackground(new java.awt.Color(34, 75, 139));
        jbtnretirar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnretirar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/minus20x20.png"))); // NOI18N
        jbtnretirar.setText("Retirar");
        jbtnretirar.setToolTipText("Supr");
        jbtnretirar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtnretirar.setBorderPainted(false);
        jbtnretirar.setContentAreaFilled(false);
        jbtnretirar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnretirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnretirarActionPerformed(evt);
            }
        });
        jPanel7.add(jbtnretirar, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 130, 40));

        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Alt + s");
        jPanel7.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, -1, -1));

        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("Alt + b");
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 50, -1, -1));

        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("Alt + c");
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 50, -1, -1));

        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setMnemonic('c');
        jButton1.setText("Cliente");
        jButton1.setToolTipText("Alt + c");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 20, -1, -1));

        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("Alt + supr");
        jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 50, -1, -1));

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1287, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
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
       
    }//GEN-LAST:event_jtfrutFocusGained

    private void jtfrutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfrutFocusLost
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jtfrutFocusLost

    private void jtfrutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfrutKeyReleased
        // TODO add your handling code here:
     
        
           if(evt.getKeyCode()==113){
           jtfvender.doClick();
        
        }
     
               
    }//GEN-LAST:event_jtfrutKeyReleased

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
        
        cliente=daocliente.buscarclientevent(rut,jlblmensaje);
        if(cliente==null){
            jlblrazonsocial.setText("Razón Social");
            jlbldireccion.setText("Dirección");
            jlblemail.setText("Email");
         if(JOptionPane.showConfirmDialog(null,"Nuevo cliente identificado desea registrarlo","",JOptionPane.YES_NO_CANCEL_OPTION)==JOptionPane.YES_OPTION)
         {
            JDNuevoCliente ncliente= new JDNuevoCliente(new JFrame(), isVisible(),rut,this,menu);
            ncliente.setVisible(true);
         }
        
             
             
        }else {
            
            if(cliente.getTipodoc().equals("6")){
            
               jcbtipocomprobante.setSelectedItem("1 - FACTURA");
            }else {
                jcbtipocomprobante.setSelectedItem("2 - BOLETA");
            
            }
            jlblrazonsocial.setText("R.S: "+cliente.getNombre_razons());
            jlbldireccion.setText("Dirección: "+cliente.getDireccion());
            jlblemail.setText("Email: "+cliente.getEmail());
        }
    }//GEN-LAST:event_jtfrutActionPerformed

    private void jbtnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnbuscarActionPerformed
        // TODO add your handling code here:
         JDBuscarProductoVenta buscarproducto = new JDBuscarProductoVenta(new JFrame(),
                    isVisible(),this,listprod);
            buscarproducto.setVisible(true);
    }//GEN-LAST:event_jbtnbuscarActionPerformed

    private void panelNice1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNice1MousePressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_panelNice1MousePressed

    private void panelNice1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNice1MouseDragged
        // TODO add your handling code here:
       
    }//GEN-LAST:event_panelNice1MouseDragged

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        // TODO add your handling code here:
//           posy=evt.getY();
//        posx=evt.getX();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        // TODO add your handling code here:
//         int xp=evt.getXOnScreen() - posx;
//        int yp=evt.getYOnScreen() - posy;
//        this.setLocation(xp, yp);
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
   
           
         
    }//GEN-LAST:event_jtablaKeyReleased

    private void jtfingresecodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfingresecodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfingresecodigoActionPerformed

    private void jcbmonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbmonedaActionPerformed
        // TODO add your handling code here:
        calculatotal();
        
    }//GEN-LAST:event_jcbmonedaActionPerformed

    private void jbtncerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncerrarActionPerformed
        // TODO add your handling code here:
          if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar la ventana?","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){

            //                    daoproducto.devolverstockrequerido(listprod,jlblmensajeproceso,this);

            this.dispose();

        }
    }//GEN-LAST:event_jbtncerrarActionPerformed

    private void jtfvenderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfvenderMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_jtfvenderMouseExited

    private void jtfvenderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfvenderMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jtfvenderMousePressed

    private void jtfvenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfvenderActionPerformed
        // TODO add your handling code here:
           if(listprod.size()>0 ){
               boolean valida =  true;
            Tipo_Comprobante tc= listcomprobante.get(jcbtipocomprobante.getSelectedIndex());
//            if(jcbtipocomprobante.getSelectedIndex()!=0){
                if(cliente!=null)
                {
                    if(calculatotal()>700 && cliente.getTipodoc().equals("-")){
                        JOptionPane.showMessageDialog(null, "Se debe identificar al beneficiario, la entidad no puede ser VARIOS por ser una venta mayor a S/700.00","",JOptionPane.ERROR_MESSAGE);
                    }else {
                    
                    
                 
                    if(errorventa==false)
                    {
                
             
                        if (JOptionPane.showConfirmDialog(null, "Seguro de realizar la venta","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
                        {

        //              if(idcliente ==0 && !jtfrut.getText().equals("R.U.T CLIENTE") && !jtfcliente.getText().equals("NOMBRE CLIENTE")
        //                      && !jtfrut.getText().equals("") && !jtfcliente.getText().equals("") ){ //VALIDO BUSQUEDA DE CLIENTE
        //                    cliente.setRut(jtfrut.getText());
        //                    cliente.setNombre_razons(jtfcliente.getText().trim().toUpperCase());
        //                    idcliente=daocliente.insertarcliente(cliente);
        //                }
                      ////////////////////    ///////////////////////////  nuevo hilo ////////////////
                        Runnable runnable=new Runnable() {

                            @Override
                            public void run() {
                            boolean validacliente=false;
                            jprogres.setVisible(true);
                            jprogres.setMinimum(0);
                            jprogres.setMaximum(50);
                            jprogres.setStringPainted(true);
                                int i = 0;
                            while(i<=50)
                            {
                                 jprogres.setValue(i);
                                 
                             
                                if(i==25)
                                {
                                 Moneda moneda= listmoneda.get(jcbmoneda.getSelectedIndex());
                                Tipo_Comprobante tipocomp= listcomprobante.get(jcbtipocomprobante.getSelectedIndex());
                                Sunat_Transaction tipoop= listtipoop.get(jcbtipoop.getSelectedIndex());
                                Double tipoc=0.0;
                                if(!jlbltipocambio.getText().replaceAll("\\s", "").equals("")){
                                    tipoc=Double.parseDouble(jlbltipocambio.getText());
                                }
        //                        venta.setIdempleado(user.getIdempleado());
                                venta.setId_sucursal(sucursalsingleton.getId());
                                venta.setDescuento(0.0);
                                venta.setIdmoneda(moneda.getId());
                                venta.setTipocambio(tipoc);
                                venta.setIdcomprobante(tipocomp.getId());
                                venta.setTotal(calculatotal());
                                venta.setSerie("");
                                venta.setNumero("");
                                venta.setDocref("");
                                
                                /////////////////////////
                                venta.setIdsunattrans(tipoop.getId());
                                venta.setCondicionpago(jtfcondicionp.getText().toUpperCase());
                                venta.setTipocambio(Double.parseDouble(jlbltipocambio.getText()));
                                venta.setOrdencompras(jtfordencompra.getText().toUpperCase());
                                venta.setPlacavehiculo(jtfplacavehiculo.getText().toUpperCase());
                                venta.setDetraccion(jckdetraccion.isSelected());
            //                venta.setMotivodescuento(jtamotivodesc.getText().toUpperCase());
                                if(cliente!=null){
                                 venta.setIdcliente(cliente.getId_cliente());
                                 validacliente=true; 
                //                 venta.setIdventa(daoventa.insertar(venta));
                                }

        //                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                VentasDAO daoventa= new VentasDAO();
//                                jlblcargarventa.setVisible(true);
                                long id =daoventa.insertar(venta,listprod,listguiatipo,validacliente,"venta","Ventas");
                                if(id!=0)
                                {
                                    JOptionPane.showMessageDialog(null,"Cod.: v"+id,"",JOptionPane.INFORMATION_MESSAGE);
                                    newventa();
                                }else {
                                    JOptionPane.showMessageDialog(null, "Error al registrar venta","",JOptionPane.ERROR_MESSAGE);
                                }
//                                jlblcargarventa.setVisible(false);
                                
                            
                            
                            }
                            i++;
                            }
                           jprogres.setVisible(false);
                            }
                        };
                        
                        
                        
                        Thread t = new Thread(runnable);
                        t.start();




                     //   daoventa.imprimirticketcaja(venta.getIdventa());

            
            
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "Ingrese valores validos en los productos","",JOptionPane.ERROR_MESSAGE);
                        
                    }
                    
                    }
                }else {
                     JOptionPane.showMessageDialog(null, "Ingrese datos del cliente ó 0000 (VARIOS) ","",JOptionPane.ERROR_MESSAGE);
                     jtfrut.selectAll();
                     jtfrut.requestFocus();
                }
            
//            }else {
//                   JOptionPane.showMessageDialog(null, "Seleccione tipo de comprobante","",JOptionPane.ERROR_MESSAGE);
//                  
//                   jcbtipocomprobante.requestFocus();
//            }
     
       
          
         
        
        }else{
            JOptionPane.showMessageDialog(null, "Ingrese productos","",JOptionPane.ERROR_MESSAGE);
            jtfingresecodigo.selectAll();
            jtfingresecodigo.requestFocus();
            
        }
       
    }//GEN-LAST:event_jtfvenderActionPerformed

    private void jPanel7MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseDragged
        // TODO add your handling code here:
        int xp=evt.getXOnScreen() - posx;
        int yp=evt.getYOnScreen() - posy;
        this.setLocation(xp, yp);
    }//GEN-LAST:event_jPanel7MouseDragged

    private void jPanel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MousePressed
        // TODO add your handling code here:
        posy=evt.getY();
        posx=evt.getX();
    }//GEN-LAST:event_jPanel7MousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jtfrut.requestFocus();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbtnagregarguiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnagregarguiaActionPerformed
        // TODO add your handling code here:
       
        String serienum = jtfguiaserie.getText().replaceAll("\\s", "");
        GuiaTipo guia;
        Ident_Guia identguia;
        
        if(!serienum.equals("")){
           
            identguia = listidetguia.get(jcbtipoguia.getSelectedIndex());
            DefaultTableModel modelguia =(DefaultTableModel) jtablaguia.getModel();
            guia = new GuiaTipo();
           String dato[]  = new String[2];
           dato[0] = serienum;
           dato[1] = identguia.getDescripcion();
           guia.setSerienumero(serienum);
           guia.setGuiatipo(identguia.getDescripcion());
           guia.setIdentguia(identguia.getOp());
           modelguia.addRow(dato);
           listguiatipo.add(guia);
           
           jtfguiaserie.setText("");
           jtfguiaserie.requestFocus();
           
                  
           
           
           
            
        
        }else {
            JOptionPane.showMessageDialog(null, "Ingrese serie - numero de la guia");
            jtfguiaserie.selectAll();
            jtfguiaserie.requestFocus();
        
        }
    }//GEN-LAST:event_jbtnagregarguiaActionPerformed

    private void jbtnretirarguiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnretirarguiaActionPerformed
        // TODO add your handling code here:
        
        int cont = jtablaguia.getSelectedRow();
        if(cont>=0){
            if(JOptionPane.showConfirmDialog(null,"Seguro que desea retirar la guia","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            DefaultTableModel tablaguia= (DefaultTableModel)jtablaguia.getModel();
            tablaguia.removeRow(cont);
            listguiatipo.remove(cont);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione item");
        }
        
        
    }//GEN-LAST:event_jbtnretirarguiaActionPerformed

    private void jtfguiaserieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfguiaserieKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            jbtnagregarguia.doClick();
        }
    }//GEN-LAST:event_jtfguiaserieKeyReleased

    private void jlbltipocambioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlbltipocambioKeyTyped
        // TODO add your handling code here:
           char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9')) 
        && (caracter != KeyEvent.VK_BACK_SPACE)
        && (caracter != '.' || jlbltipocambio.getText().contains(".")) ) {
            evt.consume();
        }
    }//GEN-LAST:event_jlbltipocambioKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbtnagregarguia;
    private javax.swing.JButton jbtnbuscar;
    private javax.swing.JButton jbtncerrar;
    private javax.swing.JButton jbtnretirar;
    private javax.swing.JButton jbtnretirarguia;
    private javax.swing.JComboBox jcbmoneda;
    private javax.swing.JComboBox jcbtipocomprobante;
    private javax.swing.JComboBox<String> jcbtipoguia;
    private javax.swing.JComboBox<String> jcbtipoop;
    private javax.swing.JCheckBox jckdetraccion;
    private javax.swing.JLabel jlbldireccion;
    private javax.swing.JLabel jlblemail;
    private javax.swing.JLabel jlblmensaje;
    private javax.swing.JLabel jlblrazonsocial;
    private javax.swing.JTextField jlbltipocambio;
    private javax.swing.JLabel jlbltotal;
    private javax.swing.JProgressBar jprogres;
    private javax.swing.JTable jtabla;
    private javax.swing.JTable jtablaguia;
    private javax.swing.JTextField jtfcondicionp;
    private javax.swing.JTextField jtfguiaserie;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jtfingresecodigo;
    private javax.swing.JTextField jtfordencompra;
    private javax.swing.JTextField jtfplacavehiculo;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jtfrut;
    private javax.swing.JButton jtfvender;
    private org.edisoncor.gui.panel.PanelNice panelNice1;
    private org.edisoncor.gui.util.WindowsUtil windowsUtil1;
    // End of variables declaration//GEN-END:variables
}
