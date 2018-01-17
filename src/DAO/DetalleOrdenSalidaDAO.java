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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author info2017
 */
public class DetalleOrdenSalidaDAO{
    
    
    public void insertar(List<DetalleOrdeSalidaEntrada> listdet,long idorden,JTextArea mens,JButton salir){
        
         List<Object> retur= new ArrayList<>();
        Conexion conexion= new Conexion();
        Statement st=null;
//        ResultSet rs;
//        long id =0;
        OrdenSalidaDAO daoorden = new OrdenSalidaDAO();
        
        // imprimir OUTPUT en jtextarea    
        
//        PrintStream standardOut;
//        PrintStream printStream = new PrintStream(new PrintOutput(mens));
//         standardOut = System.out;
//        System.setOut(printStream);
//        System.setErr(printStream);
        
         

         Runnable miRunnable = new Runnable()
      {
         @Override
         public void run()
         {
            
            PreparedStatement ps = null;
            try
            {
//               System.out.println("Me han pulsado");
//               Thread.sleep(10000); //Tarea que consume diez segundos.
//               System.out.println("Terminé");
               
               int i=1;
               salir.setEnabled(false);
               mens.append("Iniciando proceso ...");
               mens.append(System.getProperty("line.separator"));
                for(DetalleOrdeSalidaEntrada det:listdet){
                    String sql=("SELECT * from sp_insertardetalleordensalida(?,?,?)"); 
                    ps=conexion.getConnection().prepareStatement(sql);
//                    System.out.println("idprodinsert"+det.getIdproducto());
                    mens.append("Insertando Item "+i);
                    mens.append(System.getProperty("line.separator"));
                    ps.setLong(1, det.getIdproducto());
//                    System.out.println("cantinsert"+det.getCantidad());
                    ps.setBigDecimal(2,new BigDecimal(det.getCantidad()));
                    ps.setLong(3, idorden);

                    ps.executeQuery();

                    i++;
                    mens.setCaretPosition(mens.getDocument().getLength());
                    }
                    ps.close();
                    mens.append("Imprimiendo Orden de Salida ...");
                    mens.append(System.getProperty("line.separator"));
                    Thread.sleep(500);
                    daoorden.imprimir(idorden);
                    mens.append("Orden N° "+idorden+" Generada con exito");
                    mens.append(System.getProperty("line.separator"));
                    mens.setCaretPosition(mens.getDocument().getLength());
                    salir.setEnabled(true);
               
               
               
            }
            catch (SQLException e)
            {
            } catch (InterruptedException ex) {
                 Logger.getLogger(DetalleOrdenSalidaDAO.class.getName()).log(Level.SEVERE, null, ex);
             }finally{
            conexion.devolverConexionPool();
     }
         }
      };
      Thread hilo = new Thread (miRunnable);
      hilo.start();
   
     
     
         
        
      
//        if  (rs.next()){
//            
//           
//            id=(rs.getLong("iddet"));
//            //JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSAAAA");
//          
//        }
       
       

        
          
            
//return id;    
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
