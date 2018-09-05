/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.FormatoNumerico;
import DAO.ComprasDAO;
import DAO.DetalleComprasDAO;
import DAO.MonedaDAO;
import DAO.ProductoDAO;
import DAO.TipoComprobanteDAO;
import Pojos.Compras;
import Pojos.DetalleCompras;
import Pojos.Moneda;
import Pojos.Producto;
import Pojos.Proveedor;

import Pojos.SucursalSingleton;
import Pojos.Tipo_Comprobante;
import java.awt.Frame;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
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
public class JIFIngresoProducto extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFIngresoProducto
     */
//    Proveedor proveedor=new Proveedor();
    
    ProductoDAO daoproducto = new ProductoDAO();
    Producto producto=new Producto();
    ComprasDAO  daocompras = new ComprasDAO();
    Compras compras =new Compras();
    DetalleComprasDAO daodetcompra=new DetalleComprasDAO();
    int cont=0;
//    DetalleCompras[] miarray1 = new DetalleCompras[5];
    List<DetalleCompras> listadet = new ArrayList<DetalleCompras>();
    DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
//          if (column == 5 ) return true;
//          else if(column==6) return true;
//          else if (column==7) return true;
//          else 
          return false;
}
  };      
    SucursalSingleton sucursalsingleton = SucursalSingleton.getinstancia();
    boolean editar = false;
//    Proveedor proveedor;
     int posx;
    int posy;
    
    FormatoNumerico fn = new FormatoNumerico();
      
   List<Tipo_Comprobante> listcomprobante;   
   List<Moneda> listmoneda;
    public JIFIngresoProducto(){
        initComponents();
//        daoproducto.llenarcombobox(jcbdescripcion,sucursalsingleton.getId());
//        AutoCompleteDecorator.decorate(jcbdescripcion);
        String titulos[]={"Codigo","Descripción","Cant. Llego","Cant. Acordada","Precio","Importe"};
        modelo.setColumnIdentifiers(titulos);
        jtabla.setModel(modelo);
        TableColumnModel columnModel = jtabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(120);
        columnModel.getColumn(1).setPreferredWidth(450);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(5).setPreferredWidth(100);

//        
        bloqueajbtn(false, false);
        setvisibleabono(false, false);
        jtfabono.setValue(0);
        jdatapicker.setDate(new Date());
        mostracomprobante();
        mostrarmoneda();
    }
    
    public void mostracomprobante(){
        TipoComprobanteDAO comprobanteDAO = new TipoComprobanteDAO();
        
     
        
        listcomprobante = comprobanteDAO.mostrar();
        for(Tipo_Comprobante comprobante: listcomprobante){
            if(comprobante.getOp()==1 || comprobante.getOp()==2)
            {
                jcbtipocomprobante.addItem(comprobante.getOp()+" - "+comprobante.getComprobante());
               
            
            
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
            jlbltipocambio.setText("T.C.: "+m.getTipo_cambio());
            }
            
        
        }
    }
    public void bloqueajbtn(boolean guardar,boolean ingresar){
    jbtnguadar.setEnabled(guardar);
    jbtningresar.setEnabled(ingresar);
    
    }
    public void setvisibleabono(boolean labelabono, boolean abono){
        jlblabono.setVisible(labelabono);
        jtfabono.setVisible(abono);
    }
    
    public void setproveedor(Proveedor proveedor){
//    this.proveedor=proveedor;
  
//    jtfproveedor.setText(proveedor.getNombrerazons());
//    jtfrut.setText(proveedor.getRut());
   compras.setId_proveedor(proveedor.getIdproveedor());
    jtfproveedor.setText(proveedor.getNombrerazons());
    jtfrut.setText(proveedor.getRut());
            
    }
    public void limpiarjtf(){
    jtfcodigo.setText("CODIGO");
    jtfdescripcion.setText("");
    jtfprecio.setText("");
    jtfcantidad.setText("");
    jlblimporte.setText("");
    jtfstock.setValue(0);
    jtfcantidadacordada.setText("");
    jlblimagen.setIcon(null);
    producto = new Producto();
    }
    public void calculaimporte(){
        try {
            double cantidad = Double.parseDouble(jtfcantidad.getText());
            double precio = Double.parseDouble(jtfprecio.getText());
        
            double importe= cantidad*precio;
            jlblimporte.setText(fn.FormatoN(importe));
        } catch (Exception e) {
             System.err.println("exception calculaimporte"+e);
        }
        
    }
    public void calculatotal(){
        Double total=0.0,subtotal=0.0,iva;
        Moneda m = listmoneda.get(jcbmoneda.getSelectedIndex());
        if(listadet.size()>0)
        {
            for (DetalleCompras det : listadet){
            total = total + det.getImporte();
           } 

           subtotal =total/1.18;
           iva= total-subtotal;
           jlbltotal.setText(m.getSimbolo()+" "+fn.FormatoN(total));
           jlblsubtotal.setText("Subtotal: "+fn.FormatoN(subtotal));
           jlbliva.setText("I.G.V 18%: "+fn.FormatoN(iva));
        
        
        }else {
            jlbltotal.setText("* * *");
            jlblsubtotal.setText("* * *");
            jlbliva.setText("* * *");
        
        }
        
    }
    public void validaingresar(){
//        boolean validacant= validacantidad();
        try {
             if (producto.getIdproducto()!=0 && Double.parseDouble(jtfprecio.getText())>0
                && Double.parseDouble(jtfcantidad.getText())>0 && 
                     Double.parseDouble(jtfcantidadacordada.getText()) > 0 )//&& validacant==true)
             {
                jbtningresar.setEnabled(true);
        
             }else{
                jbtningresar.setEnabled(false);
                  }
        } catch (Exception e) {
            jbtningresar.setEnabled(false);
       
           
            System.err.println("exception validaingresar"+e);
        }
       
    
    }
    
    
    
