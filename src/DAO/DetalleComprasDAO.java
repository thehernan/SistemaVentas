/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Conexion.Conexion;
import Pojos.DetalleCompras;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author info2017
 */
public class DetalleComprasDAO {
     public void insertar(List<DetalleCompras> compras,long idcompra,JLabel mes){
         Runnable miRunnable = new Runnable()
      {
         @Override
         public void run()
         {
             int i = 1;
              System.out.println("com"+compras.size());
            Conexion conexion = new Conexion();
             //Iterator<DetalleCompras> it= compras.iterator();
            try{

               for(DetalleCompras det: compras){
            
              if (det.getIddetallecompra()==0){
                String insertImageSql = "SELECT * from sp_insertardetallecompra(?,?,?,?,?)";
            
                PreparedStatement ps = conexion.getConnection().prepareStatement(insertImageSql);
                mes.setText("Insertando Item "+i);
                i++;
                ps.setLong(1,det.getIdproducto());
                ps.setBigDecimal(2,new BigDecimal(det.getCantidad()));
                ps.setBigDecimal(3,new BigDecimal(det.getPrecio()));
                ps.setLong(4,idcompra);
                ps.setBigDecimal(5,new  BigDecimal(det.getCantidadacord()));
                ps.execute();
                ps.close();
              }
            
            }

            }
            catch(Exception e)
                   {
                   JOptionPane.showMessageDialog(null, e.getMessage());
                   }finally{
                   conexion.devolverConexionPool();
            }
             
             
             mes.setText(""); 
             
             
             
         }
    };
      Thread hilo = new Thread (miRunnable);
      hilo.start();
      
      
        

}
     
public boolean eliminar(long iddet,long idprod){
  
        Conexion conexion= new Conexion();
        Statement st=null;
        ResultSet rs=null;
        boolean valida=true;
     try{
        
        String sql=("SELECT * from sp_eliminardetallecompra(?,?)"); 
        PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idprod);
        ps.setLong(2, iddet);
        
        rs = ps.executeQuery();
      
        if  (rs.next()){
            
           valida=rs.getBoolean("valida");
            if(valida==false){  
             JOptionPane.showMessageDialog(null,"NO CUENTA CON STOCK, IMPOSIBLE DE ELIMINAR ITEM");
            }
 
        }

        }
     catch(SQLException | HeadlessException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }
  return valida;
}
public void editar(DetalleCompras detcompra){
  
        Conexion conexion= new Conexion();
        Statement st=null;
        ResultSet rs=null;
       
     try{
        
        String sql=("SELECT * from sp_editardetallecompra(?,?,?,?,?)"); 
        PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, detcompra.getIddetallecompra());
        ps.setBigDecimal(2,new BigDecimal(detcompra.getCantidad()));
        ps.setBigDecimal(3,new  BigDecimal(detcompra.getPrecio()));
        ps.setBigDecimal(4,new  BigDecimal(detcompra.getCantidadacord()));
        ps.setLong(5, detcompra.getIdproducto());
        
        rs = ps.executeQuery();
      
        if  (rs.next()){
            
           
             JOptionPane.showMessageDialog(null,"EDITADO EXITOSAMENTE");
           
 
        }

        }
     catch(SQLException | HeadlessException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }
  
}    
    
}
