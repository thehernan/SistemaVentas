/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.Moneda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author HERNAN
 */
public class MonedaDAO {
       
      ConexionBD Cbd = new ConexionBD();
      
      public List<Moneda> mostrar(){

        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Moneda> listmoneda = new ArrayList<>();
               
    try{
     
        ps=Cbd.conectar().prepareStatement("SELECT * from sp_mostrartipodoc()");
        rs=Cbd.RealizarConsulta(ps);
      
        while (rs.next()){
                   
             Moneda moneda = new Moneda();
             moneda.setId(rs.getLong(1));
             moneda.setMoneda(rs.getString(2));
             moneda.setOp(rs.getInt(3));
            listmoneda.add(moneda);
		
        }
       	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{

                Cbd.desconectar();
            }    
      return listmoneda;
        
      
    }
    
}
