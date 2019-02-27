/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.Unidad_Medida_Fisica;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author HERNAN
 */
public class UnidadMedidaFisicaDAO {
    ConexionBD Cbd = new ConexionBD();
    public List<Unidad_Medida_Fisica> select(){
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Unidad_Medida_Fisica> listunid = new ArrayList<>();
               
    try{
     
        ps=Cbd.conectar().prepareStatement("SELECT * from unid_medida_fisica;");
        rs=Cbd.RealizarConsulta(ps);
      
        while (rs.next()){
                   
             Unidad_Medida_Fisica unid = new Unidad_Medida_Fisica();
             unid.setId(rs.getLong("id_unidad_medida_fisica"));
             unid.setDescrip(rs.getString("descripcion"));
             unid.setConver(rs.getInt("conversion"));
             listunid.add(unid);
		
        }
       	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{

                Cbd.desconectar();
            }    
      return listunid;
        
    
  
    }
    
    
    
}
