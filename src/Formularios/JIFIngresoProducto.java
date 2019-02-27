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
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Date;
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
    boolean error;
//    DetalleCompras[] miarray1 = new DetalleCompras[5];
    List<DetalleCompras> listadet = new ArrayList<DetalleCompras>();
    DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
          if (column == 2 ) return true;
          else if(column==3) return true;
//          else if (column==7) return true;
          else 
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
        String titulos[]={"Codigo","Descripción","Cantidad","Precio","Importe"};
        modelo.setColumnIdentifiers(titulos);
        jtabla.setModel(modelo);
        TableColumnModel columnModel = jtabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(120);
        columnModel.getColumn(1).setPreferredWidth(400);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(100);
    

//        
        bloqueajbtn(false, false);
        setvisibleabono(false, false);
        jtfabono.setValue(0);
        jdatapicker.setDate(new Date());
        mostracomprobante();
        mostrarmoneda();
        jckincluyeigv.setSelected(true);
        anadeListenerAlModelo(jtabla);
        error=false;
    }
    
        private void anadeListenerAlModelo(JTable tabla) {
        tabla.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent evento) {
                actualizaimporte(evento);
            }
        });
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
//    jbtningresar.setEnabled(ingresar);
    
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
//    public void limpiarjtf(){
//    jtfcodigo.setText("");
//    jtfdescripcion.setText("");
//    jtfprecio.setText("");
//    jtfcantidad.setText("");
//    jlblimporte.setText("");
//    jtfstock.setText("");
//    jtfcantidadacordada.setText("");
//    jlblimagen.setIcon(null);
//    producto = new Producto();
//    }
//    public void calculaimporte(){
//        try {
//            double cantidad = Double.parseDouble(jtfcantidad.getText());
//            double precio = Double.parseDouble(jtfprecio.getText());
//        
//            double importe= cantidad*precio;
//            jlblimporte.setText(fn.FormatoN(importe));
//        } catch (Exception e) {
//             System.err.println("exception calculaimporte"+e);
//        }
//        
//    }
    public void calculatotal(){
        Double total=0.0,subtotal=0.0,iva;
        Moneda m = listmoneda.get(jcbmoneda.getSelectedIndex());
        if(listadet.size()>0)
        {
            for (DetalleCompras det : listadet){
            total = total + (det.getImporte());
           } 
           
            if(jckincluyeigv.isSelected()){
              subtotal =total/1.18;
              iva= total-subtotal;
              jlbltotal.setText(m.getSimbolo()+" "+fn.FormatoN(total));
              jlblsubtotal.setText("Subtotal: "+fn.FormatoN(subtotal));
              jlbliva.setText("I.G.V 18%: "+fn.FormatoN(iva));
            
            }else {
              double total1 = total + (total*0.18);
              iva= total*0.18;
              jlbltotal.setText(m.getSimbolo()+" "+fn.FormatoN(total1));
              jlblsubtotal.setText("Subtotal: "+fn.FormatoN(total));
              jlbliva.setText("I.G.V 18%: "+fn.FormatoN(iva));
                
            }
          
        
        
        }else {
            jlbltotal.setText("* * *");
            jlblsubtotal.setText("* * *");
            jlbliva.setText("* * *");
        
        }
        
    }
//    public void validaingresar(){
////        boolean validacant= validacantidad();
//        try {
//             if (producto.getIdproducto()!=0 && Double.parseDouble(jtfprecio.getText())>0
//                && Double.parseDouble(jtfcantidad.getText())>0 && 
//                     Double.parseDouble(jtfcantidadacordada.getText()) > 0 )//&& validacant==true)
//             {
//                jbtningresar.setEnabled(true);
//        
//             }else{
//                jbtningresar.setEnabled(false);
//                  }
//        } catch (Exception e) {
//            jbtningresar.setEnabled(false);
//       
//           
//            System.err.println("exception validaingresar"+e);
//        }
//       
//    
//    }
    
    
    
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
                && listadet.size()>0 && jdatapicker.getDate()!=null && valida==true && error==false){
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
    jckincluyeigv.setSelected(compras.isIncluyeigv());
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
        
        jtfproveedor.setText("");
        jtfrut.setText("");
        
        jcbtipopago.setSelectedItem("CONTADO");
        jtfabono.setValue(0);
        jtfnumero.setText("");
        
        jbtnguadar.setEnabled(false);
        jckincluyeigv.setSelected(true);
        calculatotal();
    }     
