/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.Cliente_Tipo_Documento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author HERNAN
 */
public class ClienteTipoDocumentoDAO {
     ConexionBD Cbd = new ConexionBD();
     
    public List<Cliente_Tipo_Documento> mostrar(){
        
       
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Cliente_Tipo_Documento> listclientedocumento = new ArrayList<>();
               
    try{

        
        ps=Cbd.conectar().prepareStatement("SELECT * from sp_mostrartipodoc()");
        rs=Cbd.RealizarConsulta(ps);
      
        while (rs.next()){
                   
             Cliente_Tipo_Documento clientedoc = new Cliente_Tipo_Documento();  
             clientedoc.setId(rs.getLong(1));
             clientedoc.setDocumento(rs.getString(2));
             clientedoc.setOp(rs.getString(3));
            listclientedocumento.add(clientedoc);
		
        }
       	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{

                Cbd.desconectar();
            }    
      return listclientedocumento;
        
      
    }
    
}
