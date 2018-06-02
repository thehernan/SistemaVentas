/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Conexion.Conexion;
import Conexion.ConexionBD;
import Pojos.DetalleVenta;
import Pojos.Producto;
import Pojos.Ventas;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author info2017
 */
public class VentasDAO {
    
     
     
     
     public String formatnumeric(Object n){
     DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
     simbolos.setDecimalSeparator('.');   
     DecimalFormat nf=new DecimalFormat("#.00",simbolos);
     String num = nf.format(n);
     
     return num;
     }
     
    public String insertar(Ventas venta,List<Producto> listdet,boolean cliente){
        ConexionBD Cbd = new ConexionBD();
        Connection cnn = Cbd.getConexion();
        try {
             cnn.setAutoCommit(false);
        } catch (SQLException e) {
        }
       PreparedStatement cabecera=null,detalle=null;
       ResultSet rscabecera=null,rsdetalle=null;
       String mensa=""; 
       boolean validacabecera=true,validadetalle=true; 
        
        
       long id=0;
   
     try{
            /////////////////// cabecera ///////////////////////////////////////
            if(cliente==true)
            {
                
                String sql=("SELECT * from sp_insertarventa(?,?,?,?,?)");         
                cabecera=Cbd.conectar().prepareStatement(sql);
                cabecera.setLong(1, venta.getIdcliente());
                cabecera.setLong(2, venta.getIdempleado());
                cabecera.setLong(3, venta.getId_sucursal());
                cabecera.setBigDecimal(4,new BigDecimal(venta.getDescuento()));
                cabecera.setString(5, venta.getMotivodescuento());
            
            }else
            {
               
                String sql=("SELECT * from sp_insertarventanocliente(?,?,?,?)");         
                cabecera= Cbd.conectar().prepareStatement(sql);

                cabecera.setLong(1, venta.getIdempleado());
                cabecera.setLong(2, venta.getId_sucursal());
                cabecera.setBigDecimal(3,new BigDecimal(venta.getDescuento()));
                cabecera.setString(4, venta.getMotivodescuento());
            
            }
          
            rscabecera= Cbd.RealizarConsulta(cabecera);
            
            if  (rscabecera.next()){
                id=(rscabecera.getLong("vidventa"));

            }
           if(id!=0)
           
           {
           /////////////// detalle /////////////////////////////////////////
            for(Producto prod :listdet ){
        //          System.out.println("SELECT * from sp_insertardetalleventa("+detventa.getIdproducto()+","+detventa.getPrecio()+","+detventa.getCantidad()+","+detventa.getIdventa()+")");
                String sqldet=("SELECT * from sp_insertardetalleventa(?,?,?,?)"); 
                detalle=Cbd.conectar().prepareStatement(sqldet);
                detalle.setLong(1, prod.getIdproducto());
                detalle.setBigDecimal(2, new BigDecimal(prod.getPrecio()));
                detalle.setBigDecimal(3, new BigDecimal(prod.getCantidad()));
                detalle.setLong(4, id);
                
                rsdetalle=Cbd.RealizarConsulta(detalle);
//                mensa="Vendiendo Cod.: "+prod.getCodigo();
               
            }
            
            while(rsdetalle.next()){
                if(rsdetalle.getBoolean("valida")==false)
                {
                    mensa="No cuenta con stock para venta del producto: "+rsdetalle.getString("mens")+" retire ó edite";
//                    cnn.rollback();
                    validadetalle=false;
//                    break;
                    
                }        
                
            }
           
           
           }else {
           
               
               validacabecera=false;
           }
           if(validacabecera==true && validadetalle==true)
           {
               cnn.commit();
           }else {
               cnn.rollback();
           }
            
            
            
            
      
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            try {
                cnn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(VentasDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            }finally{
                Cbd.desconectar();
            try {
                rscabecera.close();
                cabecera.close();
                detalle.close();
            } catch (SQLException ex) {
                Logger.getLogger(VentasDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }
  return mensa;  
}
    
public long insertarnocliente(Ventas venta){

     
     ConexionBD Cbd = new ConexionBD();   
      long id=0;
     try{
            
            System.out.println("SELECT * from sp_insertarventanocliente("+venta.getIdcliente()+","+venta.getIdempleado()+")");
            String sql=("SELECT * from sp_insertarventanocliente(?,?,?,?)");         
            PreparedStatement ps= Cbd.conectar().prepareStatement(sql);
          
            ps.setLong(1, venta.getIdempleado());
            ps.setLong(2, venta.getId_sucursal());
            ps.setBigDecimal(3,new BigDecimal(venta.getDescuento()));
            ps.setString(4, venta.getMotivodescuento());
            ResultSet rs=Cbd.RealizarConsulta(ps);
       if  (rs.next()){
           id=(rs.getLong("vidventa"));
         
        }
	rs.close();
        ps.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
               Cbd.desconectar();

}
  return id;  
}
    
public Ventas buscarventa(JTable tab,String cod,JLabel nombre,JLabel rut,
            String op){
        
     
    
    ConexionBD Cbd = new ConexionBD();
    Ventas venta= new Ventas();
    DefaultTableModel tabla=new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
       //      if (column == 5) return true;
    //else
    return false;
    }
    };
    String titulos[]={"CODIGO","PRODUCTO","CANTIDAD","PRECIO","IMPORTE"};
    tabla.setColumnIdentifiers(titulos);
  

   
            
    try{
	
        System.out.println("SELECT * from sp_busquedaventa('"+cod+"')");
        String sql=("SELECT * from sp_busquedaventa(?,?)"); 
        PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
        ps.setString(1, op);
        ps.setString(2, cod);
       
        ResultSet rs= Cbd.RealizarConsulta(ps);
        
     
        
        Object datosR[] = new Object[5];
        nombre.setText("");
        rut.setText("");
        double total =0.0,importe=0.0,iva=0.0,subtotal=0.0;
      
        while (rs.next()){
            nombre.setText(rs.getString("vnombre"));
           // System.out.println("NOMBRE: "+rs.getString("vnombre"));
            rut.setText(rs.getString("vrut"));
            venta.setIdventa(rs.getLong("vidventa"));
            venta.setFecha(rs.getDate("vfecha"));
            venta.setDescuento(rs.getDouble("vdescuento"));
             importe = rs.getDouble("vimporte");
             datosR[0] = rs.getObject("vcodigo");
              
             datosR[1] = rs.getObject("vproducto");
             
             datosR[2] = formatnumeric(rs.getObject("vcantidad"));
           
             datosR[3] = formatnumeric(rs.getObject("vprecio"));
             
             datosR[4] =formatnumeric(importe);
             total = total+ importe;
             tabla.addRow(datosR);
		
        }
        total=total-venta.getDescuento();
        subtotal = total/1.19;
        iva = total-subtotal;
        venta.setTotal(total);
        venta.setIva(iva);
        venta.setSuvtotal(subtotal);
        
        tab.setModel(tabla); 
        TableColumnModel columnModel = tab.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(500);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(50);
       
       
        ps.close();
        rs.close();
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
               Cbd.desconectar();
            }    
    
    return venta;
    
    }

public List<Ventas> mostrar(JTable tab,Timestamp desde,Timestamp hasta,JLabel msj){
     
    ConexionBD Cbd = new ConexionBD();
    DefaultTableModel tabla=new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
       //      if (column == 5) return true;
    //else
    return false;
    }
    }; 
    String titulos[]={"EMPLEADO","R.U.T","SUCURSAL","DIRECCION","IMPORTE","DESCUENTOS","TOTAL","%","COMISION"};
    tabla.setColumnIdentifiers(titulos);
    tab.setModel(tabla);
    List<Ventas> listvent=new ArrayList<>();
    DateFormat df = DateFormat.getDateInstance();
    NumberFormat nf= NumberFormat.getInstance();
    try{
	
        System.out.println("SELECT * from sp_mostrarventa('"+desde+"','"+hasta+"')");
        String sql=("SELECT * from sp_mostrarventa(?,?)"); 
        PreparedStatement ps= Cbd.conectar().prepareStatement(sql);
        ps.setTimestamp(1, desde);
        ps.setTimestamp(2, hasta);
        ResultSet rs= Cbd.RealizarConsulta(ps);
        Object datosR[] = new Object[9];
        msj.setText("");
        int cont=0;
        while (rs.next()){
           Ventas vent= new Ventas();
           vent.setId_sucursal(rs.getLong("vidsucur"));
           vent.setIdempleado(rs.getLong("videmple"));
          
           
             datosR[0] = rs.getObject("vnombre");
            
             datosR[1] = rs.getObject("vrut");
             
             datosR[2] = rs.getObject("vsucursal");
            
             datosR[3] = rs.getObject("vsucurdirec");
           
              datosR[4] =nf.format(rs.getObject("vimporte"));
            
              datosR[5] =nf.format(rs.getObject("vdescuento"));
             
             datosR[6] = nf.format(rs.getObject("vtotal"));
            
             datosR[7] = rs.getObject("vporcentaje");
            
             datosR[8] =nf.format( rs.getObject("vcomision"));
            
             listvent.add(vent);
            tabla.addRow(datosR);
		
            cont++;
        }
        
        tab.setModel(tabla); 
        TableColumnModel columnModel = tab.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(380);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(2).setPreferredWidth(250);
        columnModel.getColumn(3).setPreferredWidth(250);
        columnModel.getColumn(4).setPreferredWidth(80);
        columnModel.getColumn(5).setPreferredWidth(80);
        columnModel.getColumn(6).setPreferredWidth(80);
        columnModel.getColumn(7).setPreferredWidth(80);
        columnModel.getColumn(8).setPreferredWidth(80);
        rs.close();
        ps.close();
        if(cont == 0){
            msj.setText("NO SE ENCONTRARON REGISTROS EN EL RANGO DE "+df.format(desde)+" AL "+df.format(hasta));
        }else {
            msj.setText("REGISTROS ENCONTRADOS "+cont);
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
               Cbd.desconectar();
                       
            }    
    return listvent;
    
    }
public List<Ventas> mostrarenproceso(JTable tab,long idsucur,JLabel msj){
     
   
    ConexionBD Cbd = new ConexionBD();
    DefaultTableModel tabla=new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
       //      if (column == 5) return true;
    //else
    return false;
    }
    }; 
    String titulos[]={"COD. VENTA","EMPLEADO","IMPORTE","DESC.","TOTAL"};
    tabla.setColumnIdentifiers(titulos);
   
//    tab.getColumnModel().getColumn(0).setMaxWidth(0);
//    tab.getColumnModel().getColumn(0).setMinWidth(0);
//    tab.getColumnModel().getColumn(0).setPreferredWidth(0);
    List<Ventas> listventa=new ArrayList<>();
    try{
	
       
        double total =0.0;
        String sql=("SELECT * from sp_mostrarventaenproceso(?)"); 
        PreparedStatement ps= Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, idsucur);
       
