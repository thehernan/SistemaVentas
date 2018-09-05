/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.SucursalSingleton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author HERNAN
 */
public class KardexDAO {
    

     ConexionBD Cbd = new ConexionBD();
     SucursalSingleton sucur= SucursalSingleton.getinstancia();
     public void mostrar(String cadena,JTable tabla)
     {
         DefaultTableModel modelo= (DefaultTableModel)tabla.getModel();
//          DefaultTableModel modelo= new DefaultTableModel(
//                new String[]{"Documento","Codigo","Descripcion","Unid. med","Movimiento","Cantidad","Fecha"}, 0) {
//
//             public boolean isCellEditable(int row, int column) {
//    //        //      if (column == 5) return true;
//    //        //else
//            return false;
//            }
//            };
            for (int i = 0; i < modelo.getRowCount(); i++) {
                modelo.removeRow(i);
                i-=1;
            }
      
         ResultSet rs=null;
         PreparedStatement ps=null;
         
         
         
        try{

            
            ps= Cbd.conectar().prepareStatement("SELECT * from sp_mostrarkardex(?,?)");
            ps.setLong(1, sucur.getId());
            ps.setString(2, cadena);
            rs= Cbd.RealizarConsulta(ps);
            Object datosR[] = new Object[7];
            while (rs.next()){
                 datosR[0] =rs.getString("vdoc");
                 datosR[1] =rs.getString("vcodigo");
                 datosR[2] =rs.getString("vdescrip");
                 datosR[3] =rs.getString("vmedida");
                 datosR[4] =rs.getString("vop");
                 datosR[5] =rs.getString("vsaldo");
                 datosR[6] =rs.getString("vfecha");
                modelo.addRow(datosR);
              
                    
            }
            
//            tabla.setModel(modelo);
            TableColumnModel columnModel = tabla.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(100);
            columnModel.getColumn(1).setPreferredWidth(100);
            columnModel.getColumn(2).setPreferredWidth(450);
            columnModel.getColumn(3).setPreferredWidth(100);
            columnModel.getColumn(4).setPreferredWidth(100);
            columnModel.getColumn(5).setPreferredWidth(80);
            columnModel.getColumn(5).setPreferredWidth(50);
           
            } catch(Exception e )
                {
                JOptionPane.showMessageDialog(null, e.getMessage());
                }finally{
                     
                      Cbd.desconectar();
                      
 
                      
                } 
     }
}