//    public boolean validacantidad(){
//        boolean valida;
//        if(Double.parseDouble(jtfcantidad.getValue().toString()) > Double.parseDouble(jtfcantidadacordada.getValue().toString())){
//            valida=false;
//            jlblmsjcantidad.setText("LA CANTIDAD NO DEBE SER MAYOR A LA CANTIDAD ACORDADA");
//        }else {
//            valida=true;
//            jlblmsjcantidad.setText("");
//        }
//    return valida;
//    }
    public boolean validaabono(){
         boolean valida=false;
         Double total=0.0;
        try {
           
            double abono=Double.parseDouble(jtfabono.getValue().toString());
            for (DetalleCompras det : listadet){
            total = total + det.getImporte();
           } 
            System.out.println("abono"+abono);
            if(abono<=total && abono>=0){
                valida=true;

            }else {
                jlblabono.setText("INGRESE UN ABONO VALIDO");
            }
            
        } catch (Exception e) {
            valida=false;
            System.err.println("exception valida abono"+e);
        }
        
    return valida;
    }
    
    public void validaguardar(){
        System.out.println("date"+jdatapicker.getDate());
        boolean valida= validaabono();
        try {
            if (compras.getId_proveedor()!=0 && jtfnumero.getText().length()>0 && !jtfnumero.getText().equals("NUMERO")
                && listadet.size()>0 && jdatapicker.getDate()!=null && valida==true ){
            jbtnguadar.setEnabled(true);
        
            }else { jbtnguadar.setEnabled(false);}
        } catch (Exception e) {
            jbtnguadar.setEnabled(false);
            System.out.println("se produjo una exception"+ e);
        }
        
    
    }
    public void setbuscar(long id){      
    compras=new Compras();
    listadet = new ArrayList<>();
    modelo=daocompras.buscar(jtabla, id,compras,listadet ); //,jlbltotal,jlblsubtotal,jlbliva);
    jtfproveedor.setText(compras.getProveedor());
    jtfrut.setText(compras.getDocprov());
    System.out.println("lsitaa"+listadet.size());
    jdatapicker.setDate(compras.getFecha());
    jcbtipocomprobante.setSelectedItem(compras.getDocumento());
    jtfnumero.setText(compras.getNumero());
    jcbtipopago.setSelectedItem(compras.getTipopago());
    jtfabono.setValue(compras.getAbono());
    jcbtipocomprobante.setSelectedItem(compras.getComprobante());
    jcbmoneda.setSelectedItem(compras.getMoneda());
    jlbltipocambio.setText(String.valueOf(compras.getTipocambio()));
    
    editar = true ;
    calculatotal();
    
    }
    public void seteliminar(DetalleCompras detc){
        boolean valida;
    int index=jtabla.getSelectedRow();
//               long iddet= Long.parseLong(jtabla.getValueAt(index, 0).toString());
//               long idprod = Long.parseLong(jtabla.getValueAt(index, 1).toString());
//               System.out.println("idde"+iddet+"idpro"+idprod);
            
               if (detc.getIddetallecompra()!= 0){
                  
                valida=daodetcompra.eliminar(detc.getIddetallecompra(), detc.getIdproducto());
                if(valida==true){
                modelo.removeRow(index);
                listadet.remove(index);
                
                }
               }else {
                modelo.removeRow(index);
               listadet.remove(index);
               }
               calculatotal();
               validaguardar();
    
    
    }
    public void seteditar(DetalleCompras det){
    int index=jtabla.getSelectedRow();
    listadet.set(index, det);
      
//        Object[] miarray = new Object[8];
//        miarray[0]=det.getIddetallecompra();
//        miarray[1]=det.getIdproducto();
//        miarray[2]=cod;
//        miarray[3]=des;
//        miarray[4]=formatea.format(det.getCantidad());
//        miarray[5]= formatea.format(det.getCantidadacord());
//        miarray[6]=formatea.format(det.getPrecio());
//        miarray[7]=formatea.format(impor);
    
    modelo.setValueAt(fn.FormatoN(det.getCantidad()), index, 2);
    modelo.setValueAt(fn.FormatoN(det.getCantidadacord()), index, 3);
    modelo.setValueAt(fn.FormatoN(det.getPrecio()), index, 4);
    modelo.setValueAt(fn.FormatoN(det.getImporte()), index, 5);
    calculatotal();
    validaguardar();
    }
    public void nuevo(){
        //////////nuevo modelo //////////////////
        for (int i = 0; i < jtabla.getRowCount(); i++) {
        modelo.removeRow(i);
        i-=1;
        }
//        jtabla.getColumnModel().getColumn(0).setMaxWidth(0);
//        jtabla.getColumnModel().getColumn(0).setMinWidth(0);
//        jtabla.getColumnModel().getColumn(0).setPreferredWidth(0);
//        
//        jtabla.getColumnModel().getColumn(1).setMaxWidth(0);
//        jtabla.getColumnModel().getColumn(1).setMinWidth(0);
//        jtabla.getColumnModel().getColumn(1).setPreferredWidth(0);
        compras= new Compras();
        listadet = new ArrayList<DetalleCompras>();
        producto = new Producto();
        jdatapicker.setDate(new Date());
        
        jtfproveedor.setText("PROVEEDOR");
        jtfrut.setText("Doc.");
        
        jcbtipopago.setSelectedItem("CONTADO");
        jtfabono.setValue(0);
        jtfnumero.setText("NUMERO");
        
        jbtnguadar.setEnabled(false);
        calculatotal();
    }     
    public void buscarprod (String codigo)
    {
         producto=daoproducto.buscarproducto("CODIGO", 0,sucursalsingleton.getId(), codigo);
            
        /// mostrar imagen ///
        ImageIcon imageIcon = new ImageIcon(this.producto.getFoto());
        //ImageIcon imageUser = imageIcon;
        Image img = imageIcon.getImage();
        Image newimg = img.getScaledInstance(jlblimagen.getWidth(), jlblimagen.getHeight(), java.awt.Image.SCALE_AREA_AVERAGING);
        imageIcon = new ImageIcon(newimg);
        jlblimagen.setIcon(imageIcon);   
        //////////////////////////////////////////////
           // jtfcodigo.setText(producto.getCodigo());
        jtfstock.setValue((producto.getCantidad()));
        jtfdescripcion.setText(producto.getDescripcion());

//        }
        validaingresar();
    
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jtfcodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfdescripcion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jtfstock = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jlblimagen = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jbtningresar = new javax.swing.JButton();
        jlblmsjcantidad = new javax.swing.JLabel();
        jtfprecio = new javax.swing.JTextField();
        jlblimporte = new javax.swing.JTextField();
        jtfcantidad = new javax.swing.JTextField();
        jtfcantidadacordada = new javax.swing.JTextField();
        jbtnbuscarproducto = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();
        jlblmensaje = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jbtnsalir = new javax.swing.JButton();
        jbtnbuscardetalle = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jtfnumero = new javax.swing.JTextField();
        jcbtipocomprobante = new javax.swing.JComboBox();
        jbtnbuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jdatapicker = new org.jdesktop.swingx.JXDatePicker();
        jtfproveedor = new javax.swing.JTextField();
        jbtnbuscarproveedor = new javax.swing.JButton();
        jtfrut = new javax.swing.JTextField();
        jcbtipopago = new javax.swing.JComboBox();
        jlblabono = new javax.swing.JLabel();
        jtfabono = new javax.swing.JFormattedTextField();
        jlblmensajeabono = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jcbmoneda = new javax.swing.JComboBox();
        jlbltipocambio = new javax.swing.JLabel();
        jbtnguadar = new javax.swing.JButton();
        jlblsubtotal = new javax.swing.JLabel();
        jlbliva = new javax.swing.JLabel();
        jlbltotal = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(77, 161, 227)));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1170, 600));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel3.setForeground(new java.awt.Color(255, 255, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfcodigo.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfcodigo.setText("CODIGO");
        jtfcodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfcodigoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfcodigoFocusLost(evt);
            }
        });
        jtfcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfcodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfcodigoKeyReleased(evt);
            }
        });
        jPanel3.add(jtfcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 430, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel2.setText("Descripcion:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 51, -1, 20));

        jtfdescripcion.setEditable(false);
        jtfdescripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(jtfdescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 51, 610, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel8.setText("Stock:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, -1, 20));

        jtfstock.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtfstock.setEnabled(false);
        jPanel3.add(jtfstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 140, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel9.setText("Cant. Llego:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 86, -1, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel10.setText("Cant. acordada:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, -1, 20));

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel11.setText("Precio:");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, -1, 20));
        jPanel3.add(jlblimagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 20, 150, 90));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel3.setText("Importe:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, 20));

        jbtningresar.setBackground(new java.awt.Color(255, 255, 255));
        jbtningresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/addproduct20x20.png"))); // NOI18N
        jbtningresar.setText("Agregar");
        jbtningresar.setToolTipText("");
        jbtningresar.setBorderPainted(false);
        jbtningresar.setContentAreaFilled(false);
        jbtningresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtningresarActionPerformed(evt);
            }
        });
        jPanel3.add(jbtningresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 80, -1, -1));

        jlblmsjcantidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlblmsjcantidad.setForeground(new java.awt.Color(255, 51, 51));
        jPanel3.add(jlblmsjcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 124, 360, 20));

        jtfprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfprecioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfprecioKeyReleased(evt);
            }
        });
        jPanel3.add(jtfprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 90, -1));

        jlblimporte.setEnabled(false);
        jPanel3.add(jlblimporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, 80, -1));

        jtfcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfcantidadKeyReleased(evt);
            }
        });
        jPanel3.add(jtfcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 70, -1));

        jtfcantidadacordada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfcantidadacordadaKeyReleased(evt);
            }
        });
        jPanel3.add(jtfcantidadacordada, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 70, -1));

        jbtnbuscarproducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Searchx32.png"))); // NOI18N
        jbtnbuscarproducto.setBorderPainted(false);
        jbtnbuscarproducto.setContentAreaFilled(false);
        jbtnbuscarproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscarproductoActionPerformed(evt);
            }
        });
        jPanel3.add(jbtnbuscarproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 50, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 780, 120));

        jtabla.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
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
        jtabla.getTableHeader().setReorderingAllowed(false);
        jtabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtablaMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jtabla);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, 780, 310));

        jlblmensaje.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblmensaje.setForeground(new java.awt.Color(255, 51, 51));
        jPanel1.add(jlblmensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 370, 20));

        jPanel4.setBackground(new java.awt.Color(238, 238, 238));
        jPanel4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel4MouseDragged(evt);
            }
        });
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel4MousePressed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel12.setText("INGRESO DE PRODUCTOS");

        jbtnsalir.setBackground(new java.awt.Color(255, 255, 255));
        jbtnsalir.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jbtnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrarblanco.png"))); // NOI18N
        jbtnsalir.setToolTipText("Salir");
        jbtnsalir.setBorder(null);
        jbtnsalir.setBorderPainted(false);
        jbtnsalir.setContentAreaFilled(false);
        jbtnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnsalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 924, Short.MAX_VALUE)
                .addComponent(jbtnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(15, 15, 15))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, -1));

        jbtnbuscardetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Searchx32.png"))); // NOI18N
        jbtnbuscardetalle.setText("Buscar detalle");
        jbtnbuscardetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscardetalleActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnbuscardetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel5MouseDragged(evt);
            }
        });
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel5MousePressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfnumero.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfnumero.setText("NUMERO");
        jtfnumero.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfnumeroFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfnumeroFocusLost(evt);
            }
        });
        jtfnumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfnumeroKeyReleased(evt);
            }
        });
        jPanel2.add(jtfnumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 290, -1));

        jcbtipocomprobante.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jcbtipocomprobante.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jcbtipocomprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbtipocomprobanteActionPerformed(evt);
            }
        });
        jPanel2.add(jcbtipocomprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, -1));

        jbtnbuscar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Searchx32.png"))); // NOI18N
        jbtnbuscar.setToolTipText("Buscar Documento");
        jbtnbuscar.setBorder(null);
        jbtnbuscar.setBorderPainted(false);
        jbtnbuscar.setContentAreaFilled(false);
        jbtnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscarActionPerformed(evt);
            }
        });
        jPanel2.add(jbtnbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 40, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel1.setText("Fecha:");

        jdatapicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdatapickerActionPerformed(evt);
            }
        });

        jtfproveedor.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfproveedor.setText("PROVEEDOR");
        jtfproveedor.setEnabled(false);
        jtfproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfproveedorActionPerformed(evt);
            }
        });

        jbtnbuscarproveedor.setBackground(new java.awt.Color(255, 255, 255));
        jbtnbuscarproveedor.setText("...");
        jbtnbuscarproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscarproveedorActionPerformed(evt);
            }
        });

        jtfrut.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jtfrut.setText("Doc.");
        jtfrut.setEnabled(false);

        jcbtipopago.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jcbtipopago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CONTADO", "CREDITO" }));
        jcbtipopago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbtipopagoActionPerformed(evt);
            }
        });

        jlblabono.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblabono.setText("Abono:");

        jtfabono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtfabono.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfabono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfabonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfabonoKeyTyped(evt);
            }
        });

        jlblmensajeabono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlblmensajeabono.setForeground(new java.awt.Color(255, 51, 51));

        jLabel4.setText("Moneda:");

        jcbmoneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbmonedaActionPerformed(evt);
            }
        });

        jlbltipocambio.setText("jLabel5");

        jbtnguadar.setBackground(new java.awt.Color(77, 161, 227));
        jbtnguadar.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jbtnguadar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnguadar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/accept2.png"))); // NOI18N
        jbtnguadar.setText("Guardar");
        jbtnguadar.setToolTipText("");
        jbtnguadar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbtnguadar.setBorderPainted(false);
        jbtnguadar.setContentAreaFilled(false);
        jbtnguadar.setOpaque(true);
        jbtnguadar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnguadarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jbtnguadarMousePressed(evt);
            }
        });
        jbtnguadar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnguadarActionPerformed(evt);
            }
        });

        jlblsubtotal.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jlblsubtotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlblsubtotal.setText("* * *");

        jlbliva.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jlbliva.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbliva.setText("* * *");

        jlbltotal.setBackground(new java.awt.Color(244, 155, 22));
        jlbltotal.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jlbltotal.setForeground(new java.awt.Color(255, 255, 255));
        jlbltotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbltotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/compra50x50.png"))); // NOI18N
        jlbltotal.setText("* * *");
        jlbltotal.setOpaque(true);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbltotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnguadar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jtfproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnbuscarproveedor))
                            .addComponent(jtfrut, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbtipopago, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jlblabono)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfabono, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlblmensajeabono, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbmoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlbltipocambio)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdatapicker, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jlblsubtotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                                .addComponent(jlbliva, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jlbltotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jlbliva)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdatapicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnbuscarproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfrut, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbtipopago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlblabono, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtfabono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlblmensajeabono, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbmoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlbltipocambio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnguadar, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 420, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1250, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfproveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfproveedorActionPerformed

    private void jcbtipocomprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbtipocomprobanteActionPerformed
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jcbtipocomprobanteActionPerformed

    private void jbtnbuscarproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnbuscarproveedorActionPerformed
        // TODO add your handling code here:
        JDBuscarProveedor Bproveedor= new JDBuscarProveedor(new java.awt.Frame(), isVisible(),this);
        Bproveedor.setVisible(true);
    }//GEN-LAST:event_jbtnbuscarproveedorActionPerformed

    private void jtfcodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcodigoFocusGained
        // TODO add your handling code here:
        if(jtfcodigo.getText().equals("CODIGO")){
            jtfcodigo.setText("");
        
        }
    }//GEN-LAST:event_jtfcodigoFocusGained

    private void jtfcodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcodigoFocusLost
        // TODO add your handling code here:
         if(jtfcodigo.getText().equals("")){
            jtfcodigo.setText("CODIGO");
        
        }
    }//GEN-LAST:event_jtfcodigoFocusLost

    private void jtfnumeroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnumeroFocusGained
        // TODO add your handling code here:
       if(jtfnumero.getText().equals("NUMERO")){
           jtfnumero.setText("");
       
       
       }
    }//GEN-LAST:event_jtfnumeroFocusGained

    private void jtfnumeroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnumeroFocusLost
        // TODO add your handling code here:
        if(jtfnumero.getText().equals("")){
            jtfnumero.setText("NUMERO");
        
        }
    }//GEN-LAST:event_jtfnumeroFocusLost

    private void jbtningresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtningresarActionPerformed
        // TODO add your handling code here:
        int index= jtabla.getSelectedRow();
        //long id = Long.parseLong(jtabla.getValueAt(index,0).toString());
      
        Double cantidad=Double.parseDouble(jtfcantidad.getText());
        Double cantidaacor= Double.parseDouble(jtfcantidadacordada.getText());
        Double precio= Double.parseDouble(jtfprecio.getText());
