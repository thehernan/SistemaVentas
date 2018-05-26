/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.Tipo_Igv;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author HERNAN
 */
public class TipoIgvDAO {
   ConexionBD Cbd = new ConexionBD(); 
      
      public List<Tipo_Igv> mostrar(){

        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Tipo_Igv> listigv = new ArrayList<>();
               
    try{
     
        ps=Cbd.conectar().prepareStatement("SELECT * from sp_mostrartipoigv()");
        rs=Cbd.RealizarConsulta(ps);
      
        while (rs.next()){
                   
             Tipo_Igv igv = new Tipo_Igv();
             igv.setId(rs.getLong(1));
             igv.setDescripcion(rs.getString(2));
             igv.setOp(rs.getInt(3));
             listigv.add(igv);
                    
        }
       	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{

                Cbd.desconectar();
            }    
      return listigv;
        
      
    }
    
}