        ResultSet rs= Cbd.RealizarConsulta(ps);
        Object datosR[] = new Object[5];
        msj.setText("");
        int cont=0;
        while (rs.next()){
            Ventas venta = new Ventas();
            venta.setIdventa(rs.getLong("vidventa"));
            venta.setCodigo(rs.getString("vcodigo"));
            venta.setTotal(rs.getDouble("vtotal"));
             datosR[0]=venta.getCodigo();
             datosR[1] = rs.getObject("vnombre");

//             datosR[2] = rs.getObject("vrut");
//
//             datosR[3] = rs.getObject("vsucursal");
//
//             datosR[4] = rs.getObject("vsucurdirec");

             datosR[2] = formatnumeric(rs.getDouble("vimporte"));

             datosR[3] = formatnumeric(rs.getDouble("vdescuento"));
             
             datosR[4] =formatnumeric(venta.getTotal());
             total= total + venta.getTotal();
             

            tabla.addRow(datosR);
            listventa.add(venta);
		
            cont++;
        }
        tab.setModel(tabla);
        TableColumnModel columnModel = tab.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(500);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(50);
        
        
        rs.close();
        ps.close();
        if(cont == 0){
            msj.setText("NO SE ENCONTRARON VENTAS EN COLA");
        }else {
            msj.setText("VENTAS EN COLA "+cont+ " - TOTAL: " +formatnumeric(total));
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
                       
            }    

