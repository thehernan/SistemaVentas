/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.Tipo_Comprobante;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author HERNAN
 */
public class TipoComprobanteDAO {
    
    ConexionBD Cbd = new ConexionBD();
      
      public List<Tipo_Comprobante> mostrar(){

        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Tipo_Comprobante> listcomprobante = new ArrayList<>();
               
    try{
     
        ps=Cbd.conectar().prepareStatement("SELECT * from sp_mostrarcomprobante()");
        rs=Cbd.RealizarConsulta(ps);
      
        while (rs.next()){
                   
             Tipo_Comprobante comprobante = new Tipo_Comprobante();
             comprobante.setId(rs.getLong(1));
             comprobante.setComprobante(rs.getString(2));
             comprobante.setOp(rs.getInt(3));
             listcomprobante.add(comprobante);
                    
        }
       	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{

                Cbd.desconectar();
            }    
      return listcomprobante;
        
      
    }
    
}