//        Double importe= Double.parseDouble(jlblimporte.getValue().toString());
              
        DetalleCompras detcompra= new DetalleCompras();
       // List<Object> ejeoLista = new ArrayList<Object>();
       // modelo.(producto.getIdproducto(), 0, 0);
        detcompra.setIddetallecompra(0);
        detcompra.setIdproducto(producto.getIdproducto());
        detcompra.setPrecio(precio);
        detcompra.setCantidad(cantidad);
        detcompra.setCantidadacord(cantidaacor);
        detcompra.setImporte(precio*cantidad);
        
        listadet.add(detcompra);
       
        Object[] miarray = new Object[6];
        
    
        miarray[0]=producto.getCodigo();
        miarray[1]=producto.getDescripcion();
        miarray[2]=fn.FormatoN(Double.parseDouble(jtfcantidad.getText()));
        miarray[3]= fn.FormatoN(Double.parseDouble(jtfcantidadacordada.getText()));
        miarray[4]=fn.FormatoN(Double.parseDouble(jtfprecio.getText()));
        miarray[5]=fn.FormatoN(Double.parseDouble(jlblimporte.getText()));
        
        modelo.addRow(miarray);
        calculatotal();
        limpiarjtf();
        validaguardar();
        jbtningresar.setEnabled(false);
        jtfcodigo.requestFocus();
