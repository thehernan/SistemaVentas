/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.Percepcion_Tipo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author HERNAN
 */
public class PercepcionTipoDAO {
    
     ConexionBD Cbd = new ConexionBD();
      
      public List<Percepcion_Tipo> mostrar(){

        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Percepcion_Tipo> listpercepcion = new ArrayList<>();
               
    try{
     
        ps=Cbd.conectar().prepareStatement("SELECT * from sp_mostrarpercepcion()");
        rs=Cbd.RealizarConsulta(ps);
      
        while (rs.next()){
                   
             Percepcion_Tipo percepcion = new Percepcion_Tipo();
             percepcion.setId(rs.getLong(1));
             percepcion.setPercepcion(rs.getString(2));
             percepcion.setOp(rs.getInt(3));
             listpercepcion.add(percepcion);
		
        }
       	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{

                Cbd.desconectar();
            }    
      return listpercepcion;
        
      
    }
    
}
