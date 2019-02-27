/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.Ident_Guia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author HERNAN
 */
public class identGuiaDAO {
    
    ConexionBD Cbd = new ConexionBD();
      
      public List<Ident_Guia> mostrar(){

        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Ident_Guia> listidentguia = new ArrayList<>();
               
    try{
     
        ps=Cbd.conectar().prepareStatement("SELECT * from ident_guia order by id_identguia ASC;");
        rs=Cbd.RealizarConsulta(ps);
      
        while (rs.next()){
                   
             Ident_Guia idenguia = new Ident_Guia();
             idenguia.setId(rs.getLong(1));
             idenguia.setDescripcion(rs.getString(2));
             idenguia.setOp(rs.getInt(3));
             listidentguia.add(idenguia);
                    
        }
       	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{

                Cbd.desconectar();
            }    
      return listidentguia;
        
      
    }
    
}
