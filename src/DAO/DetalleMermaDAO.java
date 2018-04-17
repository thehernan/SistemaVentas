/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.DetalleMerma;
import Pojos.Producto;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author info2017
 */
public class DetalleMermaDAO {
    
    
    
     public void insertar(List<Producto> listdet,JLabel msj,long idmerma){
        
      
      ConexionBD Cbd = new ConexionBD();
      Runnable miRunnable = new Runnable()
      {
         @Override
         public void run()
         {
     try{
        int cont =1;
        PreparedStatement ps=null;
       
        msj.setVisible(true);
         for(Producto det: listdet){
         
            String sql=("SELECT * from sp_insertardetallemerma(?,?,?)"); 
            ps=Cbd.conectar().prepareStatement(sql);
            ps.setLong(1, det.getIdproducto());
            ps.setBigDecimal(2,new BigDecimal(det.getCantidad()));
            ps.setLong(3, idmerma);
        
            Cbd.actualizarDatos(ps);
            msj.setText("Insertando Item "+cont);
            cont++;
         }
        msj.setVisible(false);
      
//        if  (rs.next()){
//            
//            valida=rs.getBoolean("vvalida");
//            retur.add(valida);
//            if(valida==true){
//            retur.add(rs.getLong("iddet"));
//            //JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSAAAA");
//            }else {
//                
//             JOptionPane.showMessageDialog(null,"NO CUENTA CON STOCK PARA RETIRAR EL PRODUCTO O YA FUE VENDIDO");
//            } 
//        }
        
      

        }
     catch(SQLException | HeadlessException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();
     }
      }
      };
      Thread hilo = new Thread (miRunnable);
      hilo.start();
 
}
     
     
    public void eliminar(DetalleMerma detmerma){
    
        ConexionBD Cbd = new ConexionBD();
        Statement st=null;
        ResultSet rs=null;
//        boolean valida=false;
     try{
        
        String sql=("SELECT * from sp_eliminardetmerma(?,?,?)"); 
        
        PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, detmerma.getIddetallemerma());
        ps.setBigDecimal(2,new BigDecimal(detmerma.getCantidad()));
        ps.setLong(3, detmerma.getIdproducto());
        rs = Cbd.RealizarConsulta(ps);
      
        if  (rs.next()){
            JOptionPane.showMessageDialog(null,"PRODUCTO RETIRADO CORRECTAMENTE");

 
        }
       

        }
     catch(SQLException | HeadlessException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
           Cbd.desconectar();
            
     }
    
}
}
