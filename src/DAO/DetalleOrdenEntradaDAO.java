/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.DetalleOrdeSalidaEntrada;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextArea;

/**
 *
 * @author info2017
 */
public class DetalleOrdenEntradaDAO {
    
    
    
     public void insertar(List<DetalleOrdeSalidaEntrada> detalleorden ,long idorden,long idsucur,JTextArea mens,JButton salir){
        ConexionBD Cbd = new ConexionBD();
        System.out.println("com"+detalleorden.size());
       
//        Iterator<DetalleOrdeSalidaEntrada> it= detalleorden.iterator();
       
       OrdenSalidaDAO daoorden = new OrdenSalidaDAO();
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
              
                
                for(DetalleOrdeSalidaEntrada det: detalleorden){
                    mens.append("Insertando producto "+i);
                    mens.append(System.getProperty("line.separator"));
                    System.out.println("SELECT * from sp_insertardetalleordenentrada("+det.getIdproducto()+","+det.getCantidad()+","+idorden+","+idsucur+")");  
                    String insertImageSql = "SELECT * from sp_insertardetalleordenentrada(?,?,?,?)";

                    ps = Cbd.conectar().prepareStatement(insertImageSql);

                    ps.setLong(1,det.getIdproducto());
                    ps.setBigDecimal(2,new BigDecimal(det.getCantidad()));
                    ps.setLong(3,idorden);
                    ps.setLong(4, idsucur);
                    Cbd.actualizarDatos(ps);
                    mens.setCaretPosition(mens.getDocument().getLength());
                    i++;
          }
                
                
                
                
                  
                    mens.append("Imprimiendo Orden de Entrada ...");
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
            Cbd.desconectar();
     }
         }
      };
      Thread hilo = new Thread (miRunnable);
      hilo.start();
   
       
    
}
}
