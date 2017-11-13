/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
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
public class DetalleOrdenSalidaDAO {
     public long insertar(DetalleOrdeSalidaEntrada orsalida){
        
         List<Object> retur= new ArrayList<>();
        Conexion conexion= new Conexion();
        Statement st=null;
        ResultSet rs;
        long id =0;
     try{        
        String sql=("SELECT * from sp_insertardetalleordensalida(?,?,?)"); 
        PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, orsalida.getIdproducto());
        System.out.print("cantinsert"+orsalida.getCantidad());
        ps.setBigDecimal(2,new BigDecimal(orsalida.getCantidad()));
        ps.setLong(3, orsalida.getIdordensalidaentrada());
        
        rs = ps.executeQuery();
      
        if  (rs.next()){
            
           
            id=(rs.getLong("iddet"));
            //JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSAAAA");
          
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
return id;    
}
     
public void eliminar(DetalleOrdeSalidaEntrada det){
    
    Conexion conexion= new Conexion();
        Statement st=null;
        ResultSet rs=null;
        PreparedStatement ps=null;
//        boolean valida=false;
     try{
        
       
            
          String sql=("SELECT * from sp_eliminardetorden(?,?,?)"); 
        
            ps=conexion.getConnection().prepareStatement(sql);
            ps.setLong(1, det.getId());
            ps.setBigDecimal(2,new BigDecimal(det.getCantidad()));
            ps.setLong(3, det.getIdproducto());
            rs = ps.executeQuery();
        
      
//      
//        if  (rs.next()){
//            JOptionPane.showMessageDialog(null,"PRODUCTO RETIRADO DE LA VENTA CORRECTAMENTE");
//
// 
//        }
        rs.close();
        ps.close();

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            conexion.devolverConexionPool();
            
     }
    
    }

    
}
