/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.Cliente;
import Pojos.Codigo_sunat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author HERNAN
 */
public class CodigoSunatDAO {
    
    public List<Codigo_sunat> mostrar(JTable tabla, String cadena){
        ConexionBD Cbd = new ConexionBD();
        
         PreparedStatement ps=null;
        ResultSet rs=null;

        List<Codigo_sunat> listcodigo= new ArrayList<>();
        
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
         for (int i = 0; i < tabla.getRowCount(); i++) {
        model.removeRow(i);
        i-=1;
        }
        
    try{

        
        ps=Cbd.conectar().prepareStatement("select * from codigo_sunat_producto where concat(codigo,descripcion) like concat('%',?,'%')");
        ps.setString(1, cadena);
        rs=Cbd.RealizarConsulta(ps);
        Object datosR[] = new Object[2];
        while (rs.next()){
                   
             Codigo_sunat codigo = new Codigo_sunat();  
             codigo.setId(rs.getLong("id_codigosunat"));
             codigo.setCodigo(rs.getString("codigo"));
             codigo.setDescripcion(rs.getString("descripcion"));
             
             datosR[0] = codigo.getCodigo();
             datosR[1] = codigo.getDescripcion();

            model.addRow(datosR);
            listcodigo.add(codigo);
		
        }
       
             
        TableColumnModel columnModel = tabla.getColumnModel();
     
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(500);
        if(model.getRowCount()>0){
            tabla.setRowSelectionInterval(0, 0);
        
        }

       
     
       
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{

                Cbd.desconectar();
            }    
      return listcodigo;
        
    
    }
    
}
