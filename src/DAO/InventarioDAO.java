/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author HERNAN
 */
public class InventarioDAO {
    
    
public List<Producto> mostrar(JTable tabla,String cadena,long idsucur,Timestamp desde,Timestamp hasta){
    ConexionBD Cbd = new ConexionBD();
    DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
     for (int i = 0; i < modelo.getRowCount(); i++) {
        modelo.removeRow(i);
        i-=1;
        }
    TableColumnModel columnModel = tabla.getColumnModel();
//     if (columnModel.getColumnCount()>0){
    columnModel.getColumn(0).setPreferredWidth(120);
    columnModel.getColumn(1).setPreferredWidth(550);
    columnModel.getColumn(2).setPreferredWidth(100);
    columnModel.getColumn(3).setPreferredWidth(100);
    columnModel.getColumn(4).setPreferredWidth(100);
    columnModel.getColumn(5).setPreferredWidth(100);
    
//     }
   
    ResultSet rs=null;
    PreparedStatement ps=null; 
     List<Producto> listprod = new ArrayList<>();
    try{
	
        String sql=("SELECT * from sp_mostrarnuevoinventario(?,?,?,?)"); 
        ps= Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, idsucur);
        ps.setString(2, cadena);
        ps.setTimestamp(3,desde );
        ps.setTimestamp(4, hasta);
        rs= Cbd.RealizarConsulta(ps);
        Object datosR[] = new Object[6];
        while (rs.next()){
//             Producto prod = new Producto();
//            prod.setIdproducto(rs.getLong("id"));
//            prod.setCodigo(rs.getString("vcodigo"));
//            prod.setDescripcion(rs.getString("vdescripcion"));
            datosR[0]=rs.getString(1);
            datosR[1]=rs.getString(2);
            datosR[2]=rs.getString(3);
            datosR[3]=rs.getString(4);
            datosR[4]=rs.getString(5);
            datosR[5]=rs.getString(6);        
            modelo.addRow(datosR);
            
        }
        
//        if(modelo.getRowCount()>0){
//            tabla.setRowSelectionInterval(0, 0);
//        
//        }
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
        try {
            if(rs!=null)
            {
                rs.close();
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(InventarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            }    
    return listprod;
    }
    
}