//    public void buscarprod (String codigo)
//    {
//         producto=daoproducto.buscarproducto("CODIGO", 0,sucursalsingleton.getId(), codigo);
//         
//        System.out.println("idproductobuscar"+producto.getIdproducto());
//        /// mostrar imagen ///
//        ImageIcon imageIcon = new ImageIcon(this.producto.getFoto());
//        //ImageIcon imageUser = imageIcon;
//        Image img = imageIcon.getImage();
//        Image newimg = img.getScaledInstance(jlblimagen.getWidth(), jlblimagen.getHeight(), java.awt.Image.SCALE_AREA_AVERAGING);
//        imageIcon = new ImageIcon(newimg);
//        jlblimagen.setIcon(imageIcon);   
//        //////////////////////////////////////////////
//           // jtfcodigo.setText(producto.getCodigo());
//        double stock = producto.getCantidad();
//        
//        jtfstock.setText(fn.FormatoN(stock));
//        jtfdescripcion.setText(producto.getDescripcion());
//        jlblstockunid.setText("X "+producto.getUnidmedc());
//        jlblcantllegounid.setText("X "+producto.getUnidmedc());
//        jlblcantacordunid.setText("X "+producto.getUnidmedc());
////        }
//        validaingresar();
//        
//       
//        jtfcantidad.requestFocus();
//    
//    }
//    
    public void agregar(Producto prod){
       
      
        Double cantidad=(prod.getCantidad());
        Double cantidaacor= (prod.getCantidad());
        Double precio= (prod.getPrecio());

              
        DetalleCompras detcompra= new DetalleCompras();
       // List<Object> ejeoLista = new ArrayList<Object>();
       // modelo.(producto.getIdproducto(), 0, 0);
        detcompra.setIddetallecompra(0);
        detcompra.setIdproducto(prod.getIdproducto());
        System.out.println("idproductoagregar"+prod.getIdproducto());
        detcompra.setPrecio(prod.getPrecioc());
        detcompra.setCantidad(cantidad);
        detcompra.setCantidadacord(cantidaacor);
        detcompra.setImporte(prod.getPrecioc()*cantidad);
        detcompra.setUnidmed(prod.getUnidmedc());
        
        detcompra.setPrecio1(prod.getPrecio());
        detcompra.setPrecio2(prod.getPrecio1());
        detcompra.setPrecio3(prod.getPrecio2());
        detcompra.setPrecio4(prod.getPrecio3());
        
        
        System.err.println("unimedc"+prod.getUnidmedc());
        listadet.add(detcompra);
       
        Object[] miarray = new Object[6];
        
    
        miarray[0]=prod.getCodigo();
        miarray[1]=prod.getDescripcion();
        miarray[2]=fn.FormatoN(cantidad);
        
        miarray[3]=fn.FormatoN(prod.getPrecioc());
        miarray[4]=fn.FormatoN(prod.getPrecioc()*cantidad);
        
        modelo.addRow(miarray);
        calculatotal();
//        limpiarjtf();
        validaguardar();
//        jbtningresar.setEnabled(false);
//        jtfcodigo.requestFocus();
        producto= new Producto();
    
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
           DetalleCompras prod= listadet.get(fila);
           Double precio= Double.parseDouble(modelo.getValueAt(fila, 3).toString());
           Double cantidad= Double.parseDouble(modelo.getValueAt(fila, 2).toString());

   //        boolean vprecio=true, vcantidad=true;

               System.out.println("cantidad en tabla "+cantidad);
               System.out.println("Precio en tabla "+precio);
       
        
        
        
        
        
            if(precio>0){
                prod.setPrecio(precio);

                modelo.setValueAt(fn.FormatoN(prod.getPrecio()*prod.getCantidad()), fila,4);
                prod.setImporte(precio*prod.getCantidad());

            }else {
            JOptionPane.showMessageDialog(null,"Ingrese un precio valido","",JOptionPane.ERROR_MESSAGE);
                modelo.setValueAt((fn.FormatoN(prod.getPrecio())), fila, 3);
    //            vprecio = false;

            }
            if(cantidad>0){
                prod.setCantidad(cantidad);
                prod.setImporte(prod.getPrecio()*cantidad);
                modelo.setValueAt(fn.FormatoN(prod.getPrecio()*prod.getCantidad()), fila,4);

            }else {
                JOptionPane.showMessageDialog(null,"Ingrese cantidad valida","",JOptionPane.ERROR_MESSAGE);
                modelo.setValueAt(fn.FormatoN(prod.getCantidad()), fila, 2);
                System.out.println("cantidadanterior "+prod.getCantidad());
    //            vcantidad=false;
            }

                System.out.print("cantidadenlista" +prod.getCantidad());
                
            calculatotal();

            error=false;

                System.out.print("entertabla");
    //        if(vcantidad==true && vprecio==true){
    //        jtabla.setValueAt(newimporte, index,4);
    //        
    //        }

            } catch (NumberFormatException | HeadlessException e) {
                
                jlbltotal.setText("Error");
                error=true;
            }
    
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jlblstockunid = new javax.swing.JLabel();
        jlblcantllegounid = new javax.swing.JLabel();
        jlblcantacordunid = new javax.swing.JLabel();
        jlblimagen = new javax.swing.JLabel();
        jlblmsjcantidad = new javax.swing.JLabel();
        jckincluyeigv = new javax.swing.JCheckBox();
        jbtnreitrar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jbtnsalir = new javax.swing.JButton();
        jbtnbuscarproducto = new javax.swing.JButton();
        jbtnguadar = new javax.swing.JButton();
        jbtnbuscardetalle = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jbtnbuscarproveedor = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jbtnbuscar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jtfnumero = new javax.swing.JTextField();
        jcbtipocomprobante = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jdatapicker = new org.jdesktop.swingx.JXDatePicker();
        jtfproveedor = new javax.swing.JTextField();
        jtfrut = new javax.swing.JTextField();
        jcbtipopago = new javax.swing.JComboBox();
        jlblabono = new javax.swing.JLabel();
        jtfabono = new javax.swing.JFormattedTextField();
        jlblmensajeabono = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jcbmoneda = new javax.swing.JComboBox();
        jlbltipocambio = new javax.swing.JLabel();
        jlblsubtotal = new javax.swing.JLabel();
        jlbliva = new javax.swing.JLabel();
        jlbltotal = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jlblmensaje = new javax.swing.JLabel();

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

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        jlblstockunid.setText("...");
        jPanel3.add(jlblstockunid, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, -1, 20));

        jlblcantllegounid.setText("...");
        jPanel3.add(jlblcantllegounid, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, -1, 20));

        jlblcantacordunid.setText("...");
        jPanel3.add(jlblcantacordunid, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, -1, 20));
        jPanel3.add(jlblimagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 20, 150, 90));

        jlblmsjcantidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlblmsjcantidad.setForeground(new java.awt.Color(255, 51, 51));
        jPanel3.add(jlblmsjcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 360, 20));

        jckincluyeigv.setBackground(new java.awt.Color(255, 255, 255));
        jckincluyeigv.setForeground(new java.awt.Color(255, 0, 51));
        jckincluyeigv.setText("Incluye Igv");
        jckincluyeigv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckincluyeigvActionPerformed(evt);
            }
        });
        jPanel3.add(jckincluyeigv, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jbtnreitrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/minus20x20.png"))); // NOI18N
        jbtnreitrar.setText("Retirar");
        jbtnreitrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnreitrarActionPerformed(evt);
            }
        });
        jPanel3.add(jbtnreitrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 810, 50));

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

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 810, 450));

        jPanel4.setBackground(new java.awt.Color(34, 75, 139));
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
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/shopping_40x40.png"))); // NOI18N
        jLabel12.setText("INGRESO EXISTENCIAS");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 14, -1, -1));

        jbtnsalir.setBackground(new java.awt.Color(255, 255, 255));
        jbtnsalir.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jbtnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar.png"))); // NOI18N
        jbtnsalir.setToolTipText("Salir");
        jbtnsalir.setBorder(null);
        jbtnsalir.setBorderPainted(false);
        jbtnsalir.setContentAreaFilled(false);
        jbtnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnsalirActionPerformed(evt);
            }
        });
        jPanel4.add(jbtnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1204, 14, 34, 30));

        jbtnbuscarproducto.setBackground(new java.awt.Color(34, 75, 139));
        jbtnbuscarproducto.setForeground(new java.awt.Color(255, 255, 255));
        jbtnbuscarproducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Trolley_30px.png"))); // NOI18N
        jbtnbuscarproducto.setMnemonic('b');
        jbtnbuscarproducto.setText("Buscar prod.");
        jbtnbuscarproducto.setToolTipText("Alt + b");
        jbtnbuscarproducto.setBorderPainted(false);
        jbtnbuscarproducto.setContentAreaFilled(false);
        jbtnbuscarproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscarproductoActionPerformed(evt);
            }
        });
        jPanel4.add(jbtnbuscarproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, 40));

        jbtnguadar.setBackground(new java.awt.Color(34, 75, 139));
        jbtnguadar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnguadar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/accept2.png"))); // NOI18N
        jbtnguadar.setMnemonic('s');
        jbtnguadar.setText("Guardar");
        jbtnguadar.setToolTipText("Alt + s");
        jbtnguadar.setBorderPainted(false);
        jbtnguadar.setContentAreaFilled(false);
        jbtnguadar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        jPanel4.add(jbtnguadar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 120, 40));

        jbtnbuscardetalle.setBackground(new java.awt.Color(34, 75, 139));
        jbtnbuscardetalle.setForeground(new java.awt.Color(255, 255, 255));
        jbtnbuscardetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/List_30px.png"))); // NOI18N
        jbtnbuscardetalle.setMnemonic('d');
        jbtnbuscardetalle.setText("Buscar detalle");
        jbtnbuscardetalle.setToolTipText("alt + d");
        jbtnbuscardetalle.setBorderPainted(false);
        jbtnbuscardetalle.setContentAreaFilled(false);
        jbtnbuscardetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscardetalleActionPerformed(evt);
            }
        });
        jPanel4.add(jbtnbuscardetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, -1, 40));

        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText("Alt + d");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 50, -1, -1));

        jLabel10.setForeground(new java.awt.Color(255, 51, 51));
        jLabel10.setText("Alt + s");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, -1, -1));

        jLabel13.setForeground(new java.awt.Color(255, 51, 51));
        jLabel13.setText("Alt + b");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, -1, -1));

        jbtnbuscarproveedor.setBackground(new java.awt.Color(255, 255, 255));
        jbtnbuscarproveedor.setForeground(new java.awt.Color(255, 255, 255));
        jbtnbuscarproveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/camion_30px.png"))); // NOI18N
        jbtnbuscarproveedor.setMnemonic('p');
        jbtnbuscarproveedor.setText(" Proveedor");
        jbtnbuscarproveedor.setToolTipText("");
        jbtnbuscarproveedor.setBorderPainted(false);
        jbtnbuscarproveedor.setContentAreaFilled(false);
        jbtnbuscarproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscarproveedorActionPerformed(evt);
            }
        });
        jPanel4.add(jbtnbuscarproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, -1, 40));

        jLabel14.setForeground(new java.awt.Color(255, 51, 51));
        jLabel14.setText("Alt + o");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 50, -1, -1));

        jbtnbuscar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnbuscar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/File_Preview_30px.png"))); // NOI18N
        jbtnbuscar.setMnemonic('o');
        jbtnbuscar.setText("Buscar documento");
        jbtnbuscar.setToolTipText("Buscar Documento");
        jbtnbuscar.setBorder(null);
        jbtnbuscar.setBorderPainted(false);
        jbtnbuscar.setContentAreaFilled(false);
        jbtnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscarActionPerformed(evt);
            }
        });
        jPanel4.add(jbtnbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 10, 140, 40));

        jLabel15.setForeground(new java.awt.Color(255, 51, 51));
        jLabel15.setText("Alt + p");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 50, -1, -1));

        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add20X20.png"))); // NOI18N
        jButton1.setMnemonic('n');
        jButton1.setText("Nuevo");
        jButton1.setToolTipText("alt + n");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, -1, 40));

        jLabel16.setForeground(new java.awt.Color(255, 51, 51));
        jLabel16.setText("Alt + n");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
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
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel2.add(jtfnumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 150, -1));

        jcbtipocomprobante.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jcbtipocomprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbtipocomprobanteActionPerformed(evt);
            }
        });
        jPanel2.add(jcbtipocomprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, -1));

        jLabel17.setText("Serie - Número:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jPanel5.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 188, 256, 70));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel1.setText("Fecha:");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 154, -1, 20));

        jdatapicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdatapickerActionPerformed(evt);
            }
        });
        jPanel5.add(jdatapicker, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 153, 150, -1));

        jtfproveedor.setEnabled(false);
        jtfproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfproveedorActionPerformed(evt);
            }
        });
        jPanel5.add(jtfproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 285, 295, -1));

        jtfrut.setEnabled(false);
        jtfrut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfrutActionPerformed(evt);
            }
        });
        jPanel5.add(jtfrut, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 307, 230, 20));

        jcbtipopago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CONTADO", "CREDITO" }));
        jcbtipopago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbtipopagoActionPerformed(evt);
            }
        });
        jPanel5.add(jcbtipopago, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 339, 200, -1));

        jlblabono.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblabono.setText("Abono:");
        jPanel5.add(jlblabono, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 375, -1, 20));

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
        jPanel5.add(jtfabono, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 376, 200, -1));

        jlblmensajeabono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlblmensajeabono.setForeground(new java.awt.Color(255, 51, 51));
        jPanel5.add(jlblmensajeabono, new org.netbeans.lib.awtextra.AbsoluteConstraints(278, 375, 107, 20));

        jLabel4.setText("Moneda:");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 409, -1, 20));

        jcbmoneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbmonedaActionPerformed(evt);
            }
        });
        jPanel5.add(jcbmoneda, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 407, 240, -1));

        jlbltipocambio.setText("jLabel5");
        jPanel5.add(jlbltipocambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 443, -1, -1));

        jlblsubtotal.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jlblsubtotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlblsubtotal.setText("* * *");
        jPanel5.add(jlblsubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 79, 256, 30));

        jlbliva.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jlbliva.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbliva.setText("* * *");
        jPanel5.add(jlbliva, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 110, 256, -1));

        jlbltotal.setBackground(new java.awt.Color(34, 75, 139));
        jlbltotal.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jlbltotal.setForeground(new java.awt.Color(255, 255, 255));
        jlbltotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbltotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/box50x50.png"))); // NOI18N
        jlbltotal.setText("* * *");
        jlbltotal.setOpaque(true);
        jPanel5.add(jlbltotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 3, 413, -1));

        jLabel18.setText("Proveedor:");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 264, -1, -1));

        jLabel19.setText("N° Doc:");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 309, -1, -1));

        jlblmensaje.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblmensaje.setForeground(new java.awt.Color(255, 51, 51));
        jPanel5.add(jlblmensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 370, 20));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 400, 500));

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

    private void jtfnumeroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnumeroFocusGained
        // TODO add your handling code here:
