/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Conexion.ConexionBD;
import Pojos.SucursalSingleton;
import Pojos.Ventas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author HERNAN
 */
public class DocumentoDAO {
   
    SucursalSingleton sucursal = SucursalSingleton.getinstancia();
    public String formatnumeric(Object n){
     DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
     simbolos.setDecimalSeparator('.');   
     DecimalFormat nf=new DecimalFormat("#.00",simbolos);
     String num = nf.format(n);
     
     return num;
     }
    
    public List<Ventas> mostrar(long iddoc,String cadena,String op,Timestamp desde, Timestamp hasta,JTable tab)
    {
        ConexionBD Cbd = new ConexionBD();
        DefaultTableModel tabla=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
           //      if (column == 5) return true;
        //else
        return false;
        }
        }; 
        String titulos[]={"Fecha","Documento","Razon Social","TM","Total","Estado"};
        tabla.setColumnIdentifiers(titulos);
        tab.setModel(tabla);
        List<Ventas> listvent=new ArrayList<>();
       DateFormat df = DateFormat.getDateInstance();
        try{

//            System.out.println("SELECT * from sp_busquedasensitivadoc('"+desde+"','"+hasta+"')");
            String sql=("SELECT * from sp_busquedasensitivadoc(?,?,?,?,?,?)"); 
            PreparedStatement ps= Cbd.conectar().prepareStatement(sql);
            ps.setString(1, cadena);
            ps.setLong(2, sucursal.getId());
            ps.setTimestamp(3, desde);
            ps.setTimestamp(4, hasta);
            ps.setString(5, op);
            ps.setLong(6, iddoc);
            ResultSet rs= Cbd.RealizarConsulta(ps);
            Object datosR[] = new Object[6];
           
         
            while (rs.next()){
                Ventas vent= new Ventas();
                vent.setIdventa(rs.getLong("vidventa"));
                 datosR[0] = rs.getObject("vfecha");

                 datosR[1] = rs.getString("vserie")+" - "+rs.getObject("vnumero");

                 datosR[2] = rs.getString("vrazons");

                 datosR[3] = rs.getObject("vtotal");

                 datosR[4] =formatnumeric(rs.getObject("vimporte"));

                 datosR[5] =(rs.getObject("vestadosunat"));

              

                listvent.add(vent);
                tabla.addRow(datosR);

              
            }

            tab.setModel(tabla); 
            TableColumnModel columnModel = tab.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(80);
            columnModel.getColumn(1).setPreferredWidth(80);
            columnModel.getColumn(2).setPreferredWidth(400);
            columnModel.getColumn(3).setPreferredWidth(80);
            columnModel.getColumn(4).setPreferredWidth(80);
            columnModel.getColumn(5).setPreferredWidth(80);
        
            rs.close();
            ps.close();
//            if(cont == 0){
//                JOptionPane.showMessageDialog(null,"No se encontrarón documentos en las fechas "+df.format(desde)+" , "+df.format(hasta)
//                );
//            }
            } catch(Exception e)
                {
                JOptionPane.showMessageDialog(null, e.getMessage());
                }finally{
                   Cbd.desconectar();

                }    
    return listvent;
    }
    
    
}
