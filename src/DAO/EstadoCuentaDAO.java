/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author info2017
 */
public class EstadoCuentaDAO {
    
    public void mostrardebe(JTable tabla,long idsucur){
        
    DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
  //      if (column == 5) return true;
  //else
   return false;
  }
  };      
 String titulos[]={"ID","NOMBRE RAZON S.","RUT","TOTAL","ABONO","SALDO"};
 modelo.setColumnIdentifiers(titulos);
 tabla.setModel(modelo);
 tabla.getColumnModel().getColumn(0).setMaxWidth(0);
 tabla.getColumnModel().getColumn(0).setMinWidth(0);
 tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
  Conexion conexion = new Conexion();
          
     
    try{
	
      
        String sql=("SELECT * from sp_estadocuentadebe(?)"); 
        PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idsucur);
        ResultSet rs = ps.executeQuery();
        Object datosR[] = new Object[6];
        while (rs.next()){
                     for(int i =0; i<=1; i++){
                         
                     datosR[i] = rs.getObject("vidproveedor");
                     i++;
                     datosR[i] = rs.getObject("vnombre");
                     i++;
                     datosR[i] = rs.getObject("vrut");
                     i++;
                     datosR[i] = rs.getObject("vtotal");
                     i++;
                     datosR[i] = rs.getObject("vabono");
                     i++;
                     datosR[i] = rs.getObject("vsaldo");
                     i++;
                    
                    modelo.addRow(datosR);
		}
        }
	ps.close();
        rs.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    

    
    }
    public void imprimirdebe(long idprove,long idsucur){
try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////////////       
            //Connection miconexion = conectar.Connect();
            Conexion conexion = new Conexion();
                      
            String  rutaInforme  = "src/Reportes/EstadoCuentaDebe.jasper";
            
            Map parametros = new HashMap();
           
            parametros.put("idsucursal",  idsucur);
            parametros.put("idproveedor",  idprove);
//            parametros.put("fecha",fecha);
//            parametros.put("motivodet", motdet);
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,conexion.getConnection());
            JasperViewer jv = new JasperViewer(informe,false);  
        
             jv.setVisible(true);
             jv.setTitle(rutaInforme);
          
        }catch (HeadlessException | JRException ex) {
        JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE", "ERROR",JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null,ex.getMessage());
        }
     

}
    public void imprimirhaber(long idsucur,long idcliente ){
try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////+33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333////////       
            //Connection miconexion = conectar.Connect();
            Conexion conexion = new Conexion();
                      
            String  rutaInforme  = "src/Reportes/EstadoCuentaHaber.jasper";
            
            Map parametros = new HashMap();
           
            parametros.put("idsucursal",  idsucur);
            parametros.put("idcliente",  idcliente);
//            parametros.put("fecha",fecha);
//            parametros.put("motivodet", motdet);
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,conexion.getConnection());
            JasperViewer jv = new JasperViewer(informe,false);  
        
             jv.setVisible(true);
             jv.setTitle(rutaInforme);
          
        }catch (HeadlessException | JRException ex) {
        JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE", "ERROR",JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null,ex.getMessage());
        }
     

}
    public void mostrarhaber(JTable tabla,long idsucur){
        
    DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
  //      if (column == 5) return true;
  //else
   return false;
  }
  };      
 String titulos[]={"ID","NOMBRE RAZON S.","RUT","CELULAR","IMPORTE","ABONO","SALDO"};
 modelo.setColumnIdentifiers(titulos);
 tabla.setModel(modelo);
 tabla.getColumnModel().getColumn(0).setMaxWidth(0);
 tabla.getColumnModel().getColumn(0).setMinWidth(0);
 tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
  Conexion conexion = new Conexion();
          
     
    try{
	
      
        String sql=("SELECT * from sp_estadocuentahaber(?)"); 
        PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idsucur);
        ResultSet rs = ps.executeQuery();
        Object datosR[] = new Object[7];
        while (rs.next()){
                     for(int i =0; i<=1; i++){
                         
                     datosR[i] = rs.getObject("vidcliente");
                     i++;
                     datosR[i] = rs.getObject("vnombre");
                     i++;
                     datosR[i] = rs.getObject("vrut");
                     i++;
                     datosR[i] = rs.getObject("vcelular");
                     i++;
                     datosR[i] = rs.getObject("vimporte");
                     i++;
                     datosR[i] = rs.getObject("vabono");
                     i++;
                     datosR[i] = rs.getObject("vsaldo");
                     i++;
                    
                    modelo.addRow(datosR);
		}
        }
	ps.close();
        rs.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    

    
    }
    
}