    return listventa;
    }

public void extornar(long idvent){
     
   
  
    ConexionBD Cbd = new ConexionBD();
    try{
	

        String sql=("SELECT * from sp_extornarventa(?)"); 
        PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, idvent);
       
        ResultSet rs =Cbd.RealizarConsulta(ps);
        if(rs.next()){
            JOptionPane.showMessageDialog(null,"VENTA EXTORNADA CON EXITO");
        }else {
            JOptionPane.showMessageDialog(null, "LA VENTA SELECCIONADA YA SE EXTORNO O SE CONCRETO");
        
        }
        
       
        ps.close();
       
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
                       
            }    

    
    }
public void extornarconcretada(long idvent,String motivo){
     
 
    ConexionBD Cbd = new ConexionBD();
    try{
	

        String sql=("SELECT * from sp_extornarventaconcretada(?,?)"); 
        PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, idvent);
        ps.setString(2, motivo);
       
        ResultSet rs =Cbd.RealizarConsulta(ps);
        
        if(rs.next()){
            JOptionPane.showMessageDialog(null,"VENTA EXTORNADA CON EXITO");
        }else {
            JOptionPane.showMessageDialog(null, "LA VENTA SELECCIONADA YA SE EXTORNO");
        
        }
        
       
        ps.close();
       
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
               Cbd.desconectar();
                       
            }    

    
    }

