/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Pojos.DetalleOrdeSalidaEntrada;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author info2017
 */
public class DetalleOrdenEntradaDAO {
     public void insertar(List<DetalleOrdeSalidaEntrada> detalleorden ,long idorden,long idsucur){
        
         System.out.println("com"+detalleorden.size());
       Conexion conexion = new Conexion();
        Iterator<DetalleOrdeSalidaEntrada> it= detalleorden.iterator();
     try{
           
          for(DetalleOrdeSalidaEntrada det: detalleorden){
              
              System.out.println("SELECT * from sp_insertardetalleordenentrada("+det.getIdproducto()+","+det.getCantidad()+","+idorden+","+idsucur+")");  
            String insertImageSql = "SELECT * from sp_insertardetalleordenentrada(?,?,?,?)";

            PreparedStatement ps = conexion.getConnection().prepareStatement(insertImageSql);
            
            ps.setLong(1,det.getIdproducto());
            ps.setBigDecimal(2,new BigDecimal(det.getCantidad()));
            ps.setLong(3,idorden);
            ps.setLong(4, idsucur);
            ps.execute();
            ps.close();
          }
           
           

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            conexion.devolverConexionPool();
     }

    
}
}
