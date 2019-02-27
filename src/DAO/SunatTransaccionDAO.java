/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.Sunat_Transaction;
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
public class SunatTransaccionDAO {
    
    ConexionBD Cbd = new ConexionBD();
      
      public List<Sunat_Transaction> mostrar(){

        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Sunat_Transaction> listst = new ArrayList<>();
               
    try{
     
        ps=Cbd.conectar().prepareStatement("SELECT * from sunat_transaction order by id_sunattransaction ASC;");
        rs=Cbd.RealizarConsulta(ps);
      
        while (rs.next()){
                   
             Sunat_Transaction st = new Sunat_Transaction();
             st.setId(rs.getLong(1));
             st.setTransaction(rs.getString(2));
             st.setOp(rs.getString(3));
           
             listst.add(st);
                    
        }
       	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{

                Cbd.desconectar();
            }    
      return listst;
        
      
    }
    
}
