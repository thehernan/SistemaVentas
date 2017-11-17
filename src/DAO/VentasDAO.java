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
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
    
public Ventas buscarventa(javax.swing.JTable tab,String cod,JLabel nombre,JLabel rut,
            JFormattedTextField total,JFormattedTextField iva,JFormattedTextField subottotal,
            JFormattedTextField descuento){
        
     
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
    tab.setModel(tabla);
    try{
	
        System.out.println("SELECT * from sp_busquedaventa('"+cod+"')");
        String sql=("SELECT * from sp_busquedaventa(?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setString(1, cod);
        ResultSet rs= ps.executeQuery();
        
        String sql1=("SELECT * from sp_busquedaventatotal(?)"); 
        PreparedStatement ps1= conexion.getConnection().prepareStatement(sql1);
        ps1.setString(1, cod);
        ResultSet rs1= ps1.executeQuery();
        Object datosR[] = new Object[5];
        nombre.setText("");
        rut.setText("");
        total.setValue(0);
        iva.setValue(0);
        subottotal.setValue(0);
        descuento.setValue(0);
        while (rs.next()){
            nombre.setText(rs.getString("vnombre"));
           // System.out.println("NOMBRE: "+rs.getString("vnombre"));
            rut.setText(rs.getString("vrut"));
            venta.setIdventa(rs.getLong("vidventa"));
            venta.setFecha(rs.getDate("vfecha"));
            venta.setDescuento(rs.getDouble("vdescuento"));
            for(int i =0; i<=1; i++){
                     datosR[i] = rs.getObject("vcodigo");
                     i++;    
                     datosR[i] = rs.getObject("vproducto");
                     i++;
                     datosR[i] = rs.getObject("vcantidad");
                     i++;
                     datosR[i] = rs.getObject("vprecio");
                     i++;
                     datosR[i] = rs.getObject("vimporte");
                     i++;
//                     datosR[i] = rs.getObject("vcelular");
//                     i++;
                    
                    tabla.addRow(datosR);
		}
        }
        if(rs1.next()){
            total.setValue(rs1.getDouble("vtotal"));
            iva.setValue(rs1.getDouble("viva"));
            subottotal.setValue(rs1.getDouble("vsubtotal"));
            descuento.setValue(rs1.getDouble("vdescuento"));
        
        }
        ps1.close();
        rs1.close();
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

public void mostrar(JTable tab,Timestamp desde,Timestamp hasta,JLabel msj){
     
    Conexion conexion = new Conexion();
   
    DefaultTableModel tabla=new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
       //      if (column == 5) return true;
    //else
    return false;
    }
    }; 
    String titulos[]={"IDEMPLE","IDSUCUR","EMPLEADO","R.U.T","SUCURSAL","DIRECCION","IMPORTE","DESCUENTOS","TOTAL","%","COMISION"};
    tabla.setColumnIdentifiers(titulos);
    tab.setModel(tabla);
    tab.getColumnModel().getColumn(0).setMaxWidth(0);
    tab.getColumnModel().getColumn(0).setMinWidth(0);
    tab.getColumnModel().getColumn(0).setPreferredWidth(0);
        
    tab.getColumnModel().getColumn(1).setMaxWidth(0);
    tab.getColumnModel().getColumn(1).setMinWidth(0);
    tab.getColumnModel().getColumn(1).setPreferredWidth(0);
    try{
	
        System.out.println("SELECT * from sp_mostrarventa('"+desde+"','"+hasta+"')");
        String sql=("SELECT * from sp_mostrarventa(?,?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setTimestamp(1, desde);
        ps.setTimestamp(2, hasta);
        ResultSet rs= ps.executeQuery();
        Object datosR[] = new Object[11];
        msj.setText("");
        int cont=0;
        while (rs.next()){
           
            for(int i =0; i<=1; i++){
                     datosR[i] = rs.getObject("videmple");
                     i++;
                     datosR[i] = rs.getObject("vidsucur");
                     i++;
                     datosR[i] = rs.getObject("vnombre");
                     i++;
                     datosR[i] = rs.getObject("vrut");
                     i++;
                     datosR[i] = rs.getObject("vsucursal");
                     i++;
                     datosR[i] = rs.getObject("vsucurdirec");
                     i++;
                      datosR[i] = rs.getObject("vimporte");
                     i++;
                      datosR[i] = rs.getObject("vdescuento");
                     i++;
                     datosR[i] = rs.getObject("vtotal");
                     i++;
                     datosR[i] = rs.getObject("vporcentaje");
                     i++;
                     datosR[i] = rs.getObject("vcomision");
                     i++;
                    
                    tabla.addRow(datosR);
		}
            cont++;
        }
        rs.close();
        ps.close();
        if(cont == 0){
            msj.setText("NO SE ENCONTRARON REGISTROS EN EL RANGO DE "+desde+" AL "+hasta);
        }else {
            msj.setText("REGISTROS ENCONTRADOS "+cont);
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
                       
            }    

    
    }
public void mostrarenproceso(JTable tab,long idsucur,JLabel msj){
     
    Conexion conexion = new Conexion();
   
    DefaultTableModel tabla=new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
       //      if (column == 5) return true;
    //else
    return false;
    }
    }; 
    String titulos[]={"IDvent","COD. VENTA","EMPLEADO","R.U.T","SUCURSAL","DIRECCION","IMPORTE","DESCUENTO","TOTAL"};
    tabla.setColumnIdentifiers(titulos);
    tab.setModel(tabla);
    tab.getColumnModel().getColumn(0).setMaxWidth(0);
    tab.getColumnModel().getColumn(0).setMinWidth(0);
    tab.getColumnModel().getColumn(0).setPreferredWidth(0);
    try{
	

        String sql=("SELECT * from sp_mostrarventaenproceso(?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idsucur);
       
        ResultSet rs= ps.executeQuery();
        Object datosR[] = new Object[9];
        msj.setText("");
        int cont=0;
        while (rs.next()){
         
                     datosR[0] = rs.getObject("vidventa");
                 
                     datosR[1]=rs.getObject("vcodigo");
                     datosR[2] = rs.getObject("vnombre");
                   
                     datosR[3] = rs.getObject("vrut");
                   
                     datosR[4] = rs.getObject("vsucursal");
                    
                     datosR[5] = rs.getObject("vsucurdirec");
                  
                      datosR[6] = rs.getObject("vimporte");
                  
                      datosR[7] = rs.getObject("vdescuento");
                 
                     datosR[8] = rs.getObject("vtotal");
                   
                    
                    tabla.addRow(datosR);
		
            cont++;
        }
        rs.close();
        ps.close();
        if(cont == 0){
            msj.setText("NO SE ENCONTRARON VENTAS SIN CONCRETAR");
        }else {
            msj.setText("REGISTROS ENCONTRADOS "+cont);
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
                       
            }    

    
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

public void mostrarporempleado(JTable tab,long idemple,long idsucur,JLabel emple,JLabel sucur,
     Timestamp desde, Timestamp hasta){
        
     
    Conexion conexion = new Conexion();
 
   
    DefaultTableModel tabla=new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
    //      if (column == 5) return true;
    //else
     return false;
    }
  };     
    
    String titulos[]={"ID","CODIGO","DOCUMENTO","NUMERO","CLIENTE","FECHA","IMPORTE","DESCUENTO","TOTAL"};
    tabla.setColumnIdentifiers(titulos);
    tab.setModel(tabla);
    tab.getColumnModel().getColumn(0).setMaxWidth(0);
    tab.getColumnModel().getColumn(0).setMinWidth(0);
    tab.getColumnModel().getColumn(0).setPreferredWidth(0);
    try{
	
        //System.out.println("SELECT * from sp_mostrarreparacion('"+cod+"')");
        String sql=("SELECT * from sp_mostrarventaempleado(?,?,?,?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idemple);
        ps.setLong(2, idsucur);
        ps.setTimestamp(3, desde);
        ps.setTimestamp(4, hasta);
        ResultSet rs= ps.executeQuery();
        Object datosR[] = new Object[11];
        
        
        while (rs.next()){
            emple.setText(rs.getString("vempleado"));
            sucur.setText(rs.getString("vsucursal"));
            for(int i =0; i<=1; i++){
                      datosR[i] = rs.getObject("vidventa");
                     i++;  
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
                                  
                    tabla.addRow(datosR);
		}
           
        }
        
	rs.close();
        ps.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
                       
            }    

    
    }

public void mostrarporcliente(JTable tab,long idcliente,JLabel msj){
   Conexion conexion = new Conexion();
 
   
    DefaultTableModel tabla= new DefaultTableModel(
                new String[]{"ID","CODIGO","DOCUMENTO","NUMERO","CLIENTE","FECHA","IMPORTE","DESCUENTO","TOTAL","ANULADA"}, 0) {
 
            Class[] types = new Class[]{
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
                    ,java.lang.Object.class,java.lang.Boolean.class
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
    tab.getColumnModel().getColumn(0).setMaxWidth(0);
    tab.getColumnModel().getColumn(0).setMinWidth(0);
    tab.getColumnModel().getColumn(0).setPreferredWidth(0);
    try{
	
        //System.out.println("SELECT * from sp_mostrarreparacion('"+cod+"')");
        String sql=("SELECT * from sp_mostrarventacliente(?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idcliente);
      
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
                      datosR[i] = rs.getBoolean("vanulada");
                     
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