public List<Ventas> busquedasensitiva(JTable tab,Timestamp desde, Timestamp hasta,JLabel jtotal,long idsucursal,
        String op,String cadena){
        
     
   
    ConexionBD Cbd = new ConexionBD();
     DefaultTableModel tabla= new DefaultTableModel(
                new String[]{"COD.","DOCUMENTO","VENDEDOR","CLIENTE","FECHA","IMPORTE","DESC.","MOT.DESC","TOTAL","ANULADA","MOT. ANU","FECHA. ANU","SUCURSAL"}, 0) {
 
            Class[] types = new Class[]{
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
                    ,java.lang.Boolean.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
            };
 
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
             public boolean isCellEditable(int row, int column) {
//        //      if (column == 5) return true;
//        //else
            return false;
            }
            };
    
    
    
        tab.setModel(tabla);
        List<Ventas> listventa = new ArrayList<>();
        boolean extor;
        double total=0.0;
        double importe=0.0;
        
       
    try{
	
        //System.out.println("SELECT * from sp_mostrarreparacion('"+cod+"')");
        String sql=("SELECT * from sp_busquedasensitivaventa(?,?,?,?,?)"); 
        PreparedStatement ps= Cbd.conectar().prepareStatement(sql);
        ps.setString(1, cadena);
        ps.setLong(2,idsucursal);
        ps.setTimestamp(3, desde);
        ps.setTimestamp(4, hasta);
        ps.setString(5, op);
        ResultSet rs= Cbd.RealizarConsulta(ps);
        Object datosR[] = new Object[13];
        
        
        while (rs.next()){
           
             Ventas venta = new Ventas();
             venta.setIdventa(rs.getLong("vidventa"));   
             
             extor= rs.getBoolean("vextornado"); 
             importe=rs.getDouble("vtotal");
             if(extor==false){
             total=total+importe;
             
             }
             datosR[0] = rs.getObject("vcodigo");

             datosR[1] = rs.getObject("vdocumento");
             datosR[2] = rs.getObject("vempleado");

             datosR[3] = rs.getObject("vcliente");

             datosR[4] = rs.getObject("vfecha");

             datosR[5] =formatnumeric(rs.getObject("vimporte"));


             datosR[6] =formatnumeric(rs.getObject("vdescuento"));
             datosR[7] =(rs.getObject("vmotivodes"));

             datosR[8] =formatnumeric(importe);
             
             datosR[9] =extor;
             datosR[10] =(rs.getObject("vmotivo"));
             datosR[11] =(rs.getObject("vfechaext"));
             datosR[12] =(rs.getObject("vsucursal"));
            tabla.addRow(datosR);
	    listventa.add(venta);
           
        }
        jtotal.setText("Total: "+formatnumeric(total));
        TableColumnModel columnModel = tab.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(2).setPreferredWidth(300);
        columnModel.getColumn(3).setPreferredWidth(300);
        columnModel.getColumn(4).setPreferredWidth(80);
        columnModel.getColumn(5).setPreferredWidth(70);
        columnModel.getColumn(6).setPreferredWidth(70);
        columnModel.getColumn(7).setPreferredWidth(250);
        columnModel.getColumn(8).setPreferredWidth(70);
        columnModel.getColumn(9).setPreferredWidth(70);
        columnModel.getColumn(10).setPreferredWidth(250);
        columnModel.getColumn(11).setPreferredWidth(70);
          columnModel.getColumn(12).setPreferredWidth(300);
	rs.close();
        ps.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
                       
            }    

    return listventa;
    }