//        jtabla.setValueAt(descrip, 0, 2);
//        jtabla.setValueAt(cantidad, 0, 3);
//        jtabla.setValueAt(precio, 0, 4);
    }//GEN-LAST:event_jbtningresarActionPerformed

    private void jbtnguadarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnguadarActionPerformed
               // TODO add your handling code here:
   if (JOptionPane.showConfirmDialog(null, "Documento conforme","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){  
      
       Double total=0.0;
       String estado;
       double abono=Double.parseDouble(jtfabono.getValue().toString());
       for (DetalleCompras det : listadet){
         total = total + det.getImporte();
        } 
       if(jcbtipopago.getSelectedIndex()==1){
           if(abono==total){
               estado= "CANCELADO";
           }else {
               estado= "DEBE";
           
           }
       }else {
           estado= "CANCELADO";
       }
       
       Tipo_Comprobante  tc= listcomprobante.get(jcbtipocomprobante.getSelectedIndex());
       Moneda mon = listmoneda.get(jcbmoneda.getSelectedIndex());
        compras.setDocumento(jcbtipocomprobante.getSelectedItem().toString());
        compras.setNumero(jtfnumero.getText().trim());
        System.out.println("idprov"+compras.getId_proveedor());
        compras.setId_proveedor(compras.getId_proveedor());
        compras.setFecha(new java.sql.Date(jdatapicker.getDate().getTime()));
        compras.setId_sucursal(sucursalsingleton.getId());
        compras.setTipopago(jcbtipopago.getSelectedItem().toString());
//        compras.setId_proveedor(proveedor.getIdproveedor());
        compras.setAbono(abono);
        compras.setEstado(estado);
        compras.setIdcomprobant(tc.getId());
        compras.setIdmoneda(mon.getId());
        compras.setTipocambio(mon.getTipo_cambio());
         if(compras.getId_compra() == 0){
            long idcompra=daocompras.insertar(compras);
            System.out.println("idc"+idcompra);
            daodetcompra.insertar(listadet,idcompra,jlblmensaje);    
       }else {
           daodetcompra.insertar(listadet,compras.getId_compra(),jlblmensaje);          
           daocompras.editar(compras);
       
       } 
         nuevo();
    }
    jlblmensaje.setText("");
    }//GEN-LAST:event_jbtnguadarActionPerformed

    private void jtfcodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcodigoKeyReleased
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_jtfcodigoKeyReleased

    private void jcbtipopagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbtipopagoActionPerformed
        // TODO add your handling code here:
        if(jcbtipopago.getSelectedIndex()==0){
            setvisibleabono(false, false);
            jtfabono.setValue(0);
        }else { if( jcbtipopago.getSelectedIndex()==1){
            setvisibleabono(true,true);
        }
        
        
        }
        validaguardar();
    }//GEN-LAST:event_jcbtipopagoActionPerformed

    private void jbtnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnsalirActionPerformed
        // TODO add your handling code here:
    if(JOptionPane.showConfirmDialog(null,"Desea salir del ingreso de productos","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
       this.dispose();
    }   
    }//GEN-LAST:event_jbtnsalirActionPerformed

    private void jtfnumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfnumeroKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfnumeroKeyReleased

    private void jtfabonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfabonoKeyReleased
        // TODO add your handling code here:
    validaguardar();
      
    }//GEN-LAST:event_jtfabonoKeyReleased

    private void jdatapickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdatapickerActionPerformed
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jdatapickerActionPerformed

    private void jbtnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnbuscarActionPerformed
        // TODO add your handling code here:
        JDBuscarIngresoProducto buscaringreso = new JDBuscarIngresoProducto(new Frame(), isVisible(),this);
        buscaringreso.setVisible(true);
    }//GEN-LAST:event_jbtnbuscarActionPerformed

    private void jtablaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaMouseReleased
        // TODO add your handling code here:
             int index = jtabla.getSelectedRow();
        if(index>=0){
            if (evt.getClickCount()==2){
           String cod= jtabla.getValueAt(index, 0).toString();
           String descrip = jtabla.getValueAt(index, 1).toString();
           
          JDEditarEliminarDetalleCompra eedetcomp = new JDEditarEliminarDetalleCompra(new Frame(),isVisible()
          ,listadet.get(index),cod,descrip,this);
          eedetcomp.setVisible(true);
        }
        
        }
        
    }//GEN-LAST:event_jtablaMouseReleased

    private void jtfabonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfabonoKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jtfabonoKeyTyped

    private void jbtnguadarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnguadarMousePressed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jbtnguadarMousePressed

    private void jbtnguadarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnguadarMouseExited
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jbtnguadarMouseExited

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseDragged
        // TODO add your handling code here:
        int xp=evt.getXOnScreen() - posx;
        int yp=evt.getYOnScreen() - posy;
        this.setLocation(xp, yp);
    }//GEN-LAST:event_jPanel4MouseDragged

    private void jPanel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MousePressed
        // TODO add your handling code here:
        posy=evt.getY();
        posx=evt.getX();

    }//GEN-LAST:event_jPanel4MousePressed

    private void jtfcodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcodigoKeyPressed
        // TODO add your handling code here:}
        
        if (evt.getKeyCode()==10){
            String codigo= jtfcodigo.getText().trim();
//        if(codigo.length()>=10){
            buscarprod(codigo);
         
           
        
        
        }
         
    }//GEN-LAST:event_jtfcodigoKeyPressed

    private void jbtnbuscardetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnbuscardetalleActionPerformed
        // TODO add your handling code here:
        JDFBuscarDetalleIngresoProductos bdip=new JDFBuscarDetalleIngresoProductos(new JFrame(), isVisible(),jtabla);
        bdip.setVisible(true);
    }//GEN-LAST:event_jbtnbuscardetalleActionPerformed

    private void jPanel5MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5MouseDragged

    private void jPanel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5MousePressed

    private void jtfprecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfprecioKeyReleased
        // TODO add your handling code here:
        validaingresar();
        calculaimporte();
    }//GEN-LAST:event_jtfprecioKeyReleased

    private void jtfprecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfprecioKeyPressed
        // TODO add your handling code here:
