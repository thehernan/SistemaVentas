/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.ComprasDAO;
import DAO.DetalleComprasDAO;
import DAO.ProductoDAO;
import Pojos.Compras;
import Pojos.DetalleCompras;
import Pojos.Producto;
import Pojos.Proveedor;

import Pojos.SucursalSingleton;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;


import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

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
    
      DecimalFormat nf = new DecimalFormat("#.00");
      
      
    public JIFIngresoProducto(){
        initComponents();
//        daoproducto.llenarcombobox(jcbdescripcion,sucursalsingleton.getId());
//        AutoCompleteDecorator.decorate(jcbdescripcion);
        String titulos[]={"CODIGO","DESCRIPCION","CANT. LLEGO","CANT. ACORDADA","PRECIO","IMPORTE"};
        modelo.setColumnIdentifiers(titulos);
        jtabla.setModel(modelo);
        TableColumnModel columnModel = jtabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(450);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(50);
        columnModel.getColumn(5).setPreferredWidth(50);

//        
        bloqueajbtn(false, false);
        setvisibleabono(false, false);
        jtfabono.setValue(0);
        jdatapicker.setDate(new Date());
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
    jtfprecio.setValue(0);
    jtfcantidad.setValue(0);
    jlblimporte.setValue(0);
    jtfstock.setValue(0);
    jtfcantidadacordada.setValue(0);
    jlblimagen.setIcon(null);
    producto = new Producto();
    }
    public void calculaimporte(){
        try {
            double cantidad = Double.parseDouble(jtfcantidad.getValue().toString());
            double precio = Double.parseDouble(jtfprecio.getValue().toString());
        
            double importe= cantidad*precio;
            jlblimporte.setValue(importe);
        } catch (Exception e) {
             System.err.println("exception calculaimporte"+e);
        }
        
    }
    public void calculatotal(){
        Double total=0.0,subtotal=0.0,iva;
        
        for (DetalleCompras det : listadet){
         subtotal = subtotal + det.getImporte();
        } 
        iva= subtotal*0.18;
        total = subtotal+iva;
              
        jlbltotal.setValue(total);
        jlblsubtotal.setValue(subtotal);
        jlbliva.setValue(iva);
    }
    public void validaingresar(){
//        boolean validacant= validacantidad();
        try {
             if (producto.getIdproducto()!=0 && Double.parseDouble(jtfprecio.getValue().toString())>0
                && Double.parseDouble(jtfcantidad.getValue().toString())>0 && 
                     Double.parseDouble(jtfcantidadacordada.getValue().toString()) > 0 )//&& validacant==true)
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
   
    modelo=daocompras.buscar(jtabla, id,jtfproveedor,jtfrut,compras,listadet ); //,jlbltotal,jlblsubtotal,jlbliva);
    System.out.println("lsitaa"+listadet.size());
    jdatapicker.setDate(compras.getFecha());
    jcbdocumento.setSelectedItem(compras.getDocumento());
    jtfnumero.setText(compras.getNumero());
    jcbtipopago.setSelectedItem(compras.getTipopago());
    jtfabono.setValue(compras.getAbono());
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
        NumberFormat nf = NumberFormat.getInstance();
//        Object[] miarray = new Object[8];
//        miarray[0]=det.getIddetallecompra();
//        miarray[1]=det.getIdproducto();
//        miarray[2]=cod;
//        miarray[3]=des;
//        miarray[4]=formatea.format(det.getCantidad());
//        miarray[5]= formatea.format(det.getCantidadacord());
//        miarray[6]=formatea.format(det.getPrecio());
//        miarray[7]=formatea.format(impor);
    
    modelo.setValueAt(nf.format(det.getCantidad()), index, 2);
    modelo.setValueAt(nf.format(det.getCantidadacord()), index, 3);
    modelo.setValueAt(nf.format(det.getPrecio()), index, 4);
    modelo.setValueAt(nf.format(det.getImporte()), index, 5);
    calculatotal();
    validaguardar();
    }
    public void nuevo(){
        modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
    //          if (column == 5 ) return true;
    //          else if(column==6) return true;
    //          else if (column==7) return true;
    //          else 
          return false;
    }
    };
        String titulos[]={"CODIGO","DESCRIPCION","CANTIDAD LLEGO","CANTIDAD ACORDADA","PRECIO","IMPORTE"};
        modelo.setColumnIdentifiers(titulos);
        jtabla.setModel(modelo);
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
        jdatapicker.setDate(null);
        jtfproveedor.setText("PROVEEDOR");
        jtfrut.setText("RUT");
        jcbdocumento.setSelectedItem("FACTURA");
        jcbtipopago.setSelectedItem("CONTADO");
        jtfabono.setValue(0);
        jtfnumero.setText("NUMERO");
        calculatotal();
        jbtnguadar.setEnabled(false);
    
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
        jLabel1 = new javax.swing.JLabel();
        jtfproveedor = new javax.swing.JTextField();
        jbtnbuscarproveedor = new javax.swing.JButton();
        jtfrut = new javax.swing.JTextField();
        jcbtipopago = new javax.swing.JComboBox();
        jbtnguadar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jtfnumero = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jcbdocumento = new javax.swing.JComboBox();
        jbtnbuscar = new javax.swing.JButton();
        jdatapicker = new org.jdesktop.swingx.JXDatePicker();
        jtfabono = new javax.swing.JFormattedTextField();
        jlblabono = new javax.swing.JLabel();
        jlblmensajeabono = new javax.swing.JLabel();
        jlblsubtotal = new javax.swing.JFormattedTextField();
        jlbliva = new javax.swing.JFormattedTextField();
        jlbltotal = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jtfcodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfdescripcion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jtfstock = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jtfcantidad = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jtfcantidadacordada = new javax.swing.JFormattedTextField();
        jtfprecio = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jlblimagen = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlblimporte = new javax.swing.JFormattedTextField();
        jbtningresar = new javax.swing.JButton();
        jlblmsjcantidad = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();
        jlblmensaje = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jbtnsalir = new javax.swing.JButton();
        jbtnbuscardetalle = new javax.swing.JButton();

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

        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setPreferredSize(new java.awt.Dimension(1168, 671));

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

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel1.setText("Fecha:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 20));

        jtfproveedor.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfproveedor.setText("PROVEEDOR");
        jtfproveedor.setEnabled(false);
        jtfproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfproveedorActionPerformed(evt);
            }
        });
        jPanel1.add(jtfproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 320, -1));

        jbtnbuscarproveedor.setBackground(new java.awt.Color(255, 255, 255));
        jbtnbuscarproveedor.setText("...");
        jbtnbuscarproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscarproveedorActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnbuscarproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, -1, -1));

        jtfrut.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jtfrut.setText("R.U.T");
        jtfrut.setEnabled(false);
        jPanel1.add(jtfrut, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 150, -1));

        jcbtipopago.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jcbtipopago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CONTADO", "CREDITO" }));
        jcbtipopago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbtipopagoActionPerformed(evt);
            }
        });
        jPanel1.add(jcbtipopago, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 200, -1));

        jbtnguadar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnguadar.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jbtnguadar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save20x20.png"))); // NOI18N
        jbtnguadar.setText("Guardar");
        jbtnguadar.setToolTipText("Guardar");
        jbtnguadar.setBorder(null);
        jbtnguadar.setBorderPainted(false);
        jbtnguadar.setContentAreaFilled(false);
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
        jPanel1.add(jbtnguadar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, -1, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jLabel4.setText("SUBTOTAL :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 550, -1, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jLabel5.setText("I.G.V (18 %):");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 580, -1, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jLabel6.setText("TOTAL:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 610, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
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
        jPanel2.add(jtfnumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 160, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Nº");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 20));

        jcbdocumento.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jcbdocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FACTURA", "BOLETA", "GUIA" }));
        jcbdocumento.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jcbdocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbdocumentoActionPerformed(evt);
            }
        });
        jPanel2.add(jcbdocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, -1));

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
        jPanel2.add(jbtnbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 40, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 70, 250, 70));

        jdatapicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdatapickerActionPerformed(evt);
            }
        });
        jPanel1.add(jdatapicker, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 150, -1));

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
        jPanel1.add(jtfabono, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 200, -1));

        jlblabono.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblabono.setText("Abono:");
        jPanel1.add(jlblabono, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, 20));

        jlblmensajeabono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlblmensajeabono.setForeground(new java.awt.Color(255, 51, 51));
        jPanel1.add(jlblmensajeabono, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 119, 260, 20));

        jlblsubtotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jlblsubtotal.setCaretColor(new java.awt.Color(255, 51, 51));
        jlblsubtotal.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        jlblsubtotal.setEnabled(false);
        jPanel1.add(jlblsubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 550, 470, -1));

        jlbliva.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jlbliva.setCaretColor(new java.awt.Color(255, 51, 51));
        jlbliva.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        jlbliva.setEnabled(false);
        jPanel1.add(jlbliva, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 580, 470, -1));

        jlbltotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jlbltotal.setCaretColor(new java.awt.Color(255, 51, 51));
        jlbltotal.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        jlbltotal.setEnabled(false);
        jPanel1.add(jlbltotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 610, 470, -1));

        jPanel3.setBackground(new java.awt.Color(238, 238, 238));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRODUCTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Light", 0, 12))); // NOI18N
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
        jPanel3.add(jtfcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 21, 310, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel2.setText("Descripcion:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 51, -1, 20));

        jtfdescripcion.setEditable(false);
        jtfdescripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(jtfdescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 51, 880, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel8.setText("Stock:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 21, -1, 20));

        jtfstock.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtfstock.setEnabled(false);
        jPanel3.add(jtfstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 21, 240, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel9.setText("Cantidad Lllego:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 86, -1, 20));

        jtfcantidad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtfcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfcantidadKeyReleased(evt);
            }
        });
        jPanel3.add(jtfcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 86, 90, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel10.setText("Cantidad acordada:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 86, -1, 20));

        jtfcantidadacordada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtfcantidadacordada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfcantidadacordadaKeyReleased(evt);
            }
        });
        jPanel3.add(jtfcantidadacordada, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, 86, 110, -1));

        jtfprecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jtfprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfprecioKeyReleased(evt);
            }
        });
        jPanel3.add(jtfprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(467, 86, 120, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel11.setText("Precio");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 86, -1, 20));

        jlblimagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(jlblimagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 20, 150, 90));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel3.setText("Importe:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(597, 86, -1, 20));

        jlblimporte.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jlblimporte.setCaretColor(new java.awt.Color(255, 51, 51));
        jlblimporte.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        jlblimporte.setEnabled(false);
        jPanel3.add(jlblimporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(651, 86, 130, -1));

        jbtningresar.setBackground(new java.awt.Color(255, 255, 255));
        jbtningresar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtningresar.setText("INGRESAR");
        jbtningresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtningresarActionPerformed(evt);
            }
        });
        jPanel3.add(jbtningresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(801, 86, -1, -1));

        jlblmsjcantidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlblmsjcantidad.setForeground(new java.awt.Color(255, 51, 51));
        jPanel3.add(jlblmsjcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 124, 360, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 1140, 120));

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
        jtabla.getTableHeader().setReorderingAllowed(false);
        jtabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtablaMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jtabla);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 1140, 230));

        jlblmensaje.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblmensaje.setForeground(new java.awt.Color(255, 51, 51));
        jPanel1.add(jlblmensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 370, 20));

        jPanel4.setBackground(new java.awt.Color(220, 151, 96));
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
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 834, Short.MAX_VALUE)
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

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, -1));

        jbtnbuscardetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Searchx32.png"))); // NOI18N
        jbtnbuscardetalle.setText("Buscar detalle");
        jbtnbuscardetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscardetalleActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnbuscardetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfproveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfproveedorActionPerformed

    private void jcbdocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbdocumentoActionPerformed
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jcbdocumentoActionPerformed

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
      
        Double cantidad=Double.parseDouble(jtfcantidad.getValue().toString());
        Double cantidaacor= Double.parseDouble(jtfcantidadacordada.getValue().toString());
        Double precio= Double.parseDouble(jtfprecio.getValue().toString());
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
        miarray[2]=nf.format(jtfcantidad.getValue());
        miarray[3]= nf.format(jtfcantidadacordada.getValue());
        miarray[4]=nf.format(jtfprecio.getValue());
        miarray[5]=nf.format(jlblimporte.getValue());
        
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
   if (JOptionPane.showConfirmDialog(null, "DOCUMENTO CONFORME","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){  
      
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
        compras.setDocumento(jcbdocumento.getSelectedItem().toString());
        compras.setNumero(jtfnumero.getText().trim());
        System.out.println("idprov"+compras.getId_proveedor());
        compras.setId_proveedor(compras.getId_proveedor());
        compras.setFecha(new java.sql.Date(jdatapicker.getDate().getTime()));
        compras.setId_sucursal(sucursalsingleton.getId());
        compras.setTipopago(jcbtipopago.getSelectedItem().toString());
//        compras.setId_proveedor(proveedor.getIdproveedor());
        compras.setAbono(abono);
        compras.setEstado(estado);
        
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

    private void jtfcantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcantidadKeyReleased
        // TODO add your handling code here:
        validaingresar();
        calculaimporte();
    }//GEN-LAST:event_jtfcantidadKeyReleased

    private void jtfcantidadacordadaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcantidadacordadaKeyReleased
        // TODO add your handling code here:
        validaingresar();
    }//GEN-LAST:event_jtfcantidadacordadaKeyReleased

    private void jtfprecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfprecioKeyReleased
        // TODO add your handling code here:
        validaingresar();
        calculaimporte();
    }//GEN-LAST:event_jtfprecioKeyReleased

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
         
    }//GEN-LAST:event_jtfcodigoKeyPressed

    private void jbtnbuscardetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnbuscardetalleActionPerformed
        // TODO add your handling code here:
        JDFBuscarDetalleIngresoProductos bdip=new JDFBuscarDetalleIngresoProductos(new JFrame(), isVisible(),jtabla);
        bdip.setVisible(true);
    }//GEN-LAST:event_jbtnbuscardetalleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtnbuscar;
    private javax.swing.JButton jbtnbuscardetalle;
    private javax.swing.JButton jbtnbuscarproveedor;
    private javax.swing.JButton jbtnguadar;
    private javax.swing.JButton jbtningresar;
    private javax.swing.JButton jbtnsalir;
    private javax.swing.JComboBox jcbdocumento;
    private javax.swing.JComboBox jcbtipopago;
    private org.jdesktop.swingx.JXDatePicker jdatapicker;
    private javax.swing.JLabel jlblabono;
    private javax.swing.JLabel jlblimagen;
    private javax.swing.JFormattedTextField jlblimporte;
    private javax.swing.JFormattedTextField jlbliva;
    private javax.swing.JLabel jlblmensaje;
    private javax.swing.JLabel jlblmensajeabono;
    private javax.swing.JLabel jlblmsjcantidad;
    private javax.swing.JFormattedTextField jlblsubtotal;
    private javax.swing.JFormattedTextField jlbltotal;
    private javax.swing.JTable jtabla;
    private javax.swing.JFormattedTextField jtfabono;
    private javax.swing.JFormattedTextField jtfcantidad;
    private javax.swing.JFormattedTextField jtfcantidadacordada;
    private javax.swing.JTextField jtfcodigo;
    private javax.swing.JTextField jtfdescripcion;
    private javax.swing.JTextField jtfnumero;
    private javax.swing.JFormattedTextField jtfprecio;
    private javax.swing.JTextField jtfproveedor;
    private javax.swing.JTextField jtfrut;
    private javax.swing.JFormattedTextField jtfstock;
    // End of variables declaration//GEN-END:variables
}
