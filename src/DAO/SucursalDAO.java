/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Pojos.Sucursal;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author info2017
 */
public class SucursalDAO {
    
    public List<Sucursal> llenarcombo(JComboBox combo){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        
        Conexion conexion = new Conexion();
        List<Sucursal> listsucursal= new ArrayList<>();
        Statement st=null;
        ResultSet rs=null;
        
     
    try{
	st= conexion.getConnection().createStatement();
      
        rs=st.executeQuery("SELECT * from sp_mostrarsucursal()"); 
//        Object datosR[] = new Object[1];
        modelo.addElement("<<SELECCIONE>>");
        listsucursal.add(new Sucursal());
        while (rs.next()){
            Sucursal sucursal = new Sucursal();
            sucursal.setId(rs.getLong("id"));
            sucursal.setNombre(rs.getString("vnombre"));
            sucursal.setDireccion(rs.getString("vdireccion"));
            sucursal.setTelefono(rs.getString("vtelefono"));
            sucursal.setStockmin(rs.getDouble("vstockmin"));
//                     for(int i =0; i<=1; i++){
            modelo.addElement(rs.getObject("vnombre"));
            listsucursal.add(sucursal);
//                     i++;
                    
//                    modelo.addElement(datosR);
//		}
        }
        combo.setModel(modelo);
	
        } catch(Exception e)
            {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    
    return listsucursal;
    }
    
    public void editarstockmin(long idsucur,double stockmin){
 
        PreparedStatement preparedStatement = null;
       
        Conexion conexion = new Conexion();
      
        
     try{
       
            String insertImageSql = "SELECT * from sp_editarstockmin(?,?)";

            preparedStatement = conexion.getConnection().prepareStatement(insertImageSql);
            
            preparedStatement.setLong(1,idsucur);
            preparedStatement.setBigDecimal(2, new BigDecimal(stockmin));
       
           
            preparedStatement.execute();
            
         
            preparedStatement.close();
        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
         
            conexion.devolverConexionPool();
            
     }
     
}
    
}
