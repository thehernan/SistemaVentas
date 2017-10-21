/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Pojos.DetalleCaja;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author info2017
 */
public class DetalleCajaDAO {
    
    public void insertar(DetalleCaja detallecaja,String tipo,String estado){
 
         
        Conexion conexion = new Conexion();
        Object idventa=null;
        Object idrepara=null;
     try{
         System.out.println("tipoinsertar"+tipo);
            if(tipo.equals("VENTA")){
            idventa=detallecaja.getIdventa();
            idrepara= null;
            }else { if(tipo.equals("REPARACION")){
            idrepara=detallecaja.getIdreparacion();
            idventa=null;
            
            
            } }
         
            System.out.println("SELECT * from sp_insertardetallecaja("+idventa+","+idrepara+",'"+detallecaja.getDocumento()+"','"+detallecaja.getNumero()+"','"+ detallecaja.getIdcaja()+"',"+detallecaja.getPagocon()+",'"+estado+"',"+detallecaja.getAbono()+")");
            String insertImageSql = "SELECT * from sp_insertardetallecaja("+idventa+","+idrepara+",'"+detallecaja.getDocumento()+"','"+detallecaja.getNumero()+"','"+ detallecaja.getIdcaja()+"',"+detallecaja.getPagocon()+",'"+estado+"',"+detallecaja.getAbono()+")";

           PreparedStatement ps = conexion.getConnection().prepareStatement(insertImageSql);                  
          
           
           
            ps.execute();
            ps.close();
            

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            conexion.devolverConexionPool();
            
     }

}
    public void mostrar(JTable tabla,Long idcaja, JFormattedTextField total,
            JFormattedTextField aperturo,JFormattedTextField cierre){
    
//         DefaultTableModel modelo= new DefaultTableModel(){
//             Class[] types = new Class[]{
//                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class
//                     ,java.lang.Object.class,java.lang.Boolean.class
//            };
// 
//        public boolean isCellEditable(int row, int column) {
//        //      if (column == 5) return true;
//        //else
//        return false;
//        }
//        };      
//        String titulos[]={"DOCUMENTO","NUMERO","NOMBRES","RUT","IMPORTE","TIPO OP.","ANULADA"};
//        modelo.setColumnIdentifiers(titulos);
        DefaultTableModel modelo= new DefaultTableModel(
                new String[]{"DOCUMENTO","NUMERO","NOMBRES","RUT","IMPORTE","TIPO OP.","ANULADA"}, 0) {
 
            Class[] types = new Class[]{
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Boolean.class
            };
 
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
             public boolean isCellEditable(int row, int column) {
//        //      if (column == 5) return true;
//        //else
            return false;
            }
            };
        tabla.setModel(modelo);
        
         Conexion conexion = new Conexion();
        
         
         try{
             
             System.out.println("SELECT * from sp_mostrardetallecaja("+idcaja+")");
             String sql=("SELECT * from sp_mostrardetallecaja(?)");
             PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
             ps.setLong(1, idcaja);
             ResultSet rs = ps.executeQuery();
             
             String sql1=("SELECT * from sp_mostrardetallecajatotal(?)");
             PreparedStatement ps1=conexion.getConnection().prepareStatement(sql1);
             ps1.setLong(1, idcaja);
             ResultSet rs1 = ps1.executeQuery();
             Object datosR[]= new Object[7];
             
             while (rs.next()){
                 
                  for(int i =0; i<=1; i++){
                         
                     datosR[i] = rs.getObject("vdocumento");
                     i++;
                     datosR[i] = rs.getObject("vnumero");
                     i++;
                     datosR[i] = rs.getObject("vrazons");
                     i++;
                     datosR[i] = rs.getObject("vrut");
                     i++;
                     datosR[i] = rs.getObject("vimporte");
                     i++;
                     datosR[i] = rs.getObject("vtipo");
                     i++;
                     datosR[i] = rs.getBoolean("vanulada");
                     i++;
                    modelo.addRow(datosR);
		}
                  
             }
             if(rs1.next()){
                total.setValue(rs1.getDouble("vtotal"));
                cierre.setValue(rs1.getDouble("vcierra"));
                aperturo.setValue(rs1.getDouble("vapertura"));
             }
             
             ps.close();
             rs.close();            
             ps1.close();
             rs1.close();
         }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }finally{
             conexion.devolverConexionPool();
             
         }
 
}
    
}
