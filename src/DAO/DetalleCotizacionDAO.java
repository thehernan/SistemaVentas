/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.DetalleCotizacion;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author HERNAN
 */
public class DetalleCotizacionDAO {
    
     
    
    public void insert(List<DetalleCotizacion> listdet,long idcotiza) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ConexionBD Cbd = new ConexionBD(); 
     
        try{
           
                
            PreparedStatement ps= null;
            ResultSet rs= null;
            for(DetalleCotizacion det: listdet){
                ps = Cbd.conectar().prepareStatement("SELECT * from sp_insertdetcotizacion(?,?,?,?)");
               
                ps.setBigDecimal(1, new BigDecimal(det.getPrecioprod()));
                ps.setLong(2,idcotiza);
                ps.setLong(3,det.getIdprod());
                ps.setBigDecimal(4,new BigDecimal(det.getCantidad()));
       
                rs=Cbd.RealizarConsulta(ps);
            
            }
            
      
      
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();

            }
             
     
    }  
    
}