//       if(jtfnumero.getText().equals("NUMERO")){
//           jtfnumero.setText("");
//       
//       
//       }
    }//GEN-LAST:event_jtfnumeroFocusGained

    private void jtfnumeroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnumeroFocusLost
        // TODO add your handling code here:
//        if(jtfnumero.getText().equals("")){
//            jtfnumero.setText("NUMERO");
//        
//        }
    }//GEN-LAST:event_jtfnumeroFocusLost

    private void jbtnguadarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnguadarActionPerformed
               // TODO add your handling code here:
   if (JOptionPane.showConfirmDialog(null, "Documento conforme?","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){  
      
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
        compras.setIncluyeigv(jckincluyeigv.isSelected());
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
//             int index = jtabla.getSelectedRow();
//        if(index>=0){
//            if (evt.getClickCount()==2){
//           String cod= jtabla.getValueAt(index, 0).toString();
//           String descrip = jtabla.getValueAt(index, 1).toString();
//           
//          JDEditarEliminarDetalleCompra eedetcomp = new JDEditarEliminarDetalleCompra(new Frame(),isVisible()
//          ,listadet.get(index),cod,descrip,this);
//          eedetcomp.setVisible(true);
//        }
//        
//        }
//        
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

    private void jcbmonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbmonedaActionPerformed
        // TODO add your handling code here:
        calculatotal();
    }//GEN-LAST:event_jcbmonedaActionPerformed

    private void jbtnbuscarproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnbuscarproductoActionPerformed
           // TODO add your handling code here:
        JDBuscarProductoVenta bpb = new JDBuscarProductoVenta(new JFrame(), isVisible(),this);
        bpb.setVisible(true);
        
    }//GEN-LAST:event_jbtnbuscarproductoActionPerformed

    private void jckincluyeigvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckincluyeigvActionPerformed
        // TODO add your handling code here:
        
        calculatotal();
    }//GEN-LAST:event_jckincluyeigvActionPerformed

    private void jtfrutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfrutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfrutActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null,"Seguro de realizar un nuevo documento", "",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
             nuevo();
        }
   
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbtnreitrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnreitrarActionPerformed
        // TODO add your handling code here:
        
            int index = jtabla.getSelectedRow();
            if(index>=0){
                
            if(JOptionPane.showConfirmDialog(null,"Seguro que desea retirar el Item?","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){   
                
            DetalleCompras det = listadet.get(index);
            seteliminar(det);
             }
            }else {
                JOptionPane.showMessageDialog(null, "Seleccione Item","",JOptionPane.ERROR_MESSAGE);

            }
        
        
        
        
        
    }//GEN-LAST:event_jbtnreitrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JButton jbtnreitrar;
    private javax.swing.JButton jbtnsalir;
    private javax.swing.JComboBox jcbmoneda;
    private javax.swing.JComboBox jcbtipocomprobante;
    private javax.swing.JComboBox jcbtipopago;
    private javax.swing.JCheckBox jckincluyeigv;
    private org.jdesktop.swingx.JXDatePicker jdatapicker;
    private javax.swing.JLabel jlblabono;
    private javax.swing.JLabel jlblcantacordunid;
    private javax.swing.JLabel jlblcantllegounid;
    private javax.swing.JLabel jlblimagen;
    private javax.swing.JLabel jlbliva;
    private javax.swing.JLabel jlblmensaje;
    private javax.swing.JLabel jlblmensajeabono;
    private javax.swing.JLabel jlblmsjcantidad;
    private javax.swing.JLabel jlblstockunid;
    private javax.swing.JLabel jlblsubtotal;
    private javax.swing.JLabel jlbltipocambio;
    private javax.swing.JLabel jlbltotal;
    private javax.swing.JTable jtabla;
    private javax.swing.JFormattedTextField jtfabono;
    private javax.swing.JTextField jtfnumero;
    private javax.swing.JTextField jtfproveedor;
    private javax.swing.JTextField jtfrut;
    // End of variables declaration//GEN-END:variables
}
