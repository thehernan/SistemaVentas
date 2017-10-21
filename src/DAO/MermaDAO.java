/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Pojos.Merma;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
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
public class MermaDAO {
     public long insertar(Merma merma){ 
       Conexion conexion = new Conexion();
        
       long id=0;
     try{
         
            String sql=("SELECT * from sp_insertarmerma(?,?)");         
            PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
            ps.setString(1, merma.getMotivo());
            ps.setDate(2, merma.getFecha());
       
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
      public void eliminar(long  idmerma){
    
    Conexion conexion= new Conexion();
     
        ResultSet rs=null;
//        boolean valida=false;
     try{
        
        String sql=("SELECT * from sp_eliminarmerma(?)"); 
        
        PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idmerma);
      
        rs = ps.executeQuery();
      
        if  (rs.next()){
            //JOptionPane.showMessageDialog(null,"PRODUCTO RETIRADO DE LA VENTA CORRECTAMENTE");

 
        }

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
         conexion.devolverConexionPool();
     }
    
    }
public void imprimir(Date desde, Date hasta){
       Conexion conexion = new Conexion();  
    try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////+33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333////////       
            //Connection miconexion = conectar.Connect();
           
                      
            String  rutaInforme  = "src/Reportes/Mermas.jasper";
            
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
        }finally{
        conexion.devolverConexionPool();

}
}   
    
}
