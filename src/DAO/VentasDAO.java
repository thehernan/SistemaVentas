/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Conexion.Conexion;
import Pojos.Ventas;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public long insertar(Ventas venta){

       Conexion conexion = new Conexion();
       long id=0;
   
     try{
            
            System.out.println("SELECT * from sp_insertarventa("+venta.getIdcliente()+","+venta.getIdempleado()+")");
            String sql=("SELECT * from sp_insertarventa(?,?,?,?,?)");         
            PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
            ps.setLong(1, venta.getIdcliente());
            ps.setLong(2, venta.getIdempleado());
            ps.setLong(3, venta.getId_sucursal());
            ps.setBigDecimal(4,new BigDecimal(venta.getDescuento()));
            ps.setString(5, venta.getMotivodescuento());
            ResultSet rs= ps.executeQuery();
       if  (rs.next()){
         id=(rs.getLong("vidventa"));
     
        }
	rs.close();
        ps.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();

}
  return id;  
}
    
public long insertarnocliente(Ventas venta){

       Conexion conexion = new Conexion();
        
      long id=0;
     try{
            
            System.out.println("SELECT * from sp_insertarventanocliente("+venta.getIdcliente()+","+venta.getIdempleado()+")");
            String sql=("SELECT * from sp_insertarventanocliente(?,?,?,?)");         
            PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
          
            ps.setLong(1, venta.getIdempleado());
            ps.setLong(2, venta.getId_sucursal());
            ps.setBigDecimal(3,new BigDecimal(venta.getDescuento()));
            ps.setString(4, venta.getMotivodescuento());
            ResultSet rs= ps.executeQuery();
       if  (rs.next()){
           id=(rs.getLong("vidventa"));
         
        }
	rs.close();
        ps.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();

}
  return id;  
}
    
public Ventas buscarventa(JTable tab,String cod,JLabel nombre,JLabel rut
            ){
        
     
    Conexion conexion = new Conexion();
    
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
  

    NumberFormat nf= NumberFormat.getInstance();
            
    try{
	
        System.out.println("SELECT * from sp_busquedaventa('"+cod+"')");
        String sql=("SELECT * from sp_busquedaventa(?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setString(1, cod);
        ResultSet rs= ps.executeQuery();
        
     
        
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
             
             datosR[2] = nf.format(rs.getObject("vcantidad"));
           
             datosR[3] = nf.format(rs.getObject("vprecio"));
             
             datosR[4] =nf.format(importe);
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
               conexion.devolverConexionPool();
            }    
return venta;
    
    }

public List<Ventas> mostrar(JTable tab,Timestamp desde,Timestamp hasta,JLabel msj){
     
    Conexion conexion = new Conexion();
   
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
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setTimestamp(1, desde);
        ps.setTimestamp(2, hasta);
        ResultSet rs= ps.executeQuery();
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
                conexion.devolverConexionPool();
                       
            }    
    return listvent;
    
    }
public List<Ventas> mostrarenproceso(JTable tab,long idsucur,JLabel msj){
     
    Conexion conexion = new Conexion();
   
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
	
        NumberFormat nf= NumberFormat.getInstance();
        double total =0.0;
        String sql=("SELECT * from sp_mostrarventaenproceso(?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idsucur);
       
        ResultSet rs= ps.executeQuery();
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

             datosR[2] = nf.format(rs.getDouble("vimporte"));

             datosR[3] = nf.format(rs.getDouble("vdescuento"));
             
             datosR[4] =nf.format(venta.getTotal());
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
            msj.setText("VENTAS EN COLA "+cont+ " - TOTAL: " +nf.format(total));
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
                       
            }    

    return listventa;
    }

