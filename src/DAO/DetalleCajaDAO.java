/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ClasesGlobales.FormatoNumerico;
import Conexion.ConexionBD;
import Pojos.Caja;
import Pojos.DetalleCaja;
import java.math.BigDecimal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author info2017
 */
public class DetalleCajaDAO {
    
     ConexionBD Cbd = new ConexionBD();
     FormatoNumerico fn = new FormatoNumerico();
    
    public void insertar(DetalleCaja detallecaja,String serie,String numero){
         ConexionBD Cbd = new ConexionBD();
//          Connection cnn = Cbd.getConexion();
//          boolean estado=false;
//           try {
//                cnn.setAutoCommit(false);
//           } catch (SQLException e) {
//               JOptionPane.showMessageDialog(null, e.getMessage(),"",JOptionPane.ERROR_MESSAGE);
//           }
           PreparedStatement ps=null;
//           ResultSet rs=null;
//        Object idventa=null;
//        Object idrepara=null;
     try{
//         System.out.println("tipoinsertar"+tipo);
//            if(tipo.equals("VENTA")){
//            idventa=detallecaja.getIdventa();
//            idrepara= null;
//            }
//            
//            else { if(tipo.equals("REPARACION")){
//            idrepara=detallecaja.getIdreparacion();
//            idventa=null;
//            
//            
//            } }
            
         
//            System.out.println("SELECT * from sp_insertardetallecaja("+idventa+","+idrepara+",'"+detallecaja.getDocumento()+"','"+detallecaja.getNumero()+"','"+ detallecaja.getIdcaja()+"',"+detallecaja.getPagocon()+",'"+estado+"',"+detallecaja.getAbono()+")");
            String insertImageSql = "SELECT * from sp_insertardetallecaja(?,?,?,?,?,?)";
            

           ps =Cbd.conectar().prepareStatement(insertImageSql);                  
           ps.setLong(1, detallecaja.getIdventa());
           ps.setLong(2, detallecaja.getIdcaja());
           ps.setBigDecimal(3,new BigDecimal(detallecaja.getPagocon()));
//           ps.setString(4, estado);
           ps.setBigDecimal(4,new BigDecimal(detallecaja.getAbono()));
           ps.setString(5, serie);
           ps.setString(6, numero);
           
           Cbd.RealizarConsulta(ps);
           
//           if(rs.next())
//           {
//               venta.setSerie(rs.getString("vserie"));
//               venta.setNumero(rs.getString("vnumero"));
//           }
           
           ////////////// documento sunat
          
//            ConsumingPost post=new ConsumingPost(venta,listprod);
//            
//            if(post.apiConsume()==true)
//            {
//                cnn.commit();
//                estado=true;
//            }else {
//                cnn.rollback();
//            }
          
            //////////////////////////////////////////7
            

        }
     catch(SQLException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
             
            }finally{
           Cbd.desconectar();
            
     }
    
    }
     
    public void insertaregreso(DetalleCaja detallecaja){    
        ConexionBD Cbd = new ConexionBD();
     try{
               
            
            String insertImageSql = "SELECT * from sp_insertardetallecajaegreso(?,?,?)";
            

           PreparedStatement ps = Cbd.conectar().prepareStatement(insertImageSql);                  
           ps.setLong(1, detallecaja.getIdcaja());
           ps.setString(2, detallecaja.getMotivoanulacion());
           ps.setBigDecimal(3,new BigDecimal(detallecaja.getAbono()));
          
           
           
            Cbd.RealizarConsulta(ps);
          

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();
            
     }

}
    public Caja mostrar(JTable tabla,long idcaja){
    
        ConexionBD Cbd = new ConexionBD();
        DefaultTableModel modelo= new DefaultTableModel(
                new String[]{"Documento","Numero","Cliente","Total","Tipo OP.","Motivo"}, 0) {
 
            Class[] types = new Class[]{
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class
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
        
        Caja c = null;
        
         
         try{
             
             System.out.println("SELECT * from sp_mostrardetallecaja("+idcaja+")");
             String sql=("SELECT * from sp_mostrardetallecaja(?)");
             PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
             ps.setLong(1, idcaja);
             ResultSet rs = Cbd.RealizarConsulta(ps);
             
             String sql1=("SELECT * from sp_mostrardetallecajatotal(?)");
             PreparedStatement ps1=Cbd.conectar().prepareStatement(sql1);
             ps1.setLong(1, idcaja);
             ResultSet rs1 = ps1.executeQuery();
             Object datosR[]= new Object[8];
             
             while (rs.next()){
                 
                         
                     datosR[0] = rs.getObject("vdocumento");
                    
                     datosR[1] = rs.getObject("vnumero");
                    
                     datosR[2] = rs.getObject("vrazons");
                  
//                     datosR[3] = rs.getObject("vrut");
                  
                     datosR[3] = fn.FormatoN(rs.getDouble("vimporte"));
                 
                     datosR[4] = rs.getObject("vtipo");
                     datosR[5] = rs.getObject("vmotivo");
                  
//                     datosR[7] = rs.getBoolean("vanulada");
               
                    modelo.addRow(datosR);
		
                  
             }
             if(rs1.next()){
                 c= new Caja();
                 c.setTotal(rs1.getDouble("vtotal"));
                 c.setCierradinero(rs1.getDouble("vcierra"));
                 c.setAperturadinero(rs1.getDouble("vapertura"));
                 c.setCajero(rs1.getString("vcajero"));
               
             }
             
             TableColumnModel columnModel = tabla.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(100);
            columnModel.getColumn(1).setPreferredWidth(100);
            columnModel.getColumn(2).setPreferredWidth(600);
            columnModel.getColumn(3).setPreferredWidth(100);
            columnModel.getColumn(4).setPreferredWidth(100);
            columnModel.getColumn(5).setPreferredWidth(100);
         
             
             ps.close();
             rs.close();            
             ps1.close();
             rs1.close();
         }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }finally{
            Cbd.desconectar();
             
         }
         return c;
 
}
    
}