//         validaingresar();
//        calculaimporte();
    }//GEN-LAST:event_jtfprecioKeyPressed

    private void jcbmonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbmonedaActionPerformed
        // TODO add your handling code here:
        calculatotal();
    }//GEN-LAST:event_jcbmonedaActionPerformed

    private void jtfcantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcantidadKeyReleased
        // TODO add your handling code here:
          validaingresar();
        calculaimporte();
    }//GEN-LAST:event_jtfcantidadKeyReleased

    private void jtfcantidadacordadaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcantidadacordadaKeyReleased
        // TODO add your handling code here:
         validaingresar();
    }//GEN-LAST:event_jtfcantidadacordadaKeyReleased

    private void jbtnbuscarproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnbuscarproductoActionPerformed
        // TODO add your handling code here:
        JDBuscarProductoVenta bpb = new JDBuscarProductoVenta(new JFrame(), isVisible(),this);
        bpb.setVisible(true);
        
    }//GEN-LAST:event_jbtnbuscarproductoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtnbuscar;
    private javax.swing.JButton jbtnbuscardetalle;
    private javax.swing.JButton jbtnbuscarproducto;
    private javax.swing.JButton jbtnbuscarproveedor;
    private javax.swing.JButton jbtnguadar;
    private javax.swing.JButton jbtningresar;
    private javax.swing.JButton jbtnsalir;
    private javax.swing.JComboBox jcbmoneda;
    private javax.swing.JComboBox jcbtipocomprobante;
    private javax.swing.JComboBox jcbtipopago;
    private org.jdesktop.swingx.JXDatePicker jdatapicker;
    private javax.swing.JLabel jlblabono;
    private javax.swing.JLabel jlblimagen;
    private javax.swing.JTextField jlblimporte;
    private javax.swing.JLabel jlbliva;
    private javax.swing.JLabel jlblmensaje;
    private javax.swing.JLabel jlblmensajeabono;
    private javax.swing.JLabel jlblmsjcantidad;
    private javax.swing.JLabel jlblsubtotal;
    private javax.swing.JLabel jlbltipocambio;
    private javax.swing.JLabel jlbltotal;
    private javax.swing.JTable jtabla;
    private javax.swing.JFormattedTextField jtfabono;
    private javax.swing.JTextField jtfcantidad;
    private javax.swing.JTextField jtfcantidadacordada;
    private javax.swing.JTextField jtfcodigo;
    private javax.swing.JTextField jtfdescripcion;
    private javax.swing.JTextField jtfnumero;
    private javax.swing.JTextField jtfprecio;
    private javax.swing.JTextField jtfproveedor;
    private javax.swing.JTextField jtfrut;
    private javax.swing.JFormattedTextField jtfstock;
    // End of variables declaration//GEN-END:variables
}
