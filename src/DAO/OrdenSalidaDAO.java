/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
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
     
      ConexionBD Cbd = new ConexionBD();  
       long id=0;
     try{
         System.out.println("SELECT * from sp_insertarordensalidaentrada('"+orden.getSucurenvia()+"','"+orden.getSucursolicita()+"','"+orden.getFecha_pedido()+"','"+orden.getFecha_entrega()+"','"+orden.getAutorizado()+"','"+orden.getRecibido()+"','"+orden.getTipoop()+"')");
            String sql=("SELECT * from sp_insertarordensalidaentrada(?,?,?,?,?,?,?,?,?)");         
            PreparedStatement ps= Cbd.conectar().prepareStatement(sql);
            ps.setString(1, orden.getSucurenvia());
            ps.setString(2, orden.getSucursolicita());
            ps.setTimestamp(3, orden.getFecha_pedido());
            ps.setTimestamp(4,orden.getFecha_entrega());
            ps.setString(5,orden.getAutorizado());
            ps.setString(6,orden.getRecibido());
            ps.setString(7,orden.getTipoop());
            ps.setLong(8, 0);
            System.out.println("idsucursalrecepinsert"+orden.getIdsucurrecep());
            ps.setLong(9, orden.getIdsucurrecep());
            ResultSet rs= Cbd.RealizarConsulta(ps);
       if  (rs.next()){
           id=(rs.getLong("id"));
           
           //JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSA");
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
               Cbd.desconectar();
}
  return id;  

  }
  public OrdenSalidaEntrada buscar(long  idorden,Long sucur,JTable tabla,
          List<DetalleOrdeSalidaEntrada> listadet,JButton aceptar){
    
   
        ConexionBD Cbd = new ConexionBD();  
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
        
        PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, idorden);
        ps.setLong(2, sucur);
        rs = Cbd.RealizarConsulta(ps);
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
            TableColumnModel columnModel = tabla.getColumnModel();
     
            columnModel.getColumn(0).setPreferredWidth(90);
            columnModel.getColumn(1).setPreferredWidth(600);
            columnModel.getColumn(2).setPreferredWidth(50);    
             
            //JOptionPane.showMessageDialog(null,"PRODUCTO RETIRADO DE LA VENTA CORRECTAMENTE");

 
        

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();
     }
     return orden;
    
    }
  
  
  public void eliminar(long  idorden){
    
  
     ConexionBD Cbd = new ConexionBD();
//        ResultSet rs=null;
//        boolean valida=false;
     try{
        
        String sql=("SELECT * from sp_eliminarordensalidaentrada(?)"); 
        
        PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, idorden);
      
        Cbd.actualizarDatos(ps);
      
//        if  (rs.next()){
//            //JOptionPane.showMessageDialog(null,"PRODUCTO RETIRADO DE LA VENTA CORRECTAMENTE");
//
// 
//        }
        
        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
         Cbd.desconectar();
     }
    
    }
  
  public void imprimir(long id){
    ConexionBD Cbd = new ConexionBD();
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
  ///////////////IMPRIMIRR TODAS LAS ORDENES SEGUN RANGO DE FECHAS /////////////
  public void imprimirtodo(Timestamp desde, Timestamp hasta){
   ConexionBD Cbd = new ConexionBD();
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
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,Cbd.conectar());
            JasperViewer jv = new JasperViewer(informe,false);  
        
             jv.setVisible(true);
             jv.setTitle(rutaInforme);
             
        }catch (HeadlessException | JRException ex) {
        JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE", "ERROR",JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null,ex.getMessage());
        } finally{
       Cbd.desconectar();
    }
     

}
  ////////////////////////////////////////////////
  
  /////////////// BUSCA TODO ENTRE ORDENES DE SALIDA Y ENTRADA //////////////////
  
  public List<OrdenSalidaEntrada> mostrar(JTable tab,Timestamp desde,Timestamp hasta){
        
     ConexionBD Cbd = new ConexionBD();
   
 
   
    DefaultTableModel modelo= new DefaultTableModel(
                new String[]{"N°","SUCUR. ENVIA","SUCUR. SOLIC","FECHA PEDIDO","FECHA ENTR","AUTORIZADO","RECIBIDO","TIPO OP","RECEP."}, 0) {
 
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
    List<OrdenSalidaEntrada> listorden= new ArrayList<>();
    try{
	
        //System.out.println("SELECT * from sp_mostrarreparacion('"+cod+"')");
        String sql=("SELECT * from sp_busquedaordensalidaentradaporfechas(?,?)"); 
        PreparedStatement ps= Cbd.conectar().prepareStatement(sql);
        ps.setTimestamp(1, desde);
        ps.setTimestamp(2, hasta);
        ResultSet rs= Cbd.RealizarConsulta(ps);
        Object datosR[] = new Object[9];
//        msj.setText("");
//        int cont=0;
        while (rs.next()){
            OrdenSalidaEntrada orden = new OrdenSalidaEntrada();
            orden.setIdordensalidaentrada(rs.getLong("vidorden"));
             datosR[0] = orden.getIdordensalidaentrada();
             
             datosR[1] = rs.getObject("vsucurenvia");
            
             datosR[2] = rs.getObject("vsucursolici");
            
             datosR[3] = rs.getObject("vfecha_pedido");
             
             datosR[4] = rs.getObject("vfecha_entrega");
            
             datosR[5] = rs.getObject("vautorizadopor");
           
             datosR[6] = rs.getObject("vrecibidopor");
            
             datosR[7] = rs.getObject("vtipoop");
            
             datosR[8] = rs.getBoolean("vrecepcionado");
            
            modelo.addRow(datosR);
            listorden.add(orden);
          
        }
        TableColumnModel columnModel = tab.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(60);
        columnModel.getColumn(4).setPreferredWidth(60);
        columnModel.getColumn(5).setPreferredWidth(200);
        columnModel.getColumn(6).setPreferredWidth(200);
        columnModel.getColumn(7).setPreferredWidth(80);
        columnModel.getColumn(8).setPreferredWidth(60);
       
   
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
               Cbd.desconectar();
                       
            }    

            return listorden;
    }
  

    
}