public List<Ventas> mostrarporcliente(JTable tab,long idcliente,JLabel msj){
 
    ConexionBD Cbd = new ConexionBD();
   
    DefaultTableModel tabla= new DefaultTableModel(
                new String[]{"CODIGO","DOCUMENTO","NUMERO","CLIENTE","FECHA","IMPORTE","DESCUENTO","TOTAL","ANULADA"}, 0) {
 
            Class[] types = new Class[]{
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
                    ,java.lang.Boolean.class
            };
 
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
             public boolean isCellEditable(int row, int column) {
//        //      if (column == 5) return true;
//        //else
            return false;
            }
            };
    
//    String titulos[]={"ID","CODIGO","DOCUMENTO","NUMERO","CLIENTE","FECHA","IMPORTE","DESCUENTO","TOTAL","ANULADA"};
//    tabla.setColumnIdentifiers(titulos);
    tab.setModel(tabla);
     List<Ventas> listvent= new ArrayList<>();
    try{
	
        //System.out.println("SELECT * from sp_mostrarreparacion('"+cod+"')");
        String sql=("SELECT * from sp_mostrarventacliente(?)"); 
        PreparedStatement ps= Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, idcliente);
      
        ResultSet rs=Cbd.RealizarConsulta(ps);
        Object datosR[] = new Object[9];
        
        Integer cont = 0;
        while (rs.next()){
             Ventas venta = new Ventas();
              cont++;
            venta.setIdventa(rs.getLong("vidventa"));

//                     datosR[i] = rs.getObject("vsucursal");
//                     i++;  
             datosR[0] = rs.getObject("vcodigo");

             datosR[1] = rs.getObject("vdocumento");

             datosR[2] = rs.getObject("vnumero");

             datosR[3] = rs.getObject("vcliente");

             datosR[4] = rs.getObject("vfecha");

             datosR[5] = rs.getObject("vimporte");


             datosR[6] = rs.getObject("vdescuento");


             datosR[7] = rs.getObject("vtotal");


              datosR[8] = rs.getBoolean("vanulada");


            tabla.addRow(datosR);
            listvent.add(venta);
           
        }
        msj.setText("REGISTROS ENCONTRADOS "+cont.toString());
        TableColumnModel columnModel = tab.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(350);
        columnModel.getColumn(4).setPreferredWidth(80);
        columnModel.getColumn(5).setPreferredWidth(80);
        columnModel.getColumn(6).setPreferredWidth(80);
        columnModel.getColumn(7).setPreferredWidth(80);
        columnModel.getColumn(8).setPreferredWidth(80);
	rs.close();
        ps.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
               Cbd.desconectar();
                       
            }    

    return listvent;
    }

public List<Ventas> mostrarconcretadasanuladas(JTable tab,Date desde,Date hasta,JLabel msj){
 
 
    ConexionBD Cbd = new ConexionBD();
    DefaultTableModel tabla=new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
    //      if (column == 5) return true;
    //else
     return false;
    }
  };     
    
    String titulos[]={"CODIGO","DOCUMENTO","NUMERO","CLIENTE","FECHA","IMPORTE","DESCUENTO","TOTAL","MOTIVO"};
    tabla.setColumnIdentifiers(titulos);
    tab.setModel(tabla);
    List<Ventas> listventa= new ArrayList<>();
    try{
	
        //System.out.println("SELECT * from sp_mostrarreparacion('"+cod+"')");
        String sql=("SELECT * from sp_mostrarventaanulada(?,?)"); 
        PreparedStatement ps= Cbd.conectar().prepareStatement(sql);
        ps.setDate(1, desde);
        ps.setDate(2, hasta);
      
        ResultSet rs= Cbd.RealizarConsulta(ps);
        Object datosR[] = new Object[9];
        
        int cont = 0;
        while (rs.next()){
            Ventas venta = new Ventas();
           
            cont++;
              //   datosR[0] = rs.getObject("vidventa");

//                     datosR[i] = rs.getObject("vsucursal");
//                     i++; 
            venta.setIdventa(rs.getLong("vidventa"));
             datosR[0] = rs.getObject("vcodigo");

             datosR[1] = rs.getObject("vdocumento");

             datosR[2] = rs.getObject("vnumero");

             datosR[3] = rs.getObject("vcliente");

             datosR[4] = rs.getObject("vfecha");

             datosR[5] = rs.getObject("vimporte");

             datosR[6] = rs.getObject("vdescuento"); 

             datosR[7] = rs.getObject("vtotal");

             datosR[8] = rs.getObject("vmotivo");
                    
                                  
             tabla.addRow(datosR);
             listventa.add(venta);       
        }
        msj.setText("REGISTROS ENCONTRADOS "+cont);
        tab.setModel(tabla); 
        TableColumnModel columnModel = tab.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(380);
        columnModel.getColumn(4).setPreferredWidth(80);
        columnModel.getColumn(5).setPreferredWidth(80);
        columnModel.getColumn(6).setPreferredWidth(80);
        columnModel.getColumn(7).setPreferredWidth(80);
        columnModel.getColumn(8).setPreferredWidth(80);
        
	rs.close();
        ps.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
                       
            }    

    return listventa;
    }

 public void imprimirticketcaja(long id){
     
     
     
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        ConexionBD Cbd = new ConexionBD();
        try
        {
          //se carga el reporte
//           URL  in=this.getClass().getResource( "reporte.jasper" );
//           jasperReport=(JasperReport)JRLoader.loadObject(in);
            String  rutaInforme  = "src/Reportes/ticketventa.jasper";
            
            Map parametros = new HashMap();
            System.out.println("idticket"+id);
            parametros.put("id",id);
           //se procesa el archivo jasper
           JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,Cbd.conectar());
           //impresion de reporte
           // TRUE: muestra la ventana de dialogo "preferencias de impresion"
           JasperPrintManager.printReport(informe, true);          
         }
         catch (JRException ex)
         {
           System.err.println( "Error iReport: " + ex.getMessage() );
         }finally{
            Cbd.desconectar();
        }
 
     
     
