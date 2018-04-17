/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.DetalleOrdeSalidaEntrada;
import Pojos.Producto;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author info2017
 */
public class DetalleOrdenSalidaDAO{
    
     
    
    public void insertar(List<Producto> listdet,long idorden,JLabel mens,JButton salir){
        ConexionBD Cbd = new ConexionBD();
         List<Object> retur= new ArrayList<>();
        
        Statement st=null;
//        ResultSet rs;
//        long id =0;
        OrdenSalidaDAO daoorden = new OrdenSalidaDAO();
         Runnable miRunnable = new Runnable()
      {
         @Override
         public void run()
         {
            
            PreparedStatement ps = null;
            try
            {
               
               int i=1;
               mens.setVisible(true);
               salir.setEnabled(false);
               mens.setText("Iniciando proceso ...");
//               mens.append(System.getProperty("line.separator"));
                for(Producto det:listdet){
                    String sql=("SELECT * from sp_insertardetalleordensalida(?,?,?)"); 
                    ps=Cbd.conectar().prepareStatement(sql);
//                    System.out.println("idprodinsert"+det.getIdproducto());
                    mens.setText("Insertando Item "+i);
//                    mens.append(System.getProperty("line.separator"));
                    ps.setLong(1, det.getIdproducto());
//                    System.out.println("cantinsert"+det.getCantidad());
                    ps.setBigDecimal(2,new BigDecimal(det.getCantidad()));
                    ps.setLong(3, idorden);

                    Cbd.actualizarDatos(ps);

                    i++;
//                    mens.setCaretPosition(mens.getDocument().getLength());
                    }
                  
                    mens.setText("Imprimiendo Orden de Salida ...");
//                    mens.append(System.getProperty("line.separator"));
                    Thread.sleep(500);
                    daoorden.imprimir(idorden);
                    mens.setText("Orden N° "+idorden+" Generada con exito");
//                    mens.append(System.getProperty("line.separator"));
//                    mens.setCaretPosition(mens.getDocument().getLength());
                    Thread.sleep(500);
                    mens.setVisible(false);
                    salir.setEnabled(true);
               
               
               
            }
            catch (SQLException e)
            {
            } catch (InterruptedException ex) {
                 Logger.getLogger(DetalleOrdenSalidaDAO.class.getName()).log(Level.SEVERE, null, ex);
             }finally{
            Cbd.desconectar();
     }
         }
      };
      Thread hilo = new Thread (miRunnable);
      hilo.start();
   
   
}
     
public void eliminar(DetalleOrdeSalidaEntrada det){
    
        ConexionBD Cbd = new ConexionBD();
        Statement st=null;       
        PreparedStatement ps=null;
//        boolean valida=false;
     try{
        
       
            
          String sql=("SELECT * from sp_eliminardetorden(?,?,?)"); 
        
            ps=Cbd.conectar().prepareStatement(sql);
            ps.setLong(1, det.getId());
            ps.setBigDecimal(2,new BigDecimal(det.getCantidad()));
            ps.setLong(3, det.getIdproducto());
            Cbd.actualizarDatos(ps);
 

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();
            
     }
    
    }

    
}
