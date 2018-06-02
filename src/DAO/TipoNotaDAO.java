/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Conexion.ConexionBD;
import Pojos.TipoNota;
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
public class TipoNotaDAO {
    
    public List<TipoNota> mostrar(String tipo){
        ConexionBD Cbd = new ConexionBD(); 
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<TipoNota> listnota = new ArrayList<>();
               
    try{
     
        ps=Cbd.conectar().prepareStatement("SELECT * from sp_mostrartiponota(?)");
        ps.setString(1, tipo);
        rs=Cbd.RealizarConsulta(ps);
      
        while (rs.next()){
                   
             TipoNota tn = new TipoNota();
             tn.setId(rs.getLong(1));
             tn.setDescripcion(rs.getString(2));
             tn.setOp(rs.getInt(3));
             tn.setNota(rs.getString(4));
             listnota.add(tn);
                    
        }
       	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{

                Cbd.desconectar();
            }    
      return listnota;
        
      
    }
    
    
}