//      Conexion conexion = new Conexion();
//try{
//                      
//            String  rutaInforme  = "src/Reportes/ticketventa.jasper";
//            
//            Map parametros = new HashMap();
//            System.out.println("idticket"+id);
//            parametros.put("id",id);
//            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,conexion.getConnection());
//            JasperViewer jv = new JasperViewer(informe,false);  
//        
//             jv.setVisible(true);
//             jv.setTitle(rutaInforme);
//          
//        }catch (HeadlessException | JRException ex) {
//        JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE", "ERROR",JOptionPane.ERROR_MESSAGE);
//        JOptionPane.showMessageDialog(null,ex.getMessage());
//        }finally{
//            conexion.devolverConexionPool();
//        }
     

}
 
 public void imprimirtodocliente(long idcliente){
    ConexionBD Cbd = new ConexionBD();
    try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////////////    
           
          
            
                      
            String  rutaInforme  = "src/Reportes/TodoVentaCliente.jasper";
            
            Map parametros = new HashMap();
            System.out.println("idticket"+idcliente);
            parametros.put("idcliente",idcliente);
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,Cbd.conectar());
            JasperViewer jv = new JasperViewer(informe,false);  
        
             jv.setVisible(true);
             jv.setTitle(rutaInforme);
          
        }catch (HeadlessException | JRException ex) {
        JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE", "ERROR",JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null,ex.getMessage());
        }finally{
            Cbd.desconectar();
        }
 }
    public void imprimirunacliente(long idcliente,long idventa){
     ConexionBD Cbd = new ConexionBD();
    try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////////////    
           
          
            
                      
            String  rutaInforme  = "src/Reportes/UnaVentaCliente.jasper";
            
            Map parametros = new HashMap();
            System.out.println("idticket"+idcliente);
            parametros.put("idcliente",idcliente);
            parametros.put("idventa",idventa);
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,Cbd.conectar());
            JasperViewer jv = new JasperViewer(informe,false);  
        
             jv.setVisible(true);
             jv.setTitle(rutaInforme);
          
        }catch (HeadlessException | JRException ex) {
        JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE", "ERROR",JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null,ex.getMessage());
        }finally{
            Cbd.desconectar();
        }
     
     

}
    public void eliminar(long  idventa){
    
   
     ConexionBD Cbd = new ConexionBD();
//        ResultSet rs=null;
//        boolean valida=false;
     try{
        
        String sql=("SELECT * from sp_eliminarventa(?)"); 
        
        PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, idventa);
      
        Cbd.actualizarDatos(ps);
      
//        if  (rs.next()){
//            //JOptionPane.showMessageDialog(null,"PRODUCTO RETIRADO DE LA VENTA CORRECTAMENTE");
//
// 
//        }
        ps.close();
      

        }
     
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
        Cbd.desconectar();
     }
    
    }
    
    
}
