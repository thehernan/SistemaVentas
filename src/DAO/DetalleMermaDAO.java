/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Pojos.DetalleMerma;
import Pojos.DetalleOrdeSalidaEntrada;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author info2017
 */
public class DetalleMermaDAO {
    
     public List<Object> insertar(DetalleMerma detmerma){
        
         List<Object> retur= new ArrayList<>();
        Conexion conexion= new Conexion();
        Statement st=null;
        ResultSet rs;
       boolean valida;
     try{        
        String sql=("SELECT * from sp_insertardetallemerma(?,?,?)"); 
        PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, detmerma.getIdproducto());
        ps.setBigDecimal(2,new BigDecimal(detmerma.getCantidad()));
        ps.setLong(3, detmerma.getIdmerma());
        
        rs = ps.executeQuery();
      
        if  (rs.next()){
            
            valida=rs.getBoolean("vvalida");
            retur.add(valida);
            if(valida==true){
            retur.add(rs.getLong("iddet"));
            //JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSAAAA");
            }else {
                
             JOptionPane.showMessageDialog(null,"NO CUENTA CON STOCK PARA RETIRAR EL PRODUCTO O YA FUE VENDIDO");
            } 
        }
        ps.close();
        rs.close();

        }
     catch(SQLException | HeadlessException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            conexion.devolverConexionPool();
     }
 return retur;      
}
     public void eliminar(DetalleMerma detmerma){
    
    Conexion conexion= new Conexion();
        Statement st=null;
        ResultSet rs=null;
//        boolean valida=false;
     try{
        
        String sql=("SELECT * from sp_eliminardetmerma(?,?,?)"); 
        
        PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, detmerma.getIddetallemerma());
        ps.setBigDecimal(2,new BigDecimal(detmerma.getCantidad()));
        ps.setLong(3, detmerma.getIdproducto());
        rs = ps.executeQuery();
      
        if  (rs.next()){
            JOptionPane.showMessageDialog(null,"PRODUCTO RETIRADO CORRECTAMENTE");

 
        }
        rs.close();
        ps.close();

        }
     catch(SQLException | HeadlessException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            conexion.devolverConexionPool();
            
     }
    
}
}
