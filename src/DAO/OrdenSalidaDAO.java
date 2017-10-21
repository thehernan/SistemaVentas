/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Pojos.DetalleOrdeSalidaEntrada;
import Pojos.OrdenSalidaEntrada;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author info2017
 */
public class OrdenSalidaDAO {
  public long insertar(OrdenSalidaEntrada orden){ 
       Conexion conexion = new Conexion();
        
       long id=0;
     try{
         System.out.println("SELECT * from sp_insertarordensalidaentrada('"+orden.getSucurenvia()+"','"+orden.getSucursolicita()+"','"+orden.getFecha_pedido()+"','"+orden.getFecha_entrega()+"','"+orden.getAutorizado()+"','"+orden.getRecibido()+"','"+orden.getTipoop()+"')");
            String sql=("SELECT * from sp_insertarordensalidaentrada(?,?,?,?,?,?,?,?)");         
            PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
            ps.setString(1, orden.getSucurenvia());
            ps.setString(2, orden.getSucursolicita());
            ps.setTimestamp(3, orden.getFecha_pedido());
            ps.setTimestamp(4,orden.getFecha_entrega());
            ps.setString(5,orden.getAutorizado());
            ps.setString(6,orden.getRecibido());
            ps.setString(7,orden.getTipoop());
            ps.setLong(8, 0);
            ResultSet rs= ps.executeQuery();
       if  (rs.next()){
           id=(rs.getLong("id"));
           
           //JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSA");
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
  public OrdenSalidaEntrada buscar(long  idorden,String sucur,JTable tabla,
          List<DetalleOrdeSalidaEntrada> listadet,JButton aceptar){
    
    Conexion conexion= new Conexion();
     
        ResultSet rs=null;
//        boolean valida=false;
        OrdenSalidaEntrada orden = new OrdenSalidaEntrada();
        DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
        //      if (column == 5) return true;
        //else
        return false;
        }
    }; 
        String titulos[]={"CODIGO","DESCRIPCION","CANTIDAD"};
        modelo.setColumnIdentifiers(titulos);
      
     try{
        
        String sql=("SELECT * from sp_busquedaordensalidaentrada(?,?)"); 
        
        PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idorden);
        ps.setString(2, sucur);
        rs = ps.executeQuery();
          Object datosR[]= new Object[3];
          long idprod;
          double cantidad;
          int cont=0;
        while  (rs.next()){
            orden.setIdordensalidaentrada(rs.getLong("vidorden"));
            orden.setSucurenvia(rs.getString("vsucurenvia"));
            orden.setSucursolicita(rs.getString("vsucursolicita"));
            orden.setFecha_pedido(rs.getTimestamp("vfechapedido"));
            orden.setFecha_entrega(rs.getTimestamp("vfechaentrega"));
            orden.setAutorizado(rs.getString("vautorizado"));
            orden.setRecibido(rs.getString("vrecibido"));
            
                    
                     for(int i =0; i<=1; i++){
                         
                     DetalleOrdeSalidaEntrada detorden= new DetalleOrdeSalidaEntrada();
                     idprod=rs.getLong("vidproducto");
                     cantidad=rs.getDouble("vcantidad");
                     detorden.setIdproducto(idprod);
                     detorden.setCantidad(cantidad);    
                     datosR[i] = rs.getObject("vcodigo");
                     i++;
                     datosR[i] = rs.getObject("vdescripcion");
                     i++;
                     datosR[i] = (cantidad);
                     i++;
                     listadet.add(detorden);
                     modelo.addRow(datosR);
		}
//                     aceptar.setEnabled(true);
                     cont++;
                    
             }    if (cont>0){
//                    modelo= new DefaultTableModel(){
//                    public boolean isCellEditable(int row, int column) {
//                    //      if (column == 5) return true;
//                    //else
//                    return false;
//                    }
//                    }; 
//                    String titulos[]={"CODIGO","DESCRIPCION","CANTIDAD"};
//                    modelo.setColumnIdentifiers(titulos);
                    aceptar.setEnabled(true);
        }else {
             aceptar.setEnabled(false);
             }
         tabla.setModel(modelo);
             
             ps.close();
             rs.close();  
            //JOptionPane.showMessageDialog(null,"PRODUCTO RETIRADO DE LA VENTA CORRECTAMENTE");

 
        

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
         conexion.devolverConexionPool();
     }
     return orden;
    
    }
  
  
  public void eliminar(long  idorden){
    
    Conexion conexion= new Conexion();
     
        ResultSet rs=null;
//        boolean valida=false;
     try{
        
        String sql=("SELECT * from sp_eliminarordensalidaentrada(?)"); 
        
        PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idorden);
      
        rs = ps.executeQuery();
      
        if  (rs.next()){
            //JOptionPane.showMessageDialog(null,"PRODUCTO RETIRADO DE LA VENTA CORRECTAMENTE");

 
        }
        rs.close();
        ps.close();

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
         conexion.devolverConexionPool();
     }
    
    }
  
  public void imprimir(long id){
      Conexion conexion = new Conexion();
try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////+33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333////////       
            //Connection miconexion = conectar.Connect();
            
                      
            String  rutaInforme  = "src/Reportes/OrdenSalida.jasper";
            
            Map parametros = new HashMap();
           
            parametros.put("id",  id);
//            parametros.put("fecha",fecha);
//            parametros.put("motivodet", motdet);
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
  ///////////////IMPRIMIRR TODAS LAS ORDENES SEGUN RANGO DE FECHAS /////////////
  public void imprimirtodo(Timestamp desde, Timestamp hasta){
    Conexion conexion = new Conexion(); 
try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////+33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333////////       
            //Connection miconexion = conectar.Connect();
            
                      
            String  rutaInforme  = "src/Reportes/OrdenSalidaEntrada.jasper";
            
            Map parametros = new HashMap();
           
            parametros.put("desde",  desde);
            parametros.put("hasta",  hasta);
//            parametros.put("fecha",fecha);
//            parametros.put("motivodet", motdet);
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,conexion.getConnection());
            JasperViewer jv = new JasperViewer(informe,false);  
        
             jv.setVisible(true);
             jv.setTitle(rutaInforme);
             
        }catch (HeadlessException | JRException ex) {
        JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE", "ERROR",JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null,ex.getMessage());
        } finally{
       conexion.devolverConexionPool();
    }
     

}
  ////////////////////////////////////////////////
  
  /////////////// BUSCA TODO ENTRE ORDENES DE SALIDA Y ENTRADA //////////////////
  
  public void mostrar(JTable tab,Timestamp desde,Timestamp hasta,JLabel msj){
        
     
    Conexion conexion = new Conexion();
 
   
    DefaultTableModel modelo= new DefaultTableModel(
                new String[]{"N°","SUCUR. ENVIA","SUCUR. SOLIC","FECHA PEDIDO","FECHA ENTR","AUTORIZADO","RECIBIDO","TIPO OP","RECEPCIONADO"}, 0) {
 
            Class[] types = new Class[]{
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Boolean.class
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
    tab.setModel(modelo);
    try{
	
        //System.out.println("SELECT * from sp_mostrarreparacion('"+cod+"')");
        String sql=("SELECT * from sp_busquedaordensalidaentradaporfechas(?,?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setTimestamp(1, desde);
        ps.setTimestamp(2, hasta);
        ResultSet rs= ps.executeQuery();
        Object datosR[] = new Object[9];
        msj.setText("");
        int cont=0;
        while (rs.next()){
           
            for(int i =0; i<=1; i++){
                     datosR[i] = rs.getObject("vidorden");
                     i++;
                     datosR[i] = rs.getObject("vsucurenvia");
                     i++;
                     datosR[i] = rs.getObject("vsucursolici");
                     i++;
                     datosR[i] = rs.getObject("vfecha_pedido");
                     i++;
                     datosR[i] = rs.getObject("vfecha_entrega");
                     i++;
                     datosR[i] = rs.getObject("vfecha_entrega");
                     i++;
                     datosR[i] = rs.getObject("vrecibidopor");
                     i++;
                    datosR[i] = rs.getObject("vtipoop");
                     i++;
                     datosR[i] = rs.getBoolean("vrecepcionado");
                     i++;
                    modelo.addRow(datosR);
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
  

    
}
