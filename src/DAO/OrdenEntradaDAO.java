/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Pojos.Compras;
import Pojos.OrdenSalidaEntrada;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author info2017
 */
public class OrdenEntradaDAO {
    
     public long insertar(OrdenSalidaEntrada orden, long idordensalida){
        Conexion conexion = new Conexion();
        long id=0;
     try{
            
            String sql=("SELECT * from sp_insertarordensalidaentrada(?,?,?,?,?,?,?,?)");         
            PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
            ps.setString(1, orden.getSucurenvia());
            ps.setString(2, orden.getSucursolicita());
            ps.setTimestamp(3, orden.getFecha_pedido());
            ps.setTimestamp(4,orden.getFecha_entrega());
            ps.setString(5,orden.getAutorizado());
            ps.setString(6,orden.getRecibido());
            ps.setString(7,orden.getTipoop());
            ps.setLong(8, idordensalida);
          
            ResultSet rs=ps.executeQuery();
       if  (rs.next()){
            id=(rs.getLong("id"));
//           JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSA");
        }
	
        } catch(SQLException | HeadlessException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
             conexion.devolverConexionPool();

}
  return id;  
}
      public void imprimir(long id){
try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////+33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333////////       
            //Connection miconexion = conectar.Connect();
            Conexion conexion = new Conexion();
                      
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
        }
     

}
    
}