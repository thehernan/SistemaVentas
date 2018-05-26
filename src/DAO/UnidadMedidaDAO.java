/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.Unidad_Medida;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author HERNAN
 */
public class UnidadMedidaDAO {
    ConexionBD Cbd = new ConexionBD(); 
    public List<Unidad_Medida> mostrar(){

        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Unidad_Medida> listmedida = new ArrayList<>();
               
    try{
     
        ps=Cbd.conectar().prepareStatement("SELECT * from sp_mostrarunidadmedida()");
        rs=Cbd.RealizarConsulta(ps);
      
        while (rs.next()){
                   
             Unidad_Medida medida = new Unidad_Medida();
             medida.setId(rs.getLong(1));
             medida.setMedida(rs.getString(2));
             medida.setAbreviatura(rs.getString(3));
             listmedida.add(medida);
                    
        }
       	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{

                Cbd.desconectar();
            }    
      return listmedida;
        
      
    }
    
    
}