public void extornar(long idvent){
     
    Conexion conexion = new Conexion();
  
 
    try{
	

        String sql=("SELECT * from sp_extornarventa(?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idvent);
       
        ResultSet rs = ps.executeQuery();
        
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
                conexion.devolverConexionPool();
                       
            }    

    
    }
public void extornarconcretada(long idvent,String motivo){
     
    Conexion conexion = new Conexion();
  
 
    try{
	

        String sql=("SELECT * from sp_extornarventaconcretada(?,?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idvent);
        ps.setString(2, motivo);
       
        ResultSet rs = ps.executeQuery();
        
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
                conexion.devolverConexionPool();
                       
            }    

    
    }

public List<Ventas> mostrarporempleado(JTable tab,Ventas ventaa,JLabel emple,JLabel sucur,
     Timestamp desde, Timestamp hasta,JLabel jtotal){
        
     
    Conexion conexion = new Conexion();
 
     DefaultTableModel tabla= new DefaultTableModel(
                new String[]{"CODIGO","DOCUMENTO","NUMERO","CLIENTE","FECHA","IMPORTE","DESCUENTO","MOT.DESC","TOTAL","ANULADA","MOT. ANU","FECHA. ANU"}, 0) {
 
            Class[] types = new Class[]{
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
                    ,java.lang.Boolean.class,java.lang.Object.class,java.lang.Object.class
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
        NumberFormat nf = NumberFormat.getInstance();
       
    try{
	
        //System.out.println("SELECT * from sp_mostrarreparacion('"+cod+"')");
        String sql=("SELECT * from sp_mostrarventaempleado(?,?,?,?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, ventaa.getIdempleado());
        ps.setLong(2,ventaa.getId_sucursal());
        ps.setTimestamp(3, desde);
        ps.setTimestamp(4, hasta);
        ResultSet rs= ps.executeQuery();
        Object datosR[] = new Object[12];
        
        
        while (rs.next()){
            emple.setText(rs.getString("vempleado"));
            sucur.setText(rs.getString("vsucursal"));
             Ventas venta = new Ventas();
             venta.setIdventa(rs.getLong("vidventa"));     
             extor= rs.getBoolean("vextornado"); 
             importe=rs.getDouble("vtotal");
             if(extor==false){
             total=total+importe;
             
             }
             datosR[0] = rs.getObject("vcodigo");

             datosR[1] = rs.getObject("vdocumento");

             datosR[2] = rs.getObject("vnumero");

             datosR[3] = rs.getObject("vcliente");

             datosR[4] = rs.getObject("vfecha");

             datosR[5] =nf.format(rs.getObject("vimporte"));


             datosR[6] =nf.format(rs.getObject("vdescuento"));
              datosR[7] =(rs.getObject("vmotivodes"));

             datosR[8] =nf.format(importe);
             
             datosR[9] =extor;
             datosR[10] =(rs.getObject("vmotivo"));
             datosR[11] =(rs.getObject("vfechaext"));

            tabla.addRow(datosR);
	    listventa.add(venta);
           
        }
        jtotal.setText("Total Vendido: "+nf.format(total));
	rs.close();
        ps.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
                       
            }    

    return listventa;
    }

public List<Ventas> mostrarporcliente(JTable tab,long idcliente,JLabel msj){
   Conexion conexion = new Conexion();
 
   
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
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idcliente);
      
        ResultSet rs= ps.executeQuery();
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
	rs.close();
        ps.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
                       
            }    

    return listvent;
    }

public void mostrarconcretadasanuladas(JTable tab,Date desde,Date hasta,JLabel msj){
   Conexion conexion = new Conexion();
 
   
    DefaultTableModel tabla=new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
    //      if (column == 5) return true;
    //else
     return false;
    }
  };     
    
    String titulos[]={"ID","CODIGO","DOCUMENTO","NUMERO","CLIENTE","FECHA","IMPORTE","DESCUENTO","TOTAL","MOTIVO"};
    tabla.setColumnIdentifiers(titulos);
    tab.setModel(tabla);
    tab.getColumnModel().getColumn(0).setMaxWidth(0);
    tab.getColumnModel().getColumn(0).setMinWidth(0);
    tab.getColumnModel().getColumn(0).setPreferredWidth(0);
    try{
	
        //System.out.println("SELECT * from sp_mostrarreparacion('"+cod+"')");
        String sql=("SELECT * from sp_mostrarventaanulada(?,?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setDate(1, desde);
        ps.setDate(2, hasta);
      
        ResultSet rs= ps.executeQuery();
        Object datosR[] = new Object[10];
        
        Integer cont = 0;
        while (rs.next()){
         
            for(int i =0; i<=1; i++){
                cont++;
                      datosR[i] = rs.getObject("vidventa");
                     i++;  
//                     datosR[i] = rs.getObject("vsucursal");
//                     i++;  
                     datosR[i] = rs.getObject("vcodigo");
                     i++;
                     datosR[i] = rs.getObject("vdocumento");
                     i++;
                     datosR[i] = rs.getObject("vnumero");
                     i++;
                     datosR[i] = rs.getObject("vcliente");
                     i++;
                     datosR[i] = rs.getObject("vfecha");
                     i++;
                     datosR[i] = rs.getObject("vimporte");
                     i++;
                     datosR[i] = rs.getObject("vdescuento"); 
                     i++;
                     datosR[i] = rs.getObject("vtotal");
                     i++;
                     datosR[i] = rs.getObject("vmotivo");
                     i++;
                                  
                    tabla.addRow(datosR);
		}
           
        }
        msj.setText("REGISTROS ENCONTRADOS "+cont.toString());
	rs.close();
        ps.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
                       
            }    

    
    }

 public void imprimirticketcaja(long id){
     
     
     
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        Conexion conexion = new Conexion(); 
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
           JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,conexion.getConnection());
           //impresion de reporte
           // TRUE: muestra la ventana de dialogo "preferencias de impresion"
           JasperPrintManager.printReport(informe, true);          
         }
         catch (JRException ex)
         {
           System.err.println( "Error iReport: " + ex.getMessage() );
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
      Conexion conexion = new Conexion();
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
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,conexion.getConnection());
            JasperViewer jv = new JasperViewer(informe,false);  
        
             jv.setVisible(true);
             jv.setTitle(rutaInforme);
          
        }catch (HeadlessException | JRException ex) {
        JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE", "ERROR",JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null,ex.getMessage());
        }finally{
            conexion.devolverConexionPool();
        }
 }
    public void imprimirunacliente(long idcliente,long idventa){
      Conexion conexion = new Conexion();
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
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,conexion.getConnection());
            JasperViewer jv = new JasperViewer(informe,false);  
        
             jv.setVisible(true);
             jv.setTitle(rutaInforme);
          
        }catch (HeadlessException | JRException ex) {
        JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE", "ERROR",JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null,ex.getMessage());
        }finally{
            conexion.devolverConexionPool();
        }
     
     

}
    public void eliminar(long  idventa){
    
    Conexion conexion= new Conexion();
     
        ResultSet rs=null;
//        boolean valida=false;
     try{
        
        String sql=("SELECT * from sp_eliminarventa(?)"); 
        
        PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idventa);
      
        rs = ps.executeQuery();
      
        if  (rs.next()){
            //JOptionPane.showMessageDialog(null,"PRODUCTO RETIRADO DE LA VENTA CORRECTAMENTE");

 
        }
        ps.close();
        rs.close();

        }
     
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
         conexion.devolverConexionPool();
     }
    
    }
    
    
}
