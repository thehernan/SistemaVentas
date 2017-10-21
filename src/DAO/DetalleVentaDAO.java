/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;

import Pojos.DetalleVenta;
import Pojos.Producto;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author info2017
 */
public class DetalleVentaDAO {
    
    public void insertar(List<Producto> listprod,long idventa){
        
        List<Object> retur= new ArrayList<>();
        Conexion conexion= new Conexion();
        Statement st=null;
        ResultSet rs=null;
        long id=0;
     try{
         for(Producto prod :listprod ){
//          System.out.println("SELECT * from sp_insertardetalleventa("+detventa.getIdproducto()+","+detventa.getPrecio()+","+detventa.getCantidad()+","+detventa.getIdventa()+")");
        String sql=("SELECT * from sp_insertardetalleventa("+prod.getIdproducto()+","+prod.getPrecio()+","+prod.getCantidad()+","+idventa+")"); 
        PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
        rs = ps.executeQuery();
         
         }
        
      
//        if  (rs.next()){
//            
////            valida=rs.getBoolean("vvalida");
////            retur.add(valida);
////            if(valida==true){
//           id=(rs.getLong("iddetventa"));
//            //JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSAAAA");
////            }else {
////                
////             JOptionPane.showMessageDialog(null,"NO CUENTA CON STOCK PARA LA VENTA");
////            }
// 
//        }

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }
//return id;    
}
    public void eliminar(Producto prod){
    
    Conexion conexion= new Conexion();
        Statement st=null;
        ResultSet rs=null;
//        boolean valida=false;
     try{
        
         
        String sql=("SELECT * from sp_eliminardetventa(?,?)"); 
        
        PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
      
        ps.setBigDecimal(1,new BigDecimal(prod.getCantidad()));
        ps.setLong(2, prod.getIdproducto());
        rs = ps.executeQuery();
         
     
      
//        if  (rs.next()){
//            //JOptionPane.showMessageDialog(null,"PRODUCTO RETIRADO DE LA VENTA CORRECTAMENTE");
//
// 
//        }

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }
    
    }

}
